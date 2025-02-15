/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.backoff;

import org.springframework.util.backoff.BackOffExecution;

@FunctionalInterface
public interface BackOff {
    public BackOffExecution start();
}

