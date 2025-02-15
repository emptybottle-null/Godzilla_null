/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.agreement.srp.SRP6Util;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

public class SRP6VerifierGenerator {
    protected BigInteger N;
    protected BigInteger g;
    protected Digest digest;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.N = bigInteger;
        this.g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.N = sRP6GroupParameters.getN();
        this.g = sRP6GroupParameters.getG();
        this.digest = digest;
    }

    public BigInteger generateVerifier(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        BigInteger bigInteger = SRP6Util.calculateX(this.digest, this.N, byArray, byArray2, byArray3);
        return this.g.modPow(bigInteger, this.N);
    }
}

