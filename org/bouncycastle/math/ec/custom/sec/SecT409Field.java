/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat448;

public class SecT409Field {
    private static final long M25 = 0x1FFFFFFL;
    private static final long M59 = 0x7FFFFFFFFFFFFFFL;

    public static void add(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
        lArray3[4] = lArray[4] ^ lArray2[4];
        lArray3[5] = lArray[5] ^ lArray2[5];
        lArray3[6] = lArray[6] ^ lArray2[6];
    }

    public static void addExt(long[] lArray, long[] lArray2, long[] lArray3) {
        for (int i = 0; i < 13; ++i) {
            lArray3[i] = lArray[i] ^ lArray2[i];
        }
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
        lArray2[3] = lArray[3];
        lArray2[4] = lArray[4];
        lArray2[5] = lArray[5];
        lArray2[6] = lArray[6];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat448.fromBigInteger64(bigInteger);
        SecT409Field.reduce39(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat448.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat448.create64();
        long[] lArray4 = Nat448.create64();
        long[] lArray5 = Nat448.create64();
        SecT409Field.square(lArray, lArray3);
        SecT409Field.squareN(lArray3, 1, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray4, 1, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 3, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 6, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 12, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray5);
        SecT409Field.squareN(lArray5, 24, lArray3);
        SecT409Field.squareN(lArray3, 24, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 48, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 96, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.squareN(lArray3, 192, lArray4);
        SecT409Field.multiply(lArray3, lArray4, lArray3);
        SecT409Field.multiply(lArray3, lArray5, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat448.createExt64();
        SecT409Field.implMultiply(lArray, lArray2, lArray4);
        SecT409Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat448.createExt64();
        SecT409Field.implMultiply(lArray, lArray2, lArray4);
        SecT409Field.addExt(lArray3, lArray4, lArray3);
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
        long l9 = lArray[12];
        l6 ^= l9 << 39;
        l7 ^= l9 >>> 25 ^ l9 << 62;
        l8 ^= l9 >>> 2;
        l9 = lArray[11];
        l5 ^= l9 << 39;
        l6 ^= l9 >>> 25 ^ l9 << 62;
        l7 ^= l9 >>> 2;
        l9 = lArray[10];
        l4 ^= l9 << 39;
        l5 ^= l9 >>> 25 ^ l9 << 62;
        l6 ^= l9 >>> 2;
        l9 = lArray[9];
        l3 ^= l9 << 39;
        l4 ^= l9 >>> 25 ^ l9 << 62;
        l5 ^= l9 >>> 2;
        l9 = lArray[8];
        l2 ^= l9 << 39;
        l3 ^= l9 >>> 25 ^ l9 << 62;
        l4 ^= l9 >>> 2;
        l9 = l8;
        long l10 = l7 >>> 25;
        lArray2[0] = (l ^= l9 << 39) ^ l10;
        lArray2[1] = (l2 ^= l9 >>> 25 ^ l9 << 62) ^ l10 << 23;
        lArray2[2] = l3 ^= l9 >>> 2;
        lArray2[3] = l4;
        lArray2[4] = l5;
        lArray2[5] = l6;
        lArray2[6] = l7 & 0x1FFFFFFL;
    }

    public static void reduce39(long[] lArray, int n) {
        long l = lArray[n + 6];
        long l2 = l >>> 25;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ l2;
        int n3 = n + 1;
        lArray[n3] = lArray[n3] ^ l2 << 23;
        lArray[n + 6] = l & 0x1FFFFFFL;
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
        l = Interleave.unshuffle(lArray[4]);
        l2 = Interleave.unshuffle(lArray[5]);
        long l7 = l & 0xFFFFFFFFL | l2 << 32;
        long l8 = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[6]);
        long l9 = l & 0xFFFFFFFFL;
        long l10 = l >>> 32;
        lArray2[0] = l3 ^ l4 << 44;
        lArray2[1] = l5 ^ l6 << 44 ^ l4 >>> 20;
        lArray2[2] = l7 ^ l8 << 44 ^ l6 >>> 20;
        lArray2[3] = l9 ^ l10 << 44 ^ l8 >>> 20 ^ l4 << 13;
        lArray2[4] = l10 >>> 20 ^ l6 << 13 ^ l4 >>> 51;
        lArray2[5] = l8 << 13 ^ l6 >>> 51;
        lArray2[6] = l10 << 13 ^ l8 >>> 51;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(13);
        SecT409Field.implSquare(lArray, lArray3);
        SecT409Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(13);
        SecT409Field.implSquare(lArray, lArray3);
        SecT409Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat.create64(13);
        SecT409Field.implSquare(lArray, lArray3);
        SecT409Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT409Field.implSquare(lArray2, lArray3);
            SecT409Field.reduce(lArray3, lArray2);
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
        long l9 = lArray[8];
        long l10 = lArray[9];
        long l11 = lArray[10];
        long l12 = lArray[11];
        long l13 = lArray[12];
        long l14 = lArray[13];
        lArray[0] = l ^ l2 << 59;
        lArray[1] = l2 >>> 5 ^ l3 << 54;
        lArray[2] = l3 >>> 10 ^ l4 << 49;
        lArray[3] = l4 >>> 15 ^ l5 << 44;
        lArray[4] = l5 >>> 20 ^ l6 << 39;
        lArray[5] = l6 >>> 25 ^ l7 << 34;
        lArray[6] = l7 >>> 30 ^ l8 << 29;
        lArray[7] = l8 >>> 35 ^ l9 << 24;
        lArray[8] = l9 >>> 40 ^ l10 << 19;
        lArray[9] = l10 >>> 45 ^ l11 << 14;
        lArray[10] = l11 >>> 50 ^ l12 << 9;
        lArray[11] = l12 >>> 55 ^ l13 << 4 ^ l14 << 63;
        lArray[12] = l13 >>> 60 ^ l14 >>> 1;
        lArray[13] = 0L;
    }

    protected static void implExpand(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        long l6 = lArray[5];
        long l7 = lArray[6];
        lArray2[0] = l & 0x7FFFFFFFFFFFFFFL;
        lArray2[1] = (l >>> 59 ^ l2 << 5) & 0x7FFFFFFFFFFFFFFL;
        lArray2[2] = (l2 >>> 54 ^ l3 << 10) & 0x7FFFFFFFFFFFFFFL;
        lArray2[3] = (l3 >>> 49 ^ l4 << 15) & 0x7FFFFFFFFFFFFFFL;
        lArray2[4] = (l4 >>> 44 ^ l5 << 20) & 0x7FFFFFFFFFFFFFFL;
        lArray2[5] = (l5 >>> 39 ^ l6 << 25) & 0x7FFFFFFFFFFFFFFL;
        lArray2[6] = l6 >>> 34 ^ l7 << 30;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = new long[7];
        long[] lArray5 = new long[7];
        SecT409Field.implExpand(lArray, lArray4);
        SecT409Field.implExpand(lArray2, lArray5);
        for (int i = 0; i < 7; ++i) {
            SecT409Field.implMulwAcc(lArray4, lArray5[i], lArray3, i);
        }
        SecT409Field.implCompactExt(lArray3);
    }

    protected static void implMulwAcc(long[] lArray, long l, long[] lArray2, int n) {
        long[] lArray3 = new long[8];
        lArray3[1] = l;
        lArray3[2] = lArray3[1] << 1;
        lArray3[3] = lArray3[2] ^ l;
        lArray3[4] = lArray3[2] << 1;
        lArray3[5] = lArray3[4] ^ l;
        lArray3[6] = lArray3[3] << 1;
        lArray3[7] = lArray3[6] ^ l;
        for (int i = 0; i < 7; ++i) {
            long l2 = lArray[i];
            int n2 = (int)l2;
            long l3 = 0L;
            long l4 = lArray3[n2 & 7] ^ lArray3[n2 >>> 3 & 7] << 3;
            int n3 = 54;
            do {
                n2 = (int)(l2 >>> n3);
                long l5 = lArray3[n2 & 7] ^ lArray3[n2 >>> 3 & 7] << 3;
                l4 ^= l5 << n3;
                l3 ^= l5 >>> -n3;
            } while ((n3 -= 6) > 0);
            int n4 = n + i;
            lArray2[n4] = lArray2[n4] ^ l4 & 0x7FFFFFFFFFFFFFFL;
            int n5 = n + i + 1;
            lArray2[n5] = lArray2[n5] ^ (l4 >>> 59 ^ l3 << 5);
        }
    }

    protected static void implSquare(long[] lArray, long[] lArray2) {
        for (int i = 0; i < 6; ++i) {
            Interleave.expand64To128(lArray[i], lArray2, i << 1);
        }
        lArray2[12] = Interleave.expand32to64((int)lArray[6]);
    }
}

