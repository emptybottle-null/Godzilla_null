/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;

public interface AsymmetricBlockCipher {
    public void init(boolean var1, CipherParameters var2);

    public int getInputBlockSize();

    public int getOutputBlockSize();

    public byte[] processBlock(byte[] var1, int var2, int var3) throws InvalidCipherTextException;
}

