/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.SimpleBigDecimal;
import org.bouncycastle.math.ec.ZTauElement;

class Tnaf {
    private static final BigInteger MINUS_ONE = ECConstants.ONE.negate();
    private static final BigInteger MINUS_TWO = ECConstants.TWO.negate();
    private static final BigInteger MINUS_THREE = ECConstants.THREE.negate();
    public static final byte WIDTH = 4;
    public static final byte POW_2_WIDTH = 16;
    public static final ZTauElement[] alpha0 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, MINUS_ONE), null, new ZTauElement(MINUS_ONE, MINUS_ONE), null, new ZTauElement(ECConstants.ONE, MINUS_ONE), null};
    public static final byte[][] alpha0Tnaf = new byte[][]{null, {1}, null, {-1, 0, 1}, null, {1, 0, 1}, null, {-1, 0, 0, 1}};
    public static final ZTauElement[] alpha1 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, ECConstants.ONE), null, new ZTauElement(MINUS_ONE, ECConstants.ONE), null, new ZTauElement(ECConstants.ONE, ECConstants.ONE), null};
    public static final byte[][] alpha1Tnaf = new byte[][]{null, {1}, null, {-1, 0, 1}, null, {1, 0, 1}, null, {-1, 0, 0, -1}};

    Tnaf() {
    }

    public static BigInteger norm(byte by, ZTauElement zTauElement) {
        BigInteger bigInteger;
        BigInteger bigInteger2 = zTauElement.u.multiply(zTauElement.u);
        BigInteger bigInteger3 = zTauElement.u.multiply(zTauElement.v);
        BigInteger bigInteger4 = zTauElement.v.multiply(zTauElement.v).shiftLeft(1);
        if (by == 1) {
            bigInteger = bigInteger2.add(bigInteger3).add(bigInteger4);
        } else if (by == -1) {
            bigInteger = bigInteger2.subtract(bigInteger3).add(bigInteger4);
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        return bigInteger;
    }

    public static SimpleBigDecimal norm(byte by, SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2) {
        SimpleBigDecimal simpleBigDecimal3;
        SimpleBigDecimal simpleBigDecimal4 = simpleBigDecimal.multiply(simpleBigDecimal);
        SimpleBigDecimal simpleBigDecimal5 = simpleBigDecimal.multiply(simpleBigDecimal2);
        SimpleBigDecimal simpleBigDecimal6 = simpleBigDecimal2.multiply(simpleBigDecimal2).shiftLeft(1);
        if (by == 1) {
            simpleBigDecimal3 = simpleBigDecimal4.add(simpleBigDecimal5).add(simpleBigDecimal6);
        } else if (by == -1) {
            simpleBigDecimal3 = simpleBigDecimal4.subtract(simpleBigDecimal5).add(simpleBigDecimal6);
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        return simpleBigDecimal3;
    }

    public static ZTauElement round(SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2, byte by) {
        SimpleBigDecimal simpleBigDecimal3;
        SimpleBigDecimal simpleBigDecimal4;
        int n = simpleBigDecimal.getScale();
        if (simpleBigDecimal2.getScale() != n) {
            throw new IllegalArgumentException("lambda0 and lambda1 do not have same scale");
        }
        if (by != 1 && by != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        BigInteger bigInteger = simpleBigDecimal.round();
        BigInteger bigInteger2 = simpleBigDecimal2.round();
        SimpleBigDecimal simpleBigDecimal5 = simpleBigDecimal.subtract(bigInteger);
        SimpleBigDecimal simpleBigDecimal6 = simpleBigDecimal2.subtract(bigInteger2);
        SimpleBigDecimal simpleBigDecimal7 = simpleBigDecimal5.add(simpleBigDecimal5);
        simpleBigDecimal7 = by == 1 ? simpleBigDecimal7.add(simpleBigDecimal6) : simpleBigDecimal7.subtract(simpleBigDecimal6);
        SimpleBigDecimal simpleBigDecimal8 = simpleBigDecimal6.add(simpleBigDecimal6).add(simpleBigDecimal6);
        SimpleBigDecimal simpleBigDecimal9 = simpleBigDecimal8.add(simpleBigDecimal6);
        if (by == 1) {
            simpleBigDecimal4 = simpleBigDecimal5.subtract(simpleBigDecimal8);
            simpleBigDecimal3 = simpleBigDecimal5.add(simpleBigDecimal9);
        } else {
            simpleBigDecimal4 = simpleBigDecimal5.add(simpleBigDecimal8);
            simpleBigDecimal3 = simpleBigDecimal5.subtract(simpleBigDecimal9);
        }
        int n2 = 0;
        byte by2 = 0;
        if (simpleBigDecimal7.compareTo(ECConstants.ONE) >= 0) {
            if (simpleBigDecimal4.compareTo(MINUS_ONE) < 0) {
                by2 = by;
            } else {
                n2 = 1;
            }
        } else if (simpleBigDecimal3.compareTo(ECConstants.TWO) >= 0) {
            by2 = by;
        }
        if (simpleBigDecimal7.compareTo(MINUS_ONE) < 0) {
            if (simpleBigDecimal4.compareTo(ECConstants.ONE) >= 0) {
                by2 = -by;
            } else {
                n2 = -1;
            }
        } else if (simpleBigDecimal3.compareTo(MINUS_TWO) < 0) {
            by2 = -by;
        }
        BigInteger bigInteger3 = bigInteger.add(BigInteger.valueOf(n2));
        BigInteger bigInteger4 = bigInteger2.add(BigInteger.valueOf(by2));
        return new ZTauElement(bigInteger3, bigInteger4);
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte by, int n, int n2) {
        int n3 = (n + 5) / 2 + n2;
        BigInteger bigInteger4 = bigInteger.shiftRight(n - n3 - 2 + by);
        BigInteger bigInteger5 = bigInteger2.multiply(bigInteger4);
        BigInteger bigInteger6 = bigInteger5.shiftRight(n);
        BigInteger bigInteger7 = bigInteger3.multiply(bigInteger6);
        BigInteger bigInteger8 = bigInteger5.add(bigInteger7);
        BigInteger bigInteger9 = bigInteger8.shiftRight(n3 - n2);
        if (bigInteger8.testBit(n3 - n2 - 1)) {
            bigInteger9 = bigInteger9.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(bigInteger9, n2);
    }

    public static byte[] tauAdicNaf(byte by, ZTauElement zTauElement) {
        Object object;
        if (by != 1 && by != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        BigInteger bigInteger = Tnaf.norm(by, zTauElement);
        int n = bigInteger.bitLength();
        int n2 = n > 30 ? n + 4 : 34;
        byte[] byArray = new byte[n2];
        int n3 = 0;
        int n4 = 0;
        BigInteger bigInteger2 = zTauElement.u;
        BigInteger bigInteger3 = zTauElement.v;
        while (!bigInteger2.equals(ECConstants.ZERO) || !bigInteger3.equals(ECConstants.ZERO)) {
            if (bigInteger2.testBit(0)) {
                byArray[n3] = (byte)ECConstants.TWO.subtract(bigInteger2.subtract(bigInteger3.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                bigInteger2 = byArray[n3] == 1 ? bigInteger2.clearBit(0) : bigInteger2.add(ECConstants.ONE);
                n4 = n3;
            } else {
                byArray[n3] = 0;
            }
            object = bigInteger2;
            BigInteger bigInteger4 = bigInteger2.shiftRight(1);
            bigInteger2 = by == 1 ? bigInteger3.add(bigInteger4) : bigInteger3.subtract(bigInteger4);
            bigInteger3 = ((BigInteger)object).shiftRight(1).negate();
            ++n3;
        }
        object = new byte[++n4];
        System.arraycopy(byArray, 0, object, 0, n4);
        return object;
    }

    public static ECPoint.AbstractF2m tau(ECPoint.AbstractF2m abstractF2m) {
        return abstractF2m.tau();
    }

    public static byte getMu(ECCurve.AbstractF2m abstractF2m) {
        if (!abstractF2m.isKoblitz()) {
            throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
        }
        if (abstractF2m.getA().isZero()) {
            return -1;
        }
        return 1;
    }

    public static byte getMu(ECFieldElement eCFieldElement) {
        return (byte)(eCFieldElement.isZero() ? -1 : 1);
    }

    public static byte getMu(int n) {
        return (byte)(n == 0 ? -1 : 1);
    }

    public static BigInteger[] getLucas(byte by, int n, boolean bl) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (by != 1 && by != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        if (bl) {
            bigInteger2 = ECConstants.TWO;
            bigInteger = BigInteger.valueOf(by);
        } else {
            bigInteger2 = ECConstants.ZERO;
            bigInteger = ECConstants.ONE;
        }
        for (int i = 1; i < n; ++i) {
            BigInteger bigInteger3 = null;
            bigInteger3 = by == 1 ? bigInteger : bigInteger.negate();
            BigInteger bigInteger4 = bigInteger3.subtract(bigInteger2.shiftLeft(1));
            bigInteger2 = bigInteger;
            bigInteger = bigInteger4;
        }
        BigInteger[] bigIntegerArray = new BigInteger[]{bigInteger2, bigInteger};
        return bigIntegerArray;
    }

    public static BigInteger getTw(byte by, int n) {
        if (n == 4) {
            if (by == 1) {
                return BigInteger.valueOf(6L);
            }
            return BigInteger.valueOf(10L);
        }
        BigInteger[] bigIntegerArray = Tnaf.getLucas(by, n, false);
        BigInteger bigInteger = ECConstants.ZERO.setBit(n);
        BigInteger bigInteger2 = bigIntegerArray[1].modInverse(bigInteger);
        BigInteger bigInteger3 = ECConstants.TWO.multiply(bigIntegerArray[0]).multiply(bigInteger2).mod(bigInteger);
        return bigInteger3;
    }

    public static BigInteger[] getSi(ECCurve.AbstractF2m abstractF2m) {
        if (!abstractF2m.isKoblitz()) {
            throw new IllegalArgumentException("si is defined for Koblitz curves only");
        }
        int n = abstractF2m.getFieldSize();
        int n2 = abstractF2m.getA().toBigInteger().intValue();
        byte by = Tnaf.getMu(n2);
        int n3 = Tnaf.getShiftsForCofactor(abstractF2m.getCofactor());
        int n4 = n + 3 - n2;
        BigInteger[] bigIntegerArray = Tnaf.getLucas(by, n4, false);
        if (by == 1) {
            bigIntegerArray[0] = bigIntegerArray[0].negate();
            bigIntegerArray[1] = bigIntegerArray[1].negate();
        }
        BigInteger bigInteger = ECConstants.ONE.add(bigIntegerArray[1]).shiftRight(n3);
        BigInteger bigInteger2 = ECConstants.ONE.add(bigIntegerArray[0]).shiftRight(n3).negate();
        return new BigInteger[]{bigInteger, bigInteger2};
    }

    public static BigInteger[] getSi(int n, int n2, BigInteger bigInteger) {
        byte by = Tnaf.getMu(n2);
        int n3 = Tnaf.getShiftsForCofactor(bigInteger);
        int n4 = n + 3 - n2;
        BigInteger[] bigIntegerArray = Tnaf.getLucas(by, n4, false);
        if (by == 1) {
            bigIntegerArray[0] = bigIntegerArray[0].negate();
            bigIntegerArray[1] = bigIntegerArray[1].negate();
        }
        BigInteger bigInteger2 = ECConstants.ONE.add(bigIntegerArray[1]).shiftRight(n3);
        BigInteger bigInteger3 = ECConstants.ONE.add(bigIntegerArray[0]).shiftRight(n3).negate();
        return new BigInteger[]{bigInteger2, bigInteger3};
    }

    protected static int getShiftsForCofactor(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static ZTauElement partModReduction(BigInteger bigInteger, int n, byte by, BigInteger[] bigIntegerArray, byte by2, byte by3) {
        BigInteger bigInteger2 = by2 == 1 ? bigIntegerArray[0].add(bigIntegerArray[1]) : bigIntegerArray[0].subtract(bigIntegerArray[1]);
        BigInteger[] bigIntegerArray2 = Tnaf.getLucas(by2, n, true);
        BigInteger bigInteger3 = bigIntegerArray2[1];
        SimpleBigDecimal simpleBigDecimal = Tnaf.approximateDivisionByN(bigInteger, bigIntegerArray[0], bigInteger3, by, n, by3);
        SimpleBigDecimal simpleBigDecimal2 = Tnaf.approximateDivisionByN(bigInteger, bigIntegerArray[1], bigInteger3, by, n, by3);
        ZTauElement zTauElement = Tnaf.round(simpleBigDecimal, simpleBigDecimal2, by2);
        BigInteger bigInteger4 = bigInteger.subtract(bigInteger2.multiply(zTauElement.u)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArray[1]).multiply(zTauElement.v));
        BigInteger bigInteger5 = bigIntegerArray[1].multiply(zTauElement.u).subtract(bigIntegerArray[0].multiply(zTauElement.v));
        return new ZTauElement(bigInteger4, bigInteger5);
    }

    public static ECPoint.AbstractF2m multiplyRTnaf(ECPoint.AbstractF2m abstractF2m, BigInteger bigInteger) {
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m)abstractF2m.getCurve();
        int n = abstractF2m2.getFieldSize();
        int n2 = abstractF2m2.getA().toBigInteger().intValue();
        byte by = Tnaf.getMu(n2);
        BigInteger[] bigIntegerArray = abstractF2m2.getSi();
        ZTauElement zTauElement = Tnaf.partModReduction(bigInteger, n, (byte)n2, bigIntegerArray, by, (byte)10);
        return Tnaf.multiplyTnaf(abstractF2m, zTauElement);
    }

    public static ECPoint.AbstractF2m multiplyTnaf(ECPoint.AbstractF2m abstractF2m, ZTauElement zTauElement) {
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m)abstractF2m.getCurve();
        byte by = Tnaf.getMu(abstractF2m2.getA());
        byte[] byArray = Tnaf.tauAdicNaf(by, zTauElement);
        ECPoint.AbstractF2m abstractF2m3 = Tnaf.multiplyFromTnaf(abstractF2m, byArray);
        return abstractF2m3;
    }

    public static ECPoint.AbstractF2m multiplyFromTnaf(ECPoint.AbstractF2m abstractF2m, byte[] byArray) {
        ECCurve eCCurve = abstractF2m.getCurve();
        ECPoint.AbstractF2m abstractF2m2 = (ECPoint.AbstractF2m)eCCurve.getInfinity();
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m)abstractF2m.negate();
        int n = 0;
        for (int i = byArray.length - 1; i >= 0; --i) {
            ++n;
            byte by = byArray[i];
            if (by == 0) continue;
            abstractF2m2 = abstractF2m2.tauPow(n);
            n = 0;
            ECPoint.AbstractF2m abstractF2m4 = by > 0 ? abstractF2m : abstractF2m3;
            abstractF2m2 = (ECPoint.AbstractF2m)abstractF2m2.add(abstractF2m4);
        }
        if (n > 0) {
            abstractF2m2 = abstractF2m2.tauPow(n);
        }
        return abstractF2m2;
    }

    public static byte[] tauAdicWNaf(byte by, ZTauElement zTauElement, byte by2, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArray) {
        if (by != 1 && by != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        BigInteger bigInteger3 = Tnaf.norm(by, zTauElement);
        int n = bigInteger3.bitLength();
        int n2 = n > 30 ? n + 4 + by2 : 34 + by2;
        byte[] byArray = new byte[n2];
        BigInteger bigInteger4 = bigInteger.shiftRight(1);
        BigInteger bigInteger5 = zTauElement.u;
        BigInteger bigInteger6 = zTauElement.v;
        int n3 = 0;
        while (!bigInteger5.equals(ECConstants.ZERO) || !bigInteger6.equals(ECConstants.ZERO)) {
            BigInteger bigInteger7;
            if (bigInteger5.testBit(0)) {
                bigInteger7 = bigInteger5.add(bigInteger6.multiply(bigInteger2)).mod(bigInteger);
                byte by3 = bigInteger7.compareTo(bigInteger4) >= 0 ? (byte)bigInteger7.subtract(bigInteger).intValue() : (byte)bigInteger7.intValue();
                byArray[n3] = by3;
                boolean bl = true;
                if (by3 < 0) {
                    bl = false;
                    by3 = -by3;
                }
                if (bl) {
                    bigInteger5 = bigInteger5.subtract(zTauElementArray[by3].u);
                    bigInteger6 = bigInteger6.subtract(zTauElementArray[by3].v);
                } else {
                    bigInteger5 = bigInteger5.add(zTauElementArray[by3].u);
                    bigInteger6 = bigInteger6.add(zTauElementArray[by3].v);
                }
            } else {
                byArray[n3] = 0;
            }
            bigInteger7 = bigInteger5;
            bigInteger5 = by == 1 ? bigInteger6.add(bigInteger5.shiftRight(1)) : bigInteger6.subtract(bigInteger5.shiftRight(1));
            bigInteger6 = bigInteger7.shiftRight(1).negate();
            ++n3;
        }
        return byArray;
    }

    public static ECPoint.AbstractF2m[] getPreComp(ECPoint.AbstractF2m abstractF2m, byte by) {
        byte[][] byArray = by == 0 ? alpha0Tnaf : alpha1Tnaf;
        ECPoint[] eCPointArray = new ECPoint.AbstractF2m[byArray.length + 1 >>> 1];
        eCPointArray[0] = abstractF2m;
        int n = byArray.length;
        for (int i = 3; i < n; i += 2) {
            eCPointArray[i >>> 1] = Tnaf.multiplyFromTnaf(abstractF2m, byArray[i]);
        }
        abstractF2m.getCurve().normalizeAll(eCPointArray);
        return eCPointArray;
    }
}

