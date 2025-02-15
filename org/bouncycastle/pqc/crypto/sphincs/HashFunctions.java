/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.sphincs.Permute;
import org.bouncycastle.util.Strings;

class HashFunctions {
    private static final byte[] hashc = Strings.toByteArray("expand 32-byte to 64-byte state!");
    private final Digest dig256;
    private final Digest dig512;
    private final Permute perm = new Permute();

    HashFunctions(Digest digest) {
        this(digest, null);
    }

    HashFunctions(Digest digest, Digest digest2) {
        this.dig256 = digest;
        this.dig512 = digest2;
    }

    int varlen_hash(byte[] byArray, int n, byte[] byArray2, int n2) {
        this.dig256.update(byArray2, 0, n2);
        this.dig256.doFinal(byArray, n);
        return 0;
    }

    Digest getMessageHash() {
        return this.dig512;
    }

    int hash_2n_n(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        byte[] byArray3 = new byte[64];
        for (n3 = 0; n3 < 32; ++n3) {
            byArray3[n3] = byArray2[n2 + n3];
            byArray3[n3 + 32] = hashc[n3];
        }
        this.perm.chacha_permute(byArray3, byArray3);
        for (n3 = 0; n3 < 32; ++n3) {
            byArray3[n3] = (byte)(byArray3[n3] ^ byArray2[n2 + n3 + 32]);
        }
        this.perm.chacha_permute(byArray3, byArray3);
        for (n3 = 0; n3 < 32; ++n3) {
            byArray[n + n3] = byArray3[n3];
        }
        return 0;
    }

    int hash_2n_n_mask(byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, int n3) {
        byte[] byArray4 = new byte[64];
        for (int i = 0; i < 64; ++i) {
            byArray4[i] = (byte)(byArray2[n2 + i] ^ byArray3[n3 + i]);
        }
        int n4 = this.hash_2n_n(byArray, n, byArray4, 0);
        return n4;
    }

    int hash_n_n(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        byte[] byArray3 = new byte[64];
        for (n3 = 0; n3 < 32; ++n3) {
            byArray3[n3] = byArray2[n2 + n3];
            byArray3[n3 + 32] = hashc[n3];
        }
        this.perm.chacha_permute(byArray3, byArray3);
        for (n3 = 0; n3 < 32; ++n3) {
            byArray[n + n3] = byArray3[n3];
        }
        return 0;
    }

    int hash_n_n_mask(byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3, int n3) {
        byte[] byArray4 = new byte[32];
        for (int i = 0; i < 32; ++i) {
            byArray4[i] = (byte)(byArray2[n2 + i] ^ byArray3[n3 + i]);
        }
        return this.hash_n_n(byArray, n, byArray4, 0);
    }
}

