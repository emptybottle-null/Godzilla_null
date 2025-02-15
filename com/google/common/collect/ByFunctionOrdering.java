/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
final class ByFunctionOrdering<F, T>
extends Ordering<F>
implements Serializable {
    final Function<F, ? extends T> function;
    final Ordering<T> ordering;
    private static final long serialVersionUID = 0L;

    ByFunctionOrdering(Function<F, ? extends T> function, Ordering<T> ordering) {
        this.function = Preconditions.checkNotNull(function);
        this.ordering = Preconditions.checkNotNull(ordering);
    }

    @Override
    public int compare(F left, F right) {
        return this.ordering.compare(this.function.apply(left), this.function.apply(right));
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ByFunctionOrdering) {
            ByFunctionOrdering that = (ByFunctionOrdering)object;
            return this.function.equals(that.function) && this.ordering.equals(that.ordering);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.function, this.ordering);
    }

    public String toString() {
        return this.ordering + ".onResultOf(" + this.function + ")";
    }
}

