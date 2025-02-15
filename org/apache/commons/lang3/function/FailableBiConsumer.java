/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableBiConsumer<T, U, E extends Throwable> {
    public static final FailableBiConsumer NOP = (t, u) -> {};

    public static <T, U, E extends Throwable> FailableBiConsumer<T, U, E> nop() {
        return NOP;
    }

    public void accept(T var1, U var2) throws E;

    default public FailableBiConsumer<T, U, E> andThen(FailableBiConsumer<? super T, ? super U, E> after) {
        Objects.requireNonNull(after);
        return (t, u) -> {
            this.accept(t, u);
            after.accept(t, u);
        };
    }
}

