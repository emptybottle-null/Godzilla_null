/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;

public class AbstractMapIteratorDecorator<K, V>
implements MapIterator<K, V> {
    private final MapIterator<K, V> iterator;

    public AbstractMapIteratorDecorator(MapIterator<K, V> iterator) {
        if (iterator == null) {
            throw new NullPointerException("MapIterator must not be null");
        }
        this.iterator = iterator;
    }

    protected MapIterator<K, V> getMapIterator() {
        return this.iterator;
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
    public void remove() {
        this.iterator.remove();
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
    public V setValue(V obj) {
        return this.iterator.setValue(obj);
    }
}

