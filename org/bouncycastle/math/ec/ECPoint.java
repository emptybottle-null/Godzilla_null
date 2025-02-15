/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;

public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected ECFieldElement x;
    protected ECFieldElement y;
    protected ECFieldElement[] zs;
    protected boolean withCompression;
    protected Hashtable preCompTable = null;

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int n = null == eCCurve ? 0 : eCCurve.getCoordinateSystem();
        switch (n) {
            case 0: 
            case 5: {
                return EMPTY_ZS;
            }
        }
        ECFieldElement eCFieldElement = eCCurve.fromBigInteger(ECConstants.ONE);
        switch (n) {
            case 1: 
            case 2: 
            case 6: {
                return new ECFieldElement[]{eCFieldElement};
            }
            case 3: {
                return new ECFieldElement[]{eCFieldElement, eCFieldElement, eCFieldElement};
            }
            case 4: {
                return new ECFieldElement[]{eCFieldElement, eCCurve.getA()};
            }
        }
        throw new IllegalArgumentException("unknown coordinate system");
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, ECPoint.getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray) {
        this.curve = eCCurve;
        this.x = eCFieldElement;
        this.y = eCFieldElement2;
        this.zs = eCFieldElementArray;
    }

    protected boolean satisfiesCofactor() {
        BigInteger bigInteger = this.curve.getCofactor();
        return bigInteger == null || bigInteger.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, bigInteger).isInfinity();
    }

    protected abstract boolean satisfiesCurveEquation();

    public final ECPoint getDetachedPoint() {
        return this.normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    protected abstract ECPoint detach();

    protected int getCurveCoordinateSystem() {
        return null == this.curve ? 0 : this.curve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return this.normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return this.normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        this.checkNormalized();
        return this.getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        this.checkNormalized();
        return this.getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.x;
    }

    public ECFieldElement getYCoord() {
        return this.y;
    }

    public ECFieldElement getZCoord(int n) {
        return n < 0 || n >= this.zs.length ? null : this.zs[n];
    }

    public ECFieldElement[] getZCoords() {
        int n = this.zs.length;
        if (n == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArray = new ECFieldElement[n];
        System.arraycopy(this.zs, 0, eCFieldElementArray, 0, n);
        return eCFieldElementArray;
    }

    public final ECFieldElement getRawXCoord() {
        return this.x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.y;
    }

    protected final ECFieldElement[] getRawZCoords() {
        return this.zs;
    }

    protected void checkNormalized() {
        if (!this.isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int n = this.getCurveCoordinateSystem();
        return n == 0 || n == 5 || this.isInfinity() || this.zs[0].isOne();
    }

    public ECPoint normalize() {
        if (this.isInfinity()) {
            return this;
        }
        switch (this.getCurveCoordinateSystem()) {
            case 0: 
            case 5: {
                return this;
            }
        }
        ECFieldElement eCFieldElement = this.getZCoord(0);
        if (eCFieldElement.isOne()) {
            return this;
        }
        return this.normalize(eCFieldElement.invert());
    }

    ECPoint normalize(ECFieldElement eCFieldElement) {
        switch (this.getCurveCoordinateSystem()) {
            case 1: 
            case 6: {
                return this.createScaledPoint(eCFieldElement, eCFieldElement);
            }
            case 2: 
            case 3: 
            case 4: {
                ECFieldElement eCFieldElement2 = eCFieldElement.square();
                ECFieldElement eCFieldElement3 = eCFieldElement2.multiply(eCFieldElement);
                return this.createScaledPoint(eCFieldElement2, eCFieldElement3);
            }
        }
        throw new IllegalStateException("not a projective coordinate system");
    }

    protected ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.getCurve().createRawPoint(this.getRawXCoord().multiply(eCFieldElement), this.getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public boolean isInfinity() {
        return this.x == null || this.y == null || this.zs.length > 0 && this.zs[0].isZero();
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        if (this.isInfinity()) {
            return true;
        }
        ECCurve eCCurve = this.getCurve();
        if (eCCurve != null) {
            if (!this.satisfiesCurveEquation()) {
                return false;
            }
            if (!this.satisfiesCofactor()) {
                return false;
            }
        }
        return true;
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return this.isInfinity() ? this : this.getCurve().createRawPoint(this.getRawXCoord().multiply(eCFieldElement), this.getRawYCoord(), this.getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return this.isInfinity() ? this : this.getCurve().createRawPoint(this.getRawXCoord(), this.getRawYCoord().multiply(eCFieldElement), this.getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint eCPoint) {
        if (null == eCPoint) {
            return false;
        }
        ECCurve eCCurve = this.getCurve();
        ECCurve eCCurve2 = eCPoint.getCurve();
        boolean bl = null == eCCurve;
        boolean bl2 = null == eCCurve2;
        boolean bl3 = this.isInfinity();
        boolean bl4 = eCPoint.isInfinity();
        if (bl3 || bl4) {
            return bl3 && bl4 && (bl || bl2 || eCCurve.equals(eCCurve2));
        }
        ECPoint eCPoint2 = this;
        ECPoint eCPoint3 = eCPoint;
        if (!bl || !bl2) {
            if (bl) {
                eCPoint3 = eCPoint3.normalize();
            } else if (bl2) {
                eCPoint2 = eCPoint2.normalize();
            } else {
                if (!eCCurve.equals(eCCurve2)) {
                    return false;
                }
                ECPoint[] eCPointArray = new ECPoint[]{this, eCCurve.importPoint(eCPoint3)};
                eCCurve.normalizeAll(eCPointArray);
                eCPoint2 = eCPointArray[0];
                eCPoint3 = eCPointArray[1];
            }
        }
        return eCPoint2.getXCoord().equals(eCPoint3.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint3.getYCoord());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ECPoint)) {
            return false;
        }
        return this.equals((ECPoint)object);
    }

    public int hashCode() {
        int n;
        ECCurve eCCurve = this.getCurve();
        int n2 = n = null == eCCurve ? 0 : ~eCCurve.hashCode();
        if (!this.isInfinity()) {
            ECPoint eCPoint = this.normalize();
            n ^= eCPoint.getXCoord().hashCode() * 17;
            n ^= eCPoint.getYCoord().hashCode() * 257;
        }
        return n;
    }

    public String toString() {
        if (this.isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(this.getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(this.getRawYCoord());
        for (int i = 0; i < this.zs.length; ++i) {
            stringBuffer.append(',');
            stringBuffer.append(this.zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] getEncoded() {
        return this.getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean bl) {
        if (this.isInfinity()) {
            return new byte[1];
        }
        ECPoint eCPoint = this.normalize();
        byte[] byArray = eCPoint.getXCoord().getEncoded();
        if (bl) {
            byte[] byArray2 = new byte[byArray.length + 1];
            byArray2[0] = (byte)(eCPoint.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(byArray, 0, byArray2, 1, byArray.length);
            return byArray2;
        }
        byte[] byArray3 = eCPoint.getYCoord().getEncoded();
        byte[] byArray4 = new byte[byArray.length + byArray3.length + 1];
        byArray4[0] = 4;
        System.arraycopy(byArray, 0, byArray4, 1, byArray.length);
        System.arraycopy(byArray3, 0, byArray4, byArray.length + 1, byArray3.length);
        return byArray4;
    }

    protected abstract boolean getCompressionYTilde();

    public abstract ECPoint add(ECPoint var1);

    public abstract ECPoint negate();

    public abstract ECPoint subtract(ECPoint var1);

    public ECPoint timesPow2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPoint = this;
        while (--n >= 0) {
            eCPoint = eCPoint.twice();
        }
        return eCPoint;
    }

    public abstract ECPoint twice();

    public ECPoint twicePlus(ECPoint eCPoint) {
        return this.twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return this.twicePlus(this);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return this.getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public static abstract class AbstractF2m
    extends ECPoint {
        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        }

        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement;
            ECCurve eCCurve = this.getCurve();
            ECFieldElement eCFieldElement2 = this.x;
            ECFieldElement eCFieldElement3 = eCCurve.getA();
            ECFieldElement eCFieldElement4 = eCCurve.getB();
            int n = eCCurve.getCoordinateSystem();
            if (n == 6) {
                ECFieldElement eCFieldElement5;
                ECFieldElement eCFieldElement6;
                ECFieldElement eCFieldElement7 = this.zs[0];
                boolean bl = eCFieldElement7.isOne();
                if (eCFieldElement2.isZero()) {
                    ECFieldElement eCFieldElement8 = this.y;
                    ECFieldElement eCFieldElement9 = eCFieldElement8.square();
                    ECFieldElement eCFieldElement10 = eCFieldElement4;
                    if (!bl) {
                        eCFieldElement10 = eCFieldElement10.multiply(eCFieldElement7.square());
                    }
                    return eCFieldElement9.equals(eCFieldElement10);
                }
                ECFieldElement eCFieldElement11 = this.y;
                ECFieldElement eCFieldElement12 = eCFieldElement2.square();
                if (bl) {
                    eCFieldElement6 = eCFieldElement11.square().add(eCFieldElement11).add(eCFieldElement3);
                    eCFieldElement5 = eCFieldElement12.square().add(eCFieldElement4);
                } else {
                    ECFieldElement eCFieldElement13 = eCFieldElement7.square();
                    ECFieldElement eCFieldElement14 = eCFieldElement13.square();
                    eCFieldElement6 = eCFieldElement11.add(eCFieldElement7).multiplyPlusProduct(eCFieldElement11, eCFieldElement3, eCFieldElement13);
                    eCFieldElement5 = eCFieldElement12.squarePlusProduct(eCFieldElement4, eCFieldElement14);
                }
                eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement12);
                return eCFieldElement6.equals(eCFieldElement5);
            }
            ECFieldElement eCFieldElement15 = this.y;
            ECFieldElement eCFieldElement16 = eCFieldElement15.add(eCFieldElement2).multiply(eCFieldElement15);
            switch (n) {
                case 0: {
                    break;
                }
                case 1: {
                    eCFieldElement = this.zs[0];
                    if (eCFieldElement.isOne()) break;
                    ECFieldElement eCFieldElement17 = eCFieldElement.square();
                    ECFieldElement eCFieldElement18 = eCFieldElement.multiply(eCFieldElement17);
                    eCFieldElement16 = eCFieldElement16.multiply(eCFieldElement);
                    eCFieldElement3 = eCFieldElement3.multiply(eCFieldElement);
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement18);
                    break;
                }
                default: {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            eCFieldElement = eCFieldElement2.add(eCFieldElement3).multiply(eCFieldElement2.square()).add(eCFieldElement4);
            return eCFieldElement16.equals(eCFieldElement);
        }

        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (this.isInfinity()) {
                return this;
            }
            int n = this.getCurveCoordinateSystem();
            switch (n) {
                case 5: {
                    ECFieldElement eCFieldElement2 = this.getRawXCoord();
                    ECFieldElement eCFieldElement3 = this.getRawYCoord();
                    ECFieldElement eCFieldElement4 = eCFieldElement2.multiply(eCFieldElement);
                    ECFieldElement eCFieldElement5 = eCFieldElement3.add(eCFieldElement2).divide(eCFieldElement).add(eCFieldElement4);
                    return this.getCurve().createRawPoint(eCFieldElement2, eCFieldElement5, this.getRawZCoords(), this.withCompression);
                }
                case 6: {
                    ECFieldElement eCFieldElement6 = this.getRawXCoord();
                    ECFieldElement eCFieldElement7 = this.getRawYCoord();
                    ECFieldElement eCFieldElement8 = this.getRawZCoords()[0];
                    ECFieldElement eCFieldElement9 = eCFieldElement6.multiply(eCFieldElement.square());
                    ECFieldElement eCFieldElement10 = eCFieldElement7.add(eCFieldElement6).add(eCFieldElement9);
                    ECFieldElement eCFieldElement11 = eCFieldElement8.multiply(eCFieldElement);
                    return this.getCurve().createRawPoint(eCFieldElement9, eCFieldElement10, new ECFieldElement[]{eCFieldElement11}, this.withCompression);
                }
            }
            return super.scaleX(eCFieldElement);
        }

        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (this.isInfinity()) {
                return this;
            }
            int n = this.getCurveCoordinateSystem();
            switch (n) {
                case 5: 
                case 6: {
                    ECFieldElement eCFieldElement2 = this.getRawXCoord();
                    ECFieldElement eCFieldElement3 = this.getRawYCoord();
                    ECFieldElement eCFieldElement4 = eCFieldElement3.add(eCFieldElement2).multiply(eCFieldElement).add(eCFieldElement2);
                    return this.getCurve().createRawPoint(eCFieldElement2, eCFieldElement4, this.getRawZCoords(), this.withCompression);
                }
            }
            return super.scaleY(eCFieldElement);
        }

        public ECPoint subtract(ECPoint eCPoint) {
            if (eCPoint.isInfinity()) {
                return this;
            }
            return this.add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            if (this.isInfinity()) {
                return this;
            }
            ECCurve eCCurve = this.getCurve();
            int n = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            switch (n) {
                case 0: 
                case 5: {
                    ECFieldElement eCFieldElement2 = this.y;
                    return (AbstractF2m)eCCurve.createRawPoint(eCFieldElement.square(), eCFieldElement2.square(), this.withCompression);
                }
                case 1: 
                case 6: {
                    ECFieldElement eCFieldElement3 = this.y;
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    return (AbstractF2m)eCCurve.createRawPoint(eCFieldElement.square(), eCFieldElement3.square(), new ECFieldElement[]{eCFieldElement4.square()}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        public AbstractF2m tauPow(int n) {
            if (this.isInfinity()) {
                return this;
            }
            ECCurve eCCurve = this.getCurve();
            int n2 = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            switch (n2) {
                case 0: 
                case 5: {
                    ECFieldElement eCFieldElement2 = this.y;
                    return (AbstractF2m)eCCurve.createRawPoint(eCFieldElement.squarePow(n), eCFieldElement2.squarePow(n), this.withCompression);
                }
                case 1: 
                case 6: {
                    ECFieldElement eCFieldElement3 = this.y;
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    return (AbstractF2m)eCCurve.createRawPoint(eCFieldElement.squarePow(n), eCFieldElement3.squarePow(n), new ECFieldElement[]{eCFieldElement4.squarePow(n)}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    public static abstract class AbstractFp
    extends ECPoint {
        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
        }

        protected boolean getCompressionYTilde() {
            return this.getAffineYCoord().testBitZero();
        }

        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2 = this.x;
            ECFieldElement eCFieldElement3 = this.y;
            ECFieldElement eCFieldElement4 = this.curve.getA();
            ECFieldElement eCFieldElement5 = this.curve.getB();
            ECFieldElement eCFieldElement6 = eCFieldElement3.square();
            switch (this.getCurveCoordinateSystem()) {
                case 0: {
                    break;
                }
                case 1: {
                    eCFieldElement = this.zs[0];
                    if (eCFieldElement.isOne()) break;
                    ECFieldElement eCFieldElement7 = eCFieldElement.square();
                    ECFieldElement eCFieldElement8 = eCFieldElement.multiply(eCFieldElement7);
                    eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement);
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement7);
                    eCFieldElement5 = eCFieldElement5.multiply(eCFieldElement8);
                    break;
                }
                case 2: 
                case 3: 
                case 4: {
                    eCFieldElement = this.zs[0];
                    if (eCFieldElement.isOne()) break;
                    ECFieldElement eCFieldElement9 = eCFieldElement.square();
                    ECFieldElement eCFieldElement10 = eCFieldElement9.square();
                    ECFieldElement eCFieldElement11 = eCFieldElement9.multiply(eCFieldElement10);
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement10);
                    eCFieldElement5 = eCFieldElement5.multiply(eCFieldElement11);
                    break;
                }
                default: {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            eCFieldElement = eCFieldElement2.square().add(eCFieldElement4).multiply(eCFieldElement2).add(eCFieldElement5);
            return eCFieldElement6.equals(eCFieldElement);
        }

        public ECPoint subtract(ECPoint eCPoint) {
            if (eCPoint.isInfinity()) {
                return this;
            }
            return this.add(eCPoint.negate());
        }
    }

    public static class F2m
    extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if (eCFieldElement == null != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (eCFieldElement != null) {
                ECFieldElement.F2m.checkFieldElements(this.x, this.y);
                if (eCCurve != null) {
                    ECFieldElement.F2m.checkFieldElements(this.x, this.curve.getA());
                }
            }
            this.withCompression = bl;
        }

        F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
            this.withCompression = bl;
        }

        protected ECPoint detach() {
            return new F2m(null, this.getAffineXCoord(), this.getAffineYCoord());
        }

        public ECFieldElement getYCoord() {
            int n = this.getCurveCoordinateSystem();
            switch (n) {
                case 5: 
                case 6: {
                    ECFieldElement eCFieldElement;
                    ECFieldElement eCFieldElement2 = this.x;
                    ECFieldElement eCFieldElement3 = this.y;
                    if (this.isInfinity() || eCFieldElement2.isZero()) {
                        return eCFieldElement3;
                    }
                    ECFieldElement eCFieldElement4 = eCFieldElement3.add(eCFieldElement2).multiply(eCFieldElement2);
                    if (6 == n && !(eCFieldElement = this.zs[0]).isOne()) {
                        eCFieldElement4 = eCFieldElement4.divide(eCFieldElement);
                    }
                    return eCFieldElement4;
                }
            }
            return this.y;
        }

        protected boolean getCompressionYTilde() {
            ECFieldElement eCFieldElement = this.getRawXCoord();
            if (eCFieldElement.isZero()) {
                return false;
            }
            ECFieldElement eCFieldElement2 = this.getRawYCoord();
            switch (this.getCurveCoordinateSystem()) {
                case 5: 
                case 6: {
                    return eCFieldElement2.testBitZero() != eCFieldElement.testBitZero();
                }
            }
            return eCFieldElement2.divide(eCFieldElement).testBitZero();
        }

        public ECPoint add(ECPoint eCPoint) {
            if (this.isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve eCCurve = this.getCurve();
            int n = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = eCPoint.x;
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement3 = this.y;
                    ECFieldElement eCFieldElement4 = eCPoint.y;
                    ECFieldElement eCFieldElement5 = eCFieldElement.add(eCFieldElement2);
                    ECFieldElement eCFieldElement6 = eCFieldElement3.add(eCFieldElement4);
                    if (eCFieldElement5.isZero()) {
                        if (eCFieldElement6.isZero()) {
                            return this.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    ECFieldElement eCFieldElement7 = eCFieldElement6.divide(eCFieldElement5);
                    ECFieldElement eCFieldElement8 = eCFieldElement7.square().add(eCFieldElement7).add(eCFieldElement5).add(eCCurve.getA());
                    ECFieldElement eCFieldElement9 = eCFieldElement7.multiply(eCFieldElement.add(eCFieldElement8)).add(eCFieldElement8).add(eCFieldElement3);
                    return new F2m(eCCurve, eCFieldElement8, eCFieldElement9, this.withCompression);
                }
                case 1: {
                    ECFieldElement eCFieldElement10 = this.y;
                    ECFieldElement eCFieldElement11 = this.zs[0];
                    ECFieldElement eCFieldElement12 = eCPoint.y;
                    ECFieldElement eCFieldElement13 = eCPoint.zs[0];
                    boolean bl = eCFieldElement13.isOne();
                    ECFieldElement eCFieldElement14 = eCFieldElement11.multiply(eCFieldElement12);
                    ECFieldElement eCFieldElement15 = bl ? eCFieldElement10 : eCFieldElement10.multiply(eCFieldElement13);
                    ECFieldElement eCFieldElement16 = eCFieldElement14.add(eCFieldElement15);
                    ECFieldElement eCFieldElement17 = eCFieldElement11.multiply(eCFieldElement2);
                    ECFieldElement eCFieldElement18 = bl ? eCFieldElement : eCFieldElement.multiply(eCFieldElement13);
                    ECFieldElement eCFieldElement19 = eCFieldElement17.add(eCFieldElement18);
                    if (eCFieldElement19.isZero()) {
                        if (eCFieldElement16.isZero()) {
                            return this.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    ECFieldElement eCFieldElement20 = eCFieldElement19.square();
                    ECFieldElement eCFieldElement21 = eCFieldElement20.multiply(eCFieldElement19);
                    ECFieldElement eCFieldElement22 = bl ? eCFieldElement11 : eCFieldElement11.multiply(eCFieldElement13);
                    ECFieldElement eCFieldElement23 = eCFieldElement16.add(eCFieldElement19);
                    ECFieldElement eCFieldElement24 = eCFieldElement23.multiplyPlusProduct(eCFieldElement16, eCFieldElement20, eCCurve.getA()).multiply(eCFieldElement22).add(eCFieldElement21);
                    ECFieldElement eCFieldElement25 = eCFieldElement19.multiply(eCFieldElement24);
                    ECFieldElement eCFieldElement26 = bl ? eCFieldElement20 : eCFieldElement20.multiply(eCFieldElement13);
                    ECFieldElement eCFieldElement27 = eCFieldElement16.multiplyPlusProduct(eCFieldElement, eCFieldElement19, eCFieldElement10).multiplyPlusProduct(eCFieldElement26, eCFieldElement23, eCFieldElement24);
                    ECFieldElement eCFieldElement28 = eCFieldElement21.multiply(eCFieldElement22);
                    return new F2m(eCCurve, eCFieldElement25, eCFieldElement27, new ECFieldElement[]{eCFieldElement28}, this.withCompression);
                }
                case 6: {
                    ECFieldElement eCFieldElement29;
                    ECFieldElement eCFieldElement30;
                    ECFieldElement eCFieldElement31;
                    if (eCFieldElement.isZero()) {
                        if (eCFieldElement2.isZero()) {
                            return eCCurve.getInfinity();
                        }
                        return eCPoint.add(this);
                    }
                    ECFieldElement eCFieldElement32 = this.y;
                    ECFieldElement eCFieldElement33 = this.zs[0];
                    ECFieldElement eCFieldElement34 = eCPoint.y;
                    ECFieldElement eCFieldElement35 = eCPoint.zs[0];
                    boolean bl = eCFieldElement33.isOne();
                    ECFieldElement eCFieldElement36 = eCFieldElement2;
                    ECFieldElement eCFieldElement37 = eCFieldElement34;
                    if (!bl) {
                        eCFieldElement36 = eCFieldElement36.multiply(eCFieldElement33);
                        eCFieldElement37 = eCFieldElement37.multiply(eCFieldElement33);
                    }
                    boolean bl2 = eCFieldElement35.isOne();
                    ECFieldElement eCFieldElement38 = eCFieldElement;
                    ECFieldElement eCFieldElement39 = eCFieldElement32;
                    if (!bl2) {
                        eCFieldElement38 = eCFieldElement38.multiply(eCFieldElement35);
                        eCFieldElement39 = eCFieldElement39.multiply(eCFieldElement35);
                    }
                    ECFieldElement eCFieldElement40 = eCFieldElement39.add(eCFieldElement37);
                    ECFieldElement eCFieldElement41 = eCFieldElement38.add(eCFieldElement36);
                    if (eCFieldElement41.isZero()) {
                        if (eCFieldElement40.isZero()) {
                            return this.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    if (eCFieldElement2.isZero()) {
                        ECFieldElement eCFieldElement42;
                        ECPoint eCPoint2 = this.normalize();
                        eCFieldElement = eCPoint2.getXCoord();
                        ECFieldElement eCFieldElement43 = eCPoint2.getYCoord();
                        ECFieldElement eCFieldElement44 = eCFieldElement43.add(eCFieldElement42 = eCFieldElement34).divide(eCFieldElement);
                        eCFieldElement31 = eCFieldElement44.square().add(eCFieldElement44).add(eCFieldElement).add(eCCurve.getA());
                        if (eCFieldElement31.isZero()) {
                            return new F2m(eCCurve, eCFieldElement31, eCCurve.getB().sqrt(), this.withCompression);
                        }
                        ECFieldElement eCFieldElement45 = eCFieldElement44.multiply(eCFieldElement.add(eCFieldElement31)).add(eCFieldElement31).add(eCFieldElement43);
                        eCFieldElement30 = eCFieldElement45.divide(eCFieldElement31).add(eCFieldElement31);
                        eCFieldElement29 = eCCurve.fromBigInteger(ECConstants.ONE);
                    } else {
                        ECFieldElement eCFieldElement46;
                        eCFieldElement41 = eCFieldElement41.square();
                        ECFieldElement eCFieldElement47 = eCFieldElement40.multiply(eCFieldElement38);
                        eCFieldElement31 = eCFieldElement47.multiply(eCFieldElement46 = eCFieldElement40.multiply(eCFieldElement36));
                        if (eCFieldElement31.isZero()) {
                            return new F2m(eCCurve, eCFieldElement31, eCCurve.getB().sqrt(), this.withCompression);
                        }
                        ECFieldElement eCFieldElement48 = eCFieldElement40.multiply(eCFieldElement41);
                        if (!bl2) {
                            eCFieldElement48 = eCFieldElement48.multiply(eCFieldElement35);
                        }
                        eCFieldElement30 = eCFieldElement46.add(eCFieldElement41).squarePlusProduct(eCFieldElement48, eCFieldElement32.add(eCFieldElement33));
                        eCFieldElement29 = eCFieldElement48;
                        if (!bl) {
                            eCFieldElement29 = eCFieldElement29.multiply(eCFieldElement33);
                        }
                    }
                    return new F2m(eCCurve, eCFieldElement31, eCFieldElement30, new ECFieldElement[]{eCFieldElement29}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
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
            int n = eCCurve.getCoordinateSystem();
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement2 = this.y;
                    ECFieldElement eCFieldElement3 = eCFieldElement2.divide(eCFieldElement).add(eCFieldElement);
                    ECFieldElement eCFieldElement4 = eCFieldElement3.square().add(eCFieldElement3).add(eCCurve.getA());
                    ECFieldElement eCFieldElement5 = eCFieldElement.squarePlusProduct(eCFieldElement4, eCFieldElement3.addOne());
                    return new F2m(eCCurve, eCFieldElement4, eCFieldElement5, this.withCompression);
                }
                case 1: {
                    ECFieldElement eCFieldElement6 = this.y;
                    ECFieldElement eCFieldElement7 = this.zs[0];
                    boolean bl = eCFieldElement7.isOne();
                    ECFieldElement eCFieldElement8 = bl ? eCFieldElement : eCFieldElement.multiply(eCFieldElement7);
                    ECFieldElement eCFieldElement9 = bl ? eCFieldElement6 : eCFieldElement6.multiply(eCFieldElement7);
                    ECFieldElement eCFieldElement10 = eCFieldElement.square();
                    ECFieldElement eCFieldElement11 = eCFieldElement10.add(eCFieldElement9);
                    ECFieldElement eCFieldElement12 = eCFieldElement8;
                    ECFieldElement eCFieldElement13 = eCFieldElement12.square();
                    ECFieldElement eCFieldElement14 = eCFieldElement11.add(eCFieldElement12);
                    ECFieldElement eCFieldElement15 = eCFieldElement14.multiplyPlusProduct(eCFieldElement11, eCFieldElement13, eCCurve.getA());
                    ECFieldElement eCFieldElement16 = eCFieldElement12.multiply(eCFieldElement15);
                    ECFieldElement eCFieldElement17 = eCFieldElement10.square().multiplyPlusProduct(eCFieldElement12, eCFieldElement15, eCFieldElement14);
                    ECFieldElement eCFieldElement18 = eCFieldElement12.multiply(eCFieldElement13);
                    return new F2m(eCCurve, eCFieldElement16, eCFieldElement17, new ECFieldElement[]{eCFieldElement18}, this.withCompression);
                }
                case 6: {
                    ECFieldElement eCFieldElement19;
                    ECFieldElement eCFieldElement20 = this.y;
                    ECFieldElement eCFieldElement21 = this.zs[0];
                    boolean bl = eCFieldElement21.isOne();
                    ECFieldElement eCFieldElement22 = bl ? eCFieldElement20 : eCFieldElement20.multiply(eCFieldElement21);
                    ECFieldElement eCFieldElement23 = bl ? eCFieldElement21 : eCFieldElement21.square();
                    ECFieldElement eCFieldElement24 = eCCurve.getA();
                    ECFieldElement eCFieldElement25 = bl ? eCFieldElement24 : eCFieldElement24.multiply(eCFieldElement23);
                    ECFieldElement eCFieldElement26 = eCFieldElement20.square().add(eCFieldElement22).add(eCFieldElement25);
                    if (eCFieldElement26.isZero()) {
                        return new F2m(eCCurve, eCFieldElement26, eCCurve.getB().sqrt(), this.withCompression);
                    }
                    ECFieldElement eCFieldElement27 = eCFieldElement26.square();
                    ECFieldElement eCFieldElement28 = bl ? eCFieldElement26 : eCFieldElement26.multiply(eCFieldElement23);
                    ECFieldElement eCFieldElement29 = eCCurve.getB();
                    if (eCFieldElement29.bitLength() < eCCurve.getFieldSize() >> 1) {
                        ECFieldElement eCFieldElement30 = eCFieldElement20.add(eCFieldElement).square();
                        ECFieldElement eCFieldElement31 = eCFieldElement29.isOne() ? eCFieldElement25.add(eCFieldElement23).square() : eCFieldElement25.squarePlusProduct(eCFieldElement29, eCFieldElement23.square());
                        eCFieldElement19 = eCFieldElement30.add(eCFieldElement26).add(eCFieldElement23).multiply(eCFieldElement30).add(eCFieldElement31).add(eCFieldElement27);
                        if (eCFieldElement24.isZero()) {
                            eCFieldElement19 = eCFieldElement19.add(eCFieldElement28);
                        } else if (!eCFieldElement24.isOne()) {
                            eCFieldElement19 = eCFieldElement19.add(eCFieldElement24.addOne().multiply(eCFieldElement28));
                        }
                    } else {
                        ECFieldElement eCFieldElement32 = bl ? eCFieldElement : eCFieldElement.multiply(eCFieldElement21);
                        eCFieldElement19 = eCFieldElement32.squarePlusProduct(eCFieldElement26, eCFieldElement22).add(eCFieldElement27).add(eCFieldElement28);
                    }
                    return new F2m(eCCurve, eCFieldElement27, eCFieldElement19, new ECFieldElement[]{eCFieldElement28}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
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
            int n = eCCurve.getCoordinateSystem();
            switch (n) {
                case 6: {
                    ECFieldElement eCFieldElement2 = eCPoint.x;
                    ECFieldElement eCFieldElement3 = eCPoint.zs[0];
                    if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                        return this.twice().add(eCPoint);
                    }
                    ECFieldElement eCFieldElement4 = this.y;
                    ECFieldElement eCFieldElement5 = this.zs[0];
                    ECFieldElement eCFieldElement6 = eCPoint.y;
                    ECFieldElement eCFieldElement7 = eCFieldElement.square();
                    ECFieldElement eCFieldElement8 = eCFieldElement4.square();
                    ECFieldElement eCFieldElement9 = eCFieldElement5.square();
                    ECFieldElement eCFieldElement10 = eCFieldElement4.multiply(eCFieldElement5);
                    ECFieldElement eCFieldElement11 = eCCurve.getA().multiply(eCFieldElement9).add(eCFieldElement8).add(eCFieldElement10);
                    ECFieldElement eCFieldElement12 = eCFieldElement6.addOne();
                    ECFieldElement eCFieldElement13 = eCCurve.getA().add(eCFieldElement12).multiply(eCFieldElement9).add(eCFieldElement8).multiplyPlusProduct(eCFieldElement11, eCFieldElement7, eCFieldElement9);
                    ECFieldElement eCFieldElement14 = eCFieldElement2.multiply(eCFieldElement9);
                    ECFieldElement eCFieldElement15 = eCFieldElement14.add(eCFieldElement11).square();
                    if (eCFieldElement15.isZero()) {
                        if (eCFieldElement13.isZero()) {
                            return eCPoint.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    if (eCFieldElement13.isZero()) {
                        return new F2m(eCCurve, eCFieldElement13, eCCurve.getB().sqrt(), this.withCompression);
                    }
                    ECFieldElement eCFieldElement16 = eCFieldElement13.square().multiply(eCFieldElement14);
                    ECFieldElement eCFieldElement17 = eCFieldElement13.multiply(eCFieldElement15).multiply(eCFieldElement9);
                    ECFieldElement eCFieldElement18 = eCFieldElement13.add(eCFieldElement15).square().multiplyPlusProduct(eCFieldElement11, eCFieldElement12, eCFieldElement17);
                    return new F2m(eCCurve, eCFieldElement16, eCFieldElement18, new ECFieldElement[]{eCFieldElement17}, this.withCompression);
                }
            }
            return this.twice().add(eCPoint);
        }

        public ECPoint negate() {
            if (this.isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            switch (this.getCurveCoordinateSystem()) {
                case 0: {
                    ECFieldElement eCFieldElement2 = this.y;
                    return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement), this.withCompression);
                }
                case 1: {
                    ECFieldElement eCFieldElement3 = this.y;
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    return new F2m(this.curve, eCFieldElement, eCFieldElement3.add(eCFieldElement), new ECFieldElement[]{eCFieldElement4}, this.withCompression);
                }
                case 5: {
                    ECFieldElement eCFieldElement5 = this.y;
                    return new F2m(this.curve, eCFieldElement, eCFieldElement5.addOne(), this.withCompression);
                }
                case 6: {
                    ECFieldElement eCFieldElement6 = this.y;
                    ECFieldElement eCFieldElement7 = this.zs[0];
                    return new F2m(this.curve, eCFieldElement, eCFieldElement6.add(eCFieldElement7), new ECFieldElement[]{eCFieldElement7}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    public static class Fp
    extends AbstractFp {
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if (eCFieldElement == null != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.withCompression = bl;
        }

        Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArray);
            this.withCompression = bl;
        }

        protected ECPoint detach() {
            return new Fp(null, this.getAffineXCoord(), this.getAffineYCoord());
        }

        public ECFieldElement getZCoord(int n) {
            if (n == 1 && 4 == this.getCurveCoordinateSystem()) {
                return this.getJacobianModifiedW();
            }
            return super.getZCoord(n);
        }

        public ECPoint add(ECPoint eCPoint) {
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
            int n = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement eCFieldElement3 = eCPoint.x;
            ECFieldElement eCFieldElement4 = eCPoint.y;
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement5 = eCFieldElement3.subtract(eCFieldElement);
                    ECFieldElement eCFieldElement6 = eCFieldElement4.subtract(eCFieldElement2);
                    if (eCFieldElement5.isZero()) {
                        if (eCFieldElement6.isZero()) {
                            return this.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    ECFieldElement eCFieldElement7 = eCFieldElement6.divide(eCFieldElement5);
                    ECFieldElement eCFieldElement8 = eCFieldElement7.square().subtract(eCFieldElement).subtract(eCFieldElement3);
                    ECFieldElement eCFieldElement9 = eCFieldElement7.multiply(eCFieldElement.subtract(eCFieldElement8)).subtract(eCFieldElement2);
                    return new Fp(eCCurve, eCFieldElement8, eCFieldElement9, this.withCompression);
                }
                case 1: {
                    ECFieldElement eCFieldElement10 = this.zs[0];
                    ECFieldElement eCFieldElement11 = eCPoint.zs[0];
                    boolean bl = eCFieldElement10.isOne();
                    boolean bl2 = eCFieldElement11.isOne();
                    ECFieldElement eCFieldElement12 = bl ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement10);
                    ECFieldElement eCFieldElement13 = bl2 ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement11);
                    ECFieldElement eCFieldElement14 = eCFieldElement12.subtract(eCFieldElement13);
                    ECFieldElement eCFieldElement15 = bl ? eCFieldElement3 : eCFieldElement3.multiply(eCFieldElement10);
                    ECFieldElement eCFieldElement16 = bl2 ? eCFieldElement : eCFieldElement.multiply(eCFieldElement11);
                    ECFieldElement eCFieldElement17 = eCFieldElement15.subtract(eCFieldElement16);
                    if (eCFieldElement17.isZero()) {
                        if (eCFieldElement14.isZero()) {
                            return this.twice();
                        }
                        return eCCurve.getInfinity();
                    }
                    ECFieldElement eCFieldElement18 = bl ? eCFieldElement11 : (bl2 ? eCFieldElement10 : eCFieldElement10.multiply(eCFieldElement11));
                    ECFieldElement eCFieldElement19 = eCFieldElement17.square();
                    ECFieldElement eCFieldElement20 = eCFieldElement19.multiply(eCFieldElement17);
                    ECFieldElement eCFieldElement21 = eCFieldElement19.multiply(eCFieldElement16);
                    ECFieldElement eCFieldElement22 = eCFieldElement14.square().multiply(eCFieldElement18).subtract(eCFieldElement20).subtract(this.two(eCFieldElement21));
                    ECFieldElement eCFieldElement23 = eCFieldElement17.multiply(eCFieldElement22);
                    ECFieldElement eCFieldElement24 = eCFieldElement21.subtract(eCFieldElement22).multiplyMinusProduct(eCFieldElement14, eCFieldElement13, eCFieldElement20);
                    ECFieldElement eCFieldElement25 = eCFieldElement20.multiply(eCFieldElement18);
                    return new Fp(eCCurve, eCFieldElement23, eCFieldElement24, new ECFieldElement[]{eCFieldElement25}, this.withCompression);
                }
                case 2: 
                case 4: {
                    Object object;
                    ECFieldElement eCFieldElement26;
                    ECFieldElement eCFieldElement27;
                    ECFieldElement eCFieldElement28;
                    ECFieldElement[] eCFieldElementArray;
                    ECFieldElement[] eCFieldElementArray2 = this.zs[0];
                    ECFieldElement eCFieldElement29 = eCPoint.zs[0];
                    boolean bl = eCFieldElementArray2.isOne();
                    ECFieldElement eCFieldElement30 = null;
                    if (!bl && eCFieldElementArray2.equals(eCFieldElement29)) {
                        eCFieldElementArray = eCFieldElement.subtract(eCFieldElement3);
                        eCFieldElement28 = eCFieldElement2.subtract(eCFieldElement4);
                        if (eCFieldElementArray.isZero()) {
                            if (eCFieldElement28.isZero()) {
                                return this.twice();
                            }
                            return eCCurve.getInfinity();
                        }
                        ECFieldElement eCFieldElement31 = eCFieldElementArray.square();
                        ECFieldElement eCFieldElement32 = eCFieldElement.multiply(eCFieldElement31);
                        ECFieldElement eCFieldElement33 = eCFieldElement3.multiply(eCFieldElement31);
                        ECFieldElement eCFieldElement34 = eCFieldElement32.subtract(eCFieldElement33).multiply(eCFieldElement2);
                        eCFieldElement27 = eCFieldElement28.square().subtract(eCFieldElement32).subtract(eCFieldElement33);
                        eCFieldElement26 = eCFieldElement32.subtract(eCFieldElement27).multiply(eCFieldElement28).subtract(eCFieldElement34);
                        object = eCFieldElementArray;
                        object = object.multiply((ECFieldElement)eCFieldElementArray2);
                    } else {
                        ECFieldElement eCFieldElement35;
                        ECFieldElement eCFieldElement36;
                        ECFieldElement eCFieldElement37;
                        ECFieldElement eCFieldElement38;
                        if (bl) {
                            eCFieldElementArray = eCFieldElementArray2;
                            eCFieldElement28 = eCFieldElement3;
                            eCFieldElement38 = eCFieldElement4;
                        } else {
                            eCFieldElementArray = eCFieldElementArray2.square();
                            eCFieldElement28 = eCFieldElementArray.multiply(eCFieldElement3);
                            ECFieldElement eCFieldElement39 = eCFieldElementArray.multiply((ECFieldElement)eCFieldElementArray2);
                            eCFieldElement38 = eCFieldElement39.multiply(eCFieldElement4);
                        }
                        boolean bl3 = eCFieldElement29.isOne();
                        if (bl3) {
                            ECFieldElement eCFieldElement40 = eCFieldElement29;
                            eCFieldElement37 = eCFieldElement;
                            eCFieldElement36 = eCFieldElement2;
                        } else {
                            ECFieldElement eCFieldElement41 = eCFieldElement29.square();
                            eCFieldElement37 = eCFieldElement41.multiply(eCFieldElement);
                            eCFieldElement35 = eCFieldElement41.multiply(eCFieldElement29);
                            eCFieldElement36 = eCFieldElement35.multiply(eCFieldElement2);
                        }
                        eCFieldElement35 = eCFieldElement37.subtract(eCFieldElement28);
                        ECFieldElement eCFieldElement42 = eCFieldElement36.subtract(eCFieldElement38);
                        if (eCFieldElement35.isZero()) {
                            if (eCFieldElement42.isZero()) {
                                return this.twice();
                            }
                            return eCCurve.getInfinity();
                        }
                        ECFieldElement eCFieldElement43 = eCFieldElement35.square();
                        ECFieldElement eCFieldElement44 = eCFieldElement43.multiply(eCFieldElement35);
                        ECFieldElement eCFieldElement45 = eCFieldElement43.multiply(eCFieldElement37);
                        eCFieldElement27 = eCFieldElement42.square().add(eCFieldElement44).subtract(this.two(eCFieldElement45));
                        eCFieldElement26 = eCFieldElement45.subtract(eCFieldElement27).multiplyMinusProduct(eCFieldElement42, eCFieldElement44, eCFieldElement36);
                        object = eCFieldElement35;
                        if (!bl) {
                            object = object.multiply((ECFieldElement)eCFieldElementArray2);
                        }
                        if (!bl3) {
                            object = object.multiply(eCFieldElement29);
                        }
                        if (object == eCFieldElement35) {
                            eCFieldElement30 = eCFieldElement43;
                        }
                    }
                    if (n == 4) {
                        eCFieldElement28 = this.calculateJacobianModifiedW((ECFieldElement)object, eCFieldElement30);
                        eCFieldElementArray = new ECFieldElement[]{object, eCFieldElement28};
                    } else {
                        eCFieldElementArray = new ECFieldElement[]{object};
                    }
                    return new Fp(eCCurve, eCFieldElement27, eCFieldElement26, eCFieldElementArray, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
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
            int n = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement2 = this.x;
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement3 = eCFieldElement2.square();
                    ECFieldElement eCFieldElement4 = this.three(eCFieldElement3).add(this.getCurve().getA()).divide(this.two(eCFieldElement));
                    ECFieldElement eCFieldElement5 = eCFieldElement4.square().subtract(this.two(eCFieldElement2));
                    ECFieldElement eCFieldElement6 = eCFieldElement4.multiply(eCFieldElement2.subtract(eCFieldElement5)).subtract(eCFieldElement);
                    return new Fp(eCCurve, eCFieldElement5, eCFieldElement6, this.withCompression);
                }
                case 1: {
                    ECFieldElement eCFieldElement7 = this.zs[0];
                    boolean bl = eCFieldElement7.isOne();
                    ECFieldElement eCFieldElement8 = eCCurve.getA();
                    if (!eCFieldElement8.isZero() && !bl) {
                        eCFieldElement8 = eCFieldElement8.multiply(eCFieldElement7.square());
                    }
                    eCFieldElement8 = eCFieldElement8.add(this.three(eCFieldElement2.square()));
                    ECFieldElement eCFieldElement9 = bl ? eCFieldElement : eCFieldElement.multiply(eCFieldElement7);
                    ECFieldElement eCFieldElement10 = bl ? eCFieldElement.square() : eCFieldElement9.multiply(eCFieldElement);
                    ECFieldElement eCFieldElement11 = eCFieldElement2.multiply(eCFieldElement10);
                    ECFieldElement eCFieldElement12 = this.four(eCFieldElement11);
                    ECFieldElement eCFieldElement13 = eCFieldElement8.square().subtract(this.two(eCFieldElement12));
                    ECFieldElement eCFieldElement14 = this.two(eCFieldElement9);
                    ECFieldElement eCFieldElement15 = eCFieldElement13.multiply(eCFieldElement14);
                    ECFieldElement eCFieldElement16 = this.two(eCFieldElement10);
                    ECFieldElement eCFieldElement17 = eCFieldElement12.subtract(eCFieldElement13).multiply(eCFieldElement8).subtract(this.two(eCFieldElement16.square()));
                    ECFieldElement eCFieldElement18 = bl ? this.two(eCFieldElement16) : eCFieldElement14.square();
                    ECFieldElement eCFieldElement19 = this.two(eCFieldElement18).multiply(eCFieldElement9);
                    return new Fp(eCCurve, eCFieldElement15, eCFieldElement17, new ECFieldElement[]{eCFieldElement19}, this.withCompression);
                }
                case 2: {
                    ECFieldElement eCFieldElement20;
                    ECFieldElement eCFieldElement21;
                    ECFieldElement eCFieldElement22;
                    ECFieldElement eCFieldElement23;
                    ECFieldElement eCFieldElement24;
                    ECFieldElement eCFieldElement25 = this.zs[0];
                    boolean bl = eCFieldElement25.isOne();
                    ECFieldElement eCFieldElement26 = eCFieldElement.square();
                    ECFieldElement eCFieldElement27 = eCFieldElement26.square();
                    ECFieldElement eCFieldElement28 = eCCurve.getA();
                    ECFieldElement eCFieldElement29 = eCFieldElement28.negate();
                    if (eCFieldElement29.toBigInteger().equals(BigInteger.valueOf(3L))) {
                        eCFieldElement24 = bl ? eCFieldElement25 : eCFieldElement25.square();
                        eCFieldElement23 = this.three(eCFieldElement2.add(eCFieldElement24).multiply(eCFieldElement2.subtract(eCFieldElement24)));
                        eCFieldElement22 = this.four(eCFieldElement26.multiply(eCFieldElement2));
                    } else {
                        eCFieldElement24 = eCFieldElement2.square();
                        eCFieldElement23 = this.three(eCFieldElement24);
                        if (bl) {
                            eCFieldElement23 = eCFieldElement23.add(eCFieldElement28);
                        } else if (!eCFieldElement28.isZero()) {
                            eCFieldElement21 = eCFieldElement25.square();
                            eCFieldElement20 = eCFieldElement21.square();
                            eCFieldElement23 = eCFieldElement29.bitLength() < eCFieldElement28.bitLength() ? eCFieldElement23.subtract(eCFieldElement20.multiply(eCFieldElement29)) : eCFieldElement23.add(eCFieldElement20.multiply(eCFieldElement28));
                        }
                        eCFieldElement22 = this.four(eCFieldElement2.multiply(eCFieldElement26));
                    }
                    eCFieldElement24 = eCFieldElement23.square().subtract(this.two(eCFieldElement22));
                    eCFieldElement21 = eCFieldElement22.subtract(eCFieldElement24).multiply(eCFieldElement23).subtract(this.eight(eCFieldElement27));
                    eCFieldElement20 = this.two(eCFieldElement);
                    if (!bl) {
                        eCFieldElement20 = eCFieldElement20.multiply(eCFieldElement25);
                    }
                    return new Fp(eCCurve, eCFieldElement24, eCFieldElement21, new ECFieldElement[]{eCFieldElement20}, this.withCompression);
                }
                case 4: {
                    return this.twiceJacobianModified(true);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
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
            ECCurve eCCurve = this.getCurve();
            int n = eCCurve.getCoordinateSystem();
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement2 = this.x;
                    ECFieldElement eCFieldElement3 = eCPoint.x;
                    ECFieldElement eCFieldElement4 = eCPoint.y;
                    ECFieldElement eCFieldElement5 = eCFieldElement3.subtract(eCFieldElement2);
                    ECFieldElement eCFieldElement6 = eCFieldElement4.subtract(eCFieldElement);
                    if (eCFieldElement5.isZero()) {
                        if (eCFieldElement6.isZero()) {
                            return this.threeTimes();
                        }
                        return this;
                    }
                    ECFieldElement eCFieldElement7 = eCFieldElement5.square();
                    ECFieldElement eCFieldElement8 = eCFieldElement6.square();
                    ECFieldElement eCFieldElement9 = eCFieldElement7.multiply(this.two(eCFieldElement2).add(eCFieldElement3)).subtract(eCFieldElement8);
                    if (eCFieldElement9.isZero()) {
                        return eCCurve.getInfinity();
                    }
                    ECFieldElement eCFieldElement10 = eCFieldElement9.multiply(eCFieldElement5);
                    ECFieldElement eCFieldElement11 = eCFieldElement10.invert();
                    ECFieldElement eCFieldElement12 = eCFieldElement9.multiply(eCFieldElement11).multiply(eCFieldElement6);
                    ECFieldElement eCFieldElement13 = this.two(eCFieldElement).multiply(eCFieldElement7).multiply(eCFieldElement5).multiply(eCFieldElement11).subtract(eCFieldElement12);
                    ECFieldElement eCFieldElement14 = eCFieldElement13.subtract(eCFieldElement12).multiply(eCFieldElement12.add(eCFieldElement13)).add(eCFieldElement3);
                    ECFieldElement eCFieldElement15 = eCFieldElement2.subtract(eCFieldElement14).multiply(eCFieldElement13).subtract(eCFieldElement);
                    return new Fp(eCCurve, eCFieldElement14, eCFieldElement15, this.withCompression);
                }
                case 4: {
                    return this.twiceJacobianModified(false).add(eCPoint);
                }
            }
            return this.twice().add(eCPoint);
        }

        public ECPoint threeTimes() {
            if (this.isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve eCCurve = this.getCurve();
            int n = eCCurve.getCoordinateSystem();
            switch (n) {
                case 0: {
                    ECFieldElement eCFieldElement2 = this.x;
                    ECFieldElement eCFieldElement3 = this.two(eCFieldElement);
                    ECFieldElement eCFieldElement4 = eCFieldElement3.square();
                    ECFieldElement eCFieldElement5 = this.three(eCFieldElement2.square()).add(this.getCurve().getA());
                    ECFieldElement eCFieldElement6 = eCFieldElement5.square();
                    ECFieldElement eCFieldElement7 = this.three(eCFieldElement2).multiply(eCFieldElement4).subtract(eCFieldElement6);
                    if (eCFieldElement7.isZero()) {
                        return this.getCurve().getInfinity();
                    }
                    ECFieldElement eCFieldElement8 = eCFieldElement7.multiply(eCFieldElement3);
                    ECFieldElement eCFieldElement9 = eCFieldElement8.invert();
                    ECFieldElement eCFieldElement10 = eCFieldElement7.multiply(eCFieldElement9).multiply(eCFieldElement5);
                    ECFieldElement eCFieldElement11 = eCFieldElement4.square().multiply(eCFieldElement9).subtract(eCFieldElement10);
                    ECFieldElement eCFieldElement12 = eCFieldElement11.subtract(eCFieldElement10).multiply(eCFieldElement10.add(eCFieldElement11)).add(eCFieldElement2);
                    ECFieldElement eCFieldElement13 = eCFieldElement2.subtract(eCFieldElement12).multiply(eCFieldElement11).subtract(eCFieldElement);
                    return new Fp(eCCurve, eCFieldElement12, eCFieldElement13, this.withCompression);
                }
                case 4: {
                    return this.twiceJacobianModified(false).add(this);
                }
            }
            return this.twice().add(this);
        }

        public ECPoint timesPow2(int n) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            if (n < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (n == 0 || this.isInfinity()) {
                return this;
            }
            if (n == 1) {
                return this.twice();
            }
            ECCurve eCCurve = this.getCurve();
            ECFieldElement eCFieldElement4 = this.y;
            if (eCFieldElement4.isZero()) {
                return eCCurve.getInfinity();
            }
            int n2 = eCCurve.getCoordinateSystem();
            ECFieldElement eCFieldElement5 = eCCurve.getA();
            ECFieldElement eCFieldElement6 = this.x;
            ECFieldElement eCFieldElement7 = eCFieldElement3 = this.zs.length < 1 ? eCCurve.fromBigInteger(ECConstants.ONE) : this.zs[0];
            if (!eCFieldElement3.isOne()) {
                switch (n2) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        ECFieldElement eCFieldElement8 = eCFieldElement3.square();
                        eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement3);
                        eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement8);
                        eCFieldElement5 = this.calculateJacobianModifiedW(eCFieldElement3, eCFieldElement8);
                        break;
                    }
                    case 2: {
                        eCFieldElement5 = this.calculateJacobianModifiedW(eCFieldElement3, null);
                        break;
                    }
                    case 4: {
                        eCFieldElement5 = this.getJacobianModifiedW();
                        break;
                    }
                    default: {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                }
            }
            for (int i = 0; i < n; ++i) {
                if (eCFieldElement4.isZero()) {
                    return eCCurve.getInfinity();
                }
                eCFieldElement2 = eCFieldElement6.square();
                eCFieldElement = this.three(eCFieldElement2);
                ECFieldElement eCFieldElement9 = this.two(eCFieldElement4);
                ECFieldElement eCFieldElement10 = eCFieldElement9.multiply(eCFieldElement4);
                ECFieldElement eCFieldElement11 = this.two(eCFieldElement6.multiply(eCFieldElement10));
                ECFieldElement eCFieldElement12 = eCFieldElement10.square();
                ECFieldElement eCFieldElement13 = this.two(eCFieldElement12);
                if (!eCFieldElement5.isZero()) {
                    eCFieldElement = eCFieldElement.add(eCFieldElement5);
                    eCFieldElement5 = this.two(eCFieldElement13.multiply(eCFieldElement5));
                }
                eCFieldElement6 = eCFieldElement.square().subtract(this.two(eCFieldElement11));
                eCFieldElement4 = eCFieldElement.multiply(eCFieldElement11.subtract(eCFieldElement6)).subtract(eCFieldElement13);
                eCFieldElement3 = eCFieldElement3.isOne() ? eCFieldElement9 : eCFieldElement9.multiply(eCFieldElement3);
            }
            switch (n2) {
                case 0: {
                    ECFieldElement eCFieldElement14 = eCFieldElement3.invert();
                    eCFieldElement2 = eCFieldElement14.square();
                    eCFieldElement = eCFieldElement2.multiply(eCFieldElement14);
                    return new Fp(eCCurve, eCFieldElement6.multiply(eCFieldElement2), eCFieldElement4.multiply(eCFieldElement), this.withCompression);
                }
                case 1: {
                    eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement3);
                    eCFieldElement3 = eCFieldElement3.multiply(eCFieldElement3.square());
                    return new Fp(eCCurve, eCFieldElement6, eCFieldElement4, new ECFieldElement[]{eCFieldElement3}, this.withCompression);
                }
                case 2: {
                    return new Fp(eCCurve, eCFieldElement6, eCFieldElement4, new ECFieldElement[]{eCFieldElement3}, this.withCompression);
                }
                case 4: {
                    return new Fp(eCCurve, eCFieldElement6, eCFieldElement4, new ECFieldElement[]{eCFieldElement3, eCFieldElement5}, this.withCompression);
                }
            }
            throw new IllegalStateException("unsupported coordinate system");
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
            ECCurve eCCurve = this.getCurve();
            int n = eCCurve.getCoordinateSystem();
            if (0 != n) {
                return new Fp(eCCurve, this.x, this.y.negate(), this.zs, this.withCompression);
            }
            return new Fp(eCCurve, this.x, this.y.negate(), this.withCompression);
        }

        protected ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement eCFieldElement3 = this.getCurve().getA();
            if (eCFieldElement3.isZero() || eCFieldElement.isOne()) {
                return eCFieldElement3;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement eCFieldElement4 = eCFieldElement2.square();
            ECFieldElement eCFieldElement5 = eCFieldElement3.negate();
            eCFieldElement4 = eCFieldElement5.bitLength() < eCFieldElement3.bitLength() ? eCFieldElement4.multiply(eCFieldElement5).negate() : eCFieldElement4.multiply(eCFieldElement3);
            return eCFieldElement4;
        }

        protected ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.zs[1];
            if (eCFieldElement == null) {
                this.zs[1] = eCFieldElement = this.calculateJacobianModifiedW(this.zs[0], null);
            }
            return eCFieldElement;
        }

        protected Fp twiceJacobianModified(boolean bl) {
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            ECFieldElement eCFieldElement4 = this.getJacobianModifiedW();
            ECFieldElement eCFieldElement5 = eCFieldElement.square();
            ECFieldElement eCFieldElement6 = this.three(eCFieldElement5).add(eCFieldElement4);
            ECFieldElement eCFieldElement7 = this.two(eCFieldElement2);
            ECFieldElement eCFieldElement8 = eCFieldElement7.multiply(eCFieldElement2);
            ECFieldElement eCFieldElement9 = this.two(eCFieldElement.multiply(eCFieldElement8));
            ECFieldElement eCFieldElement10 = eCFieldElement6.square().subtract(this.two(eCFieldElement9));
            ECFieldElement eCFieldElement11 = eCFieldElement8.square();
            ECFieldElement eCFieldElement12 = this.two(eCFieldElement11);
            ECFieldElement eCFieldElement13 = eCFieldElement6.multiply(eCFieldElement9.subtract(eCFieldElement10)).subtract(eCFieldElement12);
            ECFieldElement eCFieldElement14 = bl ? this.two(eCFieldElement12.multiply(eCFieldElement4)) : null;
            ECFieldElement eCFieldElement15 = eCFieldElement3.isOne() ? eCFieldElement7 : eCFieldElement7.multiply(eCFieldElement3);
            return new Fp(this.getCurve(), eCFieldElement10, eCFieldElement13, new ECFieldElement[]{eCFieldElement15, eCFieldElement14}, this.withCompression);
        }
    }
}

