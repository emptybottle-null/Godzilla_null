/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableIntConsumer<E extends Throwable> {
    public static final FailableIntConsumer NOP = t -> {};

    public static <E extends Throwable> FailableIntConsumer<E> nop() {
        return NOP;
    }

    public void accept(int var1) throws E;

    default public FailableIntConsumer<E> andThen(FailableIntConsumer<E> after) {
        Objects.requireNonNull(after);
        return t -> {
            this.accept(t);
            after.accept(t);
        };
    }
}

