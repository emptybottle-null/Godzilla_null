/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DHKeyGeneratorHelper;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.ElGamalKeyGenerationParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;

public class ElGamalKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private ElGamalKeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (ElGamalKeyGenerationParameters)keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        DHKeyGeneratorHelper dHKeyGeneratorHelper = DHKeyGeneratorHelper.INSTANCE;
        ElGamalParameters elGamalParameters = this.param.getParameters();
        DHParameters dHParameters = new DHParameters(elGamalParameters.getP(), elGamalParameters.getG(), null, elGamalParameters.getL());
        BigInteger bigInteger = dHKeyGeneratorHelper.calculatePrivate(dHParameters, this.param.getRandom());
        BigInteger bigInteger2 = dHKeyGeneratorHelper.calculatePublic(dHParameters, bigInteger);
        return new AsymmetricCipherKeyPair(new ElGamalPublicKeyParameters(bigInteger2, elGamalParameters), new ElGamalPrivateKeyParameters(bigInteger, elGamalParameters));
    }
}

