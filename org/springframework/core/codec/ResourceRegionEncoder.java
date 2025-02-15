/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 *  reactor.core.publisher.Mono
 */
package org.springframework.core.codec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.OptionalLong;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.codec.EncodingException;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResourceRegionEncoder
extends AbstractEncoder<ResourceRegion> {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final String BOUNDARY_STRING_HINT = ResourceRegionEncoder.class.getName() + ".boundaryString";
    private final int bufferSize;

    public ResourceRegionEncoder() {
        this(4096);
    }

    public ResourceRegionEncoder(int bufferSize) {
        super(MimeTypeUtils.APPLICATION_OCTET_STREAM, MimeTypeUtils.ALL);
        Assert.isTrue(bufferSize > 0, "'bufferSize' must be larger than 0");
        this.bufferSize = bufferSize;
    }

    @Override
    public boolean canEncode(ResolvableType elementType, @Nullable MimeType mimeType) {
        return super.canEncode(elementType, mimeType) && ResourceRegion.class.isAssignableFrom(elementType.toClass());
    }

    @Override
    public Flux<DataBuffer> encode(Publisher<? extends ResourceRegion> input, DataBufferFactory bufferFactory, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        Assert.notNull(input, "'inputStream' must not be null");
        Assert.notNull((Object)bufferFactory, "'bufferFactory' must not be null");
        Assert.notNull((Object)elementType, "'elementType' must not be null");
        if (input instanceof Mono) {
            return Mono.from(input).flatMapMany(region -> {
                if (!region.getResource().isReadable()) {
                    return Flux.error((Throwable)new EncodingException("Resource " + region.getResource() + " is not readable"));
                }
                return this.writeResourceRegion((ResourceRegion)region, bufferFactory, hints);
            });
        }
        String boundaryString = (String)Hints.getRequiredHint(hints, BOUNDARY_STRING_HINT);
        byte[] startBoundary = this.toAsciiBytes("\r\n--" + boundaryString + "\r\n");
        byte[] contentType = mimeType != null ? this.toAsciiBytes("Content-Type: " + mimeType + "\r\n") : new byte[]{};
        return Flux.from(input).concatMap(region -> {
            if (!region.getResource().isReadable()) {
                return Flux.error((Throwable)new EncodingException("Resource " + region.getResource() + " is not readable"));
            }
            Flux prefix = Flux.just((Object[])new DataBuffer[]{bufferFactory.wrap(startBoundary), bufferFactory.wrap(contentType), bufferFactory.wrap(this.getContentRangeHeader((ResourceRegion)region))});
            return prefix.concatWith(this.writeResourceRegion((ResourceRegion)region, bufferFactory, hints));
        }).concatWithValues((Object[])new DataBuffer[]{this.getRegionSuffix(bufferFactory, boundaryString)});
    }

    private Flux<DataBuffer> writeResourceRegion(ResourceRegion region, DataBufferFactory bufferFactory, @Nullable Map<String, Object> hints) {
        Resource resource = region.getResource();
        long position = region.getPosition();
        long count = region.getCount();
        if (this.logger.isDebugEnabled() && !Hints.isLoggingSuppressed(hints)) {
            this.logger.debug(Hints.getLogPrefix(hints) + "Writing region " + position + "-" + (position + count) + " of [" + resource + "]");
        }
        Flux in = DataBufferUtils.read(resource, position, bufferFactory, this.bufferSize);
        if (this.logger.isDebugEnabled()) {
            in = in.doOnNext(buffer -> Hints.touchDataBuffer(buffer, hints, this.logger));
        }
        return DataBufferUtils.takeUntilByteCount(in, count);
    }

    private DataBuffer getRegionSuffix(DataBufferFactory bufferFactory, String boundaryString) {
        byte[] endBoundary = this.toAsciiBytes("\r\n--" + boundaryString + "--");
        return bufferFactory.wrap(endBoundary);
    }

    private byte[] toAsciiBytes(String in) {
        return in.getBytes(StandardCharsets.US_ASCII);
    }

    private byte[] getContentRangeHeader(ResourceRegion region) {
        long start = region.getPosition();
        long end = start + region.getCount() - 1L;
        OptionalLong contentLength = this.contentLength(region.getResource());
        if (contentLength.isPresent()) {
            long length = contentLength.getAsLong();
            return this.toAsciiBytes("Content-Range: bytes " + start + '-' + end + '/' + length + "\r\n\r\n");
        }
        return this.toAsciiBytes("Content-Range: bytes " + start + '-' + end + "\r\n\r\n");
    }

    private OptionalLong contentLength(Resource resource) {
        if (InputStreamResource.class != resource.getClass()) {
            try {
                return OptionalLong.of(resource.contentLength());
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        return OptionalLong.empty();
    }
}

