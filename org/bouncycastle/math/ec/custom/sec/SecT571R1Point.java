/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecT571Field;
import org.bouncycastle.math.ec.custom.sec.SecT571FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT571R1Curve;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat576;

public class SecT571R1Point
extends ECPoint.AbstractF2m {
    public SecT571R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecT571R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecT571R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecT571R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
    }

    public ECFieldElement getYCoord() {
        ECFieldElement eCFieldElement = this.x;
        ECFieldElement eCFieldElement2 = this.y;
        if (this.isInfinity() || eCFieldElement.isZero()) {
            return eCFieldElement2;
        }
        ECFieldElement eCFieldElement3 = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
        ECFieldElement eCFieldElement4 = this.zs[0];
        if (!eCFieldElement4.isOne()) {
            eCFieldElement3 = eCFieldElement3.divide(eCFieldElement4);
        }
        return eCFieldElement3;
    }

    protected boolean getCompressionYTilde() {
        ECFieldElement eCFieldElement = this.getRawXCoord();
        if (eCFieldElement.isZero()) {
            return false;
        }
        ECFieldElement eCFieldElement2 = this.getRawYCoord();
        return eCFieldElement2.testBitZero() != eCFieldElement.testBitZero();
    }

    public ECPoint add(ECPoint eCPoint) {
        SecT571FieldElement secT571FieldElement;
        SecT571FieldElement secT571FieldElement2;
        SecT571FieldElement secT571FieldElement3;
        long[] lArray;
        long[] lArray2;
        long[] lArray3;
        long[] lArray4;
        long[] lArray5;
        long[] lArray6;
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecT571FieldElement secT571FieldElement4 = (SecT571FieldElement)this.x;
        SecT571FieldElement secT571FieldElement5 = (SecT571FieldElement)eCPoint.getRawXCoord();
        if (secT571FieldElement4.isZero()) {
            if (secT571FieldElement5.isZero()) {
                return eCCurve.getInfinity();
            }
            return eCPoint.add(this);
        }
        SecT571FieldElement secT571FieldElement6 = (SecT571FieldElement)this.y;
        SecT571FieldElement secT571FieldElement7 = (SecT571FieldElement)this.zs[0];
        SecT571FieldElement secT571FieldElement8 = (SecT571FieldElement)eCPoint.getRawYCoord();
        SecT571FieldElement secT571FieldElement9 = (SecT571FieldElement)eCPoint.getZCoord(0);
        long[] lArray7 = Nat576.create64();
        long[] lArray8 = Nat576.create64();
        long[] lArray9 = Nat576.create64();
        long[] lArray10 = Nat576.create64();
        long[] lArray11 = lArray6 = secT571FieldElement7.isOne() ? null : SecT571Field.precompMultiplicand(secT571FieldElement7.x);
        if (lArray6 == null) {
            lArray5 = secT571FieldElement5.x;
            lArray4 = secT571FieldElement8.x;
        } else {
            lArray5 = lArray8;
            SecT571Field.multiplyPrecomp(secT571FieldElement5.x, lArray6, lArray8);
            lArray4 = lArray10;
            SecT571Field.multiplyPrecomp(secT571FieldElement8.x, lArray6, lArray10);
        }
        long[] lArray12 = lArray3 = secT571FieldElement9.isOne() ? null : SecT571Field.precompMultiplicand(secT571FieldElement9.x);
        if (lArray3 == null) {
            lArray2 = secT571FieldElement4.x;
            lArray = secT571FieldElement6.x;
        } else {
            lArray2 = lArray7;
            SecT571Field.multiplyPrecomp(secT571FieldElement4.x, lArray3, lArray7);
            lArray = lArray9;
            SecT571Field.multiplyPrecomp(secT571FieldElement6.x, lArray3, lArray9);
        }
        long[] lArray13 = lArray9;
        SecT571Field.add(lArray, lArray4, lArray13);
        long[] lArray14 = lArray10;
        SecT571Field.add(lArray2, lArray5, lArray14);
        if (Nat576.isZero64(lArray14)) {
            if (Nat576.isZero64(lArray13)) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        if (secT571FieldElement5.isZero()) {
            SecT571FieldElement secT571FieldElement10;
            ECPoint eCPoint2 = this.normalize();
            secT571FieldElement4 = (SecT571FieldElement)eCPoint2.getXCoord();
            ECFieldElement eCFieldElement = eCPoint2.getYCoord();
            ECFieldElement eCFieldElement2 = eCFieldElement.add(secT571FieldElement10 = secT571FieldElement8).divide(secT571FieldElement4);
            secT571FieldElement3 = (SecT571FieldElement)eCFieldElement2.square().add(eCFieldElement2).add(secT571FieldElement4).addOne();
            if (secT571FieldElement3.isZero()) {
                return new SecT571R1Point(eCCurve, (ECFieldElement)secT571FieldElement3, (ECFieldElement)SecT571R1Curve.SecT571R1_B_SQRT, this.withCompression);
            }
            ECFieldElement eCFieldElement3 = eCFieldElement2.multiply(secT571FieldElement4.add(secT571FieldElement3)).add(secT571FieldElement3).add(eCFieldElement);
            secT571FieldElement2 = (SecT571FieldElement)eCFieldElement3.divide(secT571FieldElement3).add(secT571FieldElement3);
            secT571FieldElement = (SecT571FieldElement)eCCurve.fromBigInteger(ECConstants.ONE);
        } else {
            SecT571Field.square(lArray14, lArray14);
            long[] lArray15 = SecT571Field.precompMultiplicand(lArray13);
            long[] lArray16 = lArray7;
            long[] lArray17 = lArray8;
            SecT571Field.multiplyPrecomp(lArray2, lArray15, lArray16);
            SecT571Field.multiplyPrecomp(lArray5, lArray15, lArray17);
            secT571FieldElement3 = new SecT571FieldElement(lArray7);
            SecT571Field.multiply(lArray16, lArray17, secT571FieldElement3.x);
            if (secT571FieldElement3.isZero()) {
                return new SecT571R1Point(eCCurve, (ECFieldElement)secT571FieldElement3, (ECFieldElement)SecT571R1Curve.SecT571R1_B_SQRT, this.withCompression);
            }
            secT571FieldElement = new SecT571FieldElement(lArray9);
            SecT571Field.multiplyPrecomp(lArray14, lArray15, secT571FieldElement.x);
            if (lArray3 != null) {
                SecT571Field.multiplyPrecomp(secT571FieldElement.x, lArray3, secT571FieldElement.x);
            }
            long[] lArray18 = Nat576.createExt64();
            SecT571Field.add(lArray17, lArray14, lArray10);
            SecT571Field.squareAddToExt(lArray10, lArray18);
            SecT571Field.add(secT571FieldElement6.x, secT571FieldElement7.x, lArray10);
            SecT571Field.multiplyAddToExt(lArray10, secT571FieldElement.x, lArray18);
            secT571FieldElement2 = new SecT571FieldElement(lArray10);
            SecT571Field.reduce(lArray18, secT571FieldElement2.x);
            if (lArray6 != null) {
                SecT571Field.multiplyPrecomp(secT571FieldElement.x, lArray6, secT571FieldElement.x);
            }
        }
        return new SecT571R1Point(eCCurve, secT571FieldElement3, secT571FieldElement2, new ECFieldElement[]{secT571FieldElement}, this.withCompression);
    }

    public ECPoint twice() {
        long[] lArray;
        long[] lArray2;
        long[] lArray3;
        long[] lArray4;
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        SecT571FieldElement secT571FieldElement = (SecT571FieldElement)this.x;
        if (secT571FieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        SecT571FieldElement secT571FieldElement2 = (SecT571FieldElement)this.y;
        SecT571FieldElement secT571FieldElement3 = (SecT571FieldElement)this.zs[0];
        long[] lArray5 = Nat576.create64();
        long[] lArray6 = Nat576.create64();
        long[] lArray7 = lArray4 = secT571FieldElement3.isOne() ? null : SecT571Field.precompMultiplicand(secT571FieldElement3.x);
        if (lArray4 == null) {
            lArray3 = secT571FieldElement2.x;
            lArray2 = secT571FieldElement3.x;
        } else {
            lArray3 = lArray5;
            SecT571Field.multiplyPrecomp(secT571FieldElement2.x, lArray4, lArray5);
            lArray2 = lArray6;
            SecT571Field.square(secT571FieldElement3.x, lArray6);
        }
        long[] lArray8 = Nat576.create64();
        SecT571Field.square(secT571FieldElement2.x, lArray8);
        SecT571Field.addBothTo(lArray3, lArray2, lArray8);
        if (Nat576.isZero64(lArray8)) {
            return new SecT571R1Point(eCCurve, (ECFieldElement)new SecT571FieldElement(lArray8), (ECFieldElement)SecT571R1Curve.SecT571R1_B_SQRT, this.withCompression);
        }
        long[] lArray9 = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(lArray8, lArray3, lArray9);
        SecT571FieldElement secT571FieldElement4 = new SecT571FieldElement(lArray5);
        SecT571Field.square(lArray8, secT571FieldElement4.x);
        SecT571FieldElement secT571FieldElement5 = new SecT571FieldElement(lArray8);
        if (lArray4 != null) {
            SecT571Field.multiply(secT571FieldElement5.x, lArray2, secT571FieldElement5.x);
        }
        if (lArray4 == null) {
            lArray = secT571FieldElement.x;
        } else {
            lArray = lArray6;
            SecT571Field.multiplyPrecomp(secT571FieldElement.x, lArray4, lArray6);
        }
        SecT571Field.squareAddToExt(lArray, lArray9);
        SecT571Field.reduce(lArray9, lArray6);
        SecT571Field.addBothTo(secT571FieldElement4.x, secT571FieldElement5.x, lArray6);
        SecT571FieldElement secT571FieldElement6 = new SecT571FieldElement(lArray6);
        return new SecT571R1Point(eCCurve, secT571FieldElement4, secT571FieldElement6, new ECFieldElement[]{secT571FieldElement5}, this.withCompression);
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this.twice();
        }
        ECCurve eCCurve = this.getCurve();
        SecT571FieldElement secT571FieldElement = (SecT571FieldElement)this.x;
        if (secT571FieldElement.isZero()) {
            return eCPoint;
        }
        SecT571FieldElement secT571FieldElement2 = (SecT571FieldElement)eCPoint.getRawXCoord();
        SecT571FieldElement secT571FieldElement3 = (SecT571FieldElement)eCPoint.getZCoord(0);
        if (secT571FieldElement2.isZero() || !secT571FieldElement3.isOne()) {
            return this.twice().add(eCPoint);
        }
        SecT571FieldElement secT571FieldElement4 = (SecT571FieldElement)this.y;
        SecT571FieldElement secT571FieldElement5 = (SecT571FieldElement)this.zs[0];
        SecT571FieldElement secT571FieldElement6 = (SecT571FieldElement)eCPoint.getRawYCoord();
        long[] lArray = Nat576.create64();
        long[] lArray2 = Nat576.create64();
        long[] lArray3 = Nat576.create64();
        long[] lArray4 = Nat576.create64();
        long[] lArray5 = lArray;
        SecT571Field.square(secT571FieldElement.x, lArray5);
        long[] lArray6 = lArray2;
        SecT571Field.square(secT571FieldElement4.x, lArray6);
        long[] lArray7 = lArray3;
        SecT571Field.square(secT571FieldElement5.x, lArray7);
        long[] lArray8 = lArray4;
        SecT571Field.multiply(secT571FieldElement4.x, secT571FieldElement5.x, lArray8);
        long[] lArray9 = lArray8;
        SecT571Field.addBothTo(lArray7, lArray6, lArray9);
        long[] lArray10 = SecT571Field.precompMultiplicand(lArray7);
        long[] lArray11 = lArray3;
        SecT571Field.multiplyPrecomp(secT571FieldElement6.x, lArray10, lArray11);
        SecT571Field.add(lArray11, lArray6, lArray11);
        long[] lArray12 = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(lArray11, lArray9, lArray12);
        SecT571Field.multiplyPrecompAddToExt(lArray5, lArray10, lArray12);
        SecT571Field.reduce(lArray12, lArray11);
        long[] lArray13 = lArray;
        SecT571Field.multiplyPrecomp(secT571FieldElement2.x, lArray10, lArray13);
        long[] lArray14 = lArray2;
        SecT571Field.add(lArray13, lArray9, lArray14);
        SecT571Field.square(lArray14, lArray14);
        if (Nat576.isZero64(lArray14)) {
            if (Nat576.isZero64(lArray11)) {
                return eCPoint.twice();
            }
            return eCCurve.getInfinity();
        }
        if (Nat576.isZero64(lArray11)) {
            return new SecT571R1Point(eCCurve, (ECFieldElement)new SecT571FieldElement(lArray11), (ECFieldElement)SecT571R1Curve.SecT571R1_B_SQRT, this.withCompression);
        }
        SecT571FieldElement secT571FieldElement7 = new SecT571FieldElement();
        SecT571Field.square(lArray11, secT571FieldElement7.x);
        SecT571Field.multiply(secT571FieldElement7.x, lArray13, secT571FieldElement7.x);
        SecT571FieldElement secT571FieldElement8 = new SecT571FieldElement(lArray);
        SecT571Field.multiply(lArray11, lArray14, secT571FieldElement8.x);
        SecT571Field.multiplyPrecomp(secT571FieldElement8.x, lArray10, secT571FieldElement8.x);
        SecT571FieldElement secT571FieldElement9 = new SecT571FieldElement(lArray2);
        SecT571Field.add(lArray11, lArray14, secT571FieldElement9.x);
        SecT571Field.square(secT571FieldElement9.x, secT571FieldElement9.x);
        Nat.zero64(18, lArray12);
        SecT571Field.multiplyAddToExt(secT571FieldElement9.x, lArray9, lArray12);
        SecT571Field.addOne(secT571FieldElement6.x, lArray4);
        SecT571Field.multiplyAddToExt(lArray4, secT571FieldElement8.x, lArray12);
        SecT571Field.reduce(lArray12, secT571FieldElement9.x);
        return new SecT571R1Point(eCCurve, secT571FieldElement7, secT571FieldElement9, new ECFieldElement[]{secT571FieldElement8}, this.withCompression);
    }

    public ECPoint negate() {
        if (this.isInfinity()) {
            return this;
        }
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return this;
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        return new SecT571R1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
    }
}

