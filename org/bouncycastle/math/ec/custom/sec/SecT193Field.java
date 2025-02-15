/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat256;

public class SecT193Field {
    private static final long M01 = 1L;
    private static final long M49 = 0x1FFFFFFFFFFFFL;

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
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
        lArray2[3] = lArray[3];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat256.fromBigInteger64(bigInteger);
        SecT193Field.reduce63(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat256.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat256.create64();
        long[] lArray4 = Nat256.create64();
        SecT193Field.square(lArray, lArray3);
        SecT193Field.squareN(lArray3, 1, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray4, 1, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 3, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 6, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 12, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 24, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 48, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray3);
        SecT193Field.squareN(lArray3, 96, lArray4);
        SecT193Field.multiply(lArray3, lArray4, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat256.createExt64();
        SecT193Field.implMultiply(lArray, lArray2, lArray4);
        SecT193Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat256.createExt64();
        SecT193Field.implMultiply(lArray, lArray2, lArray4);
        SecT193Field.addExt(lArray3, lArray4, lArray3);
    }

    public static void reduce(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        long l6 = lArray[5];
        long l7 = lArray[6];
        l3 ^= l7 << 63;
        l4 ^= l7 >>> 1 ^ l7 << 14;
        l2 ^= l6 << 63;
        l3 ^= l6 >>> 1 ^ l6 << 14;
        long l8 = (l4 ^= l6 >>> 50) >>> 1;
        lArray2[0] = (l ^= (l5 ^= l7 >>> 50) << 63) ^ l8 ^ l8 << 15;
        lArray2[1] = (l2 ^= l5 >>> 1 ^ l5 << 14) ^ l8 >>> 49;
        lArray2[2] = l3 ^= l5 >>> 50;
        lArray2[3] = l4 & 1L;
    }

    public static void reduce63(long[] lArray, int n) {
        long l = lArray[n + 3];
        long l2 = l >>> 1;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ (l2 ^ l2 << 15);
        int n3 = n + 1;
        lArray[n3] = lArray[n3] ^ l2 >>> 49;
        lArray[n + 3] = l & 1L;
    }

    public static void sqrt(long[] lArray, long[] lArray2) {
        long l = Interleave.unshuffle(lArray[0]);
        long l2 = Interleave.unshuffle(lArray[1]);
        long l3 = l & 0xFFFFFFFFL | l2 << 32;
        long l4 = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[2]);
        long l5 = l & 0xFFFFFFFFL ^ lArray[3] << 32;
        long l6 = l >>> 32;
        lArray2[0] = l3 ^ l4 << 8;
        lArray2[1] = l5 ^ l6 << 8 ^ l4 >>> 56 ^ l4 << 33;
        lArray2[2] = l6 >>> 56 ^ l6 << 33 ^ l4 >>> 31;
        lArray2[3] = l6 >>> 31;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT193Field.implSquare(lArray, lArray3);
        SecT193Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT193Field.implSquare(lArray, lArray3);
        SecT193Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat256.createExt64();
        SecT193Field.implSquare(lArray, lArray3);
        SecT193Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT193Field.implSquare(lArray2, lArray3);
            SecT193Field.reduce(lArray3, lArray2);
        }
    }

    public static int trace(long[] lArray) {
        return (int)lArray[0] & 1;
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
        lArray[0] = l ^ l2 << 49;
        lArray[1] = l2 >>> 15 ^ l3 << 34;
        lArray[2] = l3 >>> 30 ^ l4 << 19;
        lArray[3] = l4 >>> 45 ^ l5 << 4 ^ l6 << 53;
        lArray[4] = l5 >>> 60 ^ l7 << 38 ^ l6 >>> 11;
        lArray[5] = l7 >>> 26 ^ l8 << 23;
        lArray[6] = l8 >>> 41;
        lArray[7] = 0L;
    }

    protected static void implExpand(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        lArray2[0] = l & 0x1FFFFFFFFFFFFL;
        lArray2[1] = (l >>> 49 ^ l2 << 15) & 0x1FFFFFFFFFFFFL;
        lArray2[2] = (l2 >>> 34 ^ l3 << 30) & 0x1FFFFFFFFFFFFL;
        lArray2[3] = l3 >>> 19 ^ l4 << 45;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        int n;
        long[] lArray4 = new long[4];
        long[] lArray5 = new long[4];
        SecT193Field.implExpand(lArray, lArray4);
        SecT193Field.implExpand(lArray2, lArray5);
        SecT193Field.implMulwAcc(lArray4[0], lArray5[0], lArray3, 0);
        SecT193Field.implMulwAcc(lArray4[1], lArray5[1], lArray3, 1);
        SecT193Field.implMulwAcc(lArray4[2], lArray5[2], lArray3, 2);
        SecT193Field.implMulwAcc(lArray4[3], lArray5[3], lArray3, 3);
        for (n = 5; n > 0; --n) {
            int n2 = n;
            lArray3[n2] = lArray3[n2] ^ lArray3[n - 1];
        }
        SecT193Field.implMulwAcc(lArray4[0] ^ lArray4[1], lArray5[0] ^ lArray5[1], lArray3, 1);
        SecT193Field.implMulwAcc(lArray4[2] ^ lArray4[3], lArray5[2] ^ lArray5[3], lArray3, 3);
        for (n = 7; n > 1; --n) {
            int n3 = n;
            lArray3[n3] = lArray3[n3] ^ lArray3[n - 2];
        }
        long l = lArray4[0] ^ lArray4[2];
        long l2 = lArray4[1] ^ lArray4[3];
        long l3 = lArray5[0] ^ lArray5[2];
        long l4 = lArray5[1] ^ lArray5[3];
        SecT193Field.implMulwAcc(l ^ l2, l3 ^ l4, lArray3, 3);
        long[] lArray6 = new long[3];
        SecT193Field.implMulwAcc(l, l3, lArray6, 0);
        SecT193Field.implMulwAcc(l2, l4, lArray6, 1);
        long l5 = lArray6[0];
        long l6 = lArray6[1];
        long l7 = lArray6[2];
        lArray3[2] = lArray3[2] ^ l5;
        lArray3[3] = lArray3[3] ^ (l5 ^ l6);
        lArray3[4] = lArray3[4] ^ (l7 ^ l6);
        lArray3[5] = lArray3[5] ^ l7;
        SecT193Field.implCompactExt(lArray3);
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
        int n3 = 36;
        do {
            n2 = (int)(l >>> n3);
            long l5 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3 ^ lArray2[n2 >>> 6 & 7] << 6 ^ lArray2[n2 >>> 9 & 7] << 9 ^ lArray2[n2 >>> 12 & 7] << 12;
            l4 ^= l5 << n3;
            l3 ^= l5 >>> -n3;
        } while ((n3 -= 15) > 0);
        int n4 = n;
        lArray[n4] = lArray[n4] ^ l4 & 0x1FFFFFFFFFFFFL;
        int n5 = n + 1;
        lArray[n5] = lArray[n5] ^ (l4 >>> 49 ^ l3 << 15);
    }

    protected static void implSquare(long[] lArray, long[] lArray2) {
        Interleave.expand64To128(lArray[0], lArray2, 0);
        Interleave.expand64To128(lArray[1], lArray2, 2);
        Interleave.expand64To128(lArray[2], lArray2, 4);
        lArray2[6] = lArray[3] & 1L;
    }
}

