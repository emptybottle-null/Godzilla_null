/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat128;

public class SecT113Field {
    private static final long M49 = 0x1FFFFFFFFFFFFL;
    private static final long M57 = 0x1FFFFFFFFFFFFFFL;

    public static void add(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
    }

    public static void addExt(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat128.fromBigInteger64(bigInteger);
        SecT113Field.reduce15(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat128.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat128.create64();
        long[] lArray4 = Nat128.create64();
        SecT113Field.square(lArray, lArray3);
        SecT113Field.multiply(lArray3, lArray, lArray3);
        SecT113Field.square(lArray3, lArray3);
        SecT113Field.multiply(lArray3, lArray, lArray3);
        SecT113Field.squareN(lArray3, 3, lArray4);
        SecT113Field.multiply(lArray4, lArray3, lArray4);
        SecT113Field.square(lArray4, lArray4);
        SecT113Field.multiply(lArray4, lArray, lArray4);
        SecT113Field.squareN(lArray4, 7, lArray3);
        SecT113Field.multiply(lArray3, lArray4, lArray3);
        SecT113Field.squareN(lArray3, 14, lArray4);
        SecT113Field.multiply(lArray4, lArray3, lArray4);
        SecT113Field.squareN(lArray4, 28, lArray3);
        SecT113Field.multiply(lArray3, lArray4, lArray3);
        SecT113Field.squareN(lArray3, 56, lArray4);
        SecT113Field.multiply(lArray4, lArray3, lArray4);
        SecT113Field.square(lArray4, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat128.createExt64();
        SecT113Field.implMultiply(lArray, lArray2, lArray4);
        SecT113Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat128.createExt64();
        SecT113Field.implMultiply(lArray, lArray2, lArray4);
        SecT113Field.addExt(lArray3, lArray4, lArray3);
    }

    public static void reduce(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        l2 ^= l4 << 15 ^ l4 << 24;
        long l5 = (l2 ^= l3 >>> 49 ^ l3 >>> 40) >>> 49;
        lArray2[0] = (l ^= (l3 ^= l4 >>> 49 ^ l4 >>> 40) << 15 ^ l3 << 24) ^ l5 ^ l5 << 9;
        lArray2[1] = l2 & 0x1FFFFFFFFFFFFL;
    }

    public static void reduce15(long[] lArray, int n) {
        long l = lArray[n + 1];
        long l2 = l >>> 49;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ (l2 ^ l2 << 9);
        lArray[n + 1] = l & 0x1FFFFFFFFFFFFL;
    }

    public static void sqrt(long[] lArray, long[] lArray2) {
        long l = Interleave.unshuffle(lArray[0]);
        long l2 = Interleave.unshuffle(lArray[1]);
        long l3 = l & 0xFFFFFFFFL | l2 << 32;
        long l4 = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        lArray2[0] = l3 ^ l4 << 57 ^ l4 << 5;
        lArray2[1] = l4 >>> 7 ^ l4 >>> 59;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat128.createExt64();
        SecT113Field.implSquare(lArray, lArray3);
        SecT113Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat128.createExt64();
        SecT113Field.implSquare(lArray, lArray3);
        SecT113Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat128.createExt64();
        SecT113Field.implSquare(lArray, lArray3);
        SecT113Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT113Field.implSquare(lArray2, lArray3);
            SecT113Field.reduce(lArray3, lArray2);
        }
    }

    public static int trace(long[] lArray) {
        return (int)lArray[0] & 1;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long l = lArray[0];
        long l2 = lArray[1];
        l2 = (l >>> 57 ^ l2 << 7) & 0x1FFFFFFFFFFFFFFL;
        long l3 = lArray2[0];
        long l4 = lArray2[1];
        l4 = (l3 >>> 57 ^ l4 << 7) & 0x1FFFFFFFFFFFFFFL;
        long[] lArray4 = new long[6];
        SecT113Field.implMulw(l &= 0x1FFFFFFFFFFFFFFL, l3 &= 0x1FFFFFFFFFFFFFFL, lArray4, 0);
        SecT113Field.implMulw(l2, l4, lArray4, 2);
        SecT113Field.implMulw(l ^ l2, l3 ^ l4, lArray4, 4);
        long l5 = lArray4[1] ^ lArray4[2];
        long l6 = lArray4[0];
        long l7 = lArray4[3];
        long l8 = lArray4[4] ^ l6 ^ l5;
        long l9 = lArray4[5] ^ l7 ^ l5;
        lArray3[0] = l6 ^ l8 << 57;
        lArray3[1] = l8 >>> 7 ^ l9 << 50;
        lArray3[2] = l9 >>> 14 ^ l7 << 43;
        lArray3[3] = l7 >>> 21;
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
        long l4 = lArray2[n2 & 7];
        int n3 = 48;
        do {
            n2 = (int)(l >>> n3);
            long l5 = lArray2[n2 & 7] ^ lArray2[n2 >>> 3 & 7] << 3 ^ lArray2[n2 >>> 6 & 7] << 6;
            l4 ^= l5 << n3;
            l3 ^= l5 >>> -n3;
        } while ((n3 -= 9) > 0);
        lArray[n] = l4 & 0x1FFFFFFFFFFFFFFL;
        lArray[n + 1] = l4 >>> 57 ^ (l3 ^= (l & 0x100804020100800L & l2 << 7 >> 63) >>> 8) << 7;
    }

    protected static void implSquare(long[] lArray, long[] lArray2) {
        Interleave.expand64To128(lArray[0], lArray2, 0);
        Interleave.expand64To128(lArray[1], lArray2, 2);
    }
}

