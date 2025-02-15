/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface FutureCallback<V> {
    public void onSuccess(@Nullable V var1);

    public void onFailure(Throwable var1);
}

