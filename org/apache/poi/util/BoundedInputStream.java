/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.SuppressForbidden;

public class BoundedInputStream
extends InputStream {
    private final InputStream in;
    private final long max;
    private long pos;
    private long mark = -1L;
    private boolean propagateClose = true;

    public BoundedInputStream(InputStream in, long size) {
        this.max = size;
        this.in = in;
    }

    public BoundedInputStream(InputStream in) {
        this(in, -1L);
    }

    @Override
    public int read() throws IOException {
        if (this.max >= 0L && this.pos == this.max) {
            return -1;
        }
        int result = this.in.read();
        ++this.pos;
        return result;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.max >= 0L && this.pos >= this.max) {
            return -1;
        }
        long maxRead = this.max >= 0L ? Math.min((long)len, this.max - this.pos) : (long)len;
        int bytesRead = this.in.read(b, off, (int)maxRead);
        if (bytesRead == -1) {
            return -1;
        }
        this.pos += (long)bytesRead;
        return bytesRead;
    }

    @Override
    public long skip(long n) throws IOException {
        long toSkip = this.max >= 0L ? Math.min(n, this.max - this.pos) : n;
        long skippedBytes = IOUtils.skipFully(this.in, toSkip);
        this.pos += skippedBytes;
        return skippedBytes;
    }

    @Override
    @SuppressForbidden(value="just delegating")
    public int available() throws IOException {
        if (this.max >= 0L && this.pos >= this.max) {
            return 0;
        }
        return this.in.available();
    }

    public String toString() {
        return this.in.toString();
    }

    @Override
    public void close() throws IOException {
        if (this.propagateClose) {
            this.in.close();
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        this.in.reset();
        this.pos = this.mark;
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
        this.mark = this.pos;
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    public void setPropagateClose(boolean propagateClose) {
        this.propagateClose = propagateClose;
    }
}

