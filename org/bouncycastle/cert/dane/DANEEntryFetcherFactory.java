/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import org.bouncycastle.cert.dane.DANEEntryFetcher;

public interface DANEEntryFetcherFactory {
    public DANEEntryFetcher build(String var1);
}

