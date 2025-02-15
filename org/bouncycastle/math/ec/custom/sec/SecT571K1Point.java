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
import org.bouncycastle.math.raw.Nat576;

public class SecT571K1Point
extends ECPoint.AbstractF2m {
    public SecT571K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecT571K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecT571K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecT571K1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
            secT571FieldElement3 = (SecT571FieldElement)eCFieldElement2.square().add(eCFieldElement2).add(secT571FieldElement4);
            if (secT571FieldElement3.isZero()) {
                return new SecT571K1Point(eCCurve, (ECFieldElement)secT571FieldElement3, eCCurve.getB(), this.withCompression);
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
                return new SecT571K1Point(eCCurve, (ECFieldElement)secT571FieldElement3, eCCurve.getB(), this.withCompression);
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
        return new SecT571K1Point(eCCurve, secT571FieldElement3, secT571FieldElement2, new ECFieldElement[]{secT571FieldElement}, this.withCompression);
    }

    public ECPoint twice() {
        if (this.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return eCCurve.getInfinity();
        }
        ECFieldElement eCFieldElement2 = this.y;
        ECFieldElement eCFieldElement3 = this.zs[0];
        boolean bl = eCFieldElement3.isOne();
        ECFieldElement eCFieldElement4 = bl ? eCFieldElement3 : eCFieldElement3.square();
        ECFieldElement eCFieldElement5 = bl ? eCFieldElement2.square().add(eCFieldElement2) : eCFieldElement2.add(eCFieldElement3).multiply(eCFieldElement2);
        if (eCFieldElement5.isZero()) {
            return new SecT571K1Point(eCCurve, eCFieldElement5, eCCurve.getB(), this.withCompression);
        }
        ECFieldElement eCFieldElement6 = eCFieldElement5.square();
        ECFieldElement eCFieldElement7 = bl ? eCFieldElement5 : eCFieldElement5.multiply(eCFieldElement4);
        ECFieldElement eCFieldElement8 = eCFieldElement2.add(eCFieldElement).square();
        ECFieldElement eCFieldElement9 = bl ? eCFieldElement3 : eCFieldElement4.square();
        ECFieldElement eCFieldElement10 = eCFieldElement8.add(eCFieldElement5).add(eCFieldElement4).multiply(eCFieldElement8).add(eCFieldElement9).add(eCFieldElement6).add(eCFieldElement7);
        return new SecT571K1Point(eCCurve, eCFieldElement6, eCFieldElement10, new ECFieldElement[]{eCFieldElement7}, this.withCompression);
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this.twice();
        }
        ECCurve eCCurve = this.getCurve();
        ECFieldElement eCFieldElement = this.x;
        if (eCFieldElement.isZero()) {
            return eCPoint;
        }
        ECFieldElement eCFieldElement2 = eCPoint.getRawXCoord();
        ECFieldElement eCFieldElement3 = eCPoint.getZCoord(0);
        if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
            return this.twice().add(eCPoint);
        }
        ECFieldElement eCFieldElement4 = this.y;
        ECFieldElement eCFieldElement5 = this.zs[0];
        ECFieldElement eCFieldElement6 = eCPoint.getRawYCoord();
        ECFieldElement eCFieldElement7 = eCFieldElement.square();
        ECFieldElement eCFieldElement8 = eCFieldElement4.square();
        ECFieldElement eCFieldElement9 = eCFieldElement5.square();
        ECFieldElement eCFieldElement10 = eCFieldElement4.multiply(eCFieldElement5);
        ECFieldElement eCFieldElement11 = eCFieldElement8.add(eCFieldElement10);
        ECFieldElement eCFieldElement12 = eCFieldElement6.addOne();
        ECFieldElement eCFieldElement13 = eCFieldElement12.multiply(eCFieldElement9).add(eCFieldElement8).multiplyPlusProduct(eCFieldElement11, eCFieldElement7, eCFieldElement9);
        ECFieldElement eCFieldElement14 = eCFieldElement2.multiply(eCFieldElement9);
        ECFieldElement eCFieldElement15 = eCFieldElement14.add(eCFieldElement11).square();
        if (eCFieldElement15.isZero()) {
            if (eCFieldElement13.isZero()) {
                return eCPoint.twice();
            }
            return eCCurve.getInfinity();
        }
        if (eCFieldElement13.isZero()) {
            return new SecT571K1Point(eCCurve, eCFieldElement13, eCCurve.getB(), this.withCompression);
        }
        ECFieldElement eCFieldElement16 = eCFieldElement13.square().multiply(eCFieldElement14);
        ECFieldElement eCFieldElement17 = eCFieldElement13.multiply(eCFieldElement15).multiply(eCFieldElement9);
        ECFieldElement eCFieldElement18 = eCFieldElement13.add(eCFieldElement15).square().multiplyPlusProduct(eCFieldElement11, eCFieldElement12, eCFieldElement17);
        return new SecT571K1Point(eCCurve, eCFieldElement16, eCFieldElement18, new ECFieldElement[]{eCFieldElement17}, this.withCompression);
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
        return new SecT571K1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
    }
}

