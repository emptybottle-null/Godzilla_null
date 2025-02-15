/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.util.Pack;

class Seed {
    Seed() {
    }

    static void get_seed(HashFunctions hashFunctions, byte[] byArray, int n, byte[] byArray2, Tree.leafaddr leafaddr2) {
        byte[] byArray3 = new byte[40];
        for (int i = 0; i < 32; ++i) {
            byArray3[i] = byArray2[i];
        }
        long l = leafaddr2.level;
        l |= leafaddr2.subtree << 4;
        Pack.longToLittleEndian(l |= leafaddr2.subleaf << 59, byArray3, 32);
        hashFunctions.varlen_hash(byArray, n, byArray3, byArray3.length);
    }

    static void prg(byte[] byArray, int n, long l, byte[] byArray2, int n2) {
        byte[] byArray3 = new byte[8];
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(byArray2, n2, 32), byArray3));
        chaChaEngine.processBytes(byArray, n, (int)l, byArray, n);
    }
}

