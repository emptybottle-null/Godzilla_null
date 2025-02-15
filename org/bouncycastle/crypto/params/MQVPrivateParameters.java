/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

public class MQVPrivateParameters
implements CipherParameters {
    private ECPrivateKeyParameters staticPrivateKey;
    private ECPrivateKeyParameters ephemeralPrivateKey;
    private ECPublicKeyParameters ephemeralPublicKey;

    public MQVPrivateParameters(ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2) {
        this(eCPrivateKeyParameters, eCPrivateKeyParameters2, null);
    }

    public MQVPrivateParameters(ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters) {
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
        if (eCPublicKeyParameters == null) {
            eCPublicKeyParameters = new ECPublicKeyParameters(eCDomainParameters.getG().multiply(eCPrivateKeyParameters2.getD()), eCDomainParameters);
        } else if (!eCDomainParameters.equals(eCPublicKeyParameters.getParameters())) {
            throw new IllegalArgumentException("Ephemeral public key has different domain parameters");
        }
        this.staticPrivateKey = eCPrivateKeyParameters;
        this.ephemeralPrivateKey = eCPrivateKeyParameters2;
        this.ephemeralPublicKey = eCPublicKeyParameters;
    }

    public ECPrivateKeyParameters getStaticPrivateKey() {
        return this.staticPrivateKey;
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }
}

