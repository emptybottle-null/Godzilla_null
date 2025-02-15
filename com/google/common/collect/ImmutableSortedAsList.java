/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.RegularImmutableAsList;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.collect.SortedIterable;
import java.util.Comparator;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class ImmutableSortedAsList<E>
extends RegularImmutableAsList<E>
implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> backingSet, ImmutableList<E> backingList) {
        super(backingSet, backingList);
    }

    @Override
    ImmutableSortedSet<E> delegateCollection() {
        return (ImmutableSortedSet)super.delegateCollection();
    }

    @Override
    public Comparator<? super E> comparator() {
        return ((ImmutableSortedSet)this.delegateCollection()).comparator();
    }

    @Override
    @GwtIncompatible
    public int indexOf(@Nullable Object target) {
        int index = ((ImmutableSortedSet)this.delegateCollection()).indexOf(target);
        return index >= 0 && this.get(index).equals(target) ? index : -1;
    }

    @Override
    @GwtIncompatible
    public int lastIndexOf(@Nullable Object target) {
        return this.indexOf(target);
    }

    @Override
    public boolean contains(Object target) {
        return this.indexOf(target) >= 0;
    }

    @Override
    @GwtIncompatible
    ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        ImmutableList parentSubList = super.subListUnchecked(fromIndex, toIndex);
        return new RegularImmutableSortedSet(parentSubList, this.comparator()).asList();
    }

    @Override
    public Spliterator<E> spliterator() {
        return CollectSpliterators.indexed(this.size(), 1301, this.delegateList()::get, this.comparator());
    }
}

