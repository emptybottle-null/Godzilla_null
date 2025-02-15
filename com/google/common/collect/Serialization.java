/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
final class Serialization {
    private Serialization() {
    }

    static int readCount(ObjectInputStream stream) throws IOException {
        return stream.readInt();
    }

    static <K, V> void writeMap(Map<K, V> map, ObjectOutputStream stream) throws IOException {
        stream.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    static <K, V> void populateMap(Map<K, V> map, ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int size = stream.readInt();
        Serialization.populateMap(map, stream, size);
    }

    static <K, V> void populateMap(Map<K, V> map, ObjectInputStream stream, int size) throws IOException, ClassNotFoundException {
        for (int i = 0; i < size; ++i) {
            Object key = stream.readObject();
            Object value = stream.readObject();
            map.put(key, value);
        }
    }

    static <E> void writeMultiset(Multiset<E> multiset, ObjectOutputStream stream) throws IOException {
        int entryCount = multiset.entrySet().size();
        stream.writeInt(entryCount);
        for (Multiset.Entry<E> entry : multiset.entrySet()) {
            stream.writeObject(entry.getElement());
            stream.writeInt(entry.getCount());
        }
    }

    static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int distinctElements = stream.readInt();
        Serialization.populateMultiset(multiset, stream, distinctElements);
    }

    static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream stream, int distinctElements) throws IOException, ClassNotFoundException {
        for (int i = 0; i < distinctElements; ++i) {
            Object element = stream.readObject();
            int count = stream.readInt();
            multiset.add(element, count);
        }
    }

    static <K, V> void writeMultimap(Multimap<K, V> multimap, ObjectOutputStream stream) throws IOException {
        stream.writeInt(multimap.asMap().size());
        for (Map.Entry<K, Collection<V>> entry : multimap.asMap().entrySet()) {
            stream.writeObject(entry.getKey());
            stream.writeInt(entry.getValue().size());
            for (V value : entry.getValue()) {
                stream.writeObject(value);
            }
        }
    }

    static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int distinctKeys = stream.readInt();
        Serialization.populateMultimap(multimap, stream, distinctKeys);
    }

    static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream stream, int distinctKeys) throws IOException, ClassNotFoundException {
        for (int i = 0; i < distinctKeys; ++i) {
            Object key = stream.readObject();
            Collection<V> values = multimap.get(key);
            int valueCount = stream.readInt();
            for (int j = 0; j < valueCount; ++j) {
                Object value = stream.readObject();
                values.add(value);
            }
        }
    }

    static <T> FieldSetter<T> getFieldSetter(Class<T> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return new FieldSetter(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError((Object)e);
        }
    }

    static final class FieldSetter<T> {
        private final Field field;

        private FieldSetter(Field field) {
            this.field = field;
            field.setAccessible(true);
        }

        void set(T instance, Object value) {
            try {
                this.field.set(instance, value);
            } catch (IllegalAccessException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        void set(T instance, int value) {
            try {
                this.field.set(instance, value);
            } catch (IllegalAccessException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }
    }
}

