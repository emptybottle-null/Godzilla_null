/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import com.google.common.collect.FilteredKeyMultimap;
import com.google.common.collect.FilteredSetMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
final class FilteredKeySetMultimap<K, V>
extends FilteredKeyMultimap<K, V>
implements FilteredSetMultimap<K, V> {
    FilteredKeySetMultimap(SetMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        super(unfiltered, keyPredicate);
    }

    @Override
    public SetMultimap<K, V> unfiltered() {
        return (SetMultimap)this.unfiltered;
    }

    @Override
    public Set<V> get(K key) {
        return (Set)super.get(key);
    }

    @Override
    public Set<V> removeAll(Object key) {
        return (Set)super.removeAll(key);
    }

    @Override
    public Set<V> replaceValues(K key, Iterable<? extends V> values) {
        return (Set)super.replaceValues(key, values);
    }

    @Override
    public Set<Map.Entry<K, V>> entries() {
        return (Set)super.entries();
    }

    @Override
    Set<Map.Entry<K, V>> createEntries() {
        return new EntrySet();
    }

    class EntrySet
    extends FilteredKeyMultimap.Entries
    implements Set<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        @Override
        public boolean equals(@Nullable Object o) {
            return Sets.equalsImpl(this, o);
        }
    }
}

