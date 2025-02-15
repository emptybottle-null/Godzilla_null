/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

public class ElGamalParameters
implements CipherParameters {
    private BigInteger g;
    private BigInteger p;
    private int l;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int n) {
        this.g = bigInteger2;
        this.p = bigInteger;
        this.l = n;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getL() {
        return this.l;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters)object;
        return elGamalParameters.getP().equals(this.p) && elGamalParameters.getG().equals(this.g) && elGamalParameters.getL() == this.l;
    }

    public int hashCode() {
        return (this.getP().hashCode() ^ this.getG().hashCode()) + this.l;
    }
}

