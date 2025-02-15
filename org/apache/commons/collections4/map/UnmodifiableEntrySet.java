/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;

public final class UnmodifiableEntrySet<K, V>
extends AbstractSetDecorator<Map.Entry<K, V>>
implements Unmodifiable {
    private static final long serialVersionUID = 1678353579659253473L;

    public static <K, V> Set<Map.Entry<K, V>> unmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
        if (set instanceof Unmodifiable) {
            return set;
        }
        return new UnmodifiableEntrySet<K, V>(set);
    }

    private UnmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
        super(set);
    }

    @Override
    public boolean add(Map.Entry<K, V> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Map.Entry<K, V>> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super Map.Entry<K, V>> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new UnmodifiableEntrySetIterator(this.decorated().iterator());
    }

    @Override
    public Object[] toArray() {
        Object[] array = this.decorated().toArray();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new UnmodifiableEntry((Map.Entry)array[i]);
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
            result[i] = new UnmodifiableEntry((Map.Entry)result[i]);
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

    private class UnmodifiableEntry
    extends AbstractMapEntryDecorator<K, V> {
        protected UnmodifiableEntry(Map.Entry<K, V> entry) {
            super(entry);
        }

        @Override
        public V setValue(V obj) {
            throw new UnsupportedOperationException();
        }
    }

    private class UnmodifiableEntrySetIterator
    extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        protected UnmodifiableEntrySetIterator(Iterator<Map.Entry<K, V>> iterator) {
            super(iterator);
        }

        @Override
        public Map.Entry<K, V> next() {
            return new UnmodifiableEntry((Map.Entry)this.getIterator().next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

