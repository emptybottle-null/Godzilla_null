/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.ECPointMap;
import org.bouncycastle.math.ec.WNafPreCompInfo;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.math.ec.endo.ECEndomorphism;
import org.bouncycastle.math.ec.endo.GLVEndomorphism;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.PolynomialExtensionField;

public class ECAlgorithms {
    public static boolean isF2mCurve(ECCurve eCCurve) {
        return ECAlgorithms.isF2mField(eCCurve.getField());
    }

    public static boolean isF2mField(FiniteField finiteField) {
        return finiteField.getDimension() > 1 && finiteField.getCharacteristic().equals(ECConstants.TWO) && finiteField instanceof PolynomialExtensionField;
    }

    public static boolean isFpCurve(ECCurve eCCurve) {
        return ECAlgorithms.isFpField(eCCurve.getField());
    }

    public static boolean isFpField(FiniteField finiteField) {
        return finiteField.getDimension() == 1;
    }

    public static ECPoint sumOfMultiplies(ECPoint[] eCPointArray, BigInteger[] bigIntegerArray) {
        if (eCPointArray == null || bigIntegerArray == null || eCPointArray.length != bigIntegerArray.length || eCPointArray.length < 1) {
            throw new IllegalArgumentException("point and scalar arrays should be non-null, and of equal, non-zero, length");
        }
        int n = eCPointArray.length;
        switch (n) {
            case 1: {
                return eCPointArray[0].multiply(bigIntegerArray[0]);
            }
            case 2: {
                return ECAlgorithms.sumOfTwoMultiplies(eCPointArray[0], bigIntegerArray[0], eCPointArray[1], bigIntegerArray[1]);
            }
        }
        ECPoint eCPoint = eCPointArray[0];
        ECCurve eCCurve = eCPoint.getCurve();
        ECPoint[] eCPointArray2 = new ECPoint[n];
        eCPointArray2[0] = eCPoint;
        for (int i = 1; i < n; ++i) {
            eCPointArray2[i] = ECAlgorithms.importPoint(eCCurve, eCPointArray[i]);
        }
        ECEndomorphism eCEndomorphism = eCCurve.getEndomorphism();
        if (eCEndomorphism instanceof GLVEndomorphism) {
            return ECAlgorithms.validatePoint(ECAlgorithms.implSumOfMultipliesGLV(eCPointArray2, bigIntegerArray, (GLVEndomorphism)eCEndomorphism));
        }
        return ECAlgorithms.validatePoint(ECAlgorithms.implSumOfMultiplies(eCPointArray2, bigIntegerArray));
    }

    public static ECPoint sumOfTwoMultiplies(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        Object object;
        ECCurve eCCurve = eCPoint.getCurve();
        eCPoint2 = ECAlgorithms.importPoint(eCCurve, eCPoint2);
        if (eCCurve instanceof ECCurve.AbstractF2m && ((ECCurve.AbstractF2m)(object = (ECCurve.AbstractF2m)eCCurve)).isKoblitz()) {
            return ECAlgorithms.validatePoint(eCPoint.multiply(bigInteger).add(eCPoint2.multiply(bigInteger2)));
        }
        object = eCCurve.getEndomorphism();
        if (object instanceof GLVEndomorphism) {
            return ECAlgorithms.validatePoint(ECAlgorithms.implSumOfMultipliesGLV(new ECPoint[]{eCPoint, eCPoint2}, new BigInteger[]{bigInteger, bigInteger2}, (GLVEndomorphism)object));
        }
        return ECAlgorithms.validatePoint(ECAlgorithms.implShamirsTrickWNaf(eCPoint, bigInteger, eCPoint2, bigInteger2));
    }

    public static ECPoint shamirsTrick(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECCurve eCCurve = eCPoint.getCurve();
        eCPoint2 = ECAlgorithms.importPoint(eCCurve, eCPoint2);
        return ECAlgorithms.validatePoint(ECAlgorithms.implShamirsTrickJsf(eCPoint, bigInteger, eCPoint2, bigInteger2));
    }

    public static ECPoint importPoint(ECCurve eCCurve, ECPoint eCPoint) {
        ECCurve eCCurve2 = eCPoint.getCurve();
        if (!eCCurve.equals(eCCurve2)) {
            throw new IllegalArgumentException("Point must be on the same curve");
        }
        return eCCurve.importPoint(eCPoint);
    }

    public static void montgomeryTrick(ECFieldElement[] eCFieldElementArray, int n, int n2) {
        ECAlgorithms.montgomeryTrick(eCFieldElementArray, n, n2, null);
    }

