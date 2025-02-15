/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.AbstractSetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedSetMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractSortedSetMultimap<K, V>
extends AbstractSetMultimap<K, V>
implements SortedSetMultimap<K, V> {
    private static final long serialVersionUID = 430848587173315748L;

    protected AbstractSortedSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    @Override
    abstract SortedSet<V> createCollection();

    @Override
    SortedSet<V> createUnmodifiableEmptyCollection() {
        return this.unmodifiableCollectionSubclass((Collection)this.createCollection());
    }

    @Override
    <E> SortedSet<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        if (collection instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet)collection);
        }
        return Collections.unmodifiableSortedSet((SortedSet)collection);
    }

    @Override
    Collection<V> wrapCollection(K key, Collection<V> collection) {
        if (collection instanceof NavigableSet) {
            return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, null);
        }
        return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, null);
    }

    @Override
    public SortedSet<V> get(@Nullable K key) {
        return (SortedSet)super.get((Object)key);
    }

    @Override
    @CanIgnoreReturnValue
    public SortedSet<V> removeAll(@Nullable Object key) {
        return (SortedSet)super.removeAll(key);
    }

    @Override
    @CanIgnoreReturnValue
    public SortedSet<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        return (SortedSet)super.replaceValues((Object)key, (Iterable)values);
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }
}

