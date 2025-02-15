/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.util.Comparator;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.util.comparator.NullSafeComparator;

public abstract class Comparators {
    public static <T> Comparator<T> comparable() {
        return ComparableComparator.INSTANCE;
    }

    public static <T> Comparator<T> nullsLow() {
        return NullSafeComparator.NULLS_LOW;
    }

    public static <T> Comparator<T> nullsLow(Comparator<T> comparator) {
        return new NullSafeComparator<T>(comparator, true);
    }

    public static <T> Comparator<T> nullsHigh() {
        return NullSafeComparator.NULLS_HIGH;
    }

    public static <T> Comparator<T> nullsHigh(Comparator<T> comparator) {
        return new NullSafeComparator<T>(comparator, false);
    }
}

