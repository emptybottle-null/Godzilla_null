/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP384R1Field;
import org.bouncycastle.math.ec.custom.sec.SecP384R1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;

public class SecP384R1Point
extends ECPoint.AbstractFp {
    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP384R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement)this.x;
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement)this.y;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement)eCPoint.getXCoord();
        SecP384R1FieldElement secP384R1FieldElement4 = (SecP384R1FieldElement)eCPoint.getYCoord();
        SecP384R1FieldElement secP384R1FieldElement5 = (SecP384R1FieldElement)this.zs[0];
        SecP384R1FieldElement secP384R1FieldElement6 = (SecP384R1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat.create(24);
        int[] nArray6 = Nat.create(24);
        int[] nArray7 = Nat.create(12);
        int[] nArray8 = Nat.create(12);
        boolean bl = secP384R1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP384R1FieldElement3.x;
            nArray3 = secP384R1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP384R1Field.square(secP384R1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP384R1Field.multiply(nArray3, secP384R1FieldElement3.x, nArray4);
            SecP384R1Field.multiply(nArray3, secP384R1FieldElement5.x, nArray3);
            SecP384R1Field.multiply(nArray3, secP384R1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP384R1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP384R1FieldElement.x;
            nArray = secP384R1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP384R1Field.square(secP384R1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP384R1Field.multiply(nArray, secP384R1FieldElement.x, nArray2);
            SecP384R1Field.multiply(nArray, secP384R1FieldElement6.x, nArray);
            SecP384R1Field.multiply(nArray, secP384R1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat.create(12);
        SecP384R1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = Nat.create(12);
        SecP384R1Field.subtract(nArray, nArray3, nArray10);
        if (Nat.isZero(12, nArray9)) {
            if (Nat.isZero(12, nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP384R1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat.create(12);
        SecP384R1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP384R1Field.multiply(nArray11, nArray2, nArray13);
        SecP384R1Field.negate(nArray12, nArray12);
        Nat384.mul(nArray, nArray12, nArray5);
        int n = Nat.addBothTo(12, nArray13, nArray13, nArray12);
        SecP384R1Field.reduce32(n, nArray12);
        SecP384R1FieldElement secP384R1FieldElement7 = new SecP384R1FieldElement(nArray8);
        SecP384R1Field.square(nArray10, secP384R1FieldElement7.x);
        SecP384R1Field.subtract(secP384R1FieldElement7.x, nArray12, secP384R1FieldElement7.x);
        SecP384R1FieldElement secP384R1FieldElement8 = new SecP384R1FieldElement(nArray12);
        SecP384R1Field.subtract(nArray13, secP384R1FieldElement7.x, secP384R1FieldElement8.x);
        Nat384.mul(secP384R1FieldElement8.x, nArray10, nArray6);
        SecP384R1Field.addExt(nArray5, nArray6, nArray5);
        SecP384R1Field.reduce(nArray5, secP384R1FieldElement8.x);
        SecP384R1FieldElement secP384R1FieldElement9 = new SecP384R1FieldElement(nArray9);
        if (!bl) {
            SecP384R1Field.multiply(secP384R1FieldElement9.x, secP384R1FieldElement5.x, secP384R1FieldElement9.x);
        }
        if (!bl2) {
            SecP384R1Field.multiply(secP384R1FieldElement9.x, secP384R1FieldElement6.x, secP384R1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP384R1FieldElement9};
        return new SecP384R1Point(eCCurve, secP384R1FieldElement7, secP384R1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement)this.y;
        if (secP384R1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement)this.x;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement)this.zs[0];
        int[] nArray = Nat.create(12);
        int[] nArray2 = Nat.create(12);
        int[] nArray3 = Nat.create(12);
        SecP384R1Field.square(secP384R1FieldElement.x, nArray3);
        int[] nArray4 = Nat.create(12);
        SecP384R1Field.square(nArray3, nArray4);
        boolean bl = secP384R1FieldElement3.isOne();
        int[] nArray5 = secP384R1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SecP384R1Field.square(secP384R1FieldElement3.x, nArray5);
        }
        SecP384R1Field.subtract(secP384R1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SecP384R1Field.add(secP384R1FieldElement2.x, nArray5, nArray6);
        SecP384R1Field.multiply(nArray6, nArray, nArray6);
        int n = Nat.addBothTo(12, nArray6, nArray6, nArray6);
        SecP384R1Field.reduce32(n, nArray6);
        int[] nArray7 = nArray3;
        SecP384R1Field.multiply(nArray3, secP384R1FieldElement2.x, nArray7);
        n = Nat.shiftUpBits(12, nArray7, 2, 0);
        SecP384R1Field.reduce32(n, nArray7);
        n = Nat.shiftUpBits(12, nArray4, 3, 0, nArray);
        SecP384R1Field.reduce32(n, nArray);
        SecP384R1FieldElement secP384R1FieldElement4 = new SecP384R1FieldElement(nArray4);
        SecP384R1Field.square(nArray6, secP384R1FieldElement4.x);
        SecP384R1Field.subtract(secP384R1FieldElement4.x, nArray7, secP384R1FieldElement4.x);
        SecP384R1Field.subtract(secP384R1FieldElement4.x, nArray7, secP384R1FieldElement4.x);
        SecP384R1FieldElement secP384R1FieldElement5 = new SecP384R1FieldElement(nArray7);
        SecP384R1Field.subtract(nArray7, secP384R1FieldElement4.x, secP384R1FieldElement5.x);
        SecP384R1Field.multiply(secP384R1FieldElement5.x, nArray6, secP384R1FieldElement5.x);
        SecP384R1Field.subtract(secP384R1FieldElement5.x, nArray, secP384R1FieldElement5.x);
        SecP384R1FieldElement secP384R1FieldElement6 = new SecP384R1FieldElement(nArray6);
        SecP384R1Field.twice(secP384R1FieldElement.x, secP384R1FieldElement6.x);
        if (!bl) {
            SecP384R1Field.multiply(secP384R1FieldElement6.x, secP384R1FieldElement3.x, secP384R1FieldElement6.x);
        }
        return new SecP384R1Point(eCCurve, secP384R1FieldElement4, secP384R1FieldElement5, new ECFieldElement[]{secP384R1FieldElement6}, this.withCompression);
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
        return new SecP384R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

