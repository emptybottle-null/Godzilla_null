/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.Horst;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.Seed;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.pqc.crypto.sphincs.Wots;
import org.bouncycastle.util.Pack;

public class SPHINCS256Signer
implements MessageSigner {
    private final HashFunctions hashFunctions;
    private byte[] keyData;

    public SPHINCS256Signer(Digest digest, Digest digest2) {
        if (digest.getDigestSize() != 32) {
            throw new IllegalArgumentException("n-digest needs to produce 32 bytes of output");
        }
        if (digest2.getDigestSize() != 64) {
            throw new IllegalArgumentException("2n-digest needs to produce 64 bytes of output");
        }
        this.hashFunctions = new HashFunctions(digest, digest2);
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.keyData = bl ? ((SPHINCSPrivateKeyParameters)cipherParameters).getKeyData() : ((SPHINCSPublicKeyParameters)cipherParameters).getKeyData();
    }

    public byte[] generateSignature(byte[] byArray) {
        return this.crypto_sign(this.hashFunctions, byArray, this.keyData);
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        return this.verify(this.hashFunctions, byArray, byArray2, this.keyData);
    }

    static void validate_authpath(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2, int n, byte[] byArray3, int n2, byte[] byArray4, int n3) {
        int n4;
        byte[] byArray5 = new byte[64];
        if ((n & 1) != 0) {
            for (n4 = 0; n4 < 32; ++n4) {
                byArray5[32 + n4] = byArray2[n4];
            }
            for (n4 = 0; n4 < 32; ++n4) {
                byArray5[n4] = byArray3[n2 + n4];
            }
        } else {
            for (n4 = 0; n4 < 32; ++n4) {
                byArray5[n4] = byArray2[n4];
            }
            for (n4 = 0; n4 < 32; ++n4) {
                byArray5[32 + n4] = byArray3[n2 + n4];
            }
        }
        int n5 = n2 + 32;
        for (int i = 0; i < n3 - 1; ++i) {
            if (((n >>>= 1) & 1) != 0) {
                hashFunctions.hash_2n_n_mask(byArray5, 32, byArray5, 0, byArray4, 2 * (7 + i) * 32);
                for (n4 = 0; n4 < 32; ++n4) {
                    byArray5[n4] = byArray3[n5 + n4];
                }
            } else {
                hashFunctions.hash_2n_n_mask(byArray5, 0, byArray5, 0, byArray4, 2 * (7 + i) * 32);
                for (n4 = 0; n4 < 32; ++n4) {
                    byArray5[n4 + 32] = byArray3[n5 + n4];
                }
            }
            n5 += 32;
        }
        hashFunctions.hash_2n_n_mask(byArray, 0, byArray5, 0, byArray4, 2 * (7 + n3 - 1) * 32);
    }

    static void compute_authpath_wots(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2, int n, Tree.leafaddr leafaddr2, byte[] byArray3, byte[] byArray4, int n2) {
        int n3;
        Tree.leafaddr leafaddr3 = new Tree.leafaddr(leafaddr2);
        byte[] byArray5 = new byte[2048];
        byte[] byArray6 = new byte[1024];
        byte[] byArray7 = new byte[68608];
        leafaddr3.subleaf = 0L;
        while (leafaddr3.subleaf < 32L) {
            Seed.get_seed(hashFunctions, byArray6, (int)(leafaddr3.subleaf * 32L), byArray3, leafaddr3);
            ++leafaddr3.subleaf;
        }
        Wots wots = new Wots();
        leafaddr3.subleaf = 0L;
        while (leafaddr3.subleaf < 32L) {
            wots.wots_pkgen(hashFunctions, byArray7, (int)(leafaddr3.subleaf * 67L * 32L), byArray6, (int)(leafaddr3.subleaf * 32L), byArray4, 0);
            ++leafaddr3.subleaf;
        }
        leafaddr3.subleaf = 0L;
        while (leafaddr3.subleaf < 32L) {
            Tree.l_tree(hashFunctions, byArray5, (int)(1024L + leafaddr3.subleaf * 32L), byArray7, (int)(leafaddr3.subleaf * 67L * 32L), byArray4, 0);
            ++leafaddr3.subleaf;
        }
        int n4 = 0;
        for (n3 = 32; n3 > 0; n3 >>>= 1) {
            for (int i = 0; i < n3; i += 2) {
                hashFunctions.hash_2n_n_mask(byArray5, (n3 >>> 1) * 32 + (i >>> 1) * 32, byArray5, n3 * 32 + i * 32, byArray4, 2 * (7 + n4) * 32);
            }
            ++n4;
        }
        int n5 = (int)leafaddr2.subleaf;
        for (n3 = 0; n3 < n2; ++n3) {
            System.arraycopy(byArray5, (32 >>> n3) * 32 + (n5 >>> n3 ^ 1) * 32, byArray2, n + n3 * 32, 32);
        }
        System.arraycopy(byArray5, 32, byArray, 0, 32);
    }

    byte[] crypto_sign(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2) {
        int n;
        byte[] byArray3 = new byte[41000];
        byte[] byArray4 = new byte[32];
        byte[] byArray5 = new byte[64];
        long[] lArray = new long[8];
        byte[] byArray6 = new byte[32];
        byte[] byArray7 = new byte[32];
        byte[] byArray8 = new byte[1024];
        byte[] byArray9 = new byte[1088];
        for (n = 0; n < 1088; ++n) {
            byArray9[n] = byArray2[n];
        }
        int n2 = 40968;
        System.arraycopy(byArray9, 1056, byArray3, n2, 32);
        Digest digest = hashFunctions.getMessageHash();
        Object object = new byte[digest.getDigestSize()];
        digest.update(byArray3, n2, 32);
        digest.update(byArray, 0, byArray.length);
        digest.doFinal((byte[])object, 0);
        this.zerobytes(byArray3, n2, 32);
        for (int i = 0; i != lArray.length; ++i) {
            lArray[i] = Pack.littleEndianToLong(object, i * 8);
        }
        long l = lArray[0] & 0xFFFFFFFFFFFFFFFL;
        System.arraycopy(object, 16, byArray4, 0, 32);
        n2 = 39912;
        System.arraycopy(byArray4, 0, byArray3, n2, 32);
        Tree.leafaddr leafaddr2 = new Tree.leafaddr();
        leafaddr2.level = 11;
        leafaddr2.subtree = 0L;
        leafaddr2.subleaf = 0L;
        int n3 = n2 + 32;
        System.arraycopy(byArray9, 32, byArray3, n3, 1024);
        Tree.treehash(hashFunctions, byArray3, n3 + 1024, 5, byArray9, leafaddr2, byArray3, n3);
        digest = hashFunctions.getMessageHash();
        digest.update(byArray3, n2, 1088);
        digest.update(byArray, 0, byArray.length);
        digest.doFinal(byArray5, 0);
        Tree.leafaddr leafaddr3 = new Tree.leafaddr();
        leafaddr3.level = 12;
        leafaddr3.subleaf = (int)(l & 0x1FL);
        leafaddr3.subtree = l >>> 5;
        for (n = 0; n < 32; ++n) {
            byArray3[n] = byArray4[n];
        }
        int n4 = 32;
        System.arraycopy(byArray9, 32, byArray8, 0, 1024);
        for (n = 0; n < 8; ++n) {
            byArray3[n4 + n] = (byte)(l >>> 8 * n & 0xFFL);
        }
        Seed.get_seed(hashFunctions, byArray7, 0, byArray9, leafaddr3);
        object = new Horst();
        int n5 = Horst.horst_sign(hashFunctions, byArray3, n4 += 8, byArray6, byArray7, byArray8, byArray5);
        n4 += n5;
        Wots wots = new Wots();
        n = 0;
        while (n < 12) {
            leafaddr3.level = n++;
            Seed.get_seed(hashFunctions, byArray7, 0, byArray9, leafaddr3);
            wots.wots_sign(hashFunctions, byArray3, n4, byArray6, byArray7, byArray8);
            SPHINCS256Signer.compute_authpath_wots(hashFunctions, byArray6, byArray3, n4 += 2144, leafaddr3, byArray9, byArray8, 5);
            n4 += 160;
            leafaddr3.subleaf = (int)(leafaddr3.subtree & 0x1FL);
            leafaddr3.subtree >>>= 5;
        }
        this.zerobytes(byArray9, 0, 1088);
        return byArray3;
    }

    private void zerobytes(byte[] byArray, int n, int n2) {
        for (int i = 0; i != n2; ++i) {
            byArray[n + i] = 0;
        }
    }

    boolean verify(HashFunctions hashFunctions, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        int n;
        int n2 = byArray2.length;
        long l = 0L;
        byte[] byArray4 = new byte[2144];
        byte[] byArray5 = new byte[32];
        byte[] byArray6 = new byte[32];
        byte[] byArray7 = new byte[41000];
        byte[] byArray8 = new byte[1056];
        if (n2 != 41000) {
            throw new IllegalArgumentException("signature wrong size");
        }
        byte[] byArray9 = new byte[64];
        for (n = 0; n < 1056; ++n) {
            byArray8[n] = byArray3[n];
        }
        Object object = new byte[32];
        for (n = 0; n < 32; ++n) {
            object[n] = byArray2[n];
        }
        System.arraycopy(byArray2, 0, byArray7, 0, 41000);
        Digest digest = hashFunctions.getMessageHash();
        digest.update((byte[])object, 0, 32);
        digest.update(byArray8, 0, 1056);
        digest.update(byArray, 0, byArray.length);
        digest.doFinal(byArray9, 0);
        int n3 = 0;
        n3 += 32;
        n2 -= 32;
        for (n = 0; n < 8; ++n) {
            l ^= (long)(byArray7[n3 + n] & 0xFF) << 8 * n;
        }
        new Horst();
        Horst.horst_verify(hashFunctions, byArray6, byArray7, n3 + 8, byArray8, byArray9);
        n3 += 8;
        n2 -= 8;
        n3 += 13312;
        n2 -= 13312;
        object = new Wots();
        for (n = 0; n < 12; ++n) {
            ((Wots)object).wots_verify(hashFunctions, byArray4, byArray7, n3, byArray6, byArray8);
            n2 -= 2144;
            Tree.l_tree(hashFunctions, byArray5, 0, byArray4, 0, byArray8, 0);
            SPHINCS256Signer.validate_authpath(hashFunctions, byArray6, byArray5, (int)(l & 0x1FL), byArray7, n3 += 2144, byArray8, 5);
            l >>= 5;
            n3 += 160;
            n2 -= 160;
        }
        boolean bl = true;
        for (n = 0; n < 32; ++n) {
            if (byArray6[n] == byArray8[n + 1024]) continue;
            bl = false;
        }
        return bl;
    }
}

