/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXCRLStoreSelector;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

class PKIXCRLUtil {
    PKIXCRLUtil() {
    }

    public Set findCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, Date date, List list, List list2) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        try {
            hashSet.addAll(this.findCRLs(pKIXCRLStoreSelector, list2));
            hashSet.addAll(this.findCRLs(pKIXCRLStoreSelector, list));
        } catch (AnnotatedException annotatedException) {
            throw new AnnotatedException("Exception obtaining complete CRLs.", annotatedException);
        }
        HashSet<X509CRL> hashSet2 = new HashSet<X509CRL>();
        for (X509CRL x509CRL : hashSet) {
            if (!x509CRL.getNextUpdate().after(date)) continue;
            X509Certificate x509Certificate = pKIXCRLStoreSelector.getCertificateChecking();
            if (x509Certificate != null) {
                if (!x509CRL.getThisUpdate().before(x509Certificate.getNotAfter())) continue;
                hashSet2.add(x509CRL);
                continue;
            }
            hashSet2.add(x509CRL);
        }
        return hashSet2;
    }

    private final Collection findCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, List list) throws AnnotatedException {
        HashSet<Object> hashSet = new HashSet<Object>();
        Iterator iterator = list.iterator();
        AnnotatedException annotatedException = null;
        boolean bl = false;
        while (iterator.hasNext()) {
            Object object;
            Object e = iterator.next();
            if (e instanceof Store) {
                object = (Store)e;
                try {
                    hashSet.addAll(object.getMatches(pKIXCRLStoreSelector));
                    bl = true;
                } catch (StoreException storeException) {
                    annotatedException = new AnnotatedException("Exception searching in X.509 CRL store.", storeException);
                }
                continue;
            }
            object = (CertStore)e;
            try {
                hashSet.addAll(PKIXCRLStoreSelector.getCRLs(pKIXCRLStoreSelector, (CertStore)object));
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

