/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.generators.DHParametersHelper;
import org.bouncycastle.crypto.params.ElGamalParameters;

public class ElGamalParametersGenerator {
    private int size;
    private int certainty;
    private SecureRandom random;

    public void init(int n, int n2, SecureRandom secureRandom) {
        this.size = n;
        this.certainty = n2;
        this.random = secureRandom;
    }

    public ElGamalParameters generateParameters() {
        BigInteger[] bigIntegerArray = DHParametersHelper.generateSafePrimes(this.size, this.certainty, this.random);
        BigInteger bigInteger = bigIntegerArray[0];
        BigInteger bigInteger2 = bigIntegerArray[1];
        BigInteger bigInteger3 = DHParametersHelper.selectGenerator(bigInteger, bigInteger2, this.random);
        return new ElGamalParameters(bigInteger, bigInteger3);
    }
}

