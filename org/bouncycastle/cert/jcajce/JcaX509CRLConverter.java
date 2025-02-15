/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.jcajce.CertHelper;
import org.bouncycastle.cert.jcajce.DefaultCertHelper;
import org.bouncycastle.cert.jcajce.NamedCertHelper;
import org.bouncycastle.cert.jcajce.ProviderCertHelper;

public class JcaX509CRLConverter {
    private CertHelper helper = new DefaultCertHelper();

    public JcaX509CRLConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }

    public JcaX509CRLConverter setProvider(String string) {
        this.helper = new NamedCertHelper(string);
        return this;
    }

    public X509CRL getCRL(X509CRLHolder x509CRLHolder) throws CRLException {
        try {
            CertificateFactory certificateFactory = this.helper.getCertificateFactory("X.509");
            return (X509CRL)certificateFactory.generateCRL(new ByteArrayInputStream(x509CRLHolder.getEncoded()));
        } catch (IOException iOException) {
            throw new ExCRLException("exception parsing certificate: " + iOException.getMessage(), iOException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new ExCRLException("cannot find required provider:" + noSuchProviderException.getMessage(), noSuchProviderException);
        } catch (CertificateException certificateException) {
            throw new ExCRLException("cannot create factory: " + certificateException.getMessage(), certificateException);
        }
    }

    private class ExCRLException
    extends CRLException {
        private Throwable cause;

        public ExCRLException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}

