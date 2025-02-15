/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

public final class KDFDoublePipelineIterationParameters
implements DerivationParameters {
    private static final int UNUSED_R = 32;
    private final byte[] ki;
    private final boolean useCounter;
    private final int r;
    private final byte[] fixedInputData;

    private KDFDoublePipelineIterationParameters(byte[] byArray, byte[] byArray2, int n, boolean bl) {
        if (byArray == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.ki = Arrays.clone(byArray);
        this.fixedInputData = byArray2 == null ? new byte[0] : Arrays.clone(byArray2);
        if (n != 8 && n != 16 && n != 24 && n != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        this.r = n;
        this.useCounter = bl;
    }

    public static KDFDoublePipelineIterationParameters createWithCounter(byte[] byArray, byte[] byArray2, int n) {
        return new KDFDoublePipelineIterationParameters(byArray, byArray2, n, true);
    }

    public static KDFDoublePipelineIterationParameters createWithoutCounter(byte[] byArray, byte[] byArray2) {
        return new KDFDoublePipelineIterationParameters(byArray, byArray2, 32, false);
    }

    public byte[] getKI() {
        return this.ki;
    }

    public boolean useCounter() {
        return this.useCounter;
    }

    public int getR() {
        return this.r;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }
}

