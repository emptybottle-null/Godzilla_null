/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.SetMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface SortedSetMultimap<K, V>
extends SetMultimap<K, V> {
    @Override
    public SortedSet<V> get(@Nullable K var1);

    @Override
    @CanIgnoreReturnValue
    public SortedSet<V> removeAll(@Nullable Object var1);

    @Override
    @CanIgnoreReturnValue
    public SortedSet<V> replaceValues(K var1, Iterable<? extends V> var2);

    @Override
    public Map<K, Collection<V>> asMap();

    public Comparator<? super V> valueComparator();
}

