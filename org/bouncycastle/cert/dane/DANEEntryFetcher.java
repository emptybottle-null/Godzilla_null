/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import java.util.List;
import org.bouncycastle.cert.dane.DANEException;

public interface DANEEntryFetcher {
    public List getEntries() throws DANEException;
}

