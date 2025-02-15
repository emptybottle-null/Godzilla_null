/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;

public interface MessageEncryptor {
    public void init(boolean var1, CipherParameters var2);

    public byte[] messageEncrypt(byte[] var1);

    public byte[] messageDecrypt(byte[] var1) throws InvalidCipherTextException;
}

