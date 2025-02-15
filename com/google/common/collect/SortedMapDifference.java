/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.MapDifference;
import java.util.SortedMap;

@GwtCompatible
public interface SortedMapDifference<K, V>
extends MapDifference<K, V> {
    @Override
    public SortedMap<K, V> entriesOnlyOnLeft();

    @Override
    public SortedMap<K, V> entriesOnlyOnRight();

    @Override
    public SortedMap<K, V> entriesInCommon();

    @Override
    public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering();
}

