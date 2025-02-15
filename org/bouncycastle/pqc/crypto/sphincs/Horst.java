/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.Seed;

class Horst {
    static final int HORST_LOGT = 16;
    static final int HORST_T = 65536;
    static final int HORST_K = 32;
    static final int HORST_SKBYTES = 32;
    static final int HORST_SIGBYTES = 13312;
    static final int N_MASKS = 32;

    Horst() {
    }

    static void expand_seed(byte[] byArray, byte[] byArray2) {
        Seed.prg(byArray, 0, 0x200000L, byArray2, 0);
    }

    static int horst_sign(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, byte[] byArray3, byte[] byArray4, byte[] byArray5) {
        int n2;
        int n3;
        byte[] byArray6 = new byte[0x200000];
        int n4 = n;
        byte[] byArray7 = new byte[4194272];
        Horst.expand_seed(byArray6, byArray3);
        for (n3 = 0; n3 < 65536; ++n3) {
            hashFunctions.hash_n_n(byArray7, (65535 + n3) * 32, byArray6, n3 * 32);
        }
        for (n3 = 0; n3 < 16; ++n3) {
            long l = (1 << 16 - n3) - 1;
            long l2 = (1 << 16 - n3 - 1) - 1;
            for (n2 = 0; n2 < 1 << 16 - n3 - 1; ++n2) {
                hashFunctions.hash_2n_n_mask(byArray7, (int)((l2 + (long)n2) * 32L), byArray7, (int)((l + (long)(2 * n2)) * 32L), byArray4, 2 * n3 * 32);
            }
        }
        for (n2 = 2016; n2 < 4064; ++n2) {
            byArray[n4++] = byArray7[n2];
        }
        for (n3 = 0; n3 < 32; ++n3) {
            int n5;
            int n6 = (byArray5[2 * n3] & 0xFF) + ((byArray5[2 * n3 + 1] & 0xFF) << 8);
            for (n5 = 0; n5 < 32; ++n5) {
                byArray[n4++] = byArray6[n6 * 32 + n5];
            }
            n6 += 65535;
            for (n2 = 0; n2 < 10; ++n2) {
                n6 = (n6 & 1) != 0 ? n6 + 1 : n6 - 1;
                for (n5 = 0; n5 < 32; ++n5) {
                    byArray[n4++] = byArray7[n6 * 32 + n5];
                }
                n6 = (n6 - 1) / 2;
            }
        }
        for (n3 = 0; n3 < 32; ++n3) {
            byArray2[n3] = byArray7[n3];
        }
        return 13312;
    }

    static int horst_verify(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2, int n, byte[] byArray3, byte[] byArray4) {
        int n2;
        byte[] byArray5 = new byte[1024];
        int n3 = n + 2048;
        for (int i = 0; i < 32; ++i) {
            int n4;
            int n5 = (byArray4[2 * i] & 0xFF) + ((byArray4[2 * i + 1] & 0xFF) << 8);
            if ((n5 & 1) == 0) {
                hashFunctions.hash_n_n(byArray5, 0, byArray2, n3);
                for (n4 = 0; n4 < 32; ++n4) {
                    byArray5[32 + n4] = byArray2[n3 + 32 + n4];
                }
            } else {
                hashFunctions.hash_n_n(byArray5, 32, byArray2, n3);
                for (n4 = 0; n4 < 32; ++n4) {
                    byArray5[n4] = byArray2[n3 + 32 + n4];
                }
            }
            n3 += 64;
            for (n2 = 1; n2 < 10; ++n2) {
                if (((n5 >>>= 1) & 1) == 0) {
                    hashFunctions.hash_2n_n_mask(byArray5, 0, byArray5, 0, byArray3, 2 * (n2 - 1) * 32);
                    for (n4 = 0; n4 < 32; ++n4) {
                        byArray5[32 + n4] = byArray2[n3 + n4];
                    }
                } else {
                    hashFunctions.hash_2n_n_mask(byArray5, 32, byArray5, 0, byArray3, 2 * (n2 - 1) * 32);
                    for (n4 = 0; n4 < 32; ++n4) {
                        byArray5[n4] = byArray2[n3 + n4];
                    }
                }
                n3 += 32;
            }
            n5 >>>= 1;
            hashFunctions.hash_2n_n_mask(byArray5, 0, byArray5, 0, byArray3, 576);
            for (n4 = 0; n4 < 32; ++n4) {
                if (byArray2[n + n5 * 32 + n4] == byArray5[n4]) continue;
                for (n4 = 0; n4 < 32; ++n4) {
                    byArray[n4] = 0;
                }
                return -1;
            }
        }
        for (n2 = 0; n2 < 32; ++n2) {
            hashFunctions.hash_2n_n_mask(byArray5, n2 * 32, byArray2, n + 2 * n2 * 32, byArray3, 640);
        }
        for (n2 = 0; n2 < 16; ++n2) {
            hashFunctions.hash_2n_n_mask(byArray5, n2 * 32, byArray5, 2 * n2 * 32, byArray3, 704);
        }
        for (n2 = 0; n2 < 8; ++n2) {
            hashFunctions.hash_2n_n_mask(byArray5, n2 * 32, byArray5, 2 * n2 * 32, byArray3, 768);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            hashFunctions.hash_2n_n_mask(byArray5, n2 * 32, byArray5, 2 * n2 * 32, byArray3, 832);
        }
        for (n2 = 0; n2 < 2; ++n2) {
            hashFunctions.hash_2n_n_mask(byArray5, n2 * 32, byArray5, 2 * n2 * 32, byArray3, 896);
        }
        hashFunctions.hash_2n_n_mask(byArray, 0, byArray5, 0, byArray3, 960);
        return 0;
    }
}

