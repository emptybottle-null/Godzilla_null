/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.util;

import java.io.Serializable;
import java.util.Arrays;

public class DynamicIntArray
implements Serializable {
    private int[] data;
    private int size;

    public DynamicIntArray() {
        this(10);
    }

    public DynamicIntArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initialCapacity: " + initialCapacity);
        }
        this.data = new int[initialCapacity];
        this.size = 0;
    }

    public DynamicIntArray(int[] intArray) {
        this.size = intArray.length;
        int capacity = (int)Math.min((long)this.size * 110L / 100L, Integer.MAX_VALUE);
        this.data = new int[capacity];
        System.arraycopy(intArray, 0, this.data, 0, this.size);
    }

    public void add(int value) {
        this.ensureCapacity(this.size + 1);
        this.data[this.size++] = value;
    }

    public void add(int index, int[] intArray) {
        if (index > this.size) {
            this.throwException2(index);
        }
        int addCount = intArray.length;
        this.ensureCapacity(this.size + addCount);
        int moveCount = this.size - index;
        if (moveCount > 0) {
            System.arraycopy(this.data, index, this.data, index + addCount, moveCount);
        }
        System.arraycopy(intArray, 0, this.data, index, addCount);
        this.size += addCount;
    }

    public void add(int index, int value) {
        if (index > this.size) {
            this.throwException2(index);
        }
        this.ensureCapacity(this.size + 1);
        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = value;
        ++this.size;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(int integer) {
        for (int i = 0; i < this.size; ++i) {
            if (this.data[i] != integer) continue;
            return true;
        }
        return false;
    }

    public void decrement(int from, int to) {
        int i = from;
        while (i < to) {
            int n = i++;
            this.data[n] = this.data[n] - 1;
        }
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = this.data.length;
        if (minCapacity > oldCapacity) {
            int[] oldData = this.data;
            int newCapacity = oldCapacity * 3 / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            this.data = new int[newCapacity];
            System.arraycopy(oldData, 0, this.data, 0, this.size);
        }
    }

    public void fill(int value) {
        Arrays.fill(this.data, value);
    }

    public int get(int index) {
        if (index >= this.size) {
            this.throwException(index);
        }
        return this.data[index];
    }

    public int getUnsafe(int index) {
        return this.data[index];
    }

    public int getSize() {
        return this.size;
    }

    public void increment(int from, int to) {
        int i = from;
        while (i < to) {
            int n = i++;
            this.data[n] = this.data[n] + 1;
        }
    }

    public void insertRange(int offs, int count, int value) {
        if (offs > this.size) {
            this.throwException2(offs);
        }
        this.ensureCapacity(this.size + count);
        System.arraycopy(this.data, offs, this.data, offs + count, this.size - offs);
        if (value != 0) {
            Arrays.fill(this.data, offs, offs + count, value);
        }
        this.size += count;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void remove(int index) {
        int toMove;
        if (index >= this.size) {
            this.throwException(index);
        }
        if ((toMove = this.size - index - 1) > 0) {
            System.arraycopy(this.data, index + 1, this.data, index, toMove);
        }
        --this.size;
    }

    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex >= this.size || toIndex > this.size) {
            this.throwException3(fromIndex, toIndex);
        }
        int moveCount = this.size - toIndex;
        System.arraycopy(this.data, toIndex, this.data, fromIndex, moveCount);
        this.size -= toIndex - fromIndex;
    }

    public void set(int index, int value) {
        if (index >= this.size) {
            this.throwException(index);
        }
        this.data[index] = value;
    }

    public void setUnsafe(int index, int value) {
        this.data[index] = value;
    }

    private void throwException(int index) {
        throw new IndexOutOfBoundsException("Index " + index + " not in valid range [0-" + (this.size - 1) + "]");
    }

    private void throwException2(int index) {
        throw new IndexOutOfBoundsException("Index " + index + ", not in range [0-" + this.size + "]");
    }

    private void throwException3(int fromIndex, int toIndex) {
        throw new IndexOutOfBoundsException("Index range [" + fromIndex + ", " + toIndex + "] not in valid range [0-" + (this.size - 1) + "]");
    }
}

