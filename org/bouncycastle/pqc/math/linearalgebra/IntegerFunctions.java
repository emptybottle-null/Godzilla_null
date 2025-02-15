/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class IntegerFunctions {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger FOUR = BigInteger.valueOf(4L);
    private static final int[] SMALL_PRIMES = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
    private static final long SMALL_PRIME_PRODUCT = 152125131763605L;
    private static SecureRandom sr = null;
    private static final int[] jacobiTable = new int[]{0, 1, 0, -1, 0, -1, 0, 1};

    private IntegerFunctions() {
    }

    public static int jacobi(BigInteger bigInteger, BigInteger bigInteger2) {
        long l = 1L;
        l = 1L;
        if (bigInteger2.equals(ZERO)) {
            BigInteger bigInteger3 = bigInteger.abs();
            return bigInteger3.equals(ONE) ? 1 : 0;
        }
        if (!bigInteger.testBit(0) && !bigInteger2.testBit(0)) {
            return 0;
        }
        BigInteger bigInteger4 = bigInteger;
        BigInteger bigInteger5 = bigInteger2;
        if (bigInteger5.signum() == -1) {
            bigInteger5 = bigInteger5.negate();
            if (bigInteger4.signum() == -1) {
                l = -1L;
            }
        }
        BigInteger bigInteger6 = ZERO;
        while (!bigInteger5.testBit(0)) {
            bigInteger6 = bigInteger6.add(ONE);
            bigInteger5 = bigInteger5.divide(TWO);
        }
        if (bigInteger6.testBit(0)) {
            l *= (long)jacobiTable[bigInteger4.intValue() & 7];
        }
        if (bigInteger4.signum() < 0) {
            if (bigInteger5.testBit(1)) {
                l = -l;
            }
            bigInteger4 = bigInteger4.negate();
        }
        while (bigInteger4.signum() != 0) {
            bigInteger6 = ZERO;
            while (!bigInteger4.testBit(0)) {
                bigInteger6 = bigInteger6.add(ONE);
                bigInteger4 = bigInteger4.divide(TWO);
            }
            if (bigInteger6.testBit(0)) {
                l *= (long)jacobiTable[bigInteger5.intValue() & 7];
            }
            if (bigInteger4.compareTo(bigInteger5) < 0) {
                BigInteger bigInteger7 = bigInteger4;
                bigInteger4 = bigInteger5;
                bigInteger5 = bigInteger7;
                if (bigInteger4.testBit(1) && bigInteger5.testBit(1)) {
                    l = -l;
                }
            }
            bigInteger4 = bigInteger4.subtract(bigInteger5);
        }
        return bigInteger5.equals(ONE) ? (int)l : 0;
    }

    public static BigInteger ressol(BigInteger bigInteger, BigInteger bigInteger2) throws IllegalArgumentException {
        BigInteger bigInteger3 = null;
        if (bigInteger.compareTo(ZERO) < 0) {
            bigInteger = bigInteger.add(bigInteger2);
        }
        if (bigInteger.equals(ZERO)) {
            return ZERO;
        }
        if (bigInteger2.equals(TWO)) {
            return bigInteger;
        }
        if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
            if (IntegerFunctions.jacobi(bigInteger, bigInteger2) == 1) {
                bigInteger3 = bigInteger2.add(ONE);
                bigInteger3 = bigInteger3.shiftRight(2);
                return bigInteger.modPow(bigInteger3, bigInteger2);
            }
            throw new IllegalArgumentException("No quadratic residue: " + bigInteger + ", " + bigInteger2);
        }
        long l = 0L;
        BigInteger bigInteger4 = bigInteger2.subtract(ONE);
        long l2 = 0L;
        while (!bigInteger4.testBit(0)) {
            ++l2;
            bigInteger4 = bigInteger4.shiftRight(1);
        }
        bigInteger4 = bigInteger4.subtract(ONE);
        bigInteger4 = bigInteger4.shiftRight(1);
        BigInteger bigInteger5 = bigInteger.modPow(bigInteger4, bigInteger2);
        BigInteger bigInteger6 = bigInteger5.multiply(bigInteger5).remainder(bigInteger2);
        bigInteger6 = bigInteger6.multiply(bigInteger).remainder(bigInteger2);
        bigInteger5 = bigInteger5.multiply(bigInteger).remainder(bigInteger2);
        if (bigInteger6.equals(ONE)) {
            return bigInteger5;
        }
        BigInteger bigInteger7 = TWO;
        while (IntegerFunctions.jacobi(bigInteger7, bigInteger2) == 1) {
            bigInteger7 = bigInteger7.add(ONE);
        }
        bigInteger3 = bigInteger4;
        bigInteger3 = bigInteger3.multiply(TWO);
        bigInteger3 = bigInteger3.add(ONE);
        BigInteger bigInteger8 = bigInteger7.modPow(bigInteger3, bigInteger2);
        while (bigInteger6.compareTo(ONE) == 1) {
            bigInteger4 = bigInteger6;
            l = l2;
            l2 = 0L;
            while (!bigInteger4.equals(ONE)) {
                bigInteger4 = bigInteger4.multiply(bigInteger4).mod(bigInteger2);
                ++l2;
            }
            if ((l -= l2) == 0L) {
                throw new IllegalArgumentException("No quadratic residue: " + bigInteger + ", " + bigInteger2);
            }
            bigInteger3 = ONE;
            for (long i = 0L; i < l - 1L; ++i) {
                bigInteger3 = bigInteger3.shiftLeft(1);
            }
            bigInteger8 = bigInteger8.modPow(bigInteger3, bigInteger2);
            bigInteger5 = bigInteger5.multiply(bigInteger8).remainder(bigInteger2);
            bigInteger8 = bigInteger8.multiply(bigInteger8).remainder(bigInteger2);
            bigInteger6 = bigInteger6.multiply(bigInteger8).mod(bigInteger2);
        }
        return bigInteger5;
    }

    public static int gcd(int n, int n2) {
        return BigInteger.valueOf(n).gcd(BigInteger.valueOf(n2)).intValue();
    }

    public static int[] extGCD(int n, int n2) {
        BigInteger bigInteger = BigInteger.valueOf(n);
        BigInteger bigInteger2 = BigInteger.valueOf(n2);
        BigInteger[] bigIntegerArray = IntegerFunctions.extgcd(bigInteger, bigInteger2);
        int[] nArray = new int[]{bigIntegerArray[0].intValue(), bigIntegerArray[1].intValue(), bigIntegerArray[2].intValue()};
        return nArray;
    }

    public static BigInteger divideAndRound(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() < 0) {
            return IntegerFunctions.divideAndRound(bigInteger.negate(), bigInteger2).negate();
        }
        if (bigInteger2.signum() < 0) {
            return IntegerFunctions.divideAndRound(bigInteger, bigInteger2.negate()).negate();
        }
        return bigInteger.shiftLeft(1).add(bigInteger2).divide(bigInteger2.shiftLeft(1));
    }

    public static BigInteger[] divideAndRound(BigInteger[] bigIntegerArray, BigInteger bigInteger) {
        BigInteger[] bigIntegerArray2 = new BigInteger[bigIntegerArray.length];
        for (int i = 0; i < bigIntegerArray.length; ++i) {
            bigIntegerArray2[i] = IntegerFunctions.divideAndRound(bigIntegerArray[i], bigInteger);
        }
        return bigIntegerArray2;
    }

    public static int ceilLog(BigInteger bigInteger) {
        int n = 0;
        BigInteger bigInteger2 = ONE;
        while (bigInteger2.compareTo(bigInteger) < 0) {
            ++n;
            bigInteger2 = bigInteger2.shiftLeft(1);
        }
        return n;
    }

    public static int ceilLog(int n) {
        int n2 = 0;
        int n3 = 1;
        while (n3 < n) {
            n3 <<= 1;
            ++n2;
        }
        return n2;
    }

    public static int ceilLog256(int n) {
        if (n == 0) {
            return 1;
        }
        int n2 = 0;
        for (int i = n < 0 ? -n : n; i > 0; i >>>= 8) {
            ++n2;
        }
        return n2;
    }

    public static int ceilLog256(long l) {
        if (l == 0L) {
            return 1;
        }
        int n = 0;
        for (long i = l < 0L ? -l : l; i > 0L; i >>>= 8) {
            ++n;
        }
        return n;
    }

    public static int floorLog(BigInteger bigInteger) {
        int n = -1;
        BigInteger bigInteger2 = ONE;
        while (bigInteger2.compareTo(bigInteger) <= 0) {
            ++n;
            bigInteger2 = bigInteger2.shiftLeft(1);
        }
        return n;
    }

    public static int floorLog(int n) {
        int n2 = 0;
        if (n <= 0) {
            return -1;
        }
        for (int i = n >>> 1; i > 0; i >>>= 1) {
            ++n2;
        }
        return n2;
    }

    public static int maxPower(int n) {
        int n2 = 0;
        if (n != 0) {
            int n3 = 1;
            while ((n & n3) == 0) {
                ++n2;
                n3 <<= 1;
            }
        }
        return n2;
    }

    public static int bitCount(int n) {
        int n2 = 0;
        while (n != 0) {
            n2 += n & 1;
            n >>>= 1;
        }
        return n2;
    }

    public static int order(int n, int n2) {
        int n3 = n % n2;
        int n4 = 1;
        if (n3 == 0) {
            throw new IllegalArgumentException(n + " is not an element of Z/(" + n2 + "Z)^*; it is not meaningful to compute its order.");
        }
        while (n3 != 1) {
            n3 *= n;
            if ((n3 %= n2) < 0) {
                n3 += n2;
            }
            ++n4;
        }
        return n4;
    }

    public static BigInteger reduceInto(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger.subtract(bigInteger2).mod(bigInteger3.subtract(bigInteger2)).add(bigInteger2);
    }

    public static int pow(int n, int n2) {
        int n3 = 1;
        while (n2 > 0) {
            if ((n2 & 1) == 1) {
                n3 *= n;
            }
            n *= n;
            n2 >>>= 1;
        }
        return n3;
    }

    public static long pow(long l, int n) {
        long l2 = 1L;
        while (n > 0) {
            if ((n & 1) == 1) {
                l2 *= l;
            }
            l *= l;
            n >>>= 1;
        }
        return l2;
    }

    public static int modPow(int n, int n2, int n3) {
        if (n3 <= 0 || n3 * n3 > Integer.MAX_VALUE || n2 < 0) {
            return 0;
        }
        int n4 = 1;
        n = (n % n3 + n3) % n3;
        while (n2 > 0) {
            if ((n2 & 1) == 1) {
                n4 = n4 * n % n3;
            }
            n = n * n % n3;
            n2 >>>= 1;
        }
        return n4;
    }

    public static BigInteger[] extgcd(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ONE;
        BigInteger bigInteger4 = ZERO;
        BigInteger bigInteger5 = bigInteger;
        if (bigInteger2.signum() != 0) {
            BigInteger bigInteger6 = ZERO;
            BigInteger bigInteger7 = bigInteger2;
            while (bigInteger7.signum() != 0) {
                BigInteger[] bigIntegerArray = bigInteger5.divideAndRemainder(bigInteger7);
                BigInteger bigInteger8 = bigIntegerArray[0];
                BigInteger bigInteger9 = bigIntegerArray[1];
                BigInteger bigInteger10 = bigInteger3.subtract(bigInteger8.multiply(bigInteger6));
                bigInteger3 = bigInteger6;
                bigInteger5 = bigInteger7;
                bigInteger6 = bigInteger10;
                bigInteger7 = bigInteger9;
            }
            bigInteger4 = bigInteger5.subtract(bigInteger.multiply(bigInteger3)).divide(bigInteger2);
        }
        return new BigInteger[]{bigInteger5, bigInteger3, bigInteger4};
    }

    public static BigInteger leastCommonMultiple(BigInteger[] bigIntegerArray) {
        int n = bigIntegerArray.length;
        BigInteger bigInteger = bigIntegerArray[0];
        for (int i = 1; i < n; ++i) {
            BigInteger bigInteger2 = bigInteger.gcd(bigIntegerArray[i]);
            bigInteger = bigInteger.multiply(bigIntegerArray[i]).divide(bigInteger2);
        }
        return bigInteger;
    }

    public static long mod(long l, long l2) {
        long l3 = l % l2;
        if (l3 < 0L) {
            l3 += l2;
        }
        return l3;
    }

    public static int modInverse(int n, int n2) {
        return BigInteger.valueOf(n).modInverse(BigInteger.valueOf(n2)).intValue();
    }

    public static long modInverse(long l, long l2) {
        return BigInteger.valueOf(l).modInverse(BigInteger.valueOf(l2)).longValue();
    }

    public static int isPower(int n, int n2) {
        if (n <= 0) {
            return -1;
        }
        int n3 = 0;
        int n4 = n;
        while (n4 > 1) {
            if (n4 % n2 != 0) {
                return -1;
            }
            n4 /= n2;
            ++n3;
        }
        return n3;
    }

    public static int leastDiv(int n) {
        if (n < 0) {
            n = -n;
        }
        if (n == 0) {
            return 1;
        }
        if ((n & 1) == 0) {
            return 2;
        }
        for (int i = 3; i <= n / i; i += 2) {
            if (n % i != 0) continue;
            return i;
        }
        return n;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if ((n & 1) == 0) {
            return false;
        }
        if (n < 42) {
            for (int i = 0; i < SMALL_PRIMES.length; ++i) {
                if (n != SMALL_PRIMES[i]) continue;
                return true;
            }
        }
        if (n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0 || n % 17 == 0 || n % 19 == 0 || n % 23 == 0 || n % 29 == 0 || n % 31 == 0 || n % 37 == 0 || n % 41 == 0) {
            return false;
        }
        return BigInteger.valueOf(n).isProbablePrime(20);
    }

    public static boolean passesSmallPrimeTest(BigInteger bigInteger) {
        int[] nArray = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499};
        for (int i = 0; i < nArray.length; ++i) {
            if (!bigInteger.mod(BigInteger.valueOf(nArray[i])).equals(ZERO)) continue;
            return false;
        }
        return true;
    }

    public static int nextSmallerPrime(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        n = (n & 1) == 0 ? --n : (n -= 2);
        while (n > 3 & !IntegerFunctions.isPrime(n)) {
            n -= 2;
        }
        return n;
    }

    public static BigInteger nextProbablePrime(BigInteger bigInteger, int n) {
        if (bigInteger.signum() < 0 || bigInteger.signum() == 0 || bigInteger.equals(ONE)) {
            return TWO;
        }
        BigInteger bigInteger2 = bigInteger.add(ONE);
        if (!bigInteger2.testBit(0)) {
            bigInteger2 = bigInteger2.add(ONE);
        }
        while (true) {
            long l;
            if (bigInteger2.bitLength() > 6 && ((l = bigInteger2.remainder(BigInteger.valueOf(152125131763605L)).longValue()) % 3L == 0L || l % 5L == 0L || l % 7L == 0L || l % 11L == 0L || l % 13L == 0L || l % 17L == 0L || l % 19L == 0L || l % 23L == 0L || l % 29L == 0L || l % 31L == 0L || l % 37L == 0L || l % 41L == 0L)) {
                bigInteger2 = bigInteger2.add(TWO);
                continue;
            }
            if (bigInteger2.bitLength() < 4) {
                return bigInteger2;
            }
            if (bigInteger2.isProbablePrime(n)) {
                return bigInteger2;
            }
            bigInteger2 = bigInteger2.add(TWO);
        }
    }

    public static BigInteger nextProbablePrime(BigInteger bigInteger) {
        return IntegerFunctions.nextProbablePrime(bigInteger, 20);
    }

    public static BigInteger nextPrime(long l) {
        boolean bl = false;
        long l2 = 0L;
        if (l <= 1L) {
            return BigInteger.valueOf(2L);
        }
        if (l == 2L) {
            return BigInteger.valueOf(3L);
        }
        for (long i = l + 1L + (l & 1L); i <= l << 1 && !bl; i += 2L) {
            for (long j = 3L; j <= i >> 1 && !bl; j += 2L) {
                if (i % j != 0L) continue;
                bl = true;
            }
            if (bl) {
                bl = false;
                continue;
            }
            l2 = i;
            bl = true;
        }
        return BigInteger.valueOf(l2);
    }

    public static BigInteger binomial(int n, int n2) {
        BigInteger bigInteger = ONE;
        if (n == 0) {
            if (n2 == 0) {
                return bigInteger;
            }
            return ZERO;
        }
        if (n2 > n >>> 1) {
            n2 = n - n2;
        }
        for (int i = 1; i <= n2; ++i) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(n - (i - 1))).divide(BigInteger.valueOf(i));
        }
        return bigInteger;
    }

    public static BigInteger randomize(BigInteger bigInteger) {
        if (sr == null) {
            sr = new SecureRandom();
        }
        return IntegerFunctions.randomize(bigInteger, sr);
    }

    public static BigInteger randomize(BigInteger bigInteger, SecureRandom secureRandom) {
        int n = bigInteger.bitLength();
        BigInteger bigInteger2 = BigInteger.valueOf(0L);
        if (secureRandom == null) {
            secureRandom = sr != null ? sr : new SecureRandom();
        }
        for (int i = 0; i < 20; ++i) {
            bigInteger2 = new BigInteger(n, secureRandom);
            if (bigInteger2.compareTo(bigInteger) >= 0) continue;
            return bigInteger2;
        }
        return bigInteger2.mod(bigInteger);
    }

    public static BigInteger squareRoot(BigInteger bigInteger) {
        if (bigInteger.compareTo(ZERO) < 0) {
            throw new ArithmeticException("cannot extract root of negative number" + bigInteger + ".");
        }
        int n = bigInteger.bitLength();
        BigInteger bigInteger2 = ZERO;
        BigInteger bigInteger3 = ZERO;
        if ((n & 1) != 0) {
            bigInteger2 = bigInteger2.add(ONE);
            --n;
        }
        while (n > 0) {
            bigInteger3 = bigInteger3.multiply(FOUR);
            bigInteger3 = bigInteger3.add(BigInteger.valueOf((bigInteger.testBit(--n) ? 2 : 0) + (bigInteger.testBit(--n) ? 1 : 0)));
            BigInteger bigInteger4 = bigInteger2.multiply(FOUR).add(ONE);
            bigInteger2 = bigInteger2.multiply(TWO);
            if (bigInteger3.compareTo(bigInteger4) == -1) continue;
            bigInteger2 = bigInteger2.add(ONE);
            bigInteger3 = bigInteger3.subtract(bigInteger4);
        }
        return bigInteger2;
    }

    public static float intRoot(int n, int n2) {
        float f = n / n2;
        float f2 = 0.0f;
        int n3 = 0;
        while ((double)Math.abs(f2 - f) > 1.0E-4) {
            float f3 = IntegerFunctions.floatPow(f, n2);
            while (Float.isInfinite(f3)) {
                f = (f + f2) / 2.0f;
                f3 = IntegerFunctions.floatPow(f, n2);
            }
            ++n3;
            f2 = f;
            f = f2 - (f3 - (float)n) / ((float)n2 * IntegerFunctions.floatPow(f2, n2 - 1));
        }
        return f;
    }

    public static float floatPow(float f, int n) {
        float f2 = 1.0f;
        while (n > 0) {
            f2 *= f;
            --n;
        }
        return f2;
    }

    public static double log(double d) {
        if (d > 0.0 && d < 1.0) {
            double d2 = 1.0 / d;
            double d3 = -IntegerFunctions.log(d2);
            return d3;
        }
        int n = 0;
        double d4 = 1.0;
        double d5 = d;
        while (d5 > 2.0) {
            d5 /= 2.0;
            ++n;
            d4 *= 2.0;
        }
        double d6 = d / d4;
        d6 = IntegerFunctions.logBKM(d6);
        return (double)n + d6;
    }

    public static double log(long l) {
        int n = IntegerFunctions.floorLog(BigInteger.valueOf(l));
        long l2 = 1 << n;
        double d = (double)l / (double)l2;
        d = IntegerFunctions.logBKM(d);
        return (double)n + d;
    }

    private static double logBKM(double d) {
        double[] dArray = new double[]{1.0, 0.5849625007211562, 0.32192809488736235, 0.16992500144231237, 0.0874628412503394, 0.044394119358453436, 0.02236781302845451, 0.01122725542325412, 0.005624549193878107, 0.0028150156070540383, 0.0014081943928083889, 7.042690112466433E-4, 3.5217748030102726E-4, 1.7609948644250602E-4, 8.80524301221769E-5, 4.4026886827316716E-5, 2.2013611360340496E-5, 1.1006847667481442E-5, 5.503434330648604E-6, 2.751719789561283E-6, 1.375860550841138E-6, 6.879304394358497E-7, 3.4396526072176454E-7, 1.7198264061184464E-7, 8.599132286866321E-8, 4.299566207501687E-8, 2.1497831197679756E-8, 1.0748915638882709E-8, 5.374457829452062E-9, 2.687228917228708E-9, 1.3436144592400231E-9, 6.718072297764289E-10, 3.3590361492731876E-10, 1.6795180747343547E-10, 8.397590373916176E-11, 4.1987951870191886E-11, 2.0993975935248694E-11, 1.0496987967662534E-11, 5.2484939838408146E-12, 2.624246991922794E-12, 1.3121234959619935E-12, 6.56061747981146E-13, 3.2803087399061026E-13, 1.6401543699531447E-13, 8.200771849765956E-14, 4.1003859248830365E-14, 2.0501929624415328E-14, 1.02509648122077E-14, 5.1254824061038595E-15, 2.5627412030519317E-15, 1.2813706015259665E-15, 6.406853007629834E-16, 3.203426503814917E-16, 1.6017132519074588E-16, 8.008566259537294E-17, 4.004283129768647E-17, 2.0021415648843235E-17, 1.0010707824421618E-17, 5.005353912210809E-18, 2.5026769561054044E-18, 1.2513384780527022E-18, 6.256692390263511E-19, 3.1283461951317555E-19, 1.5641730975658778E-19, 7.820865487829389E-20, 3.9104327439146944E-20, 1.9552163719573472E-20, 9.776081859786736E-21, 4.888040929893368E-21, 2.444020464946684E-21, 1.222010232473342E-21, 6.11005116236671E-22, 3.055025581183355E-22, 1.5275127905916775E-22, 7.637563952958387E-23, 3.818781976479194E-23, 1.909390988239597E-23, 9.546954941197984E-24, 4.773477470598992E-24, 2.386738735299496E-24, 1.193369367649748E-24, 5.96684683824874E-25, 2.98342341912437E-25, 1.491711709562185E-25, 7.458558547810925E-26, 3.7292792739054626E-26, 1.8646396369527313E-26, 9.323198184763657E-27, 4.661599092381828E-27, 2.330799546190914E-27, 1.165399773095457E-27, 5.826998865477285E-28, 2.9134994327386427E-28, 1.4567497163693213E-28, 7.283748581846607E-29, 3.6418742909233034E-29, 1.8209371454616517E-29, 9.104685727308258E-30, 4.552342863654129E-30, 2.2761714318270646E-30};
        int n = 53;
        double d2 = 1.0;
        double d3 = 0.0;
        double d4 = 1.0;
        for (int i = 0; i < n; ++i) {
            double d5 = d2 + d2 * d4;
            if (d5 <= d) {
                d2 = d5;
                d3 += dArray[i];
            }
            d4 *= 0.5;
        }
        return d3;
    }

    public static boolean isIncreasing(int[] nArray) {
        for (int i = 1; i < nArray.length; ++i) {
            if (nArray[i - 1] < nArray[i]) continue;
            System.out.println("a[" + (i - 1) + "] = " + nArray[i - 1] + " >= " + nArray[i] + " = a[" + i + "]");
            return false;
        }
        return true;
    }

    public static byte[] integerToOctets(BigInteger bigInteger) {
        byte[] byArray = bigInteger.abs().toByteArray();
        if ((bigInteger.bitLength() & 7) != 0) {
            return byArray;
        }
        byte[] byArray2 = new byte[bigInteger.bitLength() >> 3];
        System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
        return byArray2;
    }

    public static BigInteger octetsToInteger(byte[] byArray, int n, int n2) {
        byte[] byArray2 = new byte[n2 + 1];
        byArray2[0] = 0;
        System.arraycopy(byArray, n, byArray2, 1, n2);
        return new BigInteger(byArray2);
    }

    public static BigInteger octetsToInteger(byte[] byArray) {
        return IntegerFunctions.octetsToInteger(byArray, 0, byArray.length);
    }
}

