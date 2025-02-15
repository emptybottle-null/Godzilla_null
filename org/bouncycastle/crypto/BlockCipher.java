/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;

public interface BlockCipher {
    public void init(boolean var1, CipherParameters var2) throws IllegalArgumentException;

    public String getAlgorithmName();

    public int getBlockSize();

    public int processBlock(byte[] var1, int var2, byte[] var3, int var4) throws DataLengthException, IllegalStateException;

    public void reset();
}

