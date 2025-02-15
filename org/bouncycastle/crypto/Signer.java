/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;

public interface Signer {
    public void init(boolean var1, CipherParameters var2);

    public void update(byte var1);

    public void update(byte[] var1, int var2, int var3);

    public byte[] generateSignature() throws CryptoException, DataLengthException;

    public boolean verifySignature(byte[] var1);

    public void reset();
}

