/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;

public interface StreamCipher {
    public void init(boolean var1, CipherParameters var2) throws IllegalArgumentException;

    public String getAlgorithmName();

    public byte returnByte(byte var1);

    public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException;

    public void reset();
}

