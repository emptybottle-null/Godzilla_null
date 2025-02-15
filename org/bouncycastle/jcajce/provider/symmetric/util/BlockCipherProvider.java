/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import org.bouncycastle.crypto.BlockCipher;

public interface BlockCipherProvider {
    public BlockCipher get();
}

