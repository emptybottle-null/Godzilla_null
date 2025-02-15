/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTException;

public interface ESTClientProvider {
    public ESTClient makeClient() throws ESTException;

    public boolean isTrusted();
}

