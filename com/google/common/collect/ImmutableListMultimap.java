/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.EmptyImmutableListMultimap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public class ImmutableListMultimap<K, V>
extends ImmutableMultimap<K, V>
implements ListMultimap<K, V> {
    @LazyInit
    @RetainedWith
    private transient ImmutableListMultimap<V, K> inverse;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> toImmutableListMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction, "keyFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        return Collector.of(ImmutableListMultimap::builder, (builder, t) -> builder.put(keyFunction.apply(t), valueFunction.apply(t)), Builder::combine, Builder::build, new Collector.Characteristics[0]);
    }

    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> flatteningToImmutableListMultimap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends Stream<? extends V>> valuesFunction) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valuesFunction);
        return Collectors.collectingAndThen(Multimaps.flatteningToMultimap(input -> Preconditions.checkNotNull(keyFunction.apply(input)), input -> ((Stream)valuesFunction.apply(input)).peek(Preconditions::checkNotNull), MultimapBuilder.linkedHashKeys().arrayListValues()::build), ImmutableListMultimap::copyOf);
    }

    public static <K, V> ImmutableListMultimap<K, V> of() {
        return EmptyImmutableListMultimap.INSTANCE;
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k1, V v1) {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        builder.put((Object)k1, (Object)v1);
        return builder.build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k1, V v1, K k2, V v2) {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        builder.put((Object)k1, (Object)v1);
        builder.put((Object)k2, (Object)v2);
        return builder.build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        builder.put((Object)k1, (Object)v1);
        builder.put((Object)k2, (Object)v2);
        builder.put((Object)k3, (Object)v3);
        return builder.build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        builder.put((Object)k1, (Object)v1);
        builder.put((Object)k2, (Object)v2);
        builder.put((Object)k3, (Object)v3);
        builder.put((Object)k4, (Object)v4);
        return builder.build();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        builder.put((Object)k1, (Object)v1);
        builder.put((Object)k2, (Object)v2);
        builder.put((Object)k3, (Object)v3);
        builder.put((Object)k4, (Object)v4);
        builder.put((Object)k5, (Object)v5);
        return builder.build();
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    public static <K, V> ImmutableListMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        ImmutableListMultimap kvMultimap;
        if (multimap.isEmpty()) {
            return ImmutableListMultimap.of();
        }
        if (multimap instanceof ImmutableListMultimap && !(kvMultimap = (ImmutableListMultimap)multimap).isPartialView()) {
            return kvMultimap;
        }
        return ImmutableListMultimap.fromMapEntries(multimap.asMap().entrySet(), null);
    }

    @Beta
    public static <K, V> ImmutableListMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
        return ((Builder)new Builder().putAll((Iterable)entries)).build();
    }

    static <K, V> ImmutableListMultimap<K, V> fromMapEntries(Collection<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> mapEntries, @Nullable Comparator<? super V> valueComparator) {
        if (mapEntries.isEmpty()) {
            return ImmutableListMultimap.of();
        }
        ImmutableMap.Builder<K, ImmutableList<V>> builder = new ImmutableMap.Builder<K, ImmutableList<V>>(mapEntries.size());
        int size = 0;
        for (Map.Entry<K, Collection<V>> entry : mapEntries) {
            K key = entry.getKey();
            Collection<? extends V> values = entry.getValue();
            ImmutableList<Object> list = valueComparator == null ? ImmutableList.copyOf(values) : ImmutableList.sortedCopyOf(valueComparator, values);
            if (list.isEmpty()) continue;
            builder.put(key, list);
            size += list.size();
        }
        return new ImmutableListMultimap(builder.build(), size);
    }

    ImmutableListMultimap(ImmutableMap<K, ImmutableList<V>> map, int size) {
        super(map, size);
    }

    @Override
    public ImmutableList<V> get(@Nullable K key) {
        ImmutableList list = (ImmutableList)this.map.get(key);
        return list == null ? ImmutableList.of() : list;
    }

    @Override
    public ImmutableListMultimap<V, K> inverse() {
        ImmutableListMultimap<K, V> result = this.inverse;
        return result == null ? (this.inverse = this.invert()) : result;
    }

    private ImmutableListMultimap<V, K> invert() {
        Builder<K, V> builder = ImmutableListMultimap.builder();
        for (Map.Entry entry : this.entries()) {
            builder.put(entry.getValue(), entry.getKey());
        }
        ImmutableMultimap invertedMultimap = builder.build();
        ((ImmutableListMultimap)invertedMultimap).inverse = this;
        return invertedMultimap;
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableList<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableList<V> replaceValues(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMultimap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        ImmutableMap tmpMap;
        stream.defaultReadObject();
        int keyCount = stream.readInt();
        if (keyCount < 0) {
            throw new InvalidObjectException("Invalid key count " + keyCount);
        }
        ImmutableMap.Builder<Object, ImmutableCollection> builder = ImmutableMap.builder();
        int tmpSize = 0;
        for (int i = 0; i < keyCount; ++i) {
            Object key = stream.readObject();
            int valueCount = stream.readInt();
            if (valueCount <= 0) {
                throw new InvalidObjectException("Invalid value count " + valueCount);
            }
            ImmutableList.Builder valuesBuilder = ImmutableList.builder();
            for (int j = 0; j < valueCount; ++j) {
                valuesBuilder.add(stream.readObject());
            }
            builder.put(key, valuesBuilder.build());
            tmpSize += valueCount;
        }
        try {
            tmpMap = builder.build();
        } catch (IllegalArgumentException e) {
            throw (InvalidObjectException)new InvalidObjectException(e.getMessage()).initCause(e);
        }
        ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set((ImmutableMultimap)this, tmpMap);
        ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set((ImmutableMultimap)this, tmpSize);
    }

    public static final class Builder<K, V>
    extends ImmutableMultimap.Builder<K, V> {
        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> put(K key, V value) {
            super.put(key, value);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put(entry);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
            super.putAll(entries);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, Iterable<? extends V> values) {
            super.putAll(key, values);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, V ... values) {
            super.putAll(key, values);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            super.putAll(multimap);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        Builder<K, V> combine(ImmutableMultimap.Builder<K, V> other) {
            super.combine(other);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
            super.orderKeysBy(keyComparator);
            return this;
        }

        @Override
        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
            super.orderValuesBy(valueComparator);
            return this;
        }

        @Override
        public ImmutableListMultimap<K, V> build() {
            return (ImmutableListMultimap)super.build();
        }
    }
}

