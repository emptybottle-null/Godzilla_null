/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.util.Comparator;
import java.util.SortedMap;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator;

public abstract class AbstractSortedBidiMapDecorator<K, V>
extends AbstractOrderedBidiMapDecorator<K, V>
implements SortedBidiMap<K, V> {
    public AbstractSortedBidiMapDecorator(SortedBidiMap<K, V> map) {
        super(map);
    }

    @Override
    protected SortedBidiMap<K, V> decorated() {
        return (SortedBidiMap)super.decorated();
    }

    @Override
    public SortedBidiMap<V, K> inverseBidiMap() {
        return this.decorated().inverseBidiMap();
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.decorated().comparator();
    }

    @Override
    public Comparator<? super V> valueComparator() {
        return this.decorated().valueComparator();
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
}

