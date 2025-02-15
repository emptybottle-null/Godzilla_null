/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ec.ECPair;
import org.bouncycastle.crypto.ec.ECPairFactorTransform;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

public class ECFixedTransform
implements ECPairFactorTransform {
    private ECPublicKeyParameters key;
    private BigInteger k;

    public ECFixedTransform(BigInteger bigInteger) {
        this.k = bigInteger;
    }

    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for fixed transform.");
        }
        this.key = (ECPublicKeyParameters)cipherParameters;
    }

    public ECPair transform(ECPair eCPair) {
        if (this.key == null) {
            throw new IllegalStateException("ECFixedTransform not initialised");
        }
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger = eCDomainParameters.getN();
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        BigInteger bigInteger2 = this.k.mod(bigInteger);
        ECPoint[] eCPointArray = new ECPoint[]{eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger2).add(eCPair.getX()), this.key.getQ().multiply(bigInteger2).add(eCPair.getY())};
        eCDomainParameters.getCurve().normalizeAll(eCPointArray);
        return new ECPair(eCPointArray[0], eCPointArray[1]);
    }

    public BigInteger getTransformValue() {
        return this.k;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}

