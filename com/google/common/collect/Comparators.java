/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.LexicographicalOrdering;
import com.google.common.collect.TopKSelector;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@GwtCompatible
public final class Comparators {
    private Comparators() {
    }

    @Beta
    public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
        return new LexicographicalOrdering<T>(Preconditions.checkNotNull(comparator));
    }

    @Beta
    public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<T> it = iterable.iterator();
        if (it.hasNext()) {
            T prev = it.next();
            while (it.hasNext()) {
                T next = it.next();
                if (comparator.compare(prev, next) > 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }

    @Beta
    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<T> it = iterable.iterator();
        if (it.hasNext()) {
            T prev = it.next();
            while (it.hasNext()) {
                T next = it.next();
                if (comparator.compare(prev, next) >= 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }

    public static <T> Collector<T, ?, List<T>> least(int k, Comparator<? super T> comparator) {
        CollectPreconditions.checkNonnegative(k, "k");
        Preconditions.checkNotNull(comparator);
        return Collector.of(() -> TopKSelector.least(k, comparator), TopKSelector::offer, TopKSelector::combine, TopKSelector::topK, Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, List<T>> greatest(int k, Comparator<? super T> comparator) {
        return Comparators.least(k, comparator.reversed());
    }

    @Beta
    public static <T> Comparator<Optional<T>> emptiesFirst(Comparator<? super T> valueComparator) {
        Preconditions.checkNotNull(valueComparator);
        return Comparator.comparing(o -> o.orElse(null), Comparator.nullsFirst(valueComparator));
    }

    @Beta
    public static <T> Comparator<Optional<T>> emptiesLast(Comparator<? super T> valueComparator) {
        Preconditions.checkNotNull(valueComparator);
        return Comparator.comparing(o -> o.orElse(null), Comparator.nullsLast(valueComparator));
    }
}

