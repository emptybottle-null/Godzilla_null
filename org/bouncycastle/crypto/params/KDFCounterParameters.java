/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

public final class KDFCounterParameters
implements DerivationParameters {
    private byte[] ki;
    private byte[] fixedInputDataCounterPrefix;
    private byte[] fixedInputDataCounterSuffix;
    private int r;

    public KDFCounterParameters(byte[] byArray, byte[] byArray2, int n) {
        this(byArray, null, byArray2, n);
    }

    public KDFCounterParameters(byte[] byArray, byte[] byArray2, byte[] byArray3, int n) {
        if (byArray == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.ki = Arrays.clone(byArray);
        this.fixedInputDataCounterPrefix = byArray2 == null ? new byte[0] : Arrays.clone(byArray2);
        this.fixedInputDataCounterSuffix = byArray3 == null ? new byte[0] : Arrays.clone(byArray3);
        if (n != 8 && n != 16 && n != 24 && n != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        this.r = n;
    }

    public byte[] getKI() {
        return this.ki;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public byte[] getFixedInputDataCounterPrefix() {
        return Arrays.clone(this.fixedInputDataCounterPrefix);
    }

    public byte[] getFixedInputDataCounterSuffix() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public int getR() {
        return this.r;
    }
}

