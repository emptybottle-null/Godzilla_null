/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedIterable;
import com.google.common.collect.SortedMultisetBridge;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated=true)
public interface SortedMultiset<E>
extends SortedMultisetBridge<E>,
SortedIterable<E> {
    @Override
    public Comparator<? super E> comparator();

    public Multiset.Entry<E> firstEntry();

    public Multiset.Entry<E> lastEntry();

    public Multiset.Entry<E> pollFirstEntry();

    public Multiset.Entry<E> pollLastEntry();

    @Override
    public NavigableSet<E> elementSet();

    @Override
    public Set<Multiset.Entry<E>> entrySet();

    @Override
    public Iterator<E> iterator();

    public SortedMultiset<E> descendingMultiset();

    public SortedMultiset<E> headMultiset(E var1, BoundType var2);

    public SortedMultiset<E> subMultiset(E var1, BoundType var2, E var3, BoundType var4);

    public SortedMultiset<E> tailMultiset(E var1, BoundType var2);
}

