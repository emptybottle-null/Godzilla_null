/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMultiValuedMap<K, V>
extends AbstractMultiValuedMapDecorator<K, V>
implements Unmodifiable {
    private static final long serialVersionUID = 20150612L;

    public static <K, V> UnmodifiableMultiValuedMap<K, V> unmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            return (UnmodifiableMultiValuedMap)map;
        }
        return new UnmodifiableMultiValuedMap<K, V>(map);
    }

    private UnmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    public Collection<V> remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeMapping(Object key, Object item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> get(K key) {
        return UnmodifiableCollection.unmodifiableCollection(this.decorated().get(key));
    }

    @Override
    public boolean put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(this.decorated().keySet());
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        return UnmodifiableCollection.unmodifiableCollection(this.decorated().entries());
    }

    @Override
    public MultiSet<K> keys() {
        return UnmodifiableMultiSet.unmodifiableMultiSet(this.decorated().keys());
    }

    @Override
    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(this.decorated().values());
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return UnmodifiableMap.unmodifiableMap(this.decorated().asMap());
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return UnmodifiableMapIterator.unmodifiableMapIterator(this.decorated().mapIterator());
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }
}

