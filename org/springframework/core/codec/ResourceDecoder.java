/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  reactor.core.publisher.Flux
 */
package org.springframework.core.codec;

import java.io.ByteArrayInputStream;
import java.util.Map;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDataBufferDecoder;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

public class ResourceDecoder
extends AbstractDataBufferDecoder<Resource> {
    public static String FILENAME_HINT = ResourceDecoder.class.getName() + ".filename";

    public ResourceDecoder() {
        super(MimeTypeUtils.ALL);
    }

    @Override
    public boolean canDecode(ResolvableType elementType, @Nullable MimeType mimeType) {
        return Resource.class.isAssignableFrom(elementType.toClass()) && super.canDecode(elementType, mimeType);
    }

    @Override
    public Flux<Resource> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        return Flux.from(this.decodeToMono(inputStream, elementType, mimeType, hints));
    }

    @Override
    public Resource decode(DataBuffer dataBuffer, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        String filename;
        final byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(Hints.getLogPrefix(hints) + "Read " + bytes.length + " bytes");
        }
        Class<?> clazz = elementType.toClass();
        String string = filename = hints != null ? (String)hints.get(FILENAME_HINT) : null;
        if (clazz == InputStreamResource.class) {
            return new InputStreamResource(new ByteArrayInputStream(bytes)){

                @Override
                public String getFilename() {
                    return filename;
                }

                @Override
                public long contentLength() {
                    return bytes.length;
                }
            };
        }
        if (Resource.class.isAssignableFrom(clazz)) {
            return new ByteArrayResource(bytes){

                @Override
                public String getFilename() {
                    return filename;
                }
            };
        }
        throw new IllegalStateException("Unsupported resource class: " + clazz);
    }
}

