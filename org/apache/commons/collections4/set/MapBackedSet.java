/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapBackedSet<E, V>
implements Set<E>,
Serializable {
    private static final long serialVersionUID = 6723912213766056587L;
    private final Map<E, ? super V> map;
    private final V dummyValue;

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map) {
        return MapBackedSet.mapBackedSet(map, null);
    }

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map, V dummyValue) {
        return new MapBackedSet<E, V>(map, dummyValue);
    }

    private MapBackedSet(Map<E, ? super V> map, V dummyValue) {
        if (map == null) {
            throw new NullPointerException("The map must not be null");
        }
        this.map = map;
        this.dummyValue = dummyValue;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.map.keySet().containsAll(coll);
    }

    @Override
    public boolean add(E obj) {
        int size = this.map.size();
        this.map.put(obj, this.dummyValue);
        return this.map.size() != size;
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        int size = this.map.size();
        for (E e : coll) {
            this.map.put(e, this.dummyValue);
        }
        return this.map.size() != size;
    }

    @Override
    public boolean remove(Object obj) {
        int size = this.map.size();
        this.map.remove(obj);
        return this.map.size() != size;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return this.map.keySet().removeIf(filter);
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        return this.map.keySet().removeAll(coll);
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        return this.map.keySet().retainAll(coll);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return this.map.keySet().toArray(array);
    }

    @Override
    public boolean equals(Object obj) {
        return this.map.keySet().equals(obj);
    }

    @Override
    public int hashCode() {
        return this.map.keySet().hashCode();
    }
}

