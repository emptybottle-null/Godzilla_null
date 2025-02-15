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
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class FixedSizeMap<K, V>
extends AbstractMapDecorator<K, V>
implements BoundedMap<K, V>,
Serializable {
    private static final long serialVersionUID = 7450927208116179316L;

    public static <K, V> FixedSizeMap<K, V> fixedSizeMap(Map<K, V> map) {
        return new FixedSizeMap<K, V>(map);
    }

    protected FixedSizeMap(Map<K, V> map) {
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
    public V put(K key, V value) {
        if (!this.map.containsKey(key)) {
            throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
        }
        return this.map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        for (K key : mapToCopy.keySet()) {
            if (this.containsKey(key)) continue;
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
        Set set = this.map.entrySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Set<K> keySet() {
        Set set = this.map.keySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Collection<V> values() {
        Collection coll = this.map.values();
        return UnmodifiableCollection.unmodifiableCollection(coll);
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

