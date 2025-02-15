/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters;

public class RainbowPublicKeyParameters
extends RainbowKeyParameters {
    private short[][] coeffquadratic;
    private short[][] coeffsingular;
    private short[] coeffscalar;

    public RainbowPublicKeyParameters(int n, short[][] sArray, short[][] sArray2, short[] sArray3) {
        super(false, n);
        this.coeffquadratic = sArray;
        this.coeffsingular = sArray2;
        this.coeffscalar = sArray3;
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

