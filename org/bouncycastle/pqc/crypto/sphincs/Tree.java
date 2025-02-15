/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.Seed;
import org.bouncycastle.pqc.crypto.sphincs.Wots;

class Tree {
    Tree() {
    }

    static void l_tree(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, int n3) {
        int n4 = 67;
        int n5 = 0;
        for (int i = 0; i < 7; ++i) {
            for (n5 = 0; n5 < n4 >>> 1; ++n5) {
                hashFunctions.hash_2n_n_mask(byArray2, n2 + n5 * 32, byArray2, n2 + n5 * 2 * 32, byArray3, n3 + i * 2 * 32);
            }
            if ((n4 & 1) != 0) {
                System.arraycopy(byArray2, n2 + (n4 - 1) * 32, byArray2, n2 + (n4 >>> 1) * 32, 32);
                n4 = (n4 >>> 1) + 1;
                continue;
            }
            n4 >>>= 1;
        }
        System.arraycopy(byArray2, n2, byArray, n, 32);
    }

    static void treehash(HashFunctions hashFunctions, byte[] byArray, int n, int n2, byte[] byArray2, leafaddr leafaddr2, byte[] byArray3, int n3) {
        leafaddr leafaddr3 = new leafaddr(leafaddr2);
        byte[] byArray4 = new byte[(n2 + 1) * 32];
        int[] nArray = new int[n2 + 1];
        int n4 = 0;
        int n5 = (int)(leafaddr3.subleaf + (long)(1 << n2));
        while (leafaddr3.subleaf < (long)n5) {
            Tree.gen_leaf_wots(hashFunctions, byArray4, n4 * 32, byArray3, n3, byArray2, leafaddr3);
            nArray[n4] = 0;
            ++n4;
            while (n4 > 1 && nArray[n4 - 1] == nArray[n4 - 2]) {
                int n6 = 2 * (nArray[n4 - 1] + 7) * 32;
                hashFunctions.hash_2n_n_mask(byArray4, (n4 - 2) * 32, byArray4, (n4 - 2) * 32, byArray3, n3 + n6);
                int n7 = n4 - 2;
                nArray[n7] = nArray[n7] + 1;
                --n4;
            }
            ++leafaddr3.subleaf;
        }
        for (int i = 0; i < 32; ++i) {
            byArray[n + i] = byArray4[i];
        }
    }

    static void gen_leaf_wots(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, leafaddr leafaddr2) {
        byte[] byArray4 = new byte[32];
        byte[] byArray5 = new byte[2144];
        Wots wots = new Wots();
        Seed.get_seed(hashFunctions, byArray4, 0, byArray3, leafaddr2);
        wots.wots_pkgen(hashFunctions, byArray5, 0, byArray4, 0, byArray2, n2);
        Tree.l_tree(hashFunctions, byArray, n, byArray5, 0, byArray2, n2);
    }

    static class leafaddr {
        int level;
        long subtree;
        long subleaf;

        public leafaddr() {
        }

        public leafaddr(leafaddr leafaddr2) {
            this.level = leafaddr2.level;
            this.subtree = leafaddr2.subtree;
            this.subleaf = leafaddr2.subleaf;
        }
    }
}

