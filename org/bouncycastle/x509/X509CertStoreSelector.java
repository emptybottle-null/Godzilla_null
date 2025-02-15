/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import org.bouncycastle.util.Selector;

public class X509CertStoreSelector
extends X509CertSelector
implements Selector {
    public boolean match(Object object) {
        if (!(object instanceof X509Certificate)) {
            return false;
        }
        X509Certificate x509Certificate = (X509Certificate)object;
        return super.match(x509Certificate);
    }

    public boolean match(Certificate certificate) {
        return this.match((Object)certificate);
    }

    public Object clone() {
        X509CertStoreSelector x509CertStoreSelector = (X509CertStoreSelector)super.clone();
        return x509CertStoreSelector;
    }

    public static X509CertStoreSelector getInstance(X509CertSelector x509CertSelector) {
        if (x509CertSelector == null) {
            throw new IllegalArgumentException("cannot create from null selector");
        }
        X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
        x509CertStoreSelector.setAuthorityKeyIdentifier(x509CertSelector.getAuthorityKeyIdentifier());
        x509CertStoreSelector.setBasicConstraints(x509CertSelector.getBasicConstraints());
        x509CertStoreSelector.setCertificate(x509CertSelector.getCertificate());
        x509CertStoreSelector.setCertificateValid(x509CertSelector.getCertificateValid());
        x509CertStoreSelector.setMatchAllSubjectAltNames(x509CertSelector.getMatchAllSubjectAltNames());
        try {
            x509CertStoreSelector.setPathToNames(x509CertSelector.getPathToNames());
            x509CertStoreSelector.setExtendedKeyUsage(x509CertSelector.getExtendedKeyUsage());
            x509CertStoreSelector.setNameConstraints(x509CertSelector.getNameConstraints());
            x509CertStoreSelector.setPolicy(x509CertSelector.getPolicy());
            x509CertStoreSelector.setSubjectPublicKeyAlgID(x509CertSelector.getSubjectPublicKeyAlgID());
            x509CertStoreSelector.setSubjectAlternativeNames(x509CertSelector.getSubjectAlternativeNames());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("error in passed in selector: " + iOException);
        }
        x509CertStoreSelector.setIssuer(x509CertSelector.getIssuer());
        x509CertStoreSelector.setKeyUsage(x509CertSelector.getKeyUsage());
        x509CertStoreSelector.setPrivateKeyValid(x509CertSelector.getPrivateKeyValid());
        x509CertStoreSelector.setSerialNumber(x509CertSelector.getSerialNumber());
        x509CertStoreSelector.setSubject(x509CertSelector.getSubject());
        x509CertStoreSelector.setSubjectKeyIdentifier(x509CertSelector.getSubjectKeyIdentifier());
        x509CertStoreSelector.setSubjectPublicKey(x509CertSelector.getSubjectPublicKey());
        return x509CertStoreSelector;
    }
}

