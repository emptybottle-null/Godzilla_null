/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator;

public abstract class AbstractOrderedBidiMapDecorator<K, V>
extends AbstractBidiMapDecorator<K, V>
implements OrderedBidiMap<K, V> {
    protected AbstractOrderedBidiMapDecorator(OrderedBidiMap<K, V> map) {
        super(map);
    }

    @Override
    protected OrderedBidiMap<K, V> decorated() {
        return (OrderedBidiMap)super.decorated();
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        return this.decorated().mapIterator();
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
    public K nextKey(K key) {
        return this.decorated().nextKey(key);
    }

    @Override
    public K previousKey(K key) {
        return this.decorated().previousKey(key);
    }

    @Override
    public OrderedBidiMap<V, K> inverseBidiMap() {
        return this.decorated().inverseBidiMap();
    }
}

