/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.math.BigInteger;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public final class ArithmeticUtils {
    private ArithmeticUtils() {
    }

    public static int addAndCheck(int x, int y) throws MathArithmeticException {
        long s = (long)x + (long)y;
        if (s < Integer.MIN_VALUE || s > Integer.MAX_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, x, y);
        }
        return (int)s;
    }

    public static long addAndCheck(long a, long b) throws MathArithmeticException {
        return ArithmeticUtils.addAndCheck(a, b, LocalizedFormats.OVERFLOW_IN_ADDITION);
    }

    @Deprecated
    public static long binomialCoefficient(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficient(n, k);
    }

    @Deprecated
    public static double binomialCoefficientDouble(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientDouble(n, k);
    }

    @Deprecated
    public static double binomialCoefficientLog(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientLog(n, k);
    }

    @Deprecated
    public static long factorial(int n) throws NotPositiveException, MathArithmeticException {
        return CombinatoricsUtils.factorial(n);
    }

    @Deprecated
    public static double factorialDouble(int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialDouble(n);
    }

    @Deprecated
    public static double factorialLog(int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialLog(n);
    }

    public static int gcd(int p, int q) throws MathArithmeticException {
        int a = p;
        int b = q;
        if (a == 0 || b == 0) {
            if (a == Integer.MIN_VALUE || b == Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
            }
            return FastMath.abs(a + b);
        }
        long al = a;
        long bl = b;
        boolean useLong = false;
        if (a < 0) {
            if (Integer.MIN_VALUE == a) {
                useLong = true;
            } else {
                a = -a;
            }
            al = -al;
        }
        if (b < 0) {
            if (Integer.MIN_VALUE == b) {
                useLong = true;
            } else {
                b = -b;
            }
            bl = -bl;
        }
        if (useLong) {
            if (al == bl) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
            }
            long blbu = bl;
            bl = al;
            if ((al = blbu % al) == 0L) {
                if (bl > Integer.MAX_VALUE) {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
                }
                return (int)bl;
            }
            blbu = bl;
            b = (int)al;
            a = (int)(blbu % al);
        }
        return ArithmeticUtils.gcdPositive(a, b);
    }

    private static int gcdPositive(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int aTwos = Integer.numberOfTrailingZeros(a);
        a >>= aTwos;
        int bTwos = Integer.numberOfTrailingZeros(b);
        b >>= bTwos;
        int shift = FastMath.min(aTwos, bTwos);
        while (a != b) {
            int delta = a - b;
            b = Math.min(a, b);
            a = Math.abs(delta);
            a >>= Integer.numberOfTrailingZeros(a);
        }
        return a << shift;
    }

    public static long gcd(long p, long q) throws MathArithmeticException {
        long t;
        int k;
        long u = p;
        long v = q;
        if (u == 0L || v == 0L) {
            if (u == Long.MIN_VALUE || v == Long.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, p, q);
            }
            return FastMath.abs(u) + FastMath.abs(v);
        }
        if (u > 0L) {
            u = -u;
        }
        if (v > 0L) {
            v = -v;
        }
        for (k = 0; (u & 1L) == 0L && (v & 1L) == 0L && k < 63; ++k) {
            u /= 2L;
            v /= 2L;
        }
        if (k == 63) {
            throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, p, q);
        }
        long l = t = (u & 1L) == 1L ? v : -(u / 2L);
        while (true) {
            if ((t & 1L) == 0L) {
                t /= 2L;
                continue;
            }
            if (t > 0L) {
                u = -t;
            } else {
                v = t;
            }
            if ((t = (v - u) / 2L) == 0L) break;
        }
        return -u * (1L << k);
    }

    public static int lcm(int a, int b) throws MathArithmeticException {
        if (a == 0 || b == 0) {
            return 0;
        }
        int lcm = FastMath.abs(ArithmeticUtils.mulAndCheck(a / ArithmeticUtils.gcd(a, b), b));
        if (lcm == Integer.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, a, b);
        }
        return lcm;
    }

    public static long lcm(long a, long b) throws MathArithmeticException {
        if (a == 0L || b == 0L) {
            return 0L;
        }
        long lcm = FastMath.abs(ArithmeticUtils.mulAndCheck(a / ArithmeticUtils.gcd(a, b), b));
        if (lcm == Long.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, a, b);
        }
        return lcm;
    }

    public static int mulAndCheck(int x, int y) throws MathArithmeticException {
        long m = (long)x * (long)y;
        if (m < Integer.MIN_VALUE || m > Integer.MAX_VALUE) {
            throw new MathArithmeticException();
        }
        return (int)m;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long mulAndCheck(long a, long b) throws MathArithmeticException {
        if (a > b) {
            return ArithmeticUtils.mulAndCheck(b, a);
        }
        if (a < 0L) {
            if (b < 0L) {
                if (a < Long.MAX_VALUE / b) throw new MathArithmeticException();
                return a * b;
            }
            if (b <= 0L) return 0L;
            if (Long.MIN_VALUE / b > a) throw new MathArithmeticException();
            return a * b;
        }
        if (a <= 0L) return 0L;
        if (a > Long.MAX_VALUE / b) throw new MathArithmeticException();
        return a * b;
    }

    public static int subAndCheck(int x, int y) throws MathArithmeticException {
        long s = (long)x - (long)y;
        if (s < Integer.MIN_VALUE || s > Integer.MAX_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, x, y);
        }
        return (int)s;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long subAndCheck(long a, long b) throws MathArithmeticException {
        if (b != Long.MIN_VALUE) return ArithmeticUtils.addAndCheck(a, -b, LocalizedFormats.OVERFLOW_IN_ADDITION);
        if (a >= 0L) throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, a, -b);
        return a - b;
    }

    public static int pow(int k, int e) throws NotPositiveException, MathArithmeticException {
        if (e < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        try {
            int exp = e;
            int result = 1;
            int k2p = k;
            while (true) {
                if ((exp & 1) != 0) {
                    result = ArithmeticUtils.mulAndCheck(result, k2p);
                }
                if ((exp >>= 1) == 0) break;
                k2p = ArithmeticUtils.mulAndCheck(k2p, k2p);
            }
            return result;
        } catch (MathArithmeticException mae) {
            mae.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
            mae.getContext().addMessage(LocalizedFormats.BASE, k);
            mae.getContext().addMessage(LocalizedFormats.EXPONENT, e);
            throw mae;
        }
    }

    @Deprecated
    public static int pow(int k, long e) throws NotPositiveException {
        if (e < 0L) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        int result = 1;
        int k2p = k;
        while (e != 0L) {
            if ((e & 1L) != 0L) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    public static long pow(long k, int e) throws NotPositiveException, MathArithmeticException {
        if (e < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        try {
            int exp = e;
            long result = 1L;
            long k2p = k;
            while (true) {
                if ((exp & 1) != 0) {
                    result = ArithmeticUtils.mulAndCheck(result, k2p);
                }
                if ((exp >>= 1) == 0) break;
                k2p = ArithmeticUtils.mulAndCheck(k2p, k2p);
            }
            return result;
        } catch (MathArithmeticException mae) {
            mae.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
            mae.getContext().addMessage(LocalizedFormats.BASE, k);
            mae.getContext().addMessage(LocalizedFormats.EXPONENT, e);
            throw mae;
        }
    }

    @Deprecated
    public static long pow(long k, long e) throws NotPositiveException {
        if (e < 0L) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        long result = 1L;
        long k2p = k;
        while (e != 0L) {
            if ((e & 1L) != 0L) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    public static BigInteger pow(BigInteger k, int e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        return k.pow(e);
    }

    public static BigInteger pow(BigInteger k, long e) throws NotPositiveException {
        if (e < 0L) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (e != 0L) {
            if ((e & 1L) != 0L) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e >>= 1;
        }
        return result;
    }

    public static BigInteger pow(BigInteger k, BigInteger e) throws NotPositiveException {
        if (e.compareTo(BigInteger.ZERO) < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.EXPONENT, e);
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (!BigInteger.ZERO.equals(e)) {
            if (e.testBit(0)) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e = e.shiftRight(1);
        }
        return result;
    }

    @Deprecated
    public static long stirlingS2(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.stirlingS2(n, k);
    }

    private static long addAndCheck(long a, long b, Localizable pattern) throws MathArithmeticException {
        long result;
        if (!((a ^ b) < 0L | (a ^ (result = a + b)) >= 0L)) {
            throw new MathArithmeticException(pattern, a, b);
        }
        return result;
    }

    public static boolean isPowerOfTwo(long n) {
        return n > 0L && (n & n - 1L) == 0L;
    }
}

