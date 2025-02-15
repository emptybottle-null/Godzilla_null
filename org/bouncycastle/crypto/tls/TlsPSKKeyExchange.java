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
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.tls.AbstractTlsKeyExchange;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.ServerDHParams;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsDHUtils;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.crypto.tls.TlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsPSKIdentity;
import org.bouncycastle.crypto.tls.TlsPSKIdentityManager;
import org.bouncycastle.crypto.tls.TlsRSAUtils;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;

public class TlsPSKKeyExchange
extends AbstractTlsKeyExchange {
    protected TlsPSKIdentity pskIdentity;
    protected TlsPSKIdentityManager pskIdentityManager;
    protected DHParameters dhParameters;
    protected int[] namedCurves;
    protected short[] clientECPointFormats;
    protected short[] serverECPointFormats;
    protected byte[] psk_identity_hint = null;
    protected byte[] psk = null;
    protected DHPrivateKeyParameters dhAgreePrivateKey = null;
    protected DHPublicKeyParameters dhAgreePublicKey = null;
    protected ECPrivateKeyParameters ecAgreePrivateKey = null;
    protected ECPublicKeyParameters ecAgreePublicKey = null;
    protected AsymmetricKeyParameter serverPublicKey = null;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected byte[] premasterSecret;

    public TlsPSKKeyExchange(int n, Vector vector, TlsPSKIdentity tlsPSKIdentity, TlsPSKIdentityManager tlsPSKIdentityManager, DHParameters dHParameters, int[] nArray, short[] sArray, short[] sArray2) {
        super(n, vector);
        switch (n) {
            case 13: 
            case 14: 
            case 15: 
            case 24: {
                break;
            }
            default: {
                throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        this.pskIdentity = tlsPSKIdentity;
        this.pskIdentityManager = tlsPSKIdentityManager;
        this.dhParameters = dHParameters;
        this.namedCurves = nArray;
        this.clientECPointFormats = sArray;
        this.serverECPointFormats = sArray2;
    }

    public void skipServerCredentials() throws IOException {
        if (this.keyExchange == 15) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsEncryptionCredentials)) {
            throw new TlsFatalAlert(80);
        }
        this.processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsEncryptionCredentials)tlsCredentials;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        this.psk_identity_hint = this.pskIdentityManager.getHint();
        if (this.psk_identity_hint == null && !this.requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.psk_identity_hint == null) {
            TlsUtils.writeOpaque16(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        } else {
            TlsUtils.writeOpaque16(this.psk_identity_hint, byteArrayOutputStream);
        }
        if (this.keyExchange == 14) {
            if (this.dhParameters == null) {
                throw new TlsFatalAlert(80);
            }
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, byteArrayOutputStream);
        } else if (this.keyExchange == 24) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.namedCurves, this.clientECPointFormats, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange != 15) {
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
        if (this.serverPublicKey.isPrivate()) {
            throw new TlsFatalAlert(80);
        }
        this.rsaServerPublicKey = this.validateRSAPublicKey((RSAKeyParameters)this.serverPublicKey);
        TlsUtils.validateKeyUsage(certificate2, 32);
        super.processServerCertificate(certificate);
    }

    public boolean requiresServerKeyExchange() {
        switch (this.keyExchange) {
            case 14: 
            case 24: {
                return true;
            }
        }
        return false;
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        this.psk_identity_hint = TlsUtils.readOpaque16(inputStream);
        if (this.keyExchange == 14) {
            ServerDHParams serverDHParams = ServerDHParams.parse(inputStream);
            this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(serverDHParams.getPublicKey());
            this.dhParameters = this.dhAgreePublicKey.getParameters();
        } else if (this.keyExchange == 24) {
            ECDomainParameters eCDomainParameters = TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, inputStream);
            byte[] byArray = TlsUtils.readOpaque8(inputStream);
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, eCDomainParameters, byArray));
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        if (this.psk_identity_hint == null) {
            this.pskIdentity.skipIdentityHint();
        } else {
            this.pskIdentity.notifyIdentityHint(this.psk_identity_hint);
        }
        byte[] byArray = this.pskIdentity.getPSKIdentity();
        if (byArray == null) {
            throw new TlsFatalAlert(80);
        }
        this.psk = this.pskIdentity.getPSK();
        if (this.psk == null) {
            throw new TlsFatalAlert(80);
        }
        TlsUtils.writeOpaque16(byArray, outputStream);
        this.context.getSecurityParameters().pskIdentity = Arrays.clone(byArray);
        if (this.keyExchange == 14) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhParameters, outputStream);
        } else if (this.keyExchange == 24) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), outputStream);
        } else if (this.keyExchange == 15) {
            this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, outputStream);
        }
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        byte[] byArray = TlsUtils.readOpaque16(inputStream);
        this.psk = this.pskIdentityManager.getPSK(byArray);
        if (this.psk == null) {
            throw new TlsFatalAlert(115);
        }
        this.context.getSecurityParameters().pskIdentity = byArray;
        if (this.keyExchange == 14) {
            BigInteger bigInteger = TlsDHUtils.readDHParameter(inputStream);
            this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(bigInteger, this.dhParameters));
        } else if (this.keyExchange == 24) {
            byte[] byArray2 = TlsUtils.readOpaque8(inputStream);
            ECDomainParameters eCDomainParameters = this.ecAgreePrivateKey.getParameters();
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, eCDomainParameters, byArray2));
        } else if (this.keyExchange == 15) {
            byte[] byArray3 = TlsUtils.isSSL(this.context) ? Streams.readAll(inputStream) : TlsUtils.readOpaque16(inputStream);
            this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(byArray3);
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        byte[] byArray = this.generateOtherSecret(this.psk.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 + byArray.length + this.psk.length);
        TlsUtils.writeOpaque16(byArray, byteArrayOutputStream);
        TlsUtils.writeOpaque16(this.psk, byteArrayOutputStream);
        Arrays.fill(this.psk, (byte)0);
        this.psk = null;
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateOtherSecret(int n) throws IOException {
        if (this.keyExchange == 14) {
            if (this.dhAgreePrivateKey != null) {
                return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreePublicKey, this.dhAgreePrivateKey);
            }
            throw new TlsFatalAlert(80);
        }
        if (this.keyExchange == 24) {
            if (this.ecAgreePrivateKey != null) {
                return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, this.ecAgreePrivateKey);
            }
            throw new TlsFatalAlert(80);
        }
        if (this.keyExchange == 15) {
            return this.premasterSecret;
        }
        return new byte[n];
    }

    protected RSAKeyParameters validateRSAPublicKey(RSAKeyParameters rSAKeyParameters) throws IOException {
        if (!rSAKeyParameters.getExponent().isProbablePrime(2)) {
            throw new TlsFatalAlert(47);
        }
        return rSAKeyParameters;
    }
}

