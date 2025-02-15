/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.reflect.Type;

@FunctionalInterface
public interface Typed<T> {
    public Type getType();
}

