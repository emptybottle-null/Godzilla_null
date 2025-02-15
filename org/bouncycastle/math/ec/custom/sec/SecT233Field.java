/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat256;

public class SecT233Field {
    private static final long M41 = 0x1FFFFFFFFFFL;
    private static final long M59 = 0x7FFFFFFFFFFFFFFL;

    public static void add(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
    }

    public static void addExt(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
        lArray3[4] = lArray[4] ^ lArray2[4];
        lArray3[5] = lArray[5] ^ lArray2[5];
        lArray3[6] = lArray[6] ^ lArray2[6];
        lArray3[7] = lArray[7] ^ lArray2[7];
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
        lArray2[3] = lArray[3];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat256.fromBigInteger64(bigInteger);
        SecT233Field.reduce23(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat256.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat256.create64();
        long[] lArray4 = Nat256.create64();
        SecT233Field.square(lArray, lArray3);
        SecT233Field.multiply(lArray3, lArray, lArray3);
        SecT233Field.square(lArray3, lArray3);
        SecT233Field.multiply(lArray3, lArray, lArray3);
        SecT233Field.squareN(lArray3, 3, lArray4);
        SecT233Field.multiply(lArray4, lArray3, lArray4);
        SecT233Field.square(lArray4, lArray4);
        SecT233Field.multiply(lArray4, lArray, lArray4);
        SecT233Field.squareN(lArray4, 7, lArray3);
        SecT233Field.multiply(lArray3, lArray4, lArray3);
        SecT233Field.squareN(lArray3, 14, lArray4);
        SecT233Field.multiply(lArray4, lArray3, lArray4);
        SecT233Field.square(lArray4, lArray4);
        SecT233Field.multiply(lArray4, lArray, lArray4);
        SecT233Field.squareN(lArray4, 29, lArray3);
        SecT233Field.multiply(lArray3, lArray4, lArray3);
        SecT233Field.squareN(lArray3, 58, lArray4);
        SecT233Field.multiply(lArray4, lArray3, lArray4);
        SecT233Field.squareN(lArray4, 116, lArray3);
        SecT233Field.multiply(lArray3, lArray4, lArray3);
        SecT233Field.square(lArray3, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat256.createExt64();
        SecT233Field.implMultiply(lArray, lArray2, lArray4);
        SecT233Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat256.createExt64();
        SecT233Field.implMultiply(lArray, lArray2, lArray4);
        SecT233Field.addExt(lArray3, lArray4, lArray3);
    }

    public static void reduce(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        long l6 = lArray[5];
        long l7 = lArray[6];
        long l8 = lArray[7];
        l4 ^= l8 << 23;
        l5 ^= l8 >>> 41 ^ l8 << 33;
        l3 ^= l7 << 23;
        l4 ^= l7 >>> 41 ^ l7 << 33;
        l2 ^= (l6 ^= l8 >>> 31) << 23;
        l3 ^= l6 >>> 41 ^ l6 << 33;
        long l9 = (l4 ^= l6 >>> 31) >>> 41;
        lArray2[0] = (l ^= (l5 ^= l7 >>> 31) << 23) ^ l9;
        lArray2[1] = (l2 ^= l5 >>> 41 ^ l5 << 33) ^ l9 << 10;
        lArray2[2] = l3 ^= l5 >>> 31;
        lArray2[3] = l4 & 0x1FFFFFFFFFFL;
    }

    public static void reduce23(long[] lArray, int n) {
        long l = lArray[n + 3];
        long l2 = l >>> 41;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ l2;
        int n3 = n + 1;
        lArray[n3] = lArray[n3] ^ l2 << 10;
        lArray[n + 3] = l & 0x1FFFFFFFFFFL;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT233Field.implSquare(lArray, lArray3);
        SecT233Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT233Field.implSquare(lArray, lArray3);
        SecT233Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT233Field.implSquare(lArray, lArray3);
        SecT233Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT233Field.implSquare(lArray2, lArray3);
            SecT233Field.reduce(lArray3, lArray2);
        }
    }

    public static void sqrt(long[] lArray, long[] lArray2) {
        long l = Interleave.unshuffle(lArray[0]);
        long l2 = Interleave.unshuffle(lArray[1]);
        long l3 = l & 0xFFFFFFFFL | l2 << 32;
        long l4 = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[2]);
        l2 = Interleave.unshuffle(lArray[3]);
        long l5 = l & 0xFFFFFFFFL | l2 << 32;
        long l6 = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        long l7 = l6 >>> 27;
        l6 ^= l4 >>> 27 | l6 << 37;
        l4 ^= l4 << 37;
        long[] lArray3 = Nat256.createExt64();
        int[] nArray = new int[]{32, 117, 191};
        for (int i = 0; i < nArray.length; ++i) {
            int n = nArray[i] >>> 6;
            int n2 = nArray[i] & 0x3F;
            int n3 = n;
            lArray3[n3] = lArray3[n3] ^ l4 << n2;
            int n4 = n + 1;
            lArray3[n4] = lArray3[n4] ^ (l6 << n2 | l4 >>> -n2);
            int n5 = n + 2;
            lArray3[n5] = lArray3[n5] ^ (l7 << n2 | l6 >>> -n2);
            int n6 = n + 3;
            lArray3[n6] = lArray3[n6] ^ l7 >>> -n2;
        }
        SecT233Field.reduce(lArray3, lArray2);
        lArray2[0] = lArray2[0] ^ l3;
        lArray2[1] = lArray2[1] ^ l5;
    }

