/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.io.IOException;
import java.math.BigInteger;

public interface DSAEncoder {
    public byte[] encode(BigInteger var1, BigInteger var2) throws IOException;

    public BigInteger[] decode(byte[] var1) throws IOException;
}

