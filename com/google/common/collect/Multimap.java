/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface Multimap<K, V> {
    public int size();

    public boolean isEmpty();

    public boolean containsKey(@CompatibleWith(value="K") @Nullable Object var1);

    public boolean containsValue(@CompatibleWith(value="V") @Nullable Object var1);

    public boolean containsEntry(@CompatibleWith(value="K") @Nullable Object var1, @CompatibleWith(value="V") @Nullable Object var2);

    @CanIgnoreReturnValue
    public boolean put(@Nullable K var1, @Nullable V var2);

    @CanIgnoreReturnValue
    public boolean remove(@CompatibleWith(value="K") @Nullable Object var1, @CompatibleWith(value="V") @Nullable Object var2);

    @CanIgnoreReturnValue
    public boolean putAll(@Nullable K var1, Iterable<? extends V> var2);

    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> var1);

    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@Nullable K var1, Iterable<? extends V> var2);

    @CanIgnoreReturnValue
    public Collection<V> removeAll(@CompatibleWith(value="K") @Nullable Object var1);

    public void clear();

    public Collection<V> get(@Nullable K var1);

    public Set<K> keySet();

    public Multiset<K> keys();

    public Collection<V> values();

    public Collection<Map.Entry<K, V>> entries();

    default public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        this.entries().forEach((? super T entry) -> action.accept((Object)entry.getKey(), (Object)entry.getValue()));
    }

    public Map<K, Collection<V>> asMap();

    public boolean equals(@Nullable Object var1);

    public int hashCode();
}

