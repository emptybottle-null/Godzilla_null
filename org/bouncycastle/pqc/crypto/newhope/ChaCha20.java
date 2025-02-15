/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

class ChaCha20 {
    ChaCha20() {
    }

    static void process(byte[] byArray, byte[] byArray2, byte[] byArray3, int n, int n2) {
        ChaChaEngine chaChaEngine = new ChaChaEngine(20);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(byArray), byArray2));
        chaChaEngine.processBytes(byArray3, n, n2, byArray3, n);
    }
}

