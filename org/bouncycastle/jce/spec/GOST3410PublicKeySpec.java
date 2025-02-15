/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PublicKeySpec
implements KeySpec {
    private BigInteger y;
    private BigInteger p;
    private BigInteger q;
    private BigInteger a;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.y = bigInteger;
        this.p = bigInteger2;
        this.q = bigInteger3;
        this.a = bigInteger4;
    }

    public BigInteger getY() {
        return this.y;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getA() {
        return this.a;
    }
}

