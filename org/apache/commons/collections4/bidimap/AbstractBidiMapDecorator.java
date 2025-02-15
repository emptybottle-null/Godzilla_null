/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.AbstractMapDecorator;

public abstract class AbstractBidiMapDecorator<K, V>
extends AbstractMapDecorator<K, V>
implements BidiMap<K, V> {
    protected AbstractBidiMapDecorator(BidiMap<K, V> map) {
        super(map);
    }

    @Override
    protected BidiMap<K, V> decorated() {
        return (BidiMap)super.decorated();
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return this.decorated().mapIterator();
    }

    @Override
    public K getKey(Object value) {
        return this.decorated().getKey(value);
    }

    @Override
    public K removeValue(Object value) {
        return this.decorated().removeValue(value);
    }

    @Override
    public BidiMap<V, K> inverseBidiMap() {
        return this.decorated().inverseBidiMap();
    }

    @Override
    public Set<V> values() {
        return this.decorated().values();
    }
}

