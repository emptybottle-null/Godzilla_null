/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;

public class DiscoverEndomorphisms {
    private static final int radix = 16;

    public static void main(String[] stringArray) {
        if (stringArray.length < 1) {
            System.err.println("Expected a list of curve names as arguments");
            return;
        }
        for (int i = 0; i < stringArray.length; ++i) {
            DiscoverEndomorphisms.discoverEndomorphisms(stringArray[i]);
        }
    }

    public static void discoverEndomorphisms(X9ECParameters x9ECParameters) {
        if (x9ECParameters == null) {
            throw new NullPointerException("x9");
        }
        ECCurve eCCurve = x9ECParameters.getCurve();
        if (ECAlgorithms.isFpCurve(eCCurve)) {
            BigInteger bigInteger = eCCurve.getField().getCharacteristic();
            if (eCCurve.getA().isZero() && bigInteger.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve has a 'GLV Type B' endomorphism with these parameters:");
                DiscoverEndomorphisms.printGLVTypeBParameters(x9ECParameters);
            }
        }
    }

    private static void discoverEndomorphisms(String string) {
        X9ECParameters x9ECParameters = ECNamedCurveTable.getByName(string);
        if (x9ECParameters == null) {
            System.err.println("Unknown curve: " + string);
            return;
        }
        ECCurve eCCurve = x9ECParameters.getCurve();
        if (ECAlgorithms.isFpCurve(eCCurve)) {
            BigInteger bigInteger = eCCurve.getField().getCharacteristic();
            if (eCCurve.getA().isZero() && bigInteger.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve '" + string + "' has a 'GLV Type B' endomorphism with these parameters:");
                DiscoverEndomorphisms.printGLVTypeBParameters(x9ECParameters);
            }
        }
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters) {
        BigInteger[] bigIntegerArray = DiscoverEndomorphisms.solveQuadraticEquation(x9ECParameters.getN(), ECConstants.ONE, ECConstants.ONE);
        ECFieldElement[] eCFieldElementArray = DiscoverEndomorphisms.findBetaValues(x9ECParameters.getCurve());
        DiscoverEndomorphisms.printGLVTypeBParameters(x9ECParameters, bigIntegerArray[0], eCFieldElementArray);
        System.out.println("OR");
        DiscoverEndomorphisms.printGLVTypeBParameters(x9ECParameters, bigIntegerArray[1], eCFieldElementArray);
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters, BigInteger bigInteger, ECFieldElement[] eCFieldElementArray) {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        Object object;
        ECPoint eCPoint = x9ECParameters.getG().normalize();
        ECPoint eCPoint2 = eCPoint.multiply(bigInteger).normalize();
        if (!eCPoint.getYCoord().equals(eCPoint2.getYCoord())) {
            throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
        }
        ECFieldElement eCFieldElement = eCFieldElementArray[0];
        if (!eCPoint.getXCoord().multiply(eCFieldElement).equals(eCPoint2.getXCoord())) {
            eCFieldElement = eCFieldElementArray[1];
            if (!eCPoint.getXCoord().multiply(eCFieldElement).equals(eCPoint2.getXCoord())) {
                throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
            }
        }
        BigInteger bigInteger5 = x9ECParameters.getN();
        BigInteger[] bigIntegerArray = null;
        BigInteger[] bigIntegerArray2 = null;
        BigInteger[] bigIntegerArray3 = DiscoverEndomorphisms.extEuclidGLV(bigInteger5, bigInteger);
        bigIntegerArray = new BigInteger[]{bigIntegerArray3[2], bigIntegerArray3[3].negate()};
        bigIntegerArray2 = DiscoverEndomorphisms.chooseShortest(new BigInteger[]{bigIntegerArray3[0], bigIntegerArray3[1].negate()}, new BigInteger[]{bigIntegerArray3[4], bigIntegerArray3[5].negate()});
        if (!DiscoverEndomorphisms.isVectorBoundedBySqrt(bigIntegerArray2, bigInteger5) && DiscoverEndomorphisms.areRelativelyPrime(bigIntegerArray[0], bigIntegerArray[1]) && (object = DiscoverEndomorphisms.extEuclidBezout(new BigInteger[]{(bigInteger4 = (bigInteger3 = bigIntegerArray[0]).add((bigInteger2 = bigIntegerArray[1]).multiply(bigInteger)).divide(bigInteger5)).abs(), bigInteger2.abs()})) != null) {
            BigInteger[] bigIntegerArray4;
            BigInteger bigInteger6;
            BigInteger bigInteger7 = object[0];
            BigInteger bigInteger8 = object[1];
            if (bigInteger4.signum() < 0) {
                bigInteger7 = bigInteger7.negate();
            }
            if (bigInteger2.signum() > 0) {
                bigInteger8 = bigInteger8.negate();
            }
            if (!(bigInteger6 = bigInteger4.multiply(bigInteger7).subtract(bigInteger2.multiply(bigInteger8))).equals(ECConstants.ONE)) {
                throw new IllegalStateException();
            }
            BigInteger bigInteger9 = bigInteger8.multiply(bigInteger5).subtract(bigInteger7.multiply(bigInteger));
            BigInteger bigInteger10 = bigInteger7.negate();
            BigInteger bigInteger11 = bigInteger9.negate();
            BigInteger bigInteger12 = DiscoverEndomorphisms.isqrt(bigInteger5.subtract(ECConstants.ONE)).add(ECConstants.ONE);
            BigInteger[] bigIntegerArray5 = DiscoverEndomorphisms.calculateRange(bigInteger10, bigInteger12, bigInteger2);
            BigInteger[] bigIntegerArray6 = DiscoverEndomorphisms.intersect(bigIntegerArray5, bigIntegerArray4 = DiscoverEndomorphisms.calculateRange(bigInteger11, bigInteger12, bigInteger3));
            if (bigIntegerArray6 != null) {
                BigInteger bigInteger13 = bigIntegerArray6[0];
                while (bigInteger13.compareTo(bigIntegerArray6[1]) <= 0) {
                    BigInteger[] bigIntegerArray7 = new BigInteger[]{bigInteger9.add(bigInteger13.multiply(bigInteger3)), bigInteger7.add(bigInteger13.multiply(bigInteger2))};
                    if (DiscoverEndomorphisms.isShorter(bigIntegerArray7, bigIntegerArray2)) {
                        bigIntegerArray2 = bigIntegerArray7;
                    }
                    bigInteger13 = bigInteger13.add(ECConstants.ONE);
                }
            }
        }
        bigInteger3 = bigIntegerArray[0].multiply(bigIntegerArray2[1]).subtract(bigIntegerArray[1].multiply(bigIntegerArray2[0]));
        int n = bigInteger5.bitLength() + 16 - (bigInteger5.bitLength() & 7);
        bigInteger4 = DiscoverEndomorphisms.roundQuotient(bigIntegerArray2[1].shiftLeft(n), bigInteger3);
        object = DiscoverEndomorphisms.roundQuotient(bigIntegerArray[1].shiftLeft(n), bigInteger3).negate();
        DiscoverEndomorphisms.printProperty("Beta", eCFieldElement.toBigInteger().toString(16));
        DiscoverEndomorphisms.printProperty("Lambda", bigInteger.toString(16));
        DiscoverEndomorphisms.printProperty("v1", "{ " + bigIntegerArray[0].toString(16) + ", " + bigIntegerArray[1].toString(16) + " }");
        DiscoverEndomorphisms.printProperty("v2", "{ " + bigIntegerArray2[0].toString(16) + ", " + bigIntegerArray2[1].toString(16) + " }");
        DiscoverEndomorphisms.printProperty("d", bigInteger3.toString(16));
        DiscoverEndomorphisms.printProperty("(OPT) g1", bigInteger4.toString(16));
        DiscoverEndomorphisms.printProperty("(OPT) g2", ((BigInteger)object).toString(16));
        DiscoverEndomorphisms.printProperty("(OPT) bits", Integer.toString(n));
    }

