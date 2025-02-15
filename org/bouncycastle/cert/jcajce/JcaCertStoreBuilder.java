/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Extension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.util.Store;

public class JcaCertStoreBuilder {
    private List certs = new ArrayList();
    private List crls = new ArrayList();
    private Object provider;
    private JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();
    private JcaX509CRLConverter crlConverter = new JcaX509CRLConverter();
    private String type = "Collection";

    public JcaCertStoreBuilder addCertificates(Store store) {
        this.certs.addAll(store.getMatches(null));
        return this;
    }

    public JcaCertStoreBuilder addCertificate(X509CertificateHolder x509CertificateHolder) {
        this.certs.add(x509CertificateHolder);
        return this;
    }

    public JcaCertStoreBuilder addCRLs(Store store) {
        this.crls.addAll(store.getMatches(null));
        return this;
    }

    public JcaCertStoreBuilder addCRL(X509CRLHolder x509CRLHolder) {
        this.crls.add(x509CRLHolder);
        return this;
    }

    public JcaCertStoreBuilder setProvider(String string) {
        this.certificateConverter.setProvider(string);
        this.crlConverter.setProvider(string);
        this.provider = string;
        return this;
    }

    public JcaCertStoreBuilder setProvider(Provider provider) {
        this.certificateConverter.setProvider(provider);
        this.crlConverter.setProvider(provider);
        this.provider = provider;
        return this;
    }

    public JcaCertStoreBuilder setType(String string) {
        this.type = string;
        return this;
    }

    public CertStore build() throws GeneralSecurityException {
        CollectionCertStoreParameters collectionCertStoreParameters = this.convertHolders(this.certificateConverter, this.crlConverter);
        if (this.provider instanceof String) {
            return CertStore.getInstance(this.type, (CertStoreParameters)collectionCertStoreParameters, (String)this.provider);
        }
        if (this.provider instanceof Provider) {
            return CertStore.getInstance(this.type, (CertStoreParameters)collectionCertStoreParameters, (Provider)this.provider);
        }
        return CertStore.getInstance(this.type, collectionCertStoreParameters);
    }

    private CollectionCertStoreParameters convertHolders(JcaX509CertificateConverter jcaX509CertificateConverter, JcaX509CRLConverter jcaX509CRLConverter) throws CertificateException, CRLException {
        ArrayList<X509Extension> arrayList = new ArrayList<X509Extension>(this.certs.size() + this.crls.size());
        Iterator iterator = this.certs.iterator();
        while (iterator.hasNext()) {
            arrayList.add(jcaX509CertificateConverter.getCertificate((X509CertificateHolder)iterator.next()));
        }
        iterator = this.crls.iterator();
        while (iterator.hasNext()) {
            arrayList.add(jcaX509CRLConverter.getCRL((X509CRLHolder)iterator.next()));
        }
        return new CollectionCertStoreParameters(arrayList);
    }
}

