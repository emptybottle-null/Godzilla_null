/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.GOST3410ValidationParameters;

public class GOST3410Parameters
implements CipherParameters {
    private BigInteger p;
    private BigInteger q;
    private BigInteger a;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.p = bigInteger;
        this.q = bigInteger2;
        this.a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.a = bigInteger3;
        this.p = bigInteger;
        this.q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
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

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return this.p.hashCode() ^ this.q.hashCode() ^ this.a.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters)object;
        return gOST3410Parameters.getP().equals(this.p) && gOST3410Parameters.getQ().equals(this.q) && gOST3410Parameters.getA().equals(this.a);
    }
}

