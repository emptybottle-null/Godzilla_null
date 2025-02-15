/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

public final class KDFFeedbackParameters
implements DerivationParameters {
    private static final int UNUSED_R = -1;
    private final byte[] ki;
    private final byte[] iv;
    private final boolean useCounter;
    private final int r;
    private final byte[] fixedInputData;

    private KDFFeedbackParameters(byte[] byArray, byte[] byArray2, byte[] byArray3, int n, boolean bl) {
        if (byArray == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.ki = Arrays.clone(byArray);
        this.fixedInputData = byArray3 == null ? new byte[0] : Arrays.clone(byArray3);
        this.r = n;
        this.iv = byArray2 == null ? new byte[0] : Arrays.clone(byArray2);
        this.useCounter = bl;
    }

    public static KDFFeedbackParameters createWithCounter(byte[] byArray, byte[] byArray2, byte[] byArray3, int n) {
        if (n != 8 && n != 16 && n != 24 && n != 32) {
            throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
        }
        return new KDFFeedbackParameters(byArray, byArray2, byArray3, n, true);
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        return new KDFFeedbackParameters(byArray, byArray2, byArray3, -1, false);
    }

    public byte[] getKI() {
        return this.ki;
    }

    public byte[] getIV() {
        return this.iv;
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

