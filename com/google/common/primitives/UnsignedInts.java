/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.ParseRequest;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedInts {
    static final long INT_MASK = 0xFFFFFFFFL;

    private UnsignedInts() {
    }

    static int flip(int value) {
        return value ^ Integer.MIN_VALUE;
    }

    public static int compare(int a, int b) {
        return Ints.compare(UnsignedInts.flip(a), UnsignedInts.flip(b));
    }

    public static long toLong(int value) {
        return (long)value & 0xFFFFFFFFL;
    }

    public static int checkedCast(long value) {
        Preconditions.checkArgument(value >> 32 == 0L, "out of range: %s", value);
        return (int)value;
    }

    public static int saturatedCast(long value) {
        if (value <= 0L) {
            return 0;
        }
        if (value >= 0x100000000L) {
            return -1;
        }
        return (int)value;
    }

    public static int min(int ... array) {
        Preconditions.checkArgument(array.length > 0);
        int min = UnsignedInts.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            int next = UnsignedInts.flip(array[i]);
            if (next >= min) continue;
            min = next;
        }
        return UnsignedInts.flip(min);
    }

    public static int max(int ... array) {
        Preconditions.checkArgument(array.length > 0);
        int max = UnsignedInts.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            int next = UnsignedInts.flip(array[i]);
            if (next <= max) continue;
            max = next;
        }
        return UnsignedInts.flip(max);
    }

    public static String join(String separator, int ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(UnsignedInts.toString(array[0]));
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(UnsignedInts.toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void sort(int[] array) {
        Preconditions.checkNotNull(array);
        UnsignedInts.sort(array, 0, array.length);
    }

    public static void sort(int[] array, int fromIndex, int toIndex) {
        int i;
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (i = fromIndex; i < toIndex; ++i) {
            array[i] = UnsignedInts.flip(array[i]);
        }
        Arrays.sort(array, fromIndex, toIndex);
        for (i = fromIndex; i < toIndex; ++i) {
            array[i] = UnsignedInts.flip(array[i]);
        }
    }

    public static void sortDescending(int[] array) {
        Preconditions.checkNotNull(array);
        UnsignedInts.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        while (i < toIndex) {
            int n = i++;
            array[n] = array[n] ^ Integer.MAX_VALUE;
        }
        Arrays.sort(array, fromIndex, toIndex);
        i = fromIndex;
        while (i < toIndex) {
            int n = i++;
            array[n] = array[n] ^ Integer.MAX_VALUE;
        }
    }

    public static int divide(int dividend, int divisor) {
        return (int)(UnsignedInts.toLong(dividend) / UnsignedInts.toLong(divisor));
    }

    public static int remainder(int dividend, int divisor) {
        return (int)(UnsignedInts.toLong(dividend) % UnsignedInts.toLong(divisor));
    }

    @CanIgnoreReturnValue
    public static int decode(String stringValue) {
        ParseRequest request = ParseRequest.fromString(stringValue);
        try {
            return UnsignedInts.parseUnsignedInt(request.rawValue, request.radix);
        } catch (NumberFormatException e) {
            NumberFormatException decodeException = new NumberFormatException("Error parsing value: " + stringValue);
            decodeException.initCause(e);
            throw decodeException;
        }
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String s) {
        return UnsignedInts.parseUnsignedInt(s, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String string, int radix) {
        Preconditions.checkNotNull(string);
        long result = Long.parseLong(string, radix);
        if ((result & 0xFFFFFFFFL) != result) {
            throw new NumberFormatException("Input " + string + " in base " + radix + " is not in the range of an unsigned integer");
        }
        return (int)result;
    }

    public static String toString(int x) {
        return UnsignedInts.toString(x, 10);
    }

    public static String toString(int x, int radix) {
        long asLong = (long)x & 0xFFFFFFFFL;
        return Long.toString(asLong, radix);
    }

    static enum LexicographicalComparator implements Comparator<int[]>
    {
        INSTANCE;


        @Override
        public int compare(int[] left, int[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                if (left[i] == right[i]) continue;
                return UnsignedInts.compare(left[i], right[i]);
            }
            return left.length - right.length;
        }

        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }
    }
}

