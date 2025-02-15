/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.CompositeByteBuf
 *  io.netty.buffer.Unpooled
 */
package org.springframework.core.io.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.util.Assert;

public class NettyDataBufferFactory
implements DataBufferFactory {
    private final ByteBufAllocator byteBufAllocator;

    public NettyDataBufferFactory(ByteBufAllocator byteBufAllocator) {
        Assert.notNull((Object)byteBufAllocator, "ByteBufAllocator must not be null");
        this.byteBufAllocator = byteBufAllocator;
    }

    public ByteBufAllocator getByteBufAllocator() {
        return this.byteBufAllocator;
    }

    @Override
    public NettyDataBuffer allocateBuffer() {
        ByteBuf byteBuf = this.byteBufAllocator.buffer();
        return new NettyDataBuffer(byteBuf, this);
    }

    @Override
    public NettyDataBuffer allocateBuffer(int initialCapacity) {
        ByteBuf byteBuf = this.byteBufAllocator.buffer(initialCapacity);
        return new NettyDataBuffer(byteBuf, this);
    }

    @Override
    public NettyDataBuffer wrap(ByteBuffer byteBuffer) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer((ByteBuffer)byteBuffer);
        return new NettyDataBuffer(byteBuf, this);
    }

    @Override
    public DataBuffer wrap(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer((byte[])bytes);
        return new NettyDataBuffer(byteBuf, this);
    }

    public NettyDataBuffer wrap(ByteBuf byteBuf) {
        byteBuf.touch();
        return new NettyDataBuffer(byteBuf, this);
    }

    @Override
    public DataBuffer join(List<? extends DataBuffer> dataBuffers) {
        Assert.notEmpty(dataBuffers, "DataBuffer List must not be empty");
        int bufferCount = dataBuffers.size();
        if (bufferCount == 1) {
            return dataBuffers.get(0);
        }
        CompositeByteBuf composite = this.byteBufAllocator.compositeBuffer(bufferCount);
        for (DataBuffer dataBuffer : dataBuffers) {
            Assert.isInstanceOf(NettyDataBuffer.class, dataBuffer);
            composite.addComponent(true, ((NettyDataBuffer)dataBuffer).getNativeBuffer());
        }
        return new NettyDataBuffer((ByteBuf)composite, this);
    }

    public static ByteBuf toByteBuf(DataBuffer buffer) {
        if (buffer instanceof NettyDataBuffer) {
            return ((NettyDataBuffer)buffer).getNativeBuffer();
        }
        return Unpooled.wrappedBuffer((ByteBuffer)buffer.asByteBuffer());
    }

    public String toString() {
        return "NettyDataBufferFactory (" + this.byteBufAllocator + ")";
    }
}

