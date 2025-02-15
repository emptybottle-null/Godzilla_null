/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.utils.CountingInputStream;

public class BitInputStream
implements Closeable {
    private static final int MAXIMUM_CACHE_SIZE = 63;
    private static final long[] MASKS = new long[64];
    private final CountingInputStream in;
    private final ByteOrder byteOrder;
    private long bitsCached = 0L;
    private int bitsCachedSize = 0;

    public BitInputStream(InputStream in, ByteOrder byteOrder) {
        this.in = new CountingInputStream(in);
        this.byteOrder = byteOrder;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    public void clearBitCache() {
        this.bitsCached = 0L;
        this.bitsCachedSize = 0;
    }

    public long readBits(int count) throws IOException {
        if (count < 0 || count > 63) {
            throw new IllegalArgumentException("count must not be negative or greater than 63");
        }
        if (this.ensureCache(count)) {
            return -1L;
        }
        if (this.bitsCachedSize < count) {
            return this.processBitsGreater57(count);
        }
        return this.readCachedBits(count);
    }

    public int bitsCached() {
        return this.bitsCachedSize;
    }

    public long bitsAvailable() throws IOException {
        return (long)this.bitsCachedSize + 8L * (long)this.in.available();
    }

    public void alignWithByteBoundary() {
        int toSkip = this.bitsCachedSize % 8;
        if (toSkip > 0) {
            this.readCachedBits(toSkip);
        }
    }

    public long getBytesRead() {
        return this.in.getBytesRead();
    }

    private long processBitsGreater57(int count) throws IOException {
        int overflowBits = 0;
        long overflow = 0L;
        int bitsToAddCount = count - this.bitsCachedSize;
        overflowBits = 8 - bitsToAddCount;
        long nextByte = this.in.read();
        if (nextByte < 0L) {
            return nextByte;
        }
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            long bitsToAdd = nextByte & MASKS[bitsToAddCount];
            this.bitsCached |= bitsToAdd << this.bitsCachedSize;
            overflow = nextByte >>> bitsToAddCount & MASKS[overflowBits];
        } else {
            this.bitsCached <<= bitsToAddCount;
            long bitsToAdd = nextByte >>> overflowBits & MASKS[bitsToAddCount];
            this.bitsCached |= bitsToAdd;
            overflow = nextByte & MASKS[overflowBits];
        }
        long bitsOut = this.bitsCached & MASKS[count];
        this.bitsCached = overflow;
        this.bitsCachedSize = overflowBits;
        return bitsOut;
    }

    private long readCachedBits(int count) {
        long bitsOut;
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            bitsOut = this.bitsCached & MASKS[count];
            this.bitsCached >>>= count;
        } else {
            bitsOut = this.bitsCached >> this.bitsCachedSize - count & MASKS[count];
        }
        this.bitsCachedSize -= count;
        return bitsOut;
    }

    private boolean ensureCache(int count) throws IOException {
        while (this.bitsCachedSize < count && this.bitsCachedSize < 57) {
            long nextByte = this.in.read();
            if (nextByte < 0L) {
                return true;
            }
            if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.bitsCached |= nextByte << this.bitsCachedSize;
            } else {
                this.bitsCached <<= 8;
                this.bitsCached |= nextByte;
            }
            this.bitsCachedSize += 8;
        }
        return false;
    }

    static {
        for (int i = 1; i <= 63; ++i) {
            BitInputStream.MASKS[i] = (MASKS[i - 1] << 1) + 1L;
        }
    }
}

