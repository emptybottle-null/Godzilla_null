/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.qual.Nullable;

@FunctionalInterface
@GwtCompatible
public interface AsyncFunction<I, O> {
    public ListenableFuture<O> apply(@Nullable I var1) throws Exception;
}

