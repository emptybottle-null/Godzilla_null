/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.map.HashedMap;

public class MultiKeyMap<K, V>
extends AbstractMapDecorator<MultiKey<? extends K>, V>
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1788199231038721040L;

    public static <K, V> MultiKeyMap<K, V> multiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null");
        }
        if (map.size() > 0) {
            throw new IllegalArgumentException("Map must be empty");
        }
        return new MultiKeyMap<K, V>(map);
    }

    public MultiKeyMap() {
        this(new HashedMap());
    }

    protected MultiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> map) {
        super(map);
        this.map = map;
    }

    public V get(Object key1, Object key2) {
        int hashCode = this.hash(key1, key2);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(Object key1, Object key2) {
        int hashCode = this.hash(key1, key2);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public V put(K key1, K key2, V value) {
        int hashCode = this.hash(key1, key2);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        ((AbstractHashedMap)this.decorated()).addMapping(index, hashCode, new MultiKey<K>(key1, key2), value);
        return null;
    }

    public V removeMultiKey(Object key1, Object key2) {
        int hashCode = this.hash(key1, key2);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    protected int hash(Object key1, Object key2) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2) {
        MultiKey<K> multi = entry.getKey();
        return multi.size() == 2 && (key1 == multi.getKey(0) || key1 != null && key1.equals(multi.getKey(0))) && (key2 == multi.getKey(1) || key2 != null && key2.equals(multi.getKey(1)));
    }

    public V get(Object key1, Object key2, Object key3) {
        int hashCode = this.hash(key1, key2, key3);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(Object key1, Object key2, Object key3) {
        int hashCode = this.hash(key1, key2, key3);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public V put(K key1, K key2, K key3, V value) {
        int hashCode = this.hash(key1, key2, key3);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        ((AbstractHashedMap)this.decorated()).addMapping(index, hashCode, new MultiKey<K>(key1, key2, key3), value);
        return null;
    }

    public V removeMultiKey(Object key1, Object key2, Object key3) {
        int hashCode = this.hash(key1, key2, key3);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    protected int hash(Object key1, Object key2, Object key3) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3) {
        MultiKey<K> multi = entry.getKey();
        return multi.size() == 3 && (key1 == multi.getKey(0) || key1 != null && key1.equals(multi.getKey(0))) && (key2 == multi.getKey(1) || key2 != null && key2.equals(multi.getKey(1))) && (key3 == multi.getKey(2) || key3 != null && key3.equals(multi.getKey(2)));
    }

    public V get(Object key1, Object key2, Object key3, Object key4) {
        int hashCode = this.hash(key1, key2, key3, key4);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(Object key1, Object key2, Object key3, Object key4) {
        int hashCode = this.hash(key1, key2, key3, key4);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public V put(K key1, K key2, K key3, K key4, V value) {
        int hashCode = this.hash(key1, key2, key3, key4);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        ((AbstractHashedMap)this.decorated()).addMapping(index, hashCode, new MultiKey<K>(key1, key2, key3, key4), value);
        return null;
    }

    public V removeMultiKey(Object key1, Object key2, Object key3, Object key4) {
        int hashCode = this.hash(key1, key2, key3, key4);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    protected int hash(Object key1, Object key2, Object key3, Object key4) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3, Object key4) {
        MultiKey<K> multi = entry.getKey();
        return multi.size() == 4 && (key1 == multi.getKey(0) || key1 != null && key1.equals(multi.getKey(0))) && (key2 == multi.getKey(1) || key2 != null && key2.equals(multi.getKey(1))) && (key3 == multi.getKey(2) || key3 != null && key3.equals(multi.getKey(2))) && (key4 == multi.getKey(3) || key4 != null && key4.equals(multi.getKey(3)));
    }

    public V get(Object key1, Object key2, Object key3, Object key4, Object key5) {
        int hashCode = this.hash(key1, key2, key3, key4, key5);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
        int hashCode = this.hash(key1, key2, key3, key4, key5);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public V put(K key1, K key2, K key3, K key4, K key5, V value) {
        int hashCode = this.hash(key1, key2, key3, key4, key5);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        ((AbstractHashedMap)this.decorated()).addMapping(index, hashCode, new MultiKey<K>(key1, key2, key3, key4, key5), value);
        return null;
    }

    public V removeMultiKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
        int hashCode = this.hash(key1, key2, key3, key4, key5);
        int index = ((AbstractHashedMap)this.decorated()).hashIndex(hashCode, ((AbstractHashedMap)this.decorated()).data.length);
        AbstractHashedMap.HashEntry entry = ((AbstractHashedMap)this.decorated()).data[index];
        AbstractHashedMap.HashEntry previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(entry, key1, key2, key3, key4, key5)) {
                Object oldValue = entry.getValue();
                ((AbstractHashedMap)this.decorated()).removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    protected int hash(Object key1, Object key2, Object key3, Object key4, Object key5) {
        int h = 0;
        if (key1 != null) {
            h ^= key1.hashCode();
        }
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        if (key5 != null) {
            h ^= key5.hashCode();
        }
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3, Object key4, Object key5) {
        MultiKey<K> multi = entry.getKey();
        return multi.size() == 5 && (key1 == multi.getKey(0) || key1 != null && key1.equals(multi.getKey(0))) && (key2 == multi.getKey(1) || key2 != null && key2.equals(multi.getKey(1))) && (key3 == multi.getKey(2) || key3 != null && key3.equals(multi.getKey(2))) && (key4 == multi.getKey(3) || key4 != null && key4.equals(multi.getKey(3))) && (key5 == multi.getKey(4) || key5 != null && key5.equals(multi.getKey(4)));
    }

    public boolean removeAll(Object key1) {
        boolean modified = false;
        MapIterator<MultiKey<K>, V> it = this.mapIterator();
        while (it.hasNext()) {
            MultiKey<K> multi = it.next();
            if (multi.size() < 1 || !(key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0)))) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2) {
        boolean modified = false;
        MapIterator<MultiKey<K>, V> it = this.mapIterator();
        while (it.hasNext()) {
            MultiKey<K> multi = it.next();
            if (multi.size() < 2 || !(key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) || !(key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1)))) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2, Object key3) {
        boolean modified = false;
        MapIterator<MultiKey<K>, V> it = this.mapIterator();
        while (it.hasNext()) {
            MultiKey<K> multi = it.next();
            if (multi.size() < 3 || !(key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) || !(key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1))) || !(key3 == null ? multi.getKey(2) == null : key3.equals(multi.getKey(2)))) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2, Object key3, Object key4) {
        boolean modified = false;
        MapIterator<MultiKey<K>, V> it = this.mapIterator();
        while (it.hasNext()) {
            MultiKey<K> multi = it.next();
            if (multi.size() < 4 || !(key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) || !(key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1))) || !(key3 == null ? multi.getKey(2) == null : key3.equals(multi.getKey(2))) || !(key4 == null ? multi.getKey(3) == null : key4.equals(multi.getKey(3)))) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    protected void checkKey(MultiKey<?> key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }
    }

    public MultiKeyMap<K, V> clone() {
        try {
            return (MultiKeyMap)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public V put(MultiKey<? extends K> key, V value) {
        this.checkKey(key);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends MultiKey<? extends K>, ? extends V> mapToCopy) {
        for (MultiKey<K> multiKey : mapToCopy.keySet()) {
            this.checkKey(multiKey);
        }
        super.putAll(mapToCopy);
    }

    @Override
    public MapIterator<MultiKey<? extends K>, V> mapIterator() {
        return ((AbstractHashedMap)this.decorated()).mapIterator();
    }

    @Override
    protected AbstractHashedMap<MultiKey<? extends K>, V> decorated() {
        return (AbstractHashedMap)super.decorated();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }
}

