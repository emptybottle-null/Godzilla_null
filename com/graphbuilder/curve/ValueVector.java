/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

public class ValueVector {
    protected int size = 0;
    protected double[] value = null;

    public ValueVector() {
        this.value = new double[2];
    }

    public ValueVector(double[] value, int size) {
        if (value == null) {
            throw new IllegalArgumentException("value array cannot be null.");
        }
        if (size < 0 || size > value.length) {
            throw new IllegalArgumentException("size >= 0 && size <= value.length required");
        }
        this.value = value;
        this.size = size;
    }

    public ValueVector(int initialCapacity) {
        this.value = new double[initialCapacity];
    }

    public int size() {
        return this.size;
    }

    public double get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("required: (index >= 0 && index < size) but: (index = " + index + ", size = " + this.size + ")");
        }
        return this.value[index];
    }

    public void set(double d, int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("required: (index >= 0 && index < size) but: (index = " + index + ", size = " + this.size + ")");
        }
        this.value[index] = d;
    }

    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("required: (index >= 0 && index < size) but: (index = " + index + ", size = " + this.size + ")");
        }
        for (int i = index + 1; i < this.size; ++i) {
            this.value[i - 1] = this.value[i];
        }
        --this.size;
    }

    public void add(double d) {
        this.insert(d, this.size);
    }

    public void insert(double d, int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("required: (index >= 0 && index <= size) but: (index = " + index + ", size = " + this.size + ")");
        }
        this.ensureCapacity(this.size + 1);
        for (int i = this.size; i > index; --i) {
            this.value[i] = this.value[i - 1];
        }
        this.value[index] = d;
        ++this.size;
    }

    public void ensureCapacity(int capacity) {
        if (this.value.length < capacity) {
            int x = 2 * this.value.length;
            if (x < capacity) {
                x = capacity;
            }
            double[] arr = new double[x];
            for (int i = 0; i < this.size; ++i) {
                arr[i] = this.value[i];
            }
            this.value = arr;
        }
    }

    public void trimArray() {
        if (this.size < this.value.length) {
            double[] arr = new double[this.size];
            for (int i = 0; i < this.size; ++i) {
                arr[i] = this.value[i];
            }
            this.value = arr;
        }
    }
}

