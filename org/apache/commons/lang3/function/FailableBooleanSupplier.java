/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableBooleanSupplier<E extends Throwable> {
    public boolean getAsBoolean() throws E;
}

