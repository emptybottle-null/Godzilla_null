/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.util.Comparator;

public class ComparableComparator<T extends Comparable<T>>
implements Comparator<T> {
    public static final ComparableComparator INSTANCE = new ComparableComparator();

    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}

