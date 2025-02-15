/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.collections4.map.AbstractHashedMap;

public abstract class AbstractReferenceMap<K, V>
extends AbstractHashedMap<K, V> {
    private ReferenceStrength keyType;
    private ReferenceStrength valueType;
    private boolean purgeValues;
    private transient ReferenceQueue<Object> queue;

    protected AbstractReferenceMap() {
    }

    protected AbstractReferenceMap(ReferenceStrength keyType, ReferenceStrength valueType, int capacity, float loadFactor, boolean purgeValues) {
        super(capacity, loadFactor);
        this.keyType = keyType;
        this.valueType = valueType;
        this.purgeValues = purgeValues;
    }

    @Override
    protected void init() {
        this.queue = new ReferenceQueue();
    }

    @Override
    public int size() {
        this.purgeBeforeRead();
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        this.purgeBeforeRead();
        return super.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        this.purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> entry = this.getEntry(key);
        if (entry == null) {
            return false;
        }
        return entry.getValue() != null;
    }

    @Override
    public boolean containsValue(Object value) {
        this.purgeBeforeRead();
        if (value == null) {
            return false;
        }
        return super.containsValue(value);
    }

    @Override
    public V get(Object key) {
        this.purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> entry = this.getEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("null keys not allowed");
        }
        if (value == null) {
            throw new NullPointerException("null values not allowed");
        }
        this.purgeBeforeWrite();
        return super.put(key, value);
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        this.purgeBeforeWrite();
        return super.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
        while (this.queue.poll() != null) {
        }
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return new ReferenceMapIterator(this);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new ReferenceEntrySet(this);
        }
        return this.entrySet;
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new ReferenceKeySet(this);
        }
        return this.keySet;
    }

    @Override
    public Collection<V> values() {
        if (this.values == null) {
            this.values = new ReferenceValues(this);
        }
        return this.values;
    }

    protected void purgeBeforeRead() {
        this.purge();
    }

    protected void purgeBeforeWrite() {
        this.purge();
    }

    protected void purge() {
        Reference<Object> ref = this.queue.poll();
        while (ref != null) {
            this.purge(ref);
            ref = this.queue.poll();
        }
    }

    protected void purge(Reference<?> ref) {
        int hash = ref.hashCode();
        int index = this.hashIndex(hash, this.data.length);
        AbstractHashedMap.HashEntry previous = null;
        AbstractHashedMap.HashEntry entry = this.data[index];
        while (entry != null) {
            ReferenceEntry refEntry = (ReferenceEntry)entry;
            if (refEntry.purge(ref)) {
                if (previous == null) {
                    this.data[index] = entry.next;
                } else {
                    previous.next = entry.next;
                }
                --this.size;
                refEntry.onPurge();
                return;
            }
            previous = entry;
            entry = entry.next;
        }
    }

    @Override
    protected AbstractHashedMap.HashEntry<K, V> getEntry(Object key) {
        if (key == null) {
            return null;
        }
        return super.getEntry(key);
    }

    protected int hashEntry(Object key, Object value) {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    protected boolean isEqualKey(Object key1, Object key2) {
        key2 = this.keyType == ReferenceStrength.HARD ? key2 : ((Reference)key2).get();
        return key1 == key2 || key1.equals(key2);
    }

    @Override
    protected ReferenceEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value) {
        return new ReferenceEntry<K, V>(this, next, hashCode, key, value);
    }

    @Override
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        return new ReferenceEntrySetIterator(this);
    }

    @Override
    protected Iterator<K> createKeySetIterator() {
        return new ReferenceKeySetIterator(this);
    }

    @Override
    protected Iterator<V> createValuesIterator() {
        return new ReferenceValuesIterator(this);
    }

    @Override
    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.keyType.value);
        out.writeInt(this.valueType.value);
        out.writeBoolean(this.purgeValues);
        out.writeFloat(this.loadFactor);
        out.writeInt(this.data.length);
        MapIterator<K, V> it = this.mapIterator();
        while (it.hasNext()) {
            out.writeObject(it.next());
            out.writeObject(it.getValue());
        }
        out.writeObject(null);
    }

    @Override
    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Object key;
        this.keyType = ReferenceStrength.resolve(in.readInt());
        this.valueType = ReferenceStrength.resolve(in.readInt());
        this.purgeValues = in.readBoolean();
        this.loadFactor = in.readFloat();
        int capacity = in.readInt();
        this.init();
        this.data = new AbstractHashedMap.HashEntry[capacity];
        this.threshold = this.calculateThreshold(this.data.length, this.loadFactor);
        while ((key = in.readObject()) != null) {
            Object value = in.readObject();
            this.put(key, value);
        }
    }

    protected boolean isKeyType(ReferenceStrength type) {
        return this.keyType == type;
    }

    protected boolean isValueType(ReferenceStrength type) {
        return this.valueType == type;
    }

    static class WeakRef<T>
    extends WeakReference<T> {
        private final int hash;

        public WeakRef(int hash, T r, ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    static class SoftRef<T>
    extends SoftReference<T> {
        private final int hash;

        public SoftRef(int hash, T r, ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    static class ReferenceMapIterator<K, V>
    extends ReferenceBaseIterator<K, V>
    implements MapIterator<K, V> {
        protected ReferenceMapIterator(AbstractReferenceMap<K, V> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }

        @Override
        public K getKey() {
            ReferenceEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            return ((AbstractHashedMap.HashEntry)current).getKey();
        }

        @Override
        public V getValue() {
            ReferenceEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            return ((AbstractHashedMap.HashEntry)current).getValue();
        }

        @Override
        public V setValue(V value) {
            ReferenceEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            return ((AbstractHashedMap.HashEntry)current).setValue(value);
        }
    }

    static class ReferenceValuesIterator<V>
    extends ReferenceBaseIterator<Object, V>
    implements Iterator<V> {
        ReferenceValuesIterator(AbstractReferenceMap<?, V> parent) {
            super(parent);
        }

        @Override
        public V next() {
            return this.nextEntry().getValue();
        }
    }

    static class ReferenceKeySetIterator<K>
    extends ReferenceBaseIterator<K, Object>
    implements Iterator<K> {
        ReferenceKeySetIterator(AbstractReferenceMap<K, ?> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return this.nextEntry().getKey();
        }
    }

    static class ReferenceEntrySetIterator<K, V>
    extends ReferenceBaseIterator<K, V>
    implements Iterator<Map.Entry<K, V>> {
        public ReferenceEntrySetIterator(AbstractReferenceMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.nextEntry();
        }
    }

    static class ReferenceBaseIterator<K, V> {
        final AbstractReferenceMap<K, V> parent;
        int index;
        ReferenceEntry<K, V> entry;
        ReferenceEntry<K, V> previous;
        K currentKey;
        K nextKey;
        V currentValue;
        V nextValue;
        int expectedModCount;

        public ReferenceBaseIterator(AbstractReferenceMap<K, V> parent) {
            this.parent = parent;
            this.index = parent.size() != 0 ? parent.data.length : 0;
            this.expectedModCount = parent.modCount;
        }

        public boolean hasNext() {
            this.checkMod();
            while (this.nextNull()) {
                ReferenceEntry e = this.entry;
                int i = this.index;
                while (e == null && i > 0) {
                    e = (ReferenceEntry)this.parent.data[--i];
                }
                this.entry = e;
                this.index = i;
                if (e == null) {
                    this.currentKey = null;
                    this.currentValue = null;
                    return false;
                }
                this.nextKey = e.getKey();
                this.nextValue = e.getValue();
                if (!this.nextNull()) continue;
                this.entry = this.entry.next();
            }
            return true;
        }

        private void checkMod() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean nextNull() {
            return this.nextKey == null || this.nextValue == null;
        }

        protected ReferenceEntry<K, V> nextEntry() {
            this.checkMod();
            if (this.nextNull() && !this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.previous = this.entry;
            this.entry = this.entry.next();
            this.currentKey = this.nextKey;
            this.currentValue = this.nextValue;
            this.nextKey = null;
            this.nextValue = null;
            return this.previous;
        }

        protected ReferenceEntry<K, V> currentEntry() {
            this.checkMod();
            return this.previous;
        }

        public void remove() {
            this.checkMod();
            if (this.previous == null) {
                throw new IllegalStateException();
            }
            this.parent.remove(this.currentKey);
            this.previous = null;
            this.currentKey = null;
            this.currentValue = null;
            this.expectedModCount = this.parent.modCount;
        }
    }

    protected static class ReferenceEntry<K, V>
    extends AbstractHashedMap.HashEntry<K, V> {
        private final AbstractReferenceMap<K, V> parent;

        public ReferenceEntry(AbstractReferenceMap<K, V> parent, AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value) {
            super(next, hashCode, null, null);
            this.parent = parent;
            this.key = this.toReference(((AbstractReferenceMap)parent).keyType, key, hashCode);
            this.value = this.toReference(((AbstractReferenceMap)parent).valueType, value, hashCode);
        }

        @Override
        public K getKey() {
            return (K)(((AbstractReferenceMap)this.parent).keyType == ReferenceStrength.HARD ? this.key : ((Reference)this.key).get());
        }

        @Override
        public V getValue() {
            return (V)(((AbstractReferenceMap)this.parent).valueType == ReferenceStrength.HARD ? this.value : ((Reference)this.value).get());
        }

        @Override
        public V setValue(V obj) {
            V old = this.getValue();
            if (((AbstractReferenceMap)this.parent).valueType != ReferenceStrength.HARD) {
                ((Reference)this.value).clear();
            }
            this.value = this.toReference(((AbstractReferenceMap)this.parent).valueType, obj, this.hashCode);
            return old;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object entryKey = entry.getKey();
            Object entryValue = entry.getValue();
            if (entryKey == null || entryValue == null) {
                return false;
            }
            return this.parent.isEqualKey(entryKey, this.key) && this.parent.isEqualValue(entryValue, this.getValue());
        }

        @Override
        public int hashCode() {
            return this.parent.hashEntry(this.getKey(), this.getValue());
        }

        protected <T> Object toReference(ReferenceStrength type, T referent, int hash) {
            if (type == ReferenceStrength.HARD) {
                return referent;
            }
            if (type == ReferenceStrength.SOFT) {
                return new SoftRef<T>(hash, referent, ((AbstractReferenceMap)this.parent).queue);
            }
            if (type == ReferenceStrength.WEAK) {
                return new WeakRef<T>(hash, referent, ((AbstractReferenceMap)this.parent).queue);
            }
            throw new Error();
        }

        protected void onPurge() {
        }

        protected boolean purge(Reference<?> ref) {
            boolean r = ((AbstractReferenceMap)this.parent).keyType != ReferenceStrength.HARD && this.key == ref;
            boolean bl = r = r || ((AbstractReferenceMap)this.parent).valueType != ReferenceStrength.HARD && this.value == ref;
            if (r) {
                if (((AbstractReferenceMap)this.parent).keyType != ReferenceStrength.HARD) {
                    ((Reference)this.key).clear();
                }
                if (((AbstractReferenceMap)this.parent).valueType != ReferenceStrength.HARD) {
                    ((Reference)this.value).clear();
                } else if (((AbstractReferenceMap)this.parent).purgeValues) {
                    this.nullValue();
                }
            }
            return r;
        }

        protected ReferenceEntry<K, V> next() {
            return (ReferenceEntry)this.next;
        }

        protected void nullValue() {
            this.value = null;
        }
    }

    static class ReferenceValues<V>
    extends AbstractHashedMap.Values<V> {
        protected ReferenceValues(AbstractHashedMap<?, V> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            return this.toArray(new Object[this.size()]);
        }

        @Override
        public <T> T[] toArray(T[] arr) {
            ArrayList list = new ArrayList(this.size());
            for (Object value : this) {
                list.add(value);
            }
            return list.toArray(arr);
        }
    }

    static class ReferenceKeySet<K>
    extends AbstractHashedMap.KeySet<K> {
        protected ReferenceKeySet(AbstractHashedMap<K, ?> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            return this.toArray(new Object[this.size()]);
        }

        @Override
        public <T> T[] toArray(T[] arr) {
            ArrayList list = new ArrayList(this.size());
            for (Object key : this) {
                list.add(key);
            }
            return list.toArray(arr);
        }
    }

    static class ReferenceEntrySet<K, V>
    extends AbstractHashedMap.EntrySet<K, V> {
        protected ReferenceEntrySet(AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            return this.toArray(new Object[this.size()]);
        }

        @Override
        public <T> T[] toArray(T[] arr) {
            ArrayList list = new ArrayList(this.size());
            for (Map.Entry entry : this) {
                list.add(new DefaultMapEntry(entry));
            }
            return list.toArray(arr);
        }
    }

    public static enum ReferenceStrength {
        HARD(0),
        SOFT(1),
        WEAK(2);

        public final int value;

        public static ReferenceStrength resolve(int value) {
            switch (value) {
                case 0: {
                    return HARD;
                }
                case 1: {
                    return SOFT;
                }
                case 2: {
                    return WEAK;
                }
            }
            throw new IllegalArgumentException();
        }

        private ReferenceStrength(int value) {
            this.value = value;
        }
    }
}

