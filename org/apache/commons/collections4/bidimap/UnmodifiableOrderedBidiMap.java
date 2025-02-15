/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableOrderedBidiMap<K, V>
extends AbstractOrderedBidiMapDecorator<K, V>
implements Unmodifiable {
    private UnmodifiableOrderedBidiMap<V, K> inverse;

    public static <K, V> OrderedBidiMap<K, V> unmodifiableOrderedBidiMap(OrderedBidiMap<? extends K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            OrderedBidiMap<? extends K, ? extends V> tmpMap = map;
            return tmpMap;
        }
        return new UnmodifiableOrderedBidiMap<K, V>(map);
    }

    private UnmodifiableOrderedBidiMap(OrderedBidiMap<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set set = super.entrySet();
        return UnmodifiableEntrySet.unmodifiableEntrySet(set);
    }

    @Override
    public Set<K> keySet() {
        Set set = super.keySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Set<V> values() {
        Collection set = super.values();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public K removeValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderedBidiMap<V, K> inverseBidiMap() {
        return this.inverseOrderedBidiMap();
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        MapIterator it = this.decorated().mapIterator();
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(it);
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableOrderedBidiMap<K, V>(this.decorated().inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }
}

