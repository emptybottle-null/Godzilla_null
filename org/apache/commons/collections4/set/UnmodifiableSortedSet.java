/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.AbstractSortedSetDecorator;

public final class UnmodifiableSortedSet<E>
extends AbstractSortedSetDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = -725356885467962424L;

    public static <E> SortedSet<E> unmodifiableSortedSet(SortedSet<E> set) {
        if (set instanceof Unmodifiable) {
            return set;
        }
        return new UnmodifiableSortedSet<E>(set);
    }

    private UnmodifiableSortedSet(SortedSet<E> set) {
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setCollection((Collection)in.readObject());
    }
}

