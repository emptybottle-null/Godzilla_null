/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractMultiset<E>
extends AbstractCollection<E>
implements Multiset<E> {
    private transient @MonotonicNonNull Set<E> elementSet;
    private transient @MonotonicNonNull Set<Multiset.Entry<E>> entrySet;

    AbstractMultiset() {
    }

    @Override
    public boolean isEmpty() {
        return this.entrySet().isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object element) {
        return this.count(element) > 0;
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean add(@Nullable E element) {
        this.add(element, 1);
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public int add(@Nullable E element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean remove(@Nullable Object element) {
        return this.remove(element, 1) > 0;
    }

    @Override
    @CanIgnoreReturnValue
    public int remove(@Nullable Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    @CanIgnoreReturnValue
    public int setCount(@Nullable E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean setCount(@Nullable E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    @Override
    public abstract void clear();

    @Override
    public Set<E> elementSet() {
        Set<E> result = this.elementSet;
        if (result == null) {
            this.elementSet = result = this.createElementSet();
        }
        return result;
    }

    Set<E> createElementSet() {
        return new ElementSet();
    }

    abstract Iterator<E> elementIterator();

    @Override
    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<Multiset.Entry<E>>> result = this.entrySet;
        if (result == null) {
            this.entrySet = result = this.createEntrySet();
        }
        return result;
    }

    Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    abstract Iterator<Multiset.Entry<E>> entryIterator();

    abstract int distinctElements();

    @Override
    public final boolean equals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    @Override
    public final int hashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    public final String toString() {
        return this.entrySet().toString();
    }

    class EntrySet
    extends Multisets.EntrySet<E> {
        EntrySet() {
        }

        @Override
        Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        @Override
        public Iterator<Multiset.Entry<E>> iterator() {
            return AbstractMultiset.this.entryIterator();
        }

        @Override
        public int size() {
            return AbstractMultiset.this.distinctElements();
        }
    }

    class ElementSet
    extends Multisets.ElementSet<E> {
        ElementSet() {
        }

        @Override
        Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        @Override
        public Iterator<E> iterator() {
            return AbstractMultiset.this.elementIterator();
        }
    }
}

