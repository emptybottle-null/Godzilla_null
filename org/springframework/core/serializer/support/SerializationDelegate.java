/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.util.Assert;

public class SerializationDelegate
implements Serializer<Object>,
Deserializer<Object> {
    private final Serializer<Object> serializer;
    private final Deserializer<Object> deserializer;

    public SerializationDelegate(ClassLoader classLoader) {
        this.serializer = new DefaultSerializer();
        this.deserializer = new DefaultDeserializer(classLoader);
    }

    public SerializationDelegate(Serializer<Object> serializer, Deserializer<Object> deserializer) {
        Assert.notNull(serializer, "Serializer must not be null");
        Assert.notNull(deserializer, "Deserializer must not be null");
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public void serialize(Object object, OutputStream outputStream) throws IOException {
        this.serializer.serialize(object, outputStream);
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException {
        return this.deserializer.deserialize(inputStream);
    }
}

