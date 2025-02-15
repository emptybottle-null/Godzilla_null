/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.gm;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Field;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1FieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SM2P256V1Point
extends ECPoint.AbstractFp {
    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SM2P256V1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement)this.x;
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement)this.y;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement)eCPoint.getXCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement4 = (SM2P256V1FieldElement)eCPoint.getYCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement5 = (SM2P256V1FieldElement)this.zs[0];
        SM2P256V1FieldElement sM2P256V1FieldElement6 = (SM2P256V1FieldElement)eCPoint.getZCoord(0);
        int[] nArray5 = Nat256.createExt();
        int[] nArray6 = Nat256.create();
        int[] nArray7 = Nat256.create();
        int[] nArray8 = Nat256.create();
        boolean bl = sM2P256V1FieldElement5.isOne();
        if (bl) {
            nArray4 = sM2P256V1FieldElement3.x;
            nArray3 = sM2P256V1FieldElement4.x;
        } else {
            nArray3 = nArray7;
            SM2P256V1Field.square(sM2P256V1FieldElement5.x, nArray3);
            nArray4 = nArray6;
            SM2P256V1Field.multiply(nArray3, sM2P256V1FieldElement3.x, nArray4);
            SM2P256V1Field.multiply(nArray3, sM2P256V1FieldElement5.x, nArray3);
            SM2P256V1Field.multiply(nArray3, sM2P256V1FieldElement4.x, nArray3);
        }
        boolean bl2 = sM2P256V1FieldElement6.isOne();
        if (bl2) {
            nArray2 = sM2P256V1FieldElement.x;
            nArray = sM2P256V1FieldElement2.x;
        } else {
            nArray = nArray8;
            SM2P256V1Field.square(sM2P256V1FieldElement6.x, nArray);
            nArray2 = nArray5;
            SM2P256V1Field.multiply(nArray, sM2P256V1FieldElement.x, nArray2);
            SM2P256V1Field.multiply(nArray, sM2P256V1FieldElement6.x, nArray);
            SM2P256V1Field.multiply(nArray, sM2P256V1FieldElement2.x, nArray);
        }
        int[] nArray9 = Nat256.create();
        SM2P256V1Field.subtract(nArray2, nArray4, nArray9);
        int[] nArray10 = nArray6;
        SM2P256V1Field.subtract(nArray, nArray3, nArray10);
        if (Nat256.isZero(nArray9)) {
            if (Nat256.isZero(nArray10)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        int[] nArray11 = nArray7;
        SM2P256V1Field.square(nArray9, nArray11);
        int[] nArray12 = Nat256.create();
        SM2P256V1Field.multiply(nArray11, nArray9, nArray12);
        int[] nArray13 = nArray7;
        SM2P256V1Field.multiply(nArray11, nArray2, nArray13);
        SM2P256V1Field.negate(nArray12, nArray12);
        Nat256.mul(nArray, nArray12, nArray5);
        int n = Nat256.addBothTo(nArray13, nArray13, nArray12);
        SM2P256V1Field.reduce32(n, nArray12);
        SM2P256V1FieldElement sM2P256V1FieldElement7 = new SM2P256V1FieldElement(nArray8);
        SM2P256V1Field.square(nArray10, sM2P256V1FieldElement7.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement7.x, nArray12, sM2P256V1FieldElement7.x);
        SM2P256V1FieldElement sM2P256V1FieldElement8 = new SM2P256V1FieldElement(nArray12);
        SM2P256V1Field.subtract(nArray13, sM2P256V1FieldElement7.x, sM2P256V1FieldElement8.x);
        SM2P256V1Field.multiplyAddToExt(sM2P256V1FieldElement8.x, nArray10, nArray5);
        SM2P256V1Field.reduce(nArray5, sM2P256V1FieldElement8.x);
        SM2P256V1FieldElement sM2P256V1FieldElement9 = new SM2P256V1FieldElement(nArray9);
        if (!bl) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.x, sM2P256V1FieldElement5.x, sM2P256V1FieldElement9.x);
        }
        if (!bl2) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.x, sM2P256V1FieldElement6.x, sM2P256V1FieldElement9.x);
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[]{sM2P256V1FieldElement9};
        return new SM2P256V1Point(eCCurve, sM2P256V1FieldElement7, sM2P256V1FieldElement8, eCFieldElementArray, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement)this.y;
        if (sM2P256V1FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement)this.x;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement)this.zs[0];
        int[] nArray = Nat256.create();
        int[] nArray2 = Nat256.create();
        int[] nArray3 = Nat256.create();
        SM2P256V1Field.square(sM2P256V1FieldElement.x, nArray3);
        int[] nArray4 = Nat256.create();
        SM2P256V1Field.square(nArray3, nArray4);
        boolean bl = sM2P256V1FieldElement3.isOne();
        int[] nArray5 = sM2P256V1FieldElement3.x;
        if (!bl) {
            nArray5 = nArray2;
            SM2P256V1Field.square(sM2P256V1FieldElement3.x, nArray5);
        }
        SM2P256V1Field.subtract(sM2P256V1FieldElement2.x, nArray5, nArray);
        int[] nArray6 = nArray2;
        SM2P256V1Field.add(sM2P256V1FieldElement2.x, nArray5, nArray6);
        SM2P256V1Field.multiply(nArray6, nArray, nArray6);
        int n = Nat256.addBothTo(nArray6, nArray6, nArray6);
        SM2P256V1Field.reduce32(n, nArray6);
        int[] nArray7 = nArray3;
        SM2P256V1Field.multiply(nArray3, sM2P256V1FieldElement2.x, nArray7);
        n = Nat.shiftUpBits(8, nArray7, 2, 0);
        SM2P256V1Field.reduce32(n, nArray7);
        n = Nat.shiftUpBits(8, nArray4, 3, 0, nArray);
        SM2P256V1Field.reduce32(n, nArray);
        SM2P256V1FieldElement sM2P256V1FieldElement4 = new SM2P256V1FieldElement(nArray4);
        SM2P256V1Field.square(nArray6, sM2P256V1FieldElement4.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.x, nArray7, sM2P256V1FieldElement4.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.x, nArray7, sM2P256V1FieldElement4.x);
        SM2P256V1FieldElement sM2P256V1FieldElement5 = new SM2P256V1FieldElement(nArray7);
        SM2P256V1Field.subtract(nArray7, sM2P256V1FieldElement4.x, sM2P256V1FieldElement5.x);
        SM2P256V1Field.multiply(sM2P256V1FieldElement5.x, nArray6, sM2P256V1FieldElement5.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement5.x, nArray, sM2P256V1FieldElement5.x);
        SM2P256V1FieldElement sM2P256V1FieldElement6 = new SM2P256V1FieldElement(nArray6);
        SM2P256V1Field.twice(sM2P256V1FieldElement.x, sM2P256V1FieldElement6.x);
        if (!bl) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement6.x, sM2P256V1FieldElement3.x, sM2P256V1FieldElement6.x);
        }
        return new SM2P256V1Point(eCCurve, sM2P256V1FieldElement4, sM2P256V1FieldElement5, new ECFieldElement[]{sM2P256V1FieldElement6}, this.withCompression);
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
        return new SM2P256V1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}

