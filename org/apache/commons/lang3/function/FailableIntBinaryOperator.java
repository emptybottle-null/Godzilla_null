/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableIntBinaryOperator<E extends Throwable> {
    public int applyAsInt(int var1, int var2) throws E;
}

