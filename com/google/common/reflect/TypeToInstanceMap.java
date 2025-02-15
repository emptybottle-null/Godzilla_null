/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.reflect.TypeToken;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public interface TypeToInstanceMap<B>
extends Map<TypeToken<? extends B>, B> {
    public <T extends B> @Nullable T getInstance(Class<T> var1);

    public <T extends B> @Nullable T getInstance(TypeToken<T> var1);

    @CanIgnoreReturnValue
    public <T extends B> @Nullable T putInstance(Class<T> var1, @Nullable T var2);

    @CanIgnoreReturnValue
    public <T extends B> @Nullable T putInstance(TypeToken<T> var1, @Nullable T var2);
}

