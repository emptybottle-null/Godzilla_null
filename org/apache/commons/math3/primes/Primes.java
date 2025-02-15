/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.primes;

import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.primes.SmallPrimes;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Primes {
    private Primes() {
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int p : SmallPrimes.PRIMES) {
            if (0 != n % p) continue;
            return n == p;
        }
        return SmallPrimes.millerRabinPrimeTest(n);
    }

    public static int nextPrime(int n) {
        if (n < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, n, 0);
        }
        if (n == 2) {
            return 2;
        }
        if ((n |= 1) == 1) {
            return 2;
        }
        if (Primes.isPrime(n)) {
            return n;
        }
        int rem = n % 3;
        if (0 == rem) {
            n += 2;
        } else if (1 == rem) {
            n += 4;
        }
        while (!Primes.isPrime(n)) {
            if (Primes.isPrime(n += 2)) {
                return n;
            }
            n += 4;
        }
        return n;
    }

    public static List<Integer> primeFactors(int n) {
        if (n < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, n, 2);
        }
        return SmallPrimes.trialDivision(n);
    }
}