    private static void printProperty(String string, Object object) {
        StringBuffer stringBuffer = new StringBuffer("  ");
        stringBuffer.append(string);
        while (stringBuffer.length() < 20) {
            stringBuffer.append(' ');
        }
        stringBuffer.append("= ");
        stringBuffer.append(object.toString());
        System.out.println(stringBuffer.toString());
    }

    private static boolean areRelativelyPrime(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.gcd(bigInteger2).equals(ECConstants.ONE);
    }

    private static BigInteger[] calculateRange(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        BigInteger bigInteger4 = bigInteger.subtract(bigInteger2).divide(bigInteger3);
        BigInteger bigInteger5 = bigInteger.add(bigInteger2).divide(bigInteger3);
        return DiscoverEndomorphisms.order(bigInteger4, bigInteger5);
    }

    private static BigInteger[] extEuclidBezout(BigInteger[] bigIntegerArray) {
        BigInteger[] bigIntegerArray2;
        boolean bl;
        boolean bl2 = bl = bigIntegerArray[0].compareTo(bigIntegerArray[1]) < 0;
        if (bl) {
            DiscoverEndomorphisms.swap(bigIntegerArray);
        }
        BigInteger bigInteger = bigIntegerArray[0];
        BigInteger bigInteger2 = bigIntegerArray[1];
        BigInteger bigInteger3 = ECConstants.ONE;
        BigInteger bigInteger4 = ECConstants.ZERO;
        BigInteger bigInteger5 = ECConstants.ZERO;
        BigInteger bigInteger6 = ECConstants.ONE;
        while (bigInteger2.compareTo(ECConstants.ONE) > 0) {
            bigIntegerArray2 = bigInteger.divideAndRemainder(bigInteger2);
            BigInteger bigInteger7 = bigIntegerArray2[0];
            BigInteger bigInteger8 = bigIntegerArray2[1];
            BigInteger bigInteger9 = bigInteger3.subtract(bigInteger7.multiply(bigInteger4));
            BigInteger bigInteger10 = bigInteger5.subtract(bigInteger7.multiply(bigInteger6));
            bigInteger = bigInteger2;
            bigInteger2 = bigInteger8;
            bigInteger3 = bigInteger4;
            bigInteger4 = bigInteger9;
            bigInteger5 = bigInteger6;
            bigInteger6 = bigInteger10;
        }
        if (bigInteger2.signum() <= 0) {
            return null;
        }
        bigIntegerArray2 = new BigInteger[]{bigInteger4, bigInteger6};
        if (bl) {
            DiscoverEndomorphisms.swap(bigIntegerArray2);
        }
        return bigIntegerArray2;
    }

