/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Bytes {
    private Bytes() {
    }

    public static int hashCode(byte value) {
        return value;
    }

    public static boolean contains(byte[] array, byte target) {
        for (byte value : array) {
            if (value != target) continue;
            return true;
        }
        return false;
    }

    public static int indexOf(byte[] array, byte target) {
        return Bytes.indexOf(array, target, 0, array.length);
    }

    private static int indexOf(byte[] array, byte target, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte[] target) {
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

    public static int lastIndexOf(byte[] array, byte target) {
        return Bytes.lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(byte[] array, byte target, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (array[i] != target) continue;
            return i;
        }
        return -1;
    }

    public static byte[] concat(byte[] ... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public static byte[] ensureCapacity(byte[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList)collection).toByteArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        byte[] array = new byte[len];
        for (int i = 0; i < len; ++i) {
            array[i] = ((Number)Preconditions.checkNotNull(boxedArray[i])).byteValue();
        }
        return array;
    }

    public static List<Byte> asList(byte ... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new ByteArrayAsList(backingArray);
    }

    public static void reverse(byte[] array) {
        Preconditions.checkNotNull(array);
        Bytes.reverse(array, 0, array.length);
    }

    public static void reverse(byte[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; ++i, --j) {
            byte tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    @GwtCompatible
    private static class ByteArrayAsList
    extends AbstractList<Byte>
    implements RandomAccess,
    Serializable {
        final byte[] array;
        final int start;
        final int end;
        private static final long serialVersionUID = 0L;

        ByteArrayAsList(byte[] array) {
            this(array, 0, array.length);
        }

        ByteArrayAsList(byte[] array, int start, int end) {
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
        public Byte get(int index) {
            Preconditions.checkElementIndex(index, this.size());
            return this.array[this.start + index];
        }

        @Override
        public boolean contains(Object target) {
            return target instanceof Byte && Bytes.indexOf(this.array, (Byte)target, this.start, this.end) != -1;
        }

        @Override
        public int indexOf(Object target) {
            int i;
            if (target instanceof Byte && (i = Bytes.indexOf(this.array, (Byte)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object target) {
            int i;
            if (target instanceof Byte && (i = Bytes.lastIndexOf(this.array, (Byte)target, this.start, this.end)) >= 0) {
                return i - this.start;
            }
            return -1;
        }

        @Override
        public Byte set(int index, Byte element) {
            Preconditions.checkElementIndex(index, this.size());
            byte oldValue = this.array[this.start + index];
            this.array[this.start + index] = Preconditions.checkNotNull(element);
            return oldValue;
        }

        @Override
        public List<Byte> subList(int fromIndex, int toIndex) {
            int size = this.size();
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size);
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new ByteArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof ByteArrayAsList) {
                ByteArrayAsList that = (ByteArrayAsList)object;
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
                result = 31 * result + Bytes.hashCode(this.array[i]);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.size() * 5);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; ++i) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}

