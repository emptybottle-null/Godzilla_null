/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.Nullable;

public abstract class NestedRuntimeException
extends RuntimeException {
    private static final long serialVersionUID = 5439915454935047936L;

    public NestedRuntimeException(String msg) {
        super(msg);
    }

    public NestedRuntimeException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

    @Override
    @Nullable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), this.getCause());
    }

    @Nullable
    public Throwable getRootCause() {
        return NestedExceptionUtils.getRootCause(this);
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = this.getRootCause();
        return rootCause != null ? rootCause : this;
    }

    public boolean contains(@Nullable Class<?> exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = this.getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof NestedRuntimeException) {
            return ((NestedRuntimeException)cause).contains(exType);
        }
        while (cause != null) {
            if (exType.isInstance(cause)) {
                return true;
            }
            if (cause.getCause() == cause) break;
            cause = cause.getCause();
        }
        return false;
    }

    static {
        NestedExceptionUtils.class.getName();
    }
}

