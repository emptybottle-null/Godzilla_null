/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import org.bouncycastle.jce.spec.ElGamalKeySpec;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

public class ElGamalPrivateKeySpec
extends ElGamalKeySpec {
    private BigInteger x;

    public ElGamalPrivateKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.x = bigInteger;
    }

    public BigInteger getX() {
        return this.x;
    }
}

