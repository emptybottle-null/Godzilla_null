/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.MQVPrivateParameters;
import org.bouncycastle.crypto.params.MQVPublicParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Properties;

public class ECMQVBasicAgreement
implements BasicAgreement {
    MQVPrivateParameters privParams;

    public void init(CipherParameters cipherParameters) {
        this.privParams = (MQVPrivateParameters)cipherParameters;
    }

    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        if (Properties.isOverrideSet("org.bouncycastle.ec.disable_mqv")) {
            throw new IllegalStateException("ECMQV explicitly disabled");
        }
        MQVPublicParameters mQVPublicParameters = (MQVPublicParameters)cipherParameters;
        ECPrivateKeyParameters eCPrivateKeyParameters = this.privParams.getStaticPrivateKey();
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        if (!eCDomainParameters.equals(mQVPublicParameters.getStaticPublicKey().getParameters())) {
            throw new IllegalStateException("ECMQV public key components have wrong domain parameters");
        }
        ECPoint eCPoint = this.calculateMqvAgreement(eCDomainParameters, eCPrivateKeyParameters, this.privParams.getEphemeralPrivateKey(), this.privParams.getEphemeralPublicKey(), mQVPublicParameters.getStaticPublicKey(), mQVPublicParameters.getEphemeralPublicKey()).normalize();
        if (eCPoint.isInfinity()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for MQV");
        }
        return eCPoint.getAffineXCoord().toBigInteger();
    }

    private ECPoint calculateMqvAgreement(ECDomainParameters eCDomainParameters, ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters3) {
        BigInteger bigInteger = eCDomainParameters.getN();
        int n = (bigInteger.bitLength() + 1) / 2;
        BigInteger bigInteger2 = ECConstants.ONE.shiftLeft(n);
        ECCurve eCCurve = eCDomainParameters.getCurve();
        ECPoint[] eCPointArray = new ECPoint[]{ECAlgorithms.importPoint(eCCurve, eCPublicKeyParameters.getQ()), ECAlgorithms.importPoint(eCCurve, eCPublicKeyParameters2.getQ()), ECAlgorithms.importPoint(eCCurve, eCPublicKeyParameters3.getQ())};
        eCCurve.normalizeAll(eCPointArray);
        ECPoint eCPoint = eCPointArray[0];
        ECPoint eCPoint2 = eCPointArray[1];
        ECPoint eCPoint3 = eCPointArray[2];
        BigInteger bigInteger3 = eCPoint.getAffineXCoord().toBigInteger();
        BigInteger bigInteger4 = bigInteger3.mod(bigInteger2);
        BigInteger bigInteger5 = bigInteger4.setBit(n);
        BigInteger bigInteger6 = eCPrivateKeyParameters.getD().multiply(bigInteger5).add(eCPrivateKeyParameters2.getD()).mod(bigInteger);
        BigInteger bigInteger7 = eCPoint3.getAffineXCoord().toBigInteger();
        BigInteger bigInteger8 = bigInteger7.mod(bigInteger2);
        BigInteger bigInteger9 = bigInteger8.setBit(n);
        BigInteger bigInteger10 = eCDomainParameters.getH().multiply(bigInteger6).mod(bigInteger);
        return ECAlgorithms.sumOfTwoMultiplies(eCPoint2, bigInteger9.multiply(bigInteger10).mod(bigInteger), eCPoint3, bigInteger10);
    }
}

