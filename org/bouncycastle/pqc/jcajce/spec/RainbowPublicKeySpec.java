/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;

public class RainbowPublicKeySpec
implements KeySpec {
    private short[][] coeffquadratic;
    private short[][] coeffsingular;
    private short[] coeffscalar;
    private int docLength;

    public RainbowPublicKeySpec(int n, short[][] sArray, short[][] sArray2, short[] sArray3) {
        this.docLength = n;
        this.coeffquadratic = sArray;
        this.coeffsingular = sArray2;
        this.coeffscalar = sArray3;
    }

    public int getDocLength() {
        return this.docLength;
    }

    public short[][] getCoeffQuadratic() {
        return this.coeffquadratic;
    }

    public short[][] getCoeffSingular() {
        return this.coeffsingular;
    }

    public short[] getCoeffScalar() {
        return this.coeffscalar;
    }
}

