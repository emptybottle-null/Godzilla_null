/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP521R1Field;
import org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement;
import org.bouncycastle.math.raw.Nat;

public class SecP521R1Point
extends ECPoint.AbstractFp {
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP521R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement)this.x;
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement)this.y;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement)eCPoint.getXCoord();
        SecP521R1FieldElement secP521R1FieldElement4 = (SecP521R1FieldElement)eCPoint.getYCoord();
        SecP521R1FieldElement secP521R1FieldElement5 = (SecP521R1FieldElement)this.zs[0];
        SecP521R1FieldElement secP521R1FieldElement6 = (SecP521R1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat.create(17);
        int[] nArray6 = Nat.create(17);
        int[] nArray7 = Nat.create(17);
        int[] nArray8 = Nat.create(17);
        boolean bl = secP521R1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP521R1FieldElement3.x;
            nArray3 = secP521R1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP521R1Field.square(secP521R1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP521R1Field.multiply(nArray3, secP521R1FieldElement3.x, nArray4);
            SecP521R1Field.multiply(nArray3, secP521R1FieldElement5.x, nArray3);
            SecP521R1Field.multiply(nArray3, secP521R1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP521R1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP521R1FieldElement.x;
            nArray = secP521R1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP521R1Field.square(secP521R1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP521R1Field.multiply(nArray, secP521R1FieldElement.x, nArray2);
            SecP521R1Field.multiply(nArray, secP521R1FieldElement6.x, nArray);
            SecP521R1Field.multiply(nArray, secP521R1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat.create(17);
        SecP521R1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP521R1Field.subtract(nArray, nArray3, nArray10);
        if (Nat.isZero(17, nArray9)) {
            if (Nat.isZero(17, nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP521R1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat.create(17);
        SecP521R1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP521R1Field.multiply(nArray11, nArray2, nArray13);
        SecP521R1Field.multiply(nArray, nArray12, nArray5);
        SecP521R1FieldElement secP521R1FieldElement7 = new SecP521R1FieldElement(nArray8);
        SecP521R1Field.square(nArray10, secP521R1FieldElement7.x);
        SecP521R1Field.add(secP521R1FieldElement7.x, nArray12, secP521R1FieldElement7.x);
        SecP521R1Field.subtract(secP521R1FieldElement7.x, nArray13, secP521R1FieldElement7.x);
        SecP521R1Field.subtract(secP521R1FieldElement7.x, nArray13, secP521R1FieldElement7.x);
        SecP521R1FieldElement secP521R1FieldElement8 = new SecP521R1FieldElement(nArray12);
        SecP521R1Field.subtract(nArray13, secP521R1FieldElement7.x, secP521R1FieldElement8.x);
        SecP521R1Field.multiply(secP521R1FieldElement8.x, nArray10, nArray6);
        SecP521R1Field.subtract(nArray6, nArray5, secP521R1FieldElement8.x);
        SecP521R1FieldElement secP521R1FieldElement9 = new SecP521R1FieldElement(nArray9);
        if (!bl) {
            SecP521R1Field.multiply(secP521R1FieldElement9.x, secP521R1FieldElement5.x, secP521R1FieldElement9.x);
        }
        if (!bl2) {
            SecP521R1Field.multiply(secP521R1FieldElement9.x, secP521R1FieldElement6.x, secP521R1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP521R1FieldElement9};
        return new SecP521R1Point(eCCurve, secP521R1FieldElement7, secP521R1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement)this.y;
        if (secP521R1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement)this.x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement)this.zs[0];
        int[] nArray = Nat.create(17);
        int[] nArray2 = Nat.create(17);
        int[] nArray3 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.x, nArray3);
        int[] nArray4 = Nat.create(17);
        SecP521R1Field.square(nArray3, nArray4);
        boolean bl = secP521R1FieldElement3.isOne();
        int[] nArray5 = secP521R1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SecP521R1Field.square(secP521R1FieldElement3.x, nArray5);
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SecP521R1Field.add(secP521R1FieldElement2.x, nArray5, nArray6);
        SecP521R1Field.multiply(nArray6, nArray, nArray6);
        Nat.addBothTo(17, nArray6, nArray6, nArray6);
        SecP521R1Field.reduce23(nArray6);
        int[] nArray7 = nArray3;
        SecP521R1Field.multiply(nArray3, secP521R1FieldElement2.x, nArray7);
        Nat.shiftUpBits(17, nArray7, 2, 0);
        SecP521R1Field.reduce23(nArray7);
        Nat.shiftUpBits(17, nArray4, 3, 0, nArray);
        SecP521R1Field.reduce23(nArray);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(nArray4);
        SecP521R1Field.square(nArray6, secP521R1FieldElement4.x);
        SecP521R1Field.subtract(secP521R1FieldElement4.x, nArray7, secP521R1FieldElement4.x);
        SecP521R1Field.subtract(secP521R1FieldElement4.x, nArray7, secP521R1FieldElement4.x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(nArray7);
        SecP521R1Field.subtract(nArray7, secP521R1FieldElement4.x, secP521R1FieldElement5.x);
        SecP521R1Field.multiply(secP521R1FieldElement5.x, nArray6, secP521R1FieldElement5.x);
        SecP521R1Field.subtract(secP521R1FieldElement5.x, nArray, secP521R1FieldElement5.x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(nArray6);
        SecP521R1Field.twice(secP521R1FieldElement.x, secP521R1FieldElement6.x);
        if (!bl) {
            SecP521R1Field.multiply(secP521R1FieldElement6.x, secP521R1FieldElement3.x, secP521R1FieldElement6.x);
        }
        return new SecP521R1Point(eCCurve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6}, this.withCompression);
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

    protected ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }

    protected ECFieldElement three(ECFieldElement eCFieldElement) {
        return this.two(eCFieldElement).add(eCFieldElement);
    }

    protected ECFieldElement four(ECFieldElement eCFieldElement) {
        return this.two(this.two(eCFieldElement));
    }

    protected ECFieldElement eight(ECFieldElement eCFieldElement) {
        return this.four(this.two(eCFieldElement));
    }

    protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    public ECPoint negate() {
        if (this.isInfinity()) {
            return this;
        }
        return new SecP521R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

