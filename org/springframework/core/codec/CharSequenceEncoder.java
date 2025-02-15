/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 */
package org.springframework.core.codec;

import java.nio.charset.Charset;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.codec.EncodingException;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

public final class CharSequenceEncoder
extends AbstractEncoder<CharSequence> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final ConcurrentMap<Charset, Float> charsetToMaxBytesPerChar = new ConcurrentHashMap<Charset, Float>(3);

    private CharSequenceEncoder(MimeType ... mimeTypes) {
        super(mimeTypes);
    }

    @Override
    public boolean canEncode(ResolvableType elementType, @Nullable MimeType mimeType) {
        Class<?> clazz = elementType.toClass();
        return super.canEncode(elementType, mimeType) && CharSequence.class.isAssignableFrom(clazz);
    }

    @Override
    public Flux<DataBuffer> encode(Publisher<? extends CharSequence> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return Flux.from(inputStream).map(charSequence -> this.encodeValue((CharSequence)charSequence, bufferFactory, elementType, mimeType, hints));
    }

    @Override
    public DataBuffer encodeValue(CharSequence charSequence, DataBufferFactory bufferFactory, ResolvableType valueType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        if (!Hints.isLoggingSuppressed(hints)) {
            LogFormatUtils.traceDebug(this.logger, traceOn -> {
                String formatted = LogFormatUtils.formatValue(charSequence, traceOn == false);
                return Hints.getLogPrefix(hints) + "Writing " + formatted;
            });
        }
        boolean release = true;
        Charset charset = this.getCharset(mimeType);
        int capacity = this.calculateCapacity(charSequence, charset);
        DataBuffer dataBuffer = bufferFactory.allocateBuffer(capacity);
        try {
            dataBuffer.write(charSequence, charset);
            release = false;
        } catch (CoderMalfunctionError ex) {
            throw new EncodingException("String encoding error: " + ex.getMessage(), ex);
        } finally {
            if (release) {
                DataBufferUtils.release(dataBuffer);
            }
        }
        return dataBuffer;
    }

    int calculateCapacity(CharSequence sequence, Charset charset) {
        float maxBytesPerChar = this.charsetToMaxBytesPerChar.computeIfAbsent(charset, cs -> Float.valueOf(cs.newEncoder().maxBytesPerChar())).floatValue();
        float maxBytesForSequence = (float)sequence.length() * maxBytesPerChar;
        return (int)Math.ceil(maxBytesForSequence);
    }

    private Charset getCharset(@Nullable MimeType mimeType) {
        if (mimeType != null && mimeType.getCharset() != null) {
            return mimeType.getCharset();
        }
        return DEFAULT_CHARSET;
    }

    public static CharSequenceEncoder textPlainOnly() {
        return new CharSequenceEncoder(new MimeType("text", "plain", DEFAULT_CHARSET));
    }

    public static CharSequenceEncoder allMimeTypes() {
        return new CharSequenceEncoder(new MimeType("text", "plain", DEFAULT_CHARSET), MimeTypeUtils.ALL);
    }
}

