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
import org.bouncycastle.math.ec.ECPoint;

public class ECDHCBasicAgreement
implements BasicAgreement {
    ECPrivateKeyParameters key;

    public void init(CipherParameters cipherParameters) {
        this.key = (ECPrivateKeyParameters)cipherParameters;
    }

    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)cipherParameters;
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        if (!eCDomainParameters.equals(this.key.getParameters())) {
            throw new IllegalStateException("ECDHC public key has wrong domain parameters");
        }
        BigInteger bigInteger = eCDomainParameters.getH().multiply(this.key.getD()).mod(eCDomainParameters.getN());
        ECPoint eCPoint = eCPublicKeyParameters.getQ().multiply(bigInteger).normalize();
        if (eCPoint.isInfinity()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for ECDHC");
        }
        return eCPoint.getAffineXCoord().toBigInteger();
    }
}

