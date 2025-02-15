/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.backoff;

@FunctionalInterface
public interface BackOffExecution {
    public static final long STOP = -1L;

    public long nextBackOff();
}

