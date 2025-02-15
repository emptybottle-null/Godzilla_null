/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.util.LDAPStoreHelper;

public class X509StoreLDAPCerts
extends X509StoreSpi {
    private LDAPStoreHelper helper;

    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (!(x509StoreParameters instanceof X509LDAPCertStoreParameters)) {
            throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
        }
        this.helper = new LDAPStoreHelper((X509LDAPCertStoreParameters)x509StoreParameters);
    }

    public Collection engineGetMatches(Selector selector) throws StoreException {
        if (!(selector instanceof X509CertStoreSelector)) {
            return Collections.EMPTY_SET;
        }
        X509CertStoreSelector x509CertStoreSelector = (X509CertStoreSelector)selector;
        HashSet hashSet = new HashSet();
        if (x509CertStoreSelector.getBasicConstraints() > 0) {
            hashSet.addAll(this.helper.getCACertificates(x509CertStoreSelector));
            hashSet.addAll(this.getCertificatesFromCrossCertificatePairs(x509CertStoreSelector));
        } else if (x509CertStoreSelector.getBasicConstraints() == -2) {
            hashSet.addAll(this.helper.getUserCertificates(x509CertStoreSelector));
        } else {
            hashSet.addAll(this.helper.getUserCertificates(x509CertStoreSelector));
            hashSet.addAll(this.helper.getCACertificates(x509CertStoreSelector));
            hashSet.addAll(this.getCertificatesFromCrossCertificatePairs(x509CertStoreSelector));
        }
        return hashSet;
    }

    private Collection getCertificatesFromCrossCertificatePairs(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.setForwardSelector(x509CertStoreSelector);
        x509CertPairStoreSelector.setReverseSelector(new X509CertStoreSelector());
        HashSet hashSet2 = new HashSet(this.helper.getCrossCertificatePairs(x509CertPairStoreSelector));
        HashSet<X509Certificate> hashSet3 = new HashSet<X509Certificate>();
        HashSet<X509Certificate> hashSet4 = new HashSet<X509Certificate>();
        for (X509CertificatePair x509CertificatePair : hashSet2) {
            if (x509CertificatePair.getForward() != null) {
                hashSet3.add(x509CertificatePair.getForward());
            }
            if (x509CertificatePair.getReverse() == null) continue;
            hashSet4.add(x509CertificatePair.getReverse());
        }
        hashSet.addAll(hashSet3);
        hashSet.addAll(hashSet4);
        return hashSet;
    }
}

