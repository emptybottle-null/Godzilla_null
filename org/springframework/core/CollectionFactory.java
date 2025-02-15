/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.springframework.core.SortedProperties;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

public final class CollectionFactory {
    private static final Set<Class<?>> approximableCollectionTypes = new HashSet();
    private static final Set<Class<?>> approximableMapTypes = new HashSet();

    private CollectionFactory() {
    }

    public static boolean isApproximableCollectionType(@Nullable Class<?> collectionType) {
        return collectionType != null && approximableCollectionTypes.contains(collectionType);
    }

    public static <E> Collection<E> createApproximateCollection(@Nullable Object collection, int capacity) {
        if (collection instanceof LinkedList) {
            return new LinkedList();
        }
        if (collection instanceof List) {
            return new ArrayList(capacity);
        }
        if (collection instanceof EnumSet) {
            EnumSet enumSet = EnumSet.copyOf((EnumSet)collection);
            enumSet.clear();
            return enumSet;
        }
        if (collection instanceof SortedSet) {
            return new TreeSet(((SortedSet)collection).comparator());
        }
        return new LinkedHashSet(capacity);
    }

    public static <E> Collection<E> createCollection(Class<?> collectionType, int capacity) {
        return CollectionFactory.createCollection(collectionType, null, capacity);
    }

    public static <E> Collection<E> createCollection(Class<?> collectionType, @Nullable Class<?> elementType, int capacity) {
        Assert.notNull(collectionType, "Collection type must not be null");
        if (collectionType.isInterface()) {
            if (Set.class == collectionType || Collection.class == collectionType) {
                return new LinkedHashSet(capacity);
            }
            if (List.class == collectionType) {
                return new ArrayList(capacity);
            }
            if (SortedSet.class == collectionType || NavigableSet.class == collectionType) {
                return new TreeSet();
            }
            throw new IllegalArgumentException("Unsupported Collection interface: " + collectionType.getName());
        }
        if (EnumSet.class.isAssignableFrom(collectionType)) {
            Assert.notNull(elementType, "Cannot create EnumSet for unknown element type");
            return EnumSet.noneOf(CollectionFactory.asEnumType(elementType));
        }
        if (!Collection.class.isAssignableFrom(collectionType)) {
            throw new IllegalArgumentException("Unsupported Collection type: " + collectionType.getName());
        }
        try {
            return (Collection)ReflectionUtils.accessibleConstructor(collectionType, new Class[0]).newInstance(new Object[0]);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Could not instantiate Collection type: " + collectionType.getName(), ex);
        }
    }

    public static boolean isApproximableMapType(@Nullable Class<?> mapType) {
        return mapType != null && approximableMapTypes.contains(mapType);
    }

    public static <K, V> Map<K, V> createApproximateMap(@Nullable Object map, int capacity) {
        if (map instanceof EnumMap) {
            EnumMap enumMap = new EnumMap((EnumMap)map);
            enumMap.clear();
            return enumMap;
        }
        if (map instanceof SortedMap) {
            return new TreeMap(((SortedMap)map).comparator());
        }
        return new LinkedHashMap(capacity);
    }

    public static <K, V> Map<K, V> createMap(Class<?> mapType, int capacity) {
        return CollectionFactory.createMap(mapType, null, capacity);
    }

    public static <K, V> Map<K, V> createMap(Class<?> mapType, @Nullable Class<?> keyType, int capacity) {
        Assert.notNull(mapType, "Map type must not be null");
        if (mapType.isInterface()) {
            if (Map.class == mapType) {
                return new LinkedHashMap(capacity);
            }
            if (SortedMap.class == mapType || NavigableMap.class == mapType) {
                return new TreeMap();
            }
            if (MultiValueMap.class == mapType) {
                return new LinkedMultiValueMap();
            }
            throw new IllegalArgumentException("Unsupported Map interface: " + mapType.getName());
        }
        if (EnumMap.class == mapType) {
            Assert.notNull(keyType, "Cannot create EnumMap for unknown key type");
            return new EnumMap(CollectionFactory.asEnumType(keyType));
        }
        if (!Map.class.isAssignableFrom(mapType)) {
            throw new IllegalArgumentException("Unsupported Map type: " + mapType.getName());
        }
        try {
            return (Map)ReflectionUtils.accessibleConstructor(mapType, new Class[0]).newInstance(new Object[0]);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Could not instantiate Map type: " + mapType.getName(), ex);
        }
    }

    public static Properties createStringAdaptingProperties() {
        return new SortedProperties(false){

            @Override
            @Nullable
            public String getProperty(String key) {
                Object value = this.get(key);
                return value != null ? value.toString() : null;
            }
        };
    }

    public static Properties createSortedProperties(boolean omitComments) {
        return new SortedProperties(omitComments);
    }

    public static Properties createSortedProperties(Properties properties, boolean omitComments) {
        return new SortedProperties(properties, omitComments);
    }

    private static Class<? extends Enum> asEnumType(Class<?> enumType) {
        Assert.notNull(enumType, "Enum type must not be null");
        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new IllegalArgumentException("Supplied type is not an enum: " + enumType.getName());
        }
        return enumType.asSubclass(Enum.class);
    }

    static {
        approximableCollectionTypes.add(Collection.class);
        approximableCollectionTypes.add(List.class);
        approximableCollectionTypes.add(Set.class);
        approximableCollectionTypes.add(SortedSet.class);
        approximableCollectionTypes.add(NavigableSet.class);
        approximableMapTypes.add(Map.class);
        approximableMapTypes.add(SortedMap.class);
        approximableMapTypes.add(NavigableMap.class);
        approximableCollectionTypes.add(ArrayList.class);
        approximableCollectionTypes.add(LinkedList.class);
        approximableCollectionTypes.add(HashSet.class);
        approximableCollectionTypes.add(LinkedHashSet.class);
        approximableCollectionTypes.add(TreeSet.class);
        approximableCollectionTypes.add(EnumSet.class);
        approximableMapTypes.add(HashMap.class);
        approximableMapTypes.add(LinkedHashMap.class);
        approximableMapTypes.add(TreeMap.class);
        approximableMapTypes.add(EnumMap.class);
    }
}

