/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.tls.AbstractTlsKeyExchange;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.TlsAgreementCredentials;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.crypto.tls.TlsECDSASigner;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsRSASigner;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;

public class TlsECDHKeyExchange
extends AbstractTlsKeyExchange {
    protected TlsSigner tlsSigner;
    protected int[] namedCurves;
    protected short[] clientECPointFormats;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsAgreementCredentials agreementCredentials;
    protected ECPrivateKeyParameters ecAgreePrivateKey;
    protected ECPublicKeyParameters ecAgreePublicKey;

    public TlsECDHKeyExchange(int n, Vector vector, int[] nArray, short[] sArray, short[] sArray2) {
        super(n, vector);
        switch (n) {
            case 19: {
                this.tlsSigner = new TlsRSASigner();
                break;
            }
            case 17: {
                this.tlsSigner = new TlsECDSASigner();
                break;
            }
            case 16: 
            case 18: 
            case 20: {
                this.tlsSigner = null;
                break;
            }
            default: {
                throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        this.namedCurves = nArray;
        this.clientECPointFormats = sArray;
        this.serverECPointFormats = sArray2;
    }

    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        if (this.tlsSigner != null) {
            this.tlsSigner.init(tlsContext);
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.keyExchange != 20) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange == 20) {
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
                this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey((ECPublicKeyParameters)this.serverPublicKey);
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
            case 17: 
            case 19: 
            case 20: {
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
        this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.namedCurves, this.clientECPointFormats, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        if (!this.requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
        ECDomainParameters eCDomainParameters = TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, inputStream);
        byte[] byArray = TlsUtils.readOpaque8(inputStream);
        this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, eCDomainParameters, byArray));
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        if (this.keyExchange == 20) {
            throw new TlsFatalAlert(40);
        }
        short[] sArray = certificateRequest.getCertificateTypes();
        block3: for (int i = 0; i < sArray.length; ++i) {
            switch (sArray[i]) {
                case 1: 
                case 2: 
                case 64: 
                case 65: 
                case 66: {
                    continue block3;
                }
                default: {
                    throw new TlsFatalAlert(47);
                }
            }
        }
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (this.keyExchange == 20) {
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
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), outputStream);
        }
    }

    public void processClientCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange == 20) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        if (this.ecAgreePublicKey != null) {
            return;
        }
        byte[] byArray = TlsUtils.readOpaque8(inputStream);
        ECDomainParameters eCDomainParameters = this.ecAgreePrivateKey.getParameters();
        this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, eCDomainParameters, byArray));
    }

    public byte[] generatePremasterSecret() throws IOException {
        if (this.agreementCredentials != null) {
            return this.agreementCredentials.generateAgreement(this.ecAgreePublicKey);
        }
        if (this.ecAgreePrivateKey != null) {
            return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, this.ecAgreePrivateKey);
        }
        throw new TlsFatalAlert(80);
    }
}

