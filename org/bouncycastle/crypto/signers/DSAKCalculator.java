/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;

public interface DSAKCalculator {
    public boolean isDeterministic();

    public void init(BigInteger var1, SecureRandom var2);

    public void init(BigInteger var1, BigInteger var2, byte[] var3);

    public BigInteger nextK();
}

