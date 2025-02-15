/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class BrokenInputStream
extends InputStream {
    private final IOException exception;

    public BrokenInputStream(IOException exception) {
        this.exception = exception;
    }

    public BrokenInputStream() {
        this(new IOException("Broken input stream"));
    }

    @Override
    public int read() throws IOException {
        throw this.exception;
    }

    @Override
    public int available() throws IOException {
        throw this.exception;
    }

    @Override
    public long skip(long n) throws IOException {
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