    private static BigInteger[] extEuclidGLV(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = bigInteger;
        BigInteger bigInteger4 = bigInteger2;
        BigInteger bigInteger5 = ECConstants.ZERO;
        BigInteger bigInteger6 = ECConstants.ONE;
        while (true) {
            BigInteger[] bigIntegerArray = bigInteger3.divideAndRemainder(bigInteger4);
            BigInteger bigInteger7 = bigIntegerArray[0];
            BigInteger bigInteger8 = bigIntegerArray[1];
            BigInteger bigInteger9 = bigInteger5.subtract(bigInteger7.multiply(bigInteger6));
            if (DiscoverEndomorphisms.isLessThanSqrt(bigInteger4, bigInteger)) {
                return new BigInteger[]{bigInteger3, bigInteger5, bigInteger4, bigInteger6, bigInteger8, bigInteger9};
            }
            bigInteger3 = bigInteger4;
            bigInteger4 = bigInteger8;
            bigInteger5 = bigInteger6;
            bigInteger6 = bigInteger9;
        }
    }

    private static BigInteger[] chooseShortest(BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2) {
        return DiscoverEndomorphisms.isShorter(bigIntegerArray, bigIntegerArray2) ? bigIntegerArray : bigIntegerArray2;
    }

    private static BigInteger[] intersect(BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2) {
        BigInteger bigInteger;
        BigInteger bigInteger2 = bigIntegerArray[0].max(bigIntegerArray2[0]);
        if (bigInteger2.compareTo(bigInteger = bigIntegerArray[1].min(bigIntegerArray2[1])) > 0) {
            return null;
        }
        return new BigInteger[]{bigInteger2, bigInteger};
    }

    private static boolean isLessThanSqrt(BigInteger bigInteger, BigInteger bigInteger2) {
        bigInteger = bigInteger.abs();
        bigInteger2 = bigInteger2.abs();
        int n = bigInteger2.bitLength();
        int n2 = bigInteger.bitLength() * 2;
        int n3 = n2 - 1;
        return n3 <= n && (n2 < n || bigInteger.multiply(bigInteger).compareTo(bigInteger2) < 0);
    }

