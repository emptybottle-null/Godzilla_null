/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.IterableSortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;

public abstract class AbstractSortedMapDecorator<K, V>
extends AbstractMapDecorator<K, V>
implements IterableSortedMap<K, V> {
    protected AbstractSortedMapDecorator() {
    }

    public AbstractSortedMapDecorator(SortedMap<K, V> map) {
        super(map);
    }

    @Override
    protected SortedMap<K, V> decorated() {
        return (SortedMap)super.decorated();
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.decorated().comparator();
    }

    @Override
    public K firstKey() {
        return this.decorated().firstKey();
    }

    @Override
    public K lastKey() {
        return this.decorated().lastKey();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return this.decorated().subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return this.decorated().headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return this.decorated().tailMap(fromKey);
    }

    @Override
    public K previousKey(K key) {
        SortedMap<K, V> headMap = this.headMap(key);
        return headMap.isEmpty() ? null : (K)headMap.lastKey();
    }

    @Override
    public K nextKey(K key) {
        Iterator<K> it = this.tailMap(key).keySet().iterator();
        it.next();
        return it.hasNext() ? (K)it.next() : null;
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        return new SortedMapIterator(this.entrySet());
    }

    protected static class SortedMapIterator<K, V>
    extends EntrySetToMapIteratorAdapter<K, V>
    implements OrderedMapIterator<K, V> {
        protected SortedMapIterator(Set<Map.Entry<K, V>> entrySet) {
            super(entrySet);
        }

        @Override
        public synchronized void reset() {
            super.reset();
            this.iterator = new ListIteratorWrapper(this.iterator);
        }

        @Override
        public boolean hasPrevious() {
            return ((ListIterator)this.iterator).hasPrevious();
        }

        @Override
        public K previous() {
            this.entry = (Map.Entry)((ListIterator)this.iterator).previous();
            return this.getKey();
        }
    }
}