    public static int trace(long[] lArray) {
        return (int)(lArray[0] ^ lArray[2] >>> 31) & 1;
    }

    protected static void implCompactExt(long[] lArray) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        long l6 = lArray[5];
        long l7 = lArray[6];
        long l8 = lArray[7];
        lArray[0] = l ^ l2 << 59;
        lArray[1] = l2 >>> 5 ^ l3 << 54;
        lArray[2] = l3 >>> 10 ^ l4 << 49;
        lArray[3] = l4 >>> 15 ^ l5 << 44;
        lArray[4] = l5 >>> 20 ^ l6 << 39;
        lArray[5] = l6 >>> 25 ^ l7 << 34;
        lArray[6] = l7 >>> 30 ^ l8 << 29;
        lArray[7] = l8 >>> 35;
    }

    protected static void implExpand(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        lArray2[0] = l & 0x7FFFFFFFFFFFFFFL;
        lArray2[1] = (l >>> 59 ^ l2 << 5) & 0x7FFFFFFFFFFFFFFL;
        lArray2[2] = (l2 >>> 54 ^ l3 << 10) & 0x7FFFFFFFFFFFFFFL;
        lArray2[3] = l3 >>> 49 ^ l4 << 15;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        int n;
        long[] lArray4 = new long[4];
        long[] lArray5 = new long[4];
        SecT233Field.implExpand(lArray, lArray4);
        SecT233Field.implExpand(lArray2, lArray5);
        SecT233Field.implMulwAcc(lArray4[0], lArray5[0], lArray3, 0);
        SecT233Field.implMulwAcc(lArray4[1], lArray5[1], lArray3, 1);
        SecT233Field.implMulwAcc(lArray4[2], lArray5[2], lArray3, 2);
        SecT233Field.implMulwAcc(lArray4[3], lArray5[3], lArray3, 3);
        for (n = 5; n > 0; --n) {
            int n2 = n;
            lArray3[n2] = lArray3[n2] ^ lArray3[n - 1];
        }
        SecT233Field.implMulwAcc(lArray4[0] ^ lArray4[1], lArray5[0] ^ lArray5[1], lArray3, 1);
        SecT233Field.implMulwAcc(lArray4[2] ^ lArray4[3], lArray5[2] ^ lArray5[3], lArray3, 3);
        for (n = 7; n > 1; --n) {
            int n3 = n;
            lArray3[n3] = lArray3[n3] ^ lArray3[n - 2];
        }
        long l = lArray4[0] ^ lArray4[2];
        long l2 = lArray4[1] ^ lArray4[3];
        long l3 = lArray5[0] ^ lArray5[2];
        long l4 = lArray5[1] ^ lArray5[3];
        SecT233Field.implMulwAcc(l ^ l2, l3 ^ l4, lArray3, 3);
        long[] lArray6 = new long[3];
        SecT233Field.implMulwAcc(l, l3, lArray6, 0);
        SecT233Field.implMulwAcc(l2, l4, lArray6, 1);
        long l5 = lArray6[0];
        long l6 = lArray6[1];
        long l7 = lArray6[2];
        lArray3[2] = lArray3[2] ^ l5;
        lArray3[3] = lArray3[3] ^ (l5 ^ l6);
        lArray3[4] = lArray3[4] ^ (l7 ^ l6);
        lArray3[5] = lArray3[5] ^ l7;
        SecT233Field.implCompactExt(lArray3);
    }

    protected static void implMulwAcc(long l, long l2, long[] lArray, int n) {
        long[] lArray2 = new long[8];
        lArray2[1] = l2;
        lArray2[2] = lArray2[1] << 1;
        lArray2[3] = lArray2[2] ^ l2;
        lArray2[4] = lArray2[2] << 1;
        lArray2[5] = lArray2[4] ^ l2;
        lArray2[6] = lArray2[3] << 1;
        lArray2[7] = lArray2[6] ^ l2;
        int n2 = (int)l;
        long l3 = 0L;
        long l4 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3;
        int n3 = 54;
        do {
            n2 = (int)(l >>> n3);
            long l5 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3;
            l4 ^= l5 << n3;
            l3 ^= l5 >>> -n3;
        } while ((n3 -= 6) > 0);
        int n4 = n;
        lArray[n4] = lArray[n4] ^ l4 & 0x7FFFFFFFFFFFFFFL;
        int n5 = n + 1;
        lArray[n5] = lArray[n5] ^ (l4 >>> 59 ^ l3 << 5);
    }

    protected static void implSquare(long[] lArray, long[] lArray2) {
        Interleave.expand64To128(lArray[0], lArray2, 0);
        Interleave.expand64To128(lArray[1], lArray2, 2);
        Interleave.expand64To128(lArray[2], lArray2, 4);
        long l = lArray[3];
        lArray2[6] = Interleave.expand32to64((int)l);
        lArray2[7] = (long)Interleave.expand16to32((int)(l >>> 32)) & 0xFFFFFFFFL;
    }
}

