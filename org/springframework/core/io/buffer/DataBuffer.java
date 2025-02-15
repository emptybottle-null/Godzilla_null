/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.buffer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.function.IntPredicate;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.util.Assert;

public interface DataBuffer {
    public DataBufferFactory factory();

    public int indexOf(IntPredicate var1, int var2);

    public int lastIndexOf(IntPredicate var1, int var2);

    public int readableByteCount();

    public int writableByteCount();

    public int capacity();

    public DataBuffer capacity(int var1);

    default public DataBuffer ensureCapacity(int capacity) {
        return this;
    }

    public int readPosition();

    public DataBuffer readPosition(int var1);

    public int writePosition();

    public DataBuffer writePosition(int var1);

    public byte getByte(int var1);

    public byte read();

    public DataBuffer read(byte[] var1);

    public DataBuffer read(byte[] var1, int var2, int var3);

    public DataBuffer write(byte var1);

    public DataBuffer write(byte[] var1);

    public DataBuffer write(byte[] var1, int var2, int var3);

    public DataBuffer write(DataBuffer ... var1);

    public DataBuffer write(ByteBuffer ... var1);

    default public DataBuffer write(CharSequence charSequence, Charset charset) {
        Assert.notNull((Object)charSequence, "CharSequence must not be null");
        Assert.notNull((Object)charset, "Charset must not be null");
        if (charSequence.length() != 0) {
            CharsetEncoder charsetEncoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            CharBuffer inBuffer = CharBuffer.wrap(charSequence);
            int estimatedSize = (int)((float)inBuffer.remaining() * charsetEncoder.averageBytesPerChar());
            ByteBuffer outBuffer = this.ensureCapacity(estimatedSize).asByteBuffer(this.writePosition(), this.writableByteCount());
            while (true) {
                CoderResult cr;
                CoderResult coderResult = cr = inBuffer.hasRemaining() ? charsetEncoder.encode(inBuffer, outBuffer, true) : CoderResult.UNDERFLOW;
                if (cr.isUnderflow()) {
                    cr = charsetEncoder.flush(outBuffer);
                }
                if (cr.isUnderflow()) break;
                if (!cr.isOverflow()) continue;
                this.writePosition(this.writePosition() + outBuffer.position());
                int maximumSize = (int)((float)inBuffer.remaining() * charsetEncoder.maxBytesPerChar());
                this.ensureCapacity(maximumSize);
                outBuffer = this.asByteBuffer(this.writePosition(), this.writableByteCount());
            }
            this.writePosition(this.writePosition() + outBuffer.position());
        }
        return this;
    }

    public DataBuffer slice(int var1, int var2);

    default public DataBuffer retainedSlice(int index, int length) {
        return DataBufferUtils.retain(this.slice(index, length));
    }

    public ByteBuffer asByteBuffer();

    public ByteBuffer asByteBuffer(int var1, int var2);

    public InputStream asInputStream();

    public InputStream asInputStream(boolean var1);

    public OutputStream asOutputStream();

    default public String toString(Charset charset) {
        Assert.notNull((Object)charset, "Charset must not be null");
        return this.toString(this.readPosition(), this.readableByteCount(), charset);
    }

    public String toString(int var1, int var2, Charset var3);
}