    public static void montgomeryTrick(ECFieldElement[] eCFieldElementArray, int n, int n2, ECFieldElement eCFieldElement) {
        ECFieldElement[] eCFieldElementArray2 = new ECFieldElement[n2];
        eCFieldElementArray2[0] = eCFieldElementArray[n];
        int n3 = 0;
        while (++n3 < n2) {
            eCFieldElementArray2[n3] = eCFieldElementArray2[n3 - 1].multiply(eCFieldElementArray[n + n3]);
        }
        --n3;
        if (eCFieldElement != null) {
            eCFieldElementArray2[n3] = eCFieldElementArray2[n3].multiply(eCFieldElement);
        }
        ECFieldElement eCFieldElement2 = eCFieldElementArray2[n3].invert();
        while (n3 > 0) {
            int n4 = n + n3--;
            ECFieldElement eCFieldElement3 = eCFieldElementArray[n4];
            eCFieldElementArray[n4] = eCFieldElementArray2[n3].multiply(eCFieldElement2);
            eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement3);
        }
        eCFieldElementArray[n] = eCFieldElement2;
    }

    public static ECPoint referenceMultiply(ECPoint eCPoint, BigInteger bigInteger) {
        BigInteger bigInteger2 = bigInteger.abs();
        ECPoint eCPoint2 = eCPoint.getCurve().getInfinity();
        int n = bigInteger2.bitLength();
        if (n > 0) {
            if (bigInteger2.testBit(0)) {
                eCPoint2 = eCPoint;
            }
            for (int i = 1; i < n; ++i) {
                eCPoint = eCPoint.twice();
                if (!bigInteger2.testBit(i)) continue;
                eCPoint2 = eCPoint2.add(eCPoint);
            }
        }
        return bigInteger.signum() < 0 ? eCPoint2.negate() : eCPoint2;
    }

    public static ECPoint validatePoint(ECPoint eCPoint) {
        if (!eCPoint.isValid()) {
            throw new IllegalArgumentException("Invalid point");
        }
        return eCPoint;
    }

    static ECPoint implShamirsTrickJsf(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECCurve eCCurve = eCPoint.getCurve();
        ECPoint eCPoint3 = eCCurve.getInfinity();
        ECPoint eCPoint4 = eCPoint.add(eCPoint2);
        ECPoint eCPoint5 = eCPoint.subtract(eCPoint2);
        ECPoint[] eCPointArray = new ECPoint[]{eCPoint2, eCPoint5, eCPoint, eCPoint4};
        eCCurve.normalizeAll(eCPointArray);
        ECPoint[] eCPointArray2 = new ECPoint[]{eCPointArray[3].negate(), eCPointArray[2].negate(), eCPointArray[1].negate(), eCPointArray[0].negate(), eCPoint3, eCPointArray[0], eCPointArray[1], eCPointArray[2], eCPointArray[3]};
        byte[] byArray = WNafUtil.generateJSF(bigInteger, bigInteger2);
        ECPoint eCPoint6 = eCPoint3;
        int n = byArray.length;
        while (--n >= 0) {
            byte by = byArray[n];
            int n2 = by << 24 >> 28;
            int n3 = by << 28 >> 28;
            int n4 = 4 + n2 * 3 + n3;
            eCPoint6 = eCPoint6.twicePlus(eCPointArray2[n4]);
        }
        return eCPoint6;
    }

    static ECPoint implShamirsTrickWNaf(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        boolean bl = bigInteger.signum() < 0;
        boolean bl2 = bigInteger2.signum() < 0;
        bigInteger = bigInteger.abs();
        bigInteger2 = bigInteger2.abs();
        int n = Math.max(2, Math.min(16, WNafUtil.getWindowSize(bigInteger.bitLength())));
        int n2 = Math.max(2, Math.min(16, WNafUtil.getWindowSize(bigInteger2.bitLength())));
        WNafPreCompInfo wNafPreCompInfo = WNafUtil.precompute(eCPoint, n, true);
        WNafPreCompInfo wNafPreCompInfo2 = WNafUtil.precompute(eCPoint2, n2, true);
        ECPoint[] eCPointArray = bl ? wNafPreCompInfo.getPreCompNeg() : wNafPreCompInfo.getPreComp();
        ECPoint[] eCPointArray2 = bl2 ? wNafPreCompInfo2.getPreCompNeg() : wNafPreCompInfo2.getPreComp();
        ECPoint[] eCPointArray3 = bl ? wNafPreCompInfo.getPreComp() : wNafPreCompInfo.getPreCompNeg();
        ECPoint[] eCPointArray4 = bl2 ? wNafPreCompInfo2.getPreComp() : wNafPreCompInfo2.getPreCompNeg();
        byte[] byArray = WNafUtil.generateWindowNaf(n, bigInteger);
        byte[] byArray2 = WNafUtil.generateWindowNaf(n2, bigInteger2);
        return ECAlgorithms.implShamirsTrickWNaf(eCPointArray, eCPointArray3, byArray, eCPointArray2, eCPointArray4, byArray2);
    }

    static ECPoint implShamirsTrickWNaf(ECPoint eCPoint, BigInteger bigInteger, ECPointMap eCPointMap, BigInteger bigInteger2) {
        boolean bl = bigInteger.signum() < 0;
        boolean bl2 = bigInteger2.signum() < 0;
        bigInteger = bigInteger.abs();
        bigInteger2 = bigInteger2.abs();
        int n = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(bigInteger.bitLength(), bigInteger2.bitLength()))));
        ECPoint eCPoint2 = WNafUtil.mapPointWithPrecomp(eCPoint, n, true, eCPointMap);
        WNafPreCompInfo wNafPreCompInfo = WNafUtil.getWNafPreCompInfo(eCPoint);
        WNafPreCompInfo wNafPreCompInfo2 = WNafUtil.getWNafPreCompInfo(eCPoint2);
        ECPoint[] eCPointArray = bl ? wNafPreCompInfo.getPreCompNeg() : wNafPreCompInfo.getPreComp();
        ECPoint[] eCPointArray2 = bl2 ? wNafPreCompInfo2.getPreCompNeg() : wNafPreCompInfo2.getPreComp();
        ECPoint[] eCPointArray3 = bl ? wNafPreCompInfo.getPreComp() : wNafPreCompInfo.getPreCompNeg();
        ECPoint[] eCPointArray4 = bl2 ? wNafPreCompInfo2.getPreComp() : wNafPreCompInfo2.getPreCompNeg();
        byte[] byArray = WNafUtil.generateWindowNaf(n, bigInteger);
        byte[] byArray2 = WNafUtil.generateWindowNaf(n, bigInteger2);
        return ECAlgorithms.implShamirsTrickWNaf(eCPointArray, eCPointArray3, byArray, eCPointArray2, eCPointArray4, byArray2);
    }

    private static ECPoint implShamirsTrickWNaf(ECPoint[] eCPointArray, ECPoint[] eCPointArray2, byte[] byArray, ECPoint[] eCPointArray3, ECPoint[] eCPointArray4, byte[] byArray2) {
        ECPoint eCPoint;
        int n = Math.max(byArray.length, byArray2.length);
        ECCurve eCCurve = eCPointArray[0].getCurve();
        ECPoint eCPoint2 = eCPoint = eCCurve.getInfinity();
        int n2 = 0;
        for (int i = n - 1; i >= 0; --i) {
            ECPoint[] eCPointArray5;
            int n3;
            byte by;
            byte by2 = i < byArray.length ? byArray[i] : (byte)0;
            byte by3 = by = i < byArray2.length ? byArray2[i] : (byte)0;
            if ((by2 | by) == 0) {
                ++n2;
                continue;
            }
            ECPoint eCPoint3 = eCPoint;
            if (by2 != 0) {
                n3 = Math.abs(by2);
                eCPointArray5 = by2 < 0 ? eCPointArray2 : eCPointArray;
                eCPoint3 = eCPoint3.add(eCPointArray5[n3 >>> 1]);
            }
            if (by != 0) {
                n3 = Math.abs(by);
                eCPointArray5 = by < 0 ? eCPointArray4 : eCPointArray3;
                eCPoint3 = eCPoint3.add(eCPointArray5[n3 >>> 1]);
            }
            if (n2 > 0) {
                eCPoint2 = eCPoint2.timesPow2(n2);
                n2 = 0;
            }
            eCPoint2 = eCPoint2.twicePlus(eCPoint3);
        }
        if (n2 > 0) {
            eCPoint2 = eCPoint2.timesPow2(n2);
        }
        return eCPoint2;
    }

    static ECPoint implSumOfMultiplies(ECPoint[] eCPointArray, BigInteger[] bigIntegerArray) {
        int n = eCPointArray.length;
        boolean[] blArray = new boolean[n];
        WNafPreCompInfo[] wNafPreCompInfoArray = new WNafPreCompInfo[n];
        byte[][] byArrayArray = new byte[n][];
        for (int i = 0; i < n; ++i) {
            BigInteger bigInteger = bigIntegerArray[i];
            blArray[i] = bigInteger.signum() < 0;
            bigInteger = bigInteger.abs();
            int n2 = Math.max(2, Math.min(16, WNafUtil.getWindowSize(bigInteger.bitLength())));
            wNafPreCompInfoArray[i] = WNafUtil.precompute(eCPointArray[i], n2, true);
            byArrayArray[i] = WNafUtil.generateWindowNaf(n2, bigInteger);
        }
        return ECAlgorithms.implSumOfMultiplies(blArray, wNafPreCompInfoArray, byArrayArray);
    }

    static ECPoint implSumOfMultipliesGLV(ECPoint[] eCPointArray, BigInteger[] bigIntegerArray, GLVEndomorphism gLVEndomorphism) {
        BigInteger bigInteger = eCPointArray[0].getCurve().getOrder();
        int n = eCPointArray.length;
        BigInteger[] bigIntegerArray2 = new BigInteger[n << 1];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            BigInteger[] bigIntegerArray3 = gLVEndomorphism.decomposeScalar(bigIntegerArray[i].mod(bigInteger));
            bigIntegerArray2[n2++] = bigIntegerArray3[0];
            bigIntegerArray2[n2++] = bigIntegerArray3[1];
        }
        ECPointMap eCPointMap = gLVEndomorphism.getPointMap();
        if (gLVEndomorphism.hasEfficientPointMap()) {
            return ECAlgorithms.implSumOfMultiplies(eCPointArray, eCPointMap, bigIntegerArray2);
        }
        ECPoint[] eCPointArray2 = new ECPoint[n << 1];
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            ECPoint eCPoint = eCPointArray[i];
            ECPoint eCPoint2 = eCPointMap.map(eCPoint);
            eCPointArray2[n3++] = eCPoint;
            eCPointArray2[n3++] = eCPoint2;
        }
        return ECAlgorithms.implSumOfMultiplies(eCPointArray2, bigIntegerArray2);
    }

    static ECPoint implSumOfMultiplies(ECPoint[] eCPointArray, ECPointMap eCPointMap, BigInteger[] bigIntegerArray) {
        int n = eCPointArray.length;
        int n2 = n << 1;
        boolean[] blArray = new boolean[n2];
        WNafPreCompInfo[] wNafPreCompInfoArray = new WNafPreCompInfo[n2];
        byte[][] byArrayArray = new byte[n2][];
        for (int i = 0; i < n; ++i) {
            int n3 = i << 1;
            int n4 = n3 + 1;
            BigInteger bigInteger = bigIntegerArray[n3];
            blArray[n3] = bigInteger.signum() < 0;
            bigInteger = bigInteger.abs();
            BigInteger bigInteger2 = bigIntegerArray[n4];
            blArray[n4] = bigInteger2.signum() < 0;
            bigInteger2 = bigInteger2.abs();
            int n5 = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(bigInteger.bitLength(), bigInteger2.bitLength()))));
            ECPoint eCPoint = eCPointArray[i];
            ECPoint eCPoint2 = WNafUtil.mapPointWithPrecomp(eCPoint, n5, true, eCPointMap);
            wNafPreCompInfoArray[n3] = WNafUtil.getWNafPreCompInfo(eCPoint);
            wNafPreCompInfoArray[n4] = WNafUtil.getWNafPreCompInfo(eCPoint2);
            byArrayArray[n3] = WNafUtil.generateWindowNaf(n5, bigInteger);
            byArrayArray[n4] = WNafUtil.generateWindowNaf(n5, bigInteger2);
        }
        return ECAlgorithms.implSumOfMultiplies(blArray, wNafPreCompInfoArray, byArrayArray);
    }

    private static ECPoint implSumOfMultiplies(boolean[] blArray, WNafPreCompInfo[] wNafPreCompInfoArray, byte[][] byArray) {
        ECPoint eCPoint;
        int n = 0;
        int n2 = byArray.length;
        for (int i = 0; i < n2; ++i) {
            n = Math.max(n, byArray[i].length);
        }
        ECCurve eCCurve = wNafPreCompInfoArray[0].getPreComp()[0].getCurve();
        ECPoint eCPoint2 = eCPoint = eCCurve.getInfinity();
        int n3 = 0;
        for (int i = n - 1; i >= 0; --i) {
            ECPoint eCPoint3 = eCPoint;
            for (int j = 0; j < n2; ++j) {
                byte by;
                byte[] byArray2 = byArray[j];
                byte by2 = by = i < byArray2.length ? byArray2[i] : (byte)0;
                if (by == 0) continue;
                int n4 = Math.abs(by);
                WNafPreCompInfo wNafPreCompInfo = wNafPreCompInfoArray[j];
                ECPoint[] eCPointArray = by < 0 == blArray[j] ? wNafPreCompInfo.getPreComp() : wNafPreCompInfo.getPreCompNeg();
                eCPoint3 = eCPoint3.add(eCPointArray[n4 >>> 1]);
            }
            if (eCPoint3 == eCPoint) {
                ++n3;
                continue;
            }
            if (n3 > 0) {
                eCPoint2 = eCPoint2.timesPow2(n3);
                n3 = 0;
            }
            eCPoint2 = eCPoint2.twicePlus(eCPoint3);
        }
        if (n3 > 0) {
            eCPoint2 = eCPoint2.timesPow2(n3);
        }
        return eCPoint2;
    }
}

