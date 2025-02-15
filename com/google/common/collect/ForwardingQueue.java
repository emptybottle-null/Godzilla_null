/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ForwardingCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import java.util.Queue;

@GwtCompatible
public abstract class ForwardingQueue<E>
extends ForwardingCollection<E>
implements Queue<E> {
    protected ForwardingQueue() {
    }

    @Override
    protected abstract Queue<E> delegate();

    @Override
    @CanIgnoreReturnValue
    public boolean offer(E o) {
        return this.delegate().offer(o);
    }

    @Override
    @CanIgnoreReturnValue
    public E poll() {
        return this.delegate().poll();
    }

    @Override
    @CanIgnoreReturnValue
    public E remove() {
        return this.delegate().remove();
    }

    @Override
    public E peek() {
        return this.delegate().peek();
    }

    @Override
    public E element() {
        return this.delegate().element();
    }

    protected boolean standardOffer(E e) {
        try {
            return this.add(e);
        } catch (IllegalStateException caught) {
            return false;
        }
    }

    protected E standardPeek() {
        try {
            return this.element();
        } catch (NoSuchElementException caught) {
            return null;
        }
    }

    protected E standardPoll() {
        try {
            return this.remove();
        } catch (NoSuchElementException caught) {
            return null;
        }
    }
}

