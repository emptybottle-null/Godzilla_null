/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateCrtKey;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

public class KeyPairGeneratorSpi
extends KeyPairGenerator {
    static final BigInteger defaultPublicExponent = BigInteger.valueOf(65537L);
    RSAKeyGenerationParameters param;
    RSAKeyPairGenerator engine;

    public KeyPairGeneratorSpi(String string) {
        super(string);
    }

    public KeyPairGeneratorSpi() {
        super("RSA");
        this.engine = new RSAKeyPairGenerator();
        this.param = new RSAKeyGenerationParameters(defaultPublicExponent, new SecureRandom(), 2048, PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.engine.init(this.param);
    }

    public void initialize(int n, SecureRandom secureRandom) {
        this.param = new RSAKeyGenerationParameters(defaultPublicExponent, secureRandom, n, PrimeCertaintyCalculator.getDefaultCertainty(n));
        this.engine.init(this.param);
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof RSAKeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a RSAKeyGenParameterSpec");
        }
        RSAKeyGenParameterSpec rSAKeyGenParameterSpec = (RSAKeyGenParameterSpec)algorithmParameterSpec;
        this.param = new RSAKeyGenerationParameters(rSAKeyGenParameterSpec.getPublicExponent(), secureRandom, rSAKeyGenParameterSpec.getKeysize(), PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.engine.init(this.param);
    }

    public KeyPair generateKeyPair() {
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = this.engine.generateKeyPair();
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters)asymmetricCipherKeyPair.getPublic();
        RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters)asymmetricCipherKeyPair.getPrivate();
        return new KeyPair(new BCRSAPublicKey(rSAKeyParameters), new BCRSAPrivateCrtKey(rSAPrivateCrtKeyParameters));
    }
}

