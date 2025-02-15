/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public class ComputationException
extends RuntimeException {
    private static final long serialVersionUID = 0L;

    public ComputationException(@Nullable Throwable cause) {
        super(cause);
    }
}

