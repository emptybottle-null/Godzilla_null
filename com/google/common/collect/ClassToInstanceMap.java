/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface ClassToInstanceMap<B>
extends Map<Class<? extends B>, B> {
    public <T extends B> T getInstance(Class<T> var1);

    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> var1, @Nullable T var2);
}

