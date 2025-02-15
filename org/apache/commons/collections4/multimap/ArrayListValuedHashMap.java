/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.AbstractListValuedMap;

public class ArrayListValuedHashMap<K, V>
extends AbstractListValuedMap<K, V>
implements Serializable {
    private static final long serialVersionUID = 20151118L;
    private static final int DEFAULT_INITIAL_MAP_CAPACITY = 16;
    private static final int DEFAULT_INITIAL_LIST_CAPACITY = 3;
    private final int initialListCapacity;

    public ArrayListValuedHashMap() {
        this(16, 3);
    }

    public ArrayListValuedHashMap(int initialListCapacity) {
        this(16, initialListCapacity);
    }

    public ArrayListValuedHashMap(int initialMapCapacity, int initialListCapacity) {
        super(new HashMap(initialMapCapacity));
        this.initialListCapacity = initialListCapacity;
    }

    public ArrayListValuedHashMap(MultiValuedMap<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    public ArrayListValuedHashMap(Map<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    @Override
    protected ArrayList<V> createCollection() {
        return new ArrayList(this.initialListCapacity);
    }

    public void trimToSize() {
        for (Collection collection : this.getMap().values()) {
            ArrayList list = (ArrayList)collection;
            list.trimToSize();
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        this.doWriteObject(oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.setMap(new HashMap());
        this.doReadObject(ois);
    }
}

