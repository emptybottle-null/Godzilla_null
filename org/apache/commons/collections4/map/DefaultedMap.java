/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.FactoryTransformer;
import org.apache.commons.collections4.map.AbstractMapDecorator;

public class DefaultedMap<K, V>
extends AbstractMapDecorator<K, V>
implements Serializable {
    private static final long serialVersionUID = 19698628745827L;
    private final Transformer<? super K, ? extends V> value;

    public static <K, V> DefaultedMap<K, V> defaultedMap(Map<K, V> map, V defaultValue) {
        return new DefaultedMap<K, V>(map, ConstantTransformer.constantTransformer(defaultValue));
    }

    public static <K, V> DefaultedMap<K, V> defaultedMap(Map<K, V> map, Factory<? extends V> factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        return new DefaultedMap<K, V>(map, FactoryTransformer.factoryTransformer(factory));
    }

    public static <K, V> Map<K, V> defaultedMap(Map<K, V> map, Transformer<? super K, ? extends V> transformer) {
        if (transformer == null) {
            throw new IllegalArgumentException("Transformer must not be null");
        }
        return new DefaultedMap<K, V>(map, transformer);
    }

    public DefaultedMap(V defaultValue) {
        this(ConstantTransformer.constantTransformer(defaultValue));
    }

    public DefaultedMap(Transformer<? super K, ? extends V> defaultValueTransformer) {
        this(new HashMap(), defaultValueTransformer);
    }

    protected DefaultedMap(Map<K, V> map, Transformer<? super K, ? extends V> defaultValueTransformer) {
        super(map);
        if (defaultValueTransformer == null) {
            throw new NullPointerException("Transformer must not be null.");
        }
        this.value = defaultValueTransformer;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }

    @Override
    public V get(Object key) {
        Object v = this.map.get(key);
        return v != null || this.map.containsKey(key) ? v : this.value.transform(key);
    }
}

