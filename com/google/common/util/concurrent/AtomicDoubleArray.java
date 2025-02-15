/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;

@GwtIncompatible
public class AtomicDoubleArray
implements Serializable {
    private static final long serialVersionUID = 0L;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int length) {
        this.longs = new AtomicLongArray(length);
    }

    public AtomicDoubleArray(double[] array) {
        int len = array.length;
        long[] longArray = new long[len];
        for (int i = 0; i < len; ++i) {
            longArray[i] = Double.doubleToRawLongBits(array[i]);
        }
        this.longs = new AtomicLongArray(longArray);
    }

    public final int length() {
        return this.longs.length();
    }

    public final double get(int i) {
        return Double.longBitsToDouble(this.longs.get(i));
    }

    public final void set(int i, double newValue) {
        long next = Double.doubleToRawLongBits(newValue);
        this.longs.set(i, next);
    }

    public final void lazySet(int i, double newValue) {
        long next = Double.doubleToRawLongBits(newValue);
        this.longs.lazySet(i, next);
    }

    public final double getAndSet(int i, double newValue) {
        long next = Double.doubleToRawLongBits(newValue);
        return Double.longBitsToDouble(this.longs.getAndSet(i, next));
    }

    public final boolean compareAndSet(int i, double expect, double update) {
        return this.longs.compareAndSet(i, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final boolean weakCompareAndSet(int i, double expect, double update) {
        return this.longs.weakCompareAndSet(i, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i, double delta) {
        double currentVal;
        double nextVal;
        long next;
        long current;
        while (!this.longs.compareAndSet(i, current = this.longs.get(i), next = Double.doubleToRawLongBits(nextVal = (currentVal = Double.longBitsToDouble(current)) + delta))) {
        }
        return currentVal;
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i, double delta) {
        double currentVal;
        double nextVal;
        long next;
        long current;
        while (!this.longs.compareAndSet(i, current = this.longs.get(i), next = Double.doubleToRawLongBits(nextVal = (currentVal = Double.longBitsToDouble(current)) + delta))) {
        }
        return nextVal;
    }

    public String toString() {
        int iMax = this.length() - 1;
        if (iMax == -1) {
            return "[]";
        }
        StringBuilder b = new StringBuilder(19 * (iMax + 1));
        b.append('[');
        int i = 0;
        while (true) {
            b.append(Double.longBitsToDouble(this.longs.get(i)));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(',').append(' ');
            ++i;
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        int length = this.length();
        s.writeInt(length);
        for (int i = 0; i < length; ++i) {
            s.writeDouble(this.get(i));
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int length = s.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i = 0; i < length; ++i) {
            builder.add(Double.doubleToRawLongBits(s.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }
}

