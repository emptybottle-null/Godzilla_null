/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ElGamalKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;

public class ElGamalPublicKeyParameters
extends ElGamalKeyParameters {
    private BigInteger y;

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.y = bigInteger;
    }

    public BigInteger getY() {
        return this.y;
    }

    public int hashCode() {
        return this.y.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof ElGamalPublicKeyParameters)) {
            return false;
        }
        ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters)object;
        return elGamalPublicKeyParameters.getY().equals(this.y) && super.equals(object);
    }
}

