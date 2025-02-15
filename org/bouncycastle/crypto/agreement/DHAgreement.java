/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.generators.DHKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class DHAgreement {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DHPrivateKeyParameters key;
    private DHParameters dhParams;
    private BigInteger privateValue;
    private SecureRandom random;

    public void init(CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter)parametersWithRandom.getParameters();
        } else {
            this.random = new SecureRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter)cipherParameters;
        }
        if (!(asymmetricKeyParameter instanceof DHPrivateKeyParameters)) {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        }
        this.key = (DHPrivateKeyParameters)asymmetricKeyParameter;
        this.dhParams = this.key.getParameters();
    }

    public BigInteger calculateMessage() {
        DHKeyPairGenerator dHKeyPairGenerator = new DHKeyPairGenerator();
        dHKeyPairGenerator.init(new DHKeyGenerationParameters(this.random, this.dhParams));
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = dHKeyPairGenerator.generateKeyPair();
        this.privateValue = ((DHPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate()).getX();
        return ((DHPublicKeyParameters)asymmetricCipherKeyPair.getPublic()).getY();
    }

    public BigInteger calculateAgreement(DHPublicKeyParameters dHPublicKeyParameters, BigInteger bigInteger) {
        if (!dHPublicKeyParameters.getParameters().equals(this.dhParams)) {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        }
        BigInteger bigInteger2 = this.dhParams.getP();
        BigInteger bigInteger3 = dHPublicKeyParameters.getY();
        if (bigInteger3 == null || bigInteger3.compareTo(ONE) <= 0 || bigInteger3.compareTo(bigInteger2.subtract(ONE)) >= 0) {
            throw new IllegalArgumentException("Diffie-Hellman public key is weak");
        }
        BigInteger bigInteger4 = bigInteger3.modPow(this.privateValue, bigInteger2);
        if (bigInteger4.equals(ONE)) {
            throw new IllegalStateException("Shared key can't be 1");
        }
        return bigInteger.modPow(this.key.getX(), bigInteger2).multiply(bigInteger4).mod(bigInteger2);
    }
}

