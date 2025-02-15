/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.list.AbstractSerializableListDecorator;

public final class UnmodifiableList<E>
extends AbstractSerializableListDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = 6595182819922443652L;

    public static <E> List<E> unmodifiableList(List<? extends E> list) {
        if (list instanceof Unmodifiable) {
            List<? extends E> tmpList = list;
            return tmpList;
        }
        return new UnmodifiableList<E>(list);
    }

    public UnmodifiableList(List<? extends E> list) {
        super(list);
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
    }

    @Override
    public boolean add(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
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
    public ListIterator<E> listIterator() {
        return UnmodifiableListIterator.umodifiableListIterator(this.decorated().listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return UnmodifiableListIterator.umodifiableListIterator(this.decorated().listIterator(index));
    }

    @Override
    public void add(int index, E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List sub = this.decorated().subList(fromIndex, toIndex);
        return new UnmodifiableList(sub);
    }
}

