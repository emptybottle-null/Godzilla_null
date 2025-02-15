/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.CollectionStore;

public class JcaCertStore
extends CollectionStore {
    public JcaCertStore(Collection collection) throws CertificateEncodingException {
        super(JcaCertStore.convertCerts(collection));
    }

    private static Collection convertCerts(Collection collection) throws CertificateEncodingException {
        ArrayList<X509CertificateHolder> arrayList = new ArrayList<X509CertificateHolder>(collection.size());
        for (Object e : collection) {
            if (e instanceof X509Certificate) {
                X509Certificate x509Certificate = (X509Certificate)e;
                try {
                    arrayList.add(new X509CertificateHolder(x509Certificate.getEncoded()));
                    continue;
                } catch (IOException iOException) {
                    throw new CertificateEncodingException("unable to read encoding: " + iOException.getMessage());
                }
            }
            arrayList.add((X509CertificateHolder)e);
        }
        return arrayList;
    }
}

