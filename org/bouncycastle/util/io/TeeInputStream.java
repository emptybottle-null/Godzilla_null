/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TeeInputStream
extends InputStream {
    private final InputStream input;
    private final OutputStream output;

    public TeeInputStream(InputStream inputStream, OutputStream outputStream) {
        this.input = inputStream;
        this.output = outputStream;
    }

    public int read(byte[] byArray) throws IOException {
        return this.read(byArray, 0, byArray.length);
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        int n3 = this.input.read(byArray, n, n2);
        if (n3 > 0) {
            this.output.write(byArray, n, n3);
        }
        return n3;
    }

    public int read() throws IOException {
        int n = this.input.read();
        if (n >= 0) {
            this.output.write(n);
        }
        return n;
    }

    public void close() throws IOException {
        this.input.close();
        this.output.close();
    }

    public OutputStream getOutputStream() {
        return this.output;
    }
}

