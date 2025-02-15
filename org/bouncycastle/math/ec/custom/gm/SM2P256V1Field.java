/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.gm;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public class SM2P256V1Field {
    private static final long M = 0xFFFFFFFFL;
    static final int[] P = new int[]{-1, -1, 0, -1, -1, -1, -1, -2};
    static final int[] PExt = new int[]{1, 0, -2, 1, 1, -2, 0, 2, -2, -3, 3, -2, -1, -1, 0, -2};
    private static final int P7s1 = Integer.MAX_VALUE;
    private static final int PExt15s1 = Integer.MAX_VALUE;

    public static void add(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.add(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[7] >>> 1 >= Integer.MAX_VALUE && Nat256.gte(nArray3, P)) {
            SM2P256V1Field.addPInvTo(nArray3);
        }
    }

    public static void addExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.add(16, nArray, nArray2, nArray3);
        if (n != 0 || nArray3[15] >>> 1 >= Integer.MAX_VALUE && Nat.gte(16, nArray3, PExt)) {
            Nat.subFrom(16, PExt, nArray3);
        }
    }

    public static void addOne(int[] nArray, int[] nArray2) {
        int n = Nat.inc(8, nArray, nArray2);
        if (n != 0 || nArray2[7] >>> 1 >= Integer.MAX_VALUE && Nat256.gte(nArray2, P)) {
            SM2P256V1Field.addPInvTo(nArray2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] nArray = Nat256.fromBigInteger(bigInteger);
        if (nArray[7] >>> 1 >= Integer.MAX_VALUE && Nat256.gte(nArray, P)) {
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
        SM2P256V1Field.reduce(nArray4, nArray3);
    }

    public static void multiplyAddToExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.mulAddTo(nArray, nArray2, nArray3);
        if (n != 0 || nArray3[15] >>> 1 >= Integer.MAX_VALUE && Nat.gte(16, nArray3, PExt)) {
            Nat.subFrom(16, PExt, nArray3);
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
        long l = (long)nArray[8] & 0xFFFFFFFFL;
        long l2 = (long)nArray[9] & 0xFFFFFFFFL;
        long l3 = (long)nArray[10] & 0xFFFFFFFFL;
        long l4 = (long)nArray[11] & 0xFFFFFFFFL;
        long l5 = (long)nArray[12] & 0xFFFFFFFFL;
        long l6 = (long)nArray[13] & 0xFFFFFFFFL;
        long l7 = (long)nArray[14] & 0xFFFFFFFFL;
        long l8 = (long)nArray[15] & 0xFFFFFFFFL;
        long l9 = l + l2;
        long l10 = l3 + l4;
        long l11 = l5 + l8;
        long l12 = l6 + l7;
        long l13 = l12 + (l8 << 1);
        long l14 = l9 + l12;
        long l15 = l10 + l11 + l14;
        long l16 = 0L;
        nArray2[0] = (int)(l16 += ((long)nArray[0] & 0xFFFFFFFFL) + l15 + l6 + l7 + l8);
        l16 >>= 32;
        nArray2[1] = (int)(l16 += ((long)nArray[1] & 0xFFFFFFFFL) + l15 - l + l7 + l8);
        l16 >>= 32;
        nArray2[2] = (int)(l16 += ((long)nArray[2] & 0xFFFFFFFFL) - l14);
        l16 >>= 32;
        nArray2[3] = (int)(l16 += ((long)nArray[3] & 0xFFFFFFFFL) + l15 - l2 - l3 + l6);
        l16 >>= 32;
        nArray2[4] = (int)(l16 += ((long)nArray[4] & 0xFFFFFFFFL) + l15 - l10 - l + l7);
        l16 >>= 32;
        nArray2[5] = (int)(l16 += ((long)nArray[5] & 0xFFFFFFFFL) + l13 + l3);
        l16 >>= 32;
        nArray2[6] = (int)(l16 += ((long)nArray[6] & 0xFFFFFFFFL) + l4 + l7 + l8);
        l16 >>= 32;
        nArray2[7] = (int)(l16 += ((long)nArray[7] & 0xFFFFFFFFL) + l15 + l13 + l5);
        SM2P256V1Field.reduce32((int)(l16 >>= 32), nArray2);
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
            nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) - l2);
            l >>= 32;
            nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + l2);
            if ((l >>= 32) != 0L) {
                nArray[4] = (int)(l += (long)nArray[4] & 0xFFFFFFFFL);
                l >>= 32;
                nArray[5] = (int)(l += (long)nArray[5] & 0xFFFFFFFFL);
                l >>= 32;
                nArray[6] = (int)(l += (long)nArray[6] & 0xFFFFFFFFL);
                l >>= 32;
            }
            nArray[7] = (int)(l += ((long)nArray[7] & 0xFFFFFFFFL) + l2);
            l >>= 32;
        }
        if (l != 0L || nArray[7] >>> 1 >= Integer.MAX_VALUE && Nat256.gte(nArray, P)) {
            SM2P256V1Field.addPInvTo(nArray);
        }
    }

    public static void square(int[] nArray, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        SM2P256V1Field.reduce(nArray3, nArray2);
    }

    public static void squareN(int[] nArray, int n, int[] nArray2) {
        int[] nArray3 = Nat256.createExt();
        Nat256.square(nArray, nArray3);
        SM2P256V1Field.reduce(nArray3, nArray2);
        while (--n > 0) {
            Nat256.square(nArray2, nArray3);
            SM2P256V1Field.reduce(nArray3, nArray2);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat256.sub(nArray, nArray2, nArray3);
        if (n != 0) {
            SM2P256V1Field.subPInvFrom(nArray3);
        }
    }

    public static void subtractExt(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = Nat.sub(16, nArray, nArray2, nArray3);
        if (n != 0) {
            Nat.addTo(16, PExt, nArray3);
        }
    }

    public static void twice(int[] nArray, int[] nArray2) {
        int n = Nat.shiftUpBit(8, nArray, 0, nArray2);
        if (n != 0 || nArray2[7] >>> 1 >= Integer.MAX_VALUE && Nat256.gte(nArray2, P)) {
            SM2P256V1Field.addPInvTo(nArray2);
        }
    }

    private static void addPInvTo(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) + 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) - 1L);
        l >>= 32;
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) + 1L);
        if ((l >>= 32) != 0L) {
            nArray[4] = (int)(l += (long)nArray[4] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[5] = (int)(l += (long)nArray[5] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[6] = (int)(l += (long)nArray[6] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[7] = (int)(l += ((long)nArray[7] & 0xFFFFFFFFL) + 1L);
    }

    private static void subPInvFrom(int[] nArray) {
        long l = ((long)nArray[0] & 0xFFFFFFFFL) - 1L;
        nArray[0] = (int)l;
        if ((l >>= 32) != 0L) {
            nArray[1] = (int)(l += (long)nArray[1] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[2] = (int)(l += ((long)nArray[2] & 0xFFFFFFFFL) + 1L);
        l >>= 32;
        nArray[3] = (int)(l += ((long)nArray[3] & 0xFFFFFFFFL) - 1L);
        if ((l >>= 32) != 0L) {
            nArray[4] = (int)(l += (long)nArray[4] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[5] = (int)(l += (long)nArray[5] & 0xFFFFFFFFL);
            l >>= 32;
            nArray[6] = (int)(l += (long)nArray[6] & 0xFFFFFFFFL);
            l >>= 32;
        }
        nArray[7] = (int)(l += ((long)nArray[7] & 0xFFFFFFFFL) - 1L);
    }
}

