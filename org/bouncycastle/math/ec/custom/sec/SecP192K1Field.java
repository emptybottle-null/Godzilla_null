/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public class SecP192K1Field {
    static final int[] P = new int[]{-4553, -2, -1, -1, -1, -1};
    static final int[] PExt = new int[]{20729809, 9106, 1, 0, 0, 0, -9106, -3, -1, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-20729809, -9107, -2, -1, -1, -1, 9105, 2};
    private static final int P5 = -1;
    private static final int PExt11 = -1;
    private static final int PInv33 = 4553;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat192.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[5] == -1 && Nat192.gte(nArray3, P)) {
            Nat.add33To(6, 4553, nArray3);
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
            Nat.add33To(6, 4553, nArray2);
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
        SecP192K1Field.reduce(nArray4, nArray3);
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
        long l = Nat192.mul33Add(4553, nArray, 6, nArray, 0, nArray2, 0);
        int n = Nat192.mul33DWordAdd(4553, l, nArray2, 0);
        if (n != 0 || nArray2[5] == -1 && Nat192.gte(nArray2, P)) {
            Nat.add33To(6, 4553, nArray2);
        }
    }

    public static void reduce32(int n, int[] nArray) {
        if (n != 0 && Nat192.mul33WordAdd(4553, n, nArray, 0) != 0 || nArray[5] == -1 && Nat192.gte(nArray, P)) {
            Nat.add33To(6, 4553, nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat192.createExt();
        Nat192.square(nArray, nArray3);
        SecP192K1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat192.createExt();
        Nat192.square(nArray, nArray3);
        SecP192K1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat192.square(nArray2, nArray3);
            SecP192K1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat192.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            Nat.sub33From(6, 4553, nArray3);
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
            Nat.add33To(6, 4553, nArray2);
        }
    }
}

