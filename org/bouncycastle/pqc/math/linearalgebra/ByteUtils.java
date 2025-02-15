/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

public final class ByteUtils {
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private ByteUtils() {
    }

    public static boolean equals(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            return byArray2 == null;
        }
        if (byArray2 == null) {
            return false;
        }
        if (byArray.length != byArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = byArray.length - 1; i >= 0; --i) {
            bl &= byArray[i] == byArray2[i];
        }
        return bl;
    }

    public static boolean equals(byte[][] byArray, byte[][] byArray2) {
        if (byArray.length != byArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = byArray.length - 1; i >= 0; --i) {
            bl &= ByteUtils.equals(byArray[i], byArray2[i]);
        }
        return bl;
    }

    public static boolean equals(byte[][][] byArray, byte[][][] byArray2) {
        if (byArray.length != byArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = byArray.length - 1; i >= 0; --i) {
            if (byArray[i].length != byArray2[i].length) {
                return false;
            }
            for (int j = byArray[i].length - 1; j >= 0; --j) {
                bl &= ByteUtils.equals(byArray[i][j], byArray2[i][j]);
            }
        }
        return bl;
    }

    public static int deepHashCode(byte[] byArray) {
        int n = 1;
        for (int i = 0; i < byArray.length; ++i) {
            n = 31 * n + byArray[i];
        }
        return n;
    }

    public static int deepHashCode(byte[][] byArray) {
        int n = 1;
        for (int i = 0; i < byArray.length; ++i) {
            n = 31 * n + ByteUtils.deepHashCode(byArray[i]);
        }
        return n;
    }

    public static int deepHashCode(byte[][][] byArray) {
        int n = 1;
        for (int i = 0; i < byArray.length; ++i) {
            n = 31 * n + ByteUtils.deepHashCode(byArray[i]);
        }
        return n;
    }

    public static byte[] clone(byte[] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[] byArray2 = new byte[byArray.length];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        return byArray2;
    }

    public static byte[] fromHexString(String string) {
        char[] cArray = string.toUpperCase().toCharArray();
        int n = 0;
        for (int i = 0; i < cArray.length; ++i) {
            if ((cArray[i] < '0' || cArray[i] > '9') && (cArray[i] < 'A' || cArray[i] > 'F')) continue;
            ++n;
        }
        byte[] byArray = new byte[n + 1 >> 1];
        int n2 = n & 1;
        for (int i = 0; i < cArray.length; ++i) {
            if (cArray[i] >= '0' && cArray[i] <= '9') {
                int n3 = n2 >> 1;
                byArray[n3] = (byte)(byArray[n3] << 4);
                int n4 = n2 >> 1;
                byArray[n4] = (byte)(byArray[n4] | cArray[i] - 48);
            } else {
                if (cArray[i] < 'A' || cArray[i] > 'F') continue;
                int n5 = n2 >> 1;
                byArray[n5] = (byte)(byArray[n5] << 4);
                int n6 = n2 >> 1;
                byArray[n6] = (byte)(byArray[n6] | cArray[i] - 65 + 10);
            }
            ++n2;
        }
        return byArray;
    }

    public static String toHexString(byte[] byArray) {
        String string = "";
        for (int i = 0; i < byArray.length; ++i) {
            string = string + HEX_CHARS[byArray[i] >>> 4 & 0xF];
            string = string + HEX_CHARS[byArray[i] & 0xF];
        }
        return string;
    }

    public static String toHexString(byte[] byArray, String string, String string2) {
        String string3 = new String(string);
        for (int i = 0; i < byArray.length; ++i) {
            string3 = string3 + HEX_CHARS[byArray[i] >>> 4 & 0xF];
            string3 = string3 + HEX_CHARS[byArray[i] & 0xF];
            if (i >= byArray.length - 1) continue;
            string3 = string3 + string2;
        }
        return string3;
    }

    public static String toBinaryString(byte[] byArray) {
        String string = "";
        for (int i = 0; i < byArray.length; ++i) {
            byte by = byArray[i];
            for (int j = 0; j < 8; ++j) {
                int n = by >>> j & 1;
                string = string + n;
            }
            if (i == byArray.length - 1) continue;
            string = string + " ";
        }
        return string;
    }

    public static byte[] xor(byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = new byte[byArray.length];
        for (int i = byArray.length - 1; i >= 0; --i) {
            byArray3[i] = (byte)(byArray[i] ^ byArray2[i]);
        }
        return byArray3;
    }

    public static byte[] concatenate(byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = new byte[byArray.length + byArray2.length];
        System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
        System.arraycopy(byArray2, 0, byArray3, byArray.length, byArray2.length);
        return byArray3;
    }

    public static byte[] concatenate(byte[][] byArray) {
        int n = byArray[0].length;
        byte[] byArray2 = new byte[byArray.length * n];
        int n2 = 0;
        for (int i = 0; i < byArray.length; ++i) {
            System.arraycopy(byArray[i], 0, byArray2, n2, n);
            n2 += n;
        }
        return byArray2;
    }

    public static byte[][] split(byte[] byArray, int n) throws ArrayIndexOutOfBoundsException {
        if (n > byArray.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[][] byArrayArray = new byte[][]{new byte[n], new byte[byArray.length - n]};
        System.arraycopy(byArray, 0, byArrayArray[0], 0, n);
        System.arraycopy(byArray, n, byArrayArray[1], 0, byArray.length - n);
        return byArrayArray;
    }

    public static byte[] subArray(byte[] byArray, int n, int n2) {
        byte[] byArray2 = new byte[n2 - n];
        System.arraycopy(byArray, n, byArray2, 0, n2 - n);
        return byArray2;
    }

    public static byte[] subArray(byte[] byArray, int n) {
        return ByteUtils.subArray(byArray, n, byArray.length);
    }

    public static char[] toCharArray(byte[] byArray) {
        char[] cArray = new char[byArray.length];
        for (int i = 0; i < byArray.length; ++i) {
            cArray[i] = (char)byArray[i];
        }
        return cArray;
    }
}

