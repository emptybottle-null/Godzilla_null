/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 */
package org.springframework.core.codec;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;

public interface Encoder<T> {
    public boolean canEncode(ResolvableType var1, @Nullable MimeType var2);

    public Flux<DataBuffer> encode(Publisher<? extends T> var1, DataBufferFactory var2, ResolvableType var3, @Nullable MimeType var4, @Nullable Map<String, Object> var5);

    default public DataBuffer encodeValue(T value, DataBufferFactory bufferFactory, ResolvableType valueType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        throw new UnsupportedOperationException();
    }

    public List<MimeType> getEncodableMimeTypes();

    default public List<MimeType> getEncodableMimeTypes(ResolvableType elementType) {
        return this.canEncode(elementType, null) ? this.getEncodableMimeTypes() : Collections.emptyList();
    }
}

