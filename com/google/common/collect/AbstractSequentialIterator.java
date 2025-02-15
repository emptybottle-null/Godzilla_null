/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.UnmodifiableIterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class AbstractSequentialIterator<T>
extends UnmodifiableIterator<T> {
    private @Nullable T nextOrNull;

    protected AbstractSequentialIterator(@Nullable T firstOrNull) {
        this.nextOrNull = firstOrNull;
    }

    protected abstract @Nullable T computeNext(T var1);

    @Override
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override
    public final T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            T t = this.nextOrNull;
            return t;
        } finally {
            this.nextOrNull = this.computeNext(this.nextOrNull);
        }
    }
}

