/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.util.CollectionStore;

public class JcaCRLStore
extends CollectionStore {
    public JcaCRLStore(Collection collection) throws CRLException {
        super(JcaCRLStore.convertCRLs(collection));
    }

    private static Collection convertCRLs(Collection collection) throws CRLException {
        ArrayList<X509CRLHolder> arrayList = new ArrayList<X509CRLHolder>(collection.size());
        for (Object e : collection) {
            if (e instanceof X509CRL) {
                try {
                    arrayList.add(new X509CRLHolder(((X509CRL)e).getEncoded()));
                    continue;
                } catch (IOException iOException) {
                    throw new CRLException("cannot read encoding: " + iOException.getMessage());
                }
            }
            arrayList.add((X509CRLHolder)e);
        }
        return arrayList;
    }
}

