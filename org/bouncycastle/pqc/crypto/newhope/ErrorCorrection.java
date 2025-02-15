/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.pqc.crypto.newhope.ChaCha20;
import org.bouncycastle.util.Arrays;

class ErrorCorrection {
    ErrorCorrection() {
    }

    static int abs(int n) {
        int n2 = n >> 31;
        return (n ^ n2) - n2;
    }

    static int f(int[] nArray, int n, int n2, int n3) {
        int n4 = n3 * 2730;
        int n5 = n4 >> 25;
        n4 = n3 - n5 * 12289;
        n4 = 12288 - n4;
        int n6 = (n5 -= (n4 >>= 31)) & 1;
        int n7 = n5 >> 1;
        nArray[n] = n7 + n6;
        n6 = --n5 & 1;
        nArray[n2] = (n5 >> 1) + n6;
        return ErrorCorrection.abs(n3 - nArray[n] * 2 * 12289);
    }

    static int g(int n) {
        int n2 = n * 2730;
        int n3 = n2 >> 27;
        n2 = n - n3 * 49156;
        n2 = 49155 - n2;
        int n4 = (n3 -= (n2 >>= 31)) & 1;
        n3 = (n3 >> 1) + n4;
        return ErrorCorrection.abs((n3 *= 98312) - n);
    }

    static void helpRec(short[] sArray, short[] sArray2, byte[] byArray, byte by) {
        byte[] byArray2 = new byte[8];
        byArray2[0] = by;
        byte[] byArray3 = new byte[32];
        ChaCha20.process(byArray, byArray2, byArray3, 0, byArray3.length);
        int[] nArray = new int[8];
        int[] nArray2 = new int[4];
        for (int i = 0; i < 256; ++i) {
            int n = byArray3[i >>> 3] >>> (i & 7) & 1;
            int n2 = ErrorCorrection.f(nArray, 0, 4, 8 * sArray2[0 + i] + 4 * n);
            n2 += ErrorCorrection.f(nArray, 1, 5, 8 * sArray2[256 + i] + 4 * n);
            n2 += ErrorCorrection.f(nArray, 2, 6, 8 * sArray2[512 + i] + 4 * n);
            n2 += ErrorCorrection.f(nArray, 3, 7, 8 * sArray2[768 + i] + 4 * n);
            n2 = 24577 - n2 >> 31;
            nArray2[0] = ~n2 & nArray[0] ^ n2 & nArray[4];
            nArray2[1] = ~n2 & nArray[1] ^ n2 & nArray[5];
            nArray2[2] = ~n2 & nArray[2] ^ n2 & nArray[6];
            nArray2[3] = ~n2 & nArray[3] ^ n2 & nArray[7];
            sArray[0 + i] = (short)(nArray2[0] - nArray2[3] & 3);
            sArray[256 + i] = (short)(nArray2[1] - nArray2[3] & 3);
            sArray[512 + i] = (short)(nArray2[2] - nArray2[3] & 3);
            sArray[768 + i] = (short)(-n2 + 2 * nArray2[3] & 3);
        }
    }

    static short LDDecode(int n, int n2, int n3, int n4) {
        int n5 = ErrorCorrection.g(n);
        n5 += ErrorCorrection.g(n2);
        n5 += ErrorCorrection.g(n3);
        n5 += ErrorCorrection.g(n4);
        return (short)((n5 -= 98312) >>> 31);
    }

    static void rec(byte[] byArray, short[] sArray, short[] sArray2) {
        Arrays.fill(byArray, (byte)0);
        int[] nArray = new int[4];
        for (int i = 0; i < 256; ++i) {
            nArray[0] = 196624 + 8 * sArray[0 + i] - 12289 * (2 * sArray2[0 + i] + sArray2[768 + i]);
            nArray[1] = 196624 + 8 * sArray[256 + i] - 12289 * (2 * sArray2[256 + i] + sArray2[768 + i]);
            nArray[2] = 196624 + 8 * sArray[512 + i] - 12289 * (2 * sArray2[512 + i] + sArray2[768 + i]);
            nArray[3] = 196624 + 8 * sArray[768 + i] - 12289 * sArray2[768 + i];
            int n = i >>> 3;
            byArray[n] = (byte)(byArray[n] | ErrorCorrection.LDDecode(nArray[0], nArray[1], nArray[2], nArray[3]) << (i & 7));
        }
    }
}

