/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.core.NestedIOException;
import org.springframework.core.serializer.Deserializer;
import org.springframework.lang.Nullable;

public class DefaultDeserializer
implements Deserializer<Object> {
    @Nullable
    private final ClassLoader classLoader;

    public DefaultDeserializer() {
        this.classLoader = null;
    }

    public DefaultDeserializer(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException {
        ConfigurableObjectInputStream objectInputStream = new ConfigurableObjectInputStream(inputStream, this.classLoader);
        try {
            return objectInputStream.readObject();
        } catch (ClassNotFoundException ex) {
            throw new NestedIOException("Failed to deserialize object type", ex);
        }
    }
}

