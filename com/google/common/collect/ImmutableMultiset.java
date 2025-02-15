/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultisetGwtSerializationDependencies;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.IndexedImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.JdkBackedImmutableMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableMultiset<E>
extends ImmutableMultisetGwtSerializationDependencies<E>
implements Multiset<E> {
    @LazyInit
    private transient ImmutableList<E> asList;
    @LazyInit
    private transient ImmutableSet<Multiset.Entry<E>> entrySet;

    public static <E> Collector<E, ?, ImmutableMultiset<E>> toImmutableMultiset() {
        return ImmutableMultiset.toImmutableMultiset(Function.identity(), e -> 1);
    }

    public static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(Function<? super T, ? extends E> elementFunction, ToIntFunction<? super T> countFunction) {
        Preconditions.checkNotNull(elementFunction);
        Preconditions.checkNotNull(countFunction);
        return Collector.of(LinkedHashMultiset::create, (multiset, t) -> multiset.add(Preconditions.checkNotNull(elementFunction.apply(t)), countFunction.applyAsInt(t)), (multiset1, multiset2) -> {
            multiset1.addAll(multiset2);
            return multiset1;
        }, multiset -> ImmutableMultiset.copyFromEntries(multiset.entrySet()), new Collector.Characteristics[0]);
    }

    public static <E> ImmutableMultiset<E> of() {
        return RegularImmutableMultiset.EMPTY;
    }

    public static <E> ImmutableMultiset<E> of(E element) {
        return ImmutableMultiset.copyFromElements(element);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2) {
        return ImmutableMultiset.copyFromElements(e1, e2);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3) {
        return ImmutableMultiset.copyFromElements(e1, e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4) {
        return ImmutableMultiset.copyFromElements(e1, e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5) {
        return ImmutableMultiset.copyFromElements(e1, e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E ... others) {
        return ((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)new Builder().add((Object)e1)).add((Object)e2)).add((Object)e3)).add((Object)e4)).add((Object)e5)).add((Object)e6)).add((Object[])others)).build();
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] elements) {
        return ImmutableMultiset.copyFromElements(elements);
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterable<? extends E> elements) {
        ImmutableMultiset result;
        if (elements instanceof ImmutableMultiset && !(result = (ImmutableMultiset)elements).isPartialView()) {
            return result;
        }
        Multiset<E> multiset = elements instanceof Multiset ? Multisets.cast(elements) : LinkedHashMultiset.create(elements);
        return ImmutableMultiset.copyFromEntries(multiset.entrySet());
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> elements) {
        LinkedHashMultiset multiset = LinkedHashMultiset.create();
        Iterators.addAll(multiset, elements);
        return ImmutableMultiset.copyFromEntries(multiset.entrySet());
    }

    private static <E> ImmutableMultiset<E> copyFromElements(E ... elements) {
        LinkedHashMultiset multiset = LinkedHashMultiset.create();
        Collections.addAll(multiset, elements);
        return ImmutableMultiset.copyFromEntries(multiset.entrySet());
    }

    static <E> ImmutableMultiset<E> copyFromEntries(Collection<? extends Multiset.Entry<? extends E>> entries) {
        if (entries.isEmpty()) {
            return ImmutableMultiset.of();
        }
        return RegularImmutableMultiset.create(entries);
    }

    ImmutableMultiset() {
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        final Iterator entryIterator = ((ImmutableSet)this.entrySet()).iterator();
        return new UnmodifiableIterator<E>(){
            int remaining;
            @MonotonicNonNull E element;

            @Override
            public boolean hasNext() {
                return this.remaining > 0 || entryIterator.hasNext();
            }

            @Override
            public E next() {
                if (this.remaining <= 0) {
                    Multiset.Entry entry = (Multiset.Entry)entryIterator.next();
                    this.element = entry.getElement();
                    this.remaining = entry.getCount();
                }
                --this.remaining;
                return this.element;
            }
        };
    }

    @Override
    public ImmutableList<E> asList() {
        ImmutableList<E> result = this.asList;
        return result == null ? (this.asList = super.asList()) : result;
    }

    @Override
    public boolean contains(@Nullable Object object) {
        return this.count(object) > 0;
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final int add(E element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final int remove(Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final int setCount(E element, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean setCount(E element, int oldCount, int newCount) {
        throw new UnsupportedOperationException();
    }

    @Override
    @GwtIncompatible
    int copyIntoArray(Object[] dst, int offset) {
        for (Multiset.Entry entry : this.entrySet()) {
            Arrays.fill(dst, offset, offset + entry.getCount(), entry.getElement());
            offset += entry.getCount();
        }
        return offset;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    @Override
    public int hashCode() {
        return Sets.hashCodeImpl(this.entrySet());
    }

    @Override
    public String toString() {
        return ((AbstractCollection)((Object)this.entrySet())).toString();
    }

    @Override
    public abstract ImmutableSet<E> elementSet();

    @Override
    public ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<Multiset.Entry<E>>> es = this.entrySet;
        return es == null ? (this.entrySet = this.createEntrySet()) : es;
    }

    private ImmutableSet<Multiset.Entry<E>> createEntrySet() {
        return this.isEmpty() ? ImmutableSet.of() : new EntrySet();
    }

    abstract Multiset.Entry<E> getEntry(int var1);

    @Override
    @GwtIncompatible
    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <E> Builder<E> builder() {
        return new Builder();
    }

    static final class SerializedForm
    implements Serializable {
        final Object[] elements;
        final int[] counts;
        private static final long serialVersionUID = 0L;

        SerializedForm(Multiset<?> multiset) {
            int distinct = multiset.entrySet().size();
            this.elements = new Object[distinct];
            this.counts = new int[distinct];
            int i = 0;
            for (Multiset.Entry<?> entry : multiset.entrySet()) {
                this.elements[i] = entry.getElement();
                this.counts[i] = entry.getCount();
                ++i;
            }
        }

        Object readResolve() {
            LinkedHashMultiset multiset = LinkedHashMultiset.create(this.elements.length);
            for (int i = 0; i < this.elements.length; ++i) {
                multiset.add(this.elements[i], this.counts[i]);
            }
            return ImmutableMultiset.copyOf(multiset);
        }
    }

    static final class ElementSet<E>
    extends ImmutableSet.Indexed<E> {
        private final List<Multiset.Entry<E>> entries;
        private final Multiset<E> delegate;

        ElementSet(List<Multiset.Entry<E>> entries, Multiset<E> delegate) {
            this.entries = entries;
            this.delegate = delegate;
        }

        @Override
        E get(int index) {
            return this.entries.get(index).getElement();
        }

        @Override
        public boolean contains(@Nullable Object object) {
            return this.delegate.contains(object);
        }

        @Override
        boolean isPartialView() {
            return true;
        }

        @Override
        public int size() {
            return this.entries.size();
        }
    }

    public static class Builder<E>
    extends ImmutableCollection.Builder<E> {
        final Multiset<E> contents;

        public Builder() {
            this(LinkedHashMultiset.create());
        }

        Builder(Multiset<E> contents) {
            this.contents = contents;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            this.contents.add(Preconditions.checkNotNull(element));
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E ... elements) {
            super.add(elements);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E element, int occurrences) {
            this.contents.add(Preconditions.checkNotNull(element), occurrences);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E element, int count) {
            this.contents.setCount(Preconditions.checkNotNull(element), count);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            if (elements instanceof Multiset) {
                Multiset<Object> multiset = Multisets.cast(elements);
                multiset.forEachEntry((e, n) -> this.contents.add(Preconditions.checkNotNull(e), n));
            } else {
                super.addAll(elements);
            }
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll(elements);
            return this;
        }

        @Override
        public ImmutableMultiset<E> build() {
            return ImmutableMultiset.copyOf(this.contents);
        }

        @VisibleForTesting
        ImmutableMultiset<E> buildJdkBacked() {
            if (this.contents.isEmpty()) {
                return ImmutableMultiset.of();
            }
            return JdkBackedImmutableMultiset.create(this.contents.entrySet());
        }
    }

    @GwtIncompatible
    static class EntrySetSerializedForm<E>
    implements Serializable {
        final ImmutableMultiset<E> multiset;

        EntrySetSerializedForm(ImmutableMultiset<E> multiset) {
            this.multiset = multiset;
        }

        Object readResolve() {
            return this.multiset.entrySet();
        }
    }

    private final class EntrySet
    extends IndexedImmutableSet<Multiset.Entry<E>> {
        private static final long serialVersionUID = 0L;

        private EntrySet() {
        }

        @Override
        boolean isPartialView() {
            return ImmutableMultiset.this.isPartialView();
        }

        @Override
        Multiset.Entry<E> get(int index) {
            return ImmutableMultiset.this.getEntry(index);
        }

        @Override
        public int size() {
            return ((AbstractCollection)((Object)ImmutableMultiset.this.elementSet())).size();
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry)o;
                if (entry.getCount() <= 0) {
                    return false;
                }
                int count = ImmutableMultiset.this.count(entry.getElement());
                return count == entry.getCount();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        @Override
        @GwtIncompatible
        Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }
    }
}

