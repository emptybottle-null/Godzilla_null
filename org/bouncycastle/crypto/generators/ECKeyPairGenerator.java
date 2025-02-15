/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;

public class ECKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator,
ECConstants {
    ECDomainParameters params;
    SecureRandom random;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        ECKeyGenerationParameters eCKeyGenerationParameters = (ECKeyGenerationParameters)keyGenerationParameters;
        this.random = eCKeyGenerationParameters.getRandom();
        this.params = eCKeyGenerationParameters.getDomainParameters();
        if (this.random == null) {
            this.random = new SecureRandom();
        }
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger bigInteger;
        BigInteger bigInteger2 = this.params.getN();
        int n = bigInteger2.bitLength();
        int n2 = n >>> 2;
        while ((bigInteger = new BigInteger(n, this.random)).compareTo(TWO) < 0 || bigInteger.compareTo(bigInteger2) >= 0 || WNafUtil.getNafWeight(bigInteger) < n2) {
        }
        ECPoint eCPoint = this.createBasePointMultiplier().multiply(this.params.getG(), bigInteger);
        return new AsymmetricCipherKeyPair(new ECPublicKeyParameters(eCPoint, this.params), new ECPrivateKeyParameters(bigInteger, this.params));
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}

