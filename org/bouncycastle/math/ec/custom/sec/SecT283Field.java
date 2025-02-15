/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat320;

public class SecT283Field {
    private static final long M27 = 0x7FFFFFFL;
    private static final long M57 = 0x1FFFFFFFFFFFFFFL;
    private static final long[] ROOT_Z = new long[]{878416384462358536L, 0x30C30C30C30C30C3L, -9076969306111048948L, 0x820820820820820L, 0x2082082L};

    public static void add(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
        lArray3[2] = lArray[2] ^ lArray2[2];
        lArray3[3] = lArray[3] ^ lArray2[3];
        lArray3[4] = lArray[4] ^ lArray2[4];
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
        lArray3[8] = lArray[8] ^ lArray2[8];
    }

    public static void addOne(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0] ^ 1L;
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
        lArray2[3] = lArray[3];
        lArray2[4] = lArray[4];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] lArray = Nat320.fromBigInteger64(bigInteger);
        SecT283Field.reduce37(lArray, 0);
        return lArray;
    }

    public static void invert(long[] lArray, long[] lArray2) {
        if (Nat320.isZero64(lArray)) {
            throw new IllegalStateException();
        }
        long[] lArray3 = Nat320.create64();
        long[] lArray4 = Nat320.create64();
        SecT283Field.square(lArray, lArray3);
        SecT283Field.multiply(lArray3, lArray, lArray3);
        SecT283Field.squareN(lArray3, 2, lArray4);
        SecT283Field.multiply(lArray4, lArray3, lArray4);
        SecT283Field.squareN(lArray4, 4, lArray3);
        SecT283Field.multiply(lArray3, lArray4, lArray3);
        SecT283Field.squareN(lArray3, 8, lArray4);
        SecT283Field.multiply(lArray4, lArray3, lArray4);
        SecT283Field.square(lArray4, lArray4);
        SecT283Field.multiply(lArray4, lArray, lArray4);
        SecT283Field.squareN(lArray4, 17, lArray3);
        SecT283Field.multiply(lArray3, lArray4, lArray3);
        SecT283Field.square(lArray3, lArray3);
        SecT283Field.multiply(lArray3, lArray, lArray3);
        SecT283Field.squareN(lArray3, 35, lArray4);
        SecT283Field.multiply(lArray4, lArray3, lArray4);
        SecT283Field.squareN(lArray4, 70, lArray3);
        SecT283Field.multiply(lArray3, lArray4, lArray3);
        SecT283Field.square(lArray3, lArray3);
        SecT283Field.multiply(lArray3, lArray, lArray3);
        SecT283Field.squareN(lArray3, 141, lArray4);
        SecT283Field.multiply(lArray4, lArray3, lArray4);
        SecT283Field.square(lArray4, lArray2);
    }

    public static void multiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat320.createExt64();
        SecT283Field.implMultiply(lArray, lArray2, lArray4);
        SecT283Field.reduce(lArray4, lArray3);
    }

    public static void multiplyAddToExt(long[] lArray, long[] lArray2, long[] lArray3) {
        long[] lArray4 = Nat320.createExt64();
        SecT283Field.implMultiply(lArray, lArray2, lArray4);
        SecT283Field.addExt(lArray3, lArray4, lArray3);
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
        long l9 = lArray[8];
        l4 ^= l9 << 37 ^ l9 << 42 ^ l9 << 44 ^ l9 << 49;
        l3 ^= l8 << 37 ^ l8 << 42 ^ l8 << 44 ^ l8 << 49;
        l4 ^= l8 >>> 27 ^ l8 >>> 22 ^ l8 >>> 20 ^ l8 >>> 15;
        l2 ^= l7 << 37 ^ l7 << 42 ^ l7 << 44 ^ l7 << 49;
        long l10 = (l5 ^= l9 >>> 27 ^ l9 >>> 22 ^ l9 >>> 20 ^ l9 >>> 15) >>> 27;
        lArray2[0] = (l ^= l6 << 37 ^ l6 << 42 ^ l6 << 44 ^ l6 << 49) ^ l10 ^ l10 << 5 ^ l10 << 7 ^ l10 << 12;
        lArray2[1] = l2 ^= l6 >>> 27 ^ l6 >>> 22 ^ l6 >>> 20 ^ l6 >>> 15;
        lArray2[2] = l3 ^= l7 >>> 27 ^ l7 >>> 22 ^ l7 >>> 20 ^ l7 >>> 15;
        lArray2[3] = l4;
        lArray2[4] = l5 & 0x7FFFFFFL;
    }

    public static void reduce37(long[] lArray, int n) {
        long l = lArray[n + 4];
        long l2 = l >>> 27;
        int n2 = n;
        lArray[n2] = lArray[n2] ^ (l2 ^ l2 << 5 ^ l2 << 7 ^ l2 << 12);
        lArray[n + 4] = l & 0x7FFFFFFL;
    }

    public static void sqrt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat320.create64();
        long l = Interleave.unshuffle(lArray[0]);
        long l2 = Interleave.unshuffle(lArray[1]);
        long l3 = l & 0xFFFFFFFFL | l2 << 32;
        lArray3[0] = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[2]);
        l2 = Interleave.unshuffle(lArray[3]);
        long l4 = l & 0xFFFFFFFFL | l2 << 32;
        lArray3[1] = l >>> 32 | l2 & 0xFFFFFFFF00000000L;
        l = Interleave.unshuffle(lArray[4]);
        long l5 = l & 0xFFFFFFFFL;
        lArray3[2] = l >>> 32;
        SecT283Field.multiply(lArray3, ROOT_Z, lArray2);
        lArray2[0] = lArray2[0] ^ l3;
        lArray2[1] = lArray2[1] ^ l4;
        lArray2[2] = lArray2[2] ^ l5;
    }

    public static void square(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(9);
        SecT283Field.implSquare(lArray, lArray3);
        SecT283Field.reduce(lArray3, lArray2);
    }

    public static void squareAddToExt(long[] lArray, long[] lArray2) {
        long[] lArray3 = Nat.create64(9);
        SecT283Field.implSquare(lArray, lArray3);
        SecT283Field.addExt(lArray2, lArray3, lArray2);
    }

    public static void squareN(long[] lArray, int n, long[] lArray2) {
        long[] lArray3 = Nat.create64(9);
        SecT283Field.implSquare(lArray, lArray3);
        SecT283Field.reduce(lArray3, lArray2);
        while (--n > 0) {
            SecT283Field.implSquare(lArray2, lArray3);
            SecT283Field.reduce(lArray3, lArray2);
        }
    }

    public static int trace(long[] lArray) {
        return (int)(lArray[0] ^ lArray[4] >>> 15) & 1;
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
        lArray[0] = l ^ l2 << 57;
        lArray[1] = l2 >>> 7 ^ l3 << 50;
        lArray[2] = l3 >>> 14 ^ l4 << 43;
        lArray[3] = l4 >>> 21 ^ l5 << 36;
        lArray[4] = l5 >>> 28 ^ l6 << 29;
        lArray[5] = l6 >>> 35 ^ l7 << 22;
        lArray[6] = l7 >>> 42 ^ l8 << 15;
        lArray[7] = l8 >>> 49 ^ l9 << 8;
        lArray[8] = l9 >>> 56 ^ l10 << 1;
        lArray[9] = l10 >>> 63;
    }

    protected static void implExpand(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = lArray[2];
        long l4 = lArray[3];
        long l5 = lArray[4];
        lArray2[0] = l & 0x1FFFFFFFFFFFFFFL;
        lArray2[1] = (l >>> 57 ^ l2 << 7) & 0x1FFFFFFFFFFFFFFL;
        lArray2[2] = (l2 >>> 50 ^ l3 << 14) & 0x1FFFFFFFFFFFFFFL;
        lArray2[3] = (l3 >>> 43 ^ l4 << 21) & 0x1FFFFFFFFFFFFFFL;
        lArray2[4] = l4 >>> 36 ^ l5 << 28;
    }

    protected static void implMultiply(long[] lArray, long[] lArray2, long[] lArray3) {
        long l;
        long l2;
        long l3;
        long l4;
        long l5;
        long l6;
        long l7;
        long l8;
        long[] lArray4 = new long[5];
        long[] lArray5 = new long[5];
        SecT283Field.implExpand(lArray, lArray4);
        SecT283Field.implExpand(lArray2, lArray5);
        long[] lArray6 = new long[26];
        SecT283Field.implMulw(lArray4[0], lArray5[0], lArray6, 0);
        SecT283Field.implMulw(lArray4[1], lArray5[1], lArray6, 2);
        SecT283Field.implMulw(lArray4[2], lArray5[2], lArray6, 4);
        SecT283Field.implMulw(lArray4[3], lArray5[3], lArray6, 6);
        SecT283Field.implMulw(lArray4[4], lArray5[4], lArray6, 8);
        long l9 = lArray4[0] ^ lArray4[1];
        long l10 = lArray5[0] ^ lArray5[1];
        long l11 = lArray4[0] ^ lArray4[2];
        long l12 = lArray5[0] ^ lArray5[2];
        long l13 = lArray4[2] ^ lArray4[4];
        long l14 = lArray5[2] ^ lArray5[4];
        long l15 = lArray4[3] ^ lArray4[4];
        long l16 = lArray5[3] ^ lArray5[4];
        SecT283Field.implMulw(l11 ^ lArray4[3], l12 ^ lArray5[3], lArray6, 18);
        SecT283Field.implMulw(l13 ^ lArray4[1], l14 ^ lArray5[1], lArray6, 20);
        long l17 = l9 ^ l15;
        long l18 = l10 ^ l16;
        long l19 = l17 ^ lArray4[2];
        long l20 = l18 ^ lArray5[2];
        SecT283Field.implMulw(l17, l18, lArray6, 22);
        SecT283Field.implMulw(l19, l20, lArray6, 24);
        SecT283Field.implMulw(l9, l10, lArray6, 10);
        SecT283Field.implMulw(l11, l12, lArray6, 12);
        SecT283Field.implMulw(l13, l14, lArray6, 14);
        SecT283Field.implMulw(l15, l16, lArray6, 16);
        lArray3[0] = lArray6[0];
        lArray3[9] = lArray6[9];
        long l21 = lArray6[0] ^ lArray6[1];
        long l22 = l21 ^ lArray6[2];
        lArray3[1] = l8 = l22 ^ lArray6[10];
        long l23 = lArray6[3] ^ lArray6[4];
        long l24 = lArray6[11] ^ lArray6[12];
        long l25 = l23 ^ l24;
        lArray3[2] = l7 = l22 ^ l25;
        long l26 = l21 ^ l23;
        long l27 = lArray6[5] ^ lArray6[6];
        long l28 = l26 ^ l27;
        long l29 = l28 ^ lArray6[8];
        long l30 = lArray6[13] ^ lArray6[14];
        long l31 = l29 ^ l30;
        long l32 = lArray6[18] ^ lArray6[22];
        long l33 = l32 ^ lArray6[24];
        lArray3[3] = l6 = l31 ^ l33;
        long l34 = lArray6[7] ^ lArray6[8];
        long l35 = l34 ^ lArray6[9];
        lArray3[8] = l5 = l35 ^ lArray6[17];
        long l36 = l35 ^ l27;
        long l37 = lArray6[15] ^ lArray6[16];
        lArray3[7] = l4 = l36 ^ l37;
        long l38 = l4 ^ l8;
        long l39 = lArray6[19] ^ lArray6[20];
        long l40 = lArray6[25] ^ lArray6[24];
        long l41 = lArray6[18] ^ lArray6[23];
        long l42 = l39 ^ l40;
        long l43 = l42 ^ l41;
        lArray3[4] = l3 = l43 ^ l38;
        long l44 = l7 ^ l5;
        long l45 = l42 ^ l44;
        long l46 = lArray6[21] ^ lArray6[22];
        lArray3[5] = l2 = l45 ^ l46;
        long l47 = l29 ^ lArray6[0];
        long l48 = l47 ^ lArray6[9];
        long l49 = l48 ^ l30;
        long l50 = l49 ^ lArray6[21];
        long l51 = l50 ^ lArray6[23];
        lArray3[6] = l = l51 ^ lArray6[25];
        SecT283Field.implCompactExt(lArray3);
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
        for (int i = 0; i < 4; ++i) {
            Interleave.expand64To128(lArray[i], lArray2, i << 1);
        }
        lArray2[8] = Interleave.expand32to64((int)lArray[4]);
    }
}

