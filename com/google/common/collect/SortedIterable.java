/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;

@GwtCompatible
interface SortedIterable<T>
extends Iterable<T> {
    public Comparator<? super T> comparator();

    @Override
    public Iterator<T> iterator();
}

