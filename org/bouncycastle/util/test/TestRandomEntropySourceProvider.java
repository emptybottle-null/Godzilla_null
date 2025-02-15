/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.test;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;

public class TestRandomEntropySourceProvider
implements EntropySourceProvider {
    private final SecureRandom _sr = new SecureRandom();
    private final boolean _predictionResistant;

    public TestRandomEntropySourceProvider(boolean bl) {
        this._predictionResistant = bl;
    }

    public EntropySource get(final int n) {
        return new EntropySource(){

            public boolean isPredictionResistant() {
                return TestRandomEntropySourceProvider.this._predictionResistant;
            }

            public byte[] getEntropy() {
                byte[] byArray = new byte[(n + 7) / 8];
                TestRandomEntropySourceProvider.this._sr.nextBytes(byArray);
                return byArray;
            }

            public int entropySize() {
                return n;
            }
        };
    }
}

