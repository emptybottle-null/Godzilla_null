/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

public final class PolynomialRingGF2 {
    private PolynomialRingGF2() {
    }

    public static int add(int n, int n2) {
        return n ^ n2;
    }

    public static long multiply(int n, int n2) {
        long l = 0L;
        if (n2 != 0) {
            long l2 = (long)n2 & 0xFFFFFFFFL;
            while (n != 0) {
                byte by = (byte)(n & 1);
                if (by == 1) {
                    l ^= l2;
                }
                n >>>= 1;
                l2 <<= 1;
            }
        }
        return l;
    }

    public static int modMultiply(int n, int n2, int n3) {
        int n4 = 0;
        int n5 = PolynomialRingGF2.remainder(n, n3);
        int n6 = PolynomialRingGF2.remainder(n2, n3);
        if (n6 != 0) {
            int n7 = 1 << PolynomialRingGF2.degree(n3);
            while (n5 != 0) {
                byte by = (byte)(n5 & 1);
                if (by == 1) {
                    n4 ^= n6;
                }
                n5 >>>= 1;
                if ((n6 <<= 1) < n7) continue;
                n6 ^= n3;
            }
        }
        return n4;
    }

    public static int degree(int n) {
        int n2 = -1;
        while (n != 0) {
            ++n2;
            n >>>= 1;
        }
        return n2;
    }

    public static int degree(long l) {
        int n = 0;
        while (l != 0L) {
            ++n;
            l >>>= 1;
        }
        return n - 1;
    }

    public static int remainder(int n, int n2) {
        int n3 = n;
        if (n2 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        while (PolynomialRingGF2.degree(n3) >= PolynomialRingGF2.degree(n2)) {
            n3 ^= n2 << PolynomialRingGF2.degree(n3) - PolynomialRingGF2.degree(n2);
        }
        return n3;
    }

    public static int rest(long l, int n) {
        long l2 = l;
        if (n == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        long l3 = (long)n & 0xFFFFFFFFL;
        while (l2 >>> 32 != 0L) {
            l2 ^= l3 << PolynomialRingGF2.degree(l2) - PolynomialRingGF2.degree(l3);
        }
        int n2 = (int)(l2 & 0xFFFFFFFFFFFFFFFFL);
        while (PolynomialRingGF2.degree(n2) >= PolynomialRingGF2.degree(n)) {
            n2 ^= n << PolynomialRingGF2.degree(n2) - PolynomialRingGF2.degree(n);
        }
        return n2;
    }

    public static int gcd(int n, int n2) {
        int n3 = n;
        int n4 = n2;
        while (n4 != 0) {
            int n5 = PolynomialRingGF2.remainder(n3, n4);
            n3 = n4;
            n4 = n5;
        }
        return n3;
    }

    public static boolean isIrreducible(int n) {
        if (n == 0) {
            return false;
        }
        int n2 = PolynomialRingGF2.degree(n) >>> 1;
        int n3 = 2;
        for (int i = 0; i < n2; ++i) {
            if (PolynomialRingGF2.gcd((n3 = PolynomialRingGF2.modMultiply(n3, n3, n)) ^ 2, n) == 1) continue;
            return false;
        }
        return true;
    }

    public static int getIrreduciblePolynomial(int n) {
        if (n < 0) {
            System.err.println("The Degree is negative");
            return 0;
        }
        if (n > 31) {
            System.err.println("The Degree is more then 31");
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        int n2 = 1 << n;
        int n3 = 1 << n + 1;
        for (int i = ++n2; i < n3; i += 2) {
            if (!PolynomialRingGF2.isIrreducible(i)) continue;
            return i;
        }
        return 0;
    }
}

