/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP160R2Field;
import org.bouncycastle.math.ec.custom.sec.SecP160R2FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;

public class SecP160K1Point
extends ECPoint.AbstractFp {
    public SecP160K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP160K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP160K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP160K1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
    }

    public ECPoint add(ECPoint eCPoint) {
        int[] nArray;
        int[] nArray2;
        int[] nArray3;
        int[] nArray4;
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return this.twice();
        }
        ECCurve eCCurve = this.getCurve();
        SecP160R2FieldElement secP160R2FieldElement = (SecP160R2FieldElement)this.x;
        SecP160R2FieldElement secP160R2FieldElement2 = (SecP160R2FieldElement)this.y;
        SecP160R2FieldElement secP160R2FieldElement3 = (SecP160R2FieldElement)eCPoint.getXCoord();
        SecP160R2FieldElement secP160R2FieldElement4 = (SecP160R2FieldElement)eCPoint.getYCoord();
        SecP160R2FieldElement secP160R2FieldElement5 = (SecP160R2FieldElement)this.zs[0];
        SecP160R2FieldElement secP160R2FieldElement6 = (SecP160R2FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat160.createExt();
        int[] nArray6 = Nat160.create();
        int[] nArray7 = Nat160.create();
        int[] nArray8 = Nat160.create();
        boolean bl = secP160R2FieldElement5.isOne();
        if (bl) {
            nArray4 = secP160R2FieldElement3.x;
            nArray3 = secP160R2FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP160R2Field.square(secP160R2FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP160R2Field.multiply(nArray3, secP160R2FieldElement3.x, nArray4);
            SecP160R2Field.multiply(nArray3, secP160R2FieldElement5.x, nArray3);
            SecP160R2Field.multiply(nArray3, secP160R2FieldElement4.x, nArray3);
        }
        boolean bl2 = secP160R2FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP160R2FieldElement.x;
            nArray = secP160R2FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP160R2Field.square(secP160R2FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP160R2Field.multiply(nArray, secP160R2FieldElement.x, nArray2);
            SecP160R2Field.multiply(nArray, secP160R2FieldElement6.x, nArray);
            SecP160R2Field.multiply(nArray, secP160R2FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat160.create();
        SecP160R2Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP160R2Field.subtract(nArray, nArray3, nArray10);
        if (Nat160.isZero(nArray9)) {
            if (Nat160.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP160R2Field.square(nArray9, nArray11);
        int[] nArray12 = Nat160.create();
        SecP160R2Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP160R2Field.multiply(nArray11, nArray2, nArray13);
        SecP160R2Field.negate(nArray12, nArray12);
        Nat160.mul(nArray, nArray12, nArray5);
        int n = Nat160.addBothTo(nArray13, nArray13, nArray12);
        SecP160R2Field.reduce32(n, nArray12);
        SecP160R2FieldElement secP160R2FieldElement7 = new SecP160R2FieldElement(nArray8);
        SecP160R2Field.square(nArray10, secP160R2FieldElement7.x);
        SecP160R2Field.subtract(secP160R2FieldElement7.x, nArray12, secP160R2FieldElement7.x);
        SecP160R2FieldElement secP160R2FieldElement8 = new SecP160R2FieldElement(nArray12);
        SecP160R2Field.subtract(nArray13, secP160R2FieldElement7.x, secP160R2FieldElement8.x);
        SecP160R2Field.multiplyAddToExt(secP160R2FieldElement8.x, nArray10, nArray5);
        SecP160R2Field.reduce(nArray5, secP160R2FieldElement8.x);
        SecP160R2FieldElement secP160R2FieldElement9 = new SecP160R2FieldElement(nArray9);
        if (!bl) {
            SecP160R2Field.multiply(secP160R2FieldElement9.x, secP160R2FieldElement5.x, secP160R2FieldElement9.x);
        }
        if (!bl2) {
            SecP160R2Field.multiply(secP160R2FieldElement9.x, secP160R2FieldElement6.x, secP160R2FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP160R2FieldElement9};
        return new SecP160K1Point(eCCurve, secP160R2FieldElement7, secP160R2FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP160R2FieldElement secP160R2FieldElement = (SecP160R2FieldElement)this.y;
        if (secP160R2FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP160R2FieldElement secP160R2FieldElement2 = (SecP160R2FieldElement)this.x;
        SecP160R2FieldElement secP160R2FieldElement3 = (SecP160R2FieldElement)this.zs[0];
        int[] nArray = Nat160.create();
        SecP160R2Field.square(secP160R2FieldElement.x, nArray);
        int[] nArray2 = Nat160.create();
        SecP160R2Field.square(nArray, nArray2);
        int[] nArray3 = Nat160.create();
        SecP160R2Field.square(secP160R2FieldElement2.x, nArray3);
        int n = Nat160.addBothTo(nArray3, nArray3, nArray3);
        SecP160R2Field.reduce32(n, nArray3);
        int[] nArray4 = nArray;
        SecP160R2Field.multiply(nArray, secP160R2FieldElement2.x, nArray4);
        n = Nat.shiftUpBits(5, nArray4, 2, 0);
        SecP160R2Field.reduce32(n, nArray4);
        int[] nArray5 = Nat160.create();
        n = Nat.shiftUpBits(5, nArray2, 3, 0, nArray5);
        SecP160R2Field.reduce32(n, nArray5);
        SecP160R2FieldElement secP160R2FieldElement4 = new SecP160R2FieldElement(nArray2);
        SecP160R2Field.square(nArray3, secP160R2FieldElement4.x);
        SecP160R2Field.subtract(secP160R2FieldElement4.x, nArray4, secP160R2FieldElement4.x);
        SecP160R2Field.subtract(secP160R2FieldElement4.x, nArray4, secP160R2FieldElement4.x);
        SecP160R2FieldElement secP160R2FieldElement5 = new SecP160R2FieldElement(nArray4);
        SecP160R2Field.subtract(nArray4, secP160R2FieldElement4.x, secP160R2FieldElement5.x);
        SecP160R2Field.multiply(secP160R2FieldElement5.x, nArray3, secP160R2FieldElement5.x);
        SecP160R2Field.subtract(secP160R2FieldElement5.x, nArray5, secP160R2FieldElement5.x);
        SecP160R2FieldElement secP160R2FieldElement6 = new SecP160R2FieldElement(nArray3);
        SecP160R2Field.twice(secP160R2FieldElement.x, secP160R2FieldElement6.x);
        if (!secP160R2FieldElement3.isOne()) {
            SecP160R2Field.multiply(secP160R2FieldElement6.x, secP160R2FieldElement3.x, secP160R2FieldElement6.x);
        }
        return new SecP160K1Point(eCCurve, secP160R2FieldElement4, secP160R2FieldElement5, new ECFieldElement[]{secP160R2FieldElement6}, this.withCompression);
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this == eCPoint) {
            return this.threeTimes();
        }
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this.twice();
        }
        ECFieldElement eCFieldElement = this.y;
        if (eCFieldElement.isZero()) {
            return eCPoint;
        }
        return this.twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        if (this.isInfinity() || this.y.isZero()) {
            return this;
        }
        return this.twice().add(this);
    }

    public ECPoint negate() {
        if (this.isInfinity()) {
            return this;
        }
        return new SecP160K1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

