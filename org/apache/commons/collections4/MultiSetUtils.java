/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.collections4.multiset.PredicatedMultiSet;
import org.apache.commons.collections4.multiset.SynchronizedMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;

public class MultiSetUtils {
    public static final MultiSet EMPTY_MULTISET = UnmodifiableMultiSet.unmodifiableMultiSet(new HashMultiSet());

    private MultiSetUtils() {
    }

    public static <E> MultiSet<E> synchronizedMultiSet(MultiSet<E> multiset) {
        return SynchronizedMultiSet.synchronizedMultiSet(multiset);
    }

    public static <E> MultiSet<E> unmodifiableMultiSet(MultiSet<? extends E> multiset) {
        return UnmodifiableMultiSet.unmodifiableMultiSet(multiset);
    }

    public static <E> MultiSet<E> predicatedMultiSet(MultiSet<E> multiset, Predicate<? super E> predicate) {
        return PredicatedMultiSet.predicatedMultiSet(multiset, predicate);
    }

    public static <E> MultiSet<E> emptyMultiSet() {
        return EMPTY_MULTISET;
    }
}

