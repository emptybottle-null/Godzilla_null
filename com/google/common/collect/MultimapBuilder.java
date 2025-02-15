/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Platform;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.SortedSetMultimap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

@GwtCompatible
public abstract class MultimapBuilder<K0, V0> {
    private static final int DEFAULT_EXPECTED_KEYS = 8;

    private MultimapBuilder() {
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        return MultimapBuilder.hashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> hashKeys(final int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>(){

            @Override
            <K, V> Map<K, Collection<V>> createMap() {
                return Platform.newHashMapWithExpectedSize(expectedKeys);
            }
        };
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys() {
        return MultimapBuilder.linkedHashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> linkedHashKeys(final int expectedKeys) {
        CollectPreconditions.checkNonnegative(expectedKeys, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>(){

            @Override
            <K, V> Map<K, Collection<V>> createMap() {
                return Platform.newLinkedHashMapWithExpectedSize(expectedKeys);
            }
        };
    }

    public static MultimapBuilderWithKeys<Comparable> treeKeys() {
        return MultimapBuilder.treeKeys(Ordering.natural());
    }

    public static <K0> MultimapBuilderWithKeys<K0> treeKeys(final Comparator<K0> comparator) {
        Preconditions.checkNotNull(comparator);
        return new MultimapBuilderWithKeys<K0>(){

            @Override
            <K extends K0, V> Map<K, Collection<V>> createMap() {
                return new TreeMap(comparator);
            }
        };
    }

    public static <K0 extends Enum<K0>> MultimapBuilderWithKeys<K0> enumKeys(final Class<K0> keyClass) {
        Preconditions.checkNotNull(keyClass);
        return new MultimapBuilderWithKeys<K0>(){

            @Override
            <K extends K0, V> Map<K, Collection<V>> createMap() {
                return new EnumMap(keyClass);
            }
        };
    }

    public abstract <K extends K0, V extends V0> Multimap<K, V> build();

    public <K extends K0, V extends V0> Multimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
        Multimap<? extends K, ? extends V> result = this.build();
        result.putAll(multimap);
        return result;
    }

    public static abstract class SortedSetMultimapBuilder<K0, V0>
    extends SetMultimapBuilder<K0, V0> {
        SortedSetMultimapBuilder() {
        }

        @Override
        public abstract <K extends K0, V extends V0> SortedSetMultimap<K, V> build();

        @Override
        public <K extends K0, V extends V0> SortedSetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SortedSetMultimap)super.build((Multimap)multimap);
        }
    }

    public static abstract class SetMultimapBuilder<K0, V0>
    extends MultimapBuilder<K0, V0> {
        SetMultimapBuilder() {
        }

        @Override
        public abstract <K extends K0, V extends V0> SetMultimap<K, V> build();

        @Override
        public <K extends K0, V extends V0> SetMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (SetMultimap)super.build(multimap);
        }
    }

    public static abstract class ListMultimapBuilder<K0, V0>
    extends MultimapBuilder<K0, V0> {
        ListMultimapBuilder() {
        }

        @Override
        public abstract <K extends K0, V extends V0> ListMultimap<K, V> build();

        @Override
        public <K extends K0, V extends V0> ListMultimap<K, V> build(Multimap<? extends K, ? extends V> multimap) {
            return (ListMultimap)super.build(multimap);
        }
    }

    public static abstract class MultimapBuilderWithKeys<K0> {
        private static final int DEFAULT_EXPECTED_VALUES_PER_KEY = 2;

        MultimapBuilderWithKeys() {
        }

        abstract <K extends K0, V> Map<K, Collection<V>> createMap();

        public ListMultimapBuilder<K0, Object> arrayListValues() {
            return this.arrayListValues(2);
        }

        public ListMultimapBuilder<K0, Object> arrayListValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new ListMultimapBuilder<K0, Object>(){

                @Override
                public <K extends K0, V> ListMultimap<K, V> build() {
                    return Multimaps.newListMultimap(this.createMap(), new ArrayListSupplier(expectedValuesPerKey));
                }
            };
        }

        public ListMultimapBuilder<K0, Object> linkedListValues() {
            return new ListMultimapBuilder<K0, Object>(){

                @Override
                public <K extends K0, V> ListMultimap<K, V> build() {
                    return Multimaps.newListMultimap(this.createMap(), LinkedListSupplier.instance());
                }
            };
        }

