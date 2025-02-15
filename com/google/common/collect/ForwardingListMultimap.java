/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ForwardingMultimap;
import com.google.common.collect.ListMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class ForwardingListMultimap<K, V>
extends ForwardingMultimap<K, V>
implements ListMultimap<K, V> {
    protected ForwardingListMultimap() {
    }

    @Override
    protected abstract ListMultimap<K, V> delegate();

    @Override
    public List<V> get(@Nullable K key) {
        return this.delegate().get((Object)key);
    }

    @Override
    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object key) {
        return this.delegate().removeAll(key);
    }

    @Override
    @CanIgnoreReturnValue
    public List<V> replaceValues(K key, Iterable<? extends V> values) {
        return this.delegate().replaceValues((Object)key, (Iterable)values);
    }
}

