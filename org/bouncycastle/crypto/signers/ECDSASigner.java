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
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.RandomDSAKCalculator;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

public class ECDSASigner
implements ECConstants,
DSA {
    private final DSAKCalculator kCalculator;
    private ECKeyParameters key;
    private SecureRandom random;

    public ECDSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public ECDSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        SecureRandom secureRandom = null;
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.key = (ECPrivateKeyParameters)parametersWithRandom.getParameters();
                secureRandom = parametersWithRandom.getRandom();
            } else {
                this.key = (ECPrivateKeyParameters)cipherParameters;
            }
        } else {
            this.key = (ECPublicKeyParameters)cipherParameters;
        }
        this.random = this.initSecureRandom(bl && !this.kCalculator.isDeterministic(), secureRandom);
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        ECPoint eCPoint;
        BigInteger bigInteger3;
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger4 = eCDomainParameters.getN();
        BigInteger bigInteger5 = this.calculateE(bigInteger4, byArray);
        BigInteger bigInteger6 = ((ECPrivateKeyParameters)this.key).getD();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(bigInteger4, bigInteger6, byArray);
        } else {
            this.kCalculator.init(bigInteger4, this.random);
        }
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        do {
            bigInteger2 = this.kCalculator.nextK();
        } while ((bigInteger3 = (eCPoint = eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger2).normalize()).getAffineXCoord().toBigInteger().mod(bigInteger4)).equals(ZERO) || (bigInteger = bigInteger2.modInverse(bigInteger4).multiply(bigInteger5.add(bigInteger6.multiply(bigInteger3))).mod(bigInteger4)).equals(ZERO));
        return new BigInteger[]{bigInteger3, bigInteger};
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        ECFieldElement eCFieldElement;
        BigInteger bigInteger3;
        ECPoint eCPoint;
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger4 = eCDomainParameters.getN();
        BigInteger bigInteger5 = this.calculateE(bigInteger4, byArray);
        if (bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(bigInteger4) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(ONE) < 0 || bigInteger2.compareTo(bigInteger4) >= 0) {
            return false;
        }
        BigInteger bigInteger6 = bigInteger2.modInverse(bigInteger4);
        BigInteger bigInteger7 = bigInteger5.multiply(bigInteger6).mod(bigInteger4);
        BigInteger bigInteger8 = bigInteger.multiply(bigInteger6).mod(bigInteger4);
        ECPoint eCPoint2 = eCDomainParameters.getG();
        ECPoint eCPoint3 = ECAlgorithms.sumOfTwoMultiplies(eCPoint2, bigInteger7, eCPoint = ((ECPublicKeyParameters)this.key).getQ(), bigInteger8);
        if (eCPoint3.isInfinity()) {
            return false;
        }
        ECCurve eCCurve = eCPoint3.getCurve();
        if (eCCurve != null && (bigInteger3 = eCCurve.getCofactor()) != null && bigInteger3.compareTo(EIGHT) <= 0 && (eCFieldElement = this.getDenominator(eCCurve.getCoordinateSystem(), eCPoint3)) != null && !eCFieldElement.isZero()) {
            ECFieldElement eCFieldElement2 = eCPoint3.getXCoord();
            while (eCCurve.isValidFieldElement(bigInteger)) {
                ECFieldElement eCFieldElement3 = eCCurve.fromBigInteger(bigInteger).multiply(eCFieldElement);
                if (eCFieldElement3.equals(eCFieldElement2)) {
                    return true;
                }
                bigInteger = bigInteger.add(bigInteger4);
            }
            return false;
        }
        bigInteger3 = eCPoint3.normalize().getAffineXCoord().toBigInteger().mod(bigInteger4);
        return bigInteger3.equals(bigInteger);
    }

    protected BigInteger calculateE(BigInteger bigInteger, byte[] byArray) {
        int n = bigInteger.bitLength();
        int n2 = byArray.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, byArray);
        if (n < n2) {
            bigInteger2 = bigInteger2.shiftRight(n2 - n);
        }
        return bigInteger2;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    protected ECFieldElement getDenominator(int n, ECPoint eCPoint) {
        switch (n) {
            case 1: 
            case 6: 
            case 7: {
                return eCPoint.getZCoord(0);
            }
            case 2: 
            case 3: 
            case 4: {
                return eCPoint.getZCoord(0).square();
            }
        }
        return null;
    }

    protected SecureRandom initSecureRandom(boolean bl, SecureRandom secureRandom) {
        return !bl ? null : (secureRandom != null ? secureRandom : new SecureRandom());
    }
}

