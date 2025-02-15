/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;

public class SecT233R1Point
extends ECPoint.AbstractF2m {
    public SecT233R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecT233R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if (eCFieldElement == null != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = bl;
    }

    SecT233R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        this.withCompression = bl;
    }

    protected ECPoint detach() {
        return new SecT233R1Point(null, this.getAffineXCoord(), this.getAffineYCoord());
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
        ECFieldElement eCFieldElement;
        ECFieldElement eCFieldElement2;
        ECFieldElement eCFieldElement3;
        if (this.isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        ECCurve eCCurve = this.getCurve();
        ECFieldElement eCFieldElement4 = this.x;
        ECFieldElement eCFieldElement5 = eCPoint.getRawXCoord();
        if (eCFieldElement4.isZero()) {
            if (eCFieldElement5.isZero()) {
                return eCCurve.getInfinity();
            }
            return eCPoint.add(this);
        }
        ECFieldElement eCFieldElement6 = this.y;
        ECFieldElement eCFieldElement7 = this.zs[0];
        ECFieldElement eCFieldElement8 = eCPoint.getRawYCoord();
        ECFieldElement eCFieldElement9 = eCPoint.getZCoord(0);
        boolean bl = eCFieldElement7.isOne();
        ECFieldElement eCFieldElement10 = eCFieldElement5;
        ECFieldElement eCFieldElement11 = eCFieldElement8;
        if (!bl) {
            eCFieldElement10 = eCFieldElement10.multiply(eCFieldElement7);
            eCFieldElement11 = eCFieldElement11.multiply(eCFieldElement7);
        }
        boolean bl2 = eCFieldElement9.isOne();
        ECFieldElement eCFieldElement12 = eCFieldElement4;
        ECFieldElement eCFieldElement13 = eCFieldElement6;
        if (!bl2) {
            eCFieldElement12 = eCFieldElement12.multiply(eCFieldElement9);
            eCFieldElement13 = eCFieldElement13.multiply(eCFieldElement9);
        }
        ECFieldElement eCFieldElement14 = eCFieldElement13.add(eCFieldElement11);
        ECFieldElement eCFieldElement15 = eCFieldElement12.add(eCFieldElement10);
        if (eCFieldElement15.isZero()) {
            if (eCFieldElement14.isZero()) {
                return this.twice();
            }
            return eCCurve.getInfinity();
        }
        if (eCFieldElement5.isZero()) {
            ECFieldElement eCFieldElement16;
            ECPoint eCPoint2 = this.normalize();
            eCFieldElement4 = eCPoint2.getXCoord();
            ECFieldElement eCFieldElement17 = eCPoint2.getYCoord();
            ECFieldElement eCFieldElement18 = eCFieldElement17.add(eCFieldElement16 = eCFieldElement8).divide(eCFieldElement4);
            eCFieldElement3 = eCFieldElement18.square().add(eCFieldElement18).add(eCFieldElement4).addOne();
            if (eCFieldElement3.isZero()) {
                return new SecT233R1Point(eCCurve, eCFieldElement3, eCCurve.getB().sqrt(), this.withCompression);
            }
            ECFieldElement eCFieldElement19 = eCFieldElement18.multiply(eCFieldElement4.add(eCFieldElement3)).add(eCFieldElement3).add(eCFieldElement17);
            eCFieldElement2 = eCFieldElement19.divide(eCFieldElement3).add(eCFieldElement3);
            eCFieldElement = eCCurve.fromBigInteger(ECConstants.ONE);
        } else {
            ECFieldElement eCFieldElement20;
            eCFieldElement15 = eCFieldElement15.square();
            ECFieldElement eCFieldElement21 = eCFieldElement14.multiply(eCFieldElement12);
            eCFieldElement3 = eCFieldElement21.multiply(eCFieldElement20 = eCFieldElement14.multiply(eCFieldElement10));
            if (eCFieldElement3.isZero()) {
                return new SecT233R1Point(eCCurve, eCFieldElement3, eCCurve.getB().sqrt(), this.withCompression);
            }
            ECFieldElement eCFieldElement22 = eCFieldElement14.multiply(eCFieldElement15);
            if (!bl2) {
                eCFieldElement22 = eCFieldElement22.multiply(eCFieldElement9);
            }
            eCFieldElement2 = eCFieldElement20.add(eCFieldElement15).squarePlusProduct(eCFieldElement22, eCFieldElement6.add(eCFieldElement7));
            eCFieldElement = eCFieldElement22;
            if (!bl) {
                eCFieldElement = eCFieldElement.multiply(eCFieldElement7);
            }
        }
        return new SecT233R1Point(eCCurve, eCFieldElement3, eCFieldElement2, new ECFieldElement[]{eCFieldElement}, this.withCompression);
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
        ECFieldElement eCFieldElement4 = bl ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement3);
        ECFieldElement eCFieldElement5 = bl ? eCFieldElement3 : eCFieldElement3.square();
        ECFieldElement eCFieldElement6 = eCFieldElement2.square().add(eCFieldElement4).add(eCFieldElement5);
        if (eCFieldElement6.isZero()) {
            return new SecT233R1Point(eCCurve, eCFieldElement6, eCCurve.getB().sqrt(), this.withCompression);
        }
        ECFieldElement eCFieldElement7 = eCFieldElement6.square();
        ECFieldElement eCFieldElement8 = bl ? eCFieldElement6 : eCFieldElement6.multiply(eCFieldElement5);
        ECFieldElement eCFieldElement9 = bl ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
        ECFieldElement eCFieldElement10 = eCFieldElement9.squarePlusProduct(eCFieldElement6, eCFieldElement4).add(eCFieldElement7).add(eCFieldElement8);
        return new SecT233R1Point(eCCurve, eCFieldElement7, eCFieldElement10, new ECFieldElement[]{eCFieldElement8}, this.withCompression);
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
        ECFieldElement eCFieldElement11 = eCFieldElement9.add(eCFieldElement8).add(eCFieldElement10);
        ECFieldElement eCFieldElement12 = eCFieldElement6.multiply(eCFieldElement9).add(eCFieldElement8).multiplyPlusProduct(eCFieldElement11, eCFieldElement7, eCFieldElement9);
        ECFieldElement eCFieldElement13 = eCFieldElement2.multiply(eCFieldElement9);
        ECFieldElement eCFieldElement14 = eCFieldElement13.add(eCFieldElement11).square();
        if (eCFieldElement14.isZero()) {
            if (eCFieldElement12.isZero()) {
                return eCPoint.twice();
            }
            return eCCurve.getInfinity();
        }
        if (eCFieldElement12.isZero()) {
            return new SecT233R1Point(eCCurve, eCFieldElement12, eCCurve.getB().sqrt(), this.withCompression);
        }
        ECFieldElement eCFieldElement15 = eCFieldElement12.square().multiply(eCFieldElement13);
        ECFieldElement eCFieldElement16 = eCFieldElement12.multiply(eCFieldElement14).multiply(eCFieldElement9);
        ECFieldElement eCFieldElement17 = eCFieldElement12.add(eCFieldElement14).square().multiplyPlusProduct(eCFieldElement11, eCFieldElement6.addOne(), eCFieldElement16);
        return new SecT233R1Point(eCCurve, eCFieldElement15, eCFieldElement17, new ECFieldElement[]{eCFieldElement16}, this.withCompression);
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
        return new SecT233R1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
    }
}

