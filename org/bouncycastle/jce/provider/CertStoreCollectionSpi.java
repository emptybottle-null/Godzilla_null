/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CertStoreCollectionSpi
extends CertStoreSpi {
    private CollectionCertStoreParameters params;

    public CertStoreCollectionSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (!(certStoreParameters instanceof CollectionCertStoreParameters)) {
            throw new InvalidAlgorithmParameterException("org.bouncycastle.jce.provider.CertStoreCollectionSpi: parameter must be a CollectionCertStoreParameters object\n" + certStoreParameters.toString());
        }
        this.params = (CollectionCertStoreParameters)certStoreParameters;
    }

    public Collection engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        ArrayList arrayList = new ArrayList();
        Iterator<?> iterator = this.params.getCollection().iterator();
        if (certSelector == null) {
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (!(obj instanceof Certificate)) continue;
                arrayList.add(obj);
            }
        } else {
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (!(obj instanceof Certificate) || !certSelector.match((Certificate)obj)) continue;
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        ArrayList arrayList = new ArrayList();
        Iterator<?> iterator = this.params.getCollection().iterator();
        if (cRLSelector == null) {
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (!(obj instanceof CRL)) continue;
                arrayList.add(obj);
            }
        } else {
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (!(obj instanceof CRL) || !cRLSelector.match((CRL)obj)) continue;
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

