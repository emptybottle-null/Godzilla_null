/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledFuture;

@Beta
@GwtCompatible
public interface ListenableScheduledFuture<V>
extends ScheduledFuture<V>,
ListenableFuture<V> {
}

