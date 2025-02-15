/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.ByteBufUtil
 */
package org.springframework.core.io.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.IntPredicate;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class NettyDataBuffer
implements PooledDataBuffer {
    private final ByteBuf byteBuf;
    private final NettyDataBufferFactory dataBufferFactory;

    NettyDataBuffer(ByteBuf byteBuf, NettyDataBufferFactory dataBufferFactory) {
        Assert.notNull((Object)byteBuf, "ByteBuf must not be null");
        Assert.notNull((Object)dataBufferFactory, "NettyDataBufferFactory must not be null");
        this.byteBuf = byteBuf;
        this.dataBufferFactory = dataBufferFactory;
    }

    public ByteBuf getNativeBuffer() {
        return this.byteBuf;
    }

    @Override
    public NettyDataBufferFactory factory() {
        return this.dataBufferFactory;
    }

    @Override
    public int indexOf(IntPredicate predicate, int fromIndex) {
        Assert.notNull((Object)predicate, "IntPredicate must not be null");
        if (fromIndex < 0) {
            fromIndex = 0;
        } else if (fromIndex >= this.byteBuf.writerIndex()) {
            return -1;
        }
        int length = this.byteBuf.writerIndex() - fromIndex;
        return this.byteBuf.forEachByte(fromIndex, length, predicate.negate()::test);
    }

    @Override
    public int lastIndexOf(IntPredicate predicate, int fromIndex) {
        Assert.notNull((Object)predicate, "IntPredicate must not be null");
        if (fromIndex < 0) {
            return -1;
        }
        fromIndex = Math.min(fromIndex, this.byteBuf.writerIndex() - 1);
        return this.byteBuf.forEachByteDesc(0, fromIndex + 1, predicate.negate()::test);
    }

    @Override
    public int readableByteCount() {
        return this.byteBuf.readableBytes();
    }

    @Override
    public int writableByteCount() {
        return this.byteBuf.writableBytes();
    }

    @Override
    public int readPosition() {
        return this.byteBuf.readerIndex();
    }

    @Override
    public NettyDataBuffer readPosition(int readPosition) {
        this.byteBuf.readerIndex(readPosition);
        return this;
    }

    @Override
    public int writePosition() {
        return this.byteBuf.writerIndex();
    }

    @Override
    public NettyDataBuffer writePosition(int writePosition) {
        this.byteBuf.writerIndex(writePosition);
        return this;
    }

    @Override
    public byte getByte(int index) {
        return this.byteBuf.getByte(index);
    }

    @Override
    public int capacity() {
        return this.byteBuf.capacity();
    }

    @Override
    public NettyDataBuffer capacity(int capacity) {
        this.byteBuf.capacity(capacity);
        return this;
    }

    @Override
    public DataBuffer ensureCapacity(int capacity) {
        this.byteBuf.ensureWritable(capacity);
        return this;
    }

    @Override
    public byte read() {
        return this.byteBuf.readByte();
    }

    @Override
    public NettyDataBuffer read(byte[] destination) {
        this.byteBuf.readBytes(destination);
        return this;
    }

    @Override
    public NettyDataBuffer read(byte[] destination, int offset, int length) {
        this.byteBuf.readBytes(destination, offset, length);
        return this;
    }

    @Override
    public NettyDataBuffer write(byte b) {
        this.byteBuf.writeByte((int)b);
        return this;
    }

    @Override
    public NettyDataBuffer write(byte[] source) {
        this.byteBuf.writeBytes(source);
        return this;
    }

    @Override
    public NettyDataBuffer write(byte[] source, int offset, int length) {
        this.byteBuf.writeBytes(source, offset, length);
        return this;
    }

    @Override
    public NettyDataBuffer write(DataBuffer ... buffers) {
        if (!ObjectUtils.isEmpty(buffers)) {
            if (NettyDataBuffer.hasNettyDataBuffers(buffers)) {
                ByteBuf[] nativeBuffers = new ByteBuf[buffers.length];
                for (int i = 0; i < buffers.length; ++i) {
                    nativeBuffers[i] = ((NettyDataBuffer)buffers[i]).getNativeBuffer();
                }
                this.write(nativeBuffers);
            } else {
                ByteBuffer[] byteBuffers = new ByteBuffer[buffers.length];
                for (int i = 0; i < buffers.length; ++i) {
                    byteBuffers[i] = buffers[i].asByteBuffer();
                }
                this.write(byteBuffers);
            }
        }
        return this;
    }

    private static boolean hasNettyDataBuffers(DataBuffer[] buffers) {
        for (DataBuffer buffer : buffers) {
            if (buffer instanceof NettyDataBuffer) continue;
            return false;
        }
        return true;
    }

    @Override
    public NettyDataBuffer write(ByteBuffer ... buffers) {
        if (!ObjectUtils.isEmpty(buffers)) {
            for (ByteBuffer buffer : buffers) {
                this.byteBuf.writeBytes(buffer);
            }
        }
        return this;
    }

    public NettyDataBuffer write(ByteBuf ... byteBufs) {
        if (!ObjectUtils.isEmpty(byteBufs)) {
            for (ByteBuf byteBuf : byteBufs) {
                this.byteBuf.writeBytes(byteBuf);
            }
        }
        return this;
    }

    @Override
    public DataBuffer write(CharSequence charSequence, Charset charset) {
        Assert.notNull((Object)charSequence, "CharSequence must not be null");
        Assert.notNull((Object)charset, "Charset must not be null");
        if (StandardCharsets.UTF_8.equals(charset)) {
            ByteBufUtil.writeUtf8((ByteBuf)this.byteBuf, (CharSequence)charSequence);
        } else if (StandardCharsets.US_ASCII.equals(charset)) {
            ByteBufUtil.writeAscii((ByteBuf)this.byteBuf, (CharSequence)charSequence);
        } else {
            return PooledDataBuffer.super.write(charSequence, charset);
        }
        return this;
    }

    @Override
    public NettyDataBuffer slice(int index, int length) {
        ByteBuf slice = this.byteBuf.slice(index, length);
        return new NettyDataBuffer(slice, this.dataBufferFactory);
    }

    @Override
    public NettyDataBuffer retainedSlice(int index, int length) {
        ByteBuf slice = this.byteBuf.retainedSlice(index, length);
        return new NettyDataBuffer(slice, this.dataBufferFactory);
    }

    @Override
    public ByteBuffer asByteBuffer() {
        return this.byteBuf.nioBuffer();
    }

    @Override
    public ByteBuffer asByteBuffer(int index, int length) {
        return this.byteBuf.nioBuffer(index, length);
    }

    @Override
    public InputStream asInputStream() {
        return new ByteBufInputStream(this.byteBuf);
    }

    @Override
    public InputStream asInputStream(boolean releaseOnClose) {
        return new ByteBufInputStream(this.byteBuf, releaseOnClose);
    }

    @Override
    public OutputStream asOutputStream() {
        return new ByteBufOutputStream(this.byteBuf);
    }

    @Override
    public String toString(Charset charset) {
        Assert.notNull((Object)charset, "Charset must not be null");
        return this.byteBuf.toString(charset);
    }

    @Override
    public String toString(int index, int length, Charset charset) {
        Assert.notNull((Object)charset, "Charset must not be null");
        return this.byteBuf.toString(index, length, charset);
    }

    @Override
    public boolean isAllocated() {
        return this.byteBuf.refCnt() > 0;
    }

    @Override
    public PooledDataBuffer retain() {
        return new NettyDataBuffer(this.byteBuf.retain(), this.dataBufferFactory);
    }

    @Override
    public PooledDataBuffer touch(Object hint) {
        this.byteBuf.touch(hint);
        return this;
    }

    @Override
    public boolean release() {
        return this.byteBuf.release();
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof NettyDataBuffer && this.byteBuf.equals((Object)((NettyDataBuffer)other).byteBuf);
    }

    public int hashCode() {
        return this.byteBuf.hashCode();
    }

    public String toString() {
        return this.byteBuf.toString();
    }
}

