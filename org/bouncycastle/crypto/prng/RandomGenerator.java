/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

public interface RandomGenerator {
    public void addSeedMaterial(byte[] var1);

    public void addSeedMaterial(long var1);

    public void nextBytes(byte[] var1);

    public void nextBytes(byte[] var1, int var2, int var3);
}

