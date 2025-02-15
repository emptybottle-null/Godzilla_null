/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public interface KeyEncoder {
    public byte[] getEncoded(AsymmetricKeyParameter var1);
}

