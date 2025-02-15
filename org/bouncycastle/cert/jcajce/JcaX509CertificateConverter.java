/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.CertHelper;
import org.bouncycastle.cert.jcajce.DefaultCertHelper;
import org.bouncycastle.cert.jcajce.NamedCertHelper;
import org.bouncycastle.cert.jcajce.ProviderCertHelper;

public class JcaX509CertificateConverter {
    private CertHelper helper = new DefaultCertHelper();

    public JcaX509CertificateConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }

    public JcaX509CertificateConverter setProvider(String string) {
        this.helper = new NamedCertHelper(string);
        return this;
    }

    public X509Certificate getCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            CertificateFactory certificateFactory = this.helper.getCertificateFactory("X.509");
            return (X509Certificate)certificateFactory.generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException iOException) {
            throw new ExCertificateParsingException("exception parsing certificate: " + iOException.getMessage(), iOException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new ExCertificateException("cannot find required provider:" + noSuchProviderException.getMessage(), noSuchProviderException);
        }
    }

    private class ExCertificateException
    extends CertificateException {
        private Throwable cause;

        public ExCertificateException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }

    private class ExCertificateParsingException
    extends CertificateParsingException {
        private Throwable cause;

        public ExCertificateParsingException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}

