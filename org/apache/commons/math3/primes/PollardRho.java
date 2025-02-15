/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.primes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.primes.SmallPrimes;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class PollardRho {
    private PollardRho() {
    }

    public static List<Integer> primeFactors(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        if (1 == (n = SmallPrimes.smallTrialDivision(n, factors))) {
            return factors;
        }
        if (SmallPrimes.millerRabinPrimeTest(n)) {
            factors.add(n);
            return factors;
        }
        int divisor = PollardRho.rhoBrent(n);
        factors.add(divisor);
        factors.add(n / divisor);
        return factors;
    }

    static int rhoBrent(int n) {
        int x0 = 2;
        int m = 25;
        int cst = SmallPrimes.PRIMES_LAST;
        int y = 2;
        int r = 1;
        while (true) {
            int x = y;
            for (int i = 0; i < r; ++i) {
                long y2 = (long)y * (long)y;
                y = (int)((y2 + (long)cst) % (long)n);
            }
            int k = 0;
            do {
                int out;
                int bound = FastMath.min(25, r - k);
                int q = 1;
                for (int i = -3; i < bound; ++i) {
                    long y2 = (long)y * (long)y;
                    long divisor = FastMath.abs(x - (y = (int)((y2 + (long)cst) % (long)n)));
                    if (0L == divisor) {
                        cst += SmallPrimes.PRIMES_LAST;
                        k = -25;
                        y = 2;
                        r = 1;
                        break;
                    }
                    long prod = divisor * (long)q;
                    if (0 != (q = (int)(prod % (long)n))) continue;
                    return PollardRho.gcdPositive(FastMath.abs((int)divisor), n);
                }
                if (1 == (out = PollardRho.gcdPositive(FastMath.abs(q), n))) continue;
                return out;
            } while ((k += 25) < r);
            r = 2 * r;
        }
    }

    static int gcdPositive(int a, int b) {
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
            b = FastMath.min(a, b);
            a = FastMath.abs(delta);
            a >>= Integer.numberOfTrailingZeros(a);
        }
        return a << shift;
    }
}

