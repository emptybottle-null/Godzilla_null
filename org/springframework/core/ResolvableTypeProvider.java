/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

public interface ResolvableTypeProvider {
    @Nullable
    public ResolvableType getResolvableType();
}

