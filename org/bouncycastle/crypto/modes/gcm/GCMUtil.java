/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.util.Pack;

public abstract class GCMUtil {
    private static final int E1 = -520093696;
    private static final long E1L = -2233785415175766016L;
    private static final int[] LOOKUP = GCMUtil.generateLookup();

    private static int[] generateLookup() {
        int[] nArray = new int[256];
        for (int i = 0; i < 256; ++i) {
            int n = 0;
            for (int j = 7; j >= 0; --j) {
                if ((i & 1 << j) == 0) continue;
                n ^= -520093696 >>> 7 - j;
            }
            nArray[i] = n;
        }
        return nArray;
    }

    public static byte[] oneAsBytes() {
        byte[] byArray = new byte[16];
        byArray[0] = -128;
        return byArray;
    }

    public static int[] oneAsInts() {
        int[] nArray = new int[4];
        nArray[0] = Integer.MIN_VALUE;
        return nArray;
    }

    public static long[] oneAsLongs() {
        long[] lArray = new long[2];
        lArray[0] = Long.MIN_VALUE;
        return lArray;
    }

    public static byte[] asBytes(int[] nArray) {
        byte[] byArray = new byte[16];
        Pack.intToBigEndian(nArray, byArray, 0);
        return byArray;
    }

    public static void asBytes(int[] nArray, byte[] byArray) {
        Pack.intToBigEndian(nArray, byArray, 0);
    }

    public static byte[] asBytes(long[] lArray) {
        byte[] byArray = new byte[16];
        Pack.longToBigEndian(lArray, byArray, 0);
        return byArray;
    }

    public static void asBytes(long[] lArray, byte[] byArray) {
        Pack.longToBigEndian(lArray, byArray, 0);
    }

    public static int[] asInts(byte[] byArray) {
        int[] nArray = new int[4];
        Pack.bigEndianToInt(byArray, 0, nArray);
        return nArray;
    }

    public static void asInts(byte[] byArray, int[] nArray) {
        Pack.bigEndianToInt(byArray, 0, nArray);
    }

    public static long[] asLongs(byte[] byArray) {
        long[] lArray = new long[2];
        Pack.bigEndianToLong(byArray, 0, lArray);
        return lArray;
    }

    public static void asLongs(byte[] byArray, long[] lArray) {
        Pack.bigEndianToLong(byArray, 0, lArray);
    }

    public static void multiply(byte[] byArray, byte[] byArray2) {
        int[] nArray = GCMUtil.asInts(byArray);
        int[] nArray2 = GCMUtil.asInts(byArray2);
        GCMUtil.multiply(nArray, nArray2);
        GCMUtil.asBytes(nArray, byArray);
    }

    public static void multiply(int[] nArray, int[] nArray2) {
        int n = nArray[0];
        int n2 = nArray[1];
        int n3 = nArray[2];
        int n4 = nArray[3];
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        for (int i = 0; i < 4; ++i) {
            int n9 = nArray2[i];
            for (int j = 0; j < 32; ++j) {
                int n10 = n9 >> 31;
                n9 <<= 1;
                n5 ^= n & n10;
                n6 ^= n2 & n10;
                n7 ^= n3 & n10;
                n8 ^= n4 & n10;
                int n11 = n4 << 31 >> 8;
                n4 = n4 >>> 1 | n3 << 31;
                n3 = n3 >>> 1 | n2 << 31;
                n2 = n2 >>> 1 | n << 31;
                n = n >>> 1 ^ n11 & 0xE1000000;
            }
        }
        nArray[0] = n5;
        nArray[1] = n6;
        nArray[2] = n7;
        nArray[3] = n8;
    }

    public static void multiply(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        long l2 = lArray[1];
        long l3 = 0L;
        long l4 = 0L;
        for (int i = 0; i < 2; ++i) {
            long l5 = lArray2[i];
            for (int j = 0; j < 64; ++j) {
                long l6 = l5 >> 63;
                l5 <<= 1;
                l3 ^= l & l6;
                l4 ^= l2 & l6;
                long l7 = l2 << 63 >> 8;
                l2 = l2 >>> 1 | l << 63;
                l = l >>> 1 ^ l7 & 0xE100000000000000L;
            }
        }
        lArray[0] = l3;
        lArray[1] = l4;
    }

    public static void multiplyP(int[] nArray) {
        int n = GCMUtil.shiftRight(nArray) >> 8;
        nArray[0] = nArray[0] ^ n & 0xE1000000;
    }

    public static void multiplyP(int[] nArray, int[] nArray2) {
        int n = GCMUtil.shiftRight(nArray, nArray2) >> 8;
        nArray2[0] = nArray2[0] ^ n & 0xE1000000;
    }

    public static void multiplyP8(int[] nArray) {
        int n = GCMUtil.shiftRightN(nArray, 8);
        nArray[0] = nArray[0] ^ LOOKUP[n >>> 24];
    }

