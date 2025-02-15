/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.lang.Nullable;

public abstract class NestedExceptionUtils {
    @Nullable
    public static String buildMessage(@Nullable String message, @Nullable Throwable cause) {
        if (cause == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(64);
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(cause);
        return sb.toString();
    }

    @Nullable
    public static Throwable getRootCause(@Nullable Throwable original) {
        if (original == null) {
            return null;
        }
        Throwable rootCause = null;
        for (Throwable cause = original.getCause(); cause != null && cause != rootCause; cause = cause.getCause()) {
            rootCause = cause;
        }
        return rootCause;
    }

    public static Throwable getMostSpecificCause(Throwable original) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(original);
        return rootCause != null ? rootCause : original;
    }
}

