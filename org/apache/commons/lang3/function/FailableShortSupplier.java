/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.function;

@FunctionalInterface
public interface FailableShortSupplier<E extends Throwable> {
    public short getAsShort() throws E;
}

