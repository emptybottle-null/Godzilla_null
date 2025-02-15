/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Shorts {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    private Shorts() {
    }

    public static int hashCode(short value) {
        return value;
    }

    public static short checkedCast(long value) {
        short result = (short)value;
        Preconditions.checkArgument((long)result == value, "Out of range: %s", value);
        return result;
    }

    public static short saturatedCast(long value) {
        if (value > 32767L) {
            return Short.MAX_VALUE;
        }
        if (value < -32768L) {
            return Short.MIN_VALUE;
        }
        return (short)value;
    }

    public static int compare(short a, short b) {
        return a - b;
    }

    public static boolean contains(short[] array, short target) {
        for (short value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(short[] array, short target) {
        return Shorts.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(short[] array, short target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(short[] array, short[] target) {
        Preconditions.checkNotNull(array, "array");
        Preconditions.checkNotNull(target, "target");
        if (target.length == 0) {
            return 0;
        }
        block0: for (int i = 0; i < array.length - target.length + 1; ++i) {
            for (int j = 0; j < target.length; ++j) {
                if (array[i + j] != target[j]) continue block0;
            }
            return i;
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short target) {
        return Shorts.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(short[] array, short target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static short min(short ... array) {
        Preconditions.checkArgument(array.length > 0);
        short min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= min) continue;
            min = array[i];
        }
        return min;
    }

    public static short max(short ... array) {
        Preconditions.checkArgument(array.length > 0);
        short max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= max) continue;
            max = array[i];
        }
        return max;
    }

    @Beta
    public static short constrainToRange(short value, short min, short max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", (int)min, (int)max);
        return value < min ? min : (value < max ? value : max);
    }

    public static short[] concat(short[] ... arrays) {
        int length = 0;
        for (short[] array : arrays) {
            length += array.length;
        }
        short[] result = new short[length];
        int pos = 0;
        for (short[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short value) {
        return new byte[]{(byte)(value >> 8), (byte)value};
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 2, "array too small: %s < %s", bytes.length, 2);
        return Shorts.fromBytes(bytes[0], bytes[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b1, byte b2) {
        return (short)(b1 << 8 | b2 & 0xFF);
    }

    @Beta
    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] ensureCapacity(short[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, short ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 6);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void sortDescending(short[] array) {
        Preconditions.checkNotNull(array);
        Shorts.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(short[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Shorts.reverse(array, fromIndex, toIndex);
    }

    public static void reverse(short[] array) {
        Preconditions.checkNotNull(array);
        Shorts.reverse(array, 0, array.length);
    }

    public static void reverse(short[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            short tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList)collection).toShortArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        short[] array = new short[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).shortValue();
        }
        return array;
    }

    public static List<Short> asList(short ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new ShortArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class ShortArrayAsList
    extends AbstractList<Short>
    implements RandomAccess,
    Serializable {
        final short[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        ShortArrayAsList(short[] array) {
            this(array, 0, array.length);
        }

        ShortArrayAsList(short[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public int size() {
            return this.end - this.start;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Short get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return this.array[this.start + index];
        }

        @Override
        public boolean contains(@Nullable Object target) {
            return target instanceof Short && Shorts.indexOf(this.array, (Short)target, this.start, this.end) != -1;
        }

        @Override
        public int indexOf(@Nullable Object target) {
            int i;
            if (target instanceof Short && (i = Shorts.indexOf(this.array, (Short)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(@Nullable Object target) {
            int i;
            if (target instanceof Short && (i = Shorts.lastIndexOf(this.array, (Short)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Short set(int index, Short element) {
            Preconditions.checkElementIndex(index, this.size());
            short oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element);
            return oldValue;
        }

        @Override
        public List<Short> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new ShortArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof ShortArrayAsList) {
                ShortArrayAsList that = (ShortArrayAsList)object;
                int size = this.size();
                if (that.size() != size) {
                    return false;
                }
                for (int i = 0; i < size; ++i) {
                    if (this.array[this.start + i] == that.array[that.start + i]) continue;
                    return false;
                }
                return true;
            }
            return super.equals(object);
        }

        @Override
        public int hashCode() {
            int result = 1;
            for (int i = this.start; i < this.end; ++i) {
                result = 31 * result + Shorts.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 6);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    private static enum LexicographicalComparator implements Comparator<short[]>
    {
        INSTANCE;


        @Override
        public int compare(short[] left, short[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result = Shorts.compare(left[i], right[i]);
                if (result == 0) continue;
                return result;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }
    }

    private static final class ShortConverter
    extends Converter<String, Short>
    implements Serializable {
        static final ShortConverter INSTANCE = new ShortConverter();
        private static final long serialVersionUID = 1L;

        private ShortConverter() {
        }

        @Override
        protected Short doForward(String value) {
            return Short.decode(value);
        }

        @Override
        protected String doBackward(Short value) {
            return value.toString();
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}

