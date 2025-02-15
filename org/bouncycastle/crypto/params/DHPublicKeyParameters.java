/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.DHKeyParameters;
import org.bouncycastle.crypto.params.DHParameters;

public class DHPublicKeyParameters
extends DHKeyParameters {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private BigInteger y;

    public DHPublicKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(false, dHParameters);
        this.y = this.validate(bigInteger, dHParameters);
    }

    private BigInteger validate(BigInteger bigInteger, DHParameters dHParameters) {
        if (bigInteger == null) {
            throw new NullPointerException("y value cannot be null");
        }
        if (bigInteger.compareTo(TWO) < 0 || bigInteger.compareTo(dHParameters.getP().subtract(TWO)) > 0) {
            throw new IllegalArgumentException("invalid DH public key");
        }
        if (dHParameters.getQ() != null) {
            if (ONE.equals(bigInteger.modPow(dHParameters.getQ(), dHParameters.getP()))) {
                return bigInteger;
            }
            throw new IllegalArgumentException("Y value does not appear to be in correct group");
        }
        return bigInteger;
    }

    public BigInteger getY() {
        return this.y;
    }

    public int hashCode() {
        return this.y.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof DHPublicKeyParameters)) {
            return false;
        }
        DHPublicKeyParameters dHPublicKeyParameters = (DHPublicKeyParameters)object;
        return dHPublicKeyParameters.getY().equals(this.y) && super.equals(object);
    }
}

