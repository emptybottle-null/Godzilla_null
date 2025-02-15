/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;

public class GLVTypeBParameters {
    protected final BigInteger beta;
    protected final BigInteger lambda;
    protected final BigInteger v1A;
    protected final BigInteger v1B;
    protected final BigInteger v2A;
    protected final BigInteger v2B;
    protected final BigInteger g1;
    protected final BigInteger g2;
    protected final int bits;

    private static void checkVector(BigInteger[] bigIntegerArray, String string) {
        if (bigIntegerArray == null || bigIntegerArray.length != 2 || bigIntegerArray[0] == null || bigIntegerArray[1] == null) {
            throw new IllegalArgumentException("'" + string + "' must consist of exactly 2 (non-null) values");
        }
    }

    public GLVTypeBParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2, BigInteger bigInteger3, BigInteger bigInteger4, int n) {
        GLVTypeBParameters.checkVector(bigIntegerArray, "v1");
        GLVTypeBParameters.checkVector(bigIntegerArray2, "v2");
        this.beta = bigInteger;
        this.lambda = bigInteger2;
        this.v1A = bigIntegerArray[0];
        this.v1B = bigIntegerArray[1];
        this.v2A = bigIntegerArray2[0];
        this.v2B = bigIntegerArray2[1];
        this.g1 = bigInteger3;
        this.g2 = bigInteger4;
        this.bits = n;
    }

    public BigInteger getBeta() {
        return this.beta;
    }

    public BigInteger getLambda() {
        return this.lambda;
    }

    public BigInteger[] getV1() {
        return new BigInteger[]{this.v1A, this.v1B};
    }

    public BigInteger getV1A() {
        return this.v1A;
    }

    public BigInteger getV1B() {
        return this.v1B;
    }

    public BigInteger[] getV2() {
        return new BigInteger[]{this.v2A, this.v2B};
    }

    public BigInteger getV2A() {
        return this.v2A;
    }

    public BigInteger getV2B() {
        return this.v2B;
    }

    public BigInteger getG1() {
        return this.g1;
    }

    public BigInteger getG2() {
        return this.g2;
    }

    public int getBits() {
        return this.bits;
    }
}

