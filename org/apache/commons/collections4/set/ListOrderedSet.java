/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.set.AbstractSerializableSetDecorator;

public class ListOrderedSet<E>
extends AbstractSerializableSetDecorator<E> {
    private static final long serialVersionUID = -228664372470420141L;
    private final List<E> setOrder;

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set, List<E> list) {
        if (set == null) {
            throw new NullPointerException("Set must not be null");
        }
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        if (set.size() > 0 || list.size() > 0) {
            throw new IllegalArgumentException("Set and List must be empty");
        }
        return new ListOrderedSet<E>(set, list);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set) {
        return new ListOrderedSet<E>(set);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(List<E> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        CollectionUtils.filter(list, UniquePredicate.uniquePredicate());
        HashSet<E> set = new HashSet<E>(list);
        return new ListOrderedSet<E>(set, list);
    }

    public ListOrderedSet() {
        super(new HashSet());
        this.setOrder = new ArrayList();
    }

    protected ListOrderedSet(Set<E> set) {
        super(set);
        this.setOrder = new ArrayList<E>(set);
    }

    protected ListOrderedSet(Set<E> set, List<E> list) {
        super(set);
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        this.setOrder = list;
    }

    public List<E> asList() {
        return UnmodifiableList.unmodifiableList(this.setOrder);
    }

    @Override
    public void clear() {
        this.decorated().clear();
        this.setOrder.clear();
    }

    @Override
    public OrderedIterator<E> iterator() {
        return new OrderedSetIterator(this.setOrder.listIterator(), this.decorated());
    }

    @Override
    public boolean add(E object) {
        if (this.decorated().add(object)) {
            this.setOrder.add(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        boolean result = false;
        for (E e : coll) {
            result |= this.add(e);
        }
        return result;
    }

    @Override
    public boolean remove(Object object) {
        boolean result = this.decorated().remove(object);
        if (result) {
            this.setOrder.remove(object);
        }
        return result;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        if (Objects.isNull(filter)) {
            return false;
        }
        boolean result = this.decorated().removeIf(filter);
        if (result) {
            this.setOrder.removeIf(filter);
        }
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
        boolean result = this.decorated().retainAll(coll);
        if (!result) {
            return false;
        }
        if (this.decorated().size() == 0) {
            this.setOrder.clear();
        } else {
            Iterator<E> it = this.setOrder.iterator();
            while (it.hasNext()) {
                if (this.decorated().contains(it.next())) continue;
                it.remove();
            }
        }
        return result;
    }

    @Override
    public Object[] toArray() {
        return this.setOrder.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.setOrder.toArray(a);
    }

    public E get(int index) {
        return this.setOrder.get(index);
    }

    public int indexOf(Object object) {
        return this.setOrder.indexOf(object);
    }

    public void add(int index, E object) {
        if (!this.contains(object)) {
            this.decorated().add(object);
            this.setOrder.add(index, object);
        }
    }

    public boolean addAll(int index, Collection<? extends E> coll) {
        boolean changed = false;
        ArrayList<E> toAdd = new ArrayList<E>();
        for (E e : coll) {
            if (this.contains(e)) continue;
            this.decorated().add(e);
            toAdd.add(e);
            changed = true;
        }
        if (changed) {
            this.setOrder.addAll(index, toAdd);
        }
        return changed;
    }

    public E remove(int index) {
        E obj = this.setOrder.remove(index);
        this.remove(obj);
        return obj;
    }

    @Override
    public String toString() {
        return this.setOrder.toString();
    }

    static class OrderedSetIterator<E>
    extends AbstractIteratorDecorator<E>
    implements OrderedIterator<E> {
        private final Collection<E> set;
        private E last;

        private OrderedSetIterator(ListIterator<E> iterator, Collection<E> set) {
            super(iterator);
            this.set = set;
        }

        @Override
        public E next() {
            this.last = this.getIterator().next();
            return this.last;
        }

        @Override
        public void remove() {
            this.set.remove(this.last);
            this.getIterator().remove();
            this.last = null;
        }

        @Override
        public boolean hasPrevious() {
            return ((ListIterator)this.getIterator()).hasPrevious();
        }

        @Override
        public E previous() {
            this.last = ((ListIterator)this.getIterator()).previous();
            return this.last;
        }
    }
}

