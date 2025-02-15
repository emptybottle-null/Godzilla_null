/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableAsList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
abstract class ImmutableMapEntrySet<K, V>
extends ImmutableSet<Map.Entry<K, V>> {
    ImmutableMapEntrySet() {
    }

    abstract ImmutableMap<K, V> map();

    @Override
    public int size() {
        return this.map().size();
    }

    @Override
    public boolean contains(@Nullable Object object) {
        if (object instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry)object;
            V value = this.map().get(entry.getKey());
            return value != null && value.equals(entry.getValue());
        }
        return false;
    }

    @Override
    boolean isPartialView() {
        return this.map().isPartialView();
    }

    @Override
    @GwtIncompatible
    boolean isHashCodeFast() {
        return this.map().isHashCodeFast();
    }

    @Override
    public int hashCode() {
        return this.map().hashCode();
    }

    @Override
    @GwtIncompatible
    Object writeReplace() {
        return new EntrySetSerializedForm<K, V>(this.map());
    }

    @GwtIncompatible
    private static class EntrySetSerializedForm<K, V>
    implements Serializable {
        final ImmutableMap<K, V> map;
        private static final long serialVersionUID = 0L;

        EntrySetSerializedForm(ImmutableMap<K, V> map) {
            this.map = map;
        }

        Object readResolve() {
            return this.map.entrySet();
        }
    }

    static final class RegularEntrySet<K, V>
    extends ImmutableMapEntrySet<K, V> {
        @Weak
        private final transient ImmutableMap<K, V> map;
        private final transient ImmutableList<Map.Entry<K, V>> entries;

        RegularEntrySet(ImmutableMap<K, V> map, Map.Entry<K, V>[] entries) {
            this(map, ImmutableList.asImmutableList(entries));
        }

        RegularEntrySet(ImmutableMap<K, V> map, ImmutableList<Map.Entry<K, V>> entries) {
            this.map = map;
            this.entries = entries;
        }

        @Override
        ImmutableMap<K, V> map() {
            return this.map;
        }

        @Override
        @GwtIncompatible(value="not used in GWT")
        int copyIntoArray(Object[] dst, int offset) {
            return this.entries.copyIntoArray(dst, offset);
        }

        @Override
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.entries.iterator();
        }

        @Override
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return this.entries.spliterator();
        }

        @Override
        public void forEach(Consumer<? super Map.Entry<K, V>> action) {
            this.entries.forEach((Consumer<Map.Entry<K, V>>)action);
        }

        @Override
        ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList<Map.Entry<K, V>>(this, this.entries);
        }
    }
}

