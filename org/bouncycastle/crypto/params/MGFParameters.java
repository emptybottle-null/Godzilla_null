/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

public class MGFParameters
implements DerivationParameters {
    byte[] seed;

    public MGFParameters(byte[] byArray) {
        this(byArray, 0, byArray.length);
    }

    public MGFParameters(byte[] byArray, int n, int n2) {
        this.seed = new byte[n2];
        System.arraycopy(byArray, n, this.seed, 0, n2);
    }

    public byte[] getSeed() {
        return this.seed;
    }
}

