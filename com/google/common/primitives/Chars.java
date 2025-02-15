/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
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
public final class Chars {
    public static final int BYTES = 2;

    private Chars() {
    }

    public static int hashCode(char value) {
        return value;
    }

    public static char checkedCast(long value) {
        char result = (char)value;
        Preconditions.checkArgument((long)result == value, "Out of range: %s", value);
        return result;
    }

    public static char saturatedCast(long value) {
        if (value > 65535L) {
            return '\uffff';
        }
        if (value < 0L) {
            return '\u0000';
        }
        return (char)value;
    }

    public static int compare(char a, char b) {
        return a - b;
    }

    public static boolean contains(char[] array, char target) {
        for (char value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(char[] array, char target) {
        return Chars.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(char[] array, char target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(char[] array, char[] target) {
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

    public static int lastIndexOf(char[] array, char target) {
        return Chars.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(char[] array, char target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static char min(char ... array) {
        Preconditions.checkArgument(array.length > 0);
        char min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] >= min) continue;
            min = array[i];
        }
        return min;
    }

    public static char max(char ... array) {
        Preconditions.checkArgument(array.length > 0);
        char max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] <= max) continue;
            max = array[i];
        }
        return max;
    }

    @Beta
    public static char constrainToRange(char value, char min, char max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
        return value < min ? min : (value < max ? value : max);
    }

    public static char[] concat(char[] ... arrays) {
        int length = 0;
        for (char[] array : arrays) {
            length += array.length;
        }
        char[] result = new char[length];
        int pos = 0;
        for (char[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char value) {
        return new byte[]{(byte)(value >> 8), (byte)value};
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 2, "array too small: %s < %s", bytes.length, 2);
        return Chars.fromBytes(bytes[0], bytes[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b1, byte b2) {
        return (char)(b1 << 8 | b2 & 0xFF);
    }

    public static char[] ensureCapacity(char[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, char ... array) {
        Preconditions.checkNotNull(separator);
        int len = array.length;
        if (len == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(len + separator.length() * (len - 1));
        builder.append(array[0]);
        for (int i = 1; i < len; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList)collection).toCharArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        char[] array = new char[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Character)Preconditions.checkNotNull(boxedArray[i])).charValue();
        }
        return array;
    }

    public static void sortDescending(char[] array) {
        Preconditions.checkNotNull(array);
        Chars.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(char[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Chars.reverse(array, fromIndex, toIndex);
    }

    public static void reverse(char[] array) {
        Preconditions.checkNotNull(array);
        Chars.reverse(array, 0, array.length);
    }

    public static void reverse(char[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static List<Character> asList(char ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new CharArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class CharArrayAsList
    extends AbstractList<Character>
    implements RandomAccess,
    Serializable {
        final char[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        CharArrayAsList(char[] array) {
            this(array, 0, array.length);
        }

        CharArrayAsList(char[] array, int start, int end) {
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
        public Character get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return Character.valueOf(this.array[this.start + index]);
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Character && Chars.indexOf(this.array, ((Character)target).charValue(), this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Character && (i = Chars.indexOf(this.array, ((Character)target).charValue(), this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Character && (i = Chars.lastIndexOf(this.array, ((Character)target).charValue(), this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Character set(int index, Character element) {
            Preconditions.checkElementIndex(index, this.size());
            char oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element).charValue();
            return Character.valueOf(oldValue);
        }

        @Override
        public List<Character> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new CharArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof CharArrayAsList) {
                CharArrayAsList that = (CharArrayAsList)object;
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
                result = 31 * result + Chars.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 3);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        char[] toCharArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    private static enum LexicographicalComparator implements Comparator<char[]>
    {
        INSTANCE;


        @Override
        public int compare(char[] left, char[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result = Chars.compare(left[i], right[i]);
                if (result == 0) continue;
                return result;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Chars.lexicographicalComparator()";
        }
    }
}

