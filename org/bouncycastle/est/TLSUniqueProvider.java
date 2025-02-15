/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

public interface TLSUniqueProvider {
    public boolean isTLSUniqueAvailable();

    public byte[] getTLSUnique();
}

