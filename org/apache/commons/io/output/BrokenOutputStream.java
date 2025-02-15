/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class BrokenOutputStream
extends OutputStream {
    private final IOException exception;

    public BrokenOutputStream(IOException exception) {
        this.exception = exception;
    }

    public BrokenOutputStream() {
        this(new IOException("Broken output stream"));
    }

    @Override
    public void write(int b) throws IOException {
        throw this.exception;
    }

    @Override
    public void flush() throws IOException {
        throw this.exception;
    }

    @Override
    public void close() throws IOException {
        throw this.exception;
    }
}

