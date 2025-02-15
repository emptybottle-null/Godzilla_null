/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

public final class CharUtils {
    private CharUtils() {
    }

    public static char[] clone(char[] cArray) {
        char[] cArray2 = new char[cArray.length];
        System.arraycopy(cArray, 0, cArray2, 0, cArray.length);
        return cArray2;
    }

    public static byte[] toByteArray(char[] cArray) {
        byte[] byArray = new byte[cArray.length];
        for (int i = cArray.length - 1; i >= 0; --i) {
            byArray[i] = (byte)cArray[i];
        }
        return byArray;
    }

    public static byte[] toByteArrayForPBE(char[] cArray) {
        int n;
        byte[] byArray = new byte[cArray.length];
        for (n = 0; n < cArray.length; ++n) {
            byArray[n] = (byte)cArray[n];
        }
        n = byArray.length * 2;
        byte[] byArray2 = new byte[n + 2];
        int n2 = 0;
        for (int i = 0; i < byArray.length; ++i) {
            n2 = i * 2;
            byArray2[n2] = 0;
            byArray2[n2 + 1] = byArray[i];
        }
        byArray2[n] = 0;
        byArray2[n + 1] = 0;
        return byArray2;
    }

    public static boolean equals(char[] cArray, char[] cArray2) {
        if (cArray.length != cArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = cArray.length - 1; i >= 0; --i) {
            bl &= cArray[i] == cArray2[i];
        }
        return bl;
    }
}

