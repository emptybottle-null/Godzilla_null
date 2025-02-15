/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.jce.MultiCertStoreParameters;

public class MultiCertStoreSpi
extends CertStoreSpi {
    private MultiCertStoreParameters params;

    public MultiCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (!(certStoreParameters instanceof MultiCertStoreParameters)) {
            throw new InvalidAlgorithmParameterException("org.bouncycastle.jce.provider.MultiCertStoreSpi: parameter must be a MultiCertStoreParameters object\n" + certStoreParameters.toString());
        }
        this.params = (MultiCertStoreParameters)certStoreParameters;
    }

    public Collection engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        List list;
        boolean bl = this.params.getSearchAllStores();
        Iterator iterator = this.params.getCertStores().iterator();
        List list2 = list = bl ? new ArrayList() : Collections.EMPTY_LIST;
        while (iterator.hasNext()) {
            CertStore certStore = (CertStore)iterator.next();
            Collection<? extends Certificate> collection = certStore.getCertificates(certSelector);
            if (bl) {
                list.addAll(collection);
                continue;
            }
            if (collection.isEmpty()) continue;
            return collection;
        }
        return list;
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        List list;
        boolean bl = this.params.getSearchAllStores();
        Iterator iterator = this.params.getCertStores().iterator();
        List list2 = list = bl ? new ArrayList() : Collections.EMPTY_LIST;
        while (iterator.hasNext()) {
            CertStore certStore = (CertStore)iterator.next();
            Collection<? extends CRL> collection = certStore.getCRLs(cRLSelector);
            if (bl) {
                list.addAll(collection);
                continue;
            }
            if (collection.isEmpty()) continue;
            return collection;
        }
        return list;
    }
}

