/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.tls.AbstractTlsKeyExchange;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsRSAUtils;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.io.Streams;

public class TlsRSAKeyExchange
extends AbstractTlsKeyExchange {
    protected AsymmetricKeyParameter serverPublicKey = null;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected byte[] premasterSecret;

    public TlsRSAKeyExchange(Vector vector) {
        super(1, vector);
    }

    public void skipServerCredentials() throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsEncryptionCredentials)) {
            throw new TlsFatalAlert(80);
        }
        this.processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsEncryptionCredentials)tlsCredentials;
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
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

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        short[] sArray = certificateRequest.getCertificateTypes();
        block3: for (int i = 0; i < sArray.length; ++i) {
            switch (sArray[i]) {
                case 1: 
                case 2: 
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
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, outputStream);
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        byte[] byArray = TlsUtils.isSSL(this.context) ? Streams.readAll(inputStream) : TlsUtils.readOpaque16(inputStream);
        this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(byArray);
    }

    public byte[] generatePremasterSecret() throws IOException {
        if (this.premasterSecret == null) {
            throw new TlsFatalAlert(80);
        }
        byte[] byArray = this.premasterSecret;
        this.premasterSecret = null;
        return byArray;
    }

    protected RSAKeyParameters validateRSAPublicKey(RSAKeyParameters rSAKeyParameters) throws IOException {
        if (!rSAKeyParameters.getExponent().isProbablePrime(2)) {
            throw new TlsFatalAlert(47);
        }
        return rSAKeyParameters;
    }
}

