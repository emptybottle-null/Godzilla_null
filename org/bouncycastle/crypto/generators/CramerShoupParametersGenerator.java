/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.CramerShoupParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.util.BigIntegers;

public class CramerShoupParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private int size;
    private int certainty;
    private SecureRandom random;

    public void init(int n, int n2, SecureRandom secureRandom) {
        this.size = n;
        this.certainty = n2;
        this.random = secureRandom;
    }

    public CramerShoupParameters generateParameters() {
        BigInteger[] bigIntegerArray = ParametersHelper.generateSafePrimes(this.size, this.certainty, this.random);
        BigInteger bigInteger = bigIntegerArray[1];
        BigInteger bigInteger2 = ParametersHelper.selectGenerator(bigInteger, this.random);
        BigInteger bigInteger3 = ParametersHelper.selectGenerator(bigInteger, this.random);
        while (bigInteger2.equals(bigInteger3)) {
            bigInteger3 = ParametersHelper.selectGenerator(bigInteger, this.random);
        }
        return new CramerShoupParameters(bigInteger, bigInteger2, bigInteger3, new SHA256Digest());
    }

    public CramerShoupParameters generateParameters(DHParameters dHParameters) {
        BigInteger bigInteger = dHParameters.getP();
        BigInteger bigInteger2 = dHParameters.getG();
        BigInteger bigInteger3 = ParametersHelper.selectGenerator(bigInteger, this.random);
        while (bigInteger2.equals(bigInteger3)) {
            bigInteger3 = ParametersHelper.selectGenerator(bigInteger, this.random);
        }
        return new CramerShoupParameters(bigInteger, bigInteger2, bigInteger3, new SHA256Digest());
    }

    private static class ParametersHelper {
        private static final BigInteger TWO = BigInteger.valueOf(2L);

        private ParametersHelper() {
        }

        static BigInteger[] generateSafePrimes(int n, int n2, SecureRandom secureRandom) {
            BigInteger bigInteger;
            BigInteger bigInteger2;
            int n3 = n - 1;
            while (!(bigInteger2 = (bigInteger = new BigInteger(n3, 2, secureRandom)).shiftLeft(1).add(ONE)).isProbablePrime(n2) || n2 > 2 && !bigInteger.isProbablePrime(n2)) {
            }
            return new BigInteger[]{bigInteger2, bigInteger};
        }

        static BigInteger selectGenerator(BigInteger bigInteger, SecureRandom secureRandom) {
            BigInteger bigInteger2;
            BigInteger bigInteger3;
            BigInteger bigInteger4 = bigInteger.subtract(TWO);
            while ((bigInteger3 = (bigInteger2 = BigIntegers.createRandomInRange(TWO, bigInteger4, secureRandom)).modPow(TWO, bigInteger)).equals(ONE)) {
            }
            return bigInteger3;
        }
    }
}

