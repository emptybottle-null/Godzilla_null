/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.buffer;

import java.nio.ByteBuffer;
import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.util.Assert;

public class DefaultDataBufferFactory
implements DataBufferFactory {
    public static final int DEFAULT_INITIAL_CAPACITY = 256;
    public static final DefaultDataBufferFactory sharedInstance = new DefaultDataBufferFactory();
    private final boolean preferDirect;
    private final int defaultInitialCapacity;

    public DefaultDataBufferFactory() {
        this(false);
    }

    public DefaultDataBufferFactory(boolean preferDirect) {
        this(preferDirect, 256);
    }

    public DefaultDataBufferFactory(boolean preferDirect, int defaultInitialCapacity) {
        Assert.isTrue(defaultInitialCapacity > 0, "'defaultInitialCapacity' should be larger than 0");
        this.preferDirect = preferDirect;
        this.defaultInitialCapacity = defaultInitialCapacity;
    }

    @Override
    public DefaultDataBuffer allocateBuffer() {
        return this.allocateBuffer(this.defaultInitialCapacity);
    }

    @Override
    public DefaultDataBuffer allocateBuffer(int initialCapacity) {
        ByteBuffer byteBuffer = this.preferDirect ? ByteBuffer.allocateDirect(initialCapacity) : ByteBuffer.allocate(initialCapacity);
        return DefaultDataBuffer.fromEmptyByteBuffer(this, byteBuffer);
    }

    @Override
    public DefaultDataBuffer wrap(ByteBuffer byteBuffer) {
        return DefaultDataBuffer.fromFilledByteBuffer(this, byteBuffer.slice());
    }

    @Override
    public DefaultDataBuffer wrap(byte[] bytes) {
        return DefaultDataBuffer.fromFilledByteBuffer(this, ByteBuffer.wrap(bytes));
    }

    @Override
    public DefaultDataBuffer join(List<? extends DataBuffer> dataBuffers) {
        Assert.notEmpty(dataBuffers, "DataBuffer List must not be empty");
        int capacity = dataBuffers.stream().mapToInt(DataBuffer::readableByteCount).sum();
        DefaultDataBuffer result = this.allocateBuffer(capacity);
        dataBuffers.forEach(xva$0 -> result.write((DataBuffer)xva$0));
        dataBuffers.forEach(DataBufferUtils::release);
        return result;
    }

    public String toString() {
        return "DefaultDataBufferFactory (preferDirect=" + this.preferDirect + ")";
    }
}

