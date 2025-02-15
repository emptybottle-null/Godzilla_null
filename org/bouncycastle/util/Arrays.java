/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public final class Arrays {
    private Arrays() {
    }

    public static boolean areEqual(boolean[] blArray, boolean[] blArray2) {
        if (blArray == blArray2) {
            return true;
        }
        if (blArray == null || blArray2 == null) {
            return false;
        }
        if (blArray.length != blArray2.length) {
            return false;
        }
        for (int i = 0; i != blArray.length; ++i) {
            if (blArray[i] == blArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean areEqual(char[] cArray, char[] cArray2) {
        if (cArray == cArray2) {
            return true;
        }
        if (cArray == null || cArray2 == null) {
            return false;
        }
        if (cArray.length != cArray2.length) {
            return false;
        }
        for (int i = 0; i != cArray.length; ++i) {
            if (cArray[i] == cArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean areEqual(byte[] byArray, byte[] byArray2) {
        if (byArray == byArray2) {
            return true;
        }
        if (byArray == null || byArray2 == null) {
            return false;
        }
        if (byArray.length != byArray2.length) {
            return false;
        }
        for (int i = 0; i != byArray.length; ++i) {
            if (byArray[i] == byArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean areEqual(short[] sArray, short[] sArray2) {
        if (sArray == sArray2) {
            return true;
        }
        if (sArray == null || sArray2 == null) {
            return false;
        }
        if (sArray.length != sArray2.length) {
            return false;
        }
        for (int i = 0; i != sArray.length; ++i) {
            if (sArray[i] == sArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean constantTimeAreEqual(byte[] byArray, byte[] byArray2) {
        if (byArray == byArray2) {
            return true;
        }
        if (byArray == null || byArray2 == null) {
            return false;
        }
        if (byArray.length != byArray2.length) {
            return !Arrays.constantTimeAreEqual(byArray, byArray);
        }
        int n = 0;
        for (int i = 0; i != byArray.length; ++i) {
            n |= byArray[i] ^ byArray2[i];
        }
        return n == 0;
    }

    public static boolean areEqual(int[] nArray, int[] nArray2) {
        if (nArray == nArray2) {
            return true;
        }
        if (nArray == null || nArray2 == null) {
            return false;
        }
        if (nArray.length != nArray2.length) {
            return false;
        }
        for (int i = 0; i != nArray.length; ++i) {
            if (nArray[i] == nArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean areEqual(long[] lArray, long[] lArray2) {
        if (lArray == lArray2) {
            return true;
        }
        if (lArray == null || lArray2 == null) {
            return false;
        }
        if (lArray.length != lArray2.length) {
            return false;
        }
        for (int i = 0; i != lArray.length; ++i) {
            if (lArray[i] == lArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static boolean areEqual(Object[] objectArray, Object[] objectArray2) {
        if (objectArray == objectArray2) {
            return true;
        }
        if (objectArray == null || objectArray2 == null) {
            return false;
        }
        if (objectArray.length != objectArray2.length) {
            return false;
        }
        for (int i = 0; i != objectArray.length; ++i) {
            Object object = objectArray[i];
            Object object2 = objectArray2[i];
            if (!(object == null ? object2 != null : !object.equals(object2))) continue;
            return false;
        }
        return true;
    }

    public static int compareUnsigned(byte[] byArray, byte[] byArray2) {
        if (byArray == byArray2) {
            return 0;
        }
        if (byArray == null) {
            return -1;
        }
        if (byArray2 == null) {
            return 1;
        }
        int n = Math.min(byArray.length, byArray2.length);
        for (int i = 0; i < n; ++i) {
            int n2 = byArray[i] & 0xFF;
            int n3 = byArray2[i] & 0xFF;
            if (n2 < n3) {
                return -1;
            }
            if (n2 <= n3) continue;
            return 1;
        }
        if (byArray.length < byArray2.length) {
            return -1;
        }
        if (byArray.length > byArray2.length) {
            return 1;
        }
        return 0;
    }

    public static boolean contains(short[] sArray, short s) {
        for (int i = 0; i < sArray.length; ++i) {
            if (sArray[i] != s) continue;
            return true;
        }
        return false;
    }

    public static boolean contains(int[] nArray, int n) {
        for (int i = 0; i < nArray.length; ++i) {
            if (nArray[i] != n) continue;
            return true;
        }
        return false;
    }

    public static void fill(byte[] byArray, byte by) {
        for (int i = 0; i < byArray.length; ++i) {
            byArray[i] = by;
        }
    }

    public static void fill(char[] cArray, char c) {
        for (int i = 0; i < cArray.length; ++i) {
            cArray[i] = c;
        }
    }

    public static void fill(long[] lArray, long l) {
        for (int i = 0; i < lArray.length; ++i) {
            lArray[i] = l;
        }
    }

    public static void fill(short[] sArray, short s) {
        for (int i = 0; i < sArray.length; ++i) {
            sArray[i] = s;
        }
    }

    public static void fill(int[] nArray, int n) {
        for (int i = 0; i < nArray.length; ++i) {
            nArray[i] = n;
        }
    }

    public static int hashCode(byte[] byArray) {
        if (byArray == null) {
            return 0;
        }
        int n = byArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            n2 *= 257;
            n2 ^= byArray[n];
        }
        return n2;
    }

    public static int hashCode(byte[] byArray, int n, int n2) {
        if (byArray == null) {
            return 0;
        }
        int n3 = n2;
        int n4 = n3 + 1;
        while (--n3 >= 0) {
            n4 *= 257;
            n4 ^= byArray[n + n3];
        }
        return n4;
    }

    public static int hashCode(char[] cArray) {
        if (cArray == null) {
            return 0;
        }
        int n = cArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            n2 *= 257;
            n2 ^= cArray[n];
        }
        return n2;
    }

    public static int hashCode(int[][] nArray) {
        int n = 0;
        for (int i = 0; i != nArray.length; ++i) {
            n = n * 257 + Arrays.hashCode(nArray[i]);
        }
        return n;
    }

    public static int hashCode(int[] nArray) {
        if (nArray == null) {
            return 0;
        }
        int n = nArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            n2 *= 257;
            n2 ^= nArray[n];
        }
        return n2;
    }

    public static int hashCode(int[] nArray, int n, int n2) {
        if (nArray == null) {
            return 0;
        }
        int n3 = n2;
        int n4 = n3 + 1;
        while (--n3 >= 0) {
            n4 *= 257;
            n4 ^= nArray[n + n3];
        }
        return n4;
    }

    public static int hashCode(long[] lArray) {
        if (lArray == null) {
            return 0;
        }
        int n = lArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            long l = lArray[n];
            n2 *= 257;
            n2 ^= (int)l;
            n2 *= 257;
            n2 ^= (int)(l >>> 32);
        }
        return n2;
    }

    public static int hashCode(long[] lArray, int n, int n2) {
        if (lArray == null) {
            return 0;
        }
        int n3 = n2;
        int n4 = n3 + 1;
        while (--n3 >= 0) {
            long l = lArray[n + n3];
            n4 *= 257;
            n4 ^= (int)l;
            n4 *= 257;
            n4 ^= (int)(l >>> 32);
        }
        return n4;
    }

    public static int hashCode(short[][][] sArray) {
        int n = 0;
        for (int i = 0; i != sArray.length; ++i) {
            n = n * 257 + Arrays.hashCode(sArray[i]);
        }
        return n;
    }

    public static int hashCode(short[][] sArray) {
        int n = 0;
        for (int i = 0; i != sArray.length; ++i) {
            n = n * 257 + Arrays.hashCode(sArray[i]);
        }
        return n;
    }

    public static int hashCode(short[] sArray) {
        if (sArray == null) {
            return 0;
        }
        int n = sArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            n2 *= 257;
            n2 ^= sArray[n] & 0xFF;
        }
        return n2;
    }

    public static int hashCode(Object[] objectArray) {
        if (objectArray == null) {
            return 0;
        }
        int n = objectArray.length;
        int n2 = n + 1;
        while (--n >= 0) {
            n2 *= 257;
            n2 ^= objectArray[n].hashCode();
        }
        return n2;
    }

    public static byte[] clone(byte[] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[] byArray2 = new byte[byArray.length];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        return byArray2;
    }

    public static char[] clone(char[] cArray) {
        if (cArray == null) {
            return null;
        }
        char[] cArray2 = new char[cArray.length];
        System.arraycopy(cArray, 0, cArray2, 0, cArray.length);
        return cArray2;
    }

    public static byte[] clone(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            return null;
        }
        if (byArray2 == null || byArray2.length != byArray.length) {
            return Arrays.clone(byArray);
        }
        System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
        return byArray2;
    }

    public static byte[][] clone(byte[][] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[][] byArrayArray = new byte[byArray.length][];
        for (int i = 0; i != byArrayArray.length; ++i) {
            byArrayArray[i] = Arrays.clone(byArray[i]);
        }
        return byArrayArray;
    }

    public static byte[][][] clone(byte[][][] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[][][] byArrayArray = new byte[byArray.length][][];
        for (int i = 0; i != byArrayArray.length; ++i) {
            byArrayArray[i] = Arrays.clone(byArray[i]);
        }
        return byArrayArray;
    }

    public static int[] clone(int[] nArray) {
        if (nArray == null) {
            return null;
        }
        int[] nArray2 = new int[nArray.length];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        return nArray2;
    }

    public static long[] clone(long[] lArray) {
        if (lArray == null) {
            return null;
        }
        long[] lArray2 = new long[lArray.length];
        System.arraycopy(lArray, 0, lArray2, 0, lArray.length);
        return lArray2;
    }

    public static long[] clone(long[] lArray, long[] lArray2) {
        if (lArray == null) {
            return null;
        }
        if (lArray2 == null || lArray2.length != lArray.length) {
            return Arrays.clone(lArray);
        }
        System.arraycopy(lArray, 0, lArray2, 0, lArray2.length);
        return lArray2;
    }

    public static short[] clone(short[] sArray) {
        if (sArray == null) {
            return null;
        }
        short[] sArray2 = new short[sArray.length];
        System.arraycopy(sArray, 0, sArray2, 0, sArray.length);
        return sArray2;
    }

    public static BigInteger[] clone(BigInteger[] bigIntegerArray) {
        if (bigIntegerArray == null) {
            return null;
        }
        BigInteger[] bigIntegerArray2 = new BigInteger[bigIntegerArray.length];
        System.arraycopy(bigIntegerArray, 0, bigIntegerArray2, 0, bigIntegerArray.length);
        return bigIntegerArray2;
    }

    public static byte[] copyOf(byte[] byArray, int n) {
        byte[] byArray2 = new byte[n];
        if (n < byArray.length) {
            System.arraycopy(byArray, 0, byArray2, 0, n);
        } else {
            System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        }
        return byArray2;
    }

    public static char[] copyOf(char[] cArray, int n) {
        char[] cArray2 = new char[n];
        if (n < cArray.length) {
            System.arraycopy(cArray, 0, cArray2, 0, n);
        } else {
            System.arraycopy(cArray, 0, cArray2, 0, cArray.length);
        }
        return cArray2;
    }

    public static int[] copyOf(int[] nArray, int n) {
        int[] nArray2 = new int[n];
        if (n < nArray.length) {
            System.arraycopy(nArray, 0, nArray2, 0, n);
        } else {
            System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        }
        return nArray2;
    }

    public static long[] copyOf(long[] lArray, int n) {
        long[] lArray2 = new long[n];
        if (n < lArray.length) {
            System.arraycopy(lArray, 0, lArray2, 0, n);
        } else {
            System.arraycopy(lArray, 0, lArray2, 0, lArray.length);
        }
        return lArray2;
    }

    public static BigInteger[] copyOf(BigInteger[] bigIntegerArray, int n) {
        BigInteger[] bigIntegerArray2 = new BigInteger[n];
        if (n < bigIntegerArray.length) {
            System.arraycopy(bigIntegerArray, 0, bigIntegerArray2, 0, n);
        } else {
            System.arraycopy(bigIntegerArray, 0, bigIntegerArray2, 0, bigIntegerArray.length);
        }
        return bigIntegerArray2;
    }

    public static byte[] copyOfRange(byte[] byArray, int n, int n2) {
        int n3 = Arrays.getLength(n, n2);
        byte[] byArray2 = new byte[n3];
        if (byArray.length - n < n3) {
            System.arraycopy(byArray, n, byArray2, 0, byArray.length - n);
        } else {
            System.arraycopy(byArray, n, byArray2, 0, n3);
        }
        return byArray2;
    }

    public static int[] copyOfRange(int[] nArray, int n, int n2) {
        int n3 = Arrays.getLength(n, n2);
        int[] nArray2 = new int[n3];
        if (nArray.length - n < n3) {
            System.arraycopy(nArray, n, nArray2, 0, nArray.length - n);
        } else {
            System.arraycopy(nArray, n, nArray2, 0, n3);
        }
        return nArray2;
    }

    public static long[] copyOfRange(long[] lArray, int n, int n2) {
        int n3 = Arrays.getLength(n, n2);
        long[] lArray2 = new long[n3];
        if (lArray.length - n < n3) {
            System.arraycopy(lArray, n, lArray2, 0, lArray.length - n);
        } else {
            System.arraycopy(lArray, n, lArray2, 0, n3);
        }
        return lArray2;
    }

    public static BigInteger[] copyOfRange(BigInteger[] bigIntegerArray, int n, int n2) {
        int n3 = Arrays.getLength(n, n2);
        BigInteger[] bigIntegerArray2 = new BigInteger[n3];
        if (bigIntegerArray.length - n < n3) {
            System.arraycopy(bigIntegerArray, n, bigIntegerArray2, 0, bigIntegerArray.length - n);
        } else {
            System.arraycopy(bigIntegerArray, n, bigIntegerArray2, 0, n3);
        }
        return bigIntegerArray2;
    }

    private static int getLength(int n, int n2) {
        int n3 = n2 - n;
        if (n3 < 0) {
            StringBuffer stringBuffer = new StringBuffer(n);
            stringBuffer.append(" > ").append(n2);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        return n3;
    }

    public static byte[] append(byte[] byArray, byte by) {
        if (byArray == null) {
            return new byte[]{by};
        }
        int n = byArray.length;
        byte[] byArray2 = new byte[n + 1];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        byArray2[n] = by;
        return byArray2;
    }

    public static short[] append(short[] sArray, short s) {
        if (sArray == null) {
            return new short[]{s};
        }
        int n = sArray.length;
        short[] sArray2 = new short[n + 1];
        System.arraycopy(sArray, 0, sArray2, 0, n);
        sArray2[n] = s;
        return sArray2;
    }

    public static int[] append(int[] nArray, int n) {
        if (nArray == null) {
            return new int[]{n};
        }
        int n2 = nArray.length;
        int[] nArray2 = new int[n2 + 1];
        System.arraycopy(nArray, 0, nArray2, 0, n2);
        nArray2[n2] = n;
        return nArray2;
    }

    public static String[] append(String[] stringArray, String string) {
        if (stringArray == null) {
            return new String[]{string};
        }
        int n = stringArray.length;
        String[] stringArray2 = new String[n + 1];
        System.arraycopy(stringArray, 0, stringArray2, 0, n);
        stringArray2[n] = string;
        return stringArray2;
    }

    public static byte[] concatenate(byte[] byArray, byte[] byArray2) {
        if (byArray != null && byArray2 != null) {
            byte[] byArray3 = new byte[byArray.length + byArray2.length];
            System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
            System.arraycopy(byArray2, 0, byArray3, byArray.length, byArray2.length);
            return byArray3;
        }
        if (byArray2 != null) {
            return Arrays.clone(byArray2);
        }
        return Arrays.clone(byArray);
    }

    public static byte[] concatenate(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        if (byArray != null && byArray2 != null && byArray3 != null) {
            byte[] byArray4 = new byte[byArray.length + byArray2.length + byArray3.length];
            System.arraycopy(byArray, 0, byArray4, 0, byArray.length);
            System.arraycopy(byArray2, 0, byArray4, byArray.length, byArray2.length);
            System.arraycopy(byArray3, 0, byArray4, byArray.length + byArray2.length, byArray3.length);
            return byArray4;
        }
        if (byArray == null) {
            return Arrays.concatenate(byArray2, byArray3);
        }
        if (byArray2 == null) {
            return Arrays.concatenate(byArray, byArray3);
        }
        return Arrays.concatenate(byArray, byArray2);
    }

    public static byte[] concatenate(byte[] byArray, byte[] byArray2, byte[] byArray3, byte[] byArray4) {
        if (byArray != null && byArray2 != null && byArray3 != null && byArray4 != null) {
            byte[] byArray5 = new byte[byArray.length + byArray2.length + byArray3.length + byArray4.length];
            System.arraycopy(byArray, 0, byArray5, 0, byArray.length);
            System.arraycopy(byArray2, 0, byArray5, byArray.length, byArray2.length);
            System.arraycopy(byArray3, 0, byArray5, byArray.length + byArray2.length, byArray3.length);
            System.arraycopy(byArray4, 0, byArray5, byArray.length + byArray2.length + byArray3.length, byArray4.length);
            return byArray5;
        }
        if (byArray4 == null) {
            return Arrays.concatenate(byArray, byArray2, byArray3);
        }
        if (byArray3 == null) {
            return Arrays.concatenate(byArray, byArray2, byArray4);
        }
        if (byArray2 == null) {
            return Arrays.concatenate(byArray, byArray3, byArray4);
        }
        return Arrays.concatenate(byArray2, byArray3, byArray4);
    }

    public static byte[] concatenate(byte[][] byArray) {
        int n = 0;
        for (int i = 0; i != byArray.length; ++i) {
            n += byArray[i].length;
        }
        byte[] byArray2 = new byte[n];
        int n2 = 0;
        for (int i = 0; i != byArray.length; ++i) {
            System.arraycopy(byArray[i], 0, byArray2, n2, byArray[i].length);
            n2 += byArray[i].length;
        }
        return byArray2;
    }

    public static int[] concatenate(int[] nArray, int[] nArray2) {
        if (nArray == null) {
            return Arrays.clone(nArray2);
        }
        if (nArray2 == null) {
            return Arrays.clone(nArray);
        }
        int[] nArray3 = new int[nArray.length + nArray2.length];
        System.arraycopy(nArray, 0, nArray3, 0, nArray.length);
        System.arraycopy(nArray2, 0, nArray3, nArray.length, nArray2.length);
        return nArray3;
    }

    public static byte[] prepend(byte[] byArray, byte by) {
        if (byArray == null) {
            return new byte[]{by};
        }
        int n = byArray.length;
        byte[] byArray2 = new byte[n + 1];
        System.arraycopy(byArray, 0, byArray2, 1, n);
        byArray2[0] = by;
        return byArray2;
    }

    public static short[] prepend(short[] sArray, short s) {
        if (sArray == null) {
            return new short[]{s};
        }
        int n = sArray.length;
        short[] sArray2 = new short[n + 1];
        System.arraycopy(sArray, 0, sArray2, 1, n);
        sArray2[0] = s;
        return sArray2;
    }

    public static int[] prepend(int[] nArray, int n) {
        if (nArray == null) {
            return new int[]{n};
        }
        int n2 = nArray.length;
        int[] nArray2 = new int[n2 + 1];
        System.arraycopy(nArray, 0, nArray2, 1, n2);
        nArray2[0] = n;
        return nArray2;
    }

    public static byte[] reverse(byte[] byArray) {
        if (byArray == null) {
            return null;
        }
        int n = 0;
        int n2 = byArray.length;
        byte[] byArray2 = new byte[n2];
        while (--n2 >= 0) {
            byArray2[n2] = byArray[n++];
        }
        return byArray2;
    }

    public static int[] reverse(int[] nArray) {
        if (nArray == null) {
            return null;
        }
        int n = 0;
        int n2 = nArray.length;
        int[] nArray2 = new int[n2];
        while (--n2 >= 0) {
            nArray2[n2] = nArray[n++];
        }
        return nArray2;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Iterator<T>
    implements java.util.Iterator<T> {
        private final T[] dataArray;
        private int position = 0;

        public Iterator(T[] TArray) {
            this.dataArray = TArray;
        }

        @Override
        public boolean hasNext() {
            return this.position < this.dataArray.length;
        }

        @Override
        public T next() {
            if (this.position == this.dataArray.length) {
                throw new NoSuchElementException("Out of elements: " + this.position);
            }
            return this.dataArray[this.position++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }
}

