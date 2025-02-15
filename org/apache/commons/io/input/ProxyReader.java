/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import org.apache.commons.io.IOUtils;

public abstract class ProxyReader
extends FilterReader {
    public ProxyReader(Reader proxy) {
        super(proxy);
    }

    @Override
    public int read() throws IOException {
        try {
            this.beforeRead(1);
            int c = this.in.read();
            this.afterRead(c != -1 ? 1 : -1);
            return c;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(char[] chr) throws IOException {
        try {
            this.beforeRead(IOUtils.length(chr));
            int n = this.in.read(chr);
            this.afterRead(n);
            return n;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(char[] chr, int st, int len) throws IOException {
        try {
            this.beforeRead(len);
            int n = this.in.read(chr, st, len);
            this.afterRead(n);
            return n;
        } catch (IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        try {
            this.beforeRead(IOUtils.length(target));
            int n = this.in.read(target);
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
    public boolean ready() throws IOException {
        try {
            return this.in.ready();
        } catch (IOException e) {
            this.handleIOException(e);
            return false;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.in.close();
        } catch (IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public synchronized void mark(int idx) throws IOException {
        try {
            this.in.mark(idx);
        } catch (IOException e) {
            this.handleIOException(e);
        }
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

