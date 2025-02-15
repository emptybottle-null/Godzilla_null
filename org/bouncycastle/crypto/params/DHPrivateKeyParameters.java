/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.DHKeyParameters;
import org.bouncycastle.crypto.params.DHParameters;

public class DHPrivateKeyParameters
extends DHKeyParameters {
    private BigInteger x;

    public DHPrivateKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(true, dHParameters);
        this.x = bigInteger;
    }

    public BigInteger getX() {
        return this.x;
    }

    public int hashCode() {
        return this.x.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof DHPrivateKeyParameters)) {
            return false;
        }
        DHPrivateKeyParameters dHPrivateKeyParameters = (DHPrivateKeyParameters)object;
        return dHPrivateKeyParameters.getX().equals(this.x) && super.equals(object);
    }
}

