/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;

public class ECVKOAgreement {
    private final Digest digest;
    private ECPrivateKeyParameters key;
    private BigInteger ukm;

    public ECVKOAgreement(Digest digest) {
        this.digest = digest;
    }

    public void init(CipherParameters cipherParameters) {
        ParametersWithUKM parametersWithUKM = (ParametersWithUKM)cipherParameters;
        this.key = (ECPrivateKeyParameters)parametersWithUKM.getParameters();
        this.ukm = ECVKOAgreement.toInteger(parametersWithUKM.getUKM());
    }

    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)cipherParameters;
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        if (!eCDomainParameters.equals(this.key.getParameters())) {
            throw new IllegalStateException("ECVKO public key has wrong domain parameters");
        }
        BigInteger bigInteger = eCDomainParameters.getH().multiply(this.ukm).multiply(this.key.getD()).mod(eCDomainParameters.getN());
        ECPoint eCPoint = eCPublicKeyParameters.getQ().multiply(bigInteger).normalize();
        if (eCPoint.isInfinity()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for ECVKO");
        }
        return this.fromPoint(eCPoint.normalize());
    }

    private static BigInteger toInteger(byte[] byArray) {
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray.length - i - 1];
        }
        return new BigInteger(1, byArray2);
    }

    private byte[] fromPoint(ECPoint eCPoint) {
        int n;
        BigInteger bigInteger = eCPoint.getAffineXCoord().toBigInteger();
        BigInteger bigInteger2 = eCPoint.getAffineYCoord().toBigInteger();
        int n2 = bigInteger.toByteArray().length > 33 ? 64 : 32;
        byte[] byArray = new byte[2 * n2];
        byte[] byArray2 = BigIntegers.asUnsignedByteArray(n2, bigInteger);
        byte[] byArray3 = BigIntegers.asUnsignedByteArray(n2, bigInteger2);
        for (n = 0; n != n2; ++n) {
            byArray[n] = byArray2[n2 - n - 1];
        }
        for (n = 0; n != n2; ++n) {
            byArray[n2 + n] = byArray3[n2 - n - 1];
        }
        this.digest.update(byArray, 0, byArray.length);
        byte[] byArray4 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray4, 0);
        return byArray4;
    }
}

