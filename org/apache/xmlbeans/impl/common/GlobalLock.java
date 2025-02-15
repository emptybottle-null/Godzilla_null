/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.impl.common.Mutex;

public class GlobalLock {
    private static final Mutex GLOBAL_MUTEX = new Mutex();

    public static void acquire() throws InterruptedException {
        GLOBAL_MUTEX.acquire();
    }

    public static void tryToAcquire() {
        GLOBAL_MUTEX.tryToAcquire();
    }

    public static void release() {
        GLOBAL_MUTEX.release();
    }
}

