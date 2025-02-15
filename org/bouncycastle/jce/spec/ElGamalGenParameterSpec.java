/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class ElGamalGenParameterSpec
implements AlgorithmParameterSpec {
    private int primeSize;

    public ElGamalGenParameterSpec(int n) {
        this.primeSize = n;
    }

    public int getPrimeSize() {
        return this.primeSize;
    }
}

