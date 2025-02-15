/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

@GwtCompatible
public interface PeekingIterator<E>
extends Iterator<E> {
    public E peek();

    @Override
    @CanIgnoreReturnValue
    public E next();

    @Override
    public void remove();
}

