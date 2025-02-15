/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

public class TlsSRPLoginParameters {
    protected SRP6GroupParameters group;
    protected BigInteger verifier;
    protected byte[] salt;

    public TlsSRPLoginParameters(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, byte[] byArray) {
        this.group = sRP6GroupParameters;
        this.verifier = bigInteger;
        this.salt = byArray;
    }

    public SRP6GroupParameters getGroup() {
        return this.group;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public BigInteger getVerifier() {
        return this.verifier;
    }
}

