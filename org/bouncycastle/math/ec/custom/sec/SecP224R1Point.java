/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP224R1Field;
import org.bouncycastle.math.ec.custom.sec.SecP224R1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;

public class SecP224R1Point
extends ECPoint.AbstractFp {
    public SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP224R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP224R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement)this.x;
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement)this.y;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement)eCPoint.getXCoord();
        SecP224R1FieldElement secP224R1FieldElement4 = (SecP224R1FieldElement)eCPoint.getYCoord();
        SecP224R1FieldElement secP224R1FieldElement5 = (SecP224R1FieldElement)this.zs[0];
        SecP224R1FieldElement secP224R1FieldElement6 = (SecP224R1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat224.createExt();
        int[] nArray6 = Nat224.create();
        int[] nArray7 = Nat224.create();
        int[] nArray8 = Nat224.create();
        boolean bl = secP224R1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP224R1FieldElement3.x;
            nArray3 = secP224R1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP224R1Field.square(secP224R1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP224R1Field.multiply(nArray3, secP224R1FieldElement3.x, nArray4);
            SecP224R1Field.multiply(nArray3, secP224R1FieldElement5.x, nArray3);
            SecP224R1Field.multiply(nArray3, secP224R1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP224R1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP224R1FieldElement.x;
            nArray = secP224R1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP224R1Field.square(secP224R1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP224R1Field.multiply(nArray, secP224R1FieldElement.x, nArray2);
            SecP224R1Field.multiply(nArray, secP224R1FieldElement6.x, nArray);
            SecP224R1Field.multiply(nArray, secP224R1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat224.create();
        SecP224R1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP224R1Field.subtract(nArray, nArray3, nArray10);
        if (Nat224.isZero(nArray9)) {
            if (Nat224.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP224R1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat224.create();
        SecP224R1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP224R1Field.multiply(nArray11, nArray2, nArray13);
        SecP224R1Field.negate(nArray12, nArray12);
        Nat224.mul(nArray, nArray12, nArray5);
        int n = Nat224.addBothTo(nArray13, nArray13, nArray12);
        SecP224R1Field.reduce32(n, nArray12);
        SecP224R1FieldElement secP224R1FieldElement7 = new SecP224R1FieldElement(nArray8);
        SecP224R1Field.square(nArray10, secP224R1FieldElement7.x);
        SecP224R1Field.subtract(secP224R1FieldElement7.x, nArray12, secP224R1FieldElement7.x);
        SecP224R1FieldElement secP224R1FieldElement8 = new SecP224R1FieldElement(nArray12);
        SecP224R1Field.subtract(nArray13, secP224R1FieldElement7.x, secP224R1FieldElement8.x);
        SecP224R1Field.multiplyAddToExt(secP224R1FieldElement8.x, nArray10, nArray5);
        SecP224R1Field.reduce(nArray5, secP224R1FieldElement8.x);
        SecP224R1FieldElement secP224R1FieldElement9 = new SecP224R1FieldElement(nArray9);
        if (!bl) {
            SecP224R1Field.multiply(secP224R1FieldElement9.x, secP224R1FieldElement5.x, secP224R1FieldElement9.x);
        }
        if (!bl2) {
            SecP224R1Field.multiply(secP224R1FieldElement9.x, secP224R1FieldElement6.x, secP224R1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP224R1FieldElement9};
        return new SecP224R1Point(eCCurve, secP224R1FieldElement7, secP224R1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement)this.y;
        if (secP224R1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP224R1FieldElement secP224R1FieldElement2 = (SecP224R1FieldElement)this.x;
        SecP224R1FieldElement secP224R1FieldElement3 = (SecP224R1FieldElement)this.zs[0];
        int[] nArray = Nat224.create();
        int[] nArray2 = Nat224.create();
        int[] nArray3 = Nat224.create();
        SecP224R1Field.square(secP224R1FieldElement.x, nArray3);
        int[] nArray4 = Nat224.create();
        SecP224R1Field.square(nArray3, nArray4);
        boolean bl = secP224R1FieldElement3.isOne();
        int[] nArray5 = secP224R1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SecP224R1Field.square(secP224R1FieldElement3.x, nArray5);
        }
        SecP224R1Field.subtract(secP224R1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SecP224R1Field.add(secP224R1FieldElement2.x, nArray5, nArray6);
        SecP224R1Field.multiply(nArray6, nArray, nArray6);
        int n = Nat224.addBothTo(nArray6, nArray6, nArray6);
        SecP224R1Field.reduce32(n, nArray6);
        int[] nArray7 = nArray3;
        SecP224R1Field.multiply(nArray3, secP224R1FieldElement2.x, nArray7);
        n = Nat.shiftUpBits(7, nArray7, 2, 0);
        SecP224R1Field.reduce32(n, nArray7);
        n = Nat.shiftUpBits(7, nArray4, 3, 0, nArray);
        SecP224R1Field.reduce32(n, nArray);
        SecP224R1FieldElement secP224R1FieldElement4 = new SecP224R1FieldElement(nArray4);
        SecP224R1Field.square(nArray6, secP224R1FieldElement4.x);
        SecP224R1Field.subtract(secP224R1FieldElement4.x, nArray7, secP224R1FieldElement4.x);
        SecP224R1Field.subtract(secP224R1FieldElement4.x, nArray7, secP224R1FieldElement4.x);
        SecP224R1FieldElement secP224R1FieldElement5 = new SecP224R1FieldElement(nArray7);
        SecP224R1Field.subtract(nArray7, secP224R1FieldElement4.x, secP224R1FieldElement5.x);
        SecP224R1Field.multiply(secP224R1FieldElement5.x, nArray6, secP224R1FieldElement5.x);
        SecP224R1Field.subtract(secP224R1FieldElement5.x, nArray, secP224R1FieldElement5.x);
        SecP224R1FieldElement secP224R1FieldElement6 = new SecP224R1FieldElement(nArray6);
        SecP224R1Field.twice(secP224R1FieldElement.x, secP224R1FieldElement6.x);
        if (!bl) {
            SecP224R1Field.multiply(secP224R1FieldElement6.x, secP224R1FieldElement3.x, secP224R1FieldElement6.x);
        }
        return new SecP224R1Point(eCCurve, secP224R1FieldElement4, secP224R1FieldElement5, new ECFieldElement[]{secP224R1FieldElement6}, this.withCompression);
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
        return new SecP224R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

