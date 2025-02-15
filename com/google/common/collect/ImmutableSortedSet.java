/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSetFauxverideShim;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Ordering;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.collect.SortedIterable;
import com.google.common.collect.SortedIterables;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableSortedSet<E>
extends ImmutableSortedSetFauxverideShim<E>
implements NavigableSet<E>,
SortedIterable<E> {
    static final int SPLITERATOR_CHARACTERISTICS = 1301;
    final transient Comparator<? super E> comparator;
    @LazyInit
    @GwtIncompatible
    transient ImmutableSortedSet<E> descendingSet;

    public static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(Comparator<? super E> comparator) {
        return CollectCollectors.toImmutableSortedSet(comparator);
    }

    static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet<E>(ImmutableList.of(), comparator);
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E element) {
        return new RegularImmutableSortedSet<E>(ImmutableList.of(element), Ordering.natural());
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e1, E e2) {
        return ImmutableSortedSet.construct(Ordering.natural(), 2, e1, e2);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e1, E e2, E e3) {
        return ImmutableSortedSet.construct(Ordering.natural(), 3, e1, e2, e3);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4) {
        return ImmutableSortedSet.construct(Ordering.natural(), 4, e1, e2, e3, e4);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        return ImmutableSortedSet.construct(Ordering.natural(), 5, e1, e2, e3, e4, e5);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E ... remaining) {
        Comparable[] contents = new Comparable[6 + remaining.length];
        contents[0] = e1;
        contents[1] = e2;
        contents[2] = e3;
        contents[3] = e4;
        contents[4] = e5;
        contents[5] = e6;
        System.arraycopy(remaining, 0, contents, 6, remaining.length);
        return ImmutableSortedSet.construct(Ordering.natural(), contents.length, contents);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(E[] elements) {
        return ImmutableSortedSet.construct(Ordering.natural(), elements.length, (Object[])elements.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> elements) {
        Ordering naturalOrder = Ordering.natural();
        return ImmutableSortedSet.copyOf(naturalOrder, elements);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> elements) {
        Ordering naturalOrder = Ordering.natural();
        return ImmutableSortedSet.copyOf(naturalOrder, elements);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> elements) {
        Ordering naturalOrder = Ordering.natural();
        return ImmutableSortedSet.copyOf(naturalOrder, elements);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> elements) {
        return ((Builder)new Builder<E>(comparator).addAll((Iterator)elements)).build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> elements) {
        ImmutableSortedSet original;
        Preconditions.checkNotNull(comparator);
        boolean hasSameComparator = SortedIterables.hasSameComparator(comparator, elements);
        if (hasSameComparator && elements instanceof ImmutableSortedSet && !(original = (ImmutableSortedSet)elements).isPartialView()) {
            return original;
        }
        Object[] array = Iterables.toArray(elements);
        return ImmutableSortedSet.construct(comparator, array.length, array);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Collection<? extends E> elements) {
        return ImmutableSortedSet.copyOf(comparator, elements);
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(SortedSet<E> sortedSet) {
        Comparator<E> comparator = SortedIterables.comparator(sortedSet);
        ImmutableList<E> list = ImmutableList.copyOf(sortedSet);
        if (list.isEmpty()) {
            return ImmutableSortedSet.emptySet(comparator);
        }
        return new RegularImmutableSortedSet<E>(list, comparator);
    }

    static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator, int n, E ... contents) {
        if (n == 0) {
            return ImmutableSortedSet.emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull((Object[])contents, n);
        Arrays.sort(contents, 0, n, comparator);
        int uniques = 1;
        for (int i = 1; i < n; ++i) {
            E cur = contents[i];
            E prev = contents[uniques - 1];
            if (comparator.compare(cur, prev) == 0) continue;
            contents[uniques++] = cur;
        }
        Arrays.fill(contents, uniques, n, null);
        return new RegularImmutableSortedSet<E>(ImmutableList.asImmutableList(contents, uniques), comparator);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<E>(comparator);
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder(Collections.reverseOrder());
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder(Ordering.natural());
    }

    int unsafeCompare(Object a, Object b) {
        return ImmutableSortedSet.unsafeCompare(this.comparator, a, b);
    }

    static int unsafeCompare(Comparator<?> comparator, Object a, Object b) {
        Comparator<?> unsafeComparator = comparator;
        return unsafeComparator.compare(a, b);
    }

    ImmutableSortedSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override
    public abstract UnmodifiableIterator<E> iterator();

    @Override
    public ImmutableSortedSet<E> headSet(E toElement) {
        return this.headSet((Object)toElement, false);
    }

    @Override
    @GwtIncompatible
    public ImmutableSortedSet<E> headSet(E toElement, boolean inclusive) {
        return this.headSetImpl(Preconditions.checkNotNull(toElement), inclusive);
    }

    @Override
    public ImmutableSortedSet<E> subSet(E fromElement, E toElement) {
        return this.subSet((Object)fromElement, true, (Object)toElement, false);
    }

    @Override
    @GwtIncompatible
    public ImmutableSortedSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(this.comparator.compare(fromElement, toElement) <= 0);
        return this.subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public ImmutableSortedSet<E> tailSet(E fromElement) {
        return this.tailSet((Object)fromElement, true);
    }

    @Override
    @GwtIncompatible
    public ImmutableSortedSet<E> tailSet(E fromElement, boolean inclusive) {
        return this.tailSetImpl(Preconditions.checkNotNull(fromElement), inclusive);
    }

    abstract ImmutableSortedSet<E> headSetImpl(E var1, boolean var2);

    abstract ImmutableSortedSet<E> subSetImpl(E var1, boolean var2, E var3, boolean var4);

    abstract ImmutableSortedSet<E> tailSetImpl(E var1, boolean var2);

    @Override
    @GwtIncompatible
    public E lower(E e) {
        return Iterators.getNext(((ImmutableSortedSet)this.headSet((Object)e, false)).descendingIterator(), null);
    }

    @Override
    @GwtIncompatible
    public E floor(E e) {
        return Iterators.getNext(((ImmutableSortedSet)this.headSet((Object)e, true)).descendingIterator(), null);
    }

    @Override
    @GwtIncompatible
    public E ceiling(E e) {
        return Iterables.getFirst(this.tailSet((Object)e, true), null);
    }

    @Override
    @GwtIncompatible
    public E higher(E e) {
        return Iterables.getFirst(this.tailSet((Object)e, false), null);
    }

    @Override
    public E first() {
        return this.iterator().next();
    }

    @Override
    public E last() {
        return this.descendingIterator().next();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    @GwtIncompatible
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    @GwtIncompatible
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    @GwtIncompatible
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> result = this.descendingSet;
        if (result == null) {
            result = this.descendingSet = this.createDescendingSet();
            result.descendingSet = this;
        }
        return result;
    }

    @GwtIncompatible
    abstract ImmutableSortedSet<E> createDescendingSet();

    @Override
    public Spliterator<E> spliterator() {
        return new Spliterators.AbstractSpliterator<E>(this.size(), 1365){
            final UnmodifiableIterator<E> iterator;
            {
                this.iterator = ImmutableSortedSet.this.iterator();
            }

            @Override
            public boolean tryAdvance(Consumer<? super E> action) {
                if (this.iterator.hasNext()) {
                    action.accept(this.iterator.next());
                    return true;
                }
                return false;
            }

            @Override
            public Comparator<? super E> getComparator() {
                return ImmutableSortedSet.this.comparator;
            }
        };
    }

    @Override
    @GwtIncompatible
    public abstract UnmodifiableIterator<E> descendingIterator();

    abstract int indexOf(@Nullable Object var1);

    private void readObject(ObjectInputStream unused) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override
    Object writeReplace() {
        return new SerializedForm<E>(this.comparator, this.toArray());
    }

    private static class SerializedForm<E>
    implements Serializable {
        final Comparator<? super E> comparator;
        final Object[] elements;
        private static final long serialVersionUID = 0L;

        public SerializedForm(Comparator<? super E> comparator, Object[] elements) {
            this.comparator = comparator;
            this.elements = elements;
        }

        Object readResolve() {
            return ((Builder)new Builder<E>(this.comparator).add(this.elements)).build();
        }
    }

    public static final class Builder<E>
    extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;
        private E[] elements;
        private int n;

        public Builder(Comparator<? super E> comparator) {
            super(true);
            this.comparator = Preconditions.checkNotNull(comparator);
            this.elements = new Object[4];
            this.n = 0;
        }

        @Override
        void copy() {
            this.elements = Arrays.copyOf(this.elements, this.elements.length);
        }

        private void sortAndDedup() {
            if (this.n == 0) {
                return;
            }
            Arrays.sort(this.elements, 0, this.n, this.comparator);
            int unique = 1;
            for (int i = 1; i < this.n; ++i) {
                int cmp = this.comparator.compare(this.elements[unique - 1], this.elements[i]);
                if (cmp < 0) {
                    this.elements[unique++] = this.elements[i];
                    continue;
                }
                if (cmp > 0) {
                    throw new AssertionError((Object)("Comparator " + this.comparator + " compare method violates its contract"));
                }
            }
            Arrays.fill(this.elements, unique, this.n, null);
            this.n = unique;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            Preconditions.checkNotNull(element);
            this.copyIfNecessary();
            if (this.n == this.elements.length) {
                this.sortAndDedup();
                int newLength = ImmutableCollection.Builder.expandedCapacity(this.n, this.n + 1);
                if (newLength > this.elements.length) {
                    this.elements = Arrays.copyOf(this.elements, newLength);
                }
            }
            this.elements[this.n++] = element;
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E ... elements) {
            ObjectArrays.checkElementsNotNull((Object[])elements);
            for (E e : elements) {
                this.add((Object)e);
            }
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            super.addAll((Iterable)elements);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll((Iterator)elements);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        Builder<E> combine(ImmutableSet.Builder<E> builder) {
            this.copyIfNecessary();
            Builder other = (Builder)builder;
            for (int i = 0; i < other.n; ++i) {
                this.add((Object)other.elements[i]);
            }
            return this;
        }

        @Override
        public ImmutableSortedSet<E> build() {
            this.sortAndDedup();
            if (this.n == 0) {
                return ImmutableSortedSet.emptySet(this.comparator);
            }
            this.forceCopy = true;
            return new RegularImmutableSortedSet<E>(ImmutableList.asImmutableList(this.elements, this.n), this.comparator);
        }
    }
}

