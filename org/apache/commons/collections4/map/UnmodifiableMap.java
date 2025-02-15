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
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMap<K, V>
extends AbstractMapDecorator<K, V>
implements Unmodifiable,
Serializable {
    private static final long serialVersionUID = 2737023427269031941L;

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            Map<? extends K, ? extends V> tmpMap = map;
            return tmpMap;
        }
        return new UnmodifiableMap<K, V>(map);
    }

    private UnmodifiableMap(Map<? extends K, ? extends V> map) {
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
    public MapIterator<K, V> mapIterator() {
        if (this.map instanceof IterableMap) {
            MapIterator it = ((IterableMap)this.map).mapIterator();
            return UnmodifiableMapIterator.unmodifiableMapIterator(it);
        }
        EntrySetMapIterator it = new EntrySetMapIterator(this.map);
        return UnmodifiableMapIterator.unmodifiableMapIterator(it);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set set = super.entrySet();
        return UnmodifiableEntrySet.unmodifiableEntrySet(set);
    }

    @Override
    public Set<K> keySet() {
        Set set = super.keySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Collection<V> values() {
        Collection coll = super.values();
        return UnmodifiableCollection.unmodifiableCollection(coll);
    }
}

