/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
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
public final class Floats {
    public static final int BYTES = 4;

    private Floats() {
    }

    public static int hashCode(float value) {
        return Float.valueOf(value).hashCode();
    }

    public static int compare(float a, float b) {
        return Float.compare(a, b);
    }

    public static boolean isFinite(float value) {
        return Float.NEGATIVE_INFINITY < value && value < Float.POSITIVE_INFINITY;
    }

    public static boolean contains(float[] array, float target) {
        for (float value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(float[] array, float target) {
        return Floats.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(float[] array, float target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(float[] array, float[] target) {
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

    public static int lastIndexOf(float[] array, float target) {
        return Floats.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(float[] array, float target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static float min(float ... array) {
        Preconditions.checkArgument(array.length > 0);
        float min = array[0];
        for (int i = 1; i < array.length; ++i) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    public static float max(float ... array) {
        Preconditions.checkArgument(array.length > 0);
        float max = array[0];
        for (int i = 1; i < array.length; ++i) {
            max = Math.max(max, array[i]);
        }
        return max;
    }

    @Beta
    public static float constrainToRange(float value, float min, float max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", (Object)Float.valueOf(min), (Object)Float.valueOf(max));
        return Math.min(Math.max(value, min), max);
    }

    public static float[] concat(float[] ... arrays) {
        int length = 0;
        for (float[] array : arrays) {
            length += array.length;
        }
        float[] result = new float[length];
        int pos = 0;
        for (float[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    @Beta
    public static Converter<String, Float> stringConverter() {
        return FloatConverter.INSTANCE;
    }

    public static float[] ensureCapacity(float[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, float ... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 12);
        builder.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<float[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void sortDescending(float[] array) {
        Preconditions.checkNotNull(array);
        Floats.sortDescending(array, 0, array.length);
    }

    public static void sortDescending(float[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        Floats.reverse(array, fromIndex, toIndex);
    }

    public static void reverse(float[] array) {
        Preconditions.checkNotNull(array);
        Floats.reverse(array, 0, array.length);
    }

    public static void reverse(float[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            float tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static float[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof FloatArrayAsList) {
            return ((FloatArrayAsList)collection).toFloatArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        float[] array = new float[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).floatValue();
        }
        return array;
    }

    public static List<Float> asList(float ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new FloatArrayAsList(backingArray);
    }

    @Beta
    @GwtIncompatible
    public static @Nullable Float tryParse(String string) {
        if (Doubles.FLOATING_POINT_PATTERN.matcher(string).matches()) {
            try {
                return Float.valueOf(Float.parseFloat(string));
            } catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return null;
    }

    @GwtCompatible
    private static class FloatArrayAsList
    extends AbstractList<Float>
    implements RandomAccess,
    Serializable {
        final float[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        FloatArrayAsList(float[] array) {
            this(array, 0, array.length);
        }

        FloatArrayAsList(float[] array, int start, int end) {
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
        public Float get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return Float.valueOf(this.array[this.start + index]);
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Float && Floats.indexOf(this.array, ((Float)target).floatValue(), this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Float && (i = Floats.indexOf(this.array, ((Float)target).floatValue(), this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Float && (i = Floats.lastIndexOf(this.array, ((Float)target).floatValue(), this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Float set(int index, Float element) {
            Preconditions.checkElementIndex(index, this.size());
            float oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element).floatValue();
            return Float.valueOf(oldValue);
        }

        @Override
        public List<Float> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new FloatArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof FloatArrayAsList) {
                FloatArrayAsList that = (FloatArrayAsList)object;
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
                result = 31 * result + Floats.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 12);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        float[] toFloatArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    private static enum LexicographicalComparator implements Comparator<float[]>
    {
        INSTANCE;


        @Override
        public int compare(float[] left, float[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; ++i) {
                int result = Float.compare(left[i], right[i]);
                if (result == 0) continue;
                return result;
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Floats.lexicographicalComparator()";
        }
    }

    private static final class FloatConverter
    extends Converter<String, Float>
    implements Serializable {
        static final FloatConverter INSTANCE = new FloatConverter();
        private static final long serialVersionUID = 1L;

        private FloatConverter() {
        }

        @Override
        protected Float doForward(String value) {
            return Float.valueOf(value);
        }

        @Override
        protected String doBackward(Float value) {
            return value.toString();
        }

        public String toString() {
            return "Floats.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}

