/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@GwtCompatible(emulated=true)
final class Platform {
    static <K, V> Map<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return Maps.newHashMapWithExpectedSize(expectedSize);
    }

    static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
    }

    static <E> Set<E> newHashSetWithExpectedSize(int expectedSize) {
        return Sets.newHashSetWithExpectedSize(expectedSize);
    }

    static <E> Set<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return Sets.newLinkedHashSetWithExpectedSize(expectedSize);
    }

    static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return Maps.newLinkedHashMap();
    }

    static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return Sets.newLinkedHashSet();
    }

    static <T> T[] newArray(T[] reference, int length) {
        Class<?> type = reference.getClass().getComponentType();
        Object[] result = (Object[])Array.newInstance(type, length);
        return result;
    }

    static <T> T[] copy(Object[] source, int from, int to, T[] arrayOfType) {
        return Arrays.copyOfRange(source, from, to, arrayOfType.getClass());
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    static int reduceIterationsIfGwt(int iterations) {
        return iterations;
    }

    static int reduceExponentIfGwt(int exponent) {
        return exponent;
    }

    private Platform() {
    }
}

