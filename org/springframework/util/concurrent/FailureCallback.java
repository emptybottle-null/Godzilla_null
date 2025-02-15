/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

@FunctionalInterface
public interface FailureCallback {
    public void onFailure(Throwable var1);
}

