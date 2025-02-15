/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.map.AbstractHashedMap;

public class HashedMap<K, V>
extends AbstractHashedMap<K, V>
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1788199231038721040L;

    public HashedMap() {
        super(16, 0.75f, 12);
    }

    public HashedMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashedMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    public HashedMap<K, V> clone() {
        return (HashedMap)super.clone();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        this.doWriteObject(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.doReadObject(in);
    }
}

