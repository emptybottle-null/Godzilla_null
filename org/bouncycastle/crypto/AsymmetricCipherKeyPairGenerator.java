/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;

public interface AsymmetricCipherKeyPairGenerator {
    public void init(KeyGenerationParameters var1);

    public AsymmetricCipherKeyPair generateKeyPair();
}

