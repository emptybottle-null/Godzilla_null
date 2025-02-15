/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ForwardingSetMultimap;
import com.google.common.collect.SortedSetMultimap;
import java.util.Comparator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class ForwardingSortedSetMultimap<K, V>
extends ForwardingSetMultimap<K, V>
implements SortedSetMultimap<K, V> {
    protected ForwardingSortedSetMultimap() {
    }

    @Override
    protected abstract SortedSetMultimap<K, V> delegate();

    @Override
    public SortedSet<V> get(@Nullable K key) {
        return this.delegate().get((Object)key);
    }

    @Override
    public SortedSet<V> removeAll(@Nullable Object key) {
        return this.delegate().removeAll(key);
    }

    @Override
    public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
        return this.delegate().replaceValues((Object)key, (Iterable)values);
    }

    @Override
    public Comparator<? super V> valueComparator() {
        return this.delegate().valueComparator();
    }
}

