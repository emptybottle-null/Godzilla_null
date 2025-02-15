/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.serializer.support;

import org.springframework.core.NestedRuntimeException;

public class SerializationFailedException
extends NestedRuntimeException {
    public SerializationFailedException(String message) {
        super(message);
    }

    public SerializationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

