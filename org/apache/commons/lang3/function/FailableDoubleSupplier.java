/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleSupplier<E extends Throwable> {
    public double getAsDouble() throws E;
}

