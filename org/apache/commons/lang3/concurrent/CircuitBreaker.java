/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

public interface CircuitBreaker<T> {
    public boolean isOpen();

    public boolean isClosed();

    public boolean checkState();

    public void close();

    public void open();

    public boolean incrementAndCheckState(T var1);
}

