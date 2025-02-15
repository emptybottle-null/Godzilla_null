/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import org.apache.commons.lang3.concurrent.ConcurrentException;

public interface ConcurrentInitializer<T> {
    public T get() throws ConcurrentException;
}

