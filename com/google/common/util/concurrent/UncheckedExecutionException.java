/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public class UncheckedExecutionException
extends RuntimeException {
    private static final long serialVersionUID = 0L;

    protected UncheckedExecutionException() {
    }

    protected UncheckedExecutionException(@Nullable String message) {
        super(message);
    }

    public UncheckedExecutionException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    public UncheckedExecutionException(@Nullable Throwable cause) {
        super(cause);
    }
}

