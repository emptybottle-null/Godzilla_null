/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.cert.CRL;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.PKIXParameters;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509Store;

class PKIXCRLUtil {
    PKIXCRLUtil() {
    }

    public Set findCRLs(X509CRLStoreSelector x509CRLStoreSelector, ExtendedPKIXParameters extendedPKIXParameters, Date date) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        try {
            hashSet.addAll(this.findCRLs(x509CRLStoreSelector, extendedPKIXParameters.getAdditionalStores()));
            hashSet.addAll(this.findCRLs(x509CRLStoreSelector, extendedPKIXParameters.getStores()));
            hashSet.addAll(this.findCRLs(x509CRLStoreSelector, extendedPKIXParameters.getCertStores()));
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Exception obtaining complete CRLs.", annotatedException);
        }
        HashSet<X509CRL> hashSet2 = new HashSet<X509CRL>();
        Date date2 = date;
        if (extendedPKIXParameters.getDate() != null) {
            date2 = extendedPKIXParameters.getDate();
        }
        for (X509CRL x509CRL : hashSet) {
            if (!x509CRL.getNextUpdate().after(date2)) continue;
            X509Certificate x509Certificate = x509CRLStoreSelector.getCertificateChecking();
            if (x509Certificate != null) {
                if (!x509CRL.getThisUpdate().before(x509Certificate.getNotAfter())) continue;
                hashSet2.add(x509CRL);
                continue;
            }
            hashSet2.add(x509CRL);
        }
        return hashSet2;
    }

    public Set findCRLs(X509CRLStoreSelector x509CRLStoreSelector, PKIXParameters pKIXParameters) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        try {
            hashSet.addAll(this.findCRLs(x509CRLStoreSelector, pKIXParameters.getCertStores()));
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Exception obtaining complete CRLs.", annotatedException);
        }
        return hashSet;
    }

    private final Collection findCRLs(X509CRLStoreSelector x509CRLStoreSelector, List list) throws AnnotatedException {
        HashSet<? extends CRL> hashSet = new HashSet<CRL>();
        Iterator iterator = list.iterator();
        AnnotatedException annotatedException = null;
        boolean bl = false;
        while (iterator.hasNext()) {
            Object object;
            Object e = iterator.next();
            if (e instanceof X509Store) {
                object = (X509Store)e;
                try {
                    hashSet.addAll(((X509Store)object).getMatches((Selector)x509CRLStoreSelector));
                    bl = true;
                } catch (StoreException storeException) {
                    annotatedException = new AnnotatedException("Exception searching in X.509 CRL store.", storeException);
                }
                continue;
            }
            object = (CertStore)e;
            try {
                hashSet.addAll(((CertStore)object).getCRLs(x509CRLStoreSelector));
                bl = true;
            } catch (CertStoreException certStoreException) {
                annotatedException = new AnnotatedException("Exception searching in X.509 CRL store.", certStoreException);
            }
        }
        if (!bl && annotatedException != null) {
            throw annotatedException;
        }
        return hashSet;
    }
}

