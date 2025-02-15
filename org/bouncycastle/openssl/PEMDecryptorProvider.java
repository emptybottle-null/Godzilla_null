/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.operator.OperatorCreationException;

public interface PEMDecryptorProvider {
    public PEMDecryptor get(String var1) throws OperatorCreationException;
}

