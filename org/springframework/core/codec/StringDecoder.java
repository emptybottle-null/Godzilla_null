/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package org.springframework.core.codec;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDataBufferDecoder;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.LimitedDataBufferList;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class StringDecoder
extends AbstractDataBufferDecoder<String> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final List<String> DEFAULT_DELIMITERS = Arrays.asList("\r\n", "\n");
    private final List<String> delimiters;
    private final boolean stripDelimiter;
    private Charset defaultCharset = DEFAULT_CHARSET;
    private final ConcurrentMap<Charset, byte[][]> delimitersCache = new ConcurrentHashMap<Charset, byte[][]>();

    private StringDecoder(List<String> delimiters, boolean stripDelimiter, MimeType ... mimeTypes) {
        super(mimeTypes);
        Assert.notEmpty(delimiters, "'delimiters' must not be empty");
        this.delimiters = new ArrayList<String>(delimiters);
        this.stripDelimiter = stripDelimiter;
    }

    public void setDefaultCharset(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public Charset getDefaultCharset() {
        return this.defaultCharset;
    }

    @Override
    public boolean canDecode(ResolvableType elementType, @Nullable MimeType mimeType) {
        return elementType.resolve() == String.class && super.canDecode(elementType, mimeType);
    }

    @Override
    public Flux<String> decode(Publisher<DataBuffer> input, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        byte[][] delimiterBytes = this.getDelimiterBytes(mimeType);
        LimitedDataBufferList chunks = new LimitedDataBufferList(this.getMaxInMemorySize());
        DataBufferUtils.Matcher matcher = DataBufferUtils.matcher(delimiterBytes);
        return Flux.from(input).concatMapIterable(buffer -> this.processDataBuffer((DataBuffer)buffer, matcher, chunks)).concatWith((Publisher)Mono.defer(() -> {
            if (chunks.isEmpty()) {
                return Mono.empty();
            }
            DataBuffer lastBuffer = ((DataBuffer)chunks.get(0)).factory().join(chunks);
            chunks.clear();
            return Mono.just((Object)lastBuffer);
        })).doOnTerminate(chunks::releaseAndClear).doOnDiscard(PooledDataBuffer.class, PooledDataBuffer::release).map(buffer -> this.decode((DataBuffer)buffer, elementType, mimeType, (Map)hints));
    }

    private byte[][] getDelimiterBytes(@Nullable MimeType mimeType) {
        return this.delimitersCache.computeIfAbsent(this.getCharset(mimeType), charset -> {
            byte[][] result = new byte[this.delimiters.size()][];
            for (int i = 0; i < this.delimiters.size(); ++i) {
                result[i] = this.delimiters.get(i).getBytes((Charset)charset);
            }
            return result;
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Collection<DataBuffer> processDataBuffer(DataBuffer buffer, DataBufferUtils.Matcher matcher, LimitedDataBufferList chunks) {
        try {
            ArrayList<DataBuffer> result = null;
            do {
                int endIndex;
                if ((endIndex = matcher.match(buffer)) == -1) {
                    chunks.add(buffer);
                    DataBufferUtils.retain(buffer);
                    break;
                }
                int startIndex = buffer.readPosition();
                int length = endIndex - startIndex + 1;
                DataBuffer slice = buffer.retainedSlice(startIndex, length);
                ArrayList<DataBuffer> arrayList = result = result != null ? result : new ArrayList<DataBuffer>();
                if (chunks.isEmpty()) {
                    if (this.stripDelimiter) {
                        slice.writePosition(slice.writePosition() - matcher.delimiter().length);
                    }
                    result.add(slice);
                } else {
                    chunks.add(slice);
                    DataBuffer joined = buffer.factory().join(chunks);
                    if (this.stripDelimiter) {
                        joined.writePosition(joined.writePosition() - matcher.delimiter().length);
                    }
                    result.add(joined);
                    chunks.clear();
                }
                buffer.readPosition(endIndex + 1);
            } while (buffer.readableByteCount() > 0);
            ArrayList<DataBuffer> arrayList = result != null ? result : Collections.emptyList();
            return arrayList;
        } finally {
            DataBufferUtils.release(buffer);
        }
    }

    @Override
    public String decode(DataBuffer dataBuffer, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        Charset charset = this.getCharset(mimeType);
        CharBuffer charBuffer = charset.decode(dataBuffer.asByteBuffer());
        DataBufferUtils.release(dataBuffer);
        String value = charBuffer.toString();
        LogFormatUtils.traceDebug(this.logger, traceOn -> {
            String formatted = LogFormatUtils.formatValue(value, traceOn == false);
            return Hints.getLogPrefix(hints) + "Decoded " + formatted;
        });
        return value;
    }

    private Charset getCharset(@Nullable MimeType mimeType) {
        if (mimeType != null && mimeType.getCharset() != null) {
            return mimeType.getCharset();
        }
        return this.getDefaultCharset();
    }

    @Deprecated
    public static StringDecoder textPlainOnly(boolean stripDelimiter) {
        return StringDecoder.textPlainOnly();
    }

    public static StringDecoder textPlainOnly() {
        return StringDecoder.textPlainOnly(DEFAULT_DELIMITERS, true);
    }

    public static StringDecoder textPlainOnly(List<String> delimiters, boolean stripDelimiter) {
        return new StringDecoder(delimiters, stripDelimiter, new MimeType("text", "plain", DEFAULT_CHARSET));
    }

    @Deprecated
    public static StringDecoder allMimeTypes(boolean stripDelimiter) {
        return StringDecoder.allMimeTypes();
    }

    public static StringDecoder allMimeTypes() {
        return StringDecoder.allMimeTypes(DEFAULT_DELIMITERS, true);
    }

    public static StringDecoder allMimeTypes(List<String> delimiters, boolean stripDelimiter) {
        return new StringDecoder(delimiters, stripDelimiter, new MimeType("text", "plain", DEFAULT_CHARSET), MimeTypeUtils.ALL);
    }
}

