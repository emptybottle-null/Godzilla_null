/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.set.UnmodifiableSet;

public abstract class AbstractMapBag<E>
implements Bag<E> {
    private transient Map<E, MutableInteger> map;
    private int size;
    private transient int modCount;
    private transient Set<E> uniqueSet;

    protected AbstractMapBag() {
    }

    protected AbstractMapBag(Map<E, MutableInteger> map) {
        this.map = map;
    }

    protected Map<E, MutableInteger> getMap() {
        return this.map;
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
    public boolean containsAll(Collection<?> coll) {
        if (coll instanceof Bag) {
            return this.containsAll((Bag)coll);
        }
        return this.containsAll(new HashBag(coll));
    }

    @Override
    boolean containsAll(Bag<?> other) {
        for (Object current : other.uniqueSet()) {
            if (this.getCount(current) >= other.getCount(current)) continue;
            return false;
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new BagIterator(this);
    }

    @Override
    public boolean add(E object) {
        return this.add(object, 1);
    }

    @Override
    public boolean add(E object, int nCopies) {
        ++this.modCount;
        if (nCopies > 0) {
            MutableInteger mut = this.map.get(object);
            this.size += nCopies;
            if (mut == null) {
                this.map.put(object, new MutableInteger(nCopies));
                return true;
            }
            mut.value += nCopies;
            return false;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        boolean changed = false;
        Iterator<E> i = coll.iterator();
        while (i.hasNext()) {
            boolean added = this.add(i.next());
            changed = changed || added;
        }
        return changed;
    }

    @Override
    public void clear() {
        ++this.modCount;
        this.map.clear();
        this.size = 0;
    }

    @Override
    public boolean remove(Object object) {
        MutableInteger mut = this.map.get(object);
        if (mut == null) {
            return false;
        }
        ++this.modCount;
        this.map.remove(object);
        this.size -= mut.value;
        return true;
    }

    @Override
    public boolean remove(Object object, int nCopies) {
        MutableInteger mut = this.map.get(object);
        if (mut == null) {
            return false;
        }
        if (nCopies <= 0) {
            return false;
        }
        ++this.modCount;
        if (nCopies < mut.value) {
            mut.value -= nCopies;
            this.size -= nCopies;
        } else {
            this.map.remove(object);
            this.size -= mut.value;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean result = false;
        if (coll != null) {
            Iterator<?> i = coll.iterator();
            while (i.hasNext()) {
                boolean changed = this.remove(i.next(), 1);
                result = result || changed;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        if (coll instanceof Bag) {
            return this.retainAll((Bag)coll);
        }
        return this.retainAll(new HashBag(coll));
    }

    @Override
    boolean retainAll(Bag<?> other) {
        boolean result = false;
        HashBag<E> excess = new HashBag<E>();
        for (E current : this.uniqueSet()) {
            int myCount = this.getCount(current);
            int otherCount = other.getCount(current);
            if (1 <= otherCount && otherCount <= myCount) {
                excess.add(current, myCount - otherCount);
                continue;
            }
            excess.add(current, myCount);
        }
        if (!excess.isEmpty()) {
            result = this.removeAll(excess);
        }
        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        int i = 0;
        for (E current : this.map.keySet()) {
            for (int index = this.getCount(current); index > 0; --index) {
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
        for (E current : this.map.keySet()) {
            for (int index = this.getCount(current); index > 0; --index) {
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
    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = UnmodifiableSet.unmodifiableSet(this.map.keySet());
        }
        return this.uniqueSet;
    }

    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.map.size());
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeInt(entry.getValue().value);
        }
    }

    protected void doReadObject(Map<E, MutableInteger> map, ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.map = map;
        int entrySize = in.readInt();
        for (int i = 0; i < entrySize; ++i) {
            Object obj = in.readObject();
            int count = in.readInt();
            map.put(obj, new MutableInteger(count));
            this.size += count;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Bag)) {
            return false;
        }
        Bag other = (Bag)object;
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

    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        StringBuilder buf = new StringBuilder();
        buf.append('[');
        Iterator<E> it = this.uniqueSet().iterator();
        while (it.hasNext()) {
            E current = it.next();
            int count = this.getCount(current);
            buf.append(count);
            buf.append(':');
            buf.append(current);
            if (!it.hasNext()) continue;
            buf.append(',');
        }
        buf.append(']');
        return buf.toString();
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

    static class BagIterator<E>
    implements Iterator<E> {
        private final AbstractMapBag<E> parent;
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private Map.Entry<E, MutableInteger> current;
        private int itemCount;
        private final int mods;
        private boolean canRemove;

        public BagIterator(AbstractMapBag<E> parent) {
            this.parent = parent;
            this.entryIterator = ((AbstractMapBag)parent).map.entrySet().iterator();
            this.current = null;
            this.mods = ((AbstractMapBag)parent).modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        @Override
        public E next() {
            if (((AbstractMapBag)this.parent).modCount != this.mods) {
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
            if (((AbstractMapBag)this.parent).modCount != this.mods) {
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
            ((AbstractMapBag)this.parent).size--;
            this.canRemove = false;
        }
    }
}

