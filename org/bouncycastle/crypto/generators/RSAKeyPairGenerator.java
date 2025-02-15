/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.math.Primes;
import org.bouncycastle.math.ec.WNafUtil;

public class RSAKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private RSAKeyGenerationParameters param;
    private int iterations;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (RSAKeyGenerationParameters)keyGenerationParameters;
        this.iterations = RSAKeyPairGenerator.getNumberOfIterations(this.param.getStrength(), this.param.getCertainty());
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = null;
        boolean bl = false;
        int n = this.param.getStrength();
        int n2 = (n + 1) / 2;
        int n3 = n - n2;
        int n4 = n / 2 - 100;
        if (n4 < n / 3) {
            n4 = n / 3;
        }
        int n5 = n >> 2;
        BigInteger bigInteger = BigInteger.valueOf(2L).pow(n / 2);
        BigInteger bigInteger2 = ONE.shiftLeft(n - 1);
        BigInteger bigInteger3 = ONE.shiftLeft(n4);
        while (!bl) {
            BigInteger bigInteger4;
            BigInteger bigInteger5;
            BigInteger bigInteger6;
            BigInteger bigInteger7;
            BigInteger bigInteger8;
            BigInteger bigInteger9;
            BigInteger bigInteger10;
            BigInteger bigInteger11;
            BigInteger bigInteger12 = this.param.getPublicExponent();
            BigInteger bigInteger13 = this.chooseRandomPrime(n2, bigInteger12, bigInteger2);
            while (true) {
                if ((bigInteger11 = (bigInteger10 = this.chooseRandomPrime(n3, bigInteger12, bigInteger2)).subtract(bigInteger13).abs()).bitLength() < n4 || bigInteger11.compareTo(bigInteger3) <= 0) {
                    continue;
                }
                bigInteger9 = bigInteger13.multiply(bigInteger10);
                if (bigInteger9.bitLength() != n) {
                    bigInteger13 = bigInteger13.max(bigInteger10);
                    continue;
                }
                if (WNafUtil.getNafWeight(bigInteger9) >= n5) break;
                bigInteger13 = this.chooseRandomPrime(n2, bigInteger12, bigInteger2);
            }
            if (bigInteger13.compareTo(bigInteger10) < 0) {
                bigInteger8 = bigInteger13;
                bigInteger13 = bigInteger10;
                bigInteger10 = bigInteger8;
            }
            if ((bigInteger7 = bigInteger12.modInverse(bigInteger6 = (bigInteger5 = bigInteger13.subtract(ONE)).divide(bigInteger8 = bigInteger5.gcd(bigInteger4 = bigInteger10.subtract(ONE))).multiply(bigInteger4))).compareTo(bigInteger) <= 0) continue;
            bl = true;
            bigInteger11 = bigInteger7.remainder(bigInteger5);
            BigInteger bigInteger14 = bigInteger7.remainder(bigInteger4);
            BigInteger bigInteger15 = bigInteger10.modInverse(bigInteger13);
            asymmetricCipherKeyPair = new AsymmetricCipherKeyPair(new RSAKeyParameters(false, bigInteger9, bigInteger12), new RSAPrivateCrtKeyParameters(bigInteger9, bigInteger12, bigInteger7, bigInteger13, bigInteger10, bigInteger11, bigInteger14, bigInteger15));
        }
        return asymmetricCipherKeyPair;
    }

    protected BigInteger chooseRandomPrime(int n, BigInteger bigInteger, BigInteger bigInteger2) {
        for (int i = 0; i != 5 * n; ++i) {
            BigInteger bigInteger3 = new BigInteger(n, 1, this.param.getRandom());
            if (bigInteger3.mod(bigInteger).equals(ONE) || bigInteger3.multiply(bigInteger3).compareTo(bigInteger2) < 0 || !this.isProbablePrime(bigInteger3) || !bigInteger.gcd(bigInteger3.subtract(ONE)).equals(ONE)) continue;
            return bigInteger3;
        }
        throw new IllegalStateException("unable to generate prime number for RSA key");
    }

    protected boolean isProbablePrime(BigInteger bigInteger) {
        return !Primes.hasAnySmallFactors(bigInteger) && Primes.isMRProbablePrime(bigInteger, this.param.getRandom(), this.iterations);
    }

    private static int getNumberOfIterations(int n, int n2) {
        if (n >= 1536) {
            return n2 <= 100 ? 3 : (n2 <= 128 ? 4 : 4 + (n2 - 128 + 1) / 2);
        }
        if (n >= 1024) {
            return n2 <= 100 ? 4 : (n2 <= 112 ? 5 : 5 + (n2 - 112 + 1) / 2);
        }
        if (n >= 512) {
            return n2 <= 80 ? 5 : (n2 <= 100 ? 7 : 7 + (n2 - 100 + 1) / 2);
        }
        return n2 <= 80 ? 40 : 40 + (n2 - 80 + 1) / 2;
    }
}

