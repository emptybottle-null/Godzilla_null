/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.generators.DHParametersHelper;
import org.bouncycastle.crypto.params.DHParameters;

public class DHParametersGenerator {
    private int size;
    private int certainty;
    private SecureRandom random;
    private static final BigInteger TWO = BigInteger.valueOf(2L);

    public void init(int n, int n2, SecureRandom secureRandom) {
        this.size = n;
        this.certainty = n2;
        this.random = secureRandom;
    }

    public DHParameters generateParameters() {
        BigInteger[] bigIntegerArray = DHParametersHelper.generateSafePrimes(this.size, this.certainty, this.random);
        BigInteger bigInteger = bigIntegerArray[0];
        BigInteger bigInteger2 = bigIntegerArray[1];
        BigInteger bigInteger3 = DHParametersHelper.selectGenerator(bigInteger, bigInteger2, this.random);
        return new DHParameters(bigInteger, bigInteger3, bigInteger2, TWO, null);
    }
}

