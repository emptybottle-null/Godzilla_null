/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

abstract class ForwardingCondition
implements Condition {
    ForwardingCondition() {
    }

    abstract Condition delegate();

    @Override
    public void await() throws InterruptedException {
        this.delegate().await();
    }

    @Override
    public boolean await(long time, TimeUnit unit) throws InterruptedException {
        return this.delegate().await(time, unit);
    }

    @Override
    public void awaitUninterruptibly() {
        this.delegate().awaitUninterruptibly();
    }

    @Override
    public long awaitNanos(long nanosTimeout) throws InterruptedException {
        return this.delegate().awaitNanos(nanosTimeout);
    }

    @Override
    public boolean awaitUntil(Date deadline) throws InterruptedException {
        return this.delegate().awaitUntil(deadline);
    }

    @Override
    public void signal() {
        this.delegate().signal();
    }

    @Override
    public void signalAll() {
        this.delegate().signalAll();
    }
}

