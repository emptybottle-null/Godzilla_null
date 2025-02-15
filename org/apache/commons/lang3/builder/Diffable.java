/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.builder.DiffResult;

@FunctionalInterface
public interface Diffable<T> {
    public DiffResult<T> diff(T var1);
}

