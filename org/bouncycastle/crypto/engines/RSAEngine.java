/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RSACoreEngine;

public class RSAEngine
implements AsymmetricBlockCipher {
    private RSACoreEngine core;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (this.core == null) {
            this.core = new RSACoreEngine();
        }
        this.core.init(bl, cipherParameters);
    }

    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) {
        if (this.core == null) {
            throw new IllegalStateException("RSA engine not initialised");
        }
        return this.core.convertOutput(this.core.processBlock(this.core.convertInput(byArray, n, n2)));
    }
}

