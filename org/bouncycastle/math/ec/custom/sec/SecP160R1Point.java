/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP160R1Field;
import org.bouncycastle.math.ec.custom.sec.SecP160R1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;

public class SecP160R1Point
extends ECPoint.AbstractFp {
    public SecP160R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP160R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP160R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP160R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP160R1FieldElement secP160R1FieldElement = (SecP160R1FieldElement)this.x;
        SecP160R1FieldElement secP160R1FieldElement2 = (SecP160R1FieldElement)this.y;
        SecP160R1FieldElement secP160R1FieldElement3 = (SecP160R1FieldElement)eCPoint.getXCoord();
        SecP160R1FieldElement secP160R1FieldElement4 = (SecP160R1FieldElement)eCPoint.getYCoord();
        SecP160R1FieldElement secP160R1FieldElement5 = (SecP160R1FieldElement)this.zs[0];
        SecP160R1FieldElement secP160R1FieldElement6 = (SecP160R1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat160.createExt();
        int[] nArray6 = Nat160.create();
        int[] nArray7 = Nat160.create();
        int[] nArray8 = Nat160.create();
        boolean bl = secP160R1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP160R1FieldElement3.x;
            nArray3 = secP160R1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP160R1Field.square(secP160R1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP160R1Field.multiply(nArray3, secP160R1FieldElement3.x, nArray4);
            SecP160R1Field.multiply(nArray3, secP160R1FieldElement5.x, nArray3);
            SecP160R1Field.multiply(nArray3, secP160R1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP160R1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP160R1FieldElement.x;
            nArray = secP160R1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP160R1Field.square(secP160R1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP160R1Field.multiply(nArray, secP160R1FieldElement.x, nArray2);
            SecP160R1Field.multiply(nArray, secP160R1FieldElement6.x, nArray);
            SecP160R1Field.multiply(nArray, secP160R1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat160.create();
        SecP160R1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP160R1Field.subtract(nArray, nArray3, nArray10);
        if (Nat160.isZero(nArray9)) {
            if (Nat160.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP160R1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat160.create();
        SecP160R1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP160R1Field.multiply(nArray11, nArray2, nArray13);
        SecP160R1Field.negate(nArray12, nArray12);
        Nat160.mul(nArray, nArray12, nArray5);
        int n = Nat160.addBothTo(nArray13, nArray13, nArray12);
        SecP160R1Field.reduce32(n, nArray12);
        SecP160R1FieldElement secP160R1FieldElement7 = new SecP160R1FieldElement(nArray8);
        SecP160R1Field.square(nArray10, secP160R1FieldElement7.x);
        SecP160R1Field.subtract(secP160R1FieldElement7.x, nArray12, secP160R1FieldElement7.x);
        SecP160R1FieldElement secP160R1FieldElement8 = new SecP160R1FieldElement(nArray12);
        SecP160R1Field.subtract(nArray13, secP160R1FieldElement7.x, secP160R1FieldElement8.x);
        SecP160R1Field.multiplyAddToExt(secP160R1FieldElement8.x, nArray10, nArray5);
        SecP160R1Field.reduce(nArray5, secP160R1FieldElement8.x);
        SecP160R1FieldElement secP160R1FieldElement9 = new SecP160R1FieldElement(nArray9);
        if (!bl) {
            SecP160R1Field.multiply(secP160R1FieldElement9.x, secP160R1FieldElement5.x, secP160R1FieldElement9.x);
        }
        if (!bl2) {
            SecP160R1Field.multiply(secP160R1FieldElement9.x, secP160R1FieldElement6.x, secP160R1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP160R1FieldElement9};
        return new SecP160R1Point(eCCurve, secP160R1FieldElement7, secP160R1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP160R1FieldElement secP160R1FieldElement = (SecP160R1FieldElement)this.y;
        if (secP160R1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP160R1FieldElement secP160R1FieldElement2 = (SecP160R1FieldElement)this.x;
        SecP160R1FieldElement secP160R1FieldElement3 = (SecP160R1FieldElement)this.zs[0];
        int[] nArray = Nat160.create();
        int[] nArray2 = Nat160.create();
        int[] nArray3 = Nat160.create();
        SecP160R1Field.square(secP160R1FieldElement.x, nArray3);
        int[] nArray4 = Nat160.create();
        SecP160R1Field.square(nArray3, nArray4);
        boolean bl = secP160R1FieldElement3.isOne();
        int[] nArray5 = secP160R1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SecP160R1Field.square(secP160R1FieldElement3.x, nArray5);
        }
        SecP160R1Field.subtract(secP160R1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SecP160R1Field.add(secP160R1FieldElement2.x, nArray5, nArray6);
        SecP160R1Field.multiply(nArray6, nArray, nArray6);
        int n = Nat160.addBothTo(nArray6, nArray6, nArray6);
        SecP160R1Field.reduce32(n, nArray6);
        int[] nArray7 = nArray3;
        SecP160R1Field.multiply(nArray3, secP160R1FieldElement2.x, nArray7);
        n = Nat.shiftUpBits(5, nArray7, 2, 0);
        SecP160R1Field.reduce32(n, nArray7);
        n = Nat.shiftUpBits(5, nArray4, 3, 0, nArray);
        SecP160R1Field.reduce32(n, nArray);
        SecP160R1FieldElement secP160R1FieldElement4 = new SecP160R1FieldElement(nArray4);
        SecP160R1Field.square(nArray6, secP160R1FieldElement4.x);
        SecP160R1Field.subtract(secP160R1FieldElement4.x, nArray7, secP160R1FieldElement4.x);
        SecP160R1Field.subtract(secP160R1FieldElement4.x, nArray7, secP160R1FieldElement4.x);
        SecP160R1FieldElement secP160R1FieldElement5 = new SecP160R1FieldElement(nArray7);
        SecP160R1Field.subtract(nArray7, secP160R1FieldElement4.x, secP160R1FieldElement5.x);
        SecP160R1Field.multiply(secP160R1FieldElement5.x, nArray6, secP160R1FieldElement5.x);
        SecP160R1Field.subtract(secP160R1FieldElement5.x, nArray, secP160R1FieldElement5.x);
        SecP160R1FieldElement secP160R1FieldElement6 = new SecP160R1FieldElement(nArray6);
        SecP160R1Field.twice(secP160R1FieldElement.x, secP160R1FieldElement6.x);
        if (!bl) {
            SecP160R1Field.multiply(secP160R1FieldElement6.x, secP160R1FieldElement3.x, secP160R1FieldElement6.x);
        }
        return new SecP160R1Point(eCCurve, secP160R1FieldElement4, secP160R1FieldElement5, new ECFieldElement[]{secP160R1FieldElement6}, this.withCompression);
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
        return new SecP160R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

