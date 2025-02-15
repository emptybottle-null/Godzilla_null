/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.util.Assert;

public class SerializingConverter
implements Converter<Object, byte[]> {
    private final Serializer<Object> serializer;

    public SerializingConverter() {
        this.serializer = new DefaultSerializer();
    }

    public SerializingConverter(Serializer<Object> serializer) {
        Assert.notNull(serializer, "Serializer must not be null");
        this.serializer = serializer;
    }

    @Override
    public byte[] convert(Object source) {
        try {
            return this.serializer.serializeToByteArray(source);
        } catch (Throwable ex) {
            throw new SerializationFailedException("Failed to serialize object using " + this.serializer.getClass().getSimpleName(), ex);
        }
    }
}

