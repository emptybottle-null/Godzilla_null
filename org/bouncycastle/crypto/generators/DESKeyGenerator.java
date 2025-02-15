/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.DESParameters;

public class DESKeyGenerator
extends CipherKeyGenerator {
    public void init(KeyGenerationParameters keyGenerationParameters) {
        super.init(keyGenerationParameters);
        if (this.strength == 0 || this.strength == 7) {
            this.strength = 8;
        } else if (this.strength != 8) {
            throw new IllegalArgumentException("DES key must be 64 bits long.");
        }
    }

    public byte[] generateKey() {
        byte[] byArray = new byte[8];
        do {
            this.random.nextBytes(byArray);
            DESParameters.setOddParity(byArray);
        } while (DESParameters.isWeakKey(byArray, 0));
        return byArray;
    }
}

