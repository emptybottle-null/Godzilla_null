/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import org.bouncycastle.cert.dane.DANEEntryFetcherFactory;
import org.bouncycastle.cert.dane.DANEEntryStore;
import org.bouncycastle.cert.dane.DANEException;

public class DANEEntryStoreBuilder {
    private final DANEEntryFetcherFactory daneEntryFetcher;

    public DANEEntryStoreBuilder(DANEEntryFetcherFactory dANEEntryFetcherFactory) {
        this.daneEntryFetcher = dANEEntryFetcherFactory;
    }

    public DANEEntryStore build(String string) throws DANEException {
        return new DANEEntryStore(this.daneEntryFetcher.build(string).getEntries());
    }
}

