/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SecP256K1Field {
    static final int[] P = new int[]{-977, -2, -1, -1, -1, -1, -1, -1};
    static final int[] PExt = new int[]{954529, 1954, 1, 0, 0, 0, 0, 0, -1954, -3, -1, -1, -1, -1, -1, -1};
    private static final int[] PExtInv = new int[]{-954529, -1955, -2, -1, -1, -1, -1, -1, 1953, 2};
    private static final int P7 = -1;
    private static final int PExt15 = -1;
    private static final int PInv33 = 977;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[7] == -1 && Nat256.gte(nArray3, P)) {
            Nat.add33To(8, 977, nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(16, nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[15] == -1 && Nat.gte(16, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(16, nArray3, PExtInv.length);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(8, nArray, nArray2);
        if (n != 0 || nArray2[7] == -1 && Nat256.gte(nArray2, P)) {
            Nat.add33To(8, 977, nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat256.fromBigInteger(bigInteger);
        if (nArray[7] == -1 && Nat256.gte(nArray, P)) {
            Nat256.subFrom(P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(8, nArray, 0, nArray2);
        } else {
            int n = Nat256.add(nArray, P, nArray2);
            Nat.shiftDownBit(8, nArray2, n);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat256.createExt();
        Nat256.mul(nArray, nArray2, nArray4);
        SecP256K1Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.mulAddTo(nArray, nArray2, nArray3);
        if ((n != 0 || nArray3[15] == -1 && Nat.gte(16, nArray3, PExt)) && Nat.addTo(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.incAt(16, nArray3, PExtInv.length);
        }
    }

    public static void negate(int[] nArray, int[] nArray2) {
        if (Nat256.isZero(nArray)) {
            Nat256.zero(nArray2);
        } else {
            Nat256.sub(P, nArray, nArray2);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2) {
        long l = Nat256.mul33Add(977, nArray, 8, nArray, 0, nArray2, 0);
        int n = Nat256.mul33DWordAdd(977, l, nArray2, 0);
        if (n != 0 || nArray2[7] == -1 && Nat256.gte(nArray2, P)) {
            Nat.add33To(8, 977, nArray2);
        }
    }

    public static void reduce32(int n, int[] nArray) {
        if (n != 0 && Nat256.mul33WordAdd(977, n, nArray, 0) != 0 || nArray[7] == -1 && Nat256.gte(nArray, P)) {
            Nat.add33To(8, 977, nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        SecP256K1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        SecP256K1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat256.square(nArray2, nArray3);
            SecP256K1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            Nat.sub33From(8, 977, nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(16, nArray, nArray2, nArray3);
        if (n != 0 && Nat.subFrom(PExtInv.length, PExtInv, nArray3) != 0) {
            Nat.decAt(16, nArray3, PExtInv.length);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(8, nArray, 0, nArray2);
        if (n != 0 || nArray2[7] == -1 && Nat256.gte(nArray2, P)) {
            Nat.add33To(8, 977, nArray2);
        }
    }
}

