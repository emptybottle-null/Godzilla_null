/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.AbstractDualBidiMap;

public class DualLinkedHashBidiMap<K, V>
extends AbstractDualBidiMap<K, V>
implements Serializable {
    private static final long serialVersionUID = 721969328361810L;

    public DualLinkedHashBidiMap() {
        super(new LinkedHashMap(), new LinkedHashMap());
    }

    public DualLinkedHashBidiMap(Map<? extends K, ? extends V> map) {
        super(new LinkedHashMap(), new LinkedHashMap());
        this.putAll(map);
    }

    protected DualLinkedHashBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap) {
        super(normalMap, reverseMap, inverseBidiMap);
    }

    @Override
    protected BidiMap<V, K> createBidiMap(Map<V, K> normalMap, Map<K, V> reverseMap, BidiMap<K, V> inverseBidiMap) {
        return new DualLinkedHashBidiMap<V, K>(normalMap, reverseMap, inverseBidiMap);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.normalMap);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.normalMap = new LinkedHashMap();
        this.reverseMap = new LinkedHashMap();
        Map map = (Map)in.readObject();
        this.putAll(map);
    }
}

