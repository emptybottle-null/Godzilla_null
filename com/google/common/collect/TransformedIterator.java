/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

@GwtCompatible
abstract class TransformedIterator<F, T>
implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    TransformedIterator(Iterator<? extends F> backingIterator) {
        this.backingIterator = Preconditions.checkNotNull(backingIterator);
    }

    abstract T transform(F var1);

    @Override
    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    @Override
    public final T next() {
        return this.transform(this.backingIterator.next());
    }

    @Override
    public final void remove() {
        this.backingIterator.remove();
    }
}

