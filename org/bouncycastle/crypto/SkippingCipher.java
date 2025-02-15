/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

public interface SkippingCipher {
    public long skip(long var1);

    public long seekTo(long var1);

    public long getPosition();
}

