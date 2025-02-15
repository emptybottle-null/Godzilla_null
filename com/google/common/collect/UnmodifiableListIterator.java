/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ListIterator;

@GwtCompatible
public abstract class UnmodifiableListIterator<E>
extends UnmodifiableIterator<E>
implements ListIterator<E> {
    protected UnmodifiableListIterator() {
    }

    @Override
    @Deprecated
    public final void add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void set(E e) {
        throw new UnsupportedOperationException();
    }
}

