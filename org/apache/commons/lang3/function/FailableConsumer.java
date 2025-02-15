/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableConsumer<T, E extends Throwable> {
    public static final FailableConsumer NOP = t -> {};

    public static <T, E extends Throwable> FailableConsumer<T, E> nop() {
        return NOP;
    }

    public void accept(T var1) throws E;

    default public FailableConsumer<T, E> andThen(FailableConsumer<? super T, E> after) {
        Objects.requireNonNull(after);
        return t -> {
            this.accept(t);
            after.accept(t);
        };
    }
}

