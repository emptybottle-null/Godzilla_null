/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.util.Comparator;
import java.util.SortedMap;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.map.PredicatedMap;

public class PredicatedSortedMap<K, V>
extends PredicatedMap<K, V>
implements SortedMap<K, V> {
    private static final long serialVersionUID = 3359846175935304332L;

    public static <K, V> PredicatedSortedMap<K, V> predicatedSortedMap(SortedMap<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate) {
        return new PredicatedSortedMap<K, V>(map, keyPredicate, valuePredicate);
    }

    protected PredicatedSortedMap(SortedMap<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate) {
        super(map, keyPredicate, valuePredicate);
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
        return new PredicatedSortedMap<K, V>(map, this.keyPredicate, this.valuePredicate);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap<K, V> map = this.getSortedMap().headMap(toKey);
        return new PredicatedSortedMap<K, V>(map, this.keyPredicate, this.valuePredicate);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap<K, V> map = this.getSortedMap().tailMap(fromKey);
        return new PredicatedSortedMap<K, V>(map, this.keyPredicate, this.valuePredicate);
    }
}

