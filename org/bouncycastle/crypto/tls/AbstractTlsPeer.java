/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsPeer;

public abstract class AbstractTlsPeer
implements TlsPeer {
    public boolean shouldUseGMTUnixTime() {
        return false;
    }

    public void notifySecureRenegotiation(boolean bl) throws IOException {
        if (!bl) {
            throw new TlsFatalAlert(40);
        }
    }

    public void notifyAlertRaised(short s, short s2, String string, Throwable throwable) {
    }

    public void notifyAlertReceived(short s, short s2) {
    }

    public void notifyHandshakeComplete() throws IOException {
    }
}

