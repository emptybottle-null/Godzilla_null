/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public interface Table<R, C, V> {
    public boolean contains(@CompatibleWith(value="R") @Nullable Object var1, @CompatibleWith(value="C") @Nullable Object var2);

    public boolean containsRow(@CompatibleWith(value="R") @Nullable Object var1);

    public boolean containsColumn(@CompatibleWith(value="C") @Nullable Object var1);

    public boolean containsValue(@CompatibleWith(value="V") @Nullable Object var1);

    public V get(@CompatibleWith(value="R") @Nullable Object var1, @CompatibleWith(value="C") @Nullable Object var2);

    public boolean isEmpty();

    public int size();

    public boolean equals(@Nullable Object var1);

    public int hashCode();

    public void clear();

    @CanIgnoreReturnValue
    public @Nullable V put(R var1, C var2, V var3);

    public void putAll(Table<? extends R, ? extends C, ? extends V> var1);

    @CanIgnoreReturnValue
    public @Nullable V remove(@CompatibleWith(value="R") @Nullable Object var1, @CompatibleWith(value="C") @Nullable Object var2);

    public Map<C, V> row(R var1);

    public Map<R, V> column(C var1);

    public Set<Cell<R, C, V>> cellSet();

    public Set<R> rowKeySet();

    public Set<C> columnKeySet();

    public Collection<V> values();

    public Map<R, Map<C, V>> rowMap();

    public Map<C, Map<R, V>> columnMap();

    public static interface Cell<R, C, V> {
        public @Nullable R getRowKey();

        public @Nullable C getColumnKey();

        public @Nullable V getValue();

        public boolean equals(@Nullable Object var1);

        public int hashCode();
    }
}

