/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;

public class SecP160R1Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{Integer.MAX_VALUE, -1, -1, -1, -1};
    static final int[] PExt = new int[]{1, 0x40000001, 0, 0, 0, -2, -2, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-1, -1073741826, -1, -1, -1, 1, 1};
    private static final int P4 = -1;
    private static final int PExt9 = -1;
    private static final int PInv = -2147483647;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat160.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[4] == -1 && Nat160.gte(nArray3, P)) {
            Nat.addWordTo(5, -2147483647, nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(10, nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[9] == -1 && Nat.gte(10, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(10, nArray3, PExtInv.length);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(5, nArray, nArray2);
        if (n != 0 || nArray2[4] == -1 && Nat160.gte(nArray2, P)) {
            Nat.addWordTo(5, -2147483647, nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat160.fromBigInteger(bigInteger);
        if (nArray[4] == -1 && Nat160.gte(nArray, P)) {
            Nat160.subFrom(P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(5, nArray, 0, nArray2);
        } else {
            int n = Nat160.add(nArray, P, nArray2);
            Nat.shiftDownBit(5, nArray2, n);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat160.createExt();
        Nat160.mul(nArray, nArray2, nArray4);
        SecP160R1Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat160.mulAddTo(nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[9] == -1 && Nat.gte(10, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(10, nArray3, PExtInv.length);
        }
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat160.isZero(nArray)) {
            Nat160.zero(nArray2);
        } else {
            Nat160.sub(P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        long l = (long)nArray[5] & 0xFFFFFFFFL;
        long l2 = (long)nArray[6] & 0xFFFFFFFFL;
        long l3 = (long)nArray[7] & 0xFFFFFFFFL;
        long l4 = (long)nArray[8] & 0xFFFFFFFFL;
        long l5 = (long)nArray[9] & 0xFFFFFFFFL;
        long l6 = 0L;
        nArray2[0] = (int)(l6 += ((long)nArray[0] & 0xFFFFFFFFL) + l + (l << 31));
        l6 >>>= 32;
        nArray2[1] = (int)(l6 += ((long)nArray[1] & 0xFFFFFFFFL) + l2 + (l2 << 31));
        l6 >>>= 32;
        nArray2[2] = (int)(l6 += ((long)nArray[2] & 0xFFFFFFFFL) + l3 + (l3 << 31));
        l6 >>>= 32;
        nArray2[3] = (int)(l6 += ((long)nArray[3] & 0xFFFFFFFFL) + l4 + (l4 << 31));
        l6 >>>= 32;
        nArray2[4] = (int)(l6 += ((long)nArray[4] & 0xFFFFFFFFL) + l5 + (l5 << 31));
        SecP160R1Field.reduce32((int)(l6 >>>= 32), nArray2);
    }

    public static void reduce32(int n, int[] nArray) {
        if (n != 0 && Nat160.mulWordsAdd(-2147483647, n, nArray, 0) != 0 || nArray[4] == -1 && Nat160.gte(nArray, P)) {
            Nat.addWordTo(5, -2147483647, nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat160.createExt();
        Nat160.square(nArray, nArray3);
        SecP160R1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat160.createExt();
        Nat160.square(nArray, nArray3);
        SecP160R1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat160.square(nArray2, nArray3);
            SecP160R1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat160.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            Nat.subWordFrom(5, -2147483647, nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(10, nArray, nArray2, nArray3);
        if (n != 0 && Nat.subFrom(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.decAt(10, nArray3, PExtInv.length);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(5, nArray, 0, nArray2);
        if (n != 0 || nArray2[4] == -1 && Nat160.gte(nArray2, P)) {
            Nat.addWordTo(5, -2147483647, nArray2);
        }
    }
}

