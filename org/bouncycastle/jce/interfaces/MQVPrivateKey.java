/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface MQVPrivateKey
extends PrivateKey {
    public PrivateKey getStaticPrivateKey();

    public PrivateKey getEphemeralPrivateKey();

    public PublicKey getEphemeralPublicKey();
}

