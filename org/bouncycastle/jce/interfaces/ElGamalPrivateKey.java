/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;
import org.bouncycastle.jce.interfaces.ElGamalKey;

public interface ElGamalPrivateKey
extends ElGamalKey,
DHPrivateKey {
    public BigInteger getX();
}

