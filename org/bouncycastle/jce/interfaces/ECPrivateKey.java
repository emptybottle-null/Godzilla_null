/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;
import org.bouncycastle.jce.interfaces.ECKey;

public interface ECPrivateKey
extends ECKey,
PrivateKey {
    public BigInteger getD();
}

