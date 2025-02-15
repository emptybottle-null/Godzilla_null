/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Platform;
import com.google.common.collect.RegularImmutableAsList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public abstract class ImmutableCollection<E>
extends AbstractCollection<E>
implements Serializable {
    static final int SPLITERATOR_CHARACTERISTICS = 1296;
    private static final Object[] EMPTY_ARRAY = new Object[0];

    ImmutableCollection() {
    }

    @Override
    public abstract UnmodifiableIterator<E> iterator();

    @Override
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    @Override
    public final Object[] toArray() {
        return this.toArray(EMPTY_ARRAY);
    }

    @Override
    @CanIgnoreReturnValue
    public final <T> T[] toArray(T[] other) {
        Preconditions.checkNotNull(other);
        int size = this.size();
        if (other.length < size) {
            Object[] internal = this.internalArray();
            if (internal != null) {
                return Platform.copy(internal, this.internalArrayStart(), this.internalArrayEnd(), other);
            }
            other = ObjectArrays.newArray(other, size);
        } else if (other.length > size) {
            other[size] = null;
        }
        this.copyIntoArray(other, 0);
        return other;
    }

    @Nullable Object[] internalArray() {
        return null;
    }

    int internalArrayStart() {
        throw new UnsupportedOperationException();
    }

    int internalArrayEnd() {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract boolean contains(@Nullable Object var1);

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> newElements) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> oldElements) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean retainAll(Collection<?> elementsToKeep) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> asList() {
        switch (this.size()) {
            case 0: {
                return ImmutableList.of();
            }
            case 1: {
                return ImmutableList.of(this.iterator().next());
            }
        }
        return new RegularImmutableAsList(this, this.toArray());
    }

    abstract boolean isPartialView();

    @CanIgnoreReturnValue
    int copyIntoArray(Object[] dst, int offset) {
        for (Object e : this) {
            dst[offset++] = e;
        }
        return offset;
    }

    Object writeReplace() {
        return new ImmutableList.SerializedForm(this.toArray());
    }

    public static abstract class Builder<E> {
        static final int DEFAULT_INITIAL_CAPACITY = 4;

        static int expandedCapacity(int oldCapacity, int minCapacity) {
            if (minCapacity < 0) {
                throw new AssertionError((Object)"cannot store more than MAX_VALUE elements");
            }
            int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
            }
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            return newCapacity;
        }

        Builder() {
        }

        @CanIgnoreReturnValue
        public abstract Builder<E> add(E var1);

        @CanIgnoreReturnValue
        public Builder<E> add(E ... elements) {
            for (E element : elements) {
                this.add(element);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> elements) {
            for (E element : elements) {
                this.add(element);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> elements) {
            while (elements.hasNext()) {
                this.add(elements.next());
            }
            return this;
        }

        public abstract ImmutableCollection<E> build();
    }
}

