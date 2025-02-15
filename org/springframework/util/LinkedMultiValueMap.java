/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMapAdapter;

public class LinkedMultiValueMap<K, V>
extends MultiValueMapAdapter<K, V>
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 3801124242820219131L;

    public LinkedMultiValueMap() {
        super(new LinkedHashMap());
    }

    public LinkedMultiValueMap(int expectedSize) {
        super(CollectionUtils.newLinkedHashMap(expectedSize));
    }

    public LinkedMultiValueMap(Map<K, List<V>> otherMap) {
        super(new LinkedHashMap<K, List<V>>(otherMap));
    }

    public LinkedMultiValueMap<K, V> deepCopy() {
        LinkedMultiValueMap copy = new LinkedMultiValueMap(this.size());
        this.forEach((key, values) -> copy.put((Object)key, new ArrayList(values)));
        return copy;
    }

    public LinkedMultiValueMap<K, V> clone() {
        return new LinkedMultiValueMap<K, V>(this);
    }
}

