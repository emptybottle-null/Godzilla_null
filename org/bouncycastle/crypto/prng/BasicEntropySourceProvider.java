/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.X931SecureRandom;

public class BasicEntropySourceProvider
implements EntropySourceProvider {
    private final SecureRandom _sr;
    private final boolean _predictionResistant;

    public BasicEntropySourceProvider(SecureRandom secureRandom, boolean bl) {
        this._sr = secureRandom;
        this._predictionResistant = bl;
    }

    public EntropySource get(final int n) {
        return new EntropySource(){

            public boolean isPredictionResistant() {
                return BasicEntropySourceProvider.this._predictionResistant;
            }

            public byte[] getEntropy() {
                if (BasicEntropySourceProvider.this._sr instanceof SP800SecureRandom || BasicEntropySourceProvider.this._sr instanceof X931SecureRandom) {
                    byte[] byArray = new byte[(n + 7) / 8];
                    BasicEntropySourceProvider.this._sr.nextBytes(byArray);
                    return byArray;
                }
                return BasicEntropySourceProvider.this._sr.generateSeed((n + 7) / 8);
            }

            public int entropySize() {
                return n;
            }
        };
    }
}

