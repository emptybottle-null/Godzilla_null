/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.DSAKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.RandomDSAKCalculator;

public class DSASigner
implements DSA {
    private final DSAKCalculator kCalculator;
    private DSAKeyParameters key;
    private SecureRandom random;

    public DSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        SecureRandom secureRandom = null;
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.key = (DSAPrivateKeyParameters)parametersWithRandom.getParameters();
                secureRandom = parametersWithRandom.getRandom();
            } else {
                this.key = (DSAPrivateKeyParameters)cipherParameters;
            }
        } else {
            this.key = (DSAPublicKeyParameters)cipherParameters;
        }
        this.random = this.initSecureRandom(bl && !this.kCalculator.isDeterministic(), secureRandom);
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        DSAParameters dSAParameters = this.key.getParameters();
        BigInteger bigInteger = dSAParameters.getQ();
        BigInteger bigInteger2 = this.calculateE(bigInteger, byArray);
        BigInteger bigInteger3 = ((DSAPrivateKeyParameters)this.key).getX();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(bigInteger, bigInteger3, byArray);
        } else {
            this.kCalculator.init(bigInteger, this.random);
        }
        BigInteger bigInteger4 = this.kCalculator.nextK();
        BigInteger bigInteger5 = dSAParameters.getG().modPow(bigInteger4.add(this.getRandomizer(bigInteger, this.random)), dSAParameters.getP()).mod(bigInteger);
        bigInteger4 = bigInteger4.modInverse(bigInteger).multiply(bigInteger2.add(bigInteger3.multiply(bigInteger5)));
        BigInteger bigInteger6 = bigInteger4.mod(bigInteger);
        return new BigInteger[]{bigInteger5, bigInteger6};
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters dSAParameters = this.key.getParameters();
        BigInteger bigInteger3 = dSAParameters.getQ();
        BigInteger bigInteger4 = this.calculateE(bigInteger3, byArray);
        BigInteger bigInteger5 = BigInteger.valueOf(0L);
        if (bigInteger5.compareTo(bigInteger) >= 0 || bigInteger3.compareTo(bigInteger) <= 0) {
            return false;
        }
        if (bigInteger5.compareTo(bigInteger2) >= 0 || bigInteger3.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigInteger6 = bigInteger2.modInverse(bigInteger3);
        BigInteger bigInteger7 = bigInteger4.multiply(bigInteger6).mod(bigInteger3);
        BigInteger bigInteger8 = bigInteger.multiply(bigInteger6).mod(bigInteger3);
        BigInteger bigInteger9 = dSAParameters.getP();
        bigInteger7 = dSAParameters.getG().modPow(bigInteger7, bigInteger9);
        bigInteger8 = ((DSAPublicKeyParameters)this.key).getY().modPow(bigInteger8, bigInteger9);
        BigInteger bigInteger10 = bigInteger7.multiply(bigInteger8).mod(bigInteger9).mod(bigInteger3);
        return bigInteger10.equals(bigInteger);
    }

    private BigInteger calculateE(BigInteger bigInteger, byte[] byArray) {
        if (bigInteger.bitLength() >= byArray.length * 8) {
            return new BigInteger(1, byArray);
        }
        byte[] byArray2 = new byte[bigInteger.bitLength() / 8];
        System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
        return new BigInteger(1, byArray2);
    }

    protected SecureRandom initSecureRandom(boolean bl, SecureRandom secureRandom) {
        return !bl ? null : (secureRandom != null ? secureRandom : new SecureRandom());
    }

    private BigInteger getRandomizer(BigInteger bigInteger, SecureRandom secureRandom) {
        int n = 7;
        return new BigInteger(n, secureRandom != null ? secureRandom : new SecureRandom()).add(BigInteger.valueOf(128L)).multiply(bigInteger);
    }
}

