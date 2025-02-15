/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public class SecT131Field {
    private static final long M03 = 7L;
    private static final long M44 = 0xFFFFFFFFFFFL;
    private static final long[] ROOT_Z = new long[]{2791191049453778211L, 2791191049453778402L, 6L};

    public static void add(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
    }

    public static void addExt(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
        lArray3[4] = lArray[4] ^ lArray2[4];
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat192.fromBigInteger64(bigInteger);
        SecT131Field.reduce61(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat192.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat192.create64();
        long[] lArray4 = Nat192.create64();
        SecT131Field.square(lArray, lArray3);
        SecT131Field.multiply(lArray3, lArray, lArray3);
        SecT131Field.squareN(lArray3, 2, lArray4);
        SecT131Field.multiply(lArray4, lArray3, lArray4);
        SecT131Field.squareN(lArray4, 4, lArray3);
        SecT131Field.multiply(lArray3, lArray4, lArray3);
        SecT131Field.squareN(lArray3, 8, lArray4);
        SecT131Field.multiply(lArray4, lArray3, lArray4);
        SecT131Field.squareN(lArray4, 16, lArray3);
        SecT131Field.multiply(lArray3, lArray4, lArray3);
        SecT131Field.squareN(lArray3, 32, lArray4);
        SecT131Field.multiply(lArray4, lArray3, lArray4);
        SecT131Field.square(lArray4, lArray4);
        SecT131Field.multiply(lArray4, lArray, lArray4);
        SecT131Field.squareN(lArray4, 65, lArray3);
        SecT131Field.multiply(lArray3, lArray4, lArray3);
        SecT131Field.square(lArray3, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat192.createExt64();
        SecT131Field.implMultiply(lArray, lArray2, lArray4);
        SecT131Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat192.createExt64();
        SecT131Field.implMultiply(lArray, lArray2, lArray4);
        SecT131Field.addExt(lArray3, lArray4, lArray3);
    }

    public static void reduce(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        l2 ^= l5 << 61 ^ l5 << 63;
        l3 ^= l5 >>> 3 ^ l5 >>> 1 ^ l5 ^ l5 << 5;
        long l6 = (l3 ^= l4 >>> 59) >>> 3;
        lArray2[0] = (l ^= (l4 ^= l5 >>> 59) << 61 ^ l4 << 63) ^ l6 ^ l6 << 2 ^ l6 << 3 ^ l6 << 8;
        lArray2[1] = (l2 ^= l4 >>> 3 ^ l4 >>> 1 ^ l4 ^ l4 << 5) ^ l6 >>> 56;
        lArray2[2] = l3 & 7L;
    }

    public static void reduce61(long[] lArray, int n) {
        long l = lArray[n + 2];
        long l2 = l >>> 3;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ (l2 ^ l2 << 2 ^ l2 << 3 ^ l2 << 8);
        int n3 = n + 1;
        lArray[n3] = lArray[n3] ^ l2 >>> 56;
        lArray[n + 2] = l & 7L;
    }

    public static void sqrt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat192.create64();
        long l = Interleave.unshuffle(lArray[0]);
        long l2 = Interleave.unshuffle(lArray[1]);
        long l3 = l & 0xFFFFFFFFL | l2 << 32;
        lArray3[0] = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[2]);
        long l4 = l & 0xFFFFFFFFL;
        lArray3[1] = l >>> 32;
        SecT131Field.multiply(lArray3, ROOT_Z, lArray2);
        lArray2[0] = lArray2[0] ^ l3;
        lArray2[1] = lArray2[1] ^ l4;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(5);
        SecT131Field.implSquare(lArray, lArray3);
        SecT131Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(5);
        SecT131Field.implSquare(lArray, lArray3);
        SecT131Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat.create64(5);
        SecT131Field.implSquare(lArray, lArray3);
        SecT131Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT131Field.implSquare(lArray2, lArray3);
            SecT131Field.reduce(lArray3, lArray2);
        }
    }

    public static int trace(long[] lArray) {
        return (int)(lArray[0] ^ lArray[1] >>> 59 ^ lArray[2] >>> 1) & 1;
    }

    protected static void implCompactExt(long[] lArray) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        long l6 = lArray[5];
        lArray[0] = l ^ l2 << 44;
        lArray[1] = l2 >>> 20 ^ l3 << 24;
        lArray[2] = l3 >>> 40 ^ l4 << 4 ^ l5 << 48;
        lArray[3] = l4 >>> 60 ^ l6 << 28 ^ l5 >>> 16;
        lArray[4] = l6 >>> 36;
        lArray[5] = 0L;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        l3 = (l2 >>> 24 ^ l3 << 40) & 0xFFFFFFFFFFFL;
        l2 = (l >>> 44 ^ l2 << 20) & 0xFFFFFFFFFFFL;
        l &= 0xFFFFFFFFFFFL;
        long l4 = lArray2[0];
        long l5 = lArray2[1];
        long l6 = lArray2[2];
        l6 = (l5 >>> 24 ^ l6 << 40) & 0xFFFFFFFFFFFL;
        l5 = (l4 >>> 44 ^ l5 << 20) & 0xFFFFFFFFFFFL;
        long[] lArray4 = new long[10];
        SecT131Field.implMulw(l, l4 &= 0xFFFFFFFFFFFL, lArray4, 0);
        SecT131Field.implMulw(l3, l6, lArray4, 2);
        long l7 = l ^ l2 ^ l3;
        long l8 = l4 ^ l5 ^ l6;
        SecT131Field.implMulw(l7, l8, lArray4, 4);
        long l9 = l2 << 1 ^ l3 << 2;
        long l10 = l5 << 1 ^ l6 << 2;
        SecT131Field.implMulw(l ^ l9, l4 ^ l10, lArray4, 6);
        SecT131Field.implMulw(l7 ^ l9, l8 ^ l10, lArray4, 8);
        long l11 = lArray4[6] ^ lArray4[8];
        long l12 = lArray4[7] ^ lArray4[9];
        long l13 = l11 << 1 ^ lArray4[6];
        long l14 = l11 ^ l12 << 1 ^ lArray4[7];
        long l15 = l12;
        long l16 = lArray4[0];
        long l17 = lArray4[1] ^ lArray4[0] ^ lArray4[4];
        long l18 = lArray4[1] ^ lArray4[5];
        long l19 = l16 ^ l13 ^ lArray4[2] << 4 ^ lArray4[2] << 1;
        long l20 = l17 ^ l14 ^ lArray4[3] << 4 ^ lArray4[3] << 1;
        long l21 = l18 ^ l15;
        l20 ^= l19 >>> 44;
        l19 &= 0xFFFFFFFFFFFL;
        l21 ^= l20 >>> 44;
        l19 = l19 >>> 1 ^ ((l20 &= 0xFFFFFFFFFFFL) & 1L) << 43;
        l20 = l20 >>> 1 ^ (l21 & 1L) << 43;
        l21 >>>= 1;
        l19 ^= l19 << 1;
        l19 ^= l19 << 2;
        l19 ^= l19 << 4;
        l19 ^= l19 << 8;
        l19 ^= l19 << 16;
        l19 ^= l19 << 32;
        l20 ^= (l19 &= 0xFFFFFFFFFFFL) >>> 43;
        l20 ^= l20 << 1;
        l20 ^= l20 << 2;
        l20 ^= l20 << 4;
        l20 ^= l20 << 8;
        l20 ^= l20 << 16;
        l20 ^= l20 << 32;
        l21 ^= (l20 &= 0xFFFFFFFFFFFL) >>> 43;
        l21 ^= l21 << 1;
        l21 ^= l21 << 2;
        l21 ^= l21 << 4;
        l21 ^= l21 << 8;
        l21 ^= l21 << 16;
        l21 ^= l21 << 32;
        lArray3[0] = l16;
        lArray3[1] = l17 ^ l19 ^ lArray4[2];
        lArray3[2] = l18 ^ l20 ^ l19 ^ lArray4[3];
        lArray3[3] = l21 ^ l20;
        lArray3[4] = l21 ^ lArray4[2];
        lArray3[5] = lArray4[3];
        SecT131Field.implCompactExt(lArray3);
    }

    protected static void implMulw(long l, long l2, long[] lArray, int n) {
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
        long l4 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3 ^ lArray2[n2 >>> 6 & 7] << 6;
        int n3 = 33;
        do {
            n2 = (int)(l >>> n3);
            long l5 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3 ^ lArray2[n2 >>> 6 & 7] << 6 ^ lArray2[n2 >>> 9 & 7] << 9;
            l4 ^= l5 << n3;
            l3 ^= l5 >>> -n3;
        } while ((n3 -= 12) > 0);
        lArray[n] = l4 & 0xFFFFFFFFFFFL;
        lArray[n + 1] = l4 >>> 44 ^ l3 << 20;
    }

    protected static void implSquare(long[] lArray, long[] lArray2) {
        Interleave.expand64To128(lArray[0], lArray2, 0);
        Interleave.expand64To128(lArray[1], lArray2, 2);
        lArray2[4] = (long)Interleave.expand8to16((int)lArray[2]) & 0xFFFFFFFFL;
    }
}

