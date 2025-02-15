/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Transformer;

public abstract class AbstractMultiSet<E>
extends AbstractCollection<E>
implements MultiSet<E> {
    private transient Set<E> uniqueSet;
    private transient Set<MultiSet.Entry<E>> entrySet;

    protected AbstractMultiSet() {
    }

    @Override
    public int size() {
        int totalSize = 0;
        for (MultiSet.Entry<E> entry : this.entrySet()) {
            totalSize += entry.getCount();
        }
        return totalSize;
    }

    @Override
    public int getCount(Object object) {
        for (MultiSet.Entry<E> entry : this.entrySet()) {
            E element = entry.getElement();
            if (element != object && (element == null || !element.equals(object))) continue;
            return entry.getCount();
        }
        return 0;
    }

    @Override
    public int setCount(E object, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must not be negative.");
        }
        int oldCount = this.getCount(object);
        if (oldCount < count) {
            this.add(object, count - oldCount);
        } else {
            this.remove(object, oldCount - count);
        }
        return oldCount;
    }

    @Override
    public boolean contains(Object object) {
        return this.getCount(object) > 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MultiSetIterator(this);
    }

    @Override
    public boolean add(E object) {
        this.add(object, 1);
        return true;
    }

    @Override
    public int add(E object, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        Iterator<MultiSet.Entry<E>> it = this.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @Override
    public boolean remove(Object object) {
        return this.remove(object, 1) != 0;
    }

    @Override
    public int remove(Object object, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean result = false;
        for (Object obj : coll) {
            boolean changed = this.remove(obj, this.getCount(obj)) != 0;
            result = result || changed;
        }
        return result;
    }

    @Override
    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = this.createUniqueSet();
        }
        return this.uniqueSet;
    }

    protected Set<E> createUniqueSet() {
        return new UniqueSet(this);
    }

    protected Iterator<E> createUniqueSetIterator() {
        Transformer transformer = new Transformer<MultiSet.Entry<E>, E>(){

            @Override
            public E transform(MultiSet.Entry<E> entry) {
                return entry.getElement();
            }
        };
        return IteratorUtils.transformedIterator(this.entrySet().iterator(), transformer);
    }

    @Override
    public Set<MultiSet.Entry<E>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = this.createEntrySet();
        }
        return this.entrySet;
    }

    protected Set<MultiSet.Entry<E>> createEntrySet() {
        return new EntrySet(this);
    }

    protected abstract int uniqueElements();

    protected abstract Iterator<MultiSet.Entry<E>> createEntrySetIterator();

    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.entrySet().size());
        for (MultiSet.Entry<E> entry : this.entrySet()) {
            out.writeObject(entry.getElement());
            out.writeInt(entry.getCount());
        }
    }

    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int entrySize = in.readInt();
        for (int i = 0; i < entrySize; ++i) {
            Object obj = in.readObject();
            int count = in.readInt();
            this.setCount(obj, count);
        }
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
        for (MultiSet.Entry<E> entry : this.entrySet()) {
            if (other.getCount(entry.getElement()) == this.getCount(entry.getElement())) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    public String toString() {
        return this.entrySet().toString();
    }

    protected static abstract class AbstractEntry<E>
    implements MultiSet.Entry<E> {
        protected AbstractEntry() {
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof MultiSet.Entry) {
                MultiSet.Entry other = (MultiSet.Entry)object;
                Object element = this.getElement();
                Object otherElement = other.getElement();
                return this.getCount() == other.getCount() && (element == otherElement || element != null && element.equals(otherElement));
            }
            return false;
        }

        @Override
        public int hashCode() {
            Object element = this.getElement();
            return (element == null ? 0 : element.hashCode()) ^ this.getCount();
        }

        public String toString() {
            return String.format("%s:%d", this.getElement(), this.getCount());
        }
    }

    protected static class EntrySet<E>
    extends AbstractSet<MultiSet.Entry<E>> {
        private final AbstractMultiSet<E> parent;

        protected EntrySet(AbstractMultiSet<E> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.uniqueElements();
        }

        @Override
        public Iterator<MultiSet.Entry<E>> iterator() {
            return this.parent.createEntrySetIterator();
        }

        @Override
        public boolean contains(Object obj) {
            if (!(obj instanceof MultiSet.Entry)) {
                return false;
            }
            MultiSet.Entry entry = (MultiSet.Entry)obj;
            Object element = entry.getElement();
            return this.parent.getCount(element) == entry.getCount();
        }

        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof MultiSet.Entry)) {
                return false;
            }
            MultiSet.Entry entry = (MultiSet.Entry)obj;
            Object element = entry.getElement();
            if (this.parent.contains(element)) {
                int count = this.parent.getCount(element);
                if (entry.getCount() == count) {
                    this.parent.remove(element, count);
                    return true;
                }
            }
            return false;
        }
    }

    protected static class UniqueSet<E>
    extends AbstractSet<E> {
        protected final AbstractMultiSet<E> parent;

        protected UniqueSet(AbstractMultiSet<E> parent) {
            this.parent = parent;
        }

        @Override
        public Iterator<E> iterator() {
            return this.parent.createUniqueSetIterator();
        }

        @Override
        public boolean contains(Object key) {
            return this.parent.contains(key);
        }

        @Override
        public boolean containsAll(Collection<?> coll) {
            return this.parent.containsAll(coll);
        }

        @Override
        public boolean remove(Object key) {
            return this.parent.remove(key, this.parent.getCount(key)) != 0;
        }

        @Override
        public int size() {
            return this.parent.uniqueElements();
        }

        @Override
        public void clear() {
            this.parent.clear();
        }
    }

    private static class MultiSetIterator<E>
    implements Iterator<E> {
        private final AbstractMultiSet<E> parent;
        private final Iterator<MultiSet.Entry<E>> entryIterator;
        private MultiSet.Entry<E> current;
        private int itemCount;
        private boolean canRemove;

        public MultiSetIterator(AbstractMultiSet<E> parent) {
            this.parent = parent;
            this.entryIterator = parent.entrySet().iterator();
            this.current = null;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        @Override
        public E next() {
            if (this.itemCount == 0) {
                this.current = this.entryIterator.next();
                this.itemCount = this.current.getCount();
            }
            this.canRemove = true;
            --this.itemCount;
            return this.current.getElement();
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException();
            }
            int count = this.current.getCount();
            if (count > 1) {
                this.parent.remove(this.current.getElement());
            } else {
                this.entryIterator.remove();
            }
            this.canRemove = false;
        }
    }
}