    public static void multiplyP8(int[] nArray, int[] nArray2) {
        int n = GCMUtil.shiftRightN(nArray, 8, nArray2);
        nArray2[0] = nArray2[0] ^ LOOKUP[n >>> 24];
    }

    static int shiftRight(int[] nArray) {
        int n = nArray[0];
        nArray[0] = n >>> 1;
        int n2 = n << 31;
        n = nArray[1];
        nArray[1] = n >>> 1 | n2;
        n2 = n << 31;
        n = nArray[2];
        nArray[2] = n >>> 1 | n2;
        n2 = n << 31;
        n = nArray[3];
        nArray[3] = n >>> 1 | n2;
        return n << 31;
    }

    static int shiftRight(int[] nArray, int[] nArray2) {
        int n = nArray[0];
        nArray2[0] = n >>> 1;
        int n2 = n << 31;
        n = nArray[1];
        nArray2[1] = n >>> 1 | n2;
        n2 = n << 31;
        n = nArray[2];
        nArray2[2] = n >>> 1 | n2;
        n2 = n << 31;
        n = nArray[3];
        nArray2[3] = n >>> 1 | n2;
        return n << 31;
    }

    static long shiftRight(long[] lArray) {
        long l = lArray[0];
        lArray[0] = l >>> 1;
        long l2 = l << 63;
        l = lArray[1];
        lArray[1] = l >>> 1 | l2;
        return l << 63;
    }

    static long shiftRight(long[] lArray, long[] lArray2) {
        long l = lArray[0];
        lArray2[0] = l >>> 1;
        long l2 = l << 63;
        l = lArray[1];
        lArray2[1] = l >>> 1 | l2;
        return l << 63;
    }

    static int shiftRightN(int[] nArray, int n) {
        int n2 = nArray[0];
        int n3 = 32 - n;
        nArray[0] = n2 >>> n;
        int n4 = n2 << n3;
        n2 = nArray[1];
        nArray[1] = n2 >>> n | n4;
        n4 = n2 << n3;
        n2 = nArray[2];
        nArray[2] = n2 >>> n | n4;
        n4 = n2 << n3;
        n2 = nArray[3];
        nArray[3] = n2 >>> n | n4;
        return n2 << n3;
    }

    static int shiftRightN(int[] nArray, int n, int[] nArray2) {
        int n2 = nArray[0];
        int n3 = 32 - n;
        nArray2[0] = n2 >>> n;
        int n4 = n2 << n3;
        n2 = nArray[1];
        nArray2[1] = n2 >>> n | n4;
        n4 = n2 << n3;
        n2 = nArray[2];
        nArray2[2] = n2 >>> n | n4;
        n4 = n2 << n3;
        n2 = nArray[3];
        nArray2[3] = n2 >>> n | n4;
        return n2 << n3;
    }

    public static void xor(byte[] byArray, byte[] byArray2) {
        int n = 0;
        do {
            int n2 = n;
            byArray[n2] = (byte)(byArray[n2] ^ byArray2[n]);
            int n3 = ++n;
            byArray[n3] = (byte)(byArray[n3] ^ byArray2[n]);
            int n4 = ++n;
            byArray[n4] = (byte)(byArray[n4] ^ byArray2[n]);
            int n5 = ++n;
            byArray[n5] = (byte)(byArray[n5] ^ byArray2[n]);
        } while (++n < 16);
    }

    public static void xor(byte[] byArray, byte[] byArray2, int n, int n2) {
        while (--n2 >= 0) {
            int n3 = n2;
            byArray[n3] = (byte)(byArray[n3] ^ byArray2[n + n2]);
        }
    }

    public static void xor(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        int n = 0;
        do {
            byArray3[n] = (byte)(byArray[n] ^ byArray2[n]);
            byArray3[++n] = (byte)(byArray[n] ^ byArray2[n]);
            byArray3[++n] = (byte)(byArray[n] ^ byArray2[n]);
            byArray3[++n] = (byte)(byArray[n] ^ byArray2[n]);
        } while (++n < 16);
    }

    public static void xor(int[] nArray, int[] nArray2) {
        nArray[0] = nArray[0] ^ nArray2[0];
        nArray[1] = nArray[1] ^ nArray2[1];
        nArray[2] = nArray[2] ^ nArray2[2];
        nArray[3] = nArray[3] ^ nArray2[3];
    }

    public static void xor(int[] nArray, int[] nArray2, int[] nArray3) {
        nArray3[0] = nArray[0] ^ nArray2[0];
        nArray3[1] = nArray[1] ^ nArray2[1];
        nArray3[2] = nArray[2] ^ nArray2[2];
        nArray3[3] = nArray[3] ^ nArray2[3];
    }

    public static void xor(long[] lArray, long[] lArray2) {
        lArray[0] = lArray[0] ^ lArray2[0];
        lArray[1] = lArray[1] ^ lArray2[1];
    }

    public static void xor(long[] lArray, long[] lArray2, long[] lArray3) {
        lArray3[0] = lArray[0] ^ lArray2[0];
        lArray3[1] = lArray[1] ^ lArray2[1];
    }
}

