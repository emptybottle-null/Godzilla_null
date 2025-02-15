/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.ReflectionSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

@GwtIncompatible
@ReflectionSupport(value=ReflectionSupport.Level.FULL)
public class AtomicDouble
extends Number
implements Serializable {
    private static final long serialVersionUID = 0L;
    private volatile transient long value;
    private static final AtomicLongFieldUpdater<AtomicDouble> updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble.class, "value");

    public AtomicDouble(double initialValue) {
        this.value = Double.doubleToRawLongBits(initialValue);
    }

    public AtomicDouble() {
    }

    public final double get() {
        return Double.longBitsToDouble(this.value);
    }

    public final void set(double newValue) {
        long next;
        this.value = next = Double.doubleToRawLongBits(newValue);
    }

    public final void lazySet(double newValue) {
        long next = Double.doubleToRawLongBits(newValue);
        updater.lazySet(this, next);
    }

    public final double getAndSet(double newValue) {
        long next = Double.doubleToRawLongBits(newValue);
        return Double.longBitsToDouble(updater.getAndSet(this, next));
    }

    public final boolean compareAndSet(double expect, double update) {
        return updater.compareAndSet(this, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final boolean weakCompareAndSet(double expect, double update) {
        return updater.weakCompareAndSet(this, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double delta) {
        double currentVal;
        double nextVal;
        long next;
        long current;
        while (!updater.compareAndSet(this, current = this.value, next = Double.doubleToRawLongBits(nextVal = (currentVal = Double.longBitsToDouble(current)) + delta))) {
        }
        return currentVal;
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double delta) {
        double currentVal;
        double nextVal;
        long next;
        long current;
        while (!updater.compareAndSet(this, current = this.value, next = Double.doubleToRawLongBits(nextVal = (currentVal = Double.longBitsToDouble(current)) + delta))) {
        }
        return nextVal;
    }

    public String toString() {
        return Double.toString(this.get());
    }

    @Override
    public int intValue() {
        return (int)this.get();
    }

    @Override
    public long longValue() {
        return (long)this.get();
    }

    @Override
    public float floatValue() {
        return (float)this.get();
    }

    @Override
    public double doubleValue() {
        return this.get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeDouble(this.get());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.set(s.readDouble());
    }
}

