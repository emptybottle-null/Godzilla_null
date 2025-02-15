/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public abstract class AbstractLZ77CompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    private final int windowSize;
    private final byte[] buf;
    private int writeIndex;
    private int readIndex;
    private final CountingInputStream in;
    private long bytesRemaining;
    private int backReferenceOffset;
    private int size = 0;
    private final byte[] oneByte = new byte[1];
    protected final ByteUtils.ByteSupplier supplier = new ByteUtils.ByteSupplier(){

        @Override
        public int getAsByte() throws IOException {
            return AbstractLZ77CompressorInputStream.this.readOneByte();
        }
    };

    public AbstractLZ77CompressorInputStream(InputStream is, int windowSize) throws IOException {
        this.in = new CountingInputStream(is);
        if (windowSize <= 0) {
            throw new IllegalArgumentException("windowSize must be bigger than 0");
        }
        this.windowSize = windowSize;
        this.buf = new byte[3 * windowSize];
        this.readIndex = 0;
        this.writeIndex = 0;
        this.bytesRemaining = 0L;
    }

    @Override
    public int read() throws IOException {
        return this.read(this.oneByte, 0, 1) == -1 ? -1 : this.oneByte[0] & 0xFF;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    @Override
    public int available() {
        return this.writeIndex - this.readIndex;
    }

    public int getSize() {
        return this.size;
    }

    public void prefill(byte[] data) {
        if (this.writeIndex != 0) {
            throw new IllegalStateException("The stream has already been read from, can't prefill anymore");
        }
        int len = Math.min(this.windowSize, data.length);
        System.arraycopy(data, data.length - len, this.buf, 0, len);
        this.writeIndex += len;
        this.readIndex += len;
    }

    @Override
    public long getCompressedCount() {
        return this.in.getBytesRead();
    }

    protected final void startLiteral(long length) {
        if (length < 0L) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.bytesRemaining = length;
    }

    protected final boolean hasMoreDataInBlock() {
        return this.bytesRemaining > 0L;
    }

    protected final int readLiteral(byte[] b, int off, int len) throws IOException {
        int avail = this.available();
        if (len > avail) {
            this.tryToReadLiteral(len - avail);
        }
        return this.readFromBuffer(b, off, len);
    }

    private void tryToReadLiteral(int bytesToRead) throws IOException {
        int reallyTryToRead = Math.min((int)Math.min((long)bytesToRead, this.bytesRemaining), this.buf.length - this.writeIndex);
        int bytesRead = reallyTryToRead > 0 ? IOUtils.readFully(this.in, this.buf, this.writeIndex, reallyTryToRead) : 0;
        this.count(bytesRead);
        if (reallyTryToRead != bytesRead) {
            throw new IOException("Premature end of stream reading literal");
        }
        this.writeIndex += reallyTryToRead;
        this.bytesRemaining -= (long)reallyTryToRead;
    }

    private int readFromBuffer(byte[] b, int off, int len) {
        int readable = Math.min(len, this.available());
        if (readable > 0) {
            System.arraycopy(this.buf, this.readIndex, b, off, readable);
            this.readIndex += readable;
            if (this.readIndex > 2 * this.windowSize) {
                this.slideBuffer();
            }
        }
        this.size += readable;
        return readable;
    }

    private void slideBuffer() {
        System.arraycopy(this.buf, this.windowSize, this.buf, 0, this.windowSize * 2);
        this.writeIndex -= this.windowSize;
        this.readIndex -= this.windowSize;
    }

    protected final void startBackReference(int offset, long length) {
        if (offset <= 0 || offset > this.writeIndex) {
            throw new IllegalArgumentException("offset must be bigger than 0 but not bigger than the number of bytes available for back-references");
        }
        if (length < 0L) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.backReferenceOffset = offset;
        this.bytesRemaining = length;
    }

    protected final int readBackReference(byte[] b, int off, int len) {
        int avail = this.available();
        if (len > avail) {
            this.tryToCopy(len - avail);
        }
        return this.readFromBuffer(b, off, len);
    }

    private void tryToCopy(int bytesToCopy) {
        int copy = Math.min((int)Math.min((long)bytesToCopy, this.bytesRemaining), this.buf.length - this.writeIndex);
        if (copy != 0) {
            if (this.backReferenceOffset == 1) {
                byte last = this.buf[this.writeIndex - 1];
                Arrays.fill(this.buf, this.writeIndex, this.writeIndex + copy, last);
                this.writeIndex += copy;
            } else if (copy < this.backReferenceOffset) {
                System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, copy);
                this.writeIndex += copy;
            } else {
                int fullRots = copy / this.backReferenceOffset;
                for (int i = 0; i < fullRots; ++i) {
                    System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, this.backReferenceOffset);
                    this.writeIndex += this.backReferenceOffset;
                }
                int pad = copy - this.backReferenceOffset * fullRots;
                if (pad > 0) {
                    System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, pad);
                    this.writeIndex += pad;
                }
            }
        }
        this.bytesRemaining -= (long)copy;
    }

    protected final int readOneByte() throws IOException {
        int b = this.in.read();
        if (b != -1) {
            this.count(1);
            return b & 0xFF;
        }
        return -1;
    }
}

