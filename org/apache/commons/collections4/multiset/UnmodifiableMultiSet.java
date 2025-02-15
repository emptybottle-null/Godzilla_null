/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.multiset.AbstractMultiSetDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMultiSet<E>
extends AbstractMultiSetDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = 20150611L;

    public static <E> MultiSet<E> unmodifiableMultiSet(MultiSet<? extends E> multiset) {
        if (multiset instanceof Unmodifiable) {
            MultiSet<? extends E> tmpMultiSet = multiset;
            return tmpMultiSet;
        }
        return new UnmodifiableMultiSet<E>(multiset);
    }

    private UnmodifiableMultiSet(MultiSet<? extends E> multiset) {
        super(multiset);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setCollection((Collection)in.readObject());
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
    public int setCount(E object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int add(E object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remove(Object object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<E> uniqueSet() {
        Set set = this.decorated().uniqueSet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Set<MultiSet.Entry<E>> entrySet() {
        Set set = this.decorated().entrySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }
}

