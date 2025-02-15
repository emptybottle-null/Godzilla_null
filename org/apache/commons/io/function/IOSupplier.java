/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.function;

import java.io.IOException;

@FunctionalInterface
public interface IOSupplier<T> {
    public T get() throws IOException;
}

