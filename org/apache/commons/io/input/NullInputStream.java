/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.input.UnsupportedOperationExceptions;

public class NullInputStream
extends InputStream {
    private final long size;
    private long position;
    private long mark = -1L;
    private long readlimit;
    private boolean eof;
    private final boolean throwEofException;
    private final boolean markSupported;

    public NullInputStream() {
        this(0L, true, false);
    }

    public NullInputStream(long size) {
        this(size, true, false);
    }

    public NullInputStream(long size, boolean markSupported, boolean throwEofException) {
        this.size = size;
        this.markSupported = markSupported;
        this.throwEofException = throwEofException;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    @Override
    public int available() {
        long avail = this.size - this.position;
        if (avail <= 0L) {
            return 0;
        }
        if (avail > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)avail;
    }

    @Override
    public void close() throws IOException {
        this.eof = false;
        this.position = 0L;
        this.mark = -1L;
    }

    @Override
    public synchronized void mark(int readlimit) {
        if (!this.markSupported) {
            throw UnsupportedOperationExceptions.mark();
        }
        this.mark = this.position;
        this.readlimit = readlimit;
    }

    @Override
    public boolean markSupported() {
        return this.markSupported;
    }

    @Override
    public int read() throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        if (this.position == this.size) {
            return this.doEndOfFile();
        }
        ++this.position;
        return this.processByte();
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return this.read(bytes, 0, bytes.length);
    }

    @Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        if (this.position == this.size) {
            return this.doEndOfFile();
        }
        this.position += (long)length;
        int returnLength = length;
        if (this.position > this.size) {
            returnLength = length - (int)(this.position - this.size);
            this.position = this.size;
        }
        this.processBytes(bytes, offset, returnLength);
        return returnLength;
    }

    @Override
    public synchronized void reset() throws IOException {
        if (!this.markSupported) {
            throw UnsupportedOperationExceptions.reset();
        }
        if (this.mark < 0L) {
            throw new IOException("No position has been marked");
        }
        if (this.position > this.mark + this.readlimit) {
            throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readlimit + "]");
        }
        this.position = this.mark;
        this.eof = false;
    }

    @Override
    public long skip(long numberOfBytes) throws IOException {
        if (this.eof) {
            throw new IOException("Skip after end of file");
        }
        if (this.position == this.size) {
            return this.doEndOfFile();
        }
        this.position += numberOfBytes;
        long returnLength = numberOfBytes;
        if (this.position > this.size) {
            returnLength = numberOfBytes - (this.position - this.size);
            this.position = this.size;
        }
        return returnLength;
    }

    protected int processByte() {
        return 0;
    }

    protected void processBytes(byte[] bytes, int offset, int length) {
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (this.throwEofException) {
            throw new EOFException();
        }
        return -1;
    }
}

