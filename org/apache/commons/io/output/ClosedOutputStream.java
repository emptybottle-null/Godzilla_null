/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class ClosedOutputStream
extends OutputStream {
    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = new ClosedOutputStream();

    @Override
    public void write(int b) throws IOException {
        throw new IOException("write(" + b + ") failed: stream is closed");
    }

    @Override
    public void flush() throws IOException {
        throw new IOException("flush() failed: stream is closed");
    }
}

