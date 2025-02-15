/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.buffer.CircularByteBuffer;

public class CircularBufferInputStream
extends InputStream {
    protected final InputStream in;
    protected final CircularByteBuffer buffer;
    protected final int bufferSize;
    private boolean eof;

    public CircularBufferInputStream(InputStream inputStream, int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Invalid bufferSize: " + bufferSize);
        }
        this.in = Objects.requireNonNull(inputStream, "inputStream");
        this.buffer = new CircularByteBuffer(bufferSize);
        this.bufferSize = bufferSize;
        this.eof = false;
    }

    public CircularBufferInputStream(InputStream inputStream) {
        this(inputStream, 8192);
    }

    protected void fillBuffer() throws IOException {
        if (this.eof) {
            return;
        }
        int space = this.buffer.getSpace();
        byte[] buf = IOUtils.byteArray(space);
        while (space > 0) {
            int res = this.in.read(buf, 0, space);
            if (res == -1) {
                this.eof = true;
                return;
            }
            if (res <= 0) continue;
            this.buffer.add(buf, 0, res);
            space -= res;
        }
    }

    protected boolean haveBytes(int count) throws IOException {
        if (this.buffer.getCurrentNumberOfBytes() < count) {
            this.fillBuffer();
        }
        return this.buffer.hasBytes();
    }

    @Override
    public int read() throws IOException {
        if (!this.haveBytes(1)) {
            return -1;
        }
        return this.buffer.read() & 0xFF;
    }

    @Override
    public int read(byte[] buffer) throws IOException {
        return this.read(buffer, 0, buffer.length);
    }

    @Override
    public int read(byte[] targetBuffer, int offset, int length) throws IOException {
        Objects.requireNonNull(targetBuffer, "targetBuffer");
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be negative");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative");
        }
        if (!this.haveBytes(length)) {
            return -1;
        }
        int result = Math.min(length, this.buffer.getCurrentNumberOfBytes());
        for (int i = 0; i < result; ++i) {
            targetBuffer[offset + i] = this.buffer.read();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
        this.eof = true;
        this.buffer.clear();
    }
}

