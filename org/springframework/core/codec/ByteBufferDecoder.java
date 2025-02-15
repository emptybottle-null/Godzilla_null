/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.codec;

import java.nio.ByteBuffer;
import java.util.Map;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDataBufferDecoder;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

public class ByteBufferDecoder
extends AbstractDataBufferDecoder<ByteBuffer> {
    public ByteBufferDecoder() {
        super(MimeTypeUtils.ALL);
    }

    @Override
    public boolean canDecode(ResolvableType elementType, @Nullable MimeType mimeType) {
        return ByteBuffer.class.isAssignableFrom(elementType.toClass()) && super.canDecode(elementType, mimeType);
    }

    @Override
    public ByteBuffer decode(DataBuffer dataBuffer, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        int byteCount = dataBuffer.readableByteCount();
        ByteBuffer copy = ByteBuffer.allocate(byteCount);
        copy.put(dataBuffer.asByteBuffer());
        copy.flip();
        DataBufferUtils.release(dataBuffer);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(Hints.getLogPrefix(hints) + "Read " + byteCount + " bytes");
        }
        return copy;
    }
}

