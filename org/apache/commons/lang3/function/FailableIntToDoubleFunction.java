/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntToDoubleFunction<E extends Throwable> {
    public static final FailableIntToDoubleFunction NOP = t -> 0.0;

    public static <E extends Throwable> FailableIntToDoubleFunction<E> nop() {
        return NOP;
    }

    public double applyAsDouble(int var1) throws E;
}

