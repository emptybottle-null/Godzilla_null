/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package org.springframework.core.codec;

import java.util.Map;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDecoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractDataBufferDecoder<T>
extends AbstractDecoder<T> {
    private int maxInMemorySize = 262144;

    protected AbstractDataBufferDecoder(MimeType ... supportedMimeTypes) {
        super(supportedMimeTypes);
    }

    public void setMaxInMemorySize(int byteCount) {
        this.maxInMemorySize = byteCount;
    }

    public int getMaxInMemorySize() {
        return this.maxInMemorySize;
    }

    @Override
    public Flux<T> decode(Publisher<DataBuffer> input, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return Flux.from(input).map(buffer -> this.decodeDataBuffer((DataBuffer)buffer, elementType, mimeType, hints));
    }

    @Override
    public Mono<T> decodeToMono(Publisher<DataBuffer> input, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return DataBufferUtils.join(input, this.maxInMemorySize).map(buffer -> this.decodeDataBuffer((DataBuffer)buffer, elementType, mimeType, hints));
    }

    @Deprecated
    @Nullable
    protected T decodeDataBuffer(DataBuffer buffer, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return this.decode(buffer, elementType, mimeType, hints);
    }
}

