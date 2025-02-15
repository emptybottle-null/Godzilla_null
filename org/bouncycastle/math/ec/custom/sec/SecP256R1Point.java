/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Field;
import org.bouncycastle.math.ec.custom.sec.SecP256R1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SecP256R1Point
extends ECPoint.AbstractFp {
    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecP256R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement)this.x;
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement)this.y;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement)eCPoint.getXCoord();
        SecP256R1FieldElement secP256R1FieldElement4 = (SecP256R1FieldElement)eCPoint.getYCoord();
        SecP256R1FieldElement secP256R1FieldElement5 = (SecP256R1FieldElement)this.zs[0];
        SecP256R1FieldElement secP256R1FieldElement6 = (SecP256R1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat256.createExt();
        int[] nArray6 = Nat256.create();
        int[] nArray7 = Nat256.create();
        int[] nArray8 = Nat256.create();
        boolean bl = secP256R1FieldElement5.isOne();
        if (bl) {
            nArray4 = secP256R1FieldElement3.x;
            nArray3 = secP256R1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SecP256R1Field.square(secP256R1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SecP256R1Field.multiply(nArray3, secP256R1FieldElement3.x, nArray4);
            SecP256R1Field.multiply(nArray3, secP256R1FieldElement5.x, nArray3);
            SecP256R1Field.multiply(nArray3, secP256R1FieldElement4.x, nArray3);
        }
        boolean bl2 = secP256R1FieldElement6.isOne();
        if (bl2) {
            nArray2 = secP256R1FieldElement.x;
            nArray = secP256R1FieldElement2.x;
        } else {
            nArray = nArray8;
            SecP256R1Field.square(secP256R1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SecP256R1Field.multiply(nArray, secP256R1FieldElement.x, nArray2);
            SecP256R1Field.multiply(nArray, secP256R1FieldElement6.x, nArray);
            SecP256R1Field.multiply(nArray, secP256R1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat256.create();
        SecP256R1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SecP256R1Field.subtract(nArray, nArray3, nArray10);
        if (Nat256.isZero(nArray9)) {
            if (Nat256.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SecP256R1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat256.create();
        SecP256R1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SecP256R1Field.multiply(nArray11, nArray2, nArray13);
        SecP256R1Field.negate(nArray12, nArray12);
        Nat256.mul(nArray, nArray12, nArray5);
        int n = Nat256.addBothTo(nArray13, nArray13, nArray12);
        SecP256R1Field.reduce32(n, nArray12);
        SecP256R1FieldElement secP256R1FieldElement7 = new SecP256R1FieldElement(nArray8);
        SecP256R1Field.square(nArray10, secP256R1FieldElement7.x);
        SecP256R1Field.subtract(secP256R1FieldElement7.x, nArray12, secP256R1FieldElement7.x);
        SecP256R1FieldElement secP256R1FieldElement8 = new SecP256R1FieldElement(nArray12);
        SecP256R1Field.subtract(nArray13, secP256R1FieldElement7.x, secP256R1FieldElement8.x);
        SecP256R1Field.multiplyAddToExt(secP256R1FieldElement8.x, nArray10, nArray5);
        SecP256R1Field.reduce(nArray5, secP256R1FieldElement8.x);
        SecP256R1FieldElement secP256R1FieldElement9 = new SecP256R1FieldElement(nArray9);
        if (!bl) {
            SecP256R1Field.multiply(secP256R1FieldElement9.x, secP256R1FieldElement5.x, secP256R1FieldElement9.x);
        }
        if (!bl2) {
            SecP256R1Field.multiply(secP256R1FieldElement9.x, secP256R1FieldElement6.x, secP256R1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{secP256R1FieldElement9};
        return new SecP256R1Point(eCCurve, secP256R1FieldElement7, secP256R1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement)this.y;
        if (secP256R1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement)this.x;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement)this.zs[0];
        int[] nArray = Nat256.create();
        int[] nArray2 = Nat256.create();
        int[] nArray3 = Nat256.create();
        SecP256R1Field.square(secP256R1FieldElement.x, nArray3);
        int[] nArray4 = Nat256.create();
        SecP256R1Field.square(nArray3, nArray4);
        boolean bl = secP256R1FieldElement3.isOne();
        int[] nArray5 = secP256R1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SecP256R1Field.square(secP256R1FieldElement3.x, nArray5);
        }
        SecP256R1Field.subtract(secP256R1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SecP256R1Field.add(secP256R1FieldElement2.x, nArray5, nArray6);
        SecP256R1Field.multiply(nArray6, nArray, nArray6);
        int n = Nat256.addBothTo(nArray6, nArray6, nArray6);
        SecP256R1Field.reduce32(n, nArray6);
        int[] nArray7 = nArray3;
        SecP256R1Field.multiply(nArray3, secP256R1FieldElement2.x, nArray7);
        n = Nat.shiftUpBits(8, nArray7, 2, 0);
        SecP256R1Field.reduce32(n, nArray7);
        n = Nat.shiftUpBits(8, nArray4, 3, 0, nArray);
        SecP256R1Field.reduce32(n, nArray);
        SecP256R1FieldElement secP256R1FieldElement4 = new SecP256R1FieldElement(nArray4);
        SecP256R1Field.square(nArray6, secP256R1FieldElement4.x);
        SecP256R1Field.subtract(secP256R1FieldElement4.x, nArray7, secP256R1FieldElement4.x);
        SecP256R1Field.subtract(secP256R1FieldElement4.x, nArray7, secP256R1FieldElement4.x);
        SecP256R1FieldElement secP256R1FieldElement5 = new SecP256R1FieldElement(nArray7);
        SecP256R1Field.subtract(nArray7, secP256R1FieldElement4.x, secP256R1FieldElement5.x);
        SecP256R1Field.multiply(secP256R1FieldElement5.x, nArray6, secP256R1FieldElement5.x);
        SecP256R1Field.subtract(secP256R1FieldElement5.x, nArray, secP256R1FieldElement5.x);
        SecP256R1FieldElement secP256R1FieldElement6 = new SecP256R1FieldElement(nArray6);
        SecP256R1Field.twice(secP256R1FieldElement.x, secP256R1FieldElement6.x);
        if (!bl) {
            SecP256R1Field.multiply(secP256R1FieldElement6.x, secP256R1FieldElement3.x, secP256R1FieldElement6.x);
        }
        return new SecP256R1Point(eCCurve, secP256R1FieldElement4, secP256R1FieldElement5, new ECFieldElement[]{secP256R1FieldElement6}, this.withCompression);
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
        return new SecP256R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

