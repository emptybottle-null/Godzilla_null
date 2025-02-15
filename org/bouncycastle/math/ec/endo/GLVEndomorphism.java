/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
import org.bouncycastle.math.ec.endo.ECEndomorphism;

public interface GLVEndomorphism
extends ECEndomorphism {
    public BigInteger[] decomposeScalar(BigInteger var1);
}

