/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.GOST3410KeyParameters;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.bouncycastle.crypto.params.GOST3410PublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class GOST3410Signer
implements DSA {
    GOST3410KeyParameters key;
    SecureRandom random;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (GOST3410PrivateKeyParameters)parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
                this.key = (GOST3410PrivateKeyParameters)cipherParameters;
            }
        } else {
            this.key = (GOST3410PublicKeyParameters)cipherParameters;
        }
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        BigInteger bigInteger;
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray2.length - 1 - i];
        }
        BigInteger bigInteger2 = new BigInteger(1, byArray2);
        GOST3410Parameters gOST3410Parameters = this.key.getParameters();
        while ((bigInteger = new BigInteger(gOST3410Parameters.getQ().bitLength(), this.random)).compareTo(gOST3410Parameters.getQ()) >= 0) {
        }
        BigInteger bigInteger3 = gOST3410Parameters.getA().modPow(bigInteger, gOST3410Parameters.getP()).mod(gOST3410Parameters.getQ());
        BigInteger bigInteger4 = bigInteger.multiply(bigInteger2).add(((GOST3410PrivateKeyParameters)this.key).getX().multiply(bigInteger3)).mod(gOST3410Parameters.getQ());
        BigInteger[] bigIntegerArray = new BigInteger[]{bigInteger3, bigInteger4};
        return bigIntegerArray;
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray2.length - 1 - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, byArray2);
        GOST3410Parameters gOST3410Parameters = this.key.getParameters();
        BigInteger bigInteger4 = BigInteger.valueOf(0L);
        if (bigInteger4.compareTo(bigInteger) >= 0 || gOST3410Parameters.getQ().compareTo(bigInteger) <= 0) {
            return false;
        }
        if (bigInteger4.compareTo(bigInteger2) >= 0 || gOST3410Parameters.getQ().compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigInteger5 = bigInteger3.modPow(gOST3410Parameters.getQ().subtract(new BigInteger("2")), gOST3410Parameters.getQ());
        BigInteger bigInteger6 = bigInteger2.multiply(bigInteger5).mod(gOST3410Parameters.getQ());
        BigInteger bigInteger7 = gOST3410Parameters.getQ().subtract(bigInteger).multiply(bigInteger5).mod(gOST3410Parameters.getQ());
        bigInteger6 = gOST3410Parameters.getA().modPow(bigInteger6, gOST3410Parameters.getP());
        bigInteger7 = ((GOST3410PublicKeyParameters)this.key).getY().modPow(bigInteger7, gOST3410Parameters.getP());
        BigInteger bigInteger8 = bigInteger6.multiply(bigInteger7).mod(gOST3410Parameters.getP()).mod(gOST3410Parameters.getQ());
        return bigInteger8.equals(bigInteger);
    }
}

