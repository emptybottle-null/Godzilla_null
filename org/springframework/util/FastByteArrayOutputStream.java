/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.UpdateMessageDigestInputStream;

public class FastByteArrayOutputStream
extends OutputStream {
    private static final int DEFAULT_BLOCK_SIZE = 256;
    private final Deque<byte[]> buffers = new ArrayDeque<byte[]>();
    private final int initialBlockSize;
    private int nextBlockSize = 0;
    private int alreadyBufferedSize = 0;
    private int index = 0;
    private boolean closed = false;

    public FastByteArrayOutputStream() {
        this(256);
    }

    public FastByteArrayOutputStream(int initialBlockSize) {
        Assert.isTrue(initialBlockSize > 0, "Initial block size must be greater than 0");
        this.initialBlockSize = initialBlockSize;
        this.nextBlockSize = initialBlockSize;
    }

    @Override
    public void write(int datum) throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
        if (this.buffers.peekLast() == null || this.buffers.getLast().length == this.index) {
            this.addBuffer(1);
        }
        this.buffers.getLast()[this.index++] = (byte)datum;
    }

    @Override
    public void write(byte[] data, int offset, int length) throws IOException {
        if (offset < 0 || offset + length > data.length || length < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (this.closed) {
            throw new IOException("Stream closed");
        }
        if (this.buffers.peekLast() == null || this.buffers.getLast().length == this.index) {
            this.addBuffer(length);
        }
        if (this.index + length > this.buffers.getLast().length) {
            int copyLength;
            int pos = offset;
            do {
                if (this.index == this.buffers.getLast().length) {
                    this.addBuffer(length);
                }
                if (length < (copyLength = this.buffers.getLast().length - this.index)) {
                    copyLength = length;
                }
                System.arraycopy(data, pos, this.buffers.getLast(), this.index, copyLength);
                pos += copyLength;
                this.index += copyLength;
            } while ((length -= copyLength) > 0);
        } else {
            System.arraycopy(data, offset, this.buffers.getLast(), this.index, length);
            this.index += length;
        }
    }

    @Override
    public void close() {
        this.closed = true;
    }

    public String toString() {
        return new String(this.toByteArrayUnsafe());
    }

    public int size() {
        return this.alreadyBufferedSize + this.index;
    }

    public byte[] toByteArrayUnsafe() {
        int totalSize = this.size();
        if (totalSize == 0) {
            return new byte[0];
        }
        this.resize(totalSize);
        return this.buffers.getFirst();
    }

    public byte[] toByteArray() {
        byte[] bytesUnsafe = this.toByteArrayUnsafe();
        return (byte[])bytesUnsafe.clone();
    }

    public void reset() {
        this.buffers.clear();
        this.nextBlockSize = this.initialBlockSize;
        this.closed = false;
        this.index = 0;
        this.alreadyBufferedSize = 0;
    }

    public InputStream getInputStream() {
        return new FastByteArrayInputStream(this);
    }

    public void writeTo(OutputStream out) throws IOException {
        Iterator<byte[]> it = this.buffers.iterator();
        while (it.hasNext()) {
            byte[] bytes = it.next();
            if (it.hasNext()) {
                out.write(bytes, 0, bytes.length);
                continue;
            }
            out.write(bytes, 0, this.index);
        }
    }

    public void resize(int targetCapacity) {
        Assert.isTrue(targetCapacity >= this.size(), "New capacity must not be smaller than current size");
        if (this.buffers.peekFirst() == null) {
            this.nextBlockSize = targetCapacity - this.size();
        } else if (this.size() != targetCapacity || this.buffers.getFirst().length != targetCapacity) {
            int totalSize = this.size();
            byte[] data = new byte[targetCapacity];
            int pos = 0;
            Iterator<byte[]> it = this.buffers.iterator();
            while (it.hasNext()) {
                byte[] bytes = it.next();
                if (it.hasNext()) {
                    System.arraycopy(bytes, 0, data, pos, bytes.length);
                    pos += bytes.length;
                    continue;
                }
                System.arraycopy(bytes, 0, data, pos, this.index);
            }
            this.buffers.clear();
            this.buffers.add(data);
            this.index = totalSize;
            this.alreadyBufferedSize = 0;
        }
    }

    private void addBuffer(int minCapacity) {
        if (this.buffers.peekLast() != null) {
            this.alreadyBufferedSize += this.index;
            this.index = 0;
        }
        if (this.nextBlockSize < minCapacity) {
            this.nextBlockSize = FastByteArrayOutputStream.nextPowerOf2(minCapacity);
        }
        this.buffers.add(new byte[this.nextBlockSize]);
        this.nextBlockSize *= 2;
    }

    private static int nextPowerOf2(int val) {
        --val;
        val = val >> 1 | val;
        val = val >> 2 | val;
        val = val >> 4 | val;
        val = val >> 8 | val;
        val = val >> 16 | val;
        return ++val;
    }

    private static final class FastByteArrayInputStream
    extends UpdateMessageDigestInputStream {
        private final FastByteArrayOutputStream fastByteArrayOutputStream;
        private final Iterator<byte[]> buffersIterator;
        @Nullable
        private byte[] currentBuffer;
        private int currentBufferLength = 0;
        private int nextIndexInCurrentBuffer = 0;
        private int totalBytesRead = 0;

        public FastByteArrayInputStream(FastByteArrayOutputStream fastByteArrayOutputStream) {
            this.fastByteArrayOutputStream = fastByteArrayOutputStream;
            this.buffersIterator = fastByteArrayOutputStream.buffers.iterator();
            if (this.buffersIterator.hasNext()) {
                this.currentBuffer = this.buffersIterator.next();
                this.currentBufferLength = this.currentBuffer == fastByteArrayOutputStream.buffers.getLast() ? fastByteArrayOutputStream.index : (this.currentBuffer != null ? this.currentBuffer.length : 0);
            }
        }

        @Override
        public int read() {
            if (this.currentBuffer == null) {
                return -1;
            }
            if (this.nextIndexInCurrentBuffer < this.currentBufferLength) {
                ++this.totalBytesRead;
                return this.currentBuffer[this.nextIndexInCurrentBuffer++] & 0xFF;
            }
            if (this.buffersIterator.hasNext()) {
                this.currentBuffer = this.buffersIterator.next();
                this.updateCurrentBufferLength();
                this.nextIndexInCurrentBuffer = 0;
            } else {
                this.currentBuffer = null;
            }
            return this.read();
        }

        @Override
        public int read(byte[] b) {
            return this.read(b, 0, b.length);
        }

        @Override
        public int read(byte[] b, int off, int len) {
            if (off < 0 || len < 0 || len > b.length - off) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return 0;
            }
            if (this.currentBuffer == null) {
                return -1;
            }
            if (this.nextIndexInCurrentBuffer < this.currentBufferLength) {
                int bytesToCopy = Math.min(len, this.currentBufferLength - this.nextIndexInCurrentBuffer);
                System.arraycopy(this.currentBuffer, this.nextIndexInCurrentBuffer, b, off, bytesToCopy);
                this.totalBytesRead += bytesToCopy;
                this.nextIndexInCurrentBuffer += bytesToCopy;
                int remaining = this.read(b, off + bytesToCopy, len - bytesToCopy);
                return bytesToCopy + Math.max(remaining, 0);
            }
            if (this.buffersIterator.hasNext()) {
                this.currentBuffer = this.buffersIterator.next();
                this.updateCurrentBufferLength();
                this.nextIndexInCurrentBuffer = 0;
            } else {
                this.currentBuffer = null;
            }
            return this.read(b, off, len);
        }

        @Override
        public long skip(long n) throws IOException {
            if (n > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("n exceeds maximum (2147483647): " + n);
            }
            if (n == 0L) {
                return 0L;
            }
            if (n < 0L) {
                throw new IllegalArgumentException("n must be 0 or greater: " + n);
            }
            int len = (int)n;
            if (this.currentBuffer == null) {
                return 0L;
            }
            if (this.nextIndexInCurrentBuffer < this.currentBufferLength) {
                int bytesToSkip = Math.min(len, this.currentBufferLength - this.nextIndexInCurrentBuffer);
                this.totalBytesRead += bytesToSkip;
                this.nextIndexInCurrentBuffer += bytesToSkip;
                return (long)bytesToSkip + this.skip(len - bytesToSkip);
            }
            if (this.buffersIterator.hasNext()) {
                this.currentBuffer = this.buffersIterator.next();
                this.updateCurrentBufferLength();
                this.nextIndexInCurrentBuffer = 0;
            } else {
                this.currentBuffer = null;
            }
            return this.skip(len);
        }

        @Override
        public int available() {
            return this.fastByteArrayOutputStream.size() - this.totalBytesRead;
        }

        @Override
        public void updateMessageDigest(MessageDigest messageDigest) {
            this.updateMessageDigest(messageDigest, this.available());
        }

        @Override
        public void updateMessageDigest(MessageDigest messageDigest, int len) {
            if (this.currentBuffer == null) {
                return;
            }
            if (len == 0) {
                return;
            }
            if (len < 0) {
                throw new IllegalArgumentException("len must be 0 or greater: " + len);
            }
            if (this.nextIndexInCurrentBuffer < this.currentBufferLength) {
                int bytesToCopy = Math.min(len, this.currentBufferLength - this.nextIndexInCurrentBuffer);
                messageDigest.update(this.currentBuffer, this.nextIndexInCurrentBuffer, bytesToCopy);
                this.nextIndexInCurrentBuffer += bytesToCopy;
                this.updateMessageDigest(messageDigest, len - bytesToCopy);
            } else {
                if (this.buffersIterator.hasNext()) {
                    this.currentBuffer = this.buffersIterator.next();
                    this.updateCurrentBufferLength();
                    this.nextIndexInCurrentBuffer = 0;
                } else {
                    this.currentBuffer = null;
                }
                this.updateMessageDigest(messageDigest, len);
            }
        }

        private void updateCurrentBufferLength() {
            this.currentBufferLength = this.currentBuffer == this.fastByteArrayOutputStream.buffers.getLast() ? this.fastByteArrayOutputStream.index : (this.currentBuffer != null ? this.currentBuffer.length : 0);
        }
    }
}

