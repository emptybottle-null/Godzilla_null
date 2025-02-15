/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

public class ECGOST3410Signer
implements DSA {
    ECKeyParameters key;
    SecureRandom random;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (ECPrivateKeyParameters)parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
                this.key = (ECPrivateKeyParameters)cipherParameters;
            }
        } else {
            this.key = (ECPublicKeyParameters)cipherParameters;
        }
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        BigInteger bigInteger;
        ECPoint eCPoint;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray2.length - 1 - i];
        }
        BigInteger bigInteger4 = new BigInteger(1, byArray2);
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger5 = eCDomainParameters.getN();
        BigInteger bigInteger6 = ((ECPrivateKeyParameters)this.key).getD();
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        while ((bigInteger3 = new BigInteger(bigInteger5.bitLength(), this.random)).equals(ECConstants.ZERO) || (bigInteger2 = (eCPoint = eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger3).normalize()).getAffineXCoord().toBigInteger().mod(bigInteger5)).equals(ECConstants.ZERO) || (bigInteger = bigInteger3.multiply(bigInteger4).add(bigInteger6.multiply(bigInteger2)).mod(bigInteger5)).equals(ECConstants.ZERO)) {
        }
        return new BigInteger[]{bigInteger2, bigInteger};
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint eCPoint;
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray2.length - 1 - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, byArray2);
        BigInteger bigInteger4 = this.key.getParameters().getN();
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(bigInteger4) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(ECConstants.ONE) < 0 || bigInteger2.compareTo(bigInteger4) >= 0) {
            return false;
        }
        BigInteger bigInteger5 = bigInteger3.modInverse(bigInteger4);
        BigInteger bigInteger6 = bigInteger2.multiply(bigInteger5).mod(bigInteger4);
        BigInteger bigInteger7 = bigInteger4.subtract(bigInteger).multiply(bigInteger5).mod(bigInteger4);
        ECPoint eCPoint2 = this.key.getParameters().getG();
        ECPoint eCPoint3 = ECAlgorithms.sumOfTwoMultiplies(eCPoint2, bigInteger6, eCPoint = ((ECPublicKeyParameters)this.key).getQ(), bigInteger7).normalize();
        if (eCPoint3.isInfinity()) {
            return false;
        }
        BigInteger bigInteger8 = eCPoint3.getAffineXCoord().toBigInteger().mod(bigInteger4);
        return bigInteger8.equals(bigInteger);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}

