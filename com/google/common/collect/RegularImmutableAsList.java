/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableAsList;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import java.util.function.Consumer;

@GwtCompatible(emulated=true)
class RegularImmutableAsList<E>
extends ImmutableAsList<E> {
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    RegularImmutableAsList(ImmutableCollection<E> delegate, ImmutableList<? extends E> delegateList) {
        this.delegate = delegate;
        this.delegateList = delegateList;
    }

    RegularImmutableAsList(ImmutableCollection<E> delegate, Object[] array) {
        this(delegate, ImmutableList.asImmutableList(array));
    }

    @Override
    ImmutableCollection<E> delegateCollection() {
        return this.delegate;
    }

    ImmutableList<? extends E> delegateList() {
        return this.delegateList;
    }

    @Override
    public UnmodifiableListIterator<E> listIterator(int index) {
        return this.delegateList.listIterator(index);
    }

    @Override
    @GwtIncompatible
    public void forEach(Consumer<? super E> action) {
        this.delegateList.forEach(action);
    }

    @Override
    @GwtIncompatible
    int copyIntoArray(Object[] dst, int offset) {
        return this.delegateList.copyIntoArray(dst, offset);
    }

    @Override
    Object[] internalArray() {
        return this.delegateList.internalArray();
    }

    @Override
    int internalArrayStart() {
        return this.delegateList.internalArrayStart();
    }

    @Override
    int internalArrayEnd() {
        return this.delegateList.internalArrayEnd();
    }

    @Override
    public E get(int index) {
        return this.delegateList.get(index);
    }
}

