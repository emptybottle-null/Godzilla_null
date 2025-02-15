/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class RC5Parameters
implements CipherParameters {
    private byte[] key;
    private int rounds;

    public RC5Parameters(byte[] byArray, int n) {
        if (byArray.length > 255) {
            throw new IllegalArgumentException("RC5 key length can be no greater than 255");
        }
        this.key = new byte[byArray.length];
        this.rounds = n;
        System.arraycopy(byArray, 0, this.key, 0, byArray.length);
    }

    public byte[] getKey() {
        return this.key;
    }

    public int getRounds() {
        return this.rounds;
    }
}

