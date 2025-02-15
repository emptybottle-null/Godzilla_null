/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

public interface EntropySource {
    public boolean isPredictionResistant();

    public byte[] getEntropy();

    public int entropySize();
}

