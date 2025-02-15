/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
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
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 0x4000000000000000L;

    private Longs() {
    }

    public static int hashCode(long value) {
        return (int)(value ^ value >>> 32);
    }

    public static int compare(long a, long b) {
        return a < b ? -1 : (a > b ? 1 : 0);
    }

    public static boolean contains(long[] array, long target) {
        for (long value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(long[] array, long target) {
        return Longs.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(long[] array, long target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(long[] array, long[] target) {
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

    public static int lastIndexOf(long[] array, long target) {
        return Longs.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(long[] array, long target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static long min(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= min) continue;
            min = array[i];
        }
        return min;
    }

    public static long max(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= max) continue;
            max = array[i];
        }
        return max;
    }

    @Beta
    public static long constrainToRange(long value, long min, long max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
        return Math.min(Math.max(value, min), max);
    }

    public static long[] concat(long[] ... arrays) {
        int length = 0;
        for (long[] array : arrays) {
            length += array.length;
        }
        long[] result = new long[length];
        int pos = 0;
        for (long[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public static byte[] toByteArray(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; --i) {
            result[i] = (byte)(value & 0xFFL);
            value >>= 8;
        }
        return result;
    }

    public static long fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 8, "array too small: %s < %s", bytes.length, 8);
        return Longs.fromBytes(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public static long fromBytes(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((long)b1 & 0xFFL) << 56 | ((long)b2 & 0xFFL) << 48 | ((long)b3 & 0xFFL) << 40 | ((long)b4 & 0xFFL) << 32 | ((long)b5 & 0xFFL) << 24 | ((long)b6 & 0xFFL) << 16 | ((long)b7 & 0xFFL) << 8 | (long)b8 & 0xFFL;
    }

    @Beta
    public static @Nullable Long tryParse(String string) {
        return Longs.tryParse(string, 10);
    }

    @Beta
    public static @Nullable Long tryParse(String string, int radix) {
        int digit;
        int index;
        if (Preconditions.checkNotNull(string).isEmpty()) {
            return null;
        }
        if (radix < 2 || radix > 36) {
            throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + radix);
        }
        boolean negative = string.charAt(0) == '-';
        int n = index = negative ? 1 : 0;
        if (index == string.length()) {
            return null;
        }
        if ((digit = AsciiDigits.digit(string.charAt(index++))) < 0 || digit >= radix) {
            return null;
        }
        long accum = -digit;
        long cap = Long.MIN_VALUE / (long)radix;
        while (index < string.length()) {
            if ((digit = AsciiDigits.digit(string.charAt(index++))) < 0 || digit >= radix || accum < cap) {
                return null;
            }
            if ((accum *= (long)radix) < Long.MIN_VALUE + (long)digit) {
                return null;
            }
            accum -= (long)digit;
        }
        if (negative) {
            return accum;
        }
        if (accum == Long.MIN_VALUE) {
            return null;
        }
        return -accum;
    }

    @Beta
    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] ensureCapacity(long[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, long ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 10);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void sortDescending(long[] array) {
        Preconditions.checkNotNull(array);
        Longs.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Longs.reverse(array, fromIndex, toIndex);
    }

    public static void reverse(long[] array) {
        Preconditions.checkNotNull(array);
        Longs.reverse(array, 0, array.length);
    }

    public static void reverse(long[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            long tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList)collection).toLongArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        long[] array = new long[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).longValue();
        }
        return array;
    }

    public static List<Long> asList(long ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new LongArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class LongArrayAsList
    extends AbstractList<Long>
    implements RandomAccess,
    Serializable {
        final long[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        LongArrayAsList(long[] array) {
            this(array, 0, array.length);
        }

        LongArrayAsList(long[] array, int start, int end) {
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
        public Long get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return this.array[this.start + index];
        }

        public Spliterator.OfLong spliterator() {
            return Spliterators.spliterator(this.array, this.start, this.end, 0);
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Long && Longs.indexOf(this.array, (Long)target, this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Long && (i = Longs.indexOf(this.array, (Long)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Long && (i = Longs.lastIndexOf(this.array, (Long)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Long set(int index, Long element) {
            Preconditions.checkElementIndex(index, this.size());
            long oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element);
            return oldValue;
        }

        @Override
        public List<Long> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new LongArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof LongArrayAsList) {
                LongArrayAsList that = (LongArrayAsList)object;
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
                result = 31 * result + Longs.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 10);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    private static enum LexicographicalComparator implements Comparator<long[]>
    {
        INSTANCE;


        @Override
        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result = Longs.compare(left[i], right[i]);
                if (result == 0) continue;
                return result;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Longs.lexicographicalComparator()";
        }
    }

    private static final class LongConverter
    extends Converter<String, Long>
    implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1L;

        private LongConverter() {
        }

        @Override
        protected Long doForward(String value) {
            return Long.decode(value);
        }

        @Override
        protected String doBackward(Long value) {
            return value.toString();
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    static final class AsciiDigits {
        private static final byte[] asciiDigits;

        private AsciiDigits() {
        }

        static int digit(char c) {
            return c < '\u0080' ? asciiDigits[c] : -1;
        }

        static {
            int i;
            byte[] result = new byte[128];
            Arrays.fill(result, (byte)-1);
            for (i = 0; i <= 9; ++i) {
                result[48 + i] = (byte)i;
            }
            for (i = 0; i <= 26; ++i) {
                result[65 + i] = (byte)(10 + i);
                result[97 + i] = (byte)(10 + i);
            }
            asciiDigits = result;
        }
    }
}

