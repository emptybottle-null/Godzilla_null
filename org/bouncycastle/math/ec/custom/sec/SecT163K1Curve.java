/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WTauNafMultiplier;
import org.bouncycastle.math.ec.custom.sec.SecT163FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT163K1Point;
import org.bouncycastle.util.encoders.Hex;

public class SecT163K1Curve
extends ECCurve.AbstractF2m {
    private static final int SecT163K1_DEFAULT_COORDS = 6;
    protected SecT163K1Point infinity = new SecT163K1Point(this, null, null);

    public SecT163K1Curve() {
        super(163, 3, 6, 7);
        this.b = this.a = this.fromBigInteger(BigInteger.valueOf(1L));
        this.order = new BigInteger(1, Hex.decode("04000000000000000000020108A2E0CC0D99F8A5EF"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    protected ECCurve cloneCurve() {
        return new SecT163K1Curve();
    }

    public boolean supportsCoordinateSystem(int n) {
        switch (n) {
            case 6: {
                return true;
            }
        }
        return false;
    }

    protected ECMultiplier createDefaultMultiplier() {
        return new WTauNafMultiplier();
    }

    public int getFieldSize() {
        return 163;
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT163FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecT163K1Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecT163K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return true;
    }

    public int getM() {
        return 163;
    }

    public boolean isTrinomial() {
        return false;
    }

    public int getK1() {
        return 3;
    }

    public int getK2() {
        return 6;
    }

    public int getK3() {
        return 7;
    }
}

