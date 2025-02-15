/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;

public class SecP224K1Field {
    static final int[] P = new int[]{-6803, -2, -1, -1, -1, -1, -1};
    static final int[] PExt = new int[]{46280809, 13606, 1, 0, 0, 0, 0, -13606, -3, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-46280809, -13607, -2, -1, -1, -1, -1, 13605, 2};
    private static final int P6 = -1;
    private static final int PExt13 = -1;
    private static final int PInv33 = 6803;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat224.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[6] == -1 && Nat224.gte(nArray3, P)) {
            Nat.add33To(7, 6803, nArray3);
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
            Nat.add33To(7, 6803, nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat224.fromBigInteger(bigInteger);
        if (nArray[6] == -1 && Nat224.gte(nArray, P)) {
            Nat.add33To(7, 6803, nArray);
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
        SecP224K1Field.reduce(nArray4, nArray3);
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
        long l = Nat224.mul33Add(6803, nArray, 7, nArray, 0, nArray2, 0);
        int n = Nat224.mul33DWordAdd(6803, l, nArray2, 0);
        if (n != 0 || nArray2[6] == -1 && Nat224.gte(nArray2, P)) {
            Nat.add33To(7, 6803, nArray2);
        }
    }

    public static void reduce32(int n, int[] nArray) {
        if (n != 0 && Nat224.mul33WordAdd(6803, n, nArray, 0) != 0 || nArray[6] == -1 && Nat224.gte(nArray, P)) {
            Nat.add33To(7, 6803, nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat224.createExt();
        Nat224.square(nArray, nArray3);
        SecP224K1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat224.createExt();
        Nat224.square(nArray, nArray3);
        SecP224K1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat224.square(nArray2, nArray3);
            SecP224K1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat224.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            Nat.sub33From(7, 6803, nArray3);
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
            Nat.add33To(7, 6803, nArray2);
        }
    }
}

