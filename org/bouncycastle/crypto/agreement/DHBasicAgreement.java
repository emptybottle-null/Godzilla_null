/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class DHBasicAgreement
implements BasicAgreement {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DHPrivateKeyParameters key;
    private DHParameters dhParams;

    public void init(CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            asymmetricKeyParameter = (AsymmetricKeyParameter)parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter)cipherParameters;
        }
        if (!(asymmetricKeyParameter instanceof DHPrivateKeyParameters)) {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        }
        this.key = (DHPrivateKeyParameters)asymmetricKeyParameter;
        this.dhParams = this.key.getParameters();
    }

    public int getFieldSize() {
        return (this.key.getParameters().getP().bitLength() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        DHPublicKeyParameters dHPublicKeyParameters = (DHPublicKeyParameters)cipherParameters;
        if (!dHPublicKeyParameters.getParameters().equals(this.dhParams)) {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        }
        BigInteger bigInteger = this.dhParams.getP();
        BigInteger bigInteger2 = dHPublicKeyParameters.getY();
        if (bigInteger2 == null || bigInteger2.compareTo(ONE) <= 0 || bigInteger2.compareTo(bigInteger.subtract(ONE)) >= 0) {
            throw new IllegalArgumentException("Diffie-Hellman public key is weak");
        }
        BigInteger bigInteger3 = bigInteger2.modPow(this.key.getX(), bigInteger);
        if (bigInteger3.equals(ONE)) {
            throw new IllegalStateException("Shared key can't be 1");
        }
        return bigInteger3;
    }
}

