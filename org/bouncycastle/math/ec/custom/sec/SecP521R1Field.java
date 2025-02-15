/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat512;

public class SecP521R1Field {
    static final int[] P = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};
    private static final int P16 = 511;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(16, nArray, nArray2, nArray3) + nArray[16] + nArray2[16];
        if (n > 511 || n == 511 && Nat.eq(16, nArray3, P)) {
            n += Nat.inc(16, nArray3);
            n &= 0x1FF;
        }
        nArray3[16] = n;
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(16, nArray, nArray2) + nArray[16];
        if (n > 511 || n == 511 && Nat.eq(16, nArray2, P)) {
            n += Nat.inc(16, nArray2);
            n &= 0x1FF;
        }
        nArray2[16] = n;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat.fromBigInteger(521, bigInteger);
        if (Nat.eq(17, nArray, P)) {
            Nat.zero(17, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        int n = nArray[16];
        int n2 = Nat.shiftDownBit(16, nArray, n, nArray2);
        nArray2[16] = n >>> 1 | n2 >>> 23;
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat.create(33);
        SecP521R1Field.implMultiply(nArray, nArray2, nArray4);
        SecP521R1Field.reduce(nArray4, nArray3);
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat.isZero(17, nArray)) {
            Nat.zero(17, nArray2);
        } else {
            Nat.sub(17, P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        int n = nArray[32];
        int n2 = Nat.shiftDownBits(16, nArray, 16, 9, n, nArray2, 0) >>> 23;
        n2 += n >>> 9;
        if ((n2 += Nat.addTo(16, nArray, nArray2)) > 511 || n2 == 511 && Nat.eq(16, nArray2, P)) {
            n2 += Nat.inc(16, nArray2);
            n2 &= 0x1FF;
        }
        nArray2[16] = n2;
    }

    public static void reduce23(int[] nArray) {
        int n = nArray[16];
        int n2 = Nat.addWordTo(16, n >>> 9, nArray) + (n & 0x1FF);
        if (n2 > 511 || n2 == 511 && Nat.eq(16, nArray, P)) {
            n2 += Nat.inc(16, nArray);
            n2 &= 0x1FF;
        }
        nArray[16] = n2;
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat.create(33);
        SecP521R1Field.implSquare(nArray, nArray3);
        SecP521R1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat.create(33);
        SecP521R1Field.implSquare(nArray, nArray3);
        SecP521R1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            SecP521R1Field.implSquare(nArray2, nArray3);
            SecP521R1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(16, nArray, nArray2, nArray3) + nArray[16] - nArray2[16];
        if (n < 0) {
            n += Nat.dec(16, nArray3);
            n &= 0x1FF;
        }
        nArray3[16] = n;
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = nArray[16];
        int n2 = Nat.shiftUpBit(16, nArray, n << 23, nArray2) | n << 1;
        nArray2[16] = n2 & 0x1FF;
    }

    protected static void implMultiply(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat512.mul(nArray, nArray2, nArray3);
        int n = nArray[16];
        int n2 = nArray2[16];
        nArray3[32] = Nat.mul31BothAdd(16, n, nArray2, n2, nArray, nArray3, 16) + n * n2;
    }

    protected static void implSquare(int[] nArray, int[] nArray2) {
        Nat512.square(nArray, nArray2);
        int n = nArray[16];
        nArray2[32] = Nat.mulWordAddTo(16, n << 1, nArray, 0, nArray2, 16) + n * n;
    }
}

