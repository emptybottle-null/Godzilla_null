/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.DRBGProvider;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropyUtil;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

public class SP800SecureRandom
extends SecureRandom {
    private final DRBGProvider drbgProvider;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;
    private final EntropySource entropySource;
    private SP80090DRBG drbg;

    SP800SecureRandom(SecureRandom secureRandom, EntropySource entropySource, DRBGProvider dRBGProvider, boolean bl) {
        this.randomSource = secureRandom;
        this.entropySource = entropySource;
        this.drbgProvider = dRBGProvider;
        this.predictionResistant = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSeed(byte[] byArray) {
        SP800SecureRandom sP800SecureRandom = this;
        synchronized (sP800SecureRandom) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(byArray);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSeed(long l) {
        SP800SecureRandom sP800SecureRandom = this;
        synchronized (sP800SecureRandom) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(l);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void nextBytes(byte[] byArray) {
        SP800SecureRandom sP800SecureRandom = this;
        synchronized (sP800SecureRandom) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.get(this.entropySource);
            }
            if (this.drbg.generate(byArray, null, this.predictionResistant) < 0) {
                this.drbg.reseed(null);
                this.drbg.generate(byArray, null, this.predictionResistant);
            }
        }
    }

    public byte[] generateSeed(int n) {
        return EntropyUtil.generateSeed(this.entropySource, n);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void reseed(byte[] byArray) {
        SP800SecureRandom sP800SecureRandom = this;
        synchronized (sP800SecureRandom) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.get(this.entropySource);
            }
            this.drbg.reseed(byArray);
        }
    }
}

