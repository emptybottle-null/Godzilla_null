/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import org.bouncycastle.jce.spec.ElGamalKeySpec;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

public class ElGamalPublicKeySpec
extends ElGamalKeySpec {
    private BigInteger y;

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.y = bigInteger;
    }

    public BigInteger getY() {
        return this.y;
    }
}

