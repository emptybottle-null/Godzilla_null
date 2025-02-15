/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.util.LDAPStoreHelper;

public class X509StoreLDAPCRLs
extends X509StoreSpi {
    private LDAPStoreHelper helper;

    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (!(x509StoreParameters instanceof X509LDAPCertStoreParameters)) {
            throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
        }
        this.helper = new LDAPStoreHelper((X509LDAPCertStoreParameters)x509StoreParameters);
    }

    public Collection engineGetMatches(Selector selector) throws StoreException {
        if (!(selector instanceof X509CRLStoreSelector)) {
            return Collections.EMPTY_SET;
        }
        X509CRLStoreSelector x509CRLStoreSelector = (X509CRLStoreSelector)selector;
        HashSet hashSet = new HashSet();
        if (x509CRLStoreSelector.isDeltaCRLIndicatorEnabled()) {
            hashSet.addAll(this.helper.getDeltaCertificateRevocationLists(x509CRLStoreSelector));
        } else {
            hashSet.addAll(this.helper.getDeltaCertificateRevocationLists(x509CRLStoreSelector));
            hashSet.addAll(this.helper.getAttributeAuthorityRevocationLists(x509CRLStoreSelector));
            hashSet.addAll(this.helper.getAttributeCertificateRevocationLists(x509CRLStoreSelector));
            hashSet.addAll(this.helper.getAuthorityRevocationLists(x509CRLStoreSelector));
            hashSet.addAll(this.helper.getCertificateRevocationLists(x509CRLStoreSelector));
        }
        return hashSet;
    }
}

