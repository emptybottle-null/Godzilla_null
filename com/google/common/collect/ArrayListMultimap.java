/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ArrayListMultimapGwtSerializationDependencies;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Platform;
import com.google.common.collect.Serialization;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@GwtCompatible(serializable=true, emulated=true)
public final class ArrayListMultimap<K, V>
extends ArrayListMultimapGwtSerializationDependencies<K, V> {
    private static final int DEFAULT_VALUES_PER_KEY = 3;
    @VisibleForTesting
    transient int expectedValuesPerKey;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K, V> ArrayListMultimap<K, V> create() {
        return new ArrayListMultimap<K, V>();
    }

    public static <K, V> ArrayListMultimap<K, V> create(int expectedKeys, int expectedValuesPerKey) {
        return new ArrayListMultimap<K, V>(expectedKeys, expectedValuesPerKey);
    }

    public static <K, V> ArrayListMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new ArrayListMultimap<K, V>(multimap);
    }

    private ArrayListMultimap() {
        this(12, 3);
    }

    private ArrayListMultimap(int expectedKeys, int expectedValuesPerKey) {
        super(Platform.newHashMapWithExpectedSize(expectedKeys));
        CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
        this.expectedValuesPerKey = expectedValuesPerKey;
    }

    private ArrayListMultimap(Multimap<? extends K, ? extends V> multimap) {
        this(multimap.keySet().size(), multimap instanceof ArrayListMultimap ? ((ArrayListMultimap)multimap).expectedValuesPerKey : 3);
        this.putAll((Multimap)multimap);
    }

    @Override
    List<V> createCollection() {
        return new ArrayList(this.expectedValuesPerKey);
    }

    @Deprecated
    public void trimToSize() {
        for (Collection collection : this.backingMap().values()) {
            ArrayList arrayList = (ArrayList)collection;
            arrayList.trimToSize();
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMultimap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.expectedValuesPerKey = 3;
        int distinctKeys = Serialization.readCount(stream);
        HashMap map = Maps.newHashMap();
        this.setMap(map);
        Serialization.populateMultimap(this, stream, distinctKeys);
    }
}

