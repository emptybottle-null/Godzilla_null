/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.function.BiFunction;

@GwtIncompatible
public abstract class ForwardingNavigableMap<K, V>
extends ForwardingSortedMap<K, V>
implements NavigableMap<K, V> {
    protected ForwardingNavigableMap() {
    }

    @Override
    protected abstract NavigableMap<K, V> delegate();

    @Override
    public Map.Entry<K, V> lowerEntry(K key) {
        return this.delegate().lowerEntry(key);
    }

    protected Map.Entry<K, V> standardLowerEntry(K key) {
        return this.headMap(key, false).lastEntry();
    }

    @Override
    public K lowerKey(K key) {
        return this.delegate().lowerKey(key);
    }

    protected K standardLowerKey(K key) {
        return Maps.keyOrNull(this.lowerEntry(key));
    }

    @Override
    public Map.Entry<K, V> floorEntry(K key) {
        return this.delegate().floorEntry(key);
    }

    protected Map.Entry<K, V> standardFloorEntry(K key) {
        return this.headMap(key, true).lastEntry();
    }

    @Override
    public K floorKey(K key) {
        return this.delegate().floorKey(key);
    }

    protected K standardFloorKey(K key) {
        return Maps.keyOrNull(this.floorEntry(key));
    }

    @Override
    public Map.Entry<K, V> ceilingEntry(K key) {
        return this.delegate().ceilingEntry(key);
    }

    protected Map.Entry<K, V> standardCeilingEntry(K key) {
        return this.tailMap(key, true).firstEntry();
    }

    @Override
    public K ceilingKey(K key) {
        return this.delegate().ceilingKey(key);
    }

    protected K standardCeilingKey(K key) {
        return Maps.keyOrNull(this.ceilingEntry(key));
    }

    @Override
    public Map.Entry<K, V> higherEntry(K key) {
        return this.delegate().higherEntry(key);
    }

    protected Map.Entry<K, V> standardHigherEntry(K key) {
        return this.tailMap(key, false).firstEntry();
    }

    @Override
    public K higherKey(K key) {
        return this.delegate().higherKey(key);
    }

    protected K standardHigherKey(K key) {
        return Maps.keyOrNull(this.higherEntry(key));
    }

    @Override
    public Map.Entry<K, V> firstEntry() {
        return this.delegate().firstEntry();
    }

    protected Map.Entry<K, V> standardFirstEntry() {
        return Iterables.getFirst(this.entrySet(), null);
    }

    protected K standardFirstKey() {
        Map.Entry<K, V> entry = this.firstEntry();
        if (entry == null) {
            throw new NoSuchElementException();
        }
        return entry.getKey();
    }

    @Override
    public Map.Entry<K, V> lastEntry() {
        return this.delegate().lastEntry();
    }

    protected Map.Entry<K, V> standardLastEntry() {
        return Iterables.getFirst(this.descendingMap().entrySet(), null);
    }

    protected K standardLastKey() {
        Map.Entry<K, V> entry = this.lastEntry();
        if (entry == null) {
            throw new NoSuchElementException();
        }
        return entry.getKey();
    }

    @Override
    public Map.Entry<K, V> pollFirstEntry() {
        return this.delegate().pollFirstEntry();
    }

    protected Map.Entry<K, V> standardPollFirstEntry() {
        return Iterators.pollNext(this.entrySet().iterator());
    }

    @Override
    public Map.Entry<K, V> pollLastEntry() {
        return this.delegate().pollLastEntry();
    }

    protected Map.Entry<K, V> standardPollLastEntry() {
        return Iterators.pollNext(this.descendingMap().entrySet().iterator());
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return this.delegate().descendingMap();
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return this.delegate().navigableKeySet();
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return this.delegate().descendingKeySet();
    }

    @Beta
    protected NavigableSet<K> standardDescendingKeySet() {
        return this.descendingMap().navigableKeySet();
    }

    @Override
    protected SortedMap<K, V> standardSubMap(K fromKey, K toKey) {
        return this.subMap(fromKey, true, toKey, false);
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return this.delegate().subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return this.delegate().headMap(toKey, inclusive);
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return this.delegate().tailMap(fromKey, inclusive);
    }

    protected SortedMap<K, V> standardHeadMap(K toKey) {
        return this.headMap(toKey, false);
    }

    protected SortedMap<K, V> standardTailMap(K fromKey) {
        return this.tailMap(fromKey, true);
    }

    @Beta
    protected class StandardNavigableKeySet
    extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet() {
            super(ForwardingNavigableMap.this);
        }
    }

    @Beta
    protected class StandardDescendingMap
    extends Maps.DescendingMap<K, V> {
        @Override
        NavigableMap<K, V> forward() {
            return ForwardingNavigableMap.this;
        }

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
            this.forward().replaceAll(function);
        }

        @Override
        protected Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>(){
                private Map.Entry<K, V> toRemove = null;
                private Map.Entry<K, V> nextOrNull = StandardDescendingMap.this.forward().lastEntry();

                @Override
                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                @Override
                public Map.Entry<K, V> next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    try {
                        Map.Entry entry = this.nextOrNull;
                        return entry;
                    } finally {
                        this.toRemove = this.nextOrNull;
                        this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                    }
                }

                @Override
                public void remove() {
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }
            };
        }
    }
}

