/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface ToBooleanBiFunction<T, U> {
    public boolean applyAsBoolean(T var1, U var2);
}

