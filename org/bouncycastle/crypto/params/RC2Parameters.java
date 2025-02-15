/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.KeyParameter;

public class RC2Parameters
extends KeyParameter {
    private int bits;

    public RC2Parameters(byte[] byArray) {
        this(byArray, byArray.length > 128 ? 1024 : byArray.length * 8);
    }

    public RC2Parameters(byte[] byArray, int n) {
        super(byArray);
        this.bits = n;
    }

    public int getEffectiveKeyBits() {
        return this.bits;
    }
}

