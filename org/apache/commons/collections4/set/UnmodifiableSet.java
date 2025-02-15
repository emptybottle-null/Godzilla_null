/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.AbstractSerializableSetDecorator;

public final class UnmodifiableSet<E>
extends AbstractSerializableSetDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = 6499119872185240161L;

    public static <E> Set<E> unmodifiableSet(Set<? extends E> set) {
        if (set instanceof Unmodifiable) {
            Set<? extends E> tmpSet = set;
            return tmpSet;
        }
        return new UnmodifiableSet<E>(set);
    }

    private UnmodifiableSet(Set<? extends E> set) {
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
}

