/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public interface TlsPSKIdentity {
    public void skipIdentityHint();

    public void notifyIdentityHint(byte[] var1);

    public byte[] getPSKIdentity();

    public byte[] getPSK();
}

