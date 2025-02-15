/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.BigEndianConversions;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

public final class IntUtils {
    private IntUtils() {
    }

    public static boolean equals(int[] nArray, int[] nArray2) {
        if (nArray.length != nArray2.length) {
            return false;
        }
        boolean bl = true;
        for (int i = nArray.length - 1; i >= 0; --i) {
            bl &= nArray[i] == nArray2[i];
        }
        return bl;
    }

    public static int[] clone(int[] nArray) {
        int[] nArray2 = new int[nArray.length];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        return nArray2;
    }

    public static void fill(int[] nArray, int n) {
        for (int i = nArray.length - 1; i >= 0; --i) {
            nArray[i] = n;
        }
    }

    public static void quicksort(int[] nArray) {
        IntUtils.quicksort(nArray, 0, nArray.length - 1);
    }

    public static void quicksort(int[] nArray, int n, int n2) {
        if (n2 > n) {
            int n3 = IntUtils.partition(nArray, n, n2, n2);
            IntUtils.quicksort(nArray, n, n3 - 1);
            IntUtils.quicksort(nArray, n3 + 1, n2);
        }
    }

    private static int partition(int[] nArray, int n, int n2, int n3) {
        int n4;
        int n5 = nArray[n3];
        nArray[n3] = nArray[n2];
        nArray[n2] = n5;
        int n6 = n;
        for (n4 = n; n4 < n2; ++n4) {
            if (nArray[n4] > n5) continue;
            int n7 = nArray[n6];
            nArray[n6] = nArray[n4];
            nArray[n4] = n7;
            ++n6;
        }
        n4 = nArray[n6];
        nArray[n6] = nArray[n2];
        nArray[n2] = n4;
        return n6;
    }

    public static int[] subArray(int[] nArray, int n, int n2) {
        int[] nArray2 = new int[n2 - n];
        System.arraycopy(nArray, n, nArray2, 0, n2 - n);
        return nArray2;
    }

    public static String toString(int[] nArray) {
        String string = "";
        for (int i = 0; i < nArray.length; ++i) {
            string = string + nArray[i] + " ";
        }
        return string;
    }

    public static String toHexString(int[] nArray) {
        return ByteUtils.toHexString(BigEndianConversions.toByteArray(nArray));
    }
}

