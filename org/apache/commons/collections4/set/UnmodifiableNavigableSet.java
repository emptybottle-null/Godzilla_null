/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.AbstractNavigableSetDecorator;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

public final class UnmodifiableNavigableSet<E>
extends AbstractNavigableSetDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = 20150528L;

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> set) {
        if (set instanceof Unmodifiable) {
            return set;
        }
        return new UnmodifiableNavigableSet<E>(set);
    }

    private UnmodifiableNavigableSet(NavigableSet<E> set) {
        super(set);
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
    }

    @Override
    public boolean add(E object) {
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
    public SortedSet<E> subSet(E fromElement, E toElement) {
        SortedSet<E> sub = this.decorated().subSet(fromElement, toElement);
        return UnmodifiableSortedSet.unmodifiableSortedSet(sub);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        SortedSet<E> head = this.decorated().headSet(toElement);
        return UnmodifiableSortedSet.unmodifiableSortedSet(head);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        SortedSet<E> tail = this.decorated().tailSet(fromElement);
        return UnmodifiableSortedSet.unmodifiableSortedSet(tail);
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(this.decorated().descendingSet());
    }

    @Override
    public Iterator<E> descendingIterator() {
        return UnmodifiableIterator.unmodifiableIterator(this.decorated().descendingIterator());
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        NavigableSet<E> sub = this.decorated().subSet(fromElement, fromInclusive, toElement, toInclusive);
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(sub);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        NavigableSet<E> head = this.decorated().headSet(toElement, inclusive);
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(head);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        NavigableSet<E> tail = this.decorated().tailSet(fromElement, inclusive);
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(tail);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setCollection((Collection)in.readObject());
    }
}

