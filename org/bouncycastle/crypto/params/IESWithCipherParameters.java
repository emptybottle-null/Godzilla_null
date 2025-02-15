/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.IESParameters;

public class IESWithCipherParameters
extends IESParameters {
    private int cipherKeySize;

    public IESWithCipherParameters(byte[] byArray, byte[] byArray2, int n, int n2) {
        super(byArray, byArray2, n);
        this.cipherKeySize = n2;
    }

    public int getCipherKeySize() {
        return this.cipherKeySize;
    }
}

