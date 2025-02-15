/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;
import org.bouncycastle.jce.interfaces.GOST3410Key;

public interface GOST3410PublicKey
extends GOST3410Key,
PublicKey {
    public BigInteger getY();
}

