/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.TlsProtocol;

class TlsOutputStream
extends OutputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler;

    TlsOutputStream(TlsProtocol tlsProtocol) {
        this.handler = tlsProtocol;
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.handler.writeData(byArray, n, n2);
    }

    public void write(int n) throws IOException {
        this.buf[0] = (byte)n;
        this.write(this.buf, 0, 1);
    }

    public void close() throws IOException {
        this.handler.close();
    }

    public void flush() throws IOException {
        this.handler.flush();
    }
}

