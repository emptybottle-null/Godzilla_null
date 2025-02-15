/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface ListMultimap<K, V>
extends Multimap<K, V> {
    @Override
    public List<V> get(@Nullable K var1);

    @Override
    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object var1);

    @Override
    @CanIgnoreReturnValue
    public List<V> replaceValues(K var1, Iterable<? extends V> var2);

    @Override
    public Map<K, Collection<V>> asMap();

    @Override
    public boolean equals(@Nullable Object var1);
}

