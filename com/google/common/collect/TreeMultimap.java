/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractSortedKeySortedSetMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Serialization;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
public class TreeMultimap<K, V>
extends AbstractSortedKeySortedSetMultimap<K, V> {
    private transient Comparator<? super K> keyComparator;
    private transient Comparator<? super V> valueComparator;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> create() {
        return new TreeMultimap(Ordering.natural(), Ordering.natural());
    }

    public static <K, V> TreeMultimap<K, V> create(Comparator<? super K> keyComparator, Comparator<? super V> valueComparator) {
        return new TreeMultimap<K, V>(Preconditions.checkNotNull(keyComparator), Preconditions.checkNotNull(valueComparator));
    }

    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new TreeMultimap<K, V>(Ordering.natural(), Ordering.natural(), multimap);
    }

    TreeMultimap(Comparator<? super K> keyComparator, Comparator<? super V> valueComparator) {
        super(new TreeMap(keyComparator));
        this.keyComparator = keyComparator;
        this.valueComparator = valueComparator;
    }

    private TreeMultimap(Comparator<? super K> keyComparator, Comparator<? super V> valueComparator, Multimap<? extends K, ? extends V> multimap) {
        this(keyComparator, valueComparator);
        this.putAll((Multimap)multimap);
    }

    @Override
    Map<K, Collection<V>> createAsMap() {
        return this.createMaybeNavigableAsMap();
    }

    @Override
    SortedSet<V> createCollection() {
        return new TreeSet<V>(this.valueComparator);
    }

    @Override
    Collection<V> createCollection(@Nullable K key) {
        if (key == null) {
            this.keyComparator().compare(key, key);
        }
        return super.createCollection(key);
    }

    @Deprecated
    public Comparator<? super K> keyComparator() {
        return this.keyComparator;
    }

    @Override
    public Comparator<? super V> valueComparator() {
        return this.valueComparator;
    }

    @Override
    @GwtIncompatible
    public NavigableSet<V> get(@Nullable K key) {
        return (NavigableSet)super.get((Object)key);
    }

    @Override
    public NavigableSet<K> keySet() {
        return (NavigableSet)super.keySet();
    }

    @Override
    public NavigableMap<K, Collection<V>> asMap() {
        return (NavigableMap)super.asMap();
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.keyComparator());
        stream.writeObject(this.valueComparator());
        Serialization.writeMultimap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyComparator = Preconditions.checkNotNull((Comparator)stream.readObject());
        this.valueComparator = Preconditions.checkNotNull((Comparator)stream.readObject());
        this.setMap(new TreeMap(this.keyComparator));
        Serialization.populateMultimap(this, stream);
    }
}