        public SetMultimapBuilder<K0, Object> hashSetValues() {
            return this.hashSetValues(2);
        }

        public SetMultimapBuilder<K0, Object> hashSetValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>(){

                @Override
                public <K extends K0, V> SetMultimap<K, V> build() {
                    return Multimaps.newSetMultimap(this.createMap(), new HashSetSupplier(expectedValuesPerKey));
                }
            };
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues() {
            return this.linkedHashSetValues(2);
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues(final int expectedValuesPerKey) {
            CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>(){

                @Override
                public <K extends K0, V> SetMultimap<K, V> build() {
                    return Multimaps.newSetMultimap(this.createMap(), new LinkedHashSetSupplier(expectedValuesPerKey));
                }
            };
        }

        public SortedSetMultimapBuilder<K0, Comparable> treeSetValues() {
            return this.treeSetValues(Ordering.natural());
        }

        public <V0> SortedSetMultimapBuilder<K0, V0> treeSetValues(final Comparator<V0> comparator) {
            Preconditions.checkNotNull(comparator, "comparator");
            return new SortedSetMultimapBuilder<K0, V0>(){

                @Override
                public <K extends K0, V extends V0> SortedSetMultimap<K, V> build() {
                    return Multimaps.newSortedSetMultimap(this.createMap(), new TreeSetSupplier(comparator));
                }
            };
        }

        public <V0 extends Enum<V0>> SetMultimapBuilder<K0, V0> enumSetValues(final Class<V0> valueClass) {
            Preconditions.checkNotNull(valueClass, "valueClass");
            return new SetMultimapBuilder<K0, V0>(){

                @Override
                public <K extends K0, V extends V0> SetMultimap<K, V> build() {
                    EnumSetSupplier factory = new EnumSetSupplier(valueClass);
                    return Multimaps.newSetMultimap(this.createMap(), factory);
                }
            };
        }
    }

    private static final class EnumSetSupplier<V extends Enum<V>>
    implements Supplier<Set<V>>,
    Serializable {
        private final Class<V> clazz;

        EnumSetSupplier(Class<V> clazz) {
            this.clazz = Preconditions.checkNotNull(clazz);
        }

        @Override
        public Set<V> get() {
            return EnumSet.noneOf(this.clazz);
        }
    }

    private static final class TreeSetSupplier<V>
    implements Supplier<SortedSet<V>>,
    Serializable {
        private final Comparator<? super V> comparator;

        TreeSetSupplier(Comparator<? super V> comparator) {
            this.comparator = Preconditions.checkNotNull(comparator);
        }

        @Override
        public SortedSet<V> get() {
            return new TreeSet<V>(this.comparator);
        }
    }

    private static final class LinkedHashSetSupplier<V>
    implements Supplier<Set<V>>,
    Serializable {
        private final int expectedValuesPerKey;

        LinkedHashSetSupplier(int expectedValuesPerKey) {
            this.expectedValuesPerKey = CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
        }

        @Override
        public Set<V> get() {
            return Platform.newLinkedHashSetWithExpectedSize(this.expectedValuesPerKey);
        }
    }

    private static final class HashSetSupplier<V>
    implements Supplier<Set<V>>,
    Serializable {
        private final int expectedValuesPerKey;

        HashSetSupplier(int expectedValuesPerKey) {
            this.expectedValuesPerKey = CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
        }

        @Override
        public Set<V> get() {
            return Platform.newHashSetWithExpectedSize(this.expectedValuesPerKey);
        }
    }

    private static enum LinkedListSupplier implements Supplier<List<Object>>
    {
        INSTANCE;


        public static <V> Supplier<List<V>> instance() {
            LinkedListSupplier result = INSTANCE;
            return result;
        }

        @Override
        public List<Object> get() {
            return new LinkedList<Object>();
        }
    }

    private static final class ArrayListSupplier<V>
    implements Supplier<List<V>>,
    Serializable {
        private final int expectedValuesPerKey;

        ArrayListSupplier(int expectedValuesPerKey) {
            this.expectedValuesPerKey = CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
        }

        @Override
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }
}

