/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ForwardingMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@GwtCompatible(emulated=true)
abstract class DescendingMultiset<E>
extends ForwardingMultiset<E>
implements SortedMultiset<E> {
    private transient @MonotonicNonNull Comparator<? super E> comparator;
    private transient @MonotonicNonNull NavigableSet<E> elementSet;
    private transient @MonotonicNonNull Set<Multiset.Entry<E>> entrySet;

    DescendingMultiset() {
    }

    abstract SortedMultiset<E> forwardMultiset();

    @Override
    public Comparator<? super E> comparator() {
        Comparator<? super E> result = this.comparator;
        if (result == null) {
            this.comparator = Ordering.from(this.forwardMultiset().comparator()).reverse();
            return this.comparator;
        }
        return result;
    }

    @Override
    public NavigableSet<E> elementSet() {
        NavigableSet<E> result = this.elementSet;
        if (result == null) {
            this.elementSet = new SortedMultisets.NavigableElementSet(this);
            return this.elementSet;
        }
        return result;
    }

    @Override
    public Multiset.Entry<E> pollFirstEntry() {
        return this.forwardMultiset().pollLastEntry();
    }

    @Override
    public Multiset.Entry<E> pollLastEntry() {
        return this.forwardMultiset().pollFirstEntry();
    }

    @Override
    public SortedMultiset<E> headMultiset(E toElement, BoundType boundType) {
        return this.forwardMultiset().tailMultiset(toElement, boundType).descendingMultiset();
    }

    @Override
    public SortedMultiset<E> subMultiset(E fromElement, BoundType fromBoundType, E toElement, BoundType toBoundType) {
        return this.forwardMultiset().subMultiset(toElement, toBoundType, fromElement, fromBoundType).descendingMultiset();
    }

    @Override
    public SortedMultiset<E> tailMultiset(E fromElement, BoundType boundType) {
        return this.forwardMultiset().headMultiset(fromElement, boundType).descendingMultiset();
    }

    @Override
    protected Multiset<E> delegate() {
        return this.forwardMultiset();
    }

    @Override
    public SortedMultiset<E> descendingMultiset() {
        return this.forwardMultiset();
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        return this.forwardMultiset().lastEntry();
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        return this.forwardMultiset().firstEntry();
    }

    abstract Iterator<Multiset.Entry<E>> entryIterator();

    @Override
    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<Multiset.Entry<E>>> result = this.entrySet;
        return result == null ? (this.entrySet = this.createEntrySet()) : result;
    }

    Set<Multiset.Entry<E>> createEntrySet() {
        class EntrySetImpl
        extends Multisets.EntrySet<E> {
            EntrySetImpl() {
            }

            @Override
            Multiset<E> multiset() {
                return DescendingMultiset.this;
            }

            @Override
            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.entryIterator();
            }

            @Override
            public int size() {
                return DescendingMultiset.this.forwardMultiset().entrySet().size();
            }
        }
        return new EntrySetImpl();
    }

    @Override
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override
    public Object[] toArray() {
        return this.standardToArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return this.standardToArray(array);
    }

    @Override
    public String toString() {
        return this.entrySet().toString();
    }
}

