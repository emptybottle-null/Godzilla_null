/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.SessionParameters;

public interface TlsSession {
    public SessionParameters exportSessionParameters();

    public byte[] getSessionID();

    public void invalidate();

    public boolean isResumable();
}

