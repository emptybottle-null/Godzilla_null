/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableSortedMap<K, V>
extends AbstractSortedMapDecorator<K, V>
implements Unmodifiable,
Serializable {
    private static final long serialVersionUID = 5805344239827376360L;

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            SortedMap<K, ? extends V> tmpMap = map;
            return tmpMap;
        }
        return new UnmodifiableSortedMap<K, V>(map);
    }

    private UnmodifiableSortedMap(SortedMap<K, ? extends V> map) {
        super(map);
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
        return UnmodifiableEntrySet.unmodifiableEntrySet(super.entrySet());
    }

    @Override
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(super.keySet());
    }

    @Override
    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(super.values());
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
    public Comparator<? super K> comparator() {
        return this.decorated().comparator();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return new UnmodifiableSortedMap(this.decorated().subMap(fromKey, toKey));
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return new UnmodifiableSortedMap(this.decorated().headMap(toKey));
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return new UnmodifiableSortedMap(this.decorated().tailMap(fromKey));
    }
}

