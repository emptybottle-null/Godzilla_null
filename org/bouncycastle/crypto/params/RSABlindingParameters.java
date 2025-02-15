/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSABlindingParameters
implements CipherParameters {
    private RSAKeyParameters publicKey;
    private BigInteger blindingFactor;

    public RSABlindingParameters(RSAKeyParameters rSAKeyParameters, BigInteger bigInteger) {
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("RSA parameters should be for a public key");
        }
        this.publicKey = rSAKeyParameters;
        this.blindingFactor = bigInteger;
    }

    public RSAKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public BigInteger getBlindingFactor() {
        return this.blindingFactor;
    }
}

