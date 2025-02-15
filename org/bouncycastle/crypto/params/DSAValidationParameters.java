/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;

public class DSAValidationParameters {
    private int usageIndex;
    private byte[] seed;
    private int counter;

    public DSAValidationParameters(byte[] byArray, int n) {
        this(byArray, n, -1);
    }

    public DSAValidationParameters(byte[] byArray, int n, int n2) {
        this.seed = byArray;
        this.counter = n;
        this.usageIndex = n2;
    }

    public int getCounter() {
        return this.counter;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }

    public int hashCode() {
        return this.counter ^ Arrays.hashCode(this.seed);
    }

    public boolean equals(Object object) {
        if (!(object instanceof DSAValidationParameters)) {
            return false;
        }
        DSAValidationParameters dSAValidationParameters = (DSAValidationParameters)object;
        if (dSAValidationParameters.counter != this.counter) {
            return false;
        }
        return Arrays.areEqual(this.seed, dSAValidationParameters.seed);
    }
}

