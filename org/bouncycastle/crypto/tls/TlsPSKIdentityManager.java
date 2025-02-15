/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public interface TlsPSKIdentityManager {
    public byte[] getHint();

    public byte[] getPSK(byte[] var1);
}

