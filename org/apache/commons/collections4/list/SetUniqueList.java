/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.list.AbstractSerializableListDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class SetUniqueList<E>
extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = 7196982186153478694L;
    private final Set<E> set;

    public static <E> SetUniqueList<E> setUniqueList(List<E> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        if (list.isEmpty()) {
            return new SetUniqueList<E>(list, new HashSet());
        }
        ArrayList<E> temp = new ArrayList<E>(list);
        list.clear();
        SetUniqueList<E> sl = new SetUniqueList<E>(list, new HashSet());
        sl.addAll(temp);
        return sl;
    }

    protected SetUniqueList(List<E> list, Set<E> set) {
        super(list);
        if (set == null) {
            throw new NullPointerException("Set must not be null");
        }
        this.set = set;
    }

    public Set<E> asSet() {
        return UnmodifiableSet.unmodifiableSet(this.set);
    }

    @Override
    public boolean add(E object) {
        int sizeBefore = this.size();
        this.add(this.size(), object);
        return sizeBefore != this.size();
    }

    @Override
    public void add(int index, E object) {
        if (!this.set.contains(object)) {
            this.set.add(object);
            super.add(index, object);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        return this.addAll(this.size(), coll);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        ArrayList<E> temp = new ArrayList<E>();
        for (E e : coll) {
            if (!this.set.add(e)) continue;
            temp.add(e);
        }
        return super.addAll(index, temp);
    }

    @Override
    public E set(int index, E object) {
        int pos = this.indexOf(object);
        E removed = super.set(index, object);
        if (pos != -1 && pos != index) {
            super.remove(pos);
        }
        this.set.remove(removed);
        this.set.add(object);
        return removed;
    }

    @Override
    public boolean remove(Object object) {
        boolean result = this.set.remove(object);
        if (result) {
            super.remove(object);
        }
        return result;
    }

    @Override
    public E remove(int index) {
        Object result = super.remove(index);
        this.set.remove(result);
        return result;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        boolean result = super.removeIf(filter);
        this.set.removeIf(filter);
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean result = false;
        for (Object name : coll) {
            result |= this.remove(name);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean result = this.set.retainAll(coll);
        if (!result) {
            return false;
        }
        if (this.set.size() == 0) {
            super.clear();
        } else {
            super.retainAll(this.set);
        }
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        this.set.clear();
    }

    @Override
    public boolean contains(Object object) {
        return this.set.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.set.containsAll(coll);
    }

    @Override
    public Iterator<E> iterator() {
        return new SetListIterator(super.iterator(), this.set);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new SetListListIterator(super.listIterator(), this.set);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new SetListListIterator(super.listIterator(index), this.set);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List superSubList = super.subList(fromIndex, toIndex);
        Set<E> subSet = this.createSetBasedOnList(this.set, superSubList);
        return ListUtils.unmodifiableList(new SetUniqueList(superSubList, subSet));
    }

    protected Set<E> createSetBasedOnList(Set<E> set, List<E> list) {
        Set subSet;
        if (set.getClass().equals(HashSet.class)) {
            subSet = new HashSet(list.size());
        } else {
            try {
                subSet = (Set)set.getClass().getDeclaredConstructor(set.getClass()).newInstance(set);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ie) {
                subSet = new HashSet();
            }
        }
        return subSet;
    }

    static class SetListListIterator<E>
    extends AbstractListIteratorDecorator<E> {
        private final Set<E> set;
        private E last = null;

        protected SetListListIterator(ListIterator<E> it, Set<E> set) {
            super(it);
            this.set = set;
        }

        @Override
        public E next() {
            this.last = super.next();
            return this.last;
        }

        @Override
        public E previous() {
            this.last = super.previous();
            return this.last;
        }

        @Override
        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }

        @Override
        public void add(E object) {
            if (!this.set.contains(object)) {
                super.add(object);
                this.set.add(object);
            }
        }

        @Override
        public void set(E object) {
            throw new UnsupportedOperationException("ListIterator does not support set");
        }
    }

    static class SetListIterator<E>
    extends AbstractIteratorDecorator<E> {
        private final Set<E> set;
        private E last = null;

        protected SetListIterator(Iterator<E> it, Set<E> set) {
            super(it);
            this.set = set;
        }

        @Override
        public E next() {
            this.last = super.next();
            return this.last;
        }

        @Override
        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }
    }
}

