/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.IOException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.Nullable;

public class NestedIOException
extends IOException {
    public NestedIOException(String msg) {
        super(msg);
    }

    public NestedIOException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

    @Override
    @Nullable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), this.getCause());
    }

    static {
        NestedExceptionUtils.class.getName();
    }
}

