/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public abstract class ProxyInputStream
extends FilterInputStream {
    public ProxyInputStream(InputStream proxy) {
        super(proxy);
    }

    @Override
    public int read() throws IOException {
        try {
            this.beforeRead(1);
            int b = this.in.read();
            this.afterRead(b != -1 ? 1 : -1);
            return b;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(byte[] bts) throws IOException {
        try {
            this.beforeRead(IOUtils.length(bts));
            int n = this.in.read(bts);
            this.afterRead(n);
            return n;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(byte[] bts, int off, int len) throws IOException {
        try {
            this.beforeRead(len);
            int n = this.in.read(bts, off, len);
            this.afterRead(n);
            return n;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public long skip(long ln) throws IOException {
        try {
            return this.in.skip(ln);
        } catch (IOException e) {
            this.handleIOException(e);
            return 0L;
        }
    }

    @Override
    public int available() throws IOException {
        try {
            return super.available();
        } catch (IOException e) {
            this.handleIOException(e);
            return 0;
        }
    }

    @Override
    public void close() throws IOException {
        IOUtils.close((Closeable)this.in, this::handleIOException);
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

    protected void beforeRead(int n) throws IOException {
    }

    protected void afterRead(int n) throws IOException {
    }

    protected void handleIOException(IOException e) throws IOException {
        throw e;
    }
}

