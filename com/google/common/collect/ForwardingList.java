/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class ForwardingList<E>
extends ForwardingCollection<E>
implements List<E> {
    protected ForwardingList() {
    }

    @Override
    protected abstract List<E> delegate();

    @Override
    public void add(int index, E element) {
        this.delegate().add(index, element);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(int index, Collection<? extends E> elements) {
        return this.delegate().addAll(index, elements);
    }

    @Override
    public E get(int index) {
        return this.delegate().get(index);
    }

    @Override
    public int indexOf(Object element) {
        return this.delegate().indexOf(element);
    }

    @Override
    public int lastIndexOf(Object element) {
        return this.delegate().lastIndexOf(element);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.delegate().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return this.delegate().listIterator(index);
    }

    @Override
    @CanIgnoreReturnValue
    public E remove(int index) {
        return this.delegate().remove(index);
    }

    @Override
    @CanIgnoreReturnValue
    public E set(int index, E element) {
        return this.delegate().set(index, element);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return this.delegate().subList(fromIndex, toIndex);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object == this || this.delegate().equals(object);
    }

    @Override
    public int hashCode() {
        return this.delegate().hashCode();
    }

    protected boolean standardAdd(E element) {
        this.add(this.size(), element);
        return true;
    }

    protected boolean standardAddAll(int index, Iterable<? extends E> elements) {
        return Lists.addAllImpl(this, index, elements);
    }

    protected int standardIndexOf(@Nullable Object element) {
        return Lists.indexOfImpl(this, element);
    }

    protected int standardLastIndexOf(@Nullable Object element) {
        return Lists.lastIndexOfImpl(this, element);
    }

    protected Iterator<E> standardIterator() {
        return this.listIterator();
    }

    protected ListIterator<E> standardListIterator() {
        return this.listIterator(0);
    }

    @Beta
    protected ListIterator<E> standardListIterator(int start) {
        return Lists.listIteratorImpl(this, start);
    }

    @Beta
    protected List<E> standardSubList(int fromIndex, int toIndex) {
        return Lists.subListImpl(this, fromIndex, toIndex);
    }

    @Beta
    protected boolean standardEquals(@Nullable Object object) {
        return Lists.equalsImpl(this, object);
    }

    @Beta
    protected int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}

