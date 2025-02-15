/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss.util;

public class GMSSUtil {
    public byte[] intToBytesLittleEndian(int n) {
        byte[] byArray = new byte[]{(byte)(n & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 24 & 0xFF)};
        return byArray;
    }

    public int bytesToIntLittleEndian(byte[] byArray) {
        return byArray[0] & 0xFF | (byArray[1] & 0xFF) << 8 | (byArray[2] & 0xFF) << 16 | (byArray[3] & 0xFF) << 24;
    }

    public int bytesToIntLittleEndian(byte[] byArray, int n) {
        return byArray[n++] & 0xFF | (byArray[n++] & 0xFF) << 8 | (byArray[n++] & 0xFF) << 16 | (byArray[n] & 0xFF) << 24;
    }

    public byte[] concatenateArray(byte[][] byArray) {
        byte[] byArray2 = new byte[byArray.length * byArray[0].length];
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            System.arraycopy(byArray[i], 0, byArray2, n, byArray[i].length);
            n += byArray[i].length;
        }
        return byArray2;
    }

    public void printArray(String string, byte[][] byArray) {
        System.out.println(string);
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            for (int j = 0; j < byArray[0].length; ++j) {
                System.out.println(n + "; " + byArray[i][j]);
                ++n;
            }
        }
    }

    public void printArray(String string, byte[] byArray) {
        System.out.println(string);
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            System.out.println(n + "; " + byArray[i]);
            ++n;
        }
    }

    public boolean testPowerOfTwo(int n) {
        int n2;
        for (n2 = 1; n2 < n; n2 <<= 1) {
        }
        return n == n2;
    }

    public int getLog(int n) {
        int n2 = 1;
        int n3 = 2;
        while (n3 < n) {
            n3 <<= 1;
            ++n2;
        }
        return n2;
    }
}

