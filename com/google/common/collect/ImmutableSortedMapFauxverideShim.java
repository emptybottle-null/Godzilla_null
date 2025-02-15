/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

@GwtIncompatible
abstract class ImmutableSortedMapFauxverideShim<K, V>
extends ImmutableMap<K, V> {
    ImmutableSortedMapFauxverideShim() {
    }

    @Deprecated
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap.Builder<K, V> builder() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap.Builder<K, V> builderWithExpectedSize(int expectedSize) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <K, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        throw new UnsupportedOperationException();
    }
}

