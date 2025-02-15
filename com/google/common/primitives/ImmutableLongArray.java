/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable
@Beta
@GwtCompatible
public final class ImmutableLongArray
implements Serializable {
    private static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0]);
    private final long[] array;
    private final transient int start;
    private final int end;

    public static ImmutableLongArray of() {
        return EMPTY;
    }

    public static ImmutableLongArray of(long e0) {
        return new ImmutableLongArray(new long[]{e0});
    }

    public static ImmutableLongArray of(long e0, long e1) {
        return new ImmutableLongArray(new long[]{e0, e1});
    }

    public static ImmutableLongArray of(long e0, long e1, long e2) {
        return new ImmutableLongArray(new long[]{e0, e1, e2});
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3});
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3, long e4) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3, e4});
    }

    public static ImmutableLongArray of(long e0, long e1, long e2, long e3, long e4, long e5) {
        return new ImmutableLongArray(new long[]{e0, e1, e2, e3, e4, e5});
    }

    public static ImmutableLongArray of(long first, long ... rest) {
        Preconditions.checkArgument(rest.length <= 0x7FFFFFFE, "the total number of elements must fit in an int");
        long[] array = new long[rest.length + 1];
        array[0] = first;
        System.arraycopy(rest, 0, array, 1, rest.length);
        return new ImmutableLongArray(array);
    }

    public static ImmutableLongArray copyOf(long[] values) {
        return values.length == 0 ? EMPTY : new ImmutableLongArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableLongArray copyOf(Collection<Long> values) {
        return values.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(values));
    }

    public static ImmutableLongArray copyOf(Iterable<Long> values) {
        if (values instanceof Collection) {
            return ImmutableLongArray.copyOf((Collection)values);
        }
        return ImmutableLongArray.builder().addAll(values).build();
    }

    public static ImmutableLongArray copyOf(LongStream stream) {
        long[] array = stream.toArray();
        return array.length == 0 ? EMPTY : new ImmutableLongArray(array);
    }

    public static Builder builder(int initialCapacity) {
        Preconditions.checkArgument(initialCapacity >= 0, "Invalid initialCapacity: %s", initialCapacity);
        return new Builder(initialCapacity);
    }

    public static Builder builder() {
        return new Builder(10);
    }

    private ImmutableLongArray(long[] array) {
        this(array, 0, array.length);
    }

    private ImmutableLongArray(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public long get(int index) {
        Preconditions.checkElementIndex(index, this.length());
        return this.array[this.start + index];
    }

    public int indexOf(long target) {
        for (int i = this.start; i < this.end; ++i) {
            if (this.array[i] != target) continue;
            return i - this.start;
        }
        return -1;
    }

    public int lastIndexOf(long target) {
        for (int i = this.end - 1; i >= this.start; --i) {
            if (this.array[i] != target) continue;
            return i - this.start;
        }
        return -1;
    }

    public boolean contains(long target) {
        return this.indexOf(target) >= 0;
    }

    public void forEach(LongConsumer consumer) {
        Preconditions.checkNotNull(consumer);
        for (int i = this.start; i < this.end; ++i) {
            consumer.accept(this.array[i]);
        }
    }

    public LongStream stream() {
        return Arrays.stream(this.array, this.start, this.end);
    }

    public long[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableLongArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, this.length());
        return startIndex == endIndex ? EMPTY : new ImmutableLongArray(this.array, this.start + startIndex, this.start + endIndex);
    }

    private Spliterator.OfLong spliterator() {
        return Spliterators.spliterator(this.array, this.start, this.end, 1040);
    }

    public List<Long> asList() {
        return new AsList(this);
    }

    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableLongArray)) {
            return false;
        }
        ImmutableLongArray that = (ImmutableLongArray)object;
        if (this.length() != that.length()) {
            return false;
        }
        for (int i = 0; i < this.length(); ++i) {
            if (this.get(i) == that.get(i)) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 1;
        for (int i = this.start; i < this.end; ++i) {
            hash *= 31;
            hash += Longs.hashCode(this.array[i]);
        }
        return hash;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder(this.length() * 5);
        builder.append('[').append(this.array[this.start]);
        for (int i = this.start + 1; i < this.end; ++i) {
            builder.append(", ").append(this.array[i]);
        }
        builder.append(']');
        return builder.toString();
    }

    public ImmutableLongArray trimmed() {
        return this.isPartialView() ? new ImmutableLongArray(this.toArray()) : this;
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    Object writeReplace() {
        return this.trimmed();
    }

    Object readResolve() {
        return this.isEmpty() ? EMPTY : this;
    }

    static class AsList
    extends AbstractList<Long>
    implements RandomAccess,
    Serializable {
        private final ImmutableLongArray parent;

        private AsList(ImmutableLongArray parent) {
            this.parent = parent;
        }

        @Override
        public int size() {
            return this.parent.length();
        }

        @Override
        public Long get(int index) {
            return this.parent.get(index);
        }

        @Override
        public boolean contains(Object target) {
            return this.indexOf(target) >= 0;
        }

        @Override
        public int indexOf(Object target) {
            return target instanceof Long ? this.parent.indexOf((Long)target) : -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            return target instanceof Long ? this.parent.lastIndexOf((Long)target) : -1;
        }

        @Override
        public List<Long> subList(int fromIndex, int toIndex) {
            return this.parent.subArray(fromIndex, toIndex).asList();
        }

        @Override
        public Spliterator<Long> spliterator() {
            return this.parent.spliterator();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object instanceof AsList) {
                AsList that = (AsList)object;
                return this.parent.equals(that.parent);
            }
            if (!(object instanceof List)) {
                return false;
            }
            List that = (List)object;
            if (this.size() != that.size()) {
                return false;
            }
            int i = this.parent.start;
            for (Object element : that) {
                if (element instanceof Long && this.parent.array[i++] == (Long)element) continue;
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return this.parent.hashCode();
        }

        @Override
        public String toString() {
            return this.parent.toString();
        }
    }

    @CanIgnoreReturnValue
    public static final class Builder {
        private long[] array;
        private int count = 0;

        Builder(int initialCapacity) {
            this.array = new long[initialCapacity];
        }

        public Builder add(long value) {
            this.ensureRoomFor(1);
            this.array[this.count] = value;
            ++this.count;
            return this;
        }

        public Builder addAll(long[] values) {
            this.ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public Builder addAll(Iterable<Long> values) {
            if (values instanceof Collection) {
                return this.addAll((Collection)values);
            }
            for (Long value : values) {
                this.add(value);
            }
            return this;
        }

        public Builder addAll(Collection<Long> values) {
            this.ensureRoomFor(values.size());
            for (Long value : values) {
                this.array[this.count++] = value;
            }
            return this;
        }

        public Builder addAll(LongStream stream) {
            Spliterator.OfLong spliterator = stream.spliterator();
            long size = spliterator.getExactSizeIfKnown();
            if (size > 0L) {
                this.ensureRoomFor(Ints.saturatedCast(size));
            }
            spliterator.forEachRemaining(this::add);
            return this;
        }

        public Builder addAll(ImmutableLongArray values) {
            this.ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count += values.length();
            return this;
        }

        private void ensureRoomFor(int numberToAdd) {
            int newCount = this.count + numberToAdd;
            if (newCount > this.array.length) {
                long[] newArray = new long[Builder.expandedCapacity(this.array.length, newCount)];
                System.arraycopy(this.array, 0, newArray, 0, this.count);
                this.array = newArray;
            }
        }

        private static int expandedCapacity(int oldCapacity, int minCapacity) {
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

        @CheckReturnValue
        public ImmutableLongArray build() {
            return this.count == 0 ? EMPTY : new ImmutableLongArray(this.array, 0, this.count);
        }
    }
}

