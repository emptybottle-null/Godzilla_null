/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.map.AbstractHashedMap;

public abstract class AbstractLinkedMap<K, V>
extends AbstractHashedMap<K, V>
implements OrderedMap<K, V> {
    transient LinkEntry<K, V> header;

    protected AbstractLinkedMap() {
    }

    protected AbstractLinkedMap(int initialCapacity, float loadFactor, int threshold) {
        super(initialCapacity, loadFactor, threshold);
    }

    protected AbstractLinkedMap(int initialCapacity) {
        super(initialCapacity);
    }

    protected AbstractLinkedMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    protected AbstractLinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    protected void init() {
        this.header = this.createEntry((AbstractHashedMap.HashEntry)null, -1, (Object)null, (Object)null);
        this.header.after = this.header;
        this.header.before = this.header.after;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            LinkEntry entry = this.header.after;
            while (entry != this.header) {
                if (entry.getValue() == null) {
                    return true;
                }
                entry = entry.after;
            }
        } else {
            LinkEntry entry = this.header.after;
            while (entry != this.header) {
                if (this.isEqualValue(value, entry.getValue())) {
                    return true;
                }
                entry = entry.after;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        this.header.after = this.header;
        this.header.before = this.header.after;
    }

    @Override
    public K firstKey() {
        if (this.size == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return this.header.after.getKey();
    }

    @Override
    public K lastKey() {
        if (this.size == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return this.header.before.getKey();
    }

    @Override
    public K nextKey(Object key) {
        AbstractHashedMap.HashEntry entry = this.getEntry(key);
        return entry == null || ((LinkEntry)entry).after == this.header ? null : (K)((LinkEntry)entry).after.getKey();
    }

    @Override
    protected LinkEntry<K, V> getEntry(Object key) {
        return (LinkEntry)super.getEntry(key);
    }

    @Override
    public K previousKey(Object key) {
        AbstractHashedMap.HashEntry entry = this.getEntry(key);
        return entry == null || ((LinkEntry)entry).before == this.header ? null : (K)((LinkEntry)entry).before.getKey();
    }

    protected LinkEntry<K, V> getEntry(int index) {
        LinkEntry<K, V> entry;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is less than zero");
        }
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is invalid for size " + this.size);
        }
        if (index < this.size / 2) {
            entry = this.header.after;
            for (int currentIndex = 0; currentIndex < index; ++currentIndex) {
                entry = entry.after;
            }
        } else {
            entry = this.header;
            for (int currentIndex = this.size; currentIndex > index; --currentIndex) {
                entry = entry.before;
            }
        }
        return entry;
    }

    @Override
    protected void addEntry(AbstractHashedMap.HashEntry<K, V> entry, int hashIndex) {
        LinkEntry link = (LinkEntry)entry;
        link.after = this.header;
        link.before = this.header.before;
        this.header.before.after = link;
        this.header.before = link;
        this.data[hashIndex] = link;
    }

    @Override
    protected LinkEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value) {
        return new LinkEntry<K, V>(next, hashCode, this.convertKey(key), value);
    }

    @Override
    protected void removeEntry(AbstractHashedMap.HashEntry<K, V> entry, int hashIndex, AbstractHashedMap.HashEntry<K, V> previous) {
        LinkEntry link = (LinkEntry)entry;
        link.before.after = link.after;
        link.after.before = link.before;
        link.after = null;
        link.before = null;
        super.removeEntry(entry, hashIndex, previous);
    }

    protected LinkEntry<K, V> entryBefore(LinkEntry<K, V> entry) {
        return entry.before;
    }

    protected LinkEntry<K, V> entryAfter(LinkEntry<K, V> entry) {
        return entry.after;
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        if (this.size == 0) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new LinkMapIterator(this);
    }

    @Override
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        if (this.size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new EntrySetIterator(this);
    }

    @Override
    protected Iterator<K> createKeySetIterator() {
        if (this.size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new KeySetIterator(this);
    }

    @Override
    protected Iterator<V> createValuesIterator() {
        if (this.size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new ValuesIterator(this);
    }

    protected static abstract class LinkIterator<K, V> {
        protected final AbstractLinkedMap<K, V> parent;
        protected LinkEntry<K, V> last;
        protected LinkEntry<K, V> next;
        protected int expectedModCount;

        protected LinkIterator(AbstractLinkedMap<K, V> parent) {
            this.parent = parent;
            this.next = parent.header.after;
            this.expectedModCount = parent.modCount;
        }

        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        public boolean hasPrevious() {
            return this.next.before != this.parent.header;
        }

        protected LinkEntry<K, V> nextEntry() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (this.next == this.parent.header) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.last = this.next;
            this.next = this.next.after;
            return this.last;
        }

        protected LinkEntry<K, V> previousEntry() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            LinkEntry previous = this.next.before;
            if (previous == this.parent.header) {
                throw new NoSuchElementException("No previous() entry in the iteration");
            }
            this.next = previous;
            this.last = previous;
            return this.last;
        }

        protected LinkEntry<K, V> currentEntry() {
            return this.last;
        }

        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.parent.remove(this.last.getKey());
            this.last = null;
            this.expectedModCount = this.parent.modCount;
        }

        public void reset() {
            this.last = null;
            this.next = this.parent.header.after;
        }

        public String toString() {
            if (this.last != null) {
                return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
            }
            return "Iterator[]";
        }
    }

    protected static class LinkEntry<K, V>
    extends AbstractHashedMap.HashEntry<K, V> {
        protected LinkEntry<K, V> before;
        protected LinkEntry<K, V> after;

        protected LinkEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, Object key, V value) {
            super(next, hashCode, key, value);
        }
    }

    protected static class ValuesIterator<V>
    extends LinkIterator<Object, V>
    implements OrderedIterator<V>,
    ResettableIterator<V> {
        protected ValuesIterator(AbstractLinkedMap<?, V> parent) {
            super(parent);
        }

        @Override
        public V next() {
            return super.nextEntry().getValue();
        }

        @Override
        public V previous() {
            return super.previousEntry().getValue();
        }
    }

    protected static class KeySetIterator<K>
    extends LinkIterator<K, Object>
    implements OrderedIterator<K>,
    ResettableIterator<K> {
        protected KeySetIterator(AbstractLinkedMap<K, ?> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return super.nextEntry().getKey();
        }

        @Override
        public K previous() {
            return super.previousEntry().getKey();
        }
    }

    protected static class EntrySetIterator<K, V>
    extends LinkIterator<K, V>
    implements OrderedIterator<Map.Entry<K, V>>,
    ResettableIterator<Map.Entry<K, V>> {
        protected EntrySetIterator(AbstractLinkedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            return super.nextEntry();
        }

        @Override
        public Map.Entry<K, V> previous() {
            return super.previousEntry();
        }
    }

    protected static class LinkMapIterator<K, V>
    extends LinkIterator<K, V>
    implements OrderedMapIterator<K, V>,
    ResettableIterator<K> {
        protected LinkMapIterator(AbstractLinkedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public K next() {
            return super.nextEntry().getKey();
        }

        @Override
        public K previous() {
            return super.previousEntry().getKey();
        }

        @Override
        public K getKey() {
            LinkEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            return current.getKey();
        }

        @Override
        public V getValue() {
            LinkEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            return current.getValue();
        }

        @Override
        public V setValue(V value) {
            LinkEntry current = this.currentEntry();
            if (current == null) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            return current.setValue(value);
        }
    }
}

