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
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;

public class DSTU4145Signer
implements DSA {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private ECKeyParameters key;
    private SecureRandom random;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                cipherParameters = parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
            }
            this.key = (ECPrivateKeyParameters)cipherParameters;
        } else {
            this.key = (ECPublicKeyParameters)cipherParameters;
        }
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        BigInteger bigInteger;
        ECFieldElement eCFieldElement;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        ECFieldElement eCFieldElement2;
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        ECCurve eCCurve = eCDomainParameters.getCurve();
        ECFieldElement eCFieldElement3 = DSTU4145Signer.hash2FieldElement(eCCurve, byArray);
        if (eCFieldElement3.isZero()) {
            eCFieldElement3 = eCCurve.fromBigInteger(ONE);
        }
        BigInteger bigInteger4 = eCDomainParameters.getN();
        BigInteger bigInteger5 = ((ECPrivateKeyParameters)this.key).getD();
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        do {
            bigInteger3 = DSTU4145Signer.generateRandomInteger(bigInteger4, this.random);
        } while ((eCFieldElement2 = eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger3).normalize().getAffineXCoord()).isZero() || (bigInteger2 = DSTU4145Signer.fieldElement2Integer(bigInteger4, eCFieldElement = eCFieldElement3.multiply(eCFieldElement2))).signum() == 0 || (bigInteger = bigInteger2.multiply(bigInteger5).add(bigInteger3).mod(bigInteger4)).signum() == 0);
        return new BigInteger[]{bigInteger2, bigInteger};
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint eCPoint;
        if (bigInteger.signum() <= 0 || bigInteger2.signum() <= 0) {
            return false;
        }
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger3 = eCDomainParameters.getN();
        if (bigInteger.compareTo(bigInteger3) >= 0 || bigInteger2.compareTo(bigInteger3) >= 0) {
            return false;
        }
        ECCurve eCCurve = eCDomainParameters.getCurve();
        ECFieldElement eCFieldElement = DSTU4145Signer.hash2FieldElement(eCCurve, byArray);
        if (eCFieldElement.isZero()) {
            eCFieldElement = eCCurve.fromBigInteger(ONE);
        }
        if ((eCPoint = ECAlgorithms.sumOfTwoMultiplies(eCDomainParameters.getG(), bigInteger2, ((ECPublicKeyParameters)this.key).getQ(), bigInteger).normalize()).isInfinity()) {
            return false;
        }
        ECFieldElement eCFieldElement2 = eCFieldElement.multiply(eCPoint.getAffineXCoord());
        return DSTU4145Signer.fieldElement2Integer(bigInteger3, eCFieldElement2).compareTo(bigInteger) == 0;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    private static BigInteger generateRandomInteger(BigInteger bigInteger, SecureRandom secureRandom) {
        return new BigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    private static ECFieldElement hash2FieldElement(ECCurve eCCurve, byte[] byArray) {
        byte[] byArray2 = Arrays.reverse(byArray);
        return eCCurve.fromBigInteger(DSTU4145Signer.truncate(new BigInteger(1, byArray2), eCCurve.getFieldSize()));
    }

    private static BigInteger fieldElement2Integer(BigInteger bigInteger, ECFieldElement eCFieldElement) {
        return DSTU4145Signer.truncate(eCFieldElement.toBigInteger(), bigInteger.bitLength() - 1);
    }

    private static BigInteger truncate(BigInteger bigInteger, int n) {
        if (bigInteger.bitLength() > n) {
            bigInteger = bigInteger.mod(ONE.shiftLeft(n));
        }
        return bigInteger;
    }
}

