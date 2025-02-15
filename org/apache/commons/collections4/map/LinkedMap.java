/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.map.AbstractLinkedMap;

public class LinkedMap<K, V>
extends AbstractLinkedMap<K, V>
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 9077234323521161066L;

    public LinkedMap() {
        super(16, 0.75f, 12);
    }

    public LinkedMap(int initialCapacity) {
        super(initialCapacity);
    }

    public LinkedMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public LinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    public LinkedMap<K, V> clone() {
        return (LinkedMap)super.clone();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        this.doWriteObject(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.doReadObject(in);
    }

    public K get(int index) {
        return this.getEntry(index).getKey();
    }

    public V getValue(int index) {
        return this.getEntry(index).getValue();
    }

    public int indexOf(Object key) {
        key = this.convertKey(key);
        int i = 0;
        AbstractLinkedMap.LinkEntry entry = this.header.after;
        while (entry != this.header) {
            if (this.isEqualKey(key, entry.key)) {
                return i;
            }
            entry = entry.after;
            ++i;
        }
        return -1;
    }

    public V remove(int index) {
        return this.remove(this.get(index));
    }

    public List<K> asList() {
        return new LinkedMapList(this);
    }

    static class LinkedMapList<K>
    extends AbstractList<K> {
        private final LinkedMap<K, ?> parent;

        LinkedMapList(LinkedMap<K, ?> parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.size();
        }

        @Override
        public K get(int index) {
            return this.parent.get(index);
        }

        @Override
        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        @Override
        public int indexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        @Override
        public int lastIndexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        @Override
        public boolean containsAll(Collection<?> coll) {
            return this.parent.keySet().containsAll(coll);
        }

        @Override
        public K remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeIf(Predicate<? super K> filter) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> coll) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> coll) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray() {
            return this.parent.keySet().toArray();
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return this.parent.keySet().toArray(array);
        }

        @Override
        public Iterator<K> iterator() {
            return UnmodifiableIterator.unmodifiableIterator(this.parent.keySet().iterator());
        }

        @Override
        public ListIterator<K> listIterator() {
            return UnmodifiableListIterator.umodifiableListIterator(super.listIterator());
        }

        @Override
        public ListIterator<K> listIterator(int fromIndex) {
            return UnmodifiableListIterator.umodifiableListIterator(super.listIterator(fromIndex));
        }

        @Override
        public List<K> subList(int fromIndexInclusive, int toIndexExclusive) {
            return UnmodifiableList.unmodifiableList(super.subList(fromIndexInclusive, toIndexExclusive));
        }
    }
}

