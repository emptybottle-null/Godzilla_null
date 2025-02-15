/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

public interface AEADBlockCipher {
    public void init(boolean var1, CipherParameters var2) throws IllegalArgumentException;

    public String getAlgorithmName();

    public BlockCipher getUnderlyingCipher();

    public void processAADByte(byte var1);

    public void processAADBytes(byte[] var1, int var2, int var3);

    public int processByte(byte var1, byte[] var2, int var3) throws DataLengthException;

    public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException;

    public int doFinal(byte[] var1, int var2) throws IllegalStateException, InvalidCipherTextException;

    public byte[] getMac();

    public int getUpdateOutputSize(int var1);

    public int getOutputSize(int var1);

    public void reset();
}

