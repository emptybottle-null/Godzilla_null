/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package org.springframework.core.codec;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.CodecException;
import org.springframework.core.codec.DecodingException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Decoder<T> {
    public boolean canDecode(ResolvableType var1, @Nullable MimeType var2);

    public Flux<T> decode(Publisher<DataBuffer> var1, ResolvableType var2, @Nullable MimeType var3, @Nullable Map<String, Object> var4);

    public Mono<T> decodeToMono(Publisher<DataBuffer> var1, ResolvableType var2, @Nullable MimeType var3, @Nullable Map<String, Object> var4);

    @Nullable
    default public T decode(DataBuffer buffer, ResolvableType targetType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) throws DecodingException {
        Throwable failure;
        CompletableFuture future = this.decodeToMono((Publisher<DataBuffer>)Mono.just((Object)buffer), targetType, mimeType, hints).toFuture();
        Assert.state(future.isDone(), "DataBuffer decoding should have completed.");
        try {
            return future.get();
        } catch (ExecutionException ex) {
            failure = ex.getCause();
        } catch (InterruptedException ex) {
            failure = ex;
        }
        throw failure instanceof CodecException ? (CodecException)failure : new DecodingException("Failed to decode: " + failure.getMessage(), failure);
    }

    public List<MimeType> getDecodableMimeTypes();

    default public List<MimeType> getDecodableMimeTypes(ResolvableType targetType) {
        return this.canDecode(targetType, null) ? this.getDecodableMimeTypes() : Collections.emptyList();
    }
}

