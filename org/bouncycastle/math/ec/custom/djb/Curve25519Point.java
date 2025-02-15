/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.djb;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.djb.Curve25519Field;
import org.bouncycastle.math.ec.custom.djb.Curve25519FieldElement;
import org.bouncycastle.math.raw.Nat256;

public class Curve25519Point
extends ECPoint.AbstractFp {
    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new Curve25519Point(null, this.getAffineXCoord(), this.getAffineYCoord());
    }

    public ECFieldElement getZCoord(int n) {
        if (n == 1) {
            return this.getJacobianModifiedW();
        }
        return super.getZCoord(n);
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
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement)this.x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement)this.y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement)this.zs[0];
        Curve25519FieldElement curve25519FieldElement4 = (Curve25519FieldElement)eCPoint.getXCoord();
        Curve25519FieldElement curve25519FieldElement5 = (Curve25519FieldElement)eCPoint.getYCoord();
        Curve25519FieldElement curve25519FieldElement6 = (Curve25519FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat256.createExt();
        int[] nArray6 = Nat256.create();
        int[] nArray7 = Nat256.create();
        int[] nArray8 = Nat256.create();
        boolean bl = curve25519FieldElement3.isOne();
        if (bl) {
            nArray4 = curve25519FieldElement4.x;
            nArray3 = curve25519FieldElement5.x;
        } else {
            nArray3 = nArray7;
            Curve25519Field.square(curve25519FieldElement3.x, nArray3);
            nArray4 = nArray6;
            Curve25519Field.multiply(nArray3, curve25519FieldElement4.x, nArray4);
            Curve25519Field.multiply(nArray3, curve25519FieldElement3.x, nArray3);
            Curve25519Field.multiply(nArray3, curve25519FieldElement5.x, nArray3);
        }
        boolean bl2 = curve25519FieldElement6.isOne();
        if (bl2) {
            nArray2 = curve25519FieldElement.x;
            nArray = curve25519FieldElement2.x;
        } else {
            nArray = nArray8;
            Curve25519Field.square(curve25519FieldElement6.x, nArray);
            nArray2 = nArray5;
            Curve25519Field.multiply(nArray, curve25519FieldElement.x, nArray2);
            Curve25519Field.multiply(nArray, curve25519FieldElement6.x, nArray);
            Curve25519Field.multiply(nArray, curve25519FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat256.create();
        Curve25519Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        Curve25519Field.subtract(nArray, nArray3, nArray10);
        if (Nat256.isZero(nArray9)) {
            if (Nat256.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = Nat256.create();
        Curve25519Field.square(nArray9, nArray11);
        int[] nArray12 = Nat256.create();
        Curve25519Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        Curve25519Field.multiply(nArray11, nArray2, nArray13);
        Curve25519Field.negate(nArray12, nArray12);
        Nat256.mul(nArray, nArray12, nArray5);
        int n = Nat256.addBothTo(nArray13, nArray13, nArray12);
        Curve25519Field.reduce27(n, nArray12);
        Curve25519FieldElement curve25519FieldElement7 = new Curve25519FieldElement(nArray8);
        Curve25519Field.square(nArray10, curve25519FieldElement7.x);
        Curve25519Field.subtract(curve25519FieldElement7.x, nArray12, curve25519FieldElement7.x);
        Curve25519FieldElement curve25519FieldElement8 = new Curve25519FieldElement(nArray12);
        Curve25519Field.subtract(nArray13, curve25519FieldElement7.x, curve25519FieldElement8.x);
        Curve25519Field.multiplyAddToExt(curve25519FieldElement8.x, nArray10, nArray5);
        Curve25519Field.reduce(nArray5, curve25519FieldElement8.x);
        Curve25519FieldElement curve25519FieldElement9 = new Curve25519FieldElement(nArray9);
        if (!bl) {
            Curve25519Field.multiply(curve25519FieldElement9.x, curve25519FieldElement3.x, curve25519FieldElement9.x);
        }
        if (!bl2) {
            Curve25519Field.multiply(curve25519FieldElement9.x, curve25519FieldElement6.x, curve25519FieldElement9.x);
        }
        int[] nArray14 = bl && bl2 ? nArray11 : null;
        Curve25519FieldElement curve25519FieldElement10 = this.calculateJacobianModifiedW(curve25519FieldElement9, nArray14);
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{curve25519FieldElement9, curve25519FieldElement10};
        return new Curve25519Point(eCCurve, curve25519FieldElement7, curve25519FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        ECFieldElement eCFieldElement = this.y;
        if (eCFieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        return this.twiceJacobianModified(true);
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
        return this.twiceJacobianModified(false).add(eCPoint);
    }

    public ECPoint threeTimes() {
        if (this.isInfinity()) {
            return this;
        }
        ECFieldElement eCFieldElement = this.y;
        if (eCFieldElement.isZero()) {
            return this;
        }
        return this.twiceJacobianModified(false).add(this);
    }

    public ECPoint negate() {
        if (this.isInfinity()) {
            return this;
        }
        return new Curve25519Point(this.getCurve(), this.x, this.y.negate(), this.zs, this.withCompression);
    }

    protected Curve25519FieldElement calculateJacobianModifiedW(Curve25519FieldElement curve25519FieldElement, int[] nArray) {
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement)this.getCurve().getA();
        if (curve25519FieldElement.isOne()) {
            return curve25519FieldElement2;
        }
        Curve25519FieldElement curve25519FieldElement3 = new Curve25519FieldElement();
        if (nArray == null) {
            nArray = curve25519FieldElement3.x;
            Curve25519Field.square(curve25519FieldElement.x, nArray);
        }
        Curve25519Field.square(nArray, curve25519FieldElement3.x);
        Curve25519Field.multiply(curve25519FieldElement3.x, curve25519FieldElement2.x, curve25519FieldElement3.x);
        return curve25519FieldElement3;
    }

    protected Curve25519FieldElement getJacobianModifiedW() {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement)this.zs[1];
        if (curve25519FieldElement == null) {
            curve25519FieldElement = this.calculateJacobianModifiedW((Curve25519FieldElement)this.zs[0], null);
            this.zs[1] = curve25519FieldElement;
        }
        return curve25519FieldElement;
    }

    protected Curve25519Point twiceJacobianModified(boolean bl) {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement)this.x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement)this.y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement)this.zs[0];
        Curve25519FieldElement curve25519FieldElement4 = this.getJacobianModifiedW();
        int[] nArray = Nat256.create();
        Curve25519Field.square(curve25519FieldElement.x, nArray);
        int n = Nat256.addBothTo(nArray, nArray, nArray);
        Curve25519Field.reduce27(n += Nat256.addTo(curve25519FieldElement4.x, nArray), nArray);
        int[] nArray2 = Nat256.create();
        Curve25519Field.twice(curve25519FieldElement2.x, nArray2);
        int[] nArray3 = Nat256.create();
        Curve25519Field.multiply(nArray2, curve25519FieldElement2.x, nArray3);
        int[] nArray4 = Nat256.create();
        Curve25519Field.multiply(nArray3, curve25519FieldElement.x, nArray4);
        Curve25519Field.twice(nArray4, nArray4);
        int[] nArray5 = Nat256.create();
        Curve25519Field.square(nArray3, nArray5);
        Curve25519Field.twice(nArray5, nArray5);
        Curve25519FieldElement curve25519FieldElement5 = new Curve25519FieldElement(nArray3);
        Curve25519Field.square(nArray, curve25519FieldElement5.x);
        Curve25519Field.subtract(curve25519FieldElement5.x, nArray4, curve25519FieldElement5.x);
        Curve25519Field.subtract(curve25519FieldElement5.x, nArray4, curve25519FieldElement5.x);
        Curve25519FieldElement curve25519FieldElement6 = new Curve25519FieldElement(nArray4);
        Curve25519Field.subtract(nArray4, curve25519FieldElement5.x, curve25519FieldElement6.x);
        Curve25519Field.multiply(curve25519FieldElement6.x, nArray, curve25519FieldElement6.x);
        Curve25519Field.subtract(curve25519FieldElement6.x, nArray5, curve25519FieldElement6.x);
        Curve25519FieldElement curve25519FieldElement7 = new Curve25519FieldElement(nArray2);
        if (!Nat256.isOne(curve25519FieldElement3.x)) {
            Curve25519Field.multiply(curve25519FieldElement7.x, curve25519FieldElement3.x, curve25519FieldElement7.x);
        }
        Curve25519FieldElement curve25519FieldElement8 = null;
        if (bl) {
            curve25519FieldElement8 = new Curve25519FieldElement(nArray5);
            Curve25519Field.multiply(curve25519FieldElement8.x, curve25519FieldElement4.x, curve25519FieldElement8.x);
            Curve25519Field.twice(curve25519FieldElement8.x, curve25519FieldElement8.x);
        }
        return new Curve25519Point(this.getCurve(), curve25519FieldElement5, curve25519FieldElement6, new ECFieldElement[]{curve25519FieldElement7, curve25519FieldElement8}, this.withCompression);
    }
}

