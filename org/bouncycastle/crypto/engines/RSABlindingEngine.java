/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RSACoreEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

public class RSABlindingEngine
implements AsymmetricBlockCipher {
    private RSACoreEngine core = new RSACoreEngine();
    private RSAKeyParameters key;
    private BigInteger blindingFactor;
    private boolean forEncryption;

    public void init(boolean bl, CipherParameters cipherParameters) {
        RSABlindingParameters rSABlindingParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            rSABlindingParameters = (RSABlindingParameters)parametersWithRandom.getParameters();
        } else {
            rSABlindingParameters = (RSABlindingParameters)cipherParameters;
        }
        this.core.init(bl, rSABlindingParameters.getPublicKey());
        this.forEncryption = bl;
        this.key = rSABlindingParameters.getPublicKey();
        this.blindingFactor = rSABlindingParameters.getBlindingFactor();
    }

    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) {
        BigInteger bigInteger = this.core.convertInput(byArray, n, n2);
        bigInteger = this.forEncryption ? this.blindMessage(bigInteger) : this.unblindMessage(bigInteger);
        return this.core.convertOutput(bigInteger);
    }

    private BigInteger blindMessage(BigInteger bigInteger) {
        BigInteger bigInteger2 = this.blindingFactor;
        bigInteger2 = bigInteger.multiply(bigInteger2.modPow(this.key.getExponent(), this.key.getModulus()));
        bigInteger2 = bigInteger2.mod(this.key.getModulus());
        return bigInteger2;
    }

    private BigInteger unblindMessage(BigInteger bigInteger) {
        BigInteger bigInteger2 = this.key.getModulus();
        BigInteger bigInteger3 = bigInteger;
        BigInteger bigInteger4 = this.blindingFactor.modInverse(bigInteger2);
        bigInteger3 = bigInteger3.multiply(bigInteger4);
        bigInteger3 = bigInteger3.mod(bigInteger2);
        return bigInteger3;
    }
}

