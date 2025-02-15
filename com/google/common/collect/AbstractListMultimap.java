/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.ListMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractListMultimap<K, V>
extends AbstractMapBasedMultimap<K, V>
implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    protected AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    @Override
    abstract List<V> createCollection();

    @Override
    List<V> createUnmodifiableEmptyCollection() {
        return Collections.emptyList();
    }

    @Override
    <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableList((List)collection);
    }

    @Override
    Collection<V> wrapCollection(K key, Collection<V> collection) {
        return this.wrapList(key, (List)collection, null);
    }

    @Override
    public List<V> get(@Nullable K key) {
        return (List)super.get(key);
    }

    @Override
    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object key) {
        return (List)super.removeAll(key);
    }

    @Override
    @CanIgnoreReturnValue
    public List<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        return (List)super.replaceValues(key, values);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean put(@Nullable K key, @Nullable V value) {
        return super.put(key, value);
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }
}

