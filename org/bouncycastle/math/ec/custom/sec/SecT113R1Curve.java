/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecT113FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT113R1Point;
import org.bouncycastle.util.encoders.Hex;

public class SecT113R1Curve
extends ECCurve.AbstractF2m {
    private static final int SecT113R1_DEFAULT_COORDS = 6;
    protected SecT113R1Point infinity = new SecT113R1Point(this, null, null);

    public SecT113R1Curve() {
        super(113, 9, 0, 0);
        this.a = this.fromBigInteger(new BigInteger(1, Hex.decode("003088250CA6E7C7FE649CE85820F7")));
        this.b = this.fromBigInteger(new BigInteger(1, Hex.decode("00E8BEE4D3E2260744188BE0E9C723")));
        this.order = new BigInteger(1, Hex.decode("0100000000000000D9CCEC8A39E56F"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    protected ECCurve cloneCurve() {
        return new SecT113R1Curve();
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
        return 113;
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT113FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecT113R1Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecT113R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return false;
    }

    public int getM() {
        return 113;
    }

    public boolean isTrinomial() {
        return true;
    }

    public int getK1() {
        return 9;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }
}

