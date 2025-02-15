/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;

public class DSAKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DSAKeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (DSAKeyGenerationParameters)keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        DSAParameters dSAParameters = this.param.getParameters();
        BigInteger bigInteger = DSAKeyPairGenerator.generatePrivateKey(dSAParameters.getQ(), this.param.getRandom());
        BigInteger bigInteger2 = DSAKeyPairGenerator.calculatePublicKey(dSAParameters.getP(), dSAParameters.getG(), bigInteger);
        return new AsymmetricCipherKeyPair(new DSAPublicKeyParameters(bigInteger2, dSAParameters), new DSAPrivateKeyParameters(bigInteger, dSAParameters));
    }

    private static BigInteger generatePrivateKey(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2;
        int n = bigInteger.bitLength() >>> 2;
        while (WNafUtil.getNafWeight(bigInteger2 = BigIntegers.createRandomInRange(ONE, bigInteger.subtract(ONE), secureRandom)) < n) {
        }
        return bigInteger2;
    }

    private static BigInteger calculatePublicKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.modPow(bigInteger3, bigInteger);
    }
}

