/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableAsList;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableEnumSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.JdkBackedImmutableSet;
import com.google.common.collect.RegularImmutableAsList;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public abstract class ImmutableSet<E>
extends ImmutableCollection<E>
implements Set<E> {
    static final int SPLITERATOR_CHARACTERISTICS = 1297;
    @LazyInit
    @RetainedWith
    private transient @Nullable ImmutableList<E> asList;
    static final int MAX_TABLE_SIZE = 0x40000000;
    private static final double DESIRED_LOAD_FACTOR = 0.7;
    private static final int CUTOFF = 0x2CCCCCCC;
    static final double HASH_FLOODING_FPP = 0.001;
    static final int MAX_RUN_MULTIPLIER = 13;

    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return CollectCollectors.toImmutableSet();
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E element) {
        return new SingletonImmutableSet<E>(element);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2) {
        return ImmutableSet.construct(2, e1, e2);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3) {
        return ImmutableSet.construct(3, e1, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4) {
        return ImmutableSet.construct(4, e1, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        return ImmutableSet.construct(5, e1, e2, e3, e4, e5);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E ... others) {
        Preconditions.checkArgument(others.length <= 0x7FFFFFF9, "the total number of elements must fit in an int");
        int paramCount = 6;
        Object[] elements = new Object[6 + others.length];
        elements[0] = e1;
        elements[1] = e2;
        elements[2] = e3;
        elements[3] = e4;
        elements[4] = e5;
        elements[5] = e6;
        System.arraycopy(others, 0, elements, 6, others.length);
        return ImmutableSet.construct(elements.length, elements);
    }

    private static <E> ImmutableSet<E> construct(int n, Object ... elements) {
        switch (n) {
            case 0: {
                return ImmutableSet.of();
            }
            case 1: {
                Object elem = elements[0];
                return ImmutableSet.of(elem);
            }
        }
        SetBuilderImpl builder = new RegularSetBuilderImpl<Object>(4);
        for (int i = 0; i < n; ++i) {
            Object e = Preconditions.checkNotNull(elements[i]);
            builder = ((SetBuilderImpl)builder).add(e);
        }
        return ((SetBuilderImpl)builder).review().build();
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> elements) {
        if (elements instanceof ImmutableSet && !(elements instanceof SortedSet)) {
            ImmutableSet set = (ImmutableSet)elements;
            if (!set.isPartialView()) {
                return set;
            }
        } else if (elements instanceof EnumSet) {
            return ImmutableSet.copyOfEnumSet((EnumSet)elements);
        }
        Object[] array = elements.toArray();
        return ImmutableSet.construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> elements) {
        return elements instanceof Collection ? ImmutableSet.copyOf((Collection)elements) : ImmutableSet.copyOf(elements.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> elements) {
        if (!elements.hasNext()) {
            return ImmutableSet.of();
        }
        E first = elements.next();
        if (!elements.hasNext()) {
            return ImmutableSet.of(first);
        }
        return ((Builder)((Builder)new Builder().add((Object)first)).addAll(elements)).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] elements) {
        switch (elements.length) {
            case 0: {
                return ImmutableSet.of();
            }
            case 1: {
                return ImmutableSet.of(elements[0]);
            }
        }
        return ImmutableSet.construct(elements.length, (Object[])elements.clone());
    }

    private static ImmutableSet copyOfEnumSet(EnumSet enumSet) {
        return ImmutableEnumSet.asImmutable(EnumSet.copyOf(enumSet));
    }

    ImmutableSet() {
    }

    boolean isHashCodeFast() {
        return false;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ImmutableSet && this.isHashCodeFast() && ((ImmutableSet)object).isHashCodeFast() && this.hashCode() != object.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, object);
    }

    @Override
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override
    public abstract UnmodifiableIterator<E> iterator();

    @Override
    public ImmutableList<E> asList() {
        ImmutableList<E> result = this.asList;
        return result == null ? (this.asList = this.createAsList()) : result;
    }

    ImmutableList<E> createAsList() {
        return new RegularImmutableAsList(this, this.toArray());
    }

    @Override
    Object writeReplace() {
        return new SerializedForm(this.toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        return new Builder(expectedSize);
    }

    static Object[] rebuildHashTable(int newTableSize, Object[] elements, int n) {
        Object[] hashTable = new Object[newTableSize];
        int mask = hashTable.length - 1;
        for (int i = 0; i < n; ++i) {
            int j0;
            Object e = elements[i];
            int j = j0 = Hashing.smear(e.hashCode());
            while (true) {
                int index;
                if (hashTable[index = j & mask] == null) break;
                ++j;
            }
            hashTable[index] = e;
        }
        return hashTable;
    }

    @VisibleForTesting
    static int chooseTableSize(int setSize) {
        if ((setSize = Math.max(setSize, 2)) < 0x2CCCCCCC) {
            int tableSize = Integer.highestOneBit(setSize - 1) << 1;
            while ((double)tableSize * 0.7 < (double)setSize) {
                tableSize <<= 1;
            }
            return tableSize;
        }
        Preconditions.checkArgument(setSize < 0x40000000, "collection too large");
        return 0x40000000;
    }

    static boolean hashFloodingDetected(Object[] hashTable) {
        int startOfEndRun;
        int maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(hashTable.length);
        int endOfStartRun = 0;
        while (endOfStartRun < hashTable.length && hashTable[endOfStartRun] != null) {
            if (++endOfStartRun <= maxRunBeforeFallback) continue;
            return true;
        }
        for (startOfEndRun = hashTable.length - 1; startOfEndRun > endOfStartRun && hashTable[startOfEndRun] != null; --startOfEndRun) {
            if (endOfStartRun + (hashTable.length - 1 - startOfEndRun) <= maxRunBeforeFallback) continue;
            return true;
        }
        int testBlockSize = maxRunBeforeFallback / 2;
        int i = endOfStartRun + 1;
        while (i + testBlockSize <= startOfEndRun) {
            block5: {
                for (int j = 0; j < testBlockSize; ++j) {
                    if (hashTable[i + j] != null) {
                        continue;
                    }
                    break block5;
                }
                return true;
            }
            i += testBlockSize;
        }
        return false;
    }

    private static int maxRunBeforeFallback(int tableSize) {
        return 13 * IntMath.log2(tableSize, RoundingMode.UNNECESSARY);
    }

    private static final class JdkBackedSetBuilderImpl<E>
    extends SetBuilderImpl<E> {
        private final Set<Object> delegate;

        JdkBackedSetBuilderImpl(SetBuilderImpl<E> toCopy) {
            super(toCopy);
            this.delegate = Sets.newHashSetWithExpectedSize(this.distinct);
            for (int i = 0; i < this.distinct; ++i) {
                this.delegate.add(this.dedupedElements[i]);
            }
        }

        @Override
        SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            if (this.delegate.add(e)) {
                this.addDedupedElement(e);
            }
            return this;
        }

        @Override
        SetBuilderImpl<E> copy() {
            return new JdkBackedSetBuilderImpl<E>(this);
        }

        @Override
        ImmutableSet<E> build() {
            switch (this.distinct) {
                case 0: {
                    return ImmutableSet.of();
                }
                case 1: {
                    return ImmutableSet.of(this.dedupedElements[0]);
                }
            }
            return new JdkBackedImmutableSet(this.delegate, ImmutableList.asImmutableList(this.dedupedElements, this.distinct));
        }
    }

    private static final class RegularSetBuilderImpl<E>
    extends SetBuilderImpl<E> {
        private Object[] hashTable;
        private int maxRunBeforeFallback;
        private int expandTableThreshold;
        private int hashCode;

        RegularSetBuilderImpl(int expectedCapacity) {
            super(expectedCapacity);
            int tableSize = ImmutableSet.chooseTableSize(expectedCapacity);
            this.hashTable = new Object[tableSize];
            this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(tableSize);
            this.expandTableThreshold = (int)(0.7 * (double)tableSize);
        }

        RegularSetBuilderImpl(RegularSetBuilderImpl<E> toCopy) {
            super(toCopy);
            this.hashTable = Arrays.copyOf(toCopy.hashTable, toCopy.hashTable.length);
            this.maxRunBeforeFallback = toCopy.maxRunBeforeFallback;
            this.expandTableThreshold = toCopy.expandTableThreshold;
            this.hashCode = toCopy.hashCode;
        }

        void ensureTableCapacity(int minCapacity) {
            if (minCapacity > this.expandTableThreshold && this.hashTable.length < 0x40000000) {
                int newTableSize = this.hashTable.length * 2;
                this.hashTable = ImmutableSet.rebuildHashTable(newTableSize, this.dedupedElements, this.distinct);
                this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(newTableSize);
                this.expandTableThreshold = (int)(0.7 * (double)newTableSize);
            }
        }

        @Override
        SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            int eHash = e.hashCode();
            int i0 = Hashing.smear(eHash);
            int mask = this.hashTable.length - 1;
            int i = i0;
            while (i - i0 < this.maxRunBeforeFallback) {
                int index = i & mask;
                Object tableEntry = this.hashTable[index];
                if (tableEntry == null) {
                    this.addDedupedElement(e);
                    this.hashTable[index] = e;
                    this.hashCode += eHash;
                    this.ensureTableCapacity(this.distinct);
                    return this;
                }
                if (tableEntry.equals(e)) {
                    return this;
                }
                ++i;
            }
            return new JdkBackedSetBuilderImpl<E>(this).add(e);
        }

        @Override
        SetBuilderImpl<E> copy() {
            return new RegularSetBuilderImpl<E>(this);
        }

        @Override
        SetBuilderImpl<E> review() {
            int targetTableSize = ImmutableSet.chooseTableSize(this.distinct);
            if (targetTableSize * 2 < this.hashTable.length) {
                this.hashTable = ImmutableSet.rebuildHashTable(targetTableSize, this.dedupedElements, this.distinct);
            }
            return ImmutableSet.hashFloodingDetected(this.hashTable) ? new JdkBackedSetBuilderImpl(this) : this;
        }

        @Override
        ImmutableSet<E> build() {
            switch (this.distinct) {
                case 0: {
                    return ImmutableSet.of();
                }
                case 1: {
                    return ImmutableSet.of(this.dedupedElements[0]);
                }
            }
            Object[] elements = this.distinct == this.dedupedElements.length ? this.dedupedElements : Arrays.copyOf(this.dedupedElements, this.distinct);
            return new RegularImmutableSet(elements, this.hashCode, this.hashTable, this.hashTable.length - 1);
        }
    }

    private static abstract class SetBuilderImpl<E> {
        E[] dedupedElements;
        int distinct;

        SetBuilderImpl(int expectedCapacity) {
            this.dedupedElements = new Object[expectedCapacity];
            this.distinct = 0;
        }

        SetBuilderImpl(SetBuilderImpl<E> toCopy) {
            this.dedupedElements = Arrays.copyOf(toCopy.dedupedElements, toCopy.dedupedElements.length);
            this.distinct = toCopy.distinct;
        }

        private void ensureCapacity(int minCapacity) {
            if (minCapacity > this.dedupedElements.length) {
                int newCapacity = ImmutableCollection.Builder.expandedCapacity(this.dedupedElements.length, minCapacity);
                this.dedupedElements = Arrays.copyOf(this.dedupedElements, newCapacity);
            }
        }

        final void addDedupedElement(E e) {
            this.ensureCapacity(this.distinct + 1);
            this.dedupedElements[this.distinct++] = e;
        }

        abstract SetBuilderImpl<E> add(E var1);

        final SetBuilderImpl<E> combine(SetBuilderImpl<E> other) {
            SetBuilderImpl<E> result = this;
            for (int i = 0; i < other.distinct; ++i) {
                result = result.add(other.dedupedElements[i]);
            }
            return result;
        }

        abstract SetBuilderImpl<E> copy();

        SetBuilderImpl<E> review() {
            return this;
        }

        abstract ImmutableSet<E> build();
    }

    public static class Builder<E>
    extends ImmutableCollection.Builder<E> {
        private SetBuilderImpl<E> impl;
        boolean forceCopy;

        public Builder() {
            this(4);
        }

        Builder(int capacity) {
            this.impl = new RegularSetBuilderImpl(capacity);
        }

        Builder(boolean subclass) {
            this.impl = null;
        }

        @VisibleForTesting
        void forceJdk() {
            this.impl = new JdkBackedSetBuilderImpl<E>(this.impl);
        }

        final void copyIfNecessary() {
            if (this.forceCopy) {
                this.copy();
                this.forceCopy = false;
            }
        }

        void copy() {
            this.impl = this.impl.copy();
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E element) {
            Preconditions.checkNotNull(element);
            this.copyIfNecessary();
            this.impl = this.impl.add(element);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> add(E ... elements) {
            super.add(elements);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            super.addAll(elements);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            super.addAll(elements);
            return this;
        }

        Builder<E> combine(Builder<E> other) {
            this.copyIfNecessary();
            this.impl = this.impl.combine(other.impl);
            return this;
        }

        @Override
        public ImmutableSet<E> build() {
            this.forceCopy = true;
            this.impl = this.impl.review();
            return this.impl.build();
        }
    }

    private static class SerializedForm
    implements Serializable {
        final Object[] elements;
        private static final long serialVersionUID = 0L;

        SerializedForm(Object[] elements) {
            this.elements = elements;
        }

        Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    static abstract class Indexed<E>
    extends ImmutableSet<E> {
        Indexed() {
        }

        abstract E get(int var1);

        @Override
        public UnmodifiableIterator<E> iterator() {
            return this.asList().iterator();
        }

        @Override
        public Spliterator<E> spliterator() {
            return CollectSpliterators.indexed(this.size(), 1297, this::get);
        }

        @Override
        public void forEach(Consumer<? super E> consumer) {
            Preconditions.checkNotNull(consumer);
            int n = this.size();
            for (int i = 0; i < n; ++i) {
                consumer.accept(this.get(i));
            }
        }

        @Override
        int copyIntoArray(Object[] dst, int offset) {
            return this.asList().copyIntoArray(dst, offset);
        }

        @Override
        ImmutableList<E> createAsList() {
            return new ImmutableAsList<E>(){

                @Override
                public E get(int index) {
                    return this.get(index);
                }

                @Override
                Indexed<E> delegateCollection() {
                    return this;
                }
            };
        }
    }
}

