/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat384;

public class SecP384R1Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};
    static final int[] PExt = new int[]{1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};
    private static final int P11 = -1;
    private static final int PExt23 = -1;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(12, nArray, nArray2, nArray3);
        if (n != 0 || nArray3[11] == -1 && Nat.gte(12, nArray3, P)) {
            SecP384R1Field.addPInvTo(nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(24, nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[23] == -1 && Nat.gte(24, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(24, nArray3, PExtInv.length);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(12, nArray, nArray2);
        if (n != 0 || nArray2[11] == -1 && Nat.gte(12, nArray2, P)) {
            SecP384R1Field.addPInvTo(nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat.fromBigInteger(384, bigInteger);
        if (nArray[11] == -1 && Nat.gte(12, nArray, P)) {
            Nat.subFrom(12, P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(12, nArray, 0, nArray2);
        } else {
            int n = Nat.add(12, nArray, P, nArray2);
            Nat.shiftDownBit(12, nArray2, n);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat.create(24);
        Nat384.mul(nArray, nArray2, nArray4);
        SecP384R1Field.reduce(nArray4, nArray3);
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat.isZero(12, nArray)) {
            Nat.zero(12, nArray2);
        } else {
            Nat.sub(12, P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        long l = (long)nArray[16] & 0xFFFFFFFFL;
        long l2 = (long)nArray[17] & 0xFFFFFFFFL;
        long l3 = (long)nArray[18] & 0xFFFFFFFFL;
        long l4 = (long)nArray[19] & 0xFFFFFFFFL;
        long l5 = (long)nArray[20] & 0xFFFFFFFFL;
        long l6 = (long)nArray[21] & 0xFFFFFFFFL;
        long l7 = (long)nArray[22] & 0xFFFFFFFFL;
        long l8 = (long)nArray[23] & 0xFFFFFFFFL;
        long l9 = ((long)nArray[12] & 0xFFFFFFFFL) + l5 - 1L;
        long l10 = ((long)nArray[13] & 0xFFFFFFFFL) + l7;
        long l11 = ((long)nArray[14] & 0xFFFFFFFFL) + l7 + l8;
        long l12 = ((long)nArray[15] & 0xFFFFFFFFL) + l8;
        long l13 = l2 + l6;
        long l14 = l6 - l8;
        long l15 = l7 - l8;
        long l16 = l9 + l14;
        long l17 = 0L;
        nArray2[0] = (int)(l17 += ((long)nArray[0] & 0xFFFFFFFFL) + l16);
        l17 >>= 32;
        nArray2[1] = (int)(l17 += ((long)nArray[1] & 0xFFFFFFFFL) + l8 - l9 + l10);
        l17 >>= 32;
        nArray2[2] = (int)(l17 += ((long)nArray[2] & 0xFFFFFFFFL) - l6 - l10 + l11);
        l17 >>= 32;
        nArray2[3] = (int)(l17 += ((long)nArray[3] & 0xFFFFFFFFL) - l11 + l12 + l16);
        l17 >>= 32;
        nArray2[4] = (int)(l17 += ((long)nArray[4] & 0xFFFFFFFFL) + l + l6 + l10 - l12 + l16);
        l17 >>= 32;
        nArray2[5] = (int)(l17 += ((long)nArray[5] & 0xFFFFFFFFL) - l + l10 + l11 + l13);
        l17 >>= 32;
        nArray2[6] = (int)(l17 += ((long)nArray[6] & 0xFFFFFFFFL) + l3 - l2 + l11 + l12);
        l17 >>= 32;
        nArray2[7] = (int)(l17 += ((long)nArray[7] & 0xFFFFFFFFL) + l + l4 - l3 + l12);
        l17 >>= 32;
        nArray2[8] = (int)(l17 += ((long)nArray[8] & 0xFFFFFFFFL) + l + l2 + l5 - l4);
        l17 >>= 32;
        nArray2[9] = (int)(l17 += ((long)nArray[9] & 0xFFFFFFFFL) + l3 - l5 + l13);
        l17 >>= 32;
        nArray2[10] = (int)(l17 += ((long)nArray[10] & 0xFFFFFFFFL) + l3 + l4 - l14 + l15);
        l17 >>= 32;
        nArray2[11] = (int)(l17 += ((long)nArray[11] & 0xFFFFFFFFL) + l4 + l5 - l15);
        l17 >>= 32;
        SecP384R1Field.reduce32((int)(++l17), nArray2);
    }

    public static void reduce32(int n, int[] nArray) {
        long l = 0L;
        if (n != 0) {
            long l2 = (long)n & 0xFFFFFFFFL;
            nArray[0] = (int)(l += ((long)nArray[0] & 0xFFFFFFFFL) + l2);
            l >>= 32;
            nArray[1] = (int)(l += ((long)nArray[1] & 0xFFFFFFFFL) - l2);
            if ((l >>= 32) != 0L) {
                nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
                l >>= 32;
            }
            nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + l2);
            l >>= 32;
            nArray[4] = (int)(l += ((long)nArray[4] & 0xFFFFFFFFL) + l2);
            l >>= 32;
        }
        if (l != 0L && Nat.incAt(12, nArray, 5) != 0 || nArray[11] == -1 && Nat.gte(12, nArray, P)) {
            SecP384R1Field.addPInvTo(nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat.create(24);
        Nat384.square(nArray, nArray3);
        SecP384R1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat.create(24);
        Nat384.square(nArray, nArray3);
        SecP384R1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat384.square(nArray2, nArray3);
            SecP384R1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(12, nArray, nArray2, nArray3);
        if (n != 0) {
            SecP384R1Field.subPInvFrom(nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(24, nArray, nArray2, nArray3);
        if (n != 0 && Nat.subFrom(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.decAt(24, nArray3, PExtInv.length);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(12, nArray, 0, nArray2);
        if (n != 0 || nArray2[11] == -1 && Nat.gte(12, nArray2, P)) {
            SecP384R1Field.addPInvTo(nArray2);
        }
    }

    private static void addPInvTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + 1L;
        nArray[0] = (int)l;
        l >>= 32;
        nArray[1] = (int)(l += ((long)nArray[1] & 0xFFFFFFFFL) - 1L);
        if ((l >>= 32) != 0L) {
            nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + 1L);
        l >>= 32;
        nArray[4] = (int)(l += ((long)nArray[4] & 0xFFFFFFFFL) + 1L);
        if ((l >>= 32) != 0L) {
            Nat.incAt(12, nArray, 5);
        }
    }

    private static void subPInvFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - 1L;
        nArray[0] = (int)l;
        l >>= 32;
        nArray[1] = (int)(l += ((long)nArray[1] & 0xFFFFFFFFL) + 1L);
        if ((l >>= 32) != 0L) {
            nArray[2] = (int)(l += (long)nArray[2] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) - 1L);
        l >>= 32;
        nArray[4] = (int)(l += ((long)nArray[4] & 0xFFFFFFFFL) - 1L);
        if ((l >>= 32) != 0L) {
            Nat.decAt(12, nArray, 5);
        }
    }
}

