/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractBiMap;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class EnumHashBiMap<K extends Enum<K>, V>
extends AbstractBiMap<K, V> {
    private transient Class<K> keyType;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K extends Enum<K>, V> EnumHashBiMap<K, V> create(Class<K> keyType) {
        return new EnumHashBiMap<K, V>(keyType);
    }

    public static <K extends Enum<K>, V> EnumHashBiMap<K, V> create(Map<K, ? extends V> map) {
        EnumHashBiMap<K, V> bimap = EnumHashBiMap.create(EnumBiMap.inferKeyType(map));
        bimap.putAll((Map)map);
        return bimap;
    }

    private EnumHashBiMap(Class<K> keyType) {
        super(new EnumMap(keyType), Maps.newHashMapWithExpectedSize(((Enum[])keyType.getEnumConstants()).length));
        this.keyType = keyType;
    }

    @Override
    K checkKey(K key) {
        return (K)((Enum)Preconditions.checkNotNull(key));
    }

    @Override
    @CanIgnoreReturnValue
    public V put(K key, @Nullable V value) {
        return super.put(key, value);
    }

    @Override
    @CanIgnoreReturnValue
    public V forcePut(K key, @Nullable V value) {
        return super.forcePut(key, value);
    }

    public Class<K> keyType() {
        return this.keyType;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.keyType);
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyType = (Class)stream.readObject();
        this.setDelegates(new EnumMap(this.keyType), new HashMap(((Enum[])this.keyType.getEnumConstants()).length * 3 / 2));
        Serialization.populateMap(this, stream);
    }
}

