/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

public class SM2KeyExchangePrivateParameters
implements CipherParameters {
    private final boolean initiator;
    private final ECPrivateKeyParameters staticPrivateKey;
    private final ECPoint staticPublicPoint;
    private final ECPrivateKeyParameters ephemeralPrivateKey;
    private final ECPoint ephemeralPublicPoint;

    public SM2KeyExchangePrivateParameters(boolean bl, ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2) {
        if (eCPrivateKeyParameters == null) {
            throw new NullPointerException("staticPrivateKey cannot be null");
        }
        if (eCPrivateKeyParameters2 == null) {
            throw new NullPointerException("ephemeralPrivateKey cannot be null");
        }
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        if (!eCDomainParameters.equals(eCPrivateKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("Static and ephemeral private keys have different domain parameters");
        }
        this.initiator = bl;
        this.staticPrivateKey = eCPrivateKeyParameters;
        this.staticPublicPoint = eCDomainParameters.getG().multiply(eCPrivateKeyParameters.getD()).normalize();
        this.ephemeralPrivateKey = eCPrivateKeyParameters2;
        this.ephemeralPublicPoint = eCDomainParameters.getG().multiply(eCPrivateKeyParameters2.getD()).normalize();
    }

    public boolean isInitiator() {
        return this.initiator;
    }

    public ECPrivateKeyParameters getStaticPrivateKey() {
        return this.staticPrivateKey;
    }

    public ECPoint getStaticPublicPoint() {
        return this.staticPublicPoint;
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    public ECPoint getEphemeralPublicPoint() {
        return this.ephemeralPublicPoint;
    }
}

