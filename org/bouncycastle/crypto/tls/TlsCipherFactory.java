/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;

public interface TlsCipherFactory {
    public TlsCipher createCipher(TlsContext var1, int var2, int var3) throws IOException;
}

