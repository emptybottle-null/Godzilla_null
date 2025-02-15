/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleBinaryOperator<E extends Throwable> {
    public double applyAsDouble(double var1, double var3) throws E;
}

