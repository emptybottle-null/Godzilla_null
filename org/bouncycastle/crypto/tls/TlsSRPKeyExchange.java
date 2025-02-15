/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.bouncycastle.crypto.agreement.srp.SRP6Server;
import org.bouncycastle.crypto.agreement.srp.SRP6Util;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
import org.bouncycastle.crypto.tls.AbstractTlsKeyExchange;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.DefaultTlsSRPGroupVerifier;
import org.bouncycastle.crypto.tls.DigestInputBuffer;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.ServerSRPParams;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.SignerInputBuffer;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsDSSSigner;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsRSASigner;
import org.bouncycastle.crypto.tls.TlsSRPGroupVerifier;
import org.bouncycastle.crypto.tls.TlsSRPLoginParameters;
import org.bouncycastle.crypto.tls.TlsSRPUtils;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.io.TeeInputStream;

public class TlsSRPKeyExchange
extends AbstractTlsKeyExchange {
    protected TlsSigner tlsSigner;
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;
    protected AsymmetricKeyParameter serverPublicKey = null;
    protected SRP6GroupParameters srpGroup = null;
    protected SRP6Client srpClient = null;
    protected SRP6Server srpServer = null;
    protected BigInteger srpPeerCredentials = null;
    protected BigInteger srpVerifier = null;
    protected byte[] srpSalt = null;
    protected TlsSignerCredentials serverCredentials = null;

    protected static TlsSigner createSigner(int n) {
        switch (n) {
            case 21: {
                return null;
            }
            case 23: {
                return new TlsRSASigner();
            }
            case 22: {
                return new TlsDSSSigner();
            }
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    public TlsSRPKeyExchange(int n, Vector vector, byte[] byArray, byte[] byArray2) {
        this(n, vector, new DefaultTlsSRPGroupVerifier(), byArray, byArray2);
    }

    public TlsSRPKeyExchange(int n, Vector vector, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] byArray, byte[] byArray2) {
        super(n, vector);
        this.tlsSigner = TlsSRPKeyExchange.createSigner(n);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = byArray;
        this.password = byArray2;
        this.srpClient = new SRP6Client();
    }

    public TlsSRPKeyExchange(int n, Vector vector, byte[] byArray, TlsSRPLoginParameters tlsSRPLoginParameters) {
        super(n, vector);
        this.tlsSigner = TlsSRPKeyExchange.createSigner(n);
        this.identity = byArray;
        this.srpServer = new SRP6Server();
        this.srpGroup = tlsSRPLoginParameters.getGroup();
        this.srpVerifier = tlsSRPLoginParameters.getVerifier();
        this.srpSalt = tlsSRPLoginParameters.getSalt();
    }

    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        if (this.tlsSigner != null) {
            this.tlsSigner.init(tlsContext);
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert(10);
        }
        if (certificate.isEmpty()) {
            throw new TlsFatalAlert(42);
        }
        org.bouncycastle.asn1.x509.Certificate certificate2 = certificate.getCertificateAt(0);
        SubjectPublicKeyInfo subjectPublicKeyInfo = certificate2.getSubjectPublicKeyInfo();
        try {
            this.serverPublicKey = PublicKeyFactory.createKey(subjectPublicKeyInfo);
        } catch (RuntimeException runtimeException) {
            throw new TlsFatalAlert(43, (Throwable)runtimeException);
        }
        if (!this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
            throw new TlsFatalAlert(46);
        }
        TlsUtils.validateKeyUsage(certificate2, 128);
        super.processServerCertificate(certificate);
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (this.keyExchange == 21 || !(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
        this.processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials)tlsCredentials;
    }

    public boolean requiresServerKeyExchange() {
        return true;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        this.srpServer.init(this.srpGroup, this.srpVerifier, TlsUtils.createHash((short)2), this.context.getSecureRandom());
        BigInteger bigInteger = this.srpServer.generateServerCredentials();
        ServerSRPParams serverSRPParams = new ServerSRPParams(this.srpGroup.getN(), this.srpGroup.getG(), this.srpSalt, bigInteger);
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        serverSRPParams.encode(digestInputBuffer);
        if (this.serverCredentials != null) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
            Digest digest = TlsUtils.createHash(signatureAndHashAlgorithm);
            SecurityParameters securityParameters = this.context.getSecurityParameters();
            digest.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
            digest.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
            digestInputBuffer.updateDigest(digest);
            byte[] byArray = new byte[digest.getDigestSize()];
            digest.doFinal(byArray, 0);
            byte[] byArray2 = this.serverCredentials.generateCertificateSignature(byArray);
            DigitallySigned digitallySigned = new DigitallySigned(signatureAndHashAlgorithm, byArray2);
            digitallySigned.encode(digestInputBuffer);
        }
        return digestInputBuffer.toByteArray();
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = null;
        InputStream inputStream2 = inputStream;
        if (this.tlsSigner != null) {
            signerInputBuffer = new SignerInputBuffer();
            inputStream2 = new TeeInputStream(inputStream, signerInputBuffer);
        }
        ServerSRPParams serverSRPParams = ServerSRPParams.parse(inputStream2);
        if (signerInputBuffer != null) {
            DigitallySigned digitallySigned = this.parseSignature(inputStream);
            Signer signer = this.initVerifyer(this.tlsSigner, digitallySigned.getAlgorithm(), securityParameters);
            signerInputBuffer.updateSigner(signer);
            if (!signer.verifySignature(digitallySigned.getSignature())) {
                throw new TlsFatalAlert(51);
            }
        }
        this.srpGroup = new SRP6GroupParameters(serverSRPParams.getN(), serverSRPParams.getG());
        if (!this.groupVerifier.accept(this.srpGroup)) {
            throw new TlsFatalAlert(71);
        }
        this.srpSalt = serverSRPParams.getS();
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), serverSRPParams.getB());
        } catch (CryptoException cryptoException) {
            throw new TlsFatalAlert(47, (Throwable)cryptoException);
        }
        this.srpClient.init(this.srpGroup, TlsUtils.createHash((short)2), this.context.getSecureRandom());
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        BigInteger bigInteger = this.srpClient.generateClientCredentials(this.srpSalt, this.identity, this.password);
        TlsSRPUtils.writeSRPParameter(bigInteger, outputStream);
        this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), TlsSRPUtils.readSRPParameter(inputStream));
        } catch (CryptoException cryptoException) {
            throw new TlsFatalAlert(47, (Throwable)cryptoException);
        }
        this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
    }

    public byte[] generatePremasterSecret() throws IOException {
        try {
            BigInteger bigInteger = this.srpServer != null ? this.srpServer.calculateSecret(this.srpPeerCredentials) : this.srpClient.calculateSecret(this.srpPeerCredentials);
            return BigIntegers.asUnsignedByteArray(bigInteger);
        } catch (CryptoException cryptoException) {
            throw new TlsFatalAlert(47, (Throwable)cryptoException);
        }
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }
}

