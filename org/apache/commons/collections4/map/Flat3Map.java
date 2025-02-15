/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.HashedMap;

public class Flat3Map<K, V>
implements IterableMap<K, V>,
Serializable,
Cloneable {
    private static final long serialVersionUID = -6701087419741928296L;
    private transient int size;
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    private transient K key1;
    private transient K key2;
    private transient K key3;
    private transient V value1;
    private transient V value2;
    private transient V value3;
    private transient AbstractHashedMap<K, V> delegateMap;

    public Flat3Map() {
    }

    public Flat3Map(Map<? extends K, ? extends V> map) {
        this.putAll(map);
    }

    @Override
    public V get(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.get(key);
        }
        if (key == null) {
            switch (this.size) {
                case 3: {
                    if (this.key3 == null) {
                        return this.value3;
                    }
                }
                case 2: {
                    if (this.key2 == null) {
                        return this.value2;
                    }
                }
                case 1: {
                    if (this.key1 != null) break;
                    return this.value1;
                }
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3: {
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        return this.value3;
                    }
                }
                case 2: {
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        return this.value2;
                    }
                }
                case 1: {
                    if (this.hash1 != hashCode || !key.equals(this.key1)) break;
                    return this.value1;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        if (this.delegateMap != null) {
            return this.delegateMap.size();
        }
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.containsKey(key);
        }
        if (key == null) {
            switch (this.size) {
                case 3: {
                    if (this.key3 == null) {
                        return true;
                    }
                }
                case 2: {
                    if (this.key2 == null) {
                        return true;
                    }
                }
                case 1: {
                    if (this.key1 != null) break;
                    return true;
                }
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3: {
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        return true;
                    }
                }
                case 2: {
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        return true;
                    }
                }
                case 1: {
                    if (this.hash1 != hashCode || !key.equals(this.key1)) break;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (this.delegateMap != null) {
            return this.delegateMap.containsValue(value);
        }
        if (value == null) {
            switch (this.size) {
                case 3: {
                    if (this.value3 == null) {
                        return true;
                    }
                }
                case 2: {
                    if (this.value2 == null) {
                        return true;
                    }
                }
                case 1: {
                    if (this.value1 != null) break;
                    return true;
                }
            }
        } else {
            switch (this.size) {
                case 3: {
                    if (value.equals(this.value3)) {
                        return true;
                    }
                }
                case 2: {
                    if (value.equals(this.value2)) {
                        return true;
                    }
                }
                case 1: {
                    if (!value.equals(this.value1)) break;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V put(K key, V value) {
        if (this.delegateMap != null) {
            return this.delegateMap.put(key, value);
        }
        if (key == null) {
            switch (this.size) {
                case 3: {
                    if (this.key3 == null) {
                        V old = this.value3;
                        this.value3 = value;
                        return old;
                    }
                }
                case 2: {
                    if (this.key2 == null) {
                        V old = this.value2;
                        this.value2 = value;
                        return old;
                    }
                }
                case 1: {
                    if (this.key1 != null) break;
                    V old = this.value1;
                    this.value1 = value;
                    return old;
                }
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3: {
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        V old = this.value3;
                        this.value3 = value;
                        return old;
                    }
                }
                case 2: {
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        V old = this.value2;
                        this.value2 = value;
                        return old;
                    }
                }
                case 1: {
                    if (this.hash1 != hashCode || !key.equals(this.key1)) break;
                    V old = this.value1;
                    this.value1 = value;
                    return old;
                }
            }
        }
        switch (this.size) {
            default: {
                this.convertToMap();
                this.delegateMap.put(key, value);
                return null;
            }
            case 2: {
                this.hash3 = key == null ? 0 : key.hashCode();
                this.key3 = key;
                this.value3 = value;
                break;
            }
            case 1: {
                this.hash2 = key == null ? 0 : key.hashCode();
                this.key2 = key;
                this.value2 = value;
                break;
            }
            case 0: {
                this.hash1 = key == null ? 0 : key.hashCode();
                this.key1 = key;
                this.value1 = value;
            }
        }
        ++this.size;
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        if (this.delegateMap != null) {
            this.delegateMap.putAll(map);
            return;
        }
        if (size < 4) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        } else {
            this.convertToMap();
            this.delegateMap.putAll(map);
        }
    }

    private void convertToMap() {
        this.delegateMap = this.createDelegateMap();
        switch (this.size) {
            case 3: {
                this.delegateMap.put(this.key3, this.value3);
            }
            case 2: {
                this.delegateMap.put(this.key2, this.value2);
            }
            case 1: {
                this.delegateMap.put(this.key1, this.value1);
            }
            case 0: {
                break;
            }
            default: {
                throw new IllegalStateException("Invalid map index: " + this.size);
            }
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    protected AbstractHashedMap<K, V> createDelegateMap() {
        return new HashedMap();
    }

    @Override
    public V remove(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.remove(key);
        }
        if (this.size == 0) {
            return null;
        }
        if (key == null) {
            switch (this.size) {
                case 3: {
                    if (this.key3 == null) {
                        V old = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    if (this.key2 == null) {
                        V old = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    if (this.key1 == null) {
                        V old = this.value1;
                        this.hash1 = this.hash3;
                        this.key1 = this.key3;
                        this.value1 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    return null;
                }
                case 2: {
                    if (this.key2 == null) {
                        V old = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old;
                    }
                    if (this.key1 == null) {
                        V old = this.value1;
                        this.hash1 = this.hash2;
                        this.key1 = this.key2;
                        this.value1 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old;
                    }
                    return null;
                }
                case 1: {
                    if (this.key1 != null) break;
                    V old = this.value1;
                    this.hash1 = 0;
                    this.key1 = null;
                    this.value1 = null;
                    this.size = 0;
                    return old;
                }
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3: {
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        V old = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        V old = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    if (this.hash1 == hashCode && key.equals(this.key1)) {
                        V old = this.value1;
                        this.hash1 = this.hash3;
                        this.key1 = this.key3;
                        this.value1 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old;
                    }
                    return null;
                }
                case 2: {
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        V old = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old;
                    }
                    if (this.hash1 == hashCode && key.equals(this.key1)) {
                        V old = this.value1;
                        this.hash1 = this.hash2;
                        this.key1 = this.key2;
                        this.value1 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old;
                    }
                    return null;
                }
                case 1: {
                    if (this.hash1 != hashCode || !key.equals(this.key1)) break;
                    V old = this.value1;
                    this.hash1 = 0;
                    this.key1 = null;
                    this.value1 = null;
                    this.size = 0;
                    return old;
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        if (this.delegateMap != null) {
            this.delegateMap.clear();
            this.delegateMap = null;
        } else {
            this.size = 0;
            this.hash3 = 0;
            this.hash2 = 0;
            this.hash1 = 0;
            this.key3 = null;
            this.key2 = null;
            this.key1 = null;
            this.value3 = null;
            this.value2 = null;
            this.value1 = null;
        }
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        if (this.delegateMap != null) {
            return this.delegateMap.mapIterator();
        }
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new FlatMapIterator(this);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.delegateMap != null) {
            return this.delegateMap.entrySet();
        }
        return new EntrySet(this);
    }

    @Override
    public Set<K> keySet() {
        if (this.delegateMap != null) {
            return this.delegateMap.keySet();
        }
        return new KeySet(this);
    }

    @Override
    public Collection<V> values() {
        if (this.delegateMap != null) {
            return this.delegateMap.values();
        }
        return new Values(this);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(this.size());
        MapIterator<K, V> it = this.mapIterator();
        while (it.hasNext()) {
            out.writeObject(it.next());
            out.writeObject(it.getValue());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int count = in.readInt();
        if (count > 3) {
            this.delegateMap = this.createDelegateMap();
        }
        for (int i = count; i > 0; --i) {
            this.put(in.readObject(), in.readObject());
        }
    }

    public Flat3Map<K, V> clone() {
        try {
            Flat3Map cloned = (Flat3Map)super.clone();
            if (cloned.delegateMap != null) {
                cloned.delegateMap = cloned.delegateMap.clone();
            }
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
        if (this.delegateMap != null) {
            return this.delegateMap.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map other = (Map)obj;
        if (this.size != other.size()) {
            return false;
        }
        if (this.size > 0) {
            Object otherValue = null;
            switch (this.size) {
                case 3: {
                    if (!other.containsKey(this.key3)) {
                        return false;
                    }
                    otherValue = other.get(this.key3);
                    if (this.value3 == null ? otherValue != null : !this.value3.equals(otherValue)) {
                        return false;
                    }
                }
                case 2: {
                    if (!other.containsKey(this.key2)) {
                        return false;
                    }
                    otherValue = other.get(this.key2);
                    if (this.value2 == null ? otherValue != null : !this.value2.equals(otherValue)) {
                        return false;
                    }
                }
                case 1: {
                    if (!other.containsKey(this.key1)) {
                        return false;
                    }
                    otherValue = other.get(this.key1);
                    if (!(this.value1 == null ? otherValue != null : !this.value1.equals(otherValue))) break;
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (this.delegateMap != null) {
            return this.delegateMap.hashCode();
        }
        int total = 0;
        switch (this.size) {
            case 3: {
                total += this.hash3 ^ (this.value3 == null ? 0 : this.value3.hashCode());
            }
            case 2: {
                total += this.hash2 ^ (this.value2 == null ? 0 : this.value2.hashCode());
            }
            case 1: {
                total += this.hash1 ^ (this.value1 == null ? 0 : this.value1.hashCode());
            }
            case 0: {
                break;
            }
            default: {
                throw new IllegalStateException("Invalid map index: " + this.size);
            }
        }
        return total;
    }

    public String toString() {
        if (this.delegateMap != null) {
            return this.delegateMap.toString();
        }
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(128);
        buf.append('{');
        switch (this.size) {
            case 3: {
                buf.append((Object)(this.key3 == this ? "(this Map)" : this.key3));
                buf.append('=');
                buf.append((Object)(this.value3 == this ? "(this Map)" : this.value3));
                buf.append(',');
            }
            case 2: {
                buf.append((Object)(this.key2 == this ? "(this Map)" : this.key2));
                buf.append('=');
                buf.append((Object)(this.value2 == this ? "(this Map)" : this.value2));
                buf.append(',');
            }
            case 1: {
                buf.append((Object)(this.key1 == this ? "(this Map)" : this.key1));
                buf.append('=');
                buf.append((Object)(this.value1 == this ? "(this Map)" : this.value1));
                break;
            }
            default: {
                throw new IllegalStateException("Invalid map index: " + this.size);
            }
        }
        buf.append('}');
        return buf.toString();
    }

    static class ValuesIterator<V>
    extends EntryIterator<Object, V>
    implements Iterator<V> {
        ValuesIterator(Flat3Map<?, V> parent) {
            super(parent);
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    static class Values<V>
    extends AbstractCollection<V> {
        private final Flat3Map<?, V> parent;

        Values(Flat3Map<?, V> parent) {
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
            if (((Flat3Map)this.parent).delegateMap != null) {
                return ((Flat3Map)this.parent).delegateMap.values().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new ValuesIterator<V>(this.parent);
        }
    }

    static class KeySetIterator<K>
    extends EntryIterator<K, Object>
    implements Iterator<K> {
        KeySetIterator(Flat3Map<K, ?> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    static class KeySet<K>
    extends AbstractSet<K> {
        private final Flat3Map<K, ?> parent;

        KeySet(Flat3Map<K, ?> parent) {
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
            if (((Flat3Map)this.parent).delegateMap != null) {
                return ((Flat3Map)this.parent).delegateMap.keySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new KeySetIterator<K>(this.parent);
        }
    }

    static class EntrySetIterator<K, V>
    extends EntryIterator<K, V>
    implements Iterator<Map.Entry<K, V>> {
        EntrySetIterator(Flat3Map<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    static abstract class EntryIterator<K, V> {
        private final Flat3Map<K, V> parent;
        private int nextIndex = 0;
        private FlatMapEntry<K, V> currentEntry = null;

        public EntryIterator(Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map)this.parent).size;
        }

        public Map.Entry<K, V> nextEntry() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.currentEntry = new FlatMapEntry<K, V>(this.parent, ++this.nextIndex);
            return this.currentEntry;
        }

        public void remove() {
            if (this.currentEntry == null) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.currentEntry.setRemoved(true);
            this.parent.remove(this.currentEntry.getKey());
            --this.nextIndex;
            this.currentEntry = null;
        }
    }

    static class FlatMapEntry<K, V>
    implements Map.Entry<K, V> {
        private final Flat3Map<K, V> parent;
        private final int index;
        private volatile boolean removed;

        public FlatMapEntry(Flat3Map<K, V> parent, int index) {
            this.parent = parent;
            this.index = index;
            this.removed = false;
        }

        void setRemoved(boolean flag) {
            this.removed = flag;
        }

        @Override
        public K getKey() {
            if (this.removed) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            switch (this.index) {
                case 3: {
                    return (K)((Flat3Map)this.parent).key3;
                }
                case 2: {
                    return (K)((Flat3Map)this.parent).key2;
                }
                case 1: {
                    return (K)((Flat3Map)this.parent).key1;
                }
            }
            throw new IllegalStateException("Invalid map index: " + this.index);
        }

        @Override
        public V getValue() {
            if (this.removed) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            switch (this.index) {
                case 3: {
                    return (V)((Flat3Map)this.parent).value3;
                }
                case 2: {
                    return (V)((Flat3Map)this.parent).value2;
                }
                case 1: {
                    return (V)((Flat3Map)this.parent).value1;
                }
            }
            throw new IllegalStateException("Invalid map index: " + this.index);
        }

        @Override
        public V setValue(V value) {
            if (this.removed) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            V old = this.getValue();
            switch (this.index) {
                case 3: {
                    ((Flat3Map)this.parent).value3 = value;
                    break;
                }
                case 2: {
                    ((Flat3Map)this.parent).value2 = value;
                    break;
                }
                case 1: {
                    ((Flat3Map)this.parent).value1 = value;
                    break;
                }
                default: {
                    throw new IllegalStateException("Invalid map index: " + this.index);
                }
            }
            return old;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.removed) {
                return false;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry)obj;
            K key = this.getKey();
            V value = this.getValue();
            return (key == null ? other.getKey() == null : key.equals(other.getKey())) && (value == null ? other.getValue() == null : value.equals(other.getValue()));
        }

        @Override
        public int hashCode() {
            if (this.removed) {
                return 0;
            }
            K key = this.getKey();
            V value = this.getValue();
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

        public String toString() {
            if (!this.removed) {
                return this.getKey() + "=" + this.getValue();
            }
            return "";
        }
    }

    static class EntrySet<K, V>
    extends AbstractSet<Map.Entry<K, V>> {
        private final Flat3Map<K, V> parent;

        EntrySet(Flat3Map<K, V> parent) {
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
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object key = entry.getKey();
            boolean result = this.parent.containsKey(key);
            this.parent.remove(key);
            return result;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            if (((Flat3Map)this.parent).delegateMap != null) {
                return ((Flat3Map)this.parent).delegateMap.entrySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new EntrySetIterator<K, V>(this.parent);
        }
    }

    static class FlatMapIterator<K, V>
    implements MapIterator<K, V>,
    ResettableIterator<K> {
        private final Flat3Map<K, V> parent;
        private int nextIndex = 0;
        private boolean canRemove = false;

        FlatMapIterator(Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map)this.parent).size;
        }

        @Override
        public K next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.canRemove = true;
            ++this.nextIndex;
            return this.getKey();
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.parent.remove(this.getKey());
            --this.nextIndex;
            this.canRemove = false;
        }

        @Override
        public K getKey() {
            if (!this.canRemove) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            switch (this.nextIndex) {
                case 3: {
                    return (K)((Flat3Map)this.parent).key3;
                }
                case 2: {
                    return (K)((Flat3Map)this.parent).key2;
                }
                case 1: {
                    return (K)((Flat3Map)this.parent).key1;
                }
            }
            throw new IllegalStateException("Invalid map index: " + this.nextIndex);
        }

        @Override
        public V getValue() {
            if (!this.canRemove) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            switch (this.nextIndex) {
                case 3: {
                    return (V)((Flat3Map)this.parent).value3;
                }
                case 2: {
                    return (V)((Flat3Map)this.parent).value2;
                }
                case 1: {
                    return (V)((Flat3Map)this.parent).value1;
                }
            }
            throw new IllegalStateException("Invalid map index: " + this.nextIndex);
        }

        @Override
        public V setValue(V value) {
            if (!this.canRemove) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            V old = this.getValue();
            switch (this.nextIndex) {
                case 3: {
                    ((Flat3Map)this.parent).value3 = value;
                    break;
                }
                case 2: {
                    ((Flat3Map)this.parent).value2 = value;
                    break;
                }
                case 1: {
                    ((Flat3Map)this.parent).value1 = value;
                    break;
                }
                default: {
                    throw new IllegalStateException("Invalid map index: " + this.nextIndex);
                }
            }
            return old;
        }

        @Override
        public void reset() {
            this.nextIndex = 0;
            this.canRemove = false;
        }

        public String toString() {
            if (this.canRemove) {
                return "Iterator[" + this.getKey() + "=" + this.getValue() + "]";
            }
            return "Iterator[]";
        }
    }
}

