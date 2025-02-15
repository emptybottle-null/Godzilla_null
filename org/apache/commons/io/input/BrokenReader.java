/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class BrokenReader
extends Reader {
    private final IOException exception;

    public BrokenReader(IOException exception) {
        this.exception = exception;
    }

    public BrokenReader() {
        this(new IOException("Broken reader"));
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw this.exception;
    }

    @Override
    public long skip(long n) throws IOException {
        throw this.exception;
    }

    @Override
    public boolean ready() throws IOException {
        throw this.exception;
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        throw this.exception;
    }

    @Override
    public synchronized void reset() throws IOException {
        throw this.exception;
    }

    @Override
    public void close() throws IOException {
        throw this.exception;
    }
}

