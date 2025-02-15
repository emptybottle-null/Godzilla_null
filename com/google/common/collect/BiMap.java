/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface BiMap<K, V>
extends Map<K, V> {
    @Override
    @CanIgnoreReturnValue
    public @Nullable V put(@Nullable K var1, @Nullable V var2);

    @CanIgnoreReturnValue
    public @Nullable V forcePut(@Nullable K var1, @Nullable V var2);

    @Override
    public void putAll(Map<? extends K, ? extends V> var1);

    @Override
    public Set<V> values();

    public BiMap<V, K> inverse();
}

