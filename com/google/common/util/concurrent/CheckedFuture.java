/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Deprecated
@Beta
@CanIgnoreReturnValue
@GwtCompatible
public interface CheckedFuture<V, X extends Exception>
extends ListenableFuture<V> {
    public V checkedGet() throws X;

    public V checkedGet(long var1, TimeUnit var3) throws TimeoutException, X;
}

