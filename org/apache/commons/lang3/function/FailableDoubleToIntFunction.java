/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableDoubleToIntFunction<E extends Throwable> {
    public static final FailableDoubleToIntFunction NOP = t -> 0;

    public static <E extends Throwable> FailableDoubleToIntFunction<E> nop() {
        return NOP;
    }

    public int applyAsInt(double var1) throws E;
}

