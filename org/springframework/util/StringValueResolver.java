/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import org.springframework.lang.Nullable;

@FunctionalInterface
public interface StringValueResolver {
    @Nullable
    public String resolveStringValue(String var1);
}

