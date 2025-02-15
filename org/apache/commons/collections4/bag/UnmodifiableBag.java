/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.bag.AbstractBagDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableBag<E>
extends AbstractBagDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = -1873799975157099624L;

    public static <E> Bag<E> unmodifiableBag(Bag<? extends E> bag) {
        if (bag instanceof Unmodifiable) {
            Bag<? extends E> tmpBag = bag;
            return tmpBag;
        }
        return new UnmodifiableBag<E>(bag);
    }

    private UnmodifiableBag(Bag<? extends E> bag) {
        super(bag);
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
    public boolean add(E object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<E> uniqueSet() {
        Set set = this.decorated().uniqueSet();
        return UnmodifiableSet.unmodifiableSet(set);
    }
}

