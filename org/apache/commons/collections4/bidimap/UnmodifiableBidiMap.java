/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableBidiMap<K, V>
extends AbstractBidiMapDecorator<K, V>
implements Unmodifiable {
    private UnmodifiableBidiMap<V, K> inverse;

    public static <K, V> BidiMap<K, V> unmodifiableBidiMap(BidiMap<? extends K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            BidiMap<? extends K, ? extends V> tmpMap = map;
            return tmpMap;
        }
        return new UnmodifiableBidiMap<K, V>(map);
    }

    private UnmodifiableBidiMap(BidiMap<? extends K, ? extends V> map) {
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
    public MapIterator<K, V> mapIterator() {
        MapIterator it = this.decorated().mapIterator();
        return UnmodifiableMapIterator.unmodifiableMapIterator(it);
    }

    @Override
    public synchronized BidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableBidiMap<K, V>(this.decorated().inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }
}

