/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public final class UnmodifiableBoundedCollection<E>
extends AbstractCollectionDecorator<E>
implements BoundedCollection<E>,
Unmodifiable {
    private static final long serialVersionUID = -7112672385450340330L;

    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(BoundedCollection<? extends E> coll) {
        if (coll instanceof Unmodifiable) {
            BoundedCollection<? extends E> tmpColl = coll;
            return tmpColl;
        }
        return new UnmodifiableBoundedCollection<E>(coll);
    }

    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(Collection<? extends E> coll) {
        if (coll == null) {
            throw new NullPointerException("Collection must not be null.");
        }
        for (int i = 0; i < 1000 && !(coll instanceof BoundedCollection); ++i) {
            if (coll instanceof AbstractCollectionDecorator) {
                coll = ((AbstractCollectionDecorator)coll).decorated();
                continue;
            }
            if (!(coll instanceof SynchronizedCollection)) continue;
            coll = ((SynchronizedCollection)coll).decorated();
        }
        if (!(coll instanceof BoundedCollection)) {
            throw new IllegalArgumentException("Collection is not a bounded collection.");
        }
        return new UnmodifiableBoundedCollection<E>((BoundedCollection)coll);
    }

    private UnmodifiableBoundedCollection(BoundedCollection<? extends E> coll) {
        super(coll);
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
    public boolean isFull() {
        return this.decorated().isFull();
    }

    @Override
    public int maxSize() {
        return this.decorated().maxSize();
    }

    @Override
    protected BoundedCollection<E> decorated() {
        return (BoundedCollection)super.decorated();
    }
}

