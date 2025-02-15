/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class ForwardingMultiset<E>
extends ForwardingCollection<E>
implements Multiset<E> {
    protected ForwardingMultiset() {
    }

    @Override
    protected abstract Multiset<E> delegate();

    @Override
    public int count(Object element) {
        return this.delegate().count(element);
    }

    @Override
    @CanIgnoreReturnValue
    public int add(E element, int occurrences) {
        return this.delegate().add(element, occurrences);
    }

    @Override
    @CanIgnoreReturnValue
    public int remove(Object element, int occurrences) {
        return this.delegate().remove(element, occurrences);
    }

    @Override
    public Set<E> elementSet() {
        return this.delegate().elementSet();
    }

    @Override
    public Set<Multiset.Entry<E>> entrySet() {
        return this.delegate().entrySet();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return object == this || this.delegate().equals(object);
    }

    @Override
    public int hashCode() {
        return this.delegate().hashCode();
    }

    @Override
    @CanIgnoreReturnValue
    public int setCount(E element, int count) {
        return this.delegate().setCount(element, count);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean setCount(E element, int oldCount, int newCount) {
        return this.delegate().setCount(element, oldCount, newCount);
    }

    @Override
    protected boolean standardContains(@Nullable Object object) {
        return this.count(object) > 0;
    }

    @Override
    protected void standardClear() {
        Iterators.clear(this.entrySet().iterator());
    }

    @Beta
    protected int standardCount(@Nullable Object object) {
        for (Multiset.Entry<E> entry : this.entrySet()) {
            if (!Objects.equal(entry.getElement(), object)) continue;
            return entry.getCount();
        }
        return 0;
    }

    protected boolean standardAdd(E element) {
        this.add(element, 1);
        return true;
    }

    @Override
    @Beta
    protected boolean standardAddAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override
    protected boolean standardRemove(Object element) {
        return this.remove(element, 1) > 0;
    }

    @Override
    protected boolean standardRemoveAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override
    protected boolean standardRetainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    protected int standardSetCount(E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    protected boolean standardSetCount(E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }

    protected Iterator<E> standardIterator() {
        return Multisets.iteratorImpl(this);
    }

    protected int standardSize() {
        return Multisets.linearTimeSizeImpl(this);
    }

    protected boolean standardEquals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    protected int standardHashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    protected String standardToString() {
        return this.entrySet().toString();
    }

    @Beta
    protected class StandardElementSet
    extends Multisets.ElementSet<E> {
        @Override
        Multiset<E> multiset() {
            return ForwardingMultiset.this;
        }

        @Override
        public Iterator<E> iterator() {
            return Multisets.elementIterator(this.multiset().entrySet().iterator());
        }
    }
}

