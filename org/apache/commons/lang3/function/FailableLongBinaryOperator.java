/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableLongBinaryOperator<E extends Throwable> {
    public long applyAsLong(long var1, long var3) throws E;
}

