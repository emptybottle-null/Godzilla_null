/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;

public class AbstractOrderedMapIteratorDecorator<K, V>
implements OrderedMapIterator<K, V> {
    private final OrderedMapIterator<K, V> iterator;

    public AbstractOrderedMapIteratorDecorator(OrderedMapIterator<K, V> iterator) {
        if (iterator == null) {
            throw new NullPointerException("OrderedMapIterator must not be null");
        }
        this.iterator = iterator;
    }

    protected OrderedMapIterator<K, V> getOrderedMapIterator() {
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
    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    @Override
    public K previous() {
        return this.iterator.previous();
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

