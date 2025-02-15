/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;

public class DHValidationParameters {
    private byte[] seed;
    private int counter;

    public DHValidationParameters(byte[] byArray, int n) {
        this.seed = byArray;
        this.counter = n;
    }

    public int getCounter() {
        return this.counter;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public boolean equals(Object object) {
        if (!(object instanceof DHValidationParameters)) {
            return false;
        }
        DHValidationParameters dHValidationParameters = (DHValidationParameters)object;
        if (dHValidationParameters.counter != this.counter) {
            return false;
        }
        return Arrays.areEqual(this.seed, dHValidationParameters.seed);
    }

    public int hashCode() {
        return this.counter ^ Arrays.hashCode(this.seed);
    }
}

