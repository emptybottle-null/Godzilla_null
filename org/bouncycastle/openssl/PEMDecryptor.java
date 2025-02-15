/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import org.bouncycastle.openssl.PEMException;

public interface PEMDecryptor {
    public byte[] decrypt(byte[] var1, byte[] var2) throws PEMException;
}

