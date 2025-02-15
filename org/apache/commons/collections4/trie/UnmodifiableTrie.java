/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;

public class UnmodifiableTrie<K, V>
implements Trie<K, V>,
Serializable,
Unmodifiable {
    private static final long serialVersionUID = -7156426030315945159L;
    private final Trie<K, V> delegate;

    public static <K, V> Trie<K, V> unmodifiableTrie(Trie<K, ? extends V> trie) {
        if (trie instanceof Unmodifiable) {
            Trie<K, ? extends V> tmpTrie = trie;
            return tmpTrie;
        }
        return new UnmodifiableTrie<K, V>(trie);
    }

    public UnmodifiableTrie(Trie<K, ? extends V> trie) {
        if (trie == null) {
            throw new NullPointerException("Trie must not be null");
        }
        Trie<K, ? extends V> tmpTrie = trie;
        this.delegate = tmpTrie;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(this.delegate.entrySet());
    }

    @Override
    public Set<K> keySet() {
        return Collections.unmodifiableSet(this.delegate.keySet());
    }

    @Override
    public Collection<V> values() {
        return Collections.unmodifiableCollection(this.delegate.values());
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.delegate.get(key);
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public K firstKey() {
        return this.delegate.firstKey();
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return Collections.unmodifiableSortedMap(this.delegate.headMap(toKey));
    }

    @Override
    public K lastKey() {
        return this.delegate.lastKey();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return Collections.unmodifiableSortedMap(this.delegate.subMap(fromKey, toKey));
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return Collections.unmodifiableSortedMap(this.delegate.tailMap(fromKey));
    }

    @Override
    public SortedMap<K, V> prefixMap(K key) {
        return Collections.unmodifiableSortedMap(this.delegate.prefixMap(key));
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.delegate.comparator();
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        MapIterator it = this.delegate.mapIterator();
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(it);
    }

    @Override
    public K nextKey(K key) {
        return this.delegate.nextKey(key);
    }

    @Override
    public K previousKey(K key) {
        return this.delegate.previousKey(key);
    }

    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    public String toString() {
        return this.delegate.toString();
    }
}

