/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class NaccacheSternKeyParameters
extends AsymmetricKeyParameter {
    private BigInteger g;
    private BigInteger n;
    int lowerSigmaBound;

    public NaccacheSternKeyParameters(boolean bl, BigInteger bigInteger, BigInteger bigInteger2, int n) {
        super(bl);
        this.g = bigInteger;
        this.n = bigInteger2;
        this.lowerSigmaBound = n;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.n;
    }
}

