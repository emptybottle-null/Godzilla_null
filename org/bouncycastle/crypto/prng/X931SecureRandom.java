/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.EntropyUtil;
import org.bouncycastle.crypto.prng.X931RNG;

public class X931SecureRandom
extends SecureRandom {
    private final boolean predictionResistant;
    private final SecureRandom randomSource;
    private final X931RNG drbg;

    X931SecureRandom(SecureRandom secureRandom, X931RNG x931RNG, boolean bl) {
        this.randomSource = secureRandom;
        this.drbg = x931RNG;
        this.predictionResistant = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSeed(byte[] byArray) {
        X931SecureRandom x931SecureRandom = this;
        synchronized (x931SecureRandom) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(byArray);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSeed(long l) {
        X931SecureRandom x931SecureRandom = this;
        synchronized (x931SecureRandom) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(l);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void nextBytes(byte[] byArray) {
        X931SecureRandom x931SecureRandom = this;
        synchronized (x931SecureRandom) {
            if (this.drbg.generate(byArray, this.predictionResistant) < 0) {
                this.drbg.reseed();
                this.drbg.generate(byArray, this.predictionResistant);
            }
        }
    }

    public byte[] generateSeed(int n) {
        return EntropyUtil.generateSeed(this.drbg.getEntropySource(), n);
    }
}

