/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.multiset.AbstractMultiSet;

public abstract class AbstractMapMultiSet<E>
extends AbstractMultiSet<E> {
    private transient Map<E, MutableInteger> map;
    private transient int size;
    private transient int modCount;

    protected AbstractMapMultiSet() {
    }

    protected AbstractMapMultiSet(Map<E, MutableInteger> map) {
        this.map = map;
    }

    protected Map<E, MutableInteger> getMap() {
        return this.map;
    }

    protected void setMap(Map<E, MutableInteger> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public int getCount(Object object) {
        MutableInteger count = this.map.get(object);
        if (count != null) {
            return count.value;
        }
        return 0;
    }

    @Override
    public boolean contains(Object object) {
        return this.map.containsKey(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new MapBasedMultiSetIterator(this);
    }

    @Override
    public int add(E object, int occurrences) {
        int oldCount;
        if (occurrences < 0) {
            throw new IllegalArgumentException("Occurrences must not be negative.");
        }
        MutableInteger mut = this.map.get(object);
        int n = oldCount = mut != null ? mut.value : 0;
        if (occurrences > 0) {
            ++this.modCount;
            this.size += occurrences;
            if (mut == null) {
                this.map.put(object, new MutableInteger(occurrences));
            } else {
                mut.value += occurrences;
            }
        }
        return oldCount;
    }

    @Override
    public void clear() {
        ++this.modCount;
        this.map.clear();
        this.size = 0;
    }

    @Override
    public int remove(Object object, int occurrences) {
        if (occurrences < 0) {
            throw new IllegalArgumentException("Occurrences must not be negative.");
        }
        MutableInteger mut = this.map.get(object);
        if (mut == null) {
            return 0;
        }
        int oldCount = mut.value;
        if (occurrences > 0) {
            ++this.modCount;
            if (occurrences < mut.value) {
                mut.value -= occurrences;
                this.size -= occurrences;
            } else {
                this.map.remove(object);
                this.size -= mut.value;
                mut.value = 0;
            }
        }
        return oldCount;
    }

    @Override
    protected Iterator<E> createUniqueSetIterator() {
        return new UniqueSetIterator<E>(this.getMap().keySet().iterator(), this);
    }

    @Override
    protected int uniqueElements() {
        return this.map.size();
    }

    @Override
    protected Iterator<MultiSet.Entry<E>> createEntrySetIterator() {
        return new EntrySetIterator<E>(this.map.entrySet().iterator(), this);
    }

    @Override
    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.map.size());
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeInt(entry.getValue().value);
        }
    }

    @Override
    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int entrySize = in.readInt();
        for (int i = 0; i < entrySize; ++i) {
            Object obj = in.readObject();
            int count = in.readInt();
            this.map.put(obj, new MutableInteger(count));
            this.size += count;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        int i = 0;
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            E current = entry.getKey();
            MutableInteger count = entry.getValue();
            for (int index = count.value; index > 0; --index) {
                result[i++] = current;
            }
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int size = this.size();
        if (array.length < size) {
            Object[] unchecked = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
            array = unchecked;
        }
        int i = 0;
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            E current = entry.getKey();
            MutableInteger count = entry.getValue();
            for (int index = count.value; index > 0; --index) {
                E unchecked = current;
                array[i++] = unchecked;
            }
        }
        while (i < array.length) {
            array[i++] = null;
        }
        return array;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof MultiSet)) {
            return false;
        }
        MultiSet other = (MultiSet)object;
        if (other.size() != this.size()) {
            return false;
        }
        for (E element : this.map.keySet()) {
            if (other.getCount(element) == this.getCount(element)) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            E element = entry.getKey();
            MutableInteger count = entry.getValue();
            total += (element == null ? 0 : element.hashCode()) ^ count.value;
        }
        return total;
    }

    protected static class MultiSetEntry<E>
    extends AbstractMultiSet.AbstractEntry<E> {
        protected final Map.Entry<E, MutableInteger> parentEntry;

        protected MultiSetEntry(Map.Entry<E, MutableInteger> parentEntry) {
            this.parentEntry = parentEntry;
        }

        @Override
        public E getElement() {
            return this.parentEntry.getKey();
        }

        @Override
        public int getCount() {
            return this.parentEntry.getValue().value;
        }
    }

    protected static class EntrySetIterator<E>
    implements Iterator<MultiSet.Entry<E>> {
        protected final AbstractMapMultiSet<E> parent;
        protected final Iterator<Map.Entry<E, MutableInteger>> decorated;
        protected MultiSet.Entry<E> last = null;
        protected boolean canRemove = false;

        protected EntrySetIterator(Iterator<Map.Entry<E, MutableInteger>> iterator, AbstractMapMultiSet<E> parent) {
            this.decorated = iterator;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return this.decorated.hasNext();
        }

        @Override
        public MultiSet.Entry<E> next() {
            this.last = new MultiSetEntry<E>(this.decorated.next());
            this.canRemove = true;
            return this.last;
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            this.decorated.remove();
            this.last = null;
            this.canRemove = false;
        }
    }

    protected static class UniqueSetIterator<E>
    extends AbstractIteratorDecorator<E> {
        protected final AbstractMapMultiSet<E> parent;
        protected E lastElement = null;
        protected boolean canRemove = false;

        protected UniqueSetIterator(Iterator<E> iterator, AbstractMapMultiSet<E> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public E next() {
            this.lastElement = super.next();
            this.canRemove = true;
            return this.lastElement;
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            int count = this.parent.getCount(this.lastElement);
            super.remove();
            this.parent.remove(this.lastElement, count);
            this.lastElement = null;
            this.canRemove = false;
        }
    }

    protected static class MutableInteger {
        protected int value;

        MutableInteger(int value) {
            this.value = value;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MutableInteger)) {
                return false;
            }
            return ((MutableInteger)obj).value == this.value;
        }

        public int hashCode() {
            return this.value;
        }
    }

    private static class MapBasedMultiSetIterator<E>
    implements Iterator<E> {
        private final AbstractMapMultiSet<E> parent;
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private Map.Entry<E, MutableInteger> current;
        private int itemCount;
        private final int mods;
        private boolean canRemove;

        public MapBasedMultiSetIterator(AbstractMapMultiSet<E> parent) {
            this.parent = parent;
            this.entryIterator = ((AbstractMapMultiSet)parent).map.entrySet().iterator();
            this.current = null;
            this.mods = ((AbstractMapMultiSet)parent).modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        @Override
        public E next() {
            if (((AbstractMapMultiSet)this.parent).modCount != this.mods) {
                throw new ConcurrentModificationException();
            }
            if (this.itemCount == 0) {
                this.current = this.entryIterator.next();
                this.itemCount = this.current.getValue().value;
            }
            this.canRemove = true;
            --this.itemCount;
            return this.current.getKey();
        }

        @Override
        public void remove() {
            if (((AbstractMapMultiSet)this.parent).modCount != this.mods) {
                throw new ConcurrentModificationException();
            }
            if (!this.canRemove) {
                throw new IllegalStateException();
            }
            MutableInteger mut = this.current.getValue();
            if (mut.value > 1) {
                --mut.value;
            } else {
                this.entryIterator.remove();
            }
            ((AbstractMapMultiSet)this.parent).size--;
            this.canRemove = false;
        }
    }
}

