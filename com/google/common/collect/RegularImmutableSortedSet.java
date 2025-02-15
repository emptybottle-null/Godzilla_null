/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedAsList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SortedIterables;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
final class RegularImmutableSortedSet<E>
extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = new RegularImmutableSortedSet(ImmutableList.of(), Ordering.natural());
    private final transient ImmutableList<E> elements;

    RegularImmutableSortedSet(ImmutableList<E> elements, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = elements;
    }

    @Override
    Object[] internalArray() {
        return this.elements.internalArray();
    }

    @Override
    int internalArrayStart() {
        return this.elements.internalArrayStart();
    }

    @Override
    int internalArrayEnd() {
        return this.elements.internalArrayEnd();
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }

    @Override
    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    @Override
    public Spliterator<E> spliterator() {
        return this.asList().spliterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.elements.forEach(action);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        try {
            return o != null && this.unsafeBinarySearch(o) >= 0;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> targets) {
        if (targets instanceof Multiset) {
            targets = ((Multiset)targets).elementSet();
        }
        if (!SortedIterables.hasSameComparator(this.comparator(), targets) || targets.size() <= 1) {
            return super.containsAll(targets);
        }
        Iterator thisIterator = this.iterator();
        Iterator<?> thatIterator = targets.iterator();
        if (!thisIterator.hasNext()) {
            return false;
        }
        Object target = thatIterator.next();
        Object current = thisIterator.next();
        try {
            while (true) {
                int cmp;
                if ((cmp = this.unsafeCompare(current, target)) < 0) {
                    if (!thisIterator.hasNext()) {
                        return false;
                    }
                    current = thisIterator.next();
                    continue;
                }
                if (cmp == 0) {
                    if (!thatIterator.hasNext()) {
                        return true;
                    }
                    target = thatIterator.next();
                    continue;
                }
                if (cmp > 0) break;
            }
            return false;
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    private int unsafeBinarySearch(Object key) throws ClassCastException {
        return Collections.binarySearch(this.elements, key, this.unsafeComparator());
    }

    @Override
    boolean isPartialView() {
        return this.elements.isPartialView();
    }

    @Override
    int copyIntoArray(Object[] dst, int offset) {
        return this.elements.copyIntoArray(dst, offset);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set that = (Set)object;
        if (this.size() != that.size()) {
            return false;
        }
        if (this.isEmpty()) {
            return true;
        }
        if (SortedIterables.hasSameComparator(this.comparator, that)) {
            Iterator otherIterator = that.iterator();
            try {
                for (Object element : this) {
                    Object otherElement = otherIterator.next();
                    if (otherElement != null && this.unsafeCompare(element, otherElement) == 0) continue;
                    return false;
                }
                return true;
            } catch (ClassCastException e) {
                return false;
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return this.containsAll(that);
    }

    @Override
    public E first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(0);
    }

    @Override
    public E last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(this.size() - 1);
    }

    @Override
    public E lower(E element) {
        int index = this.headIndex(element, false) - 1;
        return index == -1 ? null : (E)this.elements.get(index);
    }

    @Override
    public E floor(E element) {
        int index = this.headIndex(element, true) - 1;
        return index == -1 ? null : (E)this.elements.get(index);
    }

    @Override
    public E ceiling(E element) {
        int index = this.tailIndex(element, true);
        return index == this.size() ? null : (E)this.elements.get(index);
    }

    @Override
    public E higher(E element) {
        int index = this.tailIndex(element, false);
        return index == this.size() ? null : (E)this.elements.get(index);
    }

    @Override
    ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive) {
        return this.getSubSet(0, this.headIndex(toElement, inclusive));
    }

    int headIndex(E toElement, boolean inclusive) {
        int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(toElement), this.comparator());
        if (index >= 0) {
            return inclusive ? index + 1 : index;
        }
        return ~index;
    }

    @Override
    ImmutableSortedSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return this.tailSetImpl(fromElement, fromInclusive).headSetImpl(toElement, toInclusive);
    }

    @Override
    ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive) {
        return this.getSubSet(this.tailIndex(fromElement, inclusive), this.size());
    }

    int tailIndex(E fromElement, boolean inclusive) {
        int index = Collections.binarySearch(this.elements, Preconditions.checkNotNull(fromElement), this.comparator());
        if (index >= 0) {
            return inclusive ? index : index + 1;
        }
        return ~index;
    }

    Comparator<Object> unsafeComparator() {
        return this.comparator;
    }

    RegularImmutableSortedSet<E> getSubSet(int newFromIndex, int newToIndex) {
        if (newFromIndex == 0 && newToIndex == this.size()) {
            return this;
        }
        if (newFromIndex < newToIndex) {
            return new RegularImmutableSortedSet<E>(this.elements.subList(newFromIndex, newToIndex), this.comparator);
        }
        return RegularImmutableSortedSet.emptySet(this.comparator);
    }

    @Override
    int indexOf(@Nullable Object target) {
        int position;
        if (target == null) {
            return -1;
        }
        try {
            position = Collections.binarySearch(this.elements, target, this.unsafeComparator());
        } catch (ClassCastException e) {
            return -1;
        }
        return position >= 0 ? position : -1;
    }

    @Override
    ImmutableList<E> createAsList() {
        return this.size() <= 1 ? this.elements : new ImmutableSortedAsList<E>(this, this.elements);
    }

    @Override
    ImmutableSortedSet<E> createDescendingSet() {
        Comparator reversedOrder = Collections.reverseOrder(this.comparator);
        return this.isEmpty() ? RegularImmutableSortedSet.emptySet(reversedOrder) : new RegularImmutableSortedSet<E>(this.elements.reverse(), reversedOrder);
    }
}

