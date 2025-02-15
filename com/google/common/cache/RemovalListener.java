/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.RemovalNotification;

@FunctionalInterface
@GwtCompatible
public interface RemovalListener<K, V> {
    public void onRemoval(RemovalNotification<K, V> var1);
}

