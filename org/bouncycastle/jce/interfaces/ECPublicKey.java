/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.security.PublicKey;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.math.ec.ECPoint;

public interface ECPublicKey
extends ECKey,
PublicKey {
    public ECPoint getQ();
}

