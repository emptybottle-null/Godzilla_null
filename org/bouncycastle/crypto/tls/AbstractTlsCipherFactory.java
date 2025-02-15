/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;

public class AbstractTlsCipherFactory
implements TlsCipherFactory {
    public TlsCipher createCipher(TlsContext tlsContext, int n, int n2) throws IOException {
        throw new TlsFatalAlert(80);
    }
}

