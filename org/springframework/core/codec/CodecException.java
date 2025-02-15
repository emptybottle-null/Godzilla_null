/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.codec;

import org.springframework.core.NestedRuntimeException;
import org.springframework.lang.Nullable;

public class CodecException
extends NestedRuntimeException {
    public CodecException(String msg) {
        super(msg);
    }

    public CodecException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}

