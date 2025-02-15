/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ElGamalKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;

public class ElGamalPrivateKeyParameters
extends ElGamalKeyParameters {
    private BigInteger x;

    public ElGamalPrivateKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(true, elGamalParameters);
        this.x = bigInteger;
    }

    public BigInteger getX() {
        return this.x;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ElGamalPrivateKeyParameters)) {
            return false;
        }
        ElGamalPrivateKeyParameters elGamalPrivateKeyParameters = (ElGamalPrivateKeyParameters)object;
        if (!elGamalPrivateKeyParameters.getX().equals(this.x)) {
            return false;
        }
        return super.equals(object);
    }

    public int hashCode() {
        return this.getX().hashCode();
    }
}

