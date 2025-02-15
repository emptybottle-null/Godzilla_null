/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;
import org.bouncycastle.pqc.jcajce.interfaces.NHKey;

public interface NHPrivateKey
extends NHKey,
PrivateKey {
    public short[] getSecretData();
}

