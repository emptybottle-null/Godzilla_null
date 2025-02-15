/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

public class RandomAccessFileInputStream
extends InputStream {
    private final boolean closeOnClose;
    private final RandomAccessFile randomAccessFile;

    public RandomAccessFileInputStream(RandomAccessFile file) {
        this(file, false);
    }

    public RandomAccessFileInputStream(RandomAccessFile file, boolean closeOnClose) {
        this.randomAccessFile = Objects.requireNonNull(file, "file");
        this.closeOnClose = closeOnClose;
    }

    @Override
    public int available() throws IOException {
        long avail = this.availableLong();
        if (avail > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)avail;
    }

    public long availableLong() throws IOException {
        return this.randomAccessFile.length() - this.randomAccessFile.getFilePointer();
    }

    @Override
    public void close() throws IOException {
        super.close();
        if (this.closeOnClose) {
            this.randomAccessFile.close();
        }
    }

    public RandomAccessFile getRandomAccessFile() {
        return this.randomAccessFile;
    }

    public boolean isCloseOnClose() {
        return this.closeOnClose;
    }

    @Override
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return this.randomAccessFile.read(bytes);
    }

    @Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        return this.randomAccessFile.read(bytes, offset, length);
    }

    private void seek(long position) throws IOException {
        this.randomAccessFile.seek(position);
    }

    @Override
    public long skip(long skipCount) throws IOException {
        long newPos;
        long fileLength;
        if (skipCount <= 0L) {
            return 0L;
        }
        long filePointer = this.randomAccessFile.getFilePointer();
        if (filePointer >= (fileLength = this.randomAccessFile.length())) {
            return 0L;
        }
        long targetPos = filePointer + skipCount;
        long l = newPos = targetPos > fileLength ? fileLength - 1L : targetPos;
        if (newPos > 0L) {
            this.seek(newPos);
        }
        return this.randomAccessFile.getFilePointer() - filePointer;
    }
}

