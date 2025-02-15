/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.raw;

import org.bouncycastle.math.raw.Nat256;

public abstract class Mont256 {
    private static final long M = 0xFFFFFFFFL;

    public static int inverse32(int n) {
        int n2 = n;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        return n2;
    }

    public static void multAdd(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4, int n) {
        int n2 = 0;
        long l = (long)nArray2[0] & 0xFFFFFFFFL;
        for (int i = 0; i < 8; ++i) {
            long l2 = (long)nArray3[0] & 0xFFFFFFFFL;
            long l3 = (long)nArray[i] & 0xFFFFFFFFL;
            long l4 = l3 * l;
            long l5 = (l4 & 0xFFFFFFFFL) + l2;
            long l6 = (long)((int)l5 * n) & 0xFFFFFFFFL;
            long l7 = l6 * ((long)nArray4[0] & 0xFFFFFFFFL);
            l5 += l7 & 0xFFFFFFFFL;
            l5 = (l5 >>> 32) + (l4 >>> 32) + (l7 >>> 32);
            for (int j = 1; j < 8; ++j) {
                l4 = l3 * ((long)nArray2[j] & 0xFFFFFFFFL);
                l7 = l6 * ((long)nArray4[j] & 0xFFFFFFFFL);
                nArray3[j - 1] = (int)(l5 += (l4 & 0xFFFFFFFFL) + (l7 & 0xFFFFFFFFL) + ((long)nArray3[j] & 0xFFFFFFFFL));
                l5 = (l5 >>> 32) + (l4 >>> 32) + (l7 >>> 32);
            }
            nArray3[7] = (int)(l5 += (long)n2 & 0xFFFFFFFFL);
            n2 = (int)(l5 >>> 32);
        }
        if (n2 != 0 || Nat256.gte(nArray3, nArray4)) {
            Nat256.sub(nArray3, nArray4, nArray3);
        }
    }

    public static void multAddXF(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4) {
        int n = 0;
        long l = (long)nArray2[0] & 0xFFFFFFFFL;
        for (int i = 0; i < 8; ++i) {
            long l2 = (long)nArray[i] & 0xFFFFFFFFL;
            long l3 = l2 * l + ((long)nArray3[0] & 0xFFFFFFFFL);
            long l4 = l3 & 0xFFFFFFFFL;
            l3 = (l3 >>> 32) + l4;
            for (int j = 1; j < 8; ++j) {
                long l5 = l2 * ((long)nArray2[j] & 0xFFFFFFFFL);
                long l6 = l4 * ((long)nArray4[j] & 0xFFFFFFFFL);
                nArray3[j - 1] = (int)(l3 += (l5 & 0xFFFFFFFFL) + (l6 & 0xFFFFFFFFL) + ((long)nArray3[j] & 0xFFFFFFFFL));
                l3 = (l3 >>> 32) + (l5 >>> 32) + (l6 >>> 32);
            }
            nArray3[7] = (int)(l3 += (long)n & 0xFFFFFFFFL);
            n = (int)(l3 >>> 32);
        }
        if (n != 0 || Nat256.gte(nArray3, nArray4)) {
            Nat256.sub(nArray3, nArray4, nArray3);
        }
    }

    public static void reduce(int[] nArray, int[] nArray2, int n) {
        for (int i = 0; i < 8; ++i) {
            int n2 = nArray[0];
            long l = (long)(n2 * n) & 0xFFFFFFFFL;
            long l2 = l * ((long)nArray2[0] & 0xFFFFFFFFL) + ((long)n2 & 0xFFFFFFFFL);
            l2 >>>= 32;
            for (int j = 1; j < 8; ++j) {
                nArray[j - 1] = (int)(l2 += l * ((long)nArray2[j] & 0xFFFFFFFFL) + ((long)nArray[j] & 0xFFFFFFFFL));
                l2 >>>= 32;
            }
            nArray[7] = (int)l2;
        }
        if (Nat256.gte(nArray, nArray2)) {
            Nat256.sub(nArray, nArray2, nArray);
        }
    }

    public static void reduceXF(int[] nArray, int[] nArray2) {
        for (int i = 0; i < 8; ++i) {
            long l;
            int n = nArray[0];
            long l2 = l = (long)n & 0xFFFFFFFFL;
            for (int j = 1; j < 8; ++j) {
                nArray[j - 1] = (int)(l2 += l * ((long)nArray2[j] & 0xFFFFFFFFL) + ((long)nArray[j] & 0xFFFFFFFFL));
                l2 >>>= 32;
            }
            nArray[7] = (int)l2;
        }
        if (Nat256.gte(nArray, nArray2)) {
            Nat256.sub(nArray, nArray2, nArray);
        }
    }
}

