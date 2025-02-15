/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsCompression;

public interface TlsPeer {
    public boolean shouldUseGMTUnixTime();

    public void notifySecureRenegotiation(boolean var1) throws IOException;

    public TlsCompression getCompression() throws IOException;

    public TlsCipher getCipher() throws IOException;

    public void notifyAlertRaised(short var1, short var2, String var3, Throwable var4);

    public void notifyAlertReceived(short var1, short var2);

    public void notifyHandshakeComplete() throws IOException;
}

