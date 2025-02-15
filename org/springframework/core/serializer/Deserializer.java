/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface Deserializer<T> {
    public T deserialize(InputStream var1) throws IOException;

    default public T deserializeFromByteArray(byte[] serialized) throws IOException {
        return this.deserialize(new ByteArrayInputStream(serialized));
    }
}

