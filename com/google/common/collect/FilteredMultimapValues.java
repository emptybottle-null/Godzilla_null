/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.FilteredMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
final class FilteredMultimapValues<K, V>
extends AbstractCollection<V> {
    @Weak
    private final FilteredMultimap<K, V> multimap;

    FilteredMultimapValues(FilteredMultimap<K, V> multimap) {
        this.multimap = Preconditions.checkNotNull(multimap);
    }

    @Override
    public Iterator<V> iterator() {
        return Maps.valueIterator(this.multimap.entries().iterator());
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return this.multimap.containsValue(o);
    }

    @Override
    public int size() {
        return this.multimap.size();
    }

    @Override
    public boolean remove(@Nullable Object o) {
        Predicate<Map.Entry<K, V>> entryPredicate = this.multimap.entryPredicate();
        Iterator<Map.Entry<K, V>> unfilteredItr = this.multimap.unfiltered().entries().iterator();
        while (unfilteredItr.hasNext()) {
            Map.Entry<K, V> entry = unfilteredItr.next();
            if (!entryPredicate.apply(entry) || !Objects.equal(entry.getValue(), o)) continue;
            unfilteredItr.remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.in(c))));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c)))));
    }

    @Override
    public void clear() {
        this.multimap.clear();
    }
}

