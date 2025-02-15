/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DHKeyGeneratorHelper;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;

public class DHKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private DHKeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (DHKeyGenerationParameters)keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        DHKeyGeneratorHelper dHKeyGeneratorHelper = DHKeyGeneratorHelper.INSTANCE;
        DHParameters dHParameters = this.param.getParameters();
        BigInteger bigInteger = dHKeyGeneratorHelper.calculatePrivate(dHParameters, this.param.getRandom());
        BigInteger bigInteger2 = dHKeyGeneratorHelper.calculatePublic(dHParameters, bigInteger);
        return new AsymmetricCipherKeyPair(new DHPublicKeyParameters(bigInteger2, dHParameters), new DHPrivateKeyParameters(bigInteger, dHParameters));
    }
}

