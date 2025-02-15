/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.ParseRequest;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1L;

    private UnsignedLongs() {
    }

    private static long flip(long a) {
        return a ^ Long.MIN_VALUE;
    }

    public static int compare(long a, long b) {
        return Longs.compare(UnsignedLongs.flip(a), UnsignedLongs.flip(b));
    }

    public static long min(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long min = UnsignedLongs.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            long next = UnsignedLongs.flip(array[i]);
            if (next >= min) continue;
            min = next;
        }
        return UnsignedLongs.flip(min);
    }

    public static long max(long ... array) {
        Preconditions.checkArgument(array.length > 0);
        long max = UnsignedLongs.flip(array[0]);
        for (int i = 1; i < array.length; ++i) {
            long next = UnsignedLongs.flip(array[i]);
            if (next <= max) continue;
            max = next;
        }
        return UnsignedLongs.flip(max);
    }

    public static String join(String separator, long ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(UnsignedLongs.toString(array[0]));
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(UnsignedLongs.toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void sort(long[] array) {
        Preconditions.checkNotNull(array);
        UnsignedLongs.sort(array, 0, array.length);
    }

    public static void sort(long[] array, int fromIndex, int toIndex) {
        int i;
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (i = fromIndex; i < toIndex; ++i) {
            array[i] = UnsignedLongs.flip(array[i]);
        }
        Arrays.sort(array, fromIndex, toIndex);
        for (i = fromIndex; i < toIndex; ++i) {
            array[i] = UnsignedLongs.flip(array[i]);
        }
    }

    public static void sortDescending(long[] array) {
        Preconditions.checkNotNull(array);
        UnsignedLongs.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        while (i < toIndex) {
            int n = i++;
            array[n] = array[n] ^ Long.MAX_VALUE;
        }
        Arrays.sort(array, fromIndex, toIndex);
        i = fromIndex;
        while (i < toIndex) {
            int n = i++;
            array[n] = array[n] ^ Long.MAX_VALUE;
        }
    }

    public static long divide(long dividend, long divisor) {
        long quotient;
        if (divisor < 0L) {
            if (UnsignedLongs.compare(dividend, divisor) < 0) {
                return 0L;
            }
            return 1L;
        }
        if (dividend >= 0L) {
            return dividend / divisor;
        }
        long rem = dividend - (quotient = (dividend >>> 1) / divisor << 1) * divisor;
        return quotient + (long)(UnsignedLongs.compare(rem, divisor) >= 0 ? 1 : 0);
    }

    public static long remainder(long dividend, long divisor) {
        long rem;
        if (divisor < 0L) {
            if (UnsignedLongs.compare(dividend, divisor) < 0) {
                return dividend;
            }
            return dividend - divisor;
        }
        if (dividend >= 0L) {
            return dividend % divisor;
        }
        long quotient = (dividend >>> 1) / divisor << 1;
        return rem - (UnsignedLongs.compare(rem = dividend - quotient * divisor, divisor) >= 0 ? divisor : 0L);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String string) {
        return UnsignedLongs.parseUnsignedLong(string, 10);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String string, int radix) {
        Preconditions.checkNotNull(string);
        if (string.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        if (radix < 2 || radix > 36) {
            throw new NumberFormatException("illegal radix: " + radix);
        }
        int maxSafePos = ParseOverflowDetection.maxSafeDigits[radix] - 1;
        long value = 0L;
        for (int pos = 0; pos < string.length(); ++pos) {
            int digit = Character.digit(string.charAt(pos), radix);
            if (digit == -1) {
                throw new NumberFormatException(string);
            }
            if (pos > maxSafePos && ParseOverflowDetection.overflowInParse(value, digit, radix)) {
                throw new NumberFormatException("Too large for unsigned long: " + string);
            }
            value = value * (long)radix + (long)digit;
        }
        return value;
    }

    @CanIgnoreReturnValue
    public static long decode(String stringValue) {
        ParseRequest request = ParseRequest.fromString(stringValue);
        try {
            return UnsignedLongs.parseUnsignedLong(request.rawValue, request.radix);
        } catch (NumberFormatException e) {
            NumberFormatException decodeException = new NumberFormatException("Error parsing value: " + stringValue);
            decodeException.initCause(e);
            throw decodeException;
        }
    }

    public static String toString(long x) {
        return UnsignedLongs.toString(x, 10);
    }

    public static String toString(long x, int radix) {
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
        if (x == 0L) {
            return "0";
        }
        if (x > 0L) {
            return Long.toString(x, radix);
        }
        char[] buf = new char[64];
        int i = buf.length;
        if ((radix & radix - 1) == 0) {
            int shift = Integer.numberOfTrailingZeros(radix);
            int mask = radix - 1;
            do {
                buf[--i] = Character.forDigit((int)x & mask, radix);
            } while ((x >>>= shift) != 0L);
        } else {
            long quotient = (radix & 1) == 0 ? (x >>> 1) / (long)(radix >>> 1) : UnsignedLongs.divide(x, radix);
            long rem = x - quotient * (long)radix;
            buf[--i] = Character.forDigit((int)rem, radix);
            for (x = quotient; x > 0L; x /= (long)radix) {
                buf[--i] = Character.forDigit((int)(x % (long)radix), radix);
            }
        }
        return new String(buf, i, buf.length - i);
    }

    private static final class ParseOverflowDetection {
        static final long[] maxValueDivs = new long[37];
        static final int[] maxValueMods = new int[37];
        static final int[] maxSafeDigits = new int[37];

        private ParseOverflowDetection() {
        }

        static boolean overflowInParse(long current, int digit, int radix) {
            if (current >= 0L) {
                if (current < maxValueDivs[radix]) {
                    return false;
                }
                if (current > maxValueDivs[radix]) {
                    return true;
                }
                return digit > maxValueMods[radix];
            }
            return true;
        }

        static {
            BigInteger overflow = new BigInteger("10000000000000000", 16);
            for (int i = 2; i <= 36; ++i) {
                ParseOverflowDetection.maxValueDivs[i] = UnsignedLongs.divide(-1L, i);
                ParseOverflowDetection.maxValueMods[i] = (int)UnsignedLongs.remainder(-1L, i);
                ParseOverflowDetection.maxSafeDigits[i] = overflow.toString(i).length() - 1;
            }
        }
    }

    static enum LexicographicalComparator implements Comparator<long[]>
    {
        INSTANCE;


        @Override
        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                if (left[i] == right[i]) continue;
                return UnsignedLongs.compare(left[i], right[i]);
            }
            return left.length - right.length;
        }

        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }
    }
}

