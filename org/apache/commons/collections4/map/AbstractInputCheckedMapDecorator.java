/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;

abstract class AbstractInputCheckedMapDecorator<K, V>
extends AbstractMapDecorator<K, V> {
    protected AbstractInputCheckedMapDecorator() {
    }

    protected AbstractInputCheckedMapDecorator(Map<K, V> map) {
        super(map);
    }

    protected abstract V checkSetValue(V var1);

    protected boolean isSetValueChecking() {
        return true;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.isSetValueChecking()) {
            return new EntrySet(this.map.entrySet(), this);
        }
        return this.map.entrySet();
    }

    private class MapEntry
    extends AbstractMapEntryDecorator<K, V> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(entry);
            this.parent = parent;
        }

        @Override
        public V setValue(V value) {
            value = this.parent.checkSetValue(value);
            return this.getMapEntry().setValue(value);
        }
    }

    private class EntrySetIterator
    extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public Map.Entry<K, V> next() {
            Map.Entry entry = (Map.Entry)this.getIterator().next();
            return new MapEntry(entry, this.parent);
        }
    }

    private class EntrySet
    extends AbstractSetDecorator<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4354731610923110264L;
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySet(Set<Map.Entry<K, V>> set, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(set);
            this.parent = parent;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntrySetIterator(this.decorated().iterator(), this.parent);
        }

        @Override
        public Object[] toArray() {
            Object[] array = this.decorated().toArray();
            for (int i = 0; i < array.length; ++i) {
                array[i] = new MapEntry((Map.Entry)array[i], this.parent);
            }
            return array;
        }

        @Override
        public <T> T[] toArray(T[] array) {
            Object[] result = array;
            if (array.length > 0) {
                result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
            }
            result = this.decorated().toArray(result);
            for (int i = 0; i < result.length; ++i) {
                result[i] = new MapEntry((Map.Entry)result[i], this.parent);
            }
            if (result.length > array.length) {
                return result;
            }
            System.arraycopy(result, 0, array, 0, result.length);
            if (array.length > result.length) {
                array[result.length] = null;
            }
            return array;
        }
    }
}

