/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecT163FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT163R2Point;
import org.bouncycastle.util.encoders.Hex;

public class SecT163R2Curve
extends ECCurve.AbstractF2m {
    private static final int SecT163R2_DEFAULT_COORDS = 6;
    protected SecT163R2Point infinity = new SecT163R2Point(this, null, null);

    public SecT163R2Curve() {
        super(163, 3, 6, 7);
        this.a = this.fromBigInteger(BigInteger.valueOf(1L));
        this.b = this.fromBigInteger(new BigInteger(1, Hex.decode("020A601907B8C953CA1481EB10512F78744A3205FD")));
        this.order = new BigInteger(1, Hex.decode("040000000000000000000292FE77E70C12A4234C33"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    protected ECCurve cloneCurve() {
        return new SecT163R2Curve();
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
        return 163;
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT163FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecT163R2Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecT163R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return false;
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

