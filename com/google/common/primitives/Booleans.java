/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
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

@GwtCompatible
public final class Booleans {
    private Booleans() {
    }

    @Beta
    public static Comparator<Boolean> trueFirst() {
        return BooleanComparator.TRUE_FIRST;
    }

    @Beta
    public static Comparator<Boolean> falseFirst() {
        return BooleanComparator.FALSE_FIRST;
    }

    public static int hashCode(boolean value) {
        return value ? 1231 : 1237;
    }

    public static int compare(boolean a, boolean b) {
        return a == b ? 0 : (a ? 1 : -1);
    }

    public static boolean contains(boolean[] array, boolean target) {
        for (boolean value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(boolean[] array, boolean target) {
        return Booleans.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(boolean[] array, boolean target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(boolean[] array, boolean[] target) {
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

    public static int lastIndexOf(boolean[] array, boolean target) {
        return Booleans.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(boolean[] array, boolean target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static boolean[] concat(boolean[] ... arrays) {
        int length = 0;
        for (boolean[] array : arrays) {
            length += array.length;
        }
        boolean[] result = new boolean[length];
        int pos = 0;
        for (boolean[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public static boolean[] ensureCapacity(boolean[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, boolean ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 7);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<boolean[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static boolean[] toArray(Collection<Boolean> collection) {
        if (collection instanceof BooleanArrayAsList) {
            return ((BooleanArrayAsList)collection).toBooleanArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        boolean[] array = new boolean[len];
        for (int i = 0; i < len; ++i) {
            array[i] = (Boolean)Preconditions.checkNotNull(boxedArray[i]);
        }
        return array;
    }

    public static List<Boolean> asList(boolean ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new BooleanArrayAsList(backingArray);
    }

    @Beta
    public static int countTrue(boolean ... values) {
        int count = 0;
        for (boolean value : values) {
            if (!value) continue;
            ++count;
        }
        return count;
    }

    public static void reverse(boolean[] array) {
        Preconditions.checkNotNull(array);
        Booleans.reverse(array, 0, array.length);
    }

    public static void reverse(boolean[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            boolean tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    @GwtCompatible
    private static class BooleanArrayAsList
    extends AbstractList<Boolean>
    implements RandomAccess,
    Serializable {
        final boolean[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        BooleanArrayAsList(boolean[] array) {
            this(array, 0, array.length);
        }

        BooleanArrayAsList(boolean[] array, int start, int end) {
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
        public Boolean get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return this.array[this.start + index];
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Boolean && Booleans.indexOf(this.array, (Boolean)target, this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Boolean && (i = Booleans.indexOf(this.array, (Boolean)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Boolean && (i = Booleans.lastIndexOf(this.array, (Boolean)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Boolean set(int index, Boolean element) {
            Preconditions.checkElementIndex(index, this.size());
            boolean oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element);
            return oldValue;
        }

        @Override
        public List<Boolean> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new BooleanArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof BooleanArrayAsList) {
                BooleanArrayAsList that = (BooleanArrayAsList)object;
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
                result = 31 * result + Booleans.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 7);
            builder.append(this.array[this.start] ? "[true" : "[false");
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(this.array[i] ? ", true" : ", false");
            }
            return builder.append(']').toString();
        }

        boolean[] toBooleanArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    private static enum LexicographicalComparator implements Comparator<boolean[]>
    {
        INSTANCE;


        @Override
        public int compare(boolean[] left, boolean[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result = Booleans.compare(left[i], right[i]);
                if (result == 0) continue;
                return result;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Booleans.lexicographicalComparator()";
        }
    }

    private static enum BooleanComparator implements Comparator<Boolean>
    {
        TRUE_FIRST(1, "Booleans.trueFirst()"),
        FALSE_FIRST(-1, "Booleans.falseFirst()");

        private final int trueValue;
        private final String toString;

        private BooleanComparator(int trueValue, String toString) {
            this.trueValue = trueValue;
            this.toString = toString;
        }

        @Override
        public int compare(Boolean a, Boolean b) {
            int aVal = a != false ? this.trueValue : 0;
            int bVal = b != false ? this.trueValue : 0;
            return bVal - aVal;
        }

        public String toString() {
            return this.toString;
        }
    }
}

