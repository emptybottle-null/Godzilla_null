/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.cert.dane.DANEEntryFetcherFactory;
import org.bouncycastle.cert.dane.DANEEntrySelector;
import org.bouncycastle.cert.dane.DANEEntrySelectorFactory;
import org.bouncycastle.cert.dane.DANEException;
import org.bouncycastle.operator.DigestCalculator;

public class DANECertificateFetcher {
    private final DANEEntryFetcherFactory fetcherFactory;
    private final DANEEntrySelectorFactory selectorFactory;

    public DANECertificateFetcher(DANEEntryFetcherFactory dANEEntryFetcherFactory, DigestCalculator digestCalculator) {
        this.fetcherFactory = dANEEntryFetcherFactory;
        this.selectorFactory = new DANEEntrySelectorFactory(digestCalculator);
    }

    public List fetch(String string) throws DANEException {
        DANEEntrySelector dANEEntrySelector = this.selectorFactory.createSelector(string);
        List list = this.fetcherFactory.build(dANEEntrySelector.getDomainName()).getEntries();
        ArrayList<X509CertificateHolder> arrayList = new ArrayList<X509CertificateHolder>(list.size());
        for (DANEEntry dANEEntry : list) {
            if (!dANEEntrySelector.match(dANEEntry)) continue;
            arrayList.add(dANEEntry.getCertificate());
        }
        return Collections.unmodifiableList(arrayList);
    }
}

