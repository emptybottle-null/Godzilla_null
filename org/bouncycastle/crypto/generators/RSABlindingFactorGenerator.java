/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSABlindingFactorGenerator {
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);
    private RSAKeyParameters key;
    private SecureRandom random;

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.key = (RSAKeyParameters)parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (RSAKeyParameters)cipherParameters;
            this.random = new SecureRandom();
        }
        if (this.key instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("generator requires RSA public key");
        }
    }

    public BigInteger generateBlindingFactor() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (this.key == null) {
            throw new IllegalStateException("generator not initialised");
        }
        BigInteger bigInteger3 = this.key.getModulus();
        int n = bigInteger3.bitLength() - 1;
        do {
            bigInteger2 = new BigInteger(n, this.random);
            bigInteger = bigInteger2.gcd(bigInteger3);
        } while (bigInteger2.equals(ZERO) || bigInteger2.equals(ONE) || !bigInteger.equals(ONE));
        return bigInteger2;
    }
}

