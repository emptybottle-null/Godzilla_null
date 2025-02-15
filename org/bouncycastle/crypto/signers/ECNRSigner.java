/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECPoint;

public class ECNRSigner
implements DSA {
    private boolean forSigning;
    private ECKeyParameters key;
    private SecureRandom random;

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forSigning = bl;
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
        Object object;
        AsymmetricCipherKeyPair asymmetricCipherKeyPair;
        Object object2;
        BigInteger[] bigIntegerArray;
        if (!this.forSigning) {
            throw new IllegalStateException("not initialised for signing");
        }
        BigInteger bigInteger = ((ECPrivateKeyParameters)this.key).getParameters().getN();
        int n = bigInteger.bitLength();
        BigInteger bigInteger2 = new BigInteger(1, byArray);
        int n2 = bigInteger2.bitLength();
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)this.key;
        if (n2 > n) {
            throw new DataLengthException("input too large for ECNR key.");
        }
        BigInteger bigInteger3 = null;
        BigInteger bigInteger4 = null;
        do {
            object = new ECKeyPairGenerator();
            ((ECKeyPairGenerator)object).init(new ECKeyGenerationParameters(eCPrivateKeyParameters.getParameters(), this.random));
        } while ((bigInteger3 = (bigIntegerArray = ((ECPublicKeyParameters)(object2 = (ECPublicKeyParameters)(asymmetricCipherKeyPair = ((ECKeyPairGenerator)object).generateKeyPair()).getPublic())).getQ().getAffineXCoord().toBigInteger()).add(bigInteger2).mod(bigInteger)).equals(ECConstants.ZERO));
        object = eCPrivateKeyParameters.getD();
        object2 = ((ECPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate()).getD();
        bigInteger4 = ((BigInteger)object2).subtract(bigInteger3.multiply((BigInteger)object)).mod(bigInteger);
        bigIntegerArray = new BigInteger[]{bigInteger3, bigInteger4};
        return bigIntegerArray;
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint eCPoint;
        if (this.forSigning) {
            throw new IllegalStateException("not initialised for verifying");
        }
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)this.key;
        BigInteger bigInteger3 = eCPublicKeyParameters.getParameters().getN();
        int n = bigInteger3.bitLength();
        BigInteger bigInteger4 = new BigInteger(1, byArray);
        int n2 = bigInteger4.bitLength();
        if (n2 > n) {
            throw new DataLengthException("input too large for ECNR key.");
        }
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(bigInteger3) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(ECConstants.ZERO) < 0 || bigInteger2.compareTo(bigInteger3) >= 0) {
            return false;
        }
        ECPoint eCPoint2 = eCPublicKeyParameters.getParameters().getG();
        ECPoint eCPoint3 = ECAlgorithms.sumOfTwoMultiplies(eCPoint2, bigInteger2, eCPoint = eCPublicKeyParameters.getQ(), bigInteger).normalize();
        if (eCPoint3.isInfinity()) {
            return false;
        }
        BigInteger bigInteger5 = eCPoint3.getAffineXCoord().toBigInteger();
        BigInteger bigInteger6 = bigInteger.subtract(bigInteger5).mod(bigInteger3);
        return bigInteger6.equals(bigInteger4);
    }
}

