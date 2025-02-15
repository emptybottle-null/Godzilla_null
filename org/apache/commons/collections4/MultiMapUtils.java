/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.SetValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multimap.TransformedMultiValuedMap;
import org.apache.commons.collections4.multimap.UnmodifiableMultiValuedMap;

public class MultiMapUtils {
    public static final MultiValuedMap EMPTY_MULTI_VALUED_MAP = UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(new ArrayListValuedHashMap(0, 0));

    private MultiMapUtils() {
    }

    public static <K, V> MultiValuedMap<K, V> emptyMultiValuedMap() {
        return EMPTY_MULTI_VALUED_MAP;
    }

    public static <K, V> MultiValuedMap<K, V> emptyIfNull(MultiValuedMap<K, V> map) {
        return map == null ? EMPTY_MULTI_VALUED_MAP : map;
    }

    public static boolean isEmpty(MultiValuedMap<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> Collection<V> getCollection(MultiValuedMap<K, V> map, K key) {
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    public static <K, V> List<V> getValuesAsList(MultiValuedMap<K, V> map, K key) {
        if (map != null) {
            Collection<V> col = map.get(key);
            if (col instanceof List) {
                return (List)col;
            }
            return new ArrayList<V>(col);
        }
        return null;
    }

    public static <K, V> Set<V> getValuesAsSet(MultiValuedMap<K, V> map, K key) {
        if (map != null) {
            Collection<V> col = map.get(key);
            if (col instanceof Set) {
                return (Set)col;
            }
            return new HashSet<V>(col);
        }
        return null;
    }

    public static <K, V> Bag<V> getValuesAsBag(MultiValuedMap<K, V> map, K key) {
        if (map != null) {
            Collection<V> col = map.get(key);
            if (col instanceof Bag) {
                return (Bag)col;
            }
            return new HashBag<V>(col);
        }
        return null;
    }

    public static <K, V> ListValuedMap<K, V> newListValuedHashMap() {
        return new ArrayListValuedHashMap();
    }

    public static <K, V> SetValuedMap<K, V> newSetValuedHashMap() {
        return new HashSetValuedHashMap();
    }

    public static <K, V> MultiValuedMap<K, V> unmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> map) {
        return UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(map);
    }

    public static <K, V> MultiValuedMap<K, V> transformedMultiValuedMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedMultiValuedMap.transformingMap(map, keyTransformer, valueTransformer);
    }
}

