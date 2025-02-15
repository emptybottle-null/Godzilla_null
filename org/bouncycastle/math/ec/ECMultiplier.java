/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECPoint;

public interface ECMultiplier {
    public ECPoint multiply(ECPoint var1, BigInteger var2);
}

