/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.BoundType;
import com.google.common.collect.DescendingMultiset;
import com.google.common.collect.GwtTransient;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
abstract class AbstractSortedMultiset<E>
extends AbstractMultiset<E>
implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    private transient @MonotonicNonNull SortedMultiset<E> descendingMultiset;

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.comparator = Preconditions.checkNotNull(comparator);
    }

    @Override
    public NavigableSet<E> elementSet() {
        return (NavigableSet)super.elementSet();
    }

    @Override
    NavigableSet<E> createElementSet() {
        return new SortedMultisets.NavigableElementSet(this);
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        Iterator entryIterator = this.entryIterator();
        return entryIterator.hasNext() ? entryIterator.next() : null;
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = this.descendingEntryIterator();
        return entryIterator.hasNext() ? entryIterator.next() : null;
    }

    @Override
    public Multiset.Entry<E> pollFirstEntry() {
        Iterator entryIterator = this.entryIterator();
        if (entryIterator.hasNext()) {
            Multiset.Entry result = entryIterator.next();
            result = Multisets.immutableEntry(result.getElement(), result.getCount());
            entryIterator.remove();
            return result;
        }
        return null;
    }

    @Override
    public Multiset.Entry<E> pollLastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = this.descendingEntryIterator();
        if (entryIterator.hasNext()) {
            Multiset.Entry<E> result = entryIterator.next();
            result = Multisets.immutableEntry(result.getElement(), result.getCount());
            entryIterator.remove();
            return result;
        }
        return null;
    }

    @Override
    public SortedMultiset<E> subMultiset(@Nullable E fromElement, BoundType fromBoundType, @Nullable E toElement, BoundType toBoundType) {
        Preconditions.checkNotNull(fromBoundType);
        Preconditions.checkNotNull(toBoundType);
        return this.tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
    }

    abstract Iterator<Multiset.Entry<E>> descendingEntryIterator();

    Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(this.descendingMultiset());
    }

    @Override
    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> result = this.descendingMultiset;
        return result == null ? (this.descendingMultiset = this.createDescendingMultiset()) : result;
    }

    SortedMultiset<E> createDescendingMultiset() {
        class DescendingMultisetImpl
        extends DescendingMultiset<E> {
            DescendingMultisetImpl() {
            }

            @Override
            SortedMultiset<E> forwardMultiset() {
                return AbstractSortedMultiset.this;
            }

            @Override
            Iterator<Multiset.Entry<E>> entryIterator() {
                return AbstractSortedMultiset.this.descendingEntryIterator();
            }

            @Override
            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.descendingIterator();
            }
        }
        return new DescendingMultisetImpl();
    }
}

