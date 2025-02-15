/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.AbstractListMultimap;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.AbstractSetMultimap;
import com.google.common.collect.AbstractSortedSetMultimap;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Collections2;
import com.google.common.collect.FilteredEntryMultimap;
import com.google.common.collect.FilteredEntrySetMultimap;
import com.google.common.collect.FilteredKeyListMultimap;
import com.google.common.collect.FilteredKeyMultimap;
import com.google.common.collect.FilteredKeySetMultimap;
import com.google.common.collect.FilteredMultimap;
import com.google.common.collect.FilteredSetMultimap;
import com.google.common.collect.ForwardingMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.Synchronized;
import com.google.common.collect.TransformedIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Multimaps {
    private Multimaps() {
    }

    @Beta
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends V> valueFunction, Supplier<M> multimapSupplier) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        Preconditions.checkNotNull(multimapSupplier);
        return Collector.of(multimapSupplier, (multimap, input) -> multimap.put(keyFunction.apply(input), valueFunction.apply(input)), (multimap1, multimap2) -> {
            multimap1.putAll(multimap2);
            return multimap1;
        }, new Collector.Characteristics[0]);
    }

    @Beta
    public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(java.util.function.Function<? super T, ? extends K> keyFunction, java.util.function.Function<? super T, ? extends Stream<? extends V>> valueFunction, Supplier<M> multimapSupplier) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        Preconditions.checkNotNull(multimapSupplier);
        return Collector.of(multimapSupplier, (multimap, input) -> {
            Object key = keyFunction.apply(input);
            Collection valuesForKey = multimap.get(key);
            ((Stream)valueFunction.apply(input)).forEachOrdered(valuesForKey::add);
        }, (multimap1, multimap2) -> {
            multimap1.putAll(multimap2);
            return multimap1;
        }, new Collector.Characteristics[0]);
    }

    public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends Collection<V>> factory) {
        return new CustomMultimap<K, V>(map, factory);
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends List<V>> factory) {
        return new CustomListMultimap<K, V>(map, factory);
    }

    public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends Set<V>> factory) {
        return new CustomSetMultimap<K, V>(map, factory);
    }

    public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends SortedSet<V>> factory) {
        return new CustomSortedSetMultimap<K, V>(map, factory);
    }

    @CanIgnoreReturnValue
    public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> source, M dest) {
        Preconditions.checkNotNull(dest);
        for (Map.Entry<V, K> entry : source.entries()) {
            dest.put(entry.getValue(), entry.getKey());
        }
        return dest;
    }

    public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
        return Synchronized.multimap(multimap, null);
    }

    public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> delegate) {
        if (delegate instanceof UnmodifiableMultimap || delegate instanceof ImmutableMultimap) {
            return delegate;
        }
        return new UnmodifiableMultimap<K, V>(delegate);
    }

    @Deprecated
    public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> delegate) {
        return Preconditions.checkNotNull(delegate);
    }

    public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> multimap) {
        return Synchronized.setMultimap(multimap, null);
    }

    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> delegate) {
        if (delegate instanceof UnmodifiableSetMultimap || delegate instanceof ImmutableSetMultimap) {
            return delegate;
        }
        return new UnmodifiableSetMultimap<K, V>(delegate);
    }

    @Deprecated
    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> delegate) {
        return Preconditions.checkNotNull(delegate);
    }

    public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> multimap) {
        return Synchronized.sortedSetMultimap(multimap, null);
    }

    public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
        if (delegate instanceof UnmodifiableSortedSetMultimap) {
            return delegate;
        }
        return new UnmodifiableSortedSetMultimap<K, V>(delegate);
    }

    public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> multimap) {
        return Synchronized.listMultimap(multimap, null);
    }

    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> delegate) {
        if (delegate instanceof UnmodifiableListMultimap || delegate instanceof ImmutableListMultimap) {
            return delegate;
        }
        return new UnmodifiableListMultimap<K, V>(delegate);
    }

    @Deprecated
    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> delegate) {
        return Preconditions.checkNotNull(delegate);
    }

    private static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
        if (collection instanceof SortedSet) {
            return Collections.unmodifiableSortedSet((SortedSet)collection);
        }
        if (collection instanceof Set) {
            return Collections.unmodifiableSet((Set)collection);
        }
        if (collection instanceof List) {
            return Collections.unmodifiableList((List)collection);
        }
        return Collections.unmodifiableCollection(collection);
    }

    private static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> entries) {
        if (entries instanceof Set) {
            return Maps.unmodifiableEntrySet((Set)entries);
        }
        return new Maps.UnmodifiableEntries<K, V>(Collections.unmodifiableCollection(entries));
    }

    @Beta
    public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> multimap) {
        return multimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> multimap) {
        return multimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> multimap) {
        return multimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
        return multimap.asMap();
    }

    public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
        return new MapMultimap<K, V>(map);
    }

    public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> fromMultimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        Maps.EntryTransformer transformer = Maps.asEntryTransformer(function);
        return Multimaps.transformEntries(fromMultimap, transformer);
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> fromMultimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        Maps.EntryTransformer transformer = Maps.asEntryTransformer(function);
        return Multimaps.transformEntries(fromMultimap, transformer);
    }

    public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesMultimap<K, V1, V2>(fromMap, transformer);
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesListMultimap<K, V1, V2>(fromMap, transformer);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> values, Function<? super V, K> keyFunction) {
        return Multimaps.index(values.iterator(), keyFunction);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> values, Function<? super V, K> keyFunction) {
        Preconditions.checkNotNull(keyFunction);
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        while (values.hasNext()) {
            V value = values.next();
            Preconditions.checkNotNull(value, values);
            builder.put((Object)keyFunction.apply(value), (Object)value);
        }
        return builder.build();
    }

    public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof SetMultimap) {
            return Multimaps.filterKeys((SetMultimap)unfiltered, keyPredicate);
        }
        if (unfiltered instanceof ListMultimap) {
            return Multimaps.filterKeys((ListMultimap)unfiltered, keyPredicate);
        }
        if (unfiltered instanceof FilteredKeyMultimap) {
            FilteredKeyMultimap prev = (FilteredKeyMultimap)unfiltered;
            return new FilteredKeyMultimap(prev.unfiltered, Predicates.and(prev.keyPredicate, keyPredicate));
        }
        if (unfiltered instanceof FilteredMultimap) {
            FilteredMultimap prev = (FilteredMultimap)unfiltered;
            return Multimaps.filterFiltered(prev, Maps.keyPredicateOnEntries(keyPredicate));
        }
        return new FilteredKeyMultimap<K, V>(unfiltered, keyPredicate);
    }

    public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof FilteredKeySetMultimap) {
            FilteredKeySetMultimap prev = (FilteredKeySetMultimap)unfiltered;
            return new FilteredKeySetMultimap(prev.unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
        }
        if (unfiltered instanceof FilteredSetMultimap) {
            FilteredSetMultimap prev = (FilteredSetMultimap)unfiltered;
            return Multimaps.filterFiltered(prev, Maps.keyPredicateOnEntries(keyPredicate));
        }
        return new FilteredKeySetMultimap<K, V>(unfiltered, keyPredicate);
    }

    public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        if (unfiltered instanceof FilteredKeyListMultimap) {
            FilteredKeyListMultimap prev = (FilteredKeyListMultimap)unfiltered;
            return new FilteredKeyListMultimap(prev.unfiltered(), Predicates.and(prev.keyPredicate, keyPredicate));
        }
        return new FilteredKeyListMultimap<K, V>(unfiltered, keyPredicate);
    }

    public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return Multimaps.filterEntries(unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return Multimaps.filterEntries(unfiltered, Maps.valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(entryPredicate);
        if (unfiltered instanceof SetMultimap) {
            return Multimaps.filterEntries((SetMultimap)unfiltered, entryPredicate);
        }
        return unfiltered instanceof FilteredMultimap ? Multimaps.filterFiltered((FilteredMultimap)unfiltered, entryPredicate) : new FilteredEntryMultimap<K, V>(Preconditions.checkNotNull(unfiltered), entryPredicate);
    }

    public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(entryPredicate);
        return unfiltered instanceof FilteredSetMultimap ? Multimaps.filterFiltered((FilteredSetMultimap)unfiltered, entryPredicate) : new FilteredEntrySetMultimap<K, V>(Preconditions.checkNotNull(unfiltered), entryPredicate);
    }

    private static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Predicate<? super Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
        return new FilteredEntryMultimap<K, V>(multimap.unfiltered(), predicate);
    }

    private static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Predicate<? super Map.Entry<K, V>> predicate = Predicates.and(multimap.entryPredicate(), entryPredicate);
        return new FilteredEntrySetMultimap(multimap.unfiltered(), predicate);
    }

    static boolean equalsImpl(Multimap<?, ?> multimap, @Nullable Object object) {
        if (object == multimap) {
            return true;
        }
        if (object instanceof Multimap) {
            Multimap that = (Multimap)object;
            return multimap.asMap().equals(that.asMap());
        }
        return false;
    }

    static final class AsMap<K, V>
    extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        @Weak
        private final Multimap<K, V> multimap;

        AsMap(Multimap<K, V> multimap) {
            this.multimap = Preconditions.checkNotNull(multimap);
        }

        @Override
        public int size() {
            return this.multimap.keySet().size();
        }

        @Override
        protected Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new EntrySet();
        }

        void removeValuesForKey(Object key) {
            this.multimap.keySet().remove(key);
        }

        @Override
        public Collection<V> get(Object key) {
            return this.containsKey(key) ? this.multimap.get(key) : null;
        }

        @Override
        public Collection<V> remove(Object key) {
            return this.containsKey(key) ? this.multimap.removeAll(key) : null;
        }

        @Override
        public Set<K> keySet() {
            return this.multimap.keySet();
        }

        @Override
        public boolean isEmpty() {
            return this.multimap.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.multimap.containsKey(key);
        }

        @Override
        public void clear() {
            this.multimap.clear();
        }

        class EntrySet
        extends Maps.EntrySet<K, Collection<V>> {
            EntrySet() {
            }

            @Override
            Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return Maps.asMapEntryIterator(AsMap.this.multimap.keySet(), new Function<K, Collection<V>>(){

                    @Override
                    public Collection<V> apply(K key) {
                        return AsMap.this.multimap.get(key);
                    }
                });
            }

            @Override
            public boolean remove(Object o) {
                if (!this.contains(o)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry)o;
                AsMap.this.removeValuesForKey(entry.getKey());
                return true;
            }
        }
    }

    static abstract class Entries<K, V>
    extends AbstractCollection<Map.Entry<K, V>> {
        Entries() {
        }

        abstract Multimap<K, V> multimap();

        @Override
        public int size() {
            return this.multimap().size();
        }

        @Override
        public boolean contains(@Nullable Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                return this.multimap().containsEntry(entry.getKey(), entry.getValue());
            }
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                return this.multimap().remove(entry.getKey(), entry.getValue());
            }
            return false;
        }

        @Override
        public void clear() {
            this.multimap().clear();
        }
    }

    static class Keys<K, V>
    extends AbstractMultiset<K> {
        @Weak
        final Multimap<K, V> multimap;

        Keys(Multimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override
        Iterator<Multiset.Entry<K>> entryIterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Multiset.Entry<K>>(this.multimap.asMap().entrySet().iterator()){

                @Override
                Multiset.Entry<K> transform(final Map.Entry<K, Collection<V>> backingEntry) {
                    return new Multisets.AbstractEntry<K>(){

                        @Override
                        public K getElement() {
                            return backingEntry.getKey();
                        }

                        @Override
                        public int getCount() {
                            return ((Collection)backingEntry.getValue()).size();
                        }
                    };
                }
            };
        }

        @Override
        public Spliterator<K> spliterator() {
            return CollectSpliterators.map(this.multimap.entries().spliterator(), Map.Entry::getKey);
        }

        @Override
        public void forEach(Consumer<? super K> consumer) {
            Preconditions.checkNotNull(consumer);
            this.multimap.entries().forEach((? super T entry) -> consumer.accept((Object)entry.getKey()));
        }

        @Override
        int distinctElements() {
            return this.multimap.asMap().size();
        }

        @Override
        public int size() {
            return this.multimap.size();
        }

        @Override
        public boolean contains(@Nullable Object element) {
            return this.multimap.containsKey(element);
        }

        @Override
        public Iterator<K> iterator() {
            return Maps.keyIterator(this.multimap.entries().iterator());
        }

        @Override
        public int count(@Nullable Object element) {
            Collection<V> values = Maps.safeGet(this.multimap.asMap(), element);
            return values == null ? 0 : values.size();
        }

        @Override
        public int remove(@Nullable Object element, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return this.count(element);
            }
            Collection<V> values = Maps.safeGet(this.multimap.asMap(), element);
            if (values == null) {
                return 0;
            }
            int oldCount = values.size();
            if (occurrences >= oldCount) {
                values.clear();
            } else {
                Iterator<V> iterator = values.iterator();
                for (int i = 0; i < occurrences; ++i) {
                    iterator.next();
                    iterator.remove();
                }
            }
            return oldCount;
        }

        @Override
        public void clear() {
            this.multimap.clear();
        }

        @Override
        public Set<K> elementSet() {
            return this.multimap.keySet();
        }

        @Override
        Iterator<K> elementIterator() {
            throw new AssertionError((Object)"should never be called");
        }
    }

    private static final class TransformedEntriesListMultimap<K, V1, V2>
    extends TransformedEntriesMultimap<K, V1, V2>
    implements ListMultimap<K, V2> {
        TransformedEntriesListMultimap(ListMultimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
            super(fromMultimap, transformer);
        }

        @Override
        List<V2> transform(K key, Collection<V1> values) {
            return Lists.transform((List)values, Maps.asValueToValueFunction(this.transformer, key));
        }

        @Override
        public List<V2> get(K key) {
            return this.transform((Object)key, this.fromMultimap.get(key));
        }

        @Override
        public List<V2> removeAll(Object key) {
            return this.transform(key, this.fromMultimap.removeAll(key));
        }

        @Override
        public List<V2> replaceValues(K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }
    }

    private static class TransformedEntriesMultimap<K, V1, V2>
    extends AbstractMultimap<K, V2> {
        final Multimap<K, V1> fromMultimap;
        final Maps.EntryTransformer<? super K, ? super V1, V2> transformer;

        TransformedEntriesMultimap(Multimap<K, V1> fromMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
            this.fromMultimap = Preconditions.checkNotNull(fromMultimap);
            this.transformer = Preconditions.checkNotNull(transformer);
        }

        Collection<V2> transform(K key, Collection<V1> values) {
            Function<? super V1, V2> function = Maps.asValueToValueFunction(this.transformer, key);
            if (values instanceof List) {
                return Lists.transform((List)values, function);
            }
            return Collections2.transform(values, function);
        }

        @Override
        Map<K, Collection<V2>> createAsMap() {
            return Maps.transformEntries(this.fromMultimap.asMap(), new Maps.EntryTransformer<K, Collection<V1>, Collection<V2>>(){

                @Override
                public Collection<V2> transformEntry(K key, Collection<V1> value) {
                    return this.transform(key, value);
                }
            });
        }

        @Override
        public void clear() {
            this.fromMultimap.clear();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.fromMultimap.containsKey(key);
        }

        @Override
        Collection<Map.Entry<K, V2>> createEntries() {
            return new AbstractMultimap.Entries();
        }

        @Override
        Iterator<Map.Entry<K, V2>> entryIterator() {
            return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override
        public Collection<V2> get(K key) {
            return this.transform(key, this.fromMultimap.get(key));
        }

        @Override
        public boolean isEmpty() {
            return this.fromMultimap.isEmpty();
        }

        @Override
        Set<K> createKeySet() {
            return this.fromMultimap.keySet();
        }

        @Override
        Multiset<K> createKeys() {
            return this.fromMultimap.keys();
        }

        @Override
        public boolean put(K key, V2 value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object key, Object value) {
            return this.get(key).remove(value);
        }

        @Override
        public Collection<V2> removeAll(Object key) {
            return this.transform(key, this.fromMultimap.removeAll(key));
        }

        @Override
        public Collection<V2> replaceValues(K key, Iterable<? extends V2> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return this.fromMultimap.size();
        }

        @Override
        Collection<V2> createValues() {
            return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
        }
    }

    private static class MapMultimap<K, V>
    extends AbstractMultimap<K, V>
    implements SetMultimap<K, V>,
    Serializable {
        final Map<K, V> map;
        private static final long serialVersionUID = 7845222491160860175L;

        MapMultimap(Map<K, V> map) {
            this.map = Preconditions.checkNotNull(map);
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.map.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.map.containsValue(value);
        }

        @Override
        public boolean containsEntry(Object key, Object value) {
            return this.map.entrySet().contains(Maps.immutableEntry(key, value));
        }

        @Override
        public Set<V> get(final K key) {
            return new Sets.ImprovedAbstractSet<V>(){

                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>(){
                        int i;

                        @Override
                        public boolean hasNext() {
                            return this.i == 0 && map.containsKey(key);
                        }

                        @Override
                        public V next() {
                            if (!this.hasNext()) {
                                throw new NoSuchElementException();
                            }
                            ++this.i;
                            return map.get(key);
                        }

                        @Override
                        public void remove() {
                            CollectPreconditions.checkRemove(this.i == 1);
                            this.i = -1;
                            map.remove(key);
                        }
                    };
                }

                @Override
                public int size() {
                    return map.containsKey(key) ? 1 : 0;
                }
            };
        }

        @Override
        public boolean put(K key, V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<V> replaceValues(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object key, Object value) {
            return this.map.entrySet().remove(Maps.immutableEntry(key, value));
        }

        @Override
        public Set<V> removeAll(Object key) {
            HashSet<V> values = new HashSet<V>(2);
            if (!this.map.containsKey(key)) {
                return values;
            }
            values.add(this.map.remove(key));
            return values;
        }

        @Override
        public void clear() {
            this.map.clear();
        }

        @Override
        Set<K> createKeySet() {
            return this.map.keySet();
        }

        @Override
        Collection<V> createValues() {
            return this.map.values();
        }

        @Override
        public Set<Map.Entry<K, V>> entries() {
            return this.map.entrySet();
        }

        @Override
        Collection<Map.Entry<K, V>> createEntries() {
            throw new AssertionError((Object)"unreachable");
        }

        @Override
        Multiset<K> createKeys() {
            return new Keys(this);
        }

        @Override
        Iterator<Map.Entry<K, V>> entryIterator() {
            return this.map.entrySet().iterator();
        }

        @Override
        Map<K, Collection<V>> createAsMap() {
            return new AsMap(this);
        }

        @Override
        public int hashCode() {
            return this.map.hashCode();
        }
    }

    private static class UnmodifiableSortedSetMultimap<K, V>
    extends UnmodifiableSetMultimap<K, V>
    implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0L;

        UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> delegate) {
            super(delegate);
        }

        @Override
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap)super.delegate();
        }

        @Override
        public SortedSet<V> get(K key) {
            return Collections.unmodifiableSortedSet(this.delegate().get((Object)key));
        }

        @Override
        public SortedSet<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Comparator<? super V> valueComparator() {
            return this.delegate().valueComparator();
        }
    }

    private static class UnmodifiableSetMultimap<K, V>
    extends UnmodifiableMultimap<K, V>
    implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0L;

        UnmodifiableSetMultimap(SetMultimap<K, V> delegate) {
            super(delegate);
        }

        @Override
        public SetMultimap<K, V> delegate() {
            return (SetMultimap)super.delegate();
        }

        @Override
        public Set<V> get(K key) {
            return Collections.unmodifiableSet(this.delegate().get((Object)key));
        }

        @Override
        public Set<Map.Entry<K, V>> entries() {
            return Maps.unmodifiableEntrySet(this.delegate().entries());
        }

        @Override
        public Set<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<V> replaceValues(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UnmodifiableListMultimap<K, V>
    extends UnmodifiableMultimap<K, V>
    implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0L;

        UnmodifiableListMultimap(ListMultimap<K, V> delegate) {
            super(delegate);
        }

        @Override
        public ListMultimap<K, V> delegate() {
            return (ListMultimap)super.delegate();
        }

        @Override
        public List<V> get(K key) {
            return Collections.unmodifiableList(this.delegate().get((Object)key));
        }

        @Override
        public List<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<V> replaceValues(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UnmodifiableMultimap<K, V>
    extends ForwardingMultimap<K, V>
    implements Serializable {
        final Multimap<K, V> delegate;
        transient @MonotonicNonNull Collection<Map.Entry<K, V>> entries;
        transient @MonotonicNonNull Multiset<K> keys;
        transient @MonotonicNonNull Set<K> keySet;
        transient @MonotonicNonNull Collection<V> values;
        transient @MonotonicNonNull Map<K, Collection<V>> map;
        private static final long serialVersionUID = 0L;

        UnmodifiableMultimap(Multimap<K, V> delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        protected Multimap<K, V> delegate() {
            return this.delegate;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<Object>> result = this.map;
            if (result == null) {
                result = this.map = Collections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new Function<Collection<V>, Collection<V>>(){

                    @Override
                    public Collection<V> apply(Collection<V> collection) {
                        return Multimaps.unmodifiableValueCollection(collection);
                    }
                }));
            }
            return result;
        }

        @Override
        public Collection<Map.Entry<K, V>> entries() {
            Collection result = this.entries;
            if (result == null) {
                this.entries = result = Multimaps.unmodifiableEntries(this.delegate.entries());
            }
            return result;
        }

        @Override
        public Collection<V> get(K key) {
            return Multimaps.unmodifiableValueCollection(this.delegate.get(key));
        }

        @Override
        public Multiset<K> keys() {
            Multiset<K> result = this.keys;
            if (result == null) {
                this.keys = result = Multisets.unmodifiableMultiset(this.delegate.keys());
            }
            return result;
        }

        @Override
        public Set<K> keySet() {
            Set<K> result = this.keySet;
            if (result == null) {
                this.keySet = result = Collections.unmodifiableSet(this.delegate.keySet());
            }
            return result;
        }

        @Override
        public boolean put(K key, V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<V> removeAll(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<V> values() {
            Collection<V> result = this.values;
            if (result == null) {
                this.values = result = Collections.unmodifiableCollection(this.delegate.values());
            }
            return result;
        }
    }

    private static class CustomSortedSetMultimap<K, V>
    extends AbstractSortedSetMultimap<K, V> {
        transient com.google.common.base.Supplier<? extends SortedSet<V>> factory;
        transient Comparator<? super V> valueComparator;
        @GwtIncompatible
        private static final long serialVersionUID = 0L;

        CustomSortedSetMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends SortedSet<V>> factory) {
            super(map);
            this.factory = Preconditions.checkNotNull(factory);
            this.valueComparator = factory.get().comparator();
        }

        @Override
        Set<K> createKeySet() {
            return this.createMaybeNavigableKeySet();
        }

        @Override
        Map<K, Collection<V>> createAsMap() {
            return this.createMaybeNavigableAsMap();
        }

        @Override
        protected SortedSet<V> createCollection() {
            return this.factory.get();
        }

        @Override
        public Comparator<? super V> valueComparator() {
            return this.valueComparator;
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(this.backingMap());
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.factory = (com.google.common.base.Supplier)stream.readObject();
            this.valueComparator = this.factory.get().comparator();
            Map map = (Map)stream.readObject();
            this.setMap(map);
        }
    }

    private static class CustomSetMultimap<K, V>
    extends AbstractSetMultimap<K, V> {
        transient com.google.common.base.Supplier<? extends Set<V>> factory;
        @GwtIncompatible
        private static final long serialVersionUID = 0L;

        CustomSetMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends Set<V>> factory) {
            super(map);
            this.factory = Preconditions.checkNotNull(factory);
        }

        @Override
        Set<K> createKeySet() {
            return this.createMaybeNavigableKeySet();
        }

        @Override
        Map<K, Collection<V>> createAsMap() {
            return this.createMaybeNavigableAsMap();
        }

        @Override
        protected Set<V> createCollection() {
            return this.factory.get();
        }

        @Override
        <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            if (collection instanceof NavigableSet) {
                return Sets.unmodifiableNavigableSet((NavigableSet)collection);
            }
            if (collection instanceof SortedSet) {
                return Collections.unmodifiableSortedSet((SortedSet)collection);
            }
            return Collections.unmodifiableSet((Set)collection);
        }

        @Override
        Collection<V> wrapCollection(K key, Collection<V> collection) {
            if (collection instanceof NavigableSet) {
                return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, null);
            }
            if (collection instanceof SortedSet) {
                return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, null);
            }
            return new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection);
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(this.backingMap());
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.factory = (com.google.common.base.Supplier)stream.readObject();
            Map map = (Map)stream.readObject();
            this.setMap(map);
        }
    }

    private static class CustomListMultimap<K, V>
    extends AbstractListMultimap<K, V> {
        transient com.google.common.base.Supplier<? extends List<V>> factory;
        @GwtIncompatible
        private static final long serialVersionUID = 0L;

        CustomListMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends List<V>> factory) {
            super(map);
            this.factory = Preconditions.checkNotNull(factory);
        }

        @Override
        Set<K> createKeySet() {
            return this.createMaybeNavigableKeySet();
        }

        @Override
        Map<K, Collection<V>> createAsMap() {
            return this.createMaybeNavigableAsMap();
        }

        @Override
        protected List<V> createCollection() {
            return this.factory.get();
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(this.backingMap());
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.factory = (com.google.common.base.Supplier)stream.readObject();
            Map map = (Map)stream.readObject();
            this.setMap(map);
        }
    }

    private static class CustomMultimap<K, V>
    extends AbstractMapBasedMultimap<K, V> {
        transient com.google.common.base.Supplier<? extends Collection<V>> factory;
        @GwtIncompatible
        private static final long serialVersionUID = 0L;

        CustomMultimap(Map<K, Collection<V>> map, com.google.common.base.Supplier<? extends Collection<V>> factory) {
            super(map);
            this.factory = Preconditions.checkNotNull(factory);
        }

        @Override
        Set<K> createKeySet() {
            return this.createMaybeNavigableKeySet();
        }

        @Override
        Map<K, Collection<V>> createAsMap() {
            return this.createMaybeNavigableAsMap();
        }

        @Override
        protected Collection<V> createCollection() {
            return this.factory.get();
        }

        @Override
        <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            if (collection instanceof NavigableSet) {
                return Sets.unmodifiableNavigableSet((NavigableSet)collection);
            }
            if (collection instanceof SortedSet) {
                return Collections.unmodifiableSortedSet((SortedSet)collection);
            }
            if (collection instanceof Set) {
                return Collections.unmodifiableSet((Set)collection);
            }
            if (collection instanceof List) {
                return Collections.unmodifiableList((List)collection);
            }
            return Collections.unmodifiableCollection(collection);
        }

        @Override
        Collection<V> wrapCollection(K key, Collection<V> collection) {
            if (collection instanceof List) {
                return this.wrapList(key, (List)collection, null);
            }
            if (collection instanceof NavigableSet) {
                return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, null);
            }
            if (collection instanceof SortedSet) {
                return (AbstractMapBasedMultimap)this.new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, null);
            }
            if (collection instanceof Set) {
                return new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection);
            }
            return new AbstractMapBasedMultimap.WrappedCollection(key, collection, null);
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(this.factory);
            stream.writeObject(this.backingMap());
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.factory = (com.google.common.base.Supplier)stream.readObject();
            Map map = (Map)stream.readObject();
            this.setMap(map);
        }
    }
}

