/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import java.security.PublicKey;

public interface MQVPublicKey
extends PublicKey {
    public PublicKey getStaticKey();

    public PublicKey getEphemeralKey();
}

