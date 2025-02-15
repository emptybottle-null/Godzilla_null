/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

public final class BigEndianConversions {
    private BigEndianConversions() {
    }

    public static byte[] I2OSP(int n) {
        byte[] byArray = new byte[]{(byte)(n >>> 24), (byte)(n >>> 16), (byte)(n >>> 8), (byte)n};
        return byArray;
    }

    public static byte[] I2OSP(int n, int n2) throws ArithmeticException {
        if (n < 0) {
            return null;
        }
        int n3 = IntegerFunctions.ceilLog256(n);
        if (n3 > n2) {
            throw new ArithmeticException("Cannot encode given integer into specified number of octets.");
        }
        byte[] byArray = new byte[n2];
        for (int i = n2 - 1; i >= n2 - n3; --i) {
            byArray[i] = (byte)(n >>> 8 * (n2 - 1 - i));
        }
        return byArray;
    }

    public static void I2OSP(int n, byte[] byArray, int n2) {
        byArray[n2++] = (byte)(n >>> 24);
        byArray[n2++] = (byte)(n >>> 16);
        byArray[n2++] = (byte)(n >>> 8);
        byArray[n2] = (byte)n;
    }

    public static byte[] I2OSP(long l) {
        byte[] byArray = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
        return byArray;
    }

    public static void I2OSP(long l, byte[] byArray, int n) {
        byArray[n++] = (byte)(l >>> 56);
        byArray[n++] = (byte)(l >>> 48);
        byArray[n++] = (byte)(l >>> 40);
        byArray[n++] = (byte)(l >>> 32);
        byArray[n++] = (byte)(l >>> 24);
        byArray[n++] = (byte)(l >>> 16);
        byArray[n++] = (byte)(l >>> 8);
        byArray[n] = (byte)l;
    }

    public static void I2OSP(int n, byte[] byArray, int n2, int n3) {
        for (int i = n3 - 1; i >= 0; --i) {
            byArray[n2 + i] = (byte)(n >>> 8 * (n3 - 1 - i));
        }
    }

    public static int OS2IP(byte[] byArray) {
        if (byArray.length > 4) {
            throw new ArithmeticException("invalid input length");
        }
        if (byArray.length == 0) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            n |= (byArray[i] & 0xFF) << 8 * (byArray.length - 1 - i);
        }
        return n;
    }

    public static int OS2IP(byte[] byArray, int n) {
        int n2 = (byArray[n++] & 0xFF) << 24;
        n2 |= (byArray[n++] & 0xFF) << 16;
        n2 |= (byArray[n++] & 0xFF) << 8;
        return n2 |= byArray[n] & 0xFF;
    }

    public static int OS2IP(byte[] byArray, int n, int n2) {
        if (byArray.length == 0 || byArray.length < n + n2 - 1) {
            return 0;
        }
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 |= (byArray[n + i] & 0xFF) << 8 * (n2 - i - 1);
        }
        return n3;
    }

    public static long OS2LIP(byte[] byArray, int n) {
        long l = ((long)byArray[n++] & 0xFFL) << 56;
        l |= ((long)byArray[n++] & 0xFFL) << 48;
        l |= ((long)byArray[n++] & 0xFFL) << 40;
        l |= ((long)byArray[n++] & 0xFFL) << 32;
        l |= ((long)byArray[n++] & 0xFFL) << 24;
        l |= (long)((byArray[n++] & 0xFF) << 16);
        l |= (long)((byArray[n++] & 0xFF) << 8);
        return l |= (long)(byArray[n] & 0xFF);
    }

    public static byte[] toByteArray(int[] nArray) {
        byte[] byArray = new byte[nArray.length << 2];
        for (int i = 0; i < nArray.length; ++i) {
            BigEndianConversions.I2OSP(nArray[i], byArray, i << 2);
        }
        return byArray;
    }

    public static byte[] toByteArray(int[] nArray, int n) {
        int n2 = nArray.length;
        byte[] byArray = new byte[n];
        int n3 = 0;
        int n4 = 0;
        while (n4 <= n2 - 2) {
            BigEndianConversions.I2OSP(nArray[n4], byArray, n3);
            ++n4;
            n3 += 4;
        }
        BigEndianConversions.I2OSP(nArray[n2 - 1], byArray, n3, n - n3);
        return byArray;
    }

    public static int[] toIntArray(byte[] byArray) {
        int n = (byArray.length + 3) / 4;
        int n2 = byArray.length & 3;
        int[] nArray = new int[n];
        int n3 = 0;
        int n4 = 0;
        while (n4 <= n - 2) {
            nArray[n4] = BigEndianConversions.OS2IP(byArray, n3);
            ++n4;
            n3 += 4;
        }
        nArray[n - 1] = n2 != 0 ? BigEndianConversions.OS2IP(byArray, n3, n2) : BigEndianConversions.OS2IP(byArray, n3);
        return nArray;
    }
}

