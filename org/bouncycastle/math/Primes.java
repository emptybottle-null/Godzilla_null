/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public abstract class Primes {
    public static final int SMALL_FACTOR_LIMIT = 211;
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger THREE = BigInteger.valueOf(3L);

    public static STOutput generateSTRandomPrime(Digest digest, int n, byte[] byArray) {
        if (digest == null) {
            throw new IllegalArgumentException("'hash' cannot be null");
        }
        if (n < 2) {
            throw new IllegalArgumentException("'length' must be >= 2");
        }
        if (byArray == null || byArray.length == 0) {
            throw new IllegalArgumentException("'inputSeed' cannot be null or empty");
        }
        return Primes.implSTRandomPrime(digest, n, Arrays.clone(byArray));
    }

    public static MROutput enhancedMRProbablePrimeTest(BigInteger bigInteger, SecureRandom secureRandom, int n) {
        Primes.checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        }
        if (n < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        if (bigInteger.bitLength() == 2) {
            return MROutput.probablyPrime();
        }
        if (!bigInteger.testBit(0)) {
            return MROutput.provablyCompositeWithFactor(Primes.TWO);
        }
        BigInteger bigInteger2 = bigInteger;
        BigInteger bigInteger3 = bigInteger.subtract(ONE);
        BigInteger bigInteger4 = bigInteger.subtract(TWO);
        int n2 = bigInteger3.getLowestSetBit();
        BigInteger bigInteger5 = bigInteger3.shiftRight(n2);
        for (int i = 0; i < n; ++i) {
            BigInteger bigInteger6 = BigIntegers.createRandomInRange(TWO, bigInteger4, secureRandom);
            BigInteger bigInteger7 = bigInteger6.gcd(bigInteger2);
            if (bigInteger7.compareTo(ONE) > 0) {
                return MROutput.provablyCompositeWithFactor(bigInteger7);
            }
            BigInteger bigInteger8 = bigInteger6.modPow(bigInteger5, bigInteger2);
            if (bigInteger8.equals(ONE) || bigInteger8.equals(bigInteger3)) continue;
            boolean bl = false;
            BigInteger bigInteger9 = bigInteger8;
            for (int j = 1; j < n2; ++j) {
                if ((bigInteger8 = bigInteger8.modPow(TWO, bigInteger2)).equals(bigInteger3)) {
                    bl = true;
                    break;
                }
                if (bigInteger8.equals(ONE)) break;
                bigInteger9 = bigInteger8;
            }
            if (bl) continue;
            if (!bigInteger8.equals(ONE)) {
                bigInteger9 = bigInteger8;
                if (!(bigInteger8 = bigInteger8.modPow(TWO, bigInteger2)).equals(ONE)) {
                    bigInteger9 = bigInteger8;
                }
            }
            if ((bigInteger7 = bigInteger9.subtract(ONE).gcd(bigInteger2)).compareTo(ONE) > 0) {
                return MROutput.provablyCompositeWithFactor(bigInteger7);
            }
            return MROutput.provablyCompositeNotPrimePower();
        }
        return MROutput.probablyPrime();
    }

    public static boolean hasAnySmallFactors(BigInteger bigInteger) {
        Primes.checkCandidate(bigInteger, "candidate");
        return Primes.implHasAnySmallFactors(bigInteger);
    }

    public static boolean isMRProbablePrime(BigInteger bigInteger, SecureRandom secureRandom, int n) {
        Primes.checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        }
        if (n < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        if (bigInteger.bitLength() == 2) {
            return true;
        }
        if (!bigInteger.testBit(0)) {
            return false;
        }
        BigInteger bigInteger2 = bigInteger;
        BigInteger bigInteger3 = bigInteger.subtract(ONE);
        BigInteger bigInteger4 = bigInteger.subtract(TWO);
        int n2 = bigInteger3.getLowestSetBit();
        BigInteger bigInteger5 = bigInteger3.shiftRight(n2);
        for (int i = 0; i < n; ++i) {
            BigInteger bigInteger6 = BigIntegers.createRandomInRange(TWO, bigInteger4, secureRandom);
            if (Primes.implMRProbablePrimeToBase(bigInteger2, bigInteger3, bigInteger5, n2, bigInteger6)) continue;
            return false;
        }
        return true;
    }

    public static boolean isMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2) {
        Primes.checkCandidate(bigInteger, "candidate");
        Primes.checkCandidate(bigInteger2, "base");
        if (bigInteger2.compareTo(bigInteger.subtract(ONE)) >= 0) {
            throw new IllegalArgumentException("'base' must be < ('candidate' - 1)");
        }
        if (bigInteger.bitLength() == 2) {
            return true;
        }
        BigInteger bigInteger3 = bigInteger;
        BigInteger bigInteger4 = bigInteger.subtract(ONE);
        int n = bigInteger4.getLowestSetBit();
        BigInteger bigInteger5 = bigInteger4.shiftRight(n);
        return Primes.implMRProbablePrimeToBase(bigInteger3, bigInteger4, bigInteger5, n, bigInteger2);
    }

    private static void checkCandidate(BigInteger bigInteger, String string) {
        if (bigInteger == null || bigInteger.signum() < 1 || bigInteger.bitLength() < 2) {
            throw new IllegalArgumentException("'" + string + "' must be non-null and >= 2");
        }
    }

    private static boolean implHasAnySmallFactors(BigInteger bigInteger) {
        int n = 223092870;
        int n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 2 == 0 || n2 % 3 == 0 || n2 % 5 == 0 || n2 % 7 == 0 || n2 % 11 == 0 || n2 % 13 == 0 || n2 % 17 == 0 || n2 % 19 == 0 || n2 % 23 == 0) {
            return true;
        }
        n = 58642669;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 29 == 0 || n2 % 31 == 0 || n2 % 37 == 0 || n2 % 41 == 0 || n2 % 43 == 0) {
            return true;
        }
        n = 600662303;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 47 == 0 || n2 % 53 == 0 || n2 % 59 == 0 || n2 % 61 == 0 || n2 % 67 == 0) {
            return true;
        }
        n = 33984931;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 71 == 0 || n2 % 73 == 0 || n2 % 79 == 0 || n2 % 83 == 0) {
            return true;
        }
        n = 89809099;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 89 == 0 || n2 % 97 == 0 || n2 % 101 == 0 || n2 % 103 == 0) {
            return true;
        }
        n = 167375713;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 107 == 0 || n2 % 109 == 0 || n2 % 113 == 0 || n2 % 127 == 0) {
            return true;
        }
        n = 371700317;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 131 == 0 || n2 % 137 == 0 || n2 % 139 == 0 || n2 % 149 == 0) {
            return true;
        }
        n = 645328247;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 151 == 0 || n2 % 157 == 0 || n2 % 163 == 0 || n2 % 167 == 0) {
            return true;
        }
        n = 1070560157;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        if (n2 % 173 == 0 || n2 % 179 == 0 || n2 % 181 == 0 || n2 % 191 == 0) {
            return true;
        }
        n = 1596463769;
        n2 = bigInteger.mod(BigInteger.valueOf(n)).intValue();
        return n2 % 193 == 0 || n2 % 197 == 0 || n2 % 199 == 0 || n2 % 211 == 0;
    }

    private static boolean implMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int n, BigInteger bigInteger4) {
        BigInteger bigInteger5 = bigInteger4.modPow(bigInteger3, bigInteger);
        if (bigInteger5.equals(ONE) || bigInteger5.equals(bigInteger2)) {
            return true;
        }
        boolean bl = false;
        for (int i = 1; i < n; ++i) {
            if ((bigInteger5 = bigInteger5.modPow(TWO, bigInteger)).equals(bigInteger2)) {
                bl = true;
                break;
            }
            if (!bigInteger5.equals(ONE)) continue;
            return false;
        }
        return bl;
    }

    private static STOutput implSTRandomPrime(Digest digest, int n, byte[] byArray) {
        int n2 = digest.getDigestSize();
        if (n < 33) {
            int n3 = 0;
            byte[] byArray2 = new byte[n2];
            byte[] byArray3 = new byte[n2];
            do {
                Primes.hash(digest, byArray, byArray2, 0);
                Primes.inc(byArray, 1);
                Primes.hash(digest, byArray, byArray3, 0);
                Primes.inc(byArray, 1);
                int n4 = Primes.extract32(byArray2) ^ Primes.extract32(byArray3);
                n4 &= -1 >>> 32 - n;
                ++n3;
                long l = (long)(n4 |= 1 << n - 1 | 1) & 0xFFFFFFFFL;
                if (!Primes.isPrime32(l)) continue;
                return new STOutput(BigInteger.valueOf(l), byArray, n3);
            } while (n3 <= 4 * n);
            throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
        }
        STOutput sTOutput = Primes.implSTRandomPrime(digest, (n + 3) / 2, byArray);
        BigInteger bigInteger = sTOutput.getPrime();
        byArray = sTOutput.getPrimeSeed();
        int n5 = sTOutput.getPrimeGenCounter();
        int n6 = 8 * n2;
        int n7 = (n - 1) / n6;
        int n8 = n5;
        BigInteger bigInteger2 = Primes.hashGen(digest, byArray, n7 + 1);
        bigInteger2 = bigInteger2.mod(ONE.shiftLeft(n - 1)).setBit(n - 1);
        BigInteger bigInteger3 = bigInteger.shiftLeft(1);
        BigInteger bigInteger4 = bigInteger2.subtract(ONE).divide(bigInteger3).add(ONE).shiftLeft(1);
        int n9 = 0;
        BigInteger bigInteger5 = bigInteger4.multiply(bigInteger).add(ONE);
        while (true) {
            if (bigInteger5.bitLength() > n) {
                bigInteger4 = ONE.shiftLeft(n - 1).subtract(ONE).divide(bigInteger3).add(ONE).shiftLeft(1);
                bigInteger5 = bigInteger4.multiply(bigInteger).add(ONE);
            }
            ++n5;
            if (!Primes.implHasAnySmallFactors(bigInteger5)) {
                BigInteger bigInteger6 = Primes.hashGen(digest, byArray, n7 + 1);
                bigInteger6 = bigInteger6.mod(bigInteger5.subtract(THREE)).add(TWO);
                bigInteger4 = bigInteger4.add(BigInteger.valueOf(n9));
                n9 = 0;
                BigInteger bigInteger7 = bigInteger6.modPow(bigInteger4, bigInteger5);
                if (bigInteger5.gcd(bigInteger7.subtract(ONE)).equals(ONE) && bigInteger7.modPow(bigInteger, bigInteger5).equals(ONE)) {
                    return new STOutput(bigInteger5, byArray, n5);
                }
            } else {
                Primes.inc(byArray, n7 + 1);
            }
            if (n5 >= 4 * n + n8) {
                throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
            }
            n9 += 2;
            bigInteger5 = bigInteger5.add(bigInteger3);
        }
    }

    private static int extract32(byte[] byArray) {
        int n = 0;
        int n2 = Math.min(4, byArray.length);
        for (int i = 0; i < n2; ++i) {
            int n3 = byArray[byArray.length - (i + 1)] & 0xFF;
            n |= n3 << 8 * i;
        }
        return n;
    }

    private static void hash(Digest digest, byte[] byArray, byte[] byArray2, int n) {
        digest.update(byArray, 0, byArray.length);
        digest.doFinal(byArray2, n);
    }

    private static BigInteger hashGen(Digest digest, byte[] byArray, int n) {
        int n2 = digest.getDigestSize();
        int n3 = n * n2;
        byte[] byArray2 = new byte[n3];
        for (int i = 0; i < n; ++i) {
            Primes.hash(digest, byArray, byArray2, n3 -= n2);
            Primes.inc(byArray, 1);
        }
        return new BigInteger(1, byArray2);
    }

    private static void inc(byte[] byArray, int n) {
        int n2 = byArray.length;
        while (n > 0 && --n2 >= 0) {
            byArray[n2] = (byte)(n += byArray[n2] & 0xFF);
            n >>>= 8;
        }
    }

    private static boolean isPrime32(long l) {
        if (l >>> 32 != 0L) {
            throw new IllegalArgumentException("Size limit exceeded");
        }
        if (l <= 5L) {
            return l == 2L || l == 3L || l == 5L;
        }
        if ((l & 1L) == 0L || l % 3L == 0L || l % 5L == 0L) {
            return false;
        }
        long[] lArray = new long[]{1L, 7L, 11L, 13L, 17L, 19L, 23L, 29L};
        long l2 = 0L;
        int n = 1;
        while (true) {
            if (n < lArray.length) {
                long l3 = l2 + lArray[n];
                if (l % l3 == 0L) {
                    return l < 30L;
                }
                ++n;
                continue;
            }
            if ((l2 += 30L) * l2 >= l) {
                return true;
            }
            n = 0;
        }
    }

    public static class MROutput {
        private boolean provablyComposite;
        private BigInteger factor;

        private static MROutput probablyPrime() {
            return new MROutput(false, null);
        }

        private static MROutput provablyCompositeWithFactor(BigInteger bigInteger) {
            return new MROutput(true, bigInteger);
        }

        private static MROutput provablyCompositeNotPrimePower() {
            return new MROutput(true, null);
        }

        private MROutput(boolean bl, BigInteger bigInteger) {
            this.provablyComposite = bl;
            this.factor = bigInteger;
        }

        public BigInteger getFactor() {
            return this.factor;
        }

        public boolean isProvablyComposite() {
            return this.provablyComposite;
        }

        public boolean isNotPrimePower() {
            return this.provablyComposite && this.factor == null;
        }
    }

    public static class STOutput {
        private BigInteger prime;
        private byte[] primeSeed;
        private int primeGenCounter;

        private STOutput(BigInteger bigInteger, byte[] byArray, int n) {
            this.prime = bigInteger;
            this.primeSeed = byArray;
            this.primeGenCounter = n;
        }

        public BigInteger getPrime() {
            return this.prime;
        }

        public byte[] getPrimeSeed() {
            return this.primeSeed;
        }

        public int getPrimeGenCounter() {
            return this.primeGenCounter;
        }
    }
}

