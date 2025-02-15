/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.AbstractLinkedMap;

public class LRUMap<K, V>
extends AbstractLinkedMap<K, V>
implements BoundedMap<K, V>,
Serializable,
Cloneable {
    private static final long serialVersionUID = -612114643488955218L;
    protected static final int DEFAULT_MAX_SIZE = 100;
    private transient int maxSize;
    private boolean scanUntilRemovable;

    public LRUMap() {
        this(100, 0.75f, false);
    }

    public LRUMap(int maxSize) {
        this(maxSize, 0.75f);
    }

    public LRUMap(int maxSize, int initialSize) {
        this(maxSize, initialSize, 0.75f);
    }

    public LRUMap(int maxSize, boolean scanUntilRemovable) {
        this(maxSize, 0.75f, scanUntilRemovable);
    }

    public LRUMap(int maxSize, float loadFactor) {
        this(maxSize, loadFactor, false);
    }

    public LRUMap(int maxSize, int initialSize, float loadFactor) {
        this(maxSize, initialSize, loadFactor, false);
    }

    public LRUMap(int maxSize, float loadFactor, boolean scanUntilRemovable) {
        this(maxSize, maxSize, loadFactor, scanUntilRemovable);
    }

    public LRUMap(int maxSize, int initialSize, float loadFactor, boolean scanUntilRemovable) {
        super(initialSize, loadFactor);
        if (maxSize < 1) {
            throw new IllegalArgumentException("LRUMap max size must be greater than 0");
        }
        if (initialSize > maxSize) {
            throw new IllegalArgumentException("LRUMap initial size must not be greather than max size");
        }
        this.maxSize = maxSize;
        this.scanUntilRemovable = scanUntilRemovable;
    }

    public LRUMap(Map<? extends K, ? extends V> map) {
        this(map, false);
    }

    public LRUMap(Map<? extends K, ? extends V> map, boolean scanUntilRemovable) {
        this(map.size(), 0.75f, scanUntilRemovable);
        this.putAll(map);
    }

    @Override
    public V get(Object key) {
        return this.get(key, true);
    }

    public V get(Object key, boolean updateToMRU) {
        AbstractHashedMap.HashEntry entry = this.getEntry(key);
        if (entry == null) {
            return null;
        }
        if (updateToMRU) {
            this.moveToMRU((AbstractLinkedMap.LinkEntry<K, V>)entry);
        }
        return entry.getValue();
    }

    protected void moveToMRU(AbstractLinkedMap.LinkEntry<K, V> entry) {
        if (entry.after != this.header) {
            ++this.modCount;
            if (entry.before == null) {
                throw new IllegalStateException("Entry.before is null. This should not occur if your keys are immutable, and you have used synchronization properly.");
            }
            entry.before.after = entry.after;
            entry.after.before = entry.before;
            entry.after = this.header;
            entry.before = this.header.before;
            this.header.before.after = entry;
            this.header.before = entry;
        } else if (entry == this.header) {
            throw new IllegalStateException("Can't move header to MRU This should not occur if your keys are immutable, and you have used synchronization properly.");
        }
    }

    @Override
    protected void updateEntry(AbstractHashedMap.HashEntry<K, V> entry, V newValue) {
        this.moveToMRU((AbstractLinkedMap.LinkEntry)entry);
        entry.setValue(newValue);
    }

    @Override
    protected void addMapping(int hashIndex, int hashCode, K key, V value) {
        if (this.isFull()) {
            AbstractLinkedMap.LinkEntry reuse = this.header.after;
            boolean removeLRUEntry = false;
            if (this.scanUntilRemovable) {
                while (reuse != this.header && reuse != null) {
                    if (this.removeLRU(reuse)) {
                        removeLRUEntry = true;
                        break;
                    }
                    reuse = reuse.after;
                }
                if (reuse == null) {
                    throw new IllegalStateException("Entry.after=null, header.after=" + this.header.after + " header.before=" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
                }
            } else {
                removeLRUEntry = this.removeLRU(reuse);
            }
            if (removeLRUEntry) {
                if (reuse == null) {
                    throw new IllegalStateException("reuse=null, header.after=" + this.header.after + " header.before=" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
                }
                this.reuseMapping(reuse, hashIndex, hashCode, key, value);
            } else {
                super.addMapping(hashIndex, hashCode, key, value);
            }
        } else {
            super.addMapping(hashIndex, hashCode, key, value);
        }
    }

    protected void reuseMapping(AbstractLinkedMap.LinkEntry<K, V> entry, int hashIndex, int hashCode, K key, V value) {
        try {
            int removeIndex = this.hashIndex(entry.hashCode, this.data.length);
            AbstractHashedMap.HashEntry[] tmp = this.data;
            AbstractHashedMap.HashEntry loop = tmp[removeIndex];
            AbstractHashedMap.HashEntry previous = null;
            while (loop != entry && loop != null) {
                previous = loop;
                loop = loop.next;
            }
            if (loop == null) {
                throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + this.data[removeIndex] + " previous=" + previous + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
            }
            ++this.modCount;
            this.removeEntry(entry, removeIndex, previous);
            this.reuseEntry(entry, hashIndex, hashCode, key, value);
            this.addEntry(entry, hashIndex);
        } catch (NullPointerException ex) {
            throw new IllegalStateException("NPE, entry=" + entry + " entryIsHeader=" + (entry == this.header) + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
        }
    }

    protected boolean removeLRU(AbstractLinkedMap.LinkEntry<K, V> entry) {
        return true;
    }

    @Override
    public boolean isFull() {
        return this.size >= this.maxSize;
    }

    @Override
    public int maxSize() {
        return this.maxSize;
    }

    public boolean isScanUntilRemovable() {
        return this.scanUntilRemovable;
    }

    @Override
    public LRUMap<K, V> clone() {
        return (LRUMap)super.clone();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        this.doWriteObject(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.doReadObject(in);
    }

    @Override
    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.maxSize);
        super.doWriteObject(out);
    }

    @Override
    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.maxSize = in.readInt();
        super.doReadObject(in);
    }
}

