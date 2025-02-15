/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractBiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.Serialization;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.Map;

@GwtCompatible(emulated=true)
public final class EnumBiMap<K extends Enum<K>, V extends Enum<V>>
extends AbstractBiMap<K, V> {
    private transient Class<K> keyType;
    private transient Class<V> valueType;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K extends Enum<K>, V extends Enum<V>> EnumBiMap<K, V> create(Class<K> keyType, Class<V> valueType) {
        return new EnumBiMap<K, V>(keyType, valueType);
    }

    public static <K extends Enum<K>, V extends Enum<V>> EnumBiMap<K, V> create(Map<K, V> map) {
        EnumBiMap<K, V> bimap = EnumBiMap.create(EnumBiMap.inferKeyType(map), EnumBiMap.inferValueType(map));
        bimap.putAll((Map)map);
        return bimap;
    }

    private EnumBiMap(Class<K> keyType, Class<V> valueType) {
        super(new EnumMap(keyType), new EnumMap(valueType));
        this.keyType = keyType;
        this.valueType = valueType;
    }

    static <K extends Enum<K>> Class<K> inferKeyType(Map<K, ?> map) {
        if (map instanceof EnumBiMap) {
            return ((EnumBiMap)map).keyType();
        }
        if (map instanceof EnumHashBiMap) {
            return ((EnumHashBiMap)map).keyType();
        }
        Preconditions.checkArgument(!map.isEmpty());
        return ((Enum)map.keySet().iterator().next()).getDeclaringClass();
    }

    private static <V extends Enum<V>> Class<V> inferValueType(Map<?, V> map) {
        if (map instanceof EnumBiMap) {
            return ((EnumBiMap)map).valueType;
        }
        Preconditions.checkArgument(!map.isEmpty());
        return ((Enum)map.values().iterator().next()).getDeclaringClass();
    }

    public Class<K> keyType() {
        return this.keyType;
    }

    public Class<V> valueType() {
        return this.valueType;
    }

    @Override
    K checkKey(K key) {
        return (K)((Enum)Preconditions.checkNotNull(key));
    }

    @Override
    V checkValue(V value) {
        return (V)((Enum)Preconditions.checkNotNull(value));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.keyType);
        stream.writeObject(this.valueType);
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyType = (Class)stream.readObject();
        this.valueType = (Class)stream.readObject();
        this.setDelegates(new EnumMap(this.keyType), new EnumMap(this.valueType));
        Serialization.populateMap(this, stream);
    }
}

