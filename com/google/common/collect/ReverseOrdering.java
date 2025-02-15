/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
final class ReverseOrdering<T>
extends Ordering<T>
implements Serializable {
    final Ordering<? super T> forwardOrder;
    private static final long serialVersionUID = 0L;

    ReverseOrdering(Ordering<? super T> forwardOrder) {
        this.forwardOrder = Preconditions.checkNotNull(forwardOrder);
    }

    @Override
    public int compare(T a, T b) {
        return this.forwardOrder.compare(b, a);
    }

    @Override
    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    @Override
    public <E extends T> E min(E a, E b) {
        return this.forwardOrder.max(a, b);
    }

    @Override
    public <E extends T> E min(E a, E b, E c, E ... rest) {
        return this.forwardOrder.max(a, b, c, rest);
    }

    @Override
    public <E extends T> E min(Iterator<E> iterator) {
        return this.forwardOrder.max(iterator);
    }

    @Override
    public <E extends T> E min(Iterable<E> iterable) {
        return this.forwardOrder.max(iterable);
    }

    @Override
    public <E extends T> E max(E a, E b) {
        return this.forwardOrder.min(a, b);
    }

    @Override
    public <E extends T> E max(E a, E b, E c, E ... rest) {
        return this.forwardOrder.min(a, b, c, rest);
    }

    @Override
    public <E extends T> E max(Iterator<E> iterator) {
        return this.forwardOrder.min(iterator);
    }

    @Override
    public <E extends T> E max(Iterable<E> iterable) {
        return this.forwardOrder.min(iterable);
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ReverseOrdering) {
            ReverseOrdering that = (ReverseOrdering)object;
            return this.forwardOrder.equals(that.forwardOrder);
        }
        return false;
    }

    public String toString() {
        return this.forwardOrder + ".reverse()";
    }
}

