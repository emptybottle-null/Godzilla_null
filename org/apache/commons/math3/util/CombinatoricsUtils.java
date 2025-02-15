/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class CombinatoricsUtils {
    static final long[] FACTORIALS = new long[]{1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<Object>(null);

    private CombinatoricsUtils() {
    }

    public static long binomialCoefficient(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        CombinatoricsUtils.checkBinomial(n, k);
        if (n == k || k == 0) {
            return 1L;
        }
        if (k == 1 || k == n - 1) {
            return n;
        }
        if (k > n / 2) {
            return CombinatoricsUtils.binomialCoefficient(n, n - k);
        }
        long result = 1L;
        if (n <= 61) {
            int i = n - k + 1;
            for (int j = 1; j <= k; ++j) {
                result = result * (long)i / (long)j;
                ++i;
            }
        } else if (n <= 66) {
            int i = n - k + 1;
            for (int j = 1; j <= k; ++j) {
                long d = ArithmeticUtils.gcd(i, j);
                result = result / ((long)j / d) * ((long)i / d);
                ++i;
            }
        } else {
            int i = n - k + 1;
            for (int j = 1; j <= k; ++j) {
                long d = ArithmeticUtils.gcd(i, j);
                result = ArithmeticUtils.mulAndCheck(result / ((long)j / d), (long)i / d);
                ++i;
            }
        }
        return result;
    }

    public static double binomialCoefficientDouble(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        CombinatoricsUtils.checkBinomial(n, k);
        if (n == k || k == 0) {
            return 1.0;
        }
        if (k == 1 || k == n - 1) {
            return n;
        }
        if (k > n / 2) {
            return CombinatoricsUtils.binomialCoefficientDouble(n, n - k);
        }
        if (n < 67) {
            return CombinatoricsUtils.binomialCoefficient(n, k);
        }
        double result = 1.0;
        for (int i = 1; i <= k; ++i) {
            result *= (double)(n - k + i) / (double)i;
        }
        return FastMath.floor(result + 0.5);
    }

    public static double binomialCoefficientLog(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        int i;
        CombinatoricsUtils.checkBinomial(n, k);
        if (n == k || k == 0) {
            return 0.0;
        }
        if (k == 1 || k == n - 1) {
            return FastMath.log(n);
        }
        if (n < 67) {
            return FastMath.log(CombinatoricsUtils.binomialCoefficient(n, k));
        }
        if (n < 1030) {
            return FastMath.log(CombinatoricsUtils.binomialCoefficientDouble(n, k));
        }
        if (k > n / 2) {
            return CombinatoricsUtils.binomialCoefficientLog(n, n - k);
        }
        double logSum = 0.0;
        for (i = n - k + 1; i <= n; ++i) {
            logSum += FastMath.log(i);
        }
        for (i = 2; i <= k; ++i) {
            logSum -= FastMath.log(i);
        }
        return logSum;
    }

    public static long factorial(int n) throws NotPositiveException, MathArithmeticException {
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (n > 20) {
            throw new MathArithmeticException();
        }
        return FACTORIALS[n];
    }

    public static double factorialDouble(int n) throws NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (n < 21) {
            return FACTORIALS[n];
        }
        return FastMath.floor(FastMath.exp(CombinatoricsUtils.factorialLog(n)) + 0.5);
    }

    public static double factorialLog(int n) throws NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (n < 21) {
            return FastMath.log(FACTORIALS[n]);
        }
        double logSum = 0.0;
        for (int i = 2; i <= n; ++i) {
            logSum += FastMath.log(i);
        }
        return logSum;
    }

    public static long stirlingS2(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        if (k < 0) {
            throw new NotPositiveException(k);
        }
        if (k > n) {
            throw new NumberIsTooLargeException(k, (Number)n, true);
        }
        Object stirlingS2 = STIRLING_S2.get();
        if (stirlingS2 == null) {
            int maxIndex = 26;
            stirlingS2 = new long[26][];
            stirlingS2[0] = new long[]{1L};
            for (int i = 1; i < ((long[][])stirlingS2).length; ++i) {
                stirlingS2[i] = new long[i + 1];
                stirlingS2[i][0] = 0L;
                stirlingS2[i][1] = 1L;
                stirlingS2[i][i] = 1L;
                for (int j = 2; j < i; ++j) {
                    stirlingS2[i][j] = (long)j * stirlingS2[i - 1][j] + stirlingS2[i - 1][j - 1];
                }
            }
            STIRLING_S2.compareAndSet((long[][])null, (long[][])stirlingS2);
        }
        if (n < ((long[][])stirlingS2).length) {
            return stirlingS2[n][k];
        }
        if (k == 0) {
            return 0L;
        }
        if (k == 1 || k == n) {
            return 1L;
        }
        if (k == 2) {
            return (1L << n - 1) - 1L;
        }
        if (k == n - 1) {
            return CombinatoricsUtils.binomialCoefficient(n, 2);
        }
        long sum = 0L;
        long sign = (k & 1) == 0 ? 1L : -1L;
        for (int j = 1; j <= k; ++j) {
            if ((sum += (sign = -sign) * CombinatoricsUtils.binomialCoefficient(k, j) * (long)ArithmeticUtils.pow(j, n)) >= 0L) continue;
            throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, n, 0, ((long[][])stirlingS2).length - 1);
        }
        return sum / CombinatoricsUtils.factorial(k);
    }

    public static Iterator<int[]> combinationsIterator(int n, int k) {
        return new Combinations(n, k).iterator();
    }

    public static void checkBinomial(int n, int k) throws NumberIsTooLargeException, NotPositiveException {
        if (n < k) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, (Number)k, n, true);
        }
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, n);
        }
    }
}

