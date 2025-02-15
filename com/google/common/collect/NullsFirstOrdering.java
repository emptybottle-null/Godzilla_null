/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
final class NullsFirstOrdering<T>
extends Ordering<T>
implements Serializable {
    final Ordering<? super T> ordering;
    private static final long serialVersionUID = 0L;

    NullsFirstOrdering(Ordering<? super T> ordering) {
        this.ordering = ordering;
    }

    @Override
    public int compare(@Nullable T left, @Nullable T right) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        return this.ordering.compare(left, right);
    }

    @Override
    public <S extends T> Ordering<S> reverse() {
        return this.ordering.reverse().nullsLast();
    }

    @Override
    public <S extends T> Ordering<S> nullsFirst() {
        return this;
    }

    @Override
    public <S extends T> Ordering<S> nullsLast() {
        return this.ordering.nullsLast();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof NullsFirstOrdering) {
            NullsFirstOrdering that = (NullsFirstOrdering)object;
            return this.ordering.equals(that.ordering);
        }
        return false;
    }

    public int hashCode() {
        return this.ordering.hashCode() ^ 0x39153A74;
    }

    public String toString() {
        return this.ordering + ".nullsFirst()";
    }
}

