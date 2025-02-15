/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class FixedSizeSortedMap<K, V>
extends AbstractSortedMapDecorator<K, V>
implements BoundedMap<K, V>,
Serializable {
    private static final long serialVersionUID = 3126019624511683653L;

    public static <K, V> FixedSizeSortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> map) {
        return new FixedSizeSortedMap<K, V>(map);
    }

    protected FixedSizeSortedMap(SortedMap<K, V> map) {
        super(map);
    }

    protected SortedMap<K, V> getSortedMap() {
        return (SortedMap)this.map;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }

    @Override
    public V put(K key, V value) {
        if (!this.map.containsKey(key)) {
            throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
        }
        return this.map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        if (CollectionUtils.isSubCollection(mapToCopy.keySet(), this.keySet())) {
            throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
        }
        this.map.putAll(mapToCopy);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.entrySet());
    }

    @Override
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.keySet());
    }

    @Override
    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(this.map.values());
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return new FixedSizeSortedMap<K, V>(this.getSortedMap().subMap(fromKey, toKey));
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return new FixedSizeSortedMap<K, V>(this.getSortedMap().headMap(toKey));
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return new FixedSizeSortedMap<K, V>(this.getSortedMap().tailMap(fromKey));
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public int maxSize() {
        return this.size();
    }
}

