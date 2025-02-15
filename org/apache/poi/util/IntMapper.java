/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.common.Duplicatable;

public class IntMapper<T>
implements Duplicatable {
    private final List<T> elements;
    private final Map<T, Integer> valueKeyMap;
    private static final int _default_size = 10;

    public IntMapper() {
        this(10);
    }

    public IntMapper(int initialCapacity) {
        this.elements = new ArrayList<T>(initialCapacity);
        this.valueKeyMap = new HashMap<T, Integer>(initialCapacity);
    }

    public IntMapper(IntMapper<T> other) {
        this.elements = new ArrayList<T>(other.elements);
        this.valueKeyMap = new HashMap<T, Integer>(other.valueKeyMap);
    }

    public boolean add(T value) {
        int index = this.elements.size();
        this.elements.add(value);
        this.valueKeyMap.put(value, index);
        return true;
    }

    public int size() {
        return this.elements.size();
    }

    public T get(int index) {
        return this.elements.get(index);
    }

    public int getIndex(T o) {
        return this.valueKeyMap.getOrDefault(o, -1);
    }

    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    @Override
    public IntMapper<T> copy() {
        return new IntMapper<T>(this);
    }
}

