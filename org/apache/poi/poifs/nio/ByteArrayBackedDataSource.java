/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.poi.poifs.nio.DataSource;
import org.apache.poi.util.IOUtils;

public class ByteArrayBackedDataSource
extends DataSource {
    private static final int MAX_RECORD_LENGTH = Integer.MAX_VALUE;
    private byte[] buffer;
    private long size;

    public ByteArrayBackedDataSource(byte[] data, int size) {
        this.buffer = data;
        this.size = size;
    }

    public ByteArrayBackedDataSource(byte[] data) {
        this(data, data.length);
    }

    @Override
    public ByteBuffer read(int length, long position) {
        if (position >= this.size) {
            throw new IndexOutOfBoundsException("Unable to read " + length + " bytes from " + position + " in stream of length " + this.size);
        }
        int toRead = (int)Math.min((long)length, this.size - position);
        return ByteBuffer.wrap(this.buffer, (int)position, toRead);
    }

    @Override
    public void write(ByteBuffer src, long position) {
        long endPosition = position + (long)src.capacity();
        if (endPosition > (long)this.buffer.length) {
            this.extend(endPosition);
        }
        src.get(this.buffer, (int)position, src.capacity());
        if (endPosition > this.size) {
            this.size = endPosition;
        }
    }

    private void extend(long length) {
        long difference = length - (long)this.buffer.length;
        if ((double)difference < (double)this.buffer.length * 0.25) {
            difference = (long)((double)this.buffer.length * 0.25);
        }
        if (difference < 4096L) {
            difference = 4096L;
        }
        long totalLen = difference + (long)this.buffer.length;
        byte[] nb = IOUtils.safelyAllocate(totalLen, Integer.MAX_VALUE);
        System.arraycopy(this.buffer, 0, nb, 0, (int)this.size);
        this.buffer = nb;
    }

    @Override
    public void copyTo(OutputStream stream) throws IOException {
        stream.write(this.buffer, 0, (int)this.size);
    }

    @Override
    public long size() {
        return this.size;
    }

    @Override
    public void close() {
        this.buffer = null;
        this.size = -1L;
    }
}

