/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;

public class SingletonMap<K, V>
implements OrderedMap<K, V>,
BoundedMap<K, V>,
KeyValue<K, V>,
Serializable,
Cloneable {
    private static final long serialVersionUID = -8931271118676803261L;
    private final K key;
    private V value;

    public SingletonMap() {
        this.key = null;
    }

    public SingletonMap(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public SingletonMap(KeyValue<K, V> keyValue) {
        this.key = keyValue.getKey();
        this.value = keyValue.getValue();
    }

    public SingletonMap(Map.Entry<? extends K, ? extends V> mapEntry) {
        this.key = mapEntry.getKey();
        this.value = mapEntry.getValue();
    }

    public SingletonMap(Map<? extends K, ? extends V> map) {
        if (map.size() != 1) {
            throw new IllegalArgumentException("The map size must be 1");
        }
        Map.Entry<K, V> entry = map.entrySet().iterator().next();
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public int maxSize() {
        return 1;
    }

    @Override
    public V get(Object key) {
        if (this.isEqualKey(key)) {
            return this.value;
        }
        return null;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.isEqualKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.isEqualValue(value);
    }

    @Override
    public V put(K key, V value) {
        if (this.isEqualKey(key)) {
            return this.setValue(value);
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        switch (map.size()) {
            case 0: {
                return;
            }
            case 1: {
                Map.Entry<K, V> entry = map.entrySet().iterator().next();
                this.put(entry.getKey(), entry.getValue());
                return;
            }
        }
        throw new IllegalArgumentException("The map size must be 0 or 1");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        TiedMapEntry entry = new TiedMapEntry(this, this.getKey());
        return Collections.singleton(entry);
    }

    @Override
    public Set<K> keySet() {
        return Collections.singleton(this.key);
    }

    @Override
    public Collection<V> values() {
        return new SingletonValues(this);
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        return new SingletonMapIterator(this);
    }

    @Override
    public K firstKey() {
        return this.getKey();
    }

    @Override
    public K lastKey() {
        return this.getKey();
    }

    @Override
    public K nextKey(K key) {
        return null;
    }

    @Override
    public K previousKey(K key) {
        return null;
    }

    protected boolean isEqualKey(Object key) {
        return key == null ? this.getKey() == null : key.equals(this.getKey());
    }

    protected boolean isEqualValue(Object value) {
        return value == null ? this.getValue() == null : value.equals(this.getValue());
    }

    public SingletonMap<K, V> clone() {
        try {
            return (SingletonMap)super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map other = (Map)obj;
        if (other.size() != 1) {
            return false;
        }
        Map.Entry entry = other.entrySet().iterator().next();
        return this.isEqualKey(entry.getKey()) && this.isEqualValue(entry.getValue());
    }

    @Override
    public int hashCode() {
        return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
    }

    public String toString() {
        return new StringBuilder(128).append('{').append((Object)(this.getKey() == this ? "(this Map)" : this.getKey())).append('=').append((Object)(this.getValue() == this ? "(this Map)" : this.getValue())).append('}').toString();
    }

    static class SingletonValues<V>
    extends AbstractSet<V>
    implements Serializable {
        private static final long serialVersionUID = -3689524741863047872L;
        private final SingletonMap<?, V> parent;

        SingletonValues(SingletonMap<?, V> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object object) {
            return this.parent.containsValue(object);
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator<V> iterator() {
            return new SingletonIterator<V>(this.parent.getValue(), false);
        }
    }

    static class SingletonMapIterator<K, V>
    implements OrderedMapIterator<K, V>,
    ResettableIterator<K> {
        private final SingletonMap<K, V> parent;
        private boolean hasNext = true;
        private boolean canGetSet = false;

        SingletonMapIterator(SingletonMap<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.hasNext = false;
            this.canGetSet = true;
            return this.parent.getKey();
        }

        @Override
        public boolean hasPrevious() {
            return !this.hasNext;
        }

        @Override
        public K previous() {
            if (this.hasNext) {
                throw new NoSuchElementException("No previous() entry in the iteration");
            }
            this.hasNext = true;
            return this.parent.getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public K getKey() {
            if (!this.canGetSet) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            return this.parent.getKey();
        }

        @Override
        public V getValue() {
            if (!this.canGetSet) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            return this.parent.getValue();
        }

        @Override
        public V setValue(V value) {
            if (!this.canGetSet) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            return this.parent.setValue(value);
        }

        @Override
        public void reset() {
            this.hasNext = true;
        }

        public String toString() {
            if (this.hasNext) {
                return "Iterator[]";
            }
            return "Iterator[" + this.getKey() + "=" + this.getValue() + "]";
        }
    }
}

