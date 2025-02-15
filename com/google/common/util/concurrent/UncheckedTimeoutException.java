/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public class UncheckedTimeoutException
extends RuntimeException {
    private static final long serialVersionUID = 0L;

    public UncheckedTimeoutException() {
    }

    public UncheckedTimeoutException(@Nullable String message) {
        super(message);
    }

    public UncheckedTimeoutException(@Nullable Throwable cause) {
        super(cause);
    }

    public UncheckedTimeoutException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}

