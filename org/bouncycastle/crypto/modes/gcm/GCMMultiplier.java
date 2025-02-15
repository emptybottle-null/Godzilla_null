/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

public interface GCMMultiplier {
    public void init(byte[] var1);

    public void multiplyH(byte[] var1);
}

