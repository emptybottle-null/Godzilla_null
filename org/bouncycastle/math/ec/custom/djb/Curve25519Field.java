/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.djb;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class Curve25519Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int P7 = Integer.MAX_VALUE;
    private static final int[] PExt = new int[]{361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, 0x3FFFFFFF};
    private static final int PInv = 19;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat256.add(nArray, nArray2, nArray3);
        if (Nat256.gte(nArray3, P)) {
            Curve25519Field.subPFrom(nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat.add(16, nArray, nArray2, nArray3);
        if (Nat.gte(16, nArray3, PExt)) {
            Curve25519Field.subPExtFrom(nArray3);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        Nat.inc(8, nArray, nArray2);
        if (Nat256.gte(nArray2, P)) {
            Curve25519Field.subPFrom(nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat256.fromBigInteger(bigInteger);
        while (Nat256.gte(nArray, P)) {
            Nat256.subFrom(P, nArray);
        }
        return nArray;
    }

    public static void half(int[] nArray, int[] nArray2) {
        if ((nArray[0] & 1) == 0) {
            Nat.shiftDownBit(8, nArray, 0, nArray2);
        } else {
            Nat256.add(nArray, P, nArray2);
            Nat.shiftDownBit(8, nArray2, 0);
        }
    }

    public static void multiply(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat256.createExt();
        Nat256.mul(nArray, nArray2, nArray4);
        Curve25519Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat256.mulAddTo(nArray, nArray2, nArray3);
        if (Nat.gte(16, nArray3, PExt)) {
            Curve25519Field.subPExtFrom(nArray3);
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
        int n = nArray[7];
        Nat.shiftUpBit(8, nArray, 8, n, nArray2, 0);
        int n2 = Nat256.mulByWordAddTo(19, nArray, nArray2) << 1;
        int n3 = nArray2[7];
        n2 += (n3 >>> 31) - (n >>> 31);
        n3 &= Integer.MAX_VALUE;
        nArray2[7] = n3 += Nat.addWordTo(7, n2 * 19, nArray2);
        if (Nat256.gte(nArray2, P)) {
            Curve25519Field.subPFrom(nArray2);
        }
    }

    public static void reduce27(int n, int[] nArray) {
        int n2 = nArray[7];
        int n3 = n << 1 | n2 >>> 31;
        n2 &= Integer.MAX_VALUE;
        nArray[7] = n2 += Nat.addWordTo(7, n3 * 19, nArray);
        if (Nat256.gte(nArray, P)) {
            Curve25519Field.subPFrom(nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        Curve25519Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        Curve25519Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat256.square(nArray2, nArray3);
            Curve25519Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            Curve25519Field.addPTo(nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(16, nArray, nArray2, nArray3);
        if (n != 0) {
            Curve25519Field.addPExtTo(nArray3);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        Nat.shiftUpBit(8, nArray, 0, nArray2);
        if (Nat256.gte(nArray2, P)) {
            Curve25519Field.subPFrom(nArray2);
        }
    }

    private static int addPTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - 19L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            l = Nat.decAt(7, nArray, 1);
        }
        nArray[7] = (int)(l += ((long)nArray[7] & 0xFFFFFFFFL) + 0x80000000L);
        return (int)(l >>= 32);
    }

    private static int addPExtTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + ((long)PExt[0] & 0xFFFFFFFFL);
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            l = Nat.incAt(8, nArray, 1);
        }
        nArray[8] = (int)(l += ((long)nArray[8] & 0xFFFFFFFFL) - 19L);
        if ((l >>= 32) != 0L) {
            l = Nat.decAt(15, nArray, 9);
        }
        nArray[15] = (int)(l += ((long)nArray[15] & 0xFFFFFFFFL) + ((long)(PExt[15] + 1) & 0xFFFFFFFFL));
        return (int)(l >>= 32);
    }

    private static int subPFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + 19L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            l = Nat.incAt(7, nArray, 1);
        }
        nArray[7] = (int)(l += ((long)nArray[7] & 0xFFFFFFFFL) - 0x80000000L);
        return (int)(l >>= 32);
    }

    private static int subPExtFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - ((long)PExt[0] & 0xFFFFFFFFL);
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            l = Nat.decAt(8, nArray, 1);
        }
        nArray[8] = (int)(l += ((long)nArray[8] & 0xFFFFFFFFL) + 19L);
        if ((l >>= 32) != 0L) {
            l = Nat.incAt(15, nArray, 9);
        }
        nArray[15] = (int)(l += ((long)nArray[15] & 0xFFFFFFFFL) - ((long)(PExt[15] + 1) & 0xFFFFFFFFL));
        return (int)(l >>= 32);
    }
}

