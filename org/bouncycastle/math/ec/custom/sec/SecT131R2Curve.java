/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecT131FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT131R2Point;
import org.bouncycastle.util.encoders.Hex;

public class SecT131R2Curve
extends ECCurve.AbstractF2m {
    private static final int SecT131R2_DEFAULT_COORDS = 6;
    protected SecT131R2Point infinity = new SecT131R2Point(this, null, null);

    public SecT131R2Curve() {
        super(131, 2, 3, 8);
        this.a = this.fromBigInteger(new BigInteger(1, Hex.decode("03E5A88919D7CAFCBF415F07C2176573B2")));
        this.b = this.fromBigInteger(new BigInteger(1, Hex.decode("04B8266A46C55657AC734CE38F018F2192")));
        this.order = new BigInteger(1, Hex.decode("0400000000000000016954A233049BA98F"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    protected ECCurve cloneCurve() {
        return new SecT131R2Curve();
    }

    public boolean supportsCoordinateSystem(int n) {
        switch (n) {
            case 6: {
                return true;
            }
        }
        return false;
    }

    public int getFieldSize() {
        return 131;
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT131FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecT131R2Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecT131R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return false;
    }

    public int getM() {
        return 131;
    }

    public boolean isTrinomial() {
        return false;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }
}

