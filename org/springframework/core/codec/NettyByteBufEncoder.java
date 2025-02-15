/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  reactor.core.publisher.Flux
 */
package org.springframework.core.codec;

import io.netty.buffer.ByteBuf;
import java.util.Map;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

public class NettyByteBufEncoder
extends AbstractEncoder<ByteBuf> {
    public NettyByteBufEncoder() {
        super(MimeTypeUtils.ALL);
    }

    @Override
    public boolean canEncode(ResolvableType type, @Nullable MimeType mimeType) {
        Class<?> clazz = type.toClass();
        return super.canEncode(type, mimeType) && ByteBuf.class.isAssignableFrom(clazz);
    }

    @Override
    public Flux<DataBuffer> encode(Publisher<? extends ByteBuf> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return Flux.from(inputStream).map(byteBuffer -> this.encodeValue((ByteBuf)byteBuffer, bufferFactory, elementType, mimeType, hints));
    }

    @Override
    public DataBuffer encodeValue(ByteBuf byteBuf, DataBufferFactory bufferFactory, ResolvableType valueType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        if (this.logger.isDebugEnabled() && !Hints.isLoggingSuppressed(hints)) {
            String logPrefix = Hints.getLogPrefix(hints);
            this.logger.debug(logPrefix + "Writing " + byteBuf.readableBytes() + " bytes");
        }
        if (bufferFactory instanceof NettyDataBufferFactory) {
            return ((NettyDataBufferFactory)bufferFactory).wrap(byteBuf);
        }
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        byteBuf.release();
        return bufferFactory.wrap(bytes);
    }
}

