/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.LongAddable;
import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@GwtCompatible(emulated=true)
final class LongAdder
extends Striped64
implements Serializable,
LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    @Override
    final long fn(long v, long x) {
        return v + x;
    }

    @Override
    public void add(long x) {
        long b;
        Striped64.Cell[] as = this.cells;
        if (this.cells != null || !this.casBase(b = this.base, b + x)) {
            long v;
            Striped64.Cell a;
            int n;
            boolean uncontended = true;
            int[] hc = (int[])threadHashCode.get();
            if (hc == null || as == null || (n = as.length) < 1 || (a = as[n - 1 & hc[0]]) == null || !(uncontended = a.cas(v = a.value, v + x))) {
                this.retryUpdate(x, hc, uncontended);
            }
        }
    }

    @Override
    public void increment() {
        this.add(1L);
    }

    public void decrement() {
        this.add(-1L);
    }

    @Override
    public long sum() {
        long sum = this.base;
        Striped64.Cell[] as = this.cells;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a == null) continue;
                sum += a.value;
            }
        }
        return sum;
    }

    public void reset() {
        this.internalReset(0L);
    }

    public long sumThenReset() {
        long sum = this.base;
        Striped64.Cell[] as = this.cells;
        this.base = 0L;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a == null) continue;
                sum += a.value;
                a.value = 0L;
            }
        }
        return sum;
    }

    public String toString() {
        return Long.toString(this.sum());
    }

    @Override
    public long longValue() {
        return this.sum();
    }

    @Override
    public int intValue() {
        return (int)this.sum();
    }

    @Override
    public float floatValue() {
        return this.sum();
    }

    @Override
    public double doubleValue() {
        return this.sum();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeLong(this.sum());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = s.readLong();
    }
}

