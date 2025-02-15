/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

public interface GCMExponentiator {
    public void init(byte[] var1);

    public void exponentiateX(long var1, byte[] var3);
}

