/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultiset;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class SortedMultisets {
    private SortedMultisets() {
    }

    private static <E> E getElementOrThrow(Multiset.Entry<E> entry) {
        if (entry == null) {
            throw new NoSuchElementException();
        }
        return entry.getElement();
    }

    private static <E> E getElementOrNull(@Nullable Multiset.Entry<E> entry) {
        return entry == null ? null : (E)entry.getElement();
    }

    @GwtIncompatible
    static class NavigableElementSet<E>
    extends ElementSet<E>
    implements NavigableSet<E> {
        NavigableElementSet(SortedMultiset<E> multiset) {
            super(multiset);
        }

        @Override
        public E lower(E e) {
            return (E)SortedMultisets.getElementOrNull(this.multiset().headMultiset(e, BoundType.OPEN).lastEntry());
        }

        @Override
        public E floor(E e) {
            return (E)SortedMultisets.getElementOrNull(this.multiset().headMultiset(e, BoundType.CLOSED).lastEntry());
        }

        @Override
        public E ceiling(E e) {
            return (E)SortedMultisets.getElementOrNull(this.multiset().tailMultiset(e, BoundType.CLOSED).firstEntry());
        }

        @Override
        public E higher(E e) {
            return (E)SortedMultisets.getElementOrNull(this.multiset().tailMultiset(e, BoundType.OPEN).firstEntry());
        }

        @Override
        public NavigableSet<E> descendingSet() {
            return new NavigableElementSet(this.multiset().descendingMultiset());
        }

        @Override
        public Iterator<E> descendingIterator() {
            return this.descendingSet().iterator();
        }

        @Override
        public E pollFirst() {
            return (E)SortedMultisets.getElementOrNull(this.multiset().pollFirstEntry());
        }

        @Override
        public E pollLast() {
            return (E)SortedMultisets.getElementOrNull(this.multiset().pollLastEntry());
        }

        @Override
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return new NavigableElementSet<E>(this.multiset().subMultiset(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
        }

        @Override
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return new NavigableElementSet<E>(this.multiset().headMultiset(toElement, BoundType.forBoolean(inclusive)));
        }

        @Override
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return new NavigableElementSet<E>(this.multiset().tailMultiset(fromElement, BoundType.forBoolean(inclusive)));
        }
    }

    static class ElementSet<E>
    extends Multisets.ElementSet<E>
    implements SortedSet<E> {
        @Weak
        private final SortedMultiset<E> multiset;

        ElementSet(SortedMultiset<E> multiset) {
            this.multiset = multiset;
        }

        @Override
        final SortedMultiset<E> multiset() {
            return this.multiset;
        }

        @Override
        public Iterator<E> iterator() {
            return Multisets.elementIterator(this.multiset().entrySet().iterator());
        }

        @Override
        public Comparator<? super E> comparator() {
            return this.multiset().comparator();
        }

        @Override
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return this.multiset().subMultiset(fromElement, BoundType.CLOSED, toElement, BoundType.OPEN).elementSet();
        }

        @Override
        public SortedSet<E> headSet(E toElement) {
            return this.multiset().headMultiset(toElement, BoundType.OPEN).elementSet();
        }

        @Override
        public SortedSet<E> tailSet(E fromElement) {
            return this.multiset().tailMultiset(fromElement, BoundType.CLOSED).elementSet();
        }

        @Override
        public E first() {
            return (E)SortedMultisets.getElementOrThrow(this.multiset().firstEntry());
        }

        @Override
        public E last() {
            return (E)SortedMultisets.getElementOrThrow(this.multiset().lastEntry());
        }
    }
}

