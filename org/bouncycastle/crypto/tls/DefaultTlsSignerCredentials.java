/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.tls.AbstractTlsSignerCredentials;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsDSSSigner;
import org.bouncycastle.crypto.tls.TlsECDSASigner;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsRSASigner;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsUtils;

public class DefaultTlsSignerCredentials
extends AbstractTlsSignerCredentials {
    protected TlsContext context;
    protected Certificate certificate;
    protected AsymmetricKeyParameter privateKey;
    protected SignatureAndHashAlgorithm signatureAndHashAlgorithm;
    protected TlsSigner signer;

    public DefaultTlsSignerCredentials(TlsContext tlsContext, Certificate certificate, AsymmetricKeyParameter asymmetricKeyParameter) {
        this(tlsContext, certificate, asymmetricKeyParameter, null);
    }

    public DefaultTlsSignerCredentials(TlsContext tlsContext, Certificate certificate, AsymmetricKeyParameter asymmetricKeyParameter, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (certificate == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        }
        if (certificate.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        }
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        }
        if (!asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        }
        if (TlsUtils.isTLSv12(tlsContext) && signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAndHashAlgorithm' cannot be null for (D)TLS 1.2+");
        }
        if (asymmetricKeyParameter instanceof RSAKeyParameters) {
            this.signer = new TlsRSASigner();
        } else if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
            this.signer = new TlsDSSSigner();
        } else if (asymmetricKeyParameter instanceof ECPrivateKeyParameters) {
            this.signer = new TlsECDSASigner();
        } else {
            throw new IllegalArgumentException("'privateKey' type not supported: " + asymmetricKeyParameter.getClass().getName());
        }
        this.signer.init(tlsContext);
        this.context = tlsContext;
        this.certificate = certificate;
        this.privateKey = asymmetricKeyParameter;
        this.signatureAndHashAlgorithm = signatureAndHashAlgorithm;
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public byte[] generateCertificateSignature(byte[] byArray) throws IOException {
        try {
            if (TlsUtils.isTLSv12(this.context)) {
                return this.signer.generateRawSignature(this.signatureAndHashAlgorithm, this.privateKey, byArray);
            }
            return this.signer.generateRawSignature(this.privateKey, byArray);
        } catch (CryptoException cryptoException) {
            throw new TlsFatalAlert(80, (Throwable)cryptoException);
        }
    }

    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        return this.signatureAndHashAlgorithm;
    }
}

