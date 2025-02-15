/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.math.ec.ECPoint;

public class DualECPoints {
    private final ECPoint p;
    private final ECPoint q;
    private final int securityStrength;
    private final int cofactor;

    public DualECPoints(int n, ECPoint eCPoint, ECPoint eCPoint2, int n2) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = n;
        this.p = eCPoint;
        this.q = eCPoint2;
        this.cofactor = n2;
    }

    public int getSeedLen() {
        return this.p.getCurve().getFieldSize();
    }

    public int getMaxOutlen() {
        return (this.p.getCurve().getFieldSize() - (13 + DualECPoints.log2(this.cofactor))) / 8 * 8;
    }

    public ECPoint getP() {
        return this.p;
    }

    public ECPoint getQ() {
        return this.q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getCofactor() {
        return this.cofactor;
    }

    private static int log2(int n) {
        int n2 = 0;
        while ((n >>= 1) != 0) {
            ++n2;
        }
        return n2;
    }
}

