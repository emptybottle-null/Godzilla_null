/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class CipherKeyGenerator {
    protected SecureRandom random;
    protected int strength;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.strength = (keyGenerationParameters.getStrength() + 7) / 8;
    }

    public byte[] generateKey() {
        byte[] byArray = new byte[this.strength];
        this.random.nextBytes(byArray);
        return byArray;
    }
}

