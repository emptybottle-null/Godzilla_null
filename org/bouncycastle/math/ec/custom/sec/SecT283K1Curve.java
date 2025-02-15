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
import org.bouncycastle.math.ec.custom.sec.SecT283FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT283K1Point;
import org.bouncycastle.util.encoders.Hex;

public class SecT283K1Curve
extends ECCurve.AbstractF2m {
    private static final int SecT283K1_DEFAULT_COORDS = 6;
    protected SecT283K1Point infinity = new SecT283K1Point(this, null, null);

    public SecT283K1Curve() {
        super(283, 5, 7, 12);
        this.a = this.fromBigInteger(BigInteger.valueOf(0L));
        this.b = this.fromBigInteger(BigInteger.valueOf(1L));
        this.order = new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE9AE2ED07577265DFF7F94451E061E163C61"));
        this.cofactor = BigInteger.valueOf(4L);
        this.coord = 6;
    }

    protected ECCurve cloneCurve() {
        return new SecT283K1Curve();
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
        return 283;
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT283FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecT283K1Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecT283K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return true;
    }

    public int getM() {
        return 283;
    }

    public boolean isTrinomial() {
        return false;
    }

    public int getK1() {
        return 5;
    }

    public int getK2() {
        return 7;
    }

    public int getK3() {
        return 12;
    }
}

