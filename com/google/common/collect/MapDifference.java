/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface MapDifference<K, V> {
    public boolean areEqual();

    public Map<K, V> entriesOnlyOnLeft();

    public Map<K, V> entriesOnlyOnRight();

    public Map<K, V> entriesInCommon();

    public Map<K, ValueDifference<V>> entriesDiffering();

    public boolean equals(@Nullable Object var1);

    public int hashCode();

    public static interface ValueDifference<V> {
        public V leftValue();

        public V rightValue();

        public boolean equals(@Nullable Object var1);

        public int hashCode();
    }
}

