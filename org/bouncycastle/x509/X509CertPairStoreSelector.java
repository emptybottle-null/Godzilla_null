/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;

public class X509CertPairStoreSelector
implements Selector {
    private X509CertStoreSelector forwardSelector;
    private X509CertStoreSelector reverseSelector;
    private X509CertificatePair certPair;

    public X509CertificatePair getCertPair() {
        return this.certPair;
    }

    public void setCertPair(X509CertificatePair x509CertificatePair) {
        this.certPair = x509CertificatePair;
    }

    public void setForwardSelector(X509CertStoreSelector x509CertStoreSelector) {
        this.forwardSelector = x509CertStoreSelector;
    }

    public void setReverseSelector(X509CertStoreSelector x509CertStoreSelector) {
        this.reverseSelector = x509CertStoreSelector;
    }

    public Object clone() {
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.certPair = this.certPair;
        if (this.forwardSelector != null) {
            x509CertPairStoreSelector.setForwardSelector((X509CertStoreSelector)this.forwardSelector.clone());
        }
        if (this.reverseSelector != null) {
            x509CertPairStoreSelector.setReverseSelector((X509CertStoreSelector)this.reverseSelector.clone());
        }
        return x509CertPairStoreSelector;
    }

    public boolean match(Object object) {
        try {
            if (!(object instanceof X509CertificatePair)) {
                return false;
            }
            X509CertificatePair x509CertificatePair = (X509CertificatePair)object;
            if (this.forwardSelector != null && !this.forwardSelector.match((Object)x509CertificatePair.getForward())) {
                return false;
            }
            if (this.reverseSelector != null && !this.reverseSelector.match((Object)x509CertificatePair.getReverse())) {
                return false;
            }
            if (this.certPair != null) {
                return this.certPair.equals(object);
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public X509CertStoreSelector getForwardSelector() {
        return this.forwardSelector;
    }

    public X509CertStoreSelector getReverseSelector() {
        return this.reverseSelector;
    }
}

