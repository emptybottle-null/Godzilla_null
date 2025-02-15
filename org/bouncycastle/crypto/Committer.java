/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.Commitment;

public interface Committer {
    public Commitment commit(byte[] var1);

    public boolean isRevealed(Commitment var1, byte[] var2);
}

