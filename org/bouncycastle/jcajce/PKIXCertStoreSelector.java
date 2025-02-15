/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.io.IOException;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509CertSelector;
import java.util.Collection;
import org.bouncycastle.util.Selector;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PKIXCertStoreSelector<T extends Certificate>
implements Selector<T> {
    private final CertSelector baseSelector;

    private PKIXCertStoreSelector(CertSelector certSelector) {
        this.baseSelector = certSelector;
    }

    @Override
    public boolean match(Certificate certificate) {
        return this.baseSelector.match(certificate);
    }

    @Override
    public Object clone() {
        return new PKIXCertStoreSelector<T>(this.baseSelector);
    }

    public static Collection<? extends Certificate> getCertificates(PKIXCertStoreSelector pKIXCertStoreSelector, CertStore certStore) throws CertStoreException {
        return certStore.getCertificates(new SelectorClone(pKIXCertStoreSelector));
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final CertSelector baseSelector;

        public Builder(CertSelector certSelector) {
            this.baseSelector = (CertSelector)certSelector.clone();
        }

        public PKIXCertStoreSelector<? extends Certificate> build() {
            return new PKIXCertStoreSelector(this.baseSelector);
        }
    }

    private static class SelectorClone
    extends X509CertSelector {
        private final PKIXCertStoreSelector selector;

        SelectorClone(PKIXCertStoreSelector pKIXCertStoreSelector) {
            this.selector = pKIXCertStoreSelector;
            if (pKIXCertStoreSelector.baseSelector instanceof X509CertSelector) {
                X509CertSelector x509CertSelector = (X509CertSelector)pKIXCertStoreSelector.baseSelector;
                this.setAuthorityKeyIdentifier(x509CertSelector.getAuthorityKeyIdentifier());
                this.setBasicConstraints(x509CertSelector.getBasicConstraints());
                this.setCertificate(x509CertSelector.getCertificate());
                this.setCertificateValid(x509CertSelector.getCertificateValid());
                this.setKeyUsage(x509CertSelector.getKeyUsage());
                this.setMatchAllSubjectAltNames(x509CertSelector.getMatchAllSubjectAltNames());
                this.setPrivateKeyValid(x509CertSelector.getPrivateKeyValid());
                this.setSerialNumber(x509CertSelector.getSerialNumber());
                this.setSubjectKeyIdentifier(x509CertSelector.getSubjectKeyIdentifier());
                this.setSubjectPublicKey(x509CertSelector.getSubjectPublicKey());
                try {
                    this.setExtendedKeyUsage(x509CertSelector.getExtendedKeyUsage());
                    this.setIssuer(x509CertSelector.getIssuerAsBytes());
                    this.setNameConstraints(x509CertSelector.getNameConstraints());
                    this.setPathToNames(x509CertSelector.getPathToNames());
                    this.setPolicy(x509CertSelector.getPolicy());
                    this.setSubject(x509CertSelector.getSubjectAsBytes());
                    this.setSubjectAlternativeNames(x509CertSelector.getSubjectAlternativeNames());
                    this.setSubjectPublicKeyAlgID(x509CertSelector.getSubjectPublicKeyAlgID());
                } catch (IOException iOException) {
                    throw new IllegalStateException("base selector invalid: " + iOException.getMessage(), iOException);
                }
            }
        }

        public boolean match(Certificate certificate) {
            return this.selector == null ? certificate != null : this.selector.match(certificate);
        }
    }
}