    private static boolean isShorter(BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2) {
        BigInteger bigInteger;
        boolean bl;
        BigInteger bigInteger2 = bigIntegerArray[0].abs();
        BigInteger bigInteger3 = bigIntegerArray[1].abs();
        BigInteger bigInteger4 = bigIntegerArray2[0].abs();
        BigInteger bigInteger5 = bigIntegerArray2[1].abs();
        boolean bl2 = bigInteger2.compareTo(bigInteger4) < 0;
        boolean bl3 = bl = bigInteger3.compareTo(bigInteger5) < 0;
        if (bl2 == bl) {
            return bl2;
        }
        BigInteger bigInteger6 = bigInteger2.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger3));
        return bigInteger6.compareTo(bigInteger = bigInteger4.multiply(bigInteger4).add(bigInteger5.multiply(bigInteger5))) < 0;
    }

    private static boolean isVectorBoundedBySqrt(BigInteger[] bigIntegerArray, BigInteger bigInteger) {
        BigInteger bigInteger2 = bigIntegerArray[0].abs().max(bigIntegerArray[1].abs());
        return DiscoverEndomorphisms.isLessThanSqrt(bigInteger2, bigInteger);
    }

    private static BigInteger[] order(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.compareTo(bigInteger2) <= 0) {
            return new BigInteger[]{bigInteger, bigInteger2};
        }
        return new BigInteger[]{bigInteger2, bigInteger};
    }

    private static BigInteger roundQuotient(BigInteger bigInteger, BigInteger bigInteger2) {
        boolean bl = bigInteger.signum() != bigInteger2.signum();
        bigInteger = bigInteger.abs();
        bigInteger2 = bigInteger2.abs();
        BigInteger bigInteger3 = bigInteger.add(bigInteger2.shiftRight(1)).divide(bigInteger2);
        return bl ? bigInteger3.negate() : bigInteger3;
    }

    private static BigInteger[] solveQuadraticEquation(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        BigInteger bigInteger4 = bigInteger2.multiply(bigInteger2).subtract(bigInteger3.shiftLeft(2)).mod(bigInteger);
        BigInteger bigInteger5 = new ECFieldElement.Fp(bigInteger, bigInteger4).sqrt().toBigInteger();
        BigInteger bigInteger6 = bigInteger.subtract(bigInteger5);
        if (bigInteger5.testBit(0)) {
            bigInteger6 = bigInteger6.add(bigInteger);
        } else {
            bigInteger5 = bigInteger5.add(bigInteger);
        }
        return new BigInteger[]{bigInteger5.shiftRight(1), bigInteger6.shiftRight(1)};
    }

    private static ECFieldElement[] findBetaValues(ECCurve eCCurve) {
        Object object;
        BigInteger bigInteger;
        BigInteger bigInteger2 = eCCurve.getField().getCharacteristic();
        BigInteger bigInteger3 = bigInteger2.divide(ECConstants.THREE);
        SecureRandom secureRandom = new SecureRandom();
        while ((bigInteger = ((BigInteger)(object = BigIntegers.createRandomInRange(ECConstants.TWO, bigInteger2.subtract(ECConstants.TWO), secureRandom))).modPow(bigInteger3, bigInteger2)).equals(ECConstants.ONE)) {
        }
        object = eCCurve.fromBigInteger(bigInteger);
        return new ECFieldElement[]{object, ((ECFieldElement)object).square()};
    }

    private static BigInteger isqrt(BigInteger bigInteger) {
        BigInteger bigInteger2 = bigInteger.shiftRight(bigInteger.bitLength() / 2);
        BigInteger bigInteger3;
        while (!(bigInteger3 = bigInteger2.add(bigInteger.divide(bigInteger2)).shiftRight(1)).equals(bigInteger2)) {
            bigInteger2 = bigInteger3;
        }
        return bigInteger3;
    }

    private static void swap(BigInteger[] bigIntegerArray) {
        BigInteger bigInteger = bigIntegerArray[0];
        bigIntegerArray[0] = bigIntegerArray[1];
        bigIntegerArray[1] = bigInteger;
    }
}

