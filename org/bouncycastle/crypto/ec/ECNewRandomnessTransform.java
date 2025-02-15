/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ec.ECPair;
import org.bouncycastle.crypto.ec.ECPairFactorTransform;
import org.bouncycastle.crypto.ec.ECUtil;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

public class ECNewRandomnessTransform
implements ECPairFactorTransform {
    private ECPublicKeyParameters key;
    private SecureRandom random;
    private BigInteger lastK;

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            if (!(parametersWithRandom.getParameters() instanceof ECPublicKeyParameters)) {
                throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
            }
            this.key = (ECPublicKeyParameters)parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            if (!(cipherParameters instanceof ECPublicKeyParameters)) {
                throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
            }
            this.key = (ECPublicKeyParameters)cipherParameters;
            this.random = new SecureRandom();
        }
    }

    public ECPair transform(ECPair eCPair) {
        if (this.key == null) {
            throw new IllegalStateException("ECNewRandomnessTransform not initialised");
        }
        ECDomainParameters eCDomainParameters = this.key.getParameters();
        BigInteger bigInteger = eCDomainParameters.getN();
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        BigInteger bigInteger2 = ECUtil.generateK(bigInteger, this.random);
        ECPoint[] eCPointArray = new ECPoint[]{eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger2).add(eCPair.getX()), this.key.getQ().multiply(bigInteger2).add(eCPair.getY())};
        eCDomainParameters.getCurve().normalizeAll(eCPointArray);
        this.lastK = bigInteger2;
        return new ECPair(eCPointArray[0], eCPointArray[1]);
    }

    public BigInteger getTransformValue() {
        return this.lastK;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}

