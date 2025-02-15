/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP192K1Field;
import org.bouncycastle.math.ec.custom.sec.SecP192K1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public class SecP192K1Point
extends ECPoint.AbstractFp {
    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP192K1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement)this.x;
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement)this.y;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement)eCPoint.getXCoord();
        SecP192K1FieldElement secP192K1FieldElement4 = (SecP192K1FieldElement)eCPoint.getYCoord();
        SecP192K1FieldElement secP192K1FieldElement5 = (SecP192K1FieldElement)this.zs[0];
        SecP192K1FieldElement secP192K1FieldElement6 = (SecP192K1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat192.createExt();
        int[] nArray6 = Nat192.create();
        int[] nArray7 = Nat192.create();
        int[] nArray8 = Nat192.create();
        boolean bl = secP192K1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP192K1FieldElement3.x;
            nArray3 = secP192K1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP192K1Field.square(secP192K1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP192K1Field.multiply(nArray3, secP192K1FieldElement3.x, nArray4);
            SecP192K1Field.multiply(nArray3, secP192K1FieldElement5.x, nArray3);
            SecP192K1Field.multiply(nArray3, secP192K1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP192K1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP192K1FieldElement.x;
            nArray = secP192K1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP192K1Field.square(secP192K1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP192K1Field.multiply(nArray, secP192K1FieldElement.x, nArray2);
            SecP192K1Field.multiply(nArray, secP192K1FieldElement6.x, nArray);
            SecP192K1Field.multiply(nArray, secP192K1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat192.create();
        SecP192K1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP192K1Field.subtract(nArray, nArray3, nArray10);
        if (Nat192.isZero(nArray9)) {
            if (Nat192.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP192K1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat192.create();
        SecP192K1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP192K1Field.multiply(nArray11, nArray2, nArray13);
        SecP192K1Field.negate(nArray12, nArray12);
        Nat192.mul(nArray, nArray12, nArray5);
        int n = Nat192.addBothTo(nArray13, nArray13, nArray12);
        SecP192K1Field.reduce32(n, nArray12);
        SecP192K1FieldElement secP192K1FieldElement7 = new SecP192K1FieldElement(nArray8);
        SecP192K1Field.square(nArray10, secP192K1FieldElement7.x);
        SecP192K1Field.subtract(secP192K1FieldElement7.x, nArray12, secP192K1FieldElement7.x);
        SecP192K1FieldElement secP192K1FieldElement8 = new SecP192K1FieldElement(nArray12);
        SecP192K1Field.subtract(nArray13, secP192K1FieldElement7.x, secP192K1FieldElement8.x);
        SecP192K1Field.multiplyAddToExt(secP192K1FieldElement8.x, nArray10, nArray5);
        SecP192K1Field.reduce(nArray5, secP192K1FieldElement8.x);
        SecP192K1FieldElement secP192K1FieldElement9 = new SecP192K1FieldElement(nArray9);
        if (!bl) {
            SecP192K1Field.multiply(secP192K1FieldElement9.x, secP192K1FieldElement5.x, secP192K1FieldElement9.x);
        }
        if (!bl2) {
            SecP192K1Field.multiply(secP192K1FieldElement9.x, secP192K1FieldElement6.x, secP192K1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP192K1FieldElement9};
        return new SecP192K1Point(eCCurve, secP192K1FieldElement7, secP192K1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement)this.y;
        if (secP192K1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement)this.x;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement)this.zs[0];
        int[] nArray = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement.x, nArray);
        int[] nArray2 = Nat192.create();
        SecP192K1Field.square(nArray, nArray2);
        int[] nArray3 = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement2.x, nArray3);
        int n = Nat192.addBothTo(nArray3, nArray3, nArray3);
        SecP192K1Field.reduce32(n, nArray3);
        int[] nArray4 = nArray;
        SecP192K1Field.multiply(nArray, secP192K1FieldElement2.x, nArray4);
        n = Nat.shiftUpBits(6, nArray4, 2, 0);
        SecP192K1Field.reduce32(n, nArray4);
        int[] nArray5 = Nat192.create();
        n = Nat.shiftUpBits(6, nArray2, 3, 0, nArray5);
        SecP192K1Field.reduce32(n, nArray5);
        SecP192K1FieldElement secP192K1FieldElement4 = new SecP192K1FieldElement(nArray2);
        SecP192K1Field.square(nArray3, secP192K1FieldElement4.x);
        SecP192K1Field.subtract(secP192K1FieldElement4.x, nArray4, secP192K1FieldElement4.x);
        SecP192K1Field.subtract(secP192K1FieldElement4.x, nArray4, secP192K1FieldElement4.x);
        SecP192K1FieldElement secP192K1FieldElement5 = new SecP192K1FieldElement(nArray4);
        SecP192K1Field.subtract(nArray4, secP192K1FieldElement4.x, secP192K1FieldElement5.x);
        SecP192K1Field.multiply(secP192K1FieldElement5.x, nArray3, secP192K1FieldElement5.x);
        SecP192K1Field.subtract(secP192K1FieldElement5.x, nArray5, secP192K1FieldElement5.x);
        SecP192K1FieldElement secP192K1FieldElement6 = new SecP192K1FieldElement(nArray3);
        SecP192K1Field.twice(secP192K1FieldElement.x, secP192K1FieldElement6.x);
        if (!secP192K1FieldElement3.isOne()) {
            SecP192K1Field.multiply(secP192K1FieldElement6.x, secP192K1FieldElement3.x, secP192K1FieldElement6.x);
        }
        return new SecP192K1Point(eCCurve, secP192K1FieldElement4, secP192K1FieldElement5, new ECFieldElement[]{secP192K1FieldElement6}, this.withCompression);
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
        return new SecP192K1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

