/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.util.Arrays;

class TlsSessionImpl
implements TlsSession {
    final byte[] sessionID;
    SessionParameters sessionParameters;

    TlsSessionImpl(byte[] byArray, SessionParameters sessionParameters) {
        if (byArray == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        }
        if (byArray.length < 1 || byArray.length > 32) {
            throw new IllegalArgumentException("'sessionID' must have length between 1 and 32 bytes, inclusive");
        }
        this.sessionID = Arrays.clone(byArray);
        this.sessionParameters = sessionParameters;
    }

    public synchronized SessionParameters exportSessionParameters() {
        return this.sessionParameters == null ? null : this.sessionParameters.copy();
    }

    public synchronized byte[] getSessionID() {
        return this.sessionID;
    }

    public synchronized void invalidate() {
        if (this.sessionParameters != null) {
            this.sessionParameters.clear();
            this.sessionParameters = null;
        }
    }

    public synchronized boolean isResumable() {
        return this.sessionParameters != null;
    }
}

