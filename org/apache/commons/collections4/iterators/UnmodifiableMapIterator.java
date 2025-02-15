/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableMapIterator<K, V>
implements MapIterator<K, V>,
Unmodifiable {
    private final MapIterator<? extends K, ? extends V> iterator;

    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<? extends K, ? extends V> iterator) {
        if (iterator == null) {
            throw new NullPointerException("MapIterator must not be null");
        }
        if (iterator instanceof Unmodifiable) {
            MapIterator<? extends K, ? extends V> tmpIterator = iterator;
            return tmpIterator;
        }
        return new UnmodifiableMapIterator<K, V>(iterator);
    }

    private UnmodifiableMapIterator(MapIterator<? extends K, ? extends V> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public K next() {
        return this.iterator.next();
    }

    @Override
    public K getKey() {
        return this.iterator.getKey();
    }

    @Override
    public V getValue() {
        return this.iterator.getValue();
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}

