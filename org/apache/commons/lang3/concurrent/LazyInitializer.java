/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;

public abstract class LazyInitializer<T>
implements ConcurrentInitializer<T> {
    private static final Object NO_INIT = new Object();
    private volatile T object = NO_INIT;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public T get() throws ConcurrentException {
        T result = this.object;
        if (result == NO_INIT) {
            LazyInitializer lazyInitializer = this;
            synchronized (lazyInitializer) {
                result = this.object;
                if (result == NO_INIT) {
                    this.object = result = this.initialize();
                }
            }
        }
        return result;
    }

    protected abstract T initialize() throws ConcurrentException;
}

