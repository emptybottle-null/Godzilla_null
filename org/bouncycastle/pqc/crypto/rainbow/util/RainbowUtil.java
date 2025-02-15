/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow.util;

public class RainbowUtil {
    public static int[] convertArraytoInt(byte[] byArray) {
        int[] nArray = new int[byArray.length];
        for (int i = 0; i < byArray.length; ++i) {
            nArray[i] = byArray[i] & 0xFF;
        }
        return nArray;
    }

    public static short[] convertArray(byte[] byArray) {
        short[] sArray = new short[byArray.length];
        for (int i = 0; i < byArray.length; ++i) {
            sArray[i] = (short)(byArray[i] & 0xFF);
        }
        return sArray;
    }

    public static short[][] convertArray(byte[][] byArray) {
        short[][] sArray = new short[byArray.length][byArray[0].length];
        for (int i = 0; i < byArray.length; ++i) {
            for (int j = 0; j < byArray[0].length; ++j) {
                sArray[i][j] = (short)(byArray[i][j] & 0xFF);
            }
        }
        return sArray;
    }

    public static short[][][] convertArray(byte[][][] byArray) {
        short[][][] sArray = new short[byArray.length][byArray[0].length][byArray[0][0].length];
        for (int i = 0; i < byArray.length; ++i) {
            for (int j = 0; j < byArray[0].length; ++j) {
                for (int k = 0; k < byArray[0][0].length; ++k) {
                    sArray[i][j][k] = (short)(byArray[i][j][k] & 0xFF);
                }
            }
        }
        return sArray;
    }

    public static byte[] convertIntArray(int[] nArray) {
        byte[] byArray = new byte[nArray.length];
        for (int i = 0; i < nArray.length; ++i) {
            byArray[i] = (byte)nArray[i];
        }
        return byArray;
    }

    public static byte[] convertArray(short[] sArray) {
        byte[] byArray = new byte[sArray.length];
        for (int i = 0; i < sArray.length; ++i) {
            byArray[i] = (byte)sArray[i];
        }
        return byArray;
    }

    public static byte[][] convertArray(short[][] sArray) {
        byte[][] byArray = new byte[sArray.length][sArray[0].length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray[0].length; ++j) {
                byArray[i][j] = (byte)sArray[i][j];
            }
        }
        return byArray;
    }

    public static byte[][][] convertArray(short[][][] sArray) {
        byte[][][] byArray = new byte[sArray.length][sArray[0].length][sArray[0][0].length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray[0].length; ++j) {
                for (int k = 0; k < sArray[0][0].length; ++k) {
                    byArray[i][j][k] = (byte)sArray[i][j][k];
                }
            }
        }
        return byArray;
    }

    public static boolean equals(short[] sArray, short[] sArray2) {
        if (sArray.length != sArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = sArray.length - 1; i >= 0; --i) {
            bl &= sArray[i] == sArray2[i];
        }
        return bl;
    }

    public static boolean equals(short[][] sArray, short[][] sArray2) {
        if (sArray.length != sArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = sArray.length - 1; i >= 0; --i) {
            bl &= RainbowUtil.equals(sArray[i], sArray2[i]);
        }
        return bl;
    }

    public static boolean equals(short[][][] sArray, short[][][] sArray2) {
        if (sArray.length != sArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = sArray.length - 1; i >= 0; --i) {
            bl &= RainbowUtil.equals(sArray[i], sArray2[i]);
        }
        return bl;
    }
}

