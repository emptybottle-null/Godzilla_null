/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;

public class NaccacheSternKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private static int[] smallPrimes = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private NaccacheSternKeyGenerationParameters param;
    private static final BigInteger ONE = BigInteger.valueOf(1L);

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters)keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        int n;
        int n2 = this.param.getStrength();
        SecureRandom secureRandom = this.param.getRandom();
        int n3 = this.param.getCertainty();
        boolean bl = this.param.isDebug();
        if (bl) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector vector = NaccacheSternKeyPairGenerator.findFirstPrimes(this.param.getCntSmallPrimes());
        vector = NaccacheSternKeyPairGenerator.permuteList(vector, secureRandom);
        BigInteger bigInteger6 = ONE;
        BigInteger bigInteger7 = ONE;
        for (n = 0; n < vector.size() / 2; ++n) {
            bigInteger6 = bigInteger6.multiply((BigInteger)vector.elementAt(n));
        }
        for (n = vector.size() / 2; n < vector.size(); ++n) {
            bigInteger7 = bigInteger7.multiply((BigInteger)vector.elementAt(n));
        }
        BigInteger bigInteger8 = bigInteger6.multiply(bigInteger7);
        int n4 = n2 - bigInteger8.bitLength() - 48;
        BigInteger bigInteger9 = NaccacheSternKeyPairGenerator.generatePrime(n4 / 2 + 1, n3, secureRandom);
        BigInteger bigInteger10 = NaccacheSternKeyPairGenerator.generatePrime(n4 / 2 + 1, n3, secureRandom);
        long l = 0L;
        if (bl) {
            System.out.println("generating p and q");
        }
        BigInteger bigInteger11 = bigInteger9.multiply(bigInteger6).shiftLeft(1);
        BigInteger bigInteger12 = bigInteger10.multiply(bigInteger7).shiftLeft(1);
        while (true) {
            ++l;
            bigInteger5 = NaccacheSternKeyPairGenerator.generatePrime(24, n3, secureRandom);
            bigInteger4 = bigInteger5.multiply(bigInteger11).add(ONE);
            if (!bigInteger4.isProbablePrime(n3)) continue;
            while (bigInteger5.equals(bigInteger3 = NaccacheSternKeyPairGenerator.generatePrime(24, n3, secureRandom)) || !(bigInteger2 = bigInteger3.multiply(bigInteger12).add(ONE)).isProbablePrime(n3)) {
            }
            if (!bigInteger8.gcd(bigInteger5.multiply(bigInteger3)).equals(ONE)) continue;
            if (bigInteger4.multiply(bigInteger2).bitLength() >= n2) break;
            if (!bl) continue;
            System.out.println("key size too small. Should be " + n2 + " but is actually " + bigInteger4.multiply(bigInteger2).bitLength());
        }
        if (bl) {
            System.out.println("needed " + l + " tries to generate p and q.");
        }
        BigInteger bigInteger13 = bigInteger4.multiply(bigInteger2);
        BigInteger bigInteger14 = bigInteger4.subtract(ONE).multiply(bigInteger2.subtract(ONE));
        l = 0L;
        if (bl) {
            System.out.println("generating g");
        }
        while (true) {
            int n5;
            Vector<BigInteger> vector2 = new Vector<BigInteger>();
            for (n5 = 0; n5 != vector.size(); ++n5) {
                BigInteger bigInteger15 = (BigInteger)vector.elementAt(n5);
                BigInteger bigInteger16 = bigInteger14.divide(bigInteger15);
                do {
                    ++l;
                } while ((bigInteger = new BigInteger(n2, n3, secureRandom)).modPow(bigInteger16, bigInteger13).equals(ONE));
                vector2.addElement(bigInteger);
            }
            bigInteger = ONE;
            for (n5 = 0; n5 < vector.size(); ++n5) {
                bigInteger = bigInteger.multiply(((BigInteger)vector2.elementAt(n5)).modPow(bigInteger8.divide((BigInteger)vector.elementAt(n5)), bigInteger13)).mod(bigInteger13);
            }
            n5 = 0;
            for (int i = 0; i < vector.size(); ++i) {
                if (!bigInteger.modPow(bigInteger14.divide((BigInteger)vector.elementAt(i)), bigInteger13).equals(ONE)) continue;
                if (bl) {
                    System.out.println("g has order phi(n)/" + vector.elementAt(i) + "\n g: " + bigInteger);
                }
                n5 = 1;
                break;
            }
            if (n5 != 0) continue;
            if (bigInteger.modPow(bigInteger14.divide(BigInteger.valueOf(4L)), bigInteger13).equals(ONE)) {
                if (!bl) continue;
                System.out.println("g has order phi(n)/4\n g:" + bigInteger);
                continue;
            }
            if (bigInteger.modPow(bigInteger14.divide(bigInteger5), bigInteger13).equals(ONE)) {
                if (!bl) continue;
                System.out.println("g has order phi(n)/p'\n g: " + bigInteger);
                continue;
            }
            if (bigInteger.modPow(bigInteger14.divide(bigInteger3), bigInteger13).equals(ONE)) {
                if (!bl) continue;
                System.out.println("g has order phi(n)/q'\n g: " + bigInteger);
                continue;
            }
            if (bigInteger.modPow(bigInteger14.divide(bigInteger9), bigInteger13).equals(ONE)) {
                if (!bl) continue;
                System.out.println("g has order phi(n)/a\n g: " + bigInteger);
                continue;
            }
            if (!bigInteger.modPow(bigInteger14.divide(bigInteger10), bigInteger13).equals(ONE)) break;
            if (!bl) continue;
            System.out.println("g has order phi(n)/b\n g: " + bigInteger);
        }
        if (bl) {
            System.out.println("needed " + l + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + vector);
            System.out.println("sigma:...... " + bigInteger8 + " (" + bigInteger8.bitLength() + " bits)");
            System.out.println("a:.......... " + bigInteger9);
            System.out.println("b:.......... " + bigInteger10);
            System.out.println("p':......... " + bigInteger5);
            System.out.println("q':......... " + bigInteger3);
            System.out.println("p:.......... " + bigInteger4);
            System.out.println("q:.......... " + bigInteger2);
            System.out.println("n:.......... " + bigInteger13);
            System.out.println("phi(n):..... " + bigInteger14);
            System.out.println("g:.......... " + bigInteger);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair(new NaccacheSternKeyParameters(false, bigInteger, bigInteger13, bigInteger8.bitLength()), new NaccacheSternPrivateKeyParameters(bigInteger, bigInteger13, bigInteger8.bitLength(), vector, bigInteger14));
    }

    private static BigInteger generatePrime(int n, int n2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(n, n2, secureRandom);
        while (bigInteger.bitLength() != n) {
            bigInteger = new BigInteger(n, n2, secureRandom);
        }
        return bigInteger;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); ++i) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        vector3.removeElementAt(0);
        while (vector3.size() != 0) {
            vector2.insertElementAt(vector3.elementAt(0), NaccacheSternKeyPairGenerator.getInt(secureRandom, vector2.size() + 1));
            vector3.removeElementAt(0);
        }
        return vector2;
    }

    private static int getInt(SecureRandom secureRandom, int n) {
        int n2;
        int n3;
        if ((n & -n) == n) {
            return (int)((long)n * (long)(secureRandom.nextInt() & Integer.MAX_VALUE) >> 31);
        }
        while ((n3 = secureRandom.nextInt() & Integer.MAX_VALUE) - (n2 = n3 % n) + (n - 1) < 0) {
        }
        return n2;
    }

    private static Vector findFirstPrimes(int n) {
        Vector<BigInteger> vector = new Vector<BigInteger>(n);
        for (int i = 0; i != n; ++i) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i]));
        }
        return vector;
    }
}

