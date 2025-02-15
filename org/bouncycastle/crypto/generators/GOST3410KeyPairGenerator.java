/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.bouncycastle.crypto.params.GOST3410PublicKeyParameters;
import org.bouncycastle.math.ec.WNafUtil;

public class GOST3410KeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private GOST3410KeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (GOST3410KeyGenerationParameters)keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger bigInteger;
        GOST3410Parameters gOST3410Parameters = this.param.getParameters();
        SecureRandom secureRandom = this.param.getRandom();
        BigInteger bigInteger2 = gOST3410Parameters.getQ();
        BigInteger bigInteger3 = gOST3410Parameters.getP();
        BigInteger bigInteger4 = gOST3410Parameters.getA();
        int n = 64;
        while ((bigInteger = new BigInteger(256, secureRandom)).signum() < 1 || bigInteger.compareTo(bigInteger2) >= 0 || WNafUtil.getNafWeight(bigInteger) < n) {
        }
        BigInteger bigInteger5 = bigInteger4.modPow(bigInteger, bigInteger3);
        return new AsymmetricCipherKeyPair(new GOST3410PublicKeyParameters(bigInteger5, gOST3410Parameters), new GOST3410PrivateKeyParameters(bigInteger, gOST3410Parameters));
    }
}

