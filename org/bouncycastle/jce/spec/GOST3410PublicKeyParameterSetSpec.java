/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.math.BigInteger;

public class GOST3410PublicKeyParameterSetSpec {
    private BigInteger p;
    private BigInteger q;
    private BigInteger a;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.p = bigInteger;
        this.q = bigInteger2;
        this.a = bigInteger3;
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

    public boolean equals(Object object) {
        if (object instanceof GOST3410PublicKeyParameterSetSpec) {
            GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec)object;
            return this.a.equals(gOST3410PublicKeyParameterSetSpec.a) && this.p.equals(gOST3410PublicKeyParameterSetSpec.p) && this.q.equals(gOST3410PublicKeyParameterSetSpec.q);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.p.hashCode() ^ this.q.hashCode();
    }
}

