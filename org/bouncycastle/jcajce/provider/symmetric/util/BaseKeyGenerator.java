/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class BaseKeyGenerator
extends KeyGeneratorSpi {
    protected String algName;
    protected int keySize;
    protected int defaultKeySize;
    protected CipherKeyGenerator engine;
    protected boolean uninitialised = true;

    protected BaseKeyGenerator(String string, int n, CipherKeyGenerator cipherKeyGenerator) {
        this.algName = string;
        this.keySize = this.defaultKeySize = n;
        this.engine = cipherKeyGenerator;
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Not Implemented");
    }

    protected void engineInit(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.engine.init(new KeyGenerationParameters(secureRandom, this.defaultKeySize));
            this.uninitialised = false;
        }
    }

    protected void engineInit(int n, SecureRandom secureRandom) {
        try {
            if (secureRandom == null) {
                secureRandom = new SecureRandom();
            }
            this.engine.init(new KeyGenerationParameters(secureRandom, n));
            this.uninitialised = false;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidParameterException(illegalArgumentException.getMessage());
        }
    }

    protected SecretKey engineGenerateKey() {
        if (this.uninitialised) {
            this.engine.init(new KeyGenerationParameters(new SecureRandom(), this.defaultKeySize));
            this.uninitialised = false;
        }
        return new SecretKeySpec(this.engine.generateKey(), this.algName);
    }
}

