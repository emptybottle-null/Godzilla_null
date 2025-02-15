/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.buffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.core.io.buffer.DataBufferUtils;

public class LimitedDataBufferList
extends ArrayList<DataBuffer> {
    private final int maxByteCount;
    private int byteCount;

    public LimitedDataBufferList(int maxByteCount) {
        this.maxByteCount = maxByteCount;
    }

    @Override
    public boolean add(DataBuffer buffer) {
        this.updateCount(buffer.readableByteCount());
        return super.add(buffer);
    }

    @Override
    public void add(int index, DataBuffer buffer) {
        super.add(index, buffer);
        this.updateCount(buffer.readableByteCount());
    }

    @Override
    public boolean addAll(Collection<? extends DataBuffer> collection) {
        boolean result = super.addAll(collection);
        collection.forEach((? super T buffer) -> this.updateCount(buffer.readableByteCount()));
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends DataBuffer> collection) {
        boolean result = super.addAll(index, collection);
        collection.forEach((? super T buffer) -> this.updateCount(buffer.readableByteCount()));
        return result;
    }

    private void updateCount(int bytesToAdd) {
        if (this.maxByteCount < 0) {
            return;
        }
        if (bytesToAdd > Integer.MAX_VALUE - this.byteCount) {
            this.raiseLimitException();
        } else {
            this.byteCount += bytesToAdd;
            if (this.byteCount > this.maxByteCount) {
                this.raiseLimitException();
            }
        }
    }

    private void raiseLimitException() {
        throw new DataBufferLimitException("Exceeded limit on max bytes to buffer : " + this.maxByteCount);
    }

    @Override
    public DataBuffer remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super DataBuffer> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer set(int index, DataBuffer element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.byteCount = 0;
        super.clear();
    }

    public void releaseAndClear() {
        this.forEach((? super E buf) -> {
            try {
                DataBufferUtils.release(buf);
            } catch (Throwable throwable) {
                // empty catch block
            }
        });
        this.clear();
    }
}

