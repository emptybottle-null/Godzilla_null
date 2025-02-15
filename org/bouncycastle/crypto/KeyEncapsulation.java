/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;

public interface KeyEncapsulation {
    public void init(CipherParameters var1);

    public CipherParameters encrypt(byte[] var1, int var2, int var3);

    public CipherParameters decrypt(byte[] var1, int var2, int var3, int var4);
}

