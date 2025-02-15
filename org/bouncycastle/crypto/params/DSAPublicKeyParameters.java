/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.DSAKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;

public class DSAPublicKeyParameters
extends DSAKeyParameters {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private BigInteger y;

    public DSAPublicKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(false, dSAParameters);
        this.y = this.validate(bigInteger, dSAParameters);
    }

    private BigInteger validate(BigInteger bigInteger, DSAParameters dSAParameters) {
        if (dSAParameters != null) {
            if (TWO.compareTo(bigInteger) <= 0 && dSAParameters.getP().subtract(TWO).compareTo(bigInteger) >= 0 && ONE.equals(bigInteger.modPow(dSAParameters.getQ(), dSAParameters.getP()))) {
                return bigInteger;
            }
            throw new IllegalArgumentException("y value does not appear to be in correct group");
        }
        return bigInteger;
    }

    public BigInteger getY() {
        return this.y;
    }
}

