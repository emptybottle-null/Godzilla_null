/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtCompatible(serializable=true, emulated=true)
final class SingletonImmutableSet<E>
extends ImmutableSet<E> {
    final transient E element;
    @LazyInit
    private transient int cachedHashCode;

    SingletonImmutableSet(E element) {
        this.element = Preconditions.checkNotNull(element);
    }

    SingletonImmutableSet(E element, int hashCode) {
        this.element = element;
        this.cachedHashCode = hashCode;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean contains(Object target) {
        return this.element.equals(target);
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    @Override
    ImmutableList<E> createAsList() {
        return ImmutableList.of(this.element);
    }

    @Override
    boolean isPartialView() {
        return false;
    }

    @Override
    int copyIntoArray(Object[] dst, int offset) {
        dst[offset] = this.element;
        return offset + 1;
    }

    @Override
    public final int hashCode() {
        int code = this.cachedHashCode;
        if (code == 0) {
            this.cachedHashCode = code = this.element.hashCode();
        }
        return code;
    }

    @Override
    boolean isHashCodeFast() {
        return this.cachedHashCode != 0;
    }

    @Override
    public String toString() {
        return '[' + this.element.toString() + ']';
    }
}

