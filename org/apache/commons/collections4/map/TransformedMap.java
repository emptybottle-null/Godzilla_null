/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.AbstractInputCheckedMapDecorator;
import org.apache.commons.collections4.map.LinkedMap;

public class TransformedMap<K, V>
extends AbstractInputCheckedMapDecorator<K, V>
implements Serializable {
    private static final long serialVersionUID = 7023152376788900464L;
    protected final Transformer<? super K, ? extends K> keyTransformer;
    protected final Transformer<? super V, ? extends V> valueTransformer;

    public static <K, V> TransformedMap<K, V> transformingMap(Map<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return new TransformedMap<K, V>(map, keyTransformer, valueTransformer);
    }

    public static <K, V> TransformedMap<K, V> transformedMap(Map<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        TransformedMap<K, V> decorated = new TransformedMap<K, V>(map, keyTransformer, valueTransformer);
        if (map.size() > 0) {
            Map<? extends K, ? extends V> transformed = decorated.transformMap(map);
            decorated.clear();
            decorated.decorated().putAll(transformed);
        }
        return decorated;
    }

    protected TransformedMap(Map<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        super(map);
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }

    protected K transformKey(K object) {
        if (this.keyTransformer == null) {
            return object;
        }
        return this.keyTransformer.transform(object);
    }

    protected V transformValue(V object) {
        if (this.valueTransformer == null) {
            return object;
        }
        return this.valueTransformer.transform(object);
    }

    protected Map<K, V> transformMap(Map<? extends K, ? extends V> map) {
        if (map.isEmpty()) {
            return map;
        }
        LinkedMap<K, V> result = new LinkedMap<K, V>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.put(this.transformKey(entry.getKey()), this.transformValue(entry.getValue()));
        }
        return result;
    }

    @Override
    protected V checkSetValue(V value) {
        return this.valueTransformer.transform(value);
    }

    @Override
    protected boolean isSetValueChecking() {
        return this.valueTransformer != null;
    }

    @Override
    public V put(K key, V value) {
        key = this.transformKey(key);
        value = this.transformValue(value);
        return this.decorated().put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        mapToCopy = this.transformMap(mapToCopy);
        this.decorated().putAll(mapToCopy);
    }
}

