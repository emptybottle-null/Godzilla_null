/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.TransformedMap;

public class TransformedSortedMap<K, V>
extends TransformedMap<K, V>
implements SortedMap<K, V> {
    private static final long serialVersionUID = -8751771676410385778L;

    public static <K, V> TransformedSortedMap<K, V> transformingSortedMap(SortedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return new TransformedSortedMap<K, V>(map, keyTransformer, valueTransformer);
    }

    public static <K, V> TransformedSortedMap<K, V> transformedSortedMap(SortedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        TransformedSortedMap<K, V> decorated = new TransformedSortedMap<K, V>(map, keyTransformer, valueTransformer);
        if (map.size() > 0) {
            Map<K, V> transformed = decorated.transformMap(map);
            decorated.clear();
            decorated.decorated().putAll(transformed);
        }
        return decorated;
    }

    protected TransformedSortedMap(SortedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        super(map, keyTransformer, valueTransformer);
    }

    protected SortedMap<K, V> getSortedMap() {
        return (SortedMap)this.map;
    }

    @Override
    public K firstKey() {
        return this.getSortedMap().firstKey();
    }

    @Override
    public K lastKey() {
        return this.getSortedMap().lastKey();
    }

    @Override
    public Comparator<? super K> comparator() {
        return this.getSortedMap().comparator();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        SortedMap<K, V> map = this.getSortedMap().subMap(fromKey, toKey);
        return new TransformedSortedMap<K, V>(map, this.keyTransformer, this.valueTransformer);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap<K, V> map = this.getSortedMap().headMap(toKey);
        return new TransformedSortedMap<K, V>(map, this.keyTransformer, this.valueTransformer);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap<K, V> map = this.getSortedMap().tailMap(fromKey);
        return new TransformedSortedMap<K, V>(map, this.keyTransformer, this.valueTransformer);
    }
}

