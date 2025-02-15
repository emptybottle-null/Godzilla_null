/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;

public class SecP224R1Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{1, 0, 0, -1, -1, -1, -1};
    static final int[] PExt = new int[]{1, 0, 0, -2, -1, -1, 0, 2, 0, 0, -2, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-1, -1, -1, 1, 0, 0, -1, -3, -1, -1, 1};
    private static final int P6 = -1;
    private static final int PExt13 = -1;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat224.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[6] == -1 && Nat224.gte(nArray3, P)) {
            SecP224R1Field.addPInvTo(nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(14, nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[13] == -1 && Nat.gte(14, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(14, nArray3, PExtInv.length);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(7, nArray, nArray2);
        if (n != 0 || nArray2[6] == -1 && Nat224.gte(nArray2, P)) {
            SecP224R1Field.addPInvTo(nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat224.fromBigInteger(bigInteger);
        if (nArray[6] == -1 && Nat224.gte(nArray, P)) {
            Nat224.subFrom(P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(7, nArray, 0, nArray2);
        } else {
            int n = Nat224.add(nArray, P, nArray2);
            Nat.shiftDownBit(7, nArray2, n);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat224.createExt();
        Nat224.mul(nArray, nArray2, nArray4);
        SecP224R1Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat224.mulAddTo(nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[13] == -1 && Nat.gte(14, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(14, nArray3, PExtInv.length);
        }
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat224.isZero(nArray)) {
            Nat224.zero(nArray2);
        } else {
            Nat224.sub(P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        long l = (long)nArray[10] & 0xFFFFFFFFL;
        long l2 = (long)nArray[11] & 0xFFFFFFFFL;
        long l3 = (long)nArray[12] & 0xFFFFFFFFL;
        long l4 = (long)nArray[13] & 0xFFFFFFFFL;
        long l5 = ((long)nArray[7] & 0xFFFFFFFFL) + l2 - 1L;
        long l6 = ((long)nArray[8] & 0xFFFFFFFFL) + l3;
        long l7 = ((long)nArray[9] & 0xFFFFFFFFL) + l4;
        long l8 = 0L;
        long l9 = (l8 += ((long)nArray[0] & 0xFFFFFFFFL) - l5) & 0xFFFFFFFFL;
        l8 >>= 32;
        nArray2[1] = (int)(l8 += ((long)nArray[1] & 0xFFFFFFFFL) - l6);
        l8 >>= 32;
        nArray2[2] = (int)(l8 += ((long)nArray[2] & 0xFFFFFFFFL) - l7);
        l8 >>= 32;
        long l10 = (l8 += ((long)nArray[3] & 0xFFFFFFFFL) + l5 - l) & 0xFFFFFFFFL;
        l8 >>= 32;
        nArray2[4] = (int)(l8 += ((long)nArray[4] & 0xFFFFFFFFL) + l6 - l2);
        l8 >>= 32;
        nArray2[5] = (int)(l8 += ((long)nArray[5] & 0xFFFFFFFFL) + l7 - l3);
        l8 >>= 32;
        nArray2[6] = (int)(l8 += ((long)nArray[6] & 0xFFFFFFFFL) + l - l4);
        l8 >>= 32;
        l10 += ++l8;
        nArray2[0] = (int)(l9 -= l8);
        l8 = l9 >> 32;
        if (l8 != 0L) {
            nArray2[1] = (int)(l8 += (long)nArray2[1] & 0xFFFFFFFFL);
            l8 >>= 32;
            nArray2[2] = (int)(l8 += (long)nArray2[2] & 0xFFFFFFFFL);
            l10 += l8 >> 32;
        }
        nArray2[3] = (int)l10;
        l8 = l10 >> 32;
        if (l8 != 0L && Nat.incAt(7, nArray2, 4) != 0 || nArray2[6] == -1 && Nat224.gte(nArray2, P)) {
            SecP224R1Field.addPInvTo(nArray2);
        }
    }

    public static void reduce32(int n, int[] nArray) {
        long l = 0L;
        if (n != 0) {
            long l2 = (long)n & 0xFFFFFFFFL;
            nArray[0] = (int)(l += ((long)nArray[0] & 0xFFFFFFFFL) - l2);
            if ((l >>= 32) != 0L) {
                nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
                l >>= 32;
                nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
                l >>= 32;
            }
            nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + l2);
            l >>= 32;
        }
        if (l != 0L && Nat.incAt(7, nArray, 4) != 0 || nArray[6] == -1 && Nat224.gte(nArray, P)) {
            SecP224R1Field.addPInvTo(nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat224.createExt();
        Nat224.square(nArray, nArray3);
        SecP224R1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat224.createExt();
        Nat224.square(nArray, nArray3);
        SecP224R1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat224.square(nArray2, nArray3);
            SecP224R1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat224.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            SecP224R1Field.subPInvFrom(nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(14, nArray, nArray2, nArray3);
        if (n != 0 && Nat.subFrom(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.decAt(14, nArray3, PExtInv.length);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(7, nArray, 0, nArray2);
        if (n != 0 || nArray2[6] == -1 && Nat224.gte(nArray2, P)) {
            SecP224R1Field.addPInvTo(nArray2);
        }
    }

    private static void addPInvTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + 1L);
        if ((l >>= 32) != 0L) {
            Nat.incAt(7, nArray, 4);
        }
    }

    private static void subPInvFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) - 1L);
        if ((l >>= 32) != 0L) {
            Nat.decAt(7, nArray, 4);
        }
    }
}

