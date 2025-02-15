/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PublicKey;
import org.bouncycastle.pqc.jcajce.interfaces.NHKey;

public interface NHPublicKey
extends NHKey,
PublicKey {
    public byte[] getPublicData();
}

