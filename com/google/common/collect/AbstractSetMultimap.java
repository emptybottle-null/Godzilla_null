/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.SetMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractSetMultimap<K, V>
extends AbstractMapBasedMultimap<K, V>
implements SetMultimap<K, V> {
    private static final long serialVersionUID = 7431625294878419160L;

    protected AbstractSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    @Override
    abstract Set<V> createCollection();

    @Override
    Set<V> createUnmodifiableEmptyCollection() {
        return Collections.emptySet();
    }

    @Override
    <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableSet((Set)collection);
    }

    @Override
    Collection<V> wrapCollection(K key, Collection<V> collection) {
        return new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection);
    }

    @Override
    public Set<V> get(@Nullable K key) {
        return (Set)super.get(key);
    }

    @Override
    public Set<Map.Entry<K, V>> entries() {
        return (Set)super.entries();
    }

    @Override
    @CanIgnoreReturnValue
    public Set<V> removeAll(@Nullable Object key) {
        return (Set)super.removeAll(key);
    }

    @Override
    @CanIgnoreReturnValue
    public Set<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        return (Set)super.replaceValues(key, values);
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    @Override
    @CanIgnoreReturnValue
    public boolean put(@Nullable K key, @Nullable V value) {
        return super.put(key, value);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }
}

