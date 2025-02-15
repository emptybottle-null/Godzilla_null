/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public class SecP192R1Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{-1, -1, -2, -1, -1, -1};
    static final int[] PExt = new int[]{1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-1, -1, -3, -1, -2, -1, 1, 0, 2};
    private static final int P5 = -1;
    private static final int PExt11 = -1;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat192.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[5] == -1 && Nat192.gte(nArray3, P)) {
            SecP192R1Field.addPInvTo(nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(12, nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[11] == -1 && Nat.gte(12, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(12, nArray3, PExtInv.length);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(6, nArray, nArray2);
        if (n != 0 || nArray2[5] == -1 && Nat192.gte(nArray2, P)) {
            SecP192R1Field.addPInvTo(nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat192.fromBigInteger(bigInteger);
        if (nArray[5] == -1 && Nat192.gte(nArray, P)) {
            Nat192.subFrom(P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(6, nArray, 0, nArray2);
        } else {
            int n = Nat192.add(nArray, P, nArray2);
            Nat.shiftDownBit(6, nArray2, n);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat192.createExt();
        Nat192.mul(nArray, nArray2, nArray4);
        SecP192R1Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat192.mulAddTo(nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[11] == -1 && Nat.gte(12, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(12, nArray3, PExtInv.length);
        }
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat192.isZero(nArray)) {
            Nat192.zero(nArray2);
        } else {
            Nat192.sub(P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        long l = (long)nArray[6] & 0xFFFFFFFFL;
        long l2 = (long)nArray[7] & 0xFFFFFFFFL;
        long l3 = (long)nArray[8] & 0xFFFFFFFFL;
        long l4 = (long)nArray[9] & 0xFFFFFFFFL;
        long l5 = (long)nArray[10] & 0xFFFFFFFFL;
        long l6 = (long)nArray[11] & 0xFFFFFFFFL;
        long l7 = l + l5;
        long l8 = l2 + l6;
        long l9 = 0L;
        int n = (int)(l9 += ((long)nArray[0] & 0xFFFFFFFFL) + l7);
        l9 >>= 32;
        nArray2[1] = (int)(l9 += ((long)nArray[1] & 0xFFFFFFFFL) + l8);
        l9 >>= 32;
        long l10 = (l9 += ((long)nArray[2] & 0xFFFFFFFFL) + (l7 += l3)) & 0xFFFFFFFFL;
        l9 >>= 32;
        nArray2[3] = (int)(l9 += ((long)nArray[3] & 0xFFFFFFFFL) + (l8 += l4));
        l9 >>= 32;
        nArray2[4] = (int)(l9 += ((long)nArray[4] & 0xFFFFFFFFL) + (l7 -= l));
        l9 >>= 32;
        nArray2[5] = (int)(l9 += ((long)nArray[5] & 0xFFFFFFFFL) + (l8 -= l2));
        l10 += (l9 >>= 32);
        nArray2[0] = (int)(l9 += (long)n & 0xFFFFFFFFL);
        if ((l9 >>= 32) != 0L) {
            nArray2[1] = (int)(l9 += (long)nArray2[1] & 0xFFFFFFFFL);
            l10 += l9 >> 32;
        }
        nArray2[2] = (int)l10;
        l9 = l10 >> 32;
        if (l9 != 0L && Nat.incAt(6, nArray2, 3) != 0 || nArray2[5] == -1 && Nat192.gte(nArray2, P)) {
            SecP192R1Field.addPInvTo(nArray2);
        }
    }

    public static void reduce32(int n, int[] nArray) {
        long l = 0L;
        if (n != 0) {
            long l2 = (long)n & 0xFFFFFFFFL;
            nArray[0] = (int)(l += ((long)nArray[0] & 0xFFFFFFFFL) + l2);
            if ((l >>= 32) != 0L) {
                nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
                l >>= 32;
            }
            nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) + l2);
            l >>= 32;
        }
        if (l != 0L && Nat.incAt(6, nArray, 3) != 0 || nArray[5] == -1 && Nat192.gte(nArray, P)) {
            SecP192R1Field.addPInvTo(nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat192.createExt();
        Nat192.square(nArray, nArray3);
        SecP192R1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat192.createExt();
        Nat192.square(nArray, nArray3);
        SecP192R1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat192.square(nArray2, nArray3);
            SecP192R1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat192.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            SecP192R1Field.subPInvFrom(nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(12, nArray, nArray2, nArray3);
        if (n != 0 && Nat.subFrom(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.decAt(12, nArray3, PExtInv.length);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(6, nArray, 0, nArray2);
        if (n != 0 || nArray2[5] == -1 && Nat192.gte(nArray2, P)) {
            SecP192R1Field.addPInvTo(nArray2);
        }
    }

    private static void addPInvTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) + 1L);
        if ((l >>= 32) != 0L) {
            Nat.incAt(6, nArray, 3);
        }
    }

    private static void subPInvFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) - 1L);
        if ((l >>= 32) != 0L) {
            Nat.decAt(6, nArray, 3);
        }
    }
}

