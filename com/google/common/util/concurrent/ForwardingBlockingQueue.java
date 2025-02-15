/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@CanIgnoreReturnValue
@GwtIncompatible
public abstract class ForwardingBlockingQueue<E>
extends ForwardingQueue<E>
implements BlockingQueue<E> {
    protected ForwardingBlockingQueue() {
    }

    @Override
    protected abstract BlockingQueue<E> delegate();

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return this.delegate().drainTo(c, maxElements);
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return this.delegate().drainTo(c);
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate().offer(e, timeout, unit);
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate().poll(timeout, unit);
    }

    @Override
    public void put(E e) throws InterruptedException {
        this.delegate().put(e);
    }

    @Override
    public int remainingCapacity() {
        return this.delegate().remainingCapacity();
    }

    @Override
    public E take() throws InterruptedException {
        return this.delegate().take();
    }
}

