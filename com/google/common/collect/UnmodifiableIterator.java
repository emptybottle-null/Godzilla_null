/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract class UnmodifiableIterator<E>
implements Iterator<E> {
    protected UnmodifiableIterator() {
    }

    @Override
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

