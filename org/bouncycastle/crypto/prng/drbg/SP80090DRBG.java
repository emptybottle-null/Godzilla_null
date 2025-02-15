/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

public interface SP80090DRBG {
    public int getBlockSize();

    public int generate(byte[] var1, byte[] var2, boolean var3);

    public void reseed(byte[] var1);
}

