/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;

public class AbstractHashedMap<K, V>
extends AbstractMap<K, V>
implements IterableMap<K, V> {
    protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
    protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
    protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
    protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
    protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
    protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final int DEFAULT_THRESHOLD = 12;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int MAXIMUM_CAPACITY = 0x40000000;
    protected static final Object NULL = new Object();
    transient float loadFactor;
    transient int size;
    transient HashEntry<K, V>[] data;
    transient int threshold;
    transient int modCount;
    transient EntrySet<K, V> entrySet;
    transient KeySet<K> keySet;
    transient Values<V> values;

    protected AbstractHashedMap() {
    }

    protected AbstractHashedMap(int initialCapacity, float loadFactor, int threshold) {
        this.loadFactor = loadFactor;
        this.data = new HashEntry[initialCapacity];
        this.threshold = threshold;
        this.init();
    }

    protected AbstractHashedMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    protected AbstractHashedMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity must be a non negative number");
        }
        if (loadFactor <= 0.0f || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be greater than 0");
        }
        this.loadFactor = loadFactor;
        initialCapacity = this.calculateNewCapacity(initialCapacity);
        this.threshold = this.calculateThreshold(initialCapacity, loadFactor);
        this.data = new HashEntry[initialCapacity];
        this.init();
    }

    protected AbstractHashedMap(Map<? extends K, ? extends V> map) {
        this(Math.max(2 * map.size(), 16), 0.75f);
        super._putAll(map);
    }

    protected void init() {
    }

    @Override
    public V get(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        HashEntry<K, V> entry = this.data[this.hashIndex(hashCode, this.data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        HashEntry<K, V> entry = this.data[this.hashIndex(hashCode, this.data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            HashEntry<K, V>[] hashEntryArray = this.data;
            int n = hashEntryArray.length;
            for (int i = 0; i < n; ++i) {
                HashEntry<K, V> element;
                HashEntry<K, V> entry = element = hashEntryArray[i];
                while (entry != null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        } else {
            HashEntry<K, V>[] hashEntryArray = this.data;
            int n = hashEntryArray.length;
            for (int i = 0; i < n; ++i) {
                HashEntry<K, V> element;
                HashEntry<K, V> entry = element = hashEntryArray[i];
                while (entry != null) {
                    if (this.isEqualValue(value, entry.getValue())) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        return false;
    }

    @Override
    public V put(K key, V value) {
        Object convertedKey = this.convertKey(key);
        int hashCode = this.hash(convertedKey);
        int index = this.hashIndex(hashCode, this.data.length);
        HashEntry<K, V> entry = this.data[index];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(convertedKey, entry.key)) {
                V oldValue = entry.getValue();
                this.updateEntry(entry, value);
                return oldValue;
            }
            entry = entry.next;
        }
        this.addMapping(index, hashCode, key, value);
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        this._putAll(map);
    }

    private void _putAll(Map<? extends K, ? extends V> map) {
        int mapSize = map.size();
        if (mapSize == 0) {
            return;
        }
        int newSize = (int)((float)(this.size + mapSize) / this.loadFactor + 1.0f);
        this.ensureCapacity(this.calculateNewCapacity(newSize));
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        int index = this.hashIndex(hashCode, this.data.length);
        HashEntry<K, V> entry = this.data[index];
        HashEntry<K, V> previous = null;
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                V oldValue = entry.getValue();
                this.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public void clear() {
        ++this.modCount;
        HashEntry<K, V>[] data = this.data;
        for (int i = data.length - 1; i >= 0; --i) {
            data[i] = null;
        }
        this.size = 0;
    }

    protected Object convertKey(Object key) {
        return key == null ? NULL : key;
    }

    protected int hash(Object key) {
        int h = key.hashCode();
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(Object key1, Object key2) {
        return key1 == key2 || key1.equals(key2);
    }

    protected boolean isEqualValue(Object value1, Object value2) {
        return value1 == value2 || value1.equals(value2);
    }

    protected int hashIndex(int hashCode, int dataSize) {
        return hashCode & dataSize - 1;
    }

    protected HashEntry<K, V> getEntry(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        HashEntry<K, V> entry = this.data[this.hashIndex(hashCode, this.data.length)];
        while (entry != null) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    protected void updateEntry(HashEntry<K, V> entry, V newValue) {
        entry.setValue(newValue);
    }

    protected void reuseEntry(HashEntry<K, V> entry, int hashIndex, int hashCode, K key, V value) {
        entry.next = this.data[hashIndex];
        entry.hashCode = hashCode;
        entry.key = key;
        entry.value = value;
    }

    protected void addMapping(int hashIndex, int hashCode, K key, V value) {
        ++this.modCount;
        HashEntry<K, V> entry = this.createEntry(this.data[hashIndex], hashCode, key, value);
        this.addEntry(entry, hashIndex);
        ++this.size;
        this.checkCapacity();
    }

    protected HashEntry<K, V> createEntry(HashEntry<K, V> next, int hashCode, K key, V value) {
        return new HashEntry<K, V>(next, hashCode, this.convertKey(key), value);
    }

    protected void addEntry(HashEntry<K, V> entry, int hashIndex) {
        this.data[hashIndex] = entry;
    }

    protected void removeMapping(HashEntry<K, V> entry, int hashIndex, HashEntry<K, V> previous) {
        ++this.modCount;
        this.removeEntry(entry, hashIndex, previous);
        --this.size;
        this.destroyEntry(entry);
    }

    protected void removeEntry(HashEntry<K, V> entry, int hashIndex, HashEntry<K, V> previous) {
        if (previous == null) {
            this.data[hashIndex] = entry.next;
        } else {
            previous.next = entry.next;
        }
    }

    protected void destroyEntry(HashEntry<K, V> entry) {
        entry.next = null;
        entry.key = null;
        entry.value = null;
    }

    protected void checkCapacity() {
        int newCapacity;
        if (this.size >= this.threshold && (newCapacity = this.data.length * 2) <= 0x40000000) {
            this.ensureCapacity(newCapacity);
        }
    }

    protected void ensureCapacity(int newCapacity) {
        int oldCapacity = this.data.length;
        if (newCapacity <= oldCapacity) {
            return;
        }
        if (this.size == 0) {
            this.threshold = this.calculateThreshold(newCapacity, this.loadFactor);
            this.data = new HashEntry[newCapacity];
        } else {
            HashEntry<K, V>[] oldEntries = this.data;
            HashEntry[] newEntries = new HashEntry[newCapacity];
            ++this.modCount;
            for (int i = oldCapacity - 1; i >= 0; --i) {
                HashEntry next;
                HashEntry<K, V> entry = oldEntries[i];
                if (entry == null) continue;
                oldEntries[i] = null;
                do {
                    next = entry.next;
                    int index = this.hashIndex(entry.hashCode, newCapacity);
                    entry.next = newEntries[index];
                    newEntries[index] = entry;
                } while ((entry = next) != null);
            }
            this.threshold = this.calculateThreshold(newCapacity, this.loadFactor);
            this.data = newEntries;
        }
    }

    protected int calculateNewCapacity(int proposedCapacity) {
        int newCapacity;
        if (proposedCapacity > 0x40000000) {
            newCapacity = 0x40000000;
        } else {
            for (newCapacity = 1; newCapacity < proposedCapacity; newCapacity <<= 1) {
            }
            if (newCapacity > 0x40000000) {
                newCapacity = 0x40000000;
            }
        }
        return newCapacity;
    }

    protected int calculateThreshold(int newCapacity, float factor) {
        return (int)((float)newCapacity * factor);
    }

    protected HashEntry<K, V> entryNext(HashEntry<K, V> entry) {
        return entry.next;
    }

    protected int entryHashCode(HashEntry<K, V> entry) {
        return entry.hashCode;
    }

    protected K entryKey(HashEntry<K, V> entry) {
        return entry.getKey();
    }

    protected V entryValue(HashEntry<K, V> entry) {
        return entry.getValue();
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new HashMapIterator(this);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        if (this.size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new EntrySetIterator(this);
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    protected Iterator<K> createKeySetIterator() {
        if (this.size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new KeySetIterator(this);
    }

    @Override
    public Collection<V> values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }

    protected Iterator<V> createValuesIterator() {
        if (this.size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(this);
    }

    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeFloat(this.loadFactor);
        out.writeInt(this.data.length);
        out.writeInt(this.size);
        MapIterator<K, V> it = this.mapIterator();
        while (it.hasNext()) {
            out.writeObject(it.next());
            out.writeObject(it.getValue());
        }
    }

    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.loadFactor = in.readFloat();
        int capacity = in.readInt();
        int size = in.readInt();
        this.init();
        this.threshold = this.calculateThreshold(capacity, this.loadFactor);
        this.data = new HashEntry[capacity];
        for (int i = 0; i < size; ++i) {
            Object key = in.readObject();
            Object value = in.readObject();
            this.put(key, value);
        }
    }

    @Override
    protected AbstractHashedMap<K, V> clone() {
        try {
            AbstractHashedMap cloned = (AbstractHashedMap)super.clone();
            cloned.data = new HashEntry[this.data.length];
            cloned.entrySet = null;
            cloned.keySet = null;
            cloned.values = null;
            cloned.modCount = 0;
            cloned.size = 0;
            cloned.init();
            cloned.putAll(this);
            return cloned;
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
        Map map = (Map)obj;
        if (map.size() != this.size()) {
            return false;
        }
        MapIterator<K, V> it = this.mapIterator();
        try {
            while (it.hasNext()) {
                K key = it.next();
                V value = it.getValue();
                if (!(value == null ? map.get(key) != null || !map.containsKey(key) : !value.equals(map.get(key)))) continue;
                return false;
            }
        } catch (ClassCastException ignored) {
            return false;
        } catch (NullPointerException ignored) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int total = 0;
        Iterator<Map.Entry<K, V>> it = this.createEntrySetIterator();
        while (it.hasNext()) {
            total += it.next().hashCode();
        }
        return total;
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(32 * this.size());
        buf.append('{');
        MapIterator<K, V> it = this.mapIterator();
        boolean hasNext = it.hasNext();
        while (hasNext) {
            K key = it.next();
            V value = it.getValue();
            buf.append((Object)(key == this ? "(this Map)" : key)).append('=').append((Object)(value == this ? "(this Map)" : value));
            hasNext = it.hasNext();
            if (!hasNext) continue;
            buf.append(',').append(' ');
        }
        buf.append('}');
        return buf.toString();
    }

    protected static abstract class HashIterator<K, V> {
        private final AbstractHashedMap<K, V> parent;
        private int hashIndex;
        private HashEntry<K, V> last;
        private HashEntry<K, V> next;
        private int expectedModCount;

        protected HashIterator(AbstractHashedMap<K, V> parent) {
            this.parent = parent;
            HashEntry<K, V>[] data = parent.data;
            int i = data.length;
            HashEntry next = null;
            while (i > 0 && next == null) {
                next = data[--i];
            }
            this.next = next;
            this.hashIndex = i;
            this.expectedModCount = parent.modCount;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        protected HashEntry<K, V> nextEntry() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            HashEntry<K, V> newCurrent = this.next;
            if (newCurrent == null) {
                throw new NoSuchElementException(AbstractHashedMap.NO_NEXT_ENTRY);
            }
            HashEntry<K, V>[] data = this.parent.data;
            int i = this.hashIndex;
            HashEntry n = newCurrent.next;
            while (n == null && i > 0) {
                n = data[--i];
            }
            this.next = n;
            this.hashIndex = i;
            this.last = newCurrent;
            return newCurrent;
        }

        protected HashEntry<K, V> currentEntry() {
            return this.last;
        }

        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException(AbstractHashedMap.REMOVE_INVALID);
            }
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.parent.remove(this.last.getKey());
            this.last = null;
            this.expectedModCount = this.parent.modCount;
        }

        public String toString() {
            if (this.last != null) {
                return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
            }
            return "Iterator[]";
        }
    }

    protected static class HashEntry<K, V>
    implements Map.Entry<K, V>,
    KeyValue<K, V> {
        protected HashEntry<K, V> next;
        protected int hashCode;
        protected Object key;
        protected Object value;

        protected HashEntry(HashEntry<K, V> next, int hashCode, Object key, V value) {
            this.next = next;
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            if (this.key == NULL) {
                return null;
            }
            return (K)this.key;
        }

        @Override
        public V getValue() {
            return (V)this.value;
        }

        @Override
        public V setValue(V value) {
            Object old = this.value;
            this.value = value;
            return (V)old;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry)obj;
            return (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey())) && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
        }

        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        public String toString() {
            return "" + this.getKey() + '=' + this.getValue();
        }
    }

    protected static class ValuesIterator<V>
    extends HashIterator<Object, V>
    implements Iterator<V> {
        protected ValuesIterator(AbstractHashedMap<?, V> parent) {
            super(parent);
        }

        @Override
        public V next() {
            return super.nextEntry().getValue();
        }
    }

    protected static class Values<V>
    extends AbstractCollection<V> {
        private final AbstractHashedMap<?, V> parent;

        protected Values(AbstractHashedMap<?, V> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.size();
        }

        @Override
        public void clear() {
            this.parent.clear();
        }

        @Override
        public boolean contains(Object value) {
            return this.parent.containsValue(value);
        }

        @Override
        public Iterator<V> iterator() {
            return this.parent.createValuesIterator();
        }
    }

    protected static class KeySetIterator<K>
    extends HashIterator<K, Object>
    implements Iterator<K> {
        protected KeySetIterator(AbstractHashedMap<K, ?> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return super.nextEntry().getKey();
        }
    }

    protected static class KeySet<K>
    extends AbstractSet<K> {
        private final AbstractHashedMap<K, ?> parent;

        protected KeySet(AbstractHashedMap<K, ?> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.size();
        }

        @Override
        public void clear() {
            this.parent.clear();
        }

        @Override
        public boolean contains(Object key) {
            return this.parent.containsKey(key);
        }

        @Override
        public boolean remove(Object key) {
            boolean result = this.parent.containsKey(key);
            this.parent.remove(key);
            return result;
        }

        @Override
        public Iterator<K> iterator() {
            return this.parent.createKeySetIterator();
        }
    }

    protected static class EntrySetIterator<K, V>
    extends HashIterator<K, V>
    implements Iterator<Map.Entry<K, V>> {
        protected EntrySetIterator(AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            return super.nextEntry();
        }
    }

    protected static class EntrySet<K, V>
    extends AbstractSet<Map.Entry<K, V>> {
        private final AbstractHashedMap<K, V> parent;

        protected EntrySet(AbstractHashedMap<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.size();
        }

        @Override
        public void clear() {
            this.parent.clear();
        }

        @Override
        public boolean contains(Object entry) {
            if (entry instanceof Map.Entry) {
                Map.Entry e = (Map.Entry)entry;
                HashEntry<K, V> match = this.parent.getEntry(e.getKey());
                return match != null && match.equals(e);
            }
            return false;
        }

        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            if (!this.contains(obj)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            this.parent.remove(entry.getKey());
            return true;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator();
        }
    }

    protected static class HashMapIterator<K, V>
    extends HashIterator<K, V>
    implements MapIterator<K, V> {
        protected HashMapIterator(AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return super.nextEntry().getKey();
        }

        @Override
        public K getKey() {
            HashEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.GETKEY_INVALID);
            }
            return current.getKey();
        }

        @Override
        public V getValue() {
            HashEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }
            return current.getValue();
        }

        @Override
        public V setValue(V value) {
            HashEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException(AbstractHashedMap.SETVALUE_INVALID);
            }
            return current.setValue(value);
        }
    }
}

