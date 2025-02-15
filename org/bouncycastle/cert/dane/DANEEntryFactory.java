/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.cert.dane.DANEEntrySelector;
import org.bouncycastle.cert.dane.DANEEntrySelectorFactory;
import org.bouncycastle.cert.dane.DANEException;
import org.bouncycastle.operator.DigestCalculator;

public class DANEEntryFactory {
    private final DANEEntrySelectorFactory selectorFactory;

    public DANEEntryFactory(DigestCalculator digestCalculator) {
        this.selectorFactory = new DANEEntrySelectorFactory(digestCalculator);
    }

    public DANEEntry createEntry(String string, X509CertificateHolder x509CertificateHolder) throws DANEException {
        return this.createEntry(string, 3, x509CertificateHolder);
    }

    public DANEEntry createEntry(String string, int n, X509CertificateHolder x509CertificateHolder) throws DANEException {
        if (n < 0 || n > 3) {
            throw new DANEException("unknown certificate usage: " + n);
        }
        DANEEntrySelector dANEEntrySelector = this.selectorFactory.createSelector(string);
        byte[] byArray = new byte[]{(byte)n, 0, 0};
        return new DANEEntry(dANEEntrySelector.getDomainName(), byArray, x509CertificateHolder);
    }
}

