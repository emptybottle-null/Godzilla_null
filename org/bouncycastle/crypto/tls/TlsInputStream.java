/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.tls.TlsProtocol;

class TlsInputStream
extends InputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler = null;

    TlsInputStream(TlsProtocol tlsProtocol) {
        this.handler = tlsProtocol;
    }

    public int available() throws IOException {
        return this.handler.applicationDataAvailable();
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        return this.handler.readApplicationData(byArray, n, n2);
    }

    public int read() throws IOException {
        if (this.read(this.buf) < 0) {
            return -1;
        }
        return this.buf[0] & 0xFF;
    }

    public void close() throws IOException {
        this.handler.close();
    }
}

