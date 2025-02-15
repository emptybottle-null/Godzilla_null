/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableObjIntConsumer<T, E extends Throwable> {
    public static final FailableObjIntConsumer NOP = (t, u) -> {};

    public static <T, E extends Throwable> FailableObjIntConsumer<T, E> nop() {
        return NOP;
    }

    public void accept(T var1, int var2) throws E;
}

