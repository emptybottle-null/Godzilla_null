/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;

public interface Mac {
    public void init(CipherParameters var1) throws IllegalArgumentException;

    public String getAlgorithmName();

    public int getMacSize();

    public void update(byte var1) throws IllegalStateException;

    public void update(byte[] var1, int var2, int var3) throws DataLengthException, IllegalStateException;

    public int doFinal(byte[] var1, int var2) throws DataLengthException, IllegalStateException;

    public void reset();
}

