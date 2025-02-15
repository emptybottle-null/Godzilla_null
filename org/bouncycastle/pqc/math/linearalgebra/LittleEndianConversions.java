/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

public final class LittleEndianConversions {
    private LittleEndianConversions() {
    }

    public static int OS2IP(byte[] byArray) {
        return byArray[0] & 0xFF | (byArray[1] & 0xFF) << 8 | (byArray[2] & 0xFF) << 16 | (byArray[3] & 0xFF) << 24;
    }

    public static int OS2IP(byte[] byArray, int n) {
        int n2 = byArray[n++] & 0xFF;
        n2 |= (byArray[n++] & 0xFF) << 8;
        n2 |= (byArray[n++] & 0xFF) << 16;
        return n2 |= (byArray[n] & 0xFF) << 24;
    }

    public static int OS2IP(byte[] byArray, int n, int n2) {
        int n3 = 0;
        for (int i = n2 - 1; i >= 0; --i) {
            n3 |= (byArray[n + i] & 0xFF) << 8 * i;
        }
        return n3;
    }

    public static long OS2LIP(byte[] byArray, int n) {
        long l = byArray[n++] & 0xFF;
        l |= (long)((byArray[n++] & 0xFF) << 8);
        l |= (long)((byArray[n++] & 0xFF) << 16);
        l |= ((long)byArray[n++] & 0xFFL) << 24;
        l |= ((long)byArray[n++] & 0xFFL) << 32;
        l |= ((long)byArray[n++] & 0xFFL) << 40;
        l |= ((long)byArray[n++] & 0xFFL) << 48;
        return l |= ((long)byArray[n++] & 0xFFL) << 56;
    }

    public static byte[] I2OSP(int n) {
        byte[] byArray = new byte[]{(byte)n, (byte)(n >>> 8), (byte)(n >>> 16), (byte)(n >>> 24)};
        return byArray;
    }

    public static void I2OSP(int n, byte[] byArray, int n2) {
        byArray[n2++] = (byte)n;
        byArray[n2++] = (byte)(n >>> 8);
        byArray[n2++] = (byte)(n >>> 16);
        byArray[n2++] = (byte)(n >>> 24);
    }

    public static void I2OSP(int n, byte[] byArray, int n2, int n3) {
        for (int i = n3 - 1; i >= 0; --i) {
            byArray[n2 + i] = (byte)(n >>> 8 * i);
        }
    }

    public static byte[] I2OSP(long l) {
        byte[] byArray = new byte[]{(byte)l, (byte)(l >>> 8), (byte)(l >>> 16), (byte)(l >>> 24), (byte)(l >>> 32), (byte)(l >>> 40), (byte)(l >>> 48), (byte)(l >>> 56)};
        return byArray;
    }

    public static void I2OSP(long l, byte[] byArray, int n) {
        byArray[n++] = (byte)l;
        byArray[n++] = (byte)(l >>> 8);
        byArray[n++] = (byte)(l >>> 16);
        byArray[n++] = (byte)(l >>> 24);
        byArray[n++] = (byte)(l >>> 32);
        byArray[n++] = (byte)(l >>> 40);
        byArray[n++] = (byte)(l >>> 48);
        byArray[n] = (byte)(l >>> 56);
    }

    public static byte[] toByteArray(int[] nArray, int n) {
        int n2 = nArray.length;
        byte[] byArray = new byte[n];
        int n3 = 0;
        int n4 = 0;
        while (n4 <= n2 - 2) {
            LittleEndianConversions.I2OSP(nArray[n4], byArray, n3);
            ++n4;
            n3 += 4;
        }
        LittleEndianConversions.I2OSP(nArray[n2 - 1], byArray, n3, n - n3);
        return byArray;
    }

    public static int[] toIntArray(byte[] byArray) {
        int n = (byArray.length + 3) / 4;
        int n2 = byArray.length & 3;
        int[] nArray = new int[n];
        int n3 = 0;
        int n4 = 0;
        while (n4 <= n - 2) {
            nArray[n4] = LittleEndianConversions.OS2IP(byArray, n3);
            ++n4;
            n3 += 4;
        }
        nArray[n - 1] = n2 != 0 ? LittleEndianConversions.OS2IP(byArray, n3, n2) : LittleEndianConversions.OS2IP(byArray, n3);
        return nArray;
    }
}

