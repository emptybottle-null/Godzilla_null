/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.FilteredMultimap;
import com.google.common.collect.FilteredMultimapValues;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
class FilteredEntryMultimap<K, V>
extends AbstractMultimap<K, V>
implements FilteredMultimap<K, V> {
    final Multimap<K, V> unfiltered;
    final Predicate<? super Map.Entry<K, V>> predicate;

    FilteredEntryMultimap(Multimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
        this.unfiltered = Preconditions.checkNotNull(unfiltered);
        this.predicate = Preconditions.checkNotNull(predicate);
    }

    @Override
    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    @Override
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return this.predicate;
    }

    @Override
    public int size() {
        return this.entries().size();
    }

    private boolean satisfies(K key, V value) {
        return this.predicate.apply(Maps.immutableEntry(key, value));
    }

    static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof Set) {
            return Sets.filter((Set)collection, predicate);
        }
        return Collections2.filter(collection, predicate);
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return this.asMap().get(key) != null;
    }

    @Override
    public Collection<V> removeAll(@Nullable Object key) {
        return MoreObjects.firstNonNull(this.asMap().remove(key), this.unmodifiableEmptyCollection());
    }

    Collection<V> unmodifiableEmptyCollection() {
        return this.unfiltered instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList();
    }

    @Override
    public void clear() {
        this.entries().clear();
    }

    @Override
    public Collection<V> get(K key) {
        return FilteredEntryMultimap.filterCollection(this.unfiltered.get(key), new ValuePredicate(key));
    }

    @Override
    Collection<Map.Entry<K, V>> createEntries() {
        return FilteredEntryMultimap.filterCollection(this.unfiltered.entries(), this.predicate);
    }

    @Override
    Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    @Override
    Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    Map<K, Collection<V>> createAsMap() {
        return new AsMap();
    }

    @Override
    Set<K> createKeySet() {
        return this.asMap().keySet();
    }

    boolean removeEntriesIf(Predicate<? super Map.Entry<K, Collection<V>>> predicate) {
        Iterator<Map.Entry<K, Collection<V>>> entryIterator = this.unfiltered.asMap().entrySet().iterator();
        boolean changed = false;
        while (entryIterator.hasNext()) {
            Map.Entry<K, Collection<V>> entry = entryIterator.next();
            K key = entry.getKey();
            Collection<V> collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
            if (collection.isEmpty() || !predicate.apply(Maps.immutableEntry(key, collection))) continue;
            if (collection.size() == entry.getValue().size()) {
                entryIterator.remove();
            } else {
                collection.clear();
            }
            changed = true;
        }
        return changed;
    }

    @Override
    Multiset<K> createKeys() {
        return new Keys();
    }

    class Keys
    extends Multimaps.Keys<K, V> {
        Keys() {
            super(FilteredEntryMultimap.this);
        }

        @Override
        public int remove(@Nullable Object key, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return this.count(key);
            }
            Collection collection = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (collection == null) {
                return 0;
            }
            Object k = key;
            int oldCount = 0;
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                Object v = itr.next();
                if (!FilteredEntryMultimap.this.satisfies(k, v) || ++oldCount > occurrences) continue;
                itr.remove();
            }
            return oldCount;
        }

        @Override
        public Set<Multiset.Entry<K>> entrySet() {
            return new Multisets.EntrySet<K>(){

                @Override
                Multiset<K> multiset() {
                    return Keys.this;
                }

                @Override
                public Iterator<Multiset.Entry<K>> iterator() {
                    return Keys.this.entryIterator();
                }

                @Override
                public int size() {
                    return FilteredEntryMultimap.this.keySet().size();
                }

                private boolean removeEntriesIf(final Predicate<? super Multiset.Entry<K>> predicate) {
                    return FilteredEntryMultimap.this.removeEntriesIf(new Predicate<Map.Entry<K, Collection<V>>>(){

                        @Override
                        public boolean apply(Map.Entry<K, Collection<V>> entry) {
                            return predicate.apply(Multisets.immutableEntry(entry.getKey(), entry.getValue().size()));
                        }
                    });
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return this.removeEntriesIf(Predicates.in(c));
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return this.removeEntriesIf(Predicates.not(Predicates.in(c)));
                }
            };
        }
    }

    class AsMap
    extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        AsMap() {
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return this.get(key) != null;
        }

        @Override
        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        @Override
        public Collection<V> get(@Nullable Object key) {
            Collection result = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (result == null) {
                return null;
            }
            Object k = key;
            return (result = FilteredEntryMultimap.filterCollection(result, new ValuePredicate(k))).isEmpty() ? null : result;
        }

        @Override
        public Collection<V> remove(@Nullable Object key) {
            Collection collection = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (collection == null) {
                return null;
            }
            Object k = key;
            ArrayList result = Lists.newArrayList();
            Iterator itr = collection.iterator();
            while (itr.hasNext()) {
                Object v = itr.next();
                if (!FilteredEntryMultimap.this.satisfies(k, v)) continue;
                itr.remove();
                result.add(v);
            }
            if (result.isEmpty()) {
                return null;
            }
            if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(result));
            }
            return Collections.unmodifiableList(result);
        }

        @Override
        Set<K> createKeySet() {
            class KeySetImpl
            extends Maps.KeySet<K, Collection<V>> {
                KeySetImpl() {
                    super(AsMap.this);
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(c)));
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
                }

                @Override
                public boolean remove(@Nullable Object o) {
                    return AsMap.this.remove(o) != null;
                }
            }
            return new KeySetImpl();
        }

        @Override
        Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            class EntrySetImpl
            extends Maps.EntrySet<K, Collection<V>> {
                EntrySetImpl() {
                }

                @Override
                Map<K, Collection<V>> map() {
                    return AsMap.this;
                }

                @Override
                public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                    return new AbstractIterator<Map.Entry<K, Collection<V>>>(){
                        final Iterator<Map.Entry<K, Collection<V>>> backingIterator;
                        {
                            this.backingIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                        }

                        @Override
                        protected Map.Entry<K, Collection<V>> computeNext() {
                            while (this.backingIterator.hasNext()) {
                                Map.Entry entry = this.backingIterator.next();
                                Object key = entry.getKey();
                                Collection collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
                                if (collection.isEmpty()) continue;
                                return Maps.immutableEntry(key, collection);
                            }
                            return (Map.Entry)this.endOfData();
                        }
                    };
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(c));
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(c)));
                }

                @Override
                public int size() {
                    return Iterators.size(this.iterator());
                }
            }
            return new EntrySetImpl();
        }

        @Override
        Collection<Collection<V>> createValues() {
            class ValuesImpl
            extends Maps.Values<K, Collection<V>> {
                ValuesImpl() {
                    super(AsMap.this);
                }

                @Override
                public boolean remove(@Nullable Object o) {
                    if (o instanceof Collection) {
                        Collection c = (Collection)o;
                        Iterator entryIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                        while (entryIterator.hasNext()) {
                            Map.Entry entry = entryIterator.next();
                            Object key = entry.getKey();
                            Collection collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
                            if (collection.isEmpty() || !c.equals(collection)) continue;
                            if (collection.size() == entry.getValue().size()) {
                                entryIterator.remove();
                            } else {
                                collection.clear();
                            }
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
                }
            }
            return new ValuesImpl();
        }
    }

    final class ValuePredicate
    implements Predicate<V> {
        private final K key;

        ValuePredicate(K key) {
            this.key = key;
        }

        @Override
        public boolean apply(@Nullable V value) {
            return FilteredEntryMultimap.this.satisfies(this.key, value);
        }
    }
}

