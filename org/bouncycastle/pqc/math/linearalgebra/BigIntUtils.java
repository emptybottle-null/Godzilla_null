/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

public final class BigIntUtils {
    private BigIntUtils() {
    }

    public static boolean equals(BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2) {
        int n = 0;
        if (bigIntegerArray.length != bigIntegerArray2.length) {
            return false;
        }
        for (int i = 0; i < bigIntegerArray.length; ++i) {
            n |= bigIntegerArray[i].compareTo(bigIntegerArray2[i]);
        }
        return n == 0;
    }

    public static void fill(BigInteger[] bigIntegerArray, BigInteger bigInteger) {
        for (int i = bigIntegerArray.length - 1; i >= 0; --i) {
            bigIntegerArray[i] = bigInteger;
        }
    }

    public static BigInteger[] subArray(BigInteger[] bigIntegerArray, int n, int n2) {
        BigInteger[] bigIntegerArray2 = new BigInteger[n2 - n];
        System.arraycopy(bigIntegerArray, n, bigIntegerArray2, 0, n2 - n);
        return bigIntegerArray2;
    }

    public static int[] toIntArray(BigInteger[] bigIntegerArray) {
        int[] nArray = new int[bigIntegerArray.length];
        for (int i = 0; i < bigIntegerArray.length; ++i) {
            nArray[i] = bigIntegerArray[i].intValue();
        }
        return nArray;
    }

    public static int[] toIntArrayModQ(int n, BigInteger[] bigIntegerArray) {
        BigInteger bigInteger = BigInteger.valueOf(n);
        int[] nArray = new int[bigIntegerArray.length];
        for (int i = 0; i < bigIntegerArray.length; ++i) {
            nArray[i] = bigIntegerArray[i].mod(bigInteger).intValue();
        }
        return nArray;
    }

    public static byte[] toMinimalByteArray(BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (byArray.length == 1 || (bigInteger.bitLength() & 7) != 0) {
            return byArray;
        }
        byte[] byArray2 = new byte[bigInteger.bitLength() >> 3];
        System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
        return byArray2;
    }
}

