/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.RandomDSAKCalculator;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.BigIntegers;

public class SM2Signer
implements DSA,
ECConstants {
    private final DSAKCalculator kCalculator = new RandomDSAKCalculator();
    private byte[] userID;
    private int curveLength;
    private ECDomainParameters ecParams;
    private ECPoint pubPoint;
    private ECKeyParameters ecKey;
    private SecureRandom random;

    public void init(boolean bl, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithID) {
            cipherParameters2 = ((ParametersWithID)cipherParameters).getParameters();
            this.userID = ((ParametersWithID)cipherParameters).getID();
        } else {
            cipherParameters2 = cipherParameters;
            this.userID = new byte[0];
        }
        if (bl) {
            if (cipherParameters2 instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters2;
                this.ecKey = (ECKeyParameters)parametersWithRandom.getParameters();
                this.ecParams = this.ecKey.getParameters();
                this.kCalculator.init(this.ecParams.getN(), parametersWithRandom.getRandom());
            } else {
                this.ecKey = (ECKeyParameters)cipherParameters2;
                this.ecParams = this.ecKey.getParameters();
                this.kCalculator.init(this.ecParams.getN(), new SecureRandom());
            }
            this.pubPoint = this.ecParams.getG().multiply(((ECPrivateKeyParameters)this.ecKey).getD()).normalize();
        } else {
            this.ecKey = (ECKeyParameters)cipherParameters2;
            this.ecParams = this.ecKey.getParameters();
            this.pubPoint = ((ECPublicKeyParameters)this.ecKey).getQ();
        }
        this.curveLength = (this.ecParams.getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger[] generateSignature(byte[] byArray) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        SM3Digest sM3Digest = new SM3Digest();
        byte[] byArray2 = this.getZ(sM3Digest);
        sM3Digest.update(byArray2, 0, byArray2.length);
        sM3Digest.update(byArray, 0, byArray.length);
        byte[] byArray3 = new byte[sM3Digest.getDigestSize()];
        sM3Digest.doFinal(byArray3, 0);
        BigInteger bigInteger3 = this.ecParams.getN();
        BigInteger bigInteger4 = this.calculateE(byArray3);
        BigInteger bigInteger5 = ((ECPrivateKeyParameters)this.ecKey).getD();
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        while (true) {
            BigInteger bigInteger6 = this.kCalculator.nextK();
            Object object = eCMultiplier.multiply(this.ecParams.getG(), bigInteger6).normalize();
            bigInteger2 = bigInteger4.add(((ECPoint)object).getAffineXCoord().toBigInteger()).mod(bigInteger3);
            if (bigInteger2.equals(ZERO) || bigInteger2.add(bigInteger6).equals(bigInteger3)) continue;
            object = bigInteger5.add(ONE).modInverse(bigInteger3);
            bigInteger = bigInteger6.subtract(bigInteger2.multiply(bigInteger5)).mod(bigInteger3);
            if (!(bigInteger = ((BigInteger)object).multiply(bigInteger).mod(bigInteger3)).equals(ZERO)) break;
        }
        return new BigInteger[]{bigInteger2, bigInteger};
    }

    public boolean verifySignature(byte[] byArray, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = this.ecParams.getN();
        if (bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(bigInteger3) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(ONE) < 0 || bigInteger2.compareTo(bigInteger3) >= 0) {
            return false;
        }
        ECPoint eCPoint = ((ECPublicKeyParameters)this.ecKey).getQ();
        SM3Digest sM3Digest = new SM3Digest();
        byte[] byArray2 = this.getZ(sM3Digest);
        sM3Digest.update(byArray2, 0, byArray2.length);
        sM3Digest.update(byArray, 0, byArray.length);
        byte[] byArray3 = new byte[sM3Digest.getDigestSize()];
        sM3Digest.doFinal(byArray3, 0);
        BigInteger bigInteger4 = this.calculateE(byArray3);
        BigInteger bigInteger5 = bigInteger.add(bigInteger2).mod(bigInteger3);
        if (bigInteger5.equals(ZERO)) {
            return false;
        }
        ECPoint eCPoint2 = this.ecParams.getG().multiply(bigInteger2);
        eCPoint2 = eCPoint2.add(eCPoint.multiply(bigInteger5)).normalize();
        return bigInteger.equals(bigInteger4.add(eCPoint2.getAffineXCoord().toBigInteger()).mod(bigInteger3));
    }

    private byte[] getZ(Digest digest) {
        this.addUserID(digest, this.userID);
        this.addFieldElement(digest, this.ecParams.getCurve().getA());
        this.addFieldElement(digest, this.ecParams.getCurve().getB());
        this.addFieldElement(digest, this.ecParams.getG().getAffineXCoord());
        this.addFieldElement(digest, this.ecParams.getG().getAffineYCoord());
        this.addFieldElement(digest, this.pubPoint.getAffineXCoord());
        this.addFieldElement(digest, this.pubPoint.getAffineYCoord());
        byte[] byArray = new byte[digest.getDigestSize()];
        digest.doFinal(byArray, 0);
        return byArray;
    }

    private void addUserID(Digest digest, byte[] byArray) {
        int n = byArray.length * 8;
        digest.update((byte)(n >> 8 & 0xFF));
        digest.update((byte)(n & 0xFF));
        digest.update(byArray, 0, byArray.length);
    }

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(this.curveLength, eCFieldElement.toBigInteger());
        digest.update(byArray, 0, byArray.length);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    protected BigInteger calculateE(byte[] byArray) {
        return new BigInteger(1, byArray);
    }
}

