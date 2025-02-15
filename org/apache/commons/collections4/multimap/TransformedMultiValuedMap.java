/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class TransformedMultiValuedMap<K, V>
extends AbstractMultiValuedMapDecorator<K, V> {
    private static final long serialVersionUID = 20150612L;
    private final Transformer<? super K, ? extends K> keyTransformer;
    private final Transformer<? super V, ? extends V> valueTransformer;

    public static <K, V> TransformedMultiValuedMap<K, V> transformingMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return new TransformedMultiValuedMap<K, V>(map, keyTransformer, valueTransformer);
    }

    public static <K, V> TransformedMultiValuedMap<K, V> transformedMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        TransformedMultiValuedMap<K, V> decorated = new TransformedMultiValuedMap<K, V>(map, keyTransformer, valueTransformer);
        if (!map.isEmpty()) {
            ArrayListValuedHashMap<K, V> mapCopy = new ArrayListValuedHashMap<K, V>(map);
            decorated.clear();
            decorated.putAll(mapCopy);
        }
        return decorated;
    }

    protected TransformedMultiValuedMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        super(map);
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
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

    @Override
    public boolean put(K key, V value) {
        return this.decorated().put(this.transformKey(key), this.transformValue(value));
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        if (values == null) {
            throw new NullPointerException("Values must not be null.");
        }
        FluentIterable<V> transformedValues = FluentIterable.of(values).transform(this.valueTransformer);
        Iterator it = transformedValues.iterator();
        return it.hasNext() && CollectionUtils.addAll(this.decorated().get(this.transformKey(key)), it);
    }

    @Override
    public boolean putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null.");
        }
        boolean changed = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            changed |= this.put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null.");
        }
        boolean changed = false;
        for (Map.Entry<K, V> entry : map.entries()) {
            changed |= this.put(entry.getKey(), entry.getValue());
        }
        return changed;
    }
}

