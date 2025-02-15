/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.Seed;

class Wots {
    static final int WOTS_LOGW = 4;
    static final int WOTS_W = 16;
    static final int WOTS_L1 = 64;
    static final int WOTS_L = 67;
    static final int WOTS_LOG_L = 7;
    static final int WOTS_SIGBYTES = 2144;

    Wots() {
    }

    static void expand_seed(byte[] byArray, int n, byte[] byArray2, int n2) {
        Wots.clear(byArray, n, 2144);
        Seed.prg(byArray, n, 2144L, byArray2, n2);
    }

    private static void clear(byte[] byArray, int n, int n2) {
        for (int i = 0; i != n2; ++i) {
            byArray[i + n] = 0;
        }
    }

    static void gen_chain(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, int n3, int n4) {
        for (int i = 0; i < 32; ++i) {
            byArray[i + n] = byArray2[i + n2];
        }
        for (int i = 0; i < n4 && i < 16; ++i) {
            hashFunctions.hash_n_n_mask(byArray, n, byArray, n, byArray3, n3 + i * 32);
        }
    }

    void wots_pkgen(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, int n3) {
        Wots.expand_seed(byArray, n, byArray2, n2);
        for (int i = 0; i < 67; ++i) {
            Wots.gen_chain(hashFunctions, byArray, n + i * 32, byArray, n + i * 32, byArray3, n3, 15);
        }
    }

    void wots_sign(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, byte[] byArray3, byte[] byArray4) {
        int n2;
        int[] nArray = new int[67];
        int n3 = 0;
        for (n2 = 0; n2 < 64; n2 += 2) {
            nArray[n2] = byArray2[n2 / 2] & 0xF;
            nArray[n2 + 1] = (byArray2[n2 / 2] & 0xFF) >>> 4;
            n3 += 15 - nArray[n2];
            n3 += 15 - nArray[n2 + 1];
        }
        while (n2 < 67) {
            nArray[n2] = n3 & 0xF;
            n3 >>>= 4;
            ++n2;
        }
        Wots.expand_seed(byArray, n, byArray3, 0);
        for (n2 = 0; n2 < 67; ++n2) {
            Wots.gen_chain(hashFunctions, byArray, n + n2 * 32, byArray, n + n2 * 32, byArray4, 0, nArray[n2]);
        }
    }

    void wots_verify(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2, int n, byte[] byArray3, byte[] byArray4) {
        int n2;
        int[] nArray = new int[67];
        int n3 = 0;
        for (n2 = 0; n2 < 64; n2 += 2) {
            nArray[n2] = byArray3[n2 / 2] & 0xF;
            nArray[n2 + 1] = (byArray3[n2 / 2] & 0xFF) >>> 4;
            n3 += 15 - nArray[n2];
            n3 += 15 - nArray[n2 + 1];
        }
        while (n2 < 67) {
            nArray[n2] = n3 & 0xF;
            n3 >>>= 4;
            ++n2;
        }
        for (n2 = 0; n2 < 67; ++n2) {
            Wots.gen_chain(hashFunctions, byArray, n2 * 32, byArray2, n + n2 * 32, byArray4, nArray[n2] * 32, 15 - nArray[n2]);
        }
    }
}

