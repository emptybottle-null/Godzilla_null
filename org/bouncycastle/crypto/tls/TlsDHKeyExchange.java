/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.tls.AbstractTlsKeyExchange;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.ServerDHParams;
import org.bouncycastle.crypto.tls.TlsAgreementCredentials;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsDHUtils;
import org.bouncycastle.crypto.tls.TlsDSSSigner;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsRSASigner;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;

public class TlsDHKeyExchange
extends AbstractTlsKeyExchange {
    protected TlsSigner tlsSigner;
    protected DHParameters dhParameters;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsAgreementCredentials agreementCredentials;
    protected DHPrivateKeyParameters dhAgreePrivateKey;
    protected DHPublicKeyParameters dhAgreePublicKey;

    public TlsDHKeyExchange(int n, Vector vector, DHParameters dHParameters) {
        super(n, vector);
        switch (n) {
            case 7: 
            case 9: 
            case 11: {
                this.tlsSigner = null;
                break;
            }
            case 5: {
                this.tlsSigner = new TlsRSASigner();
                break;
            }
            case 3: {
                this.tlsSigner = new TlsDSSSigner();
                break;
            }
            default: {
                throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        this.dhParameters = dHParameters;
    }

    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        if (this.tlsSigner != null) {
            this.tlsSigner.init(tlsContext);
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.keyExchange != 11) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange == 11) {
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
        if (this.tlsSigner == null) {
            try {
                this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey((DHPublicKeyParameters)this.serverPublicKey);
                this.dhParameters = this.validateDHParameters(this.dhAgreePublicKey.getParameters());
            } catch (ClassCastException classCastException) {
                throw new TlsFatalAlert(46, (Throwable)classCastException);
            }
            TlsUtils.validateKeyUsage(certificate2, 8);
        } else {
            if (!this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
                throw new TlsFatalAlert(46);
            }
            TlsUtils.validateKeyUsage(certificate2, 128);
        }
        super.processServerCertificate(certificate);
    }

    public boolean requiresServerKeyExchange() {
        switch (this.keyExchange) {
            case 3: 
            case 5: 
            case 11: {
                return true;
            }
        }
        return false;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        if (!this.requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        if (!this.requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
        ServerDHParams serverDHParams = ServerDHParams.parse(inputStream);
        this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(serverDHParams.getPublicKey());
        this.dhParameters = this.validateDHParameters(this.dhAgreePublicKey.getParameters());
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert(40);
        }
        short[] sArray = certificateRequest.getCertificateTypes();
        block3: for (int i = 0; i < sArray.length; ++i) {
            switch (sArray[i]) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 64: {
                    continue block3;
                }
                default: {
                    throw new TlsFatalAlert(47);
                }
            }
        }
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert(80);
        }
        if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.agreementCredentials = (TlsAgreementCredentials)tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        if (this.agreementCredentials == null) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhParameters, outputStream);
        }
    }

    public void processClientCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange == 11) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        if (this.dhAgreePublicKey != null) {
            return;
        }
        BigInteger bigInteger = TlsDHUtils.readDHParameter(inputStream);
        this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(bigInteger, this.dhParameters));
    }

    public byte[] generatePremasterSecret() throws IOException {
        if (this.agreementCredentials != null) {
            return this.agreementCredentials.generateAgreement(this.dhAgreePublicKey);
        }
        if (this.dhAgreePrivateKey != null) {
            return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreePublicKey, this.dhAgreePrivateKey);
        }
        throw new TlsFatalAlert(80);
    }

    protected int getMinimumPrimeBits() {
        return 1024;
    }

    protected DHParameters validateDHParameters(DHParameters dHParameters) throws IOException {
        if (dHParameters.getP().bitLength() < this.getMinimumPrimeBits()) {
            throw new TlsFatalAlert(71);
        }
        return TlsDHUtils.validateDHParameters(dHParameters);
    }
}

