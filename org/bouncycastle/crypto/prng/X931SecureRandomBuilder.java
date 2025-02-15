/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.BasicEntropySourceProvider;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.X931RNG;
import org.bouncycastle.crypto.prng.X931SecureRandom;
import org.bouncycastle.util.Pack;

public class X931SecureRandomBuilder {
    private SecureRandom random;
    private EntropySourceProvider entropySourceProvider;
    private byte[] dateTimeVector;

    public X931SecureRandomBuilder() {
        this(new SecureRandom(), false);
    }

    public X931SecureRandomBuilder(SecureRandom secureRandom, boolean bl) {
        this.random = secureRandom;
        this.entropySourceProvider = new BasicEntropySourceProvider(this.random, bl);
    }

    public X931SecureRandomBuilder(EntropySourceProvider entropySourceProvider) {
        this.random = null;
        this.entropySourceProvider = entropySourceProvider;
    }

    public X931SecureRandomBuilder setDateTimeVector(byte[] byArray) {
        this.dateTimeVector = byArray;
        return this;
    }

    public X931SecureRandom build(BlockCipher blockCipher, KeyParameter keyParameter, boolean bl) {
        if (this.dateTimeVector == null) {
            this.dateTimeVector = new byte[blockCipher.getBlockSize()];
            Pack.longToBigEndian(System.currentTimeMillis(), this.dateTimeVector, 0);
        }
        blockCipher.init(true, keyParameter);
        return new X931SecureRandom(this.random, new X931RNG(blockCipher, this.dateTimeVector, this.entropySourceProvider.get(blockCipher.getBlockSize() * 8)), bl);
    }
}

