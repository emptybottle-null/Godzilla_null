/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.DescendingMultiset;
import com.google.common.collect.ForwardingMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

@Beta
@GwtCompatible(emulated=true)
public abstract class ForwardingSortedMultiset<E>
extends ForwardingMultiset<E>
implements SortedMultiset<E> {
    protected ForwardingSortedMultiset() {
    }

    @Override
    protected abstract SortedMultiset<E> delegate();

    @Override
    public NavigableSet<E> elementSet() {
        return this.delegate().elementSet();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.delegate().comparator();
    }

    @Override
    public SortedMultiset<E> descendingMultiset() {
        return this.delegate().descendingMultiset();
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        return this.delegate().firstEntry();
    }

    protected Multiset.Entry<E> standardFirstEntry() {
        Iterator entryIterator = this.entrySet().iterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry entry = entryIterator.next();
        return Multisets.immutableEntry(entry.getElement(), entry.getCount());
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        return this.delegate().lastEntry();
    }

    protected Multiset.Entry<E> standardLastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = this.descendingMultiset().entrySet().iterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> entry = entryIterator.next();
        return Multisets.immutableEntry(entry.getElement(), entry.getCount());
    }

    @Override
    public Multiset.Entry<E> pollFirstEntry() {
        return this.delegate().pollFirstEntry();
    }

    protected Multiset.Entry<E> standardPollFirstEntry() {
        Iterator entryIterator = this.entrySet().iterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry entry = entryIterator.next();
        entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        entryIterator.remove();
        return entry;
    }

    @Override
    public Multiset.Entry<E> pollLastEntry() {
        return this.delegate().pollLastEntry();
    }

    protected Multiset.Entry<E> standardPollLastEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = this.descendingMultiset().entrySet().iterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry<E> entry = entryIterator.next();
        entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        entryIterator.remove();
        return entry;
    }

    @Override
    public SortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return this.delegate().headMultiset(upperBound, boundType);
    }

    @Override
    public SortedMultiset<E> subMultiset(E lowerBound, BoundType lowerBoundType, E upperBound, BoundType upperBoundType) {
        return this.delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType);
    }

    protected SortedMultiset<E> standardSubMultiset(E lowerBound, BoundType lowerBoundType, E upperBound, BoundType upperBoundType) {
        return this.tailMultiset(lowerBound, lowerBoundType).headMultiset(upperBound, upperBoundType);
    }

    @Override
    public SortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return this.delegate().tailMultiset(lowerBound, boundType);
    }

    protected abstract class StandardDescendingMultiset
    extends DescendingMultiset<E> {
        @Override
        SortedMultiset<E> forwardMultiset() {
            return ForwardingSortedMultiset.this;
        }
    }

    protected class StandardElementSet
    extends SortedMultisets.NavigableElementSet<E> {
        public StandardElementSet() {
            super(ForwardingSortedMultiset.this);
        }
    }
}

