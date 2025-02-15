/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import org.springframework.lang.Nullable;

@FunctionalInterface
public interface SuccessCallback<T> {
    public void onSuccess(@Nullable T var1);
}

