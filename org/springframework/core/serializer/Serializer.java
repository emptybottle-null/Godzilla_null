/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
public interface Serializer<T> {
    public void serialize(T var1, OutputStream var2) throws IOException;

    default public byte[] serializeToByteArray(T object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        this.serialize(object, out);
        return out.toByteArray();
    }
}

