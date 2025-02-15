/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.map.AbstractMapDecorator;

@Deprecated
public class MultiValueMap<K, V>
extends AbstractMapDecorator<K, Object>
implements MultiMap<K, V>,
Serializable {
    private static final long serialVersionUID = -2214159910087182007L;
    private final Factory<? extends Collection<V>> collectionFactory;
    private transient Collection<V> valuesView;

    public static <K, V> MultiValueMap<K, V> multiValueMap(Map<K, ? super Collection<V>> map) {
        return MultiValueMap.multiValueMap(map, ArrayList.class);
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Class<C> collectionClass) {
        return new MultiValueMap<K, V>(map, new ReflectionFactory<C>(collectionClass));
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Factory<C> collectionFactory) {
        return new MultiValueMap<K, V>(map, collectionFactory);
    }

    public MultiValueMap() {
        this(new HashMap(), new ReflectionFactory<ArrayList>(ArrayList.class));
    }

    protected <C extends Collection<V>> MultiValueMap(Map<K, ? super C> map, Factory<C> collectionFactory) {
        super(map);
        if (collectionFactory == null) {
            throw new IllegalArgumentException("The factory must not be null");
        }
        this.collectionFactory = collectionFactory;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }

    @Override
    public void clear() {
        this.decorated().clear();
    }

    @Override
    public boolean removeMapping(Object key, Object value) {
        Collection<V> valuesForKey = this.getCollection(key);
        if (valuesForKey == null) {
            return false;
        }
        boolean removed = valuesForKey.remove(value);
        if (!removed) {
            return false;
        }
        if (valuesForKey.isEmpty()) {
            this.remove(key);
        }
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        Set pairs = this.decorated().entrySet();
        if (pairs != null) {
            for (Map.Entry entry : pairs) {
                if (!((Collection)entry.getValue()).contains(value)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object put(K key, Object value) {
        boolean result = false;
        Collection<V> coll = this.getCollection(key);
        if (coll == null) {
            coll = this.createCollection(1);
            coll.add(value);
            if (coll.size() > 0) {
                this.decorated().put(key, coll);
                result = true;
            }
        } else {
            result = coll.add(value);
        }
        return result ? value : null;
    }

    @Override
    public void putAll(Map<? extends K, ?> map) {
        if (map instanceof MultiMap) {
            for (Map.Entry entry : ((MultiMap)map).entrySet()) {
                this.putAll(entry.getKey(), (Collection)entry.getValue());
            }
        } else {
            for (Map.Entry<K, ?> entry : map.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Set<Map.Entry<K, Object>> entrySet() {
        return super.entrySet();
    }

    @Override
    public Collection<Object> values() {
        Values vs = this.valuesView;
        return vs != null ? vs : (this.valuesView = new Values());
    }

    public boolean containsValue(Object key, Object value) {
        Collection<V> coll = this.getCollection(key);
        if (coll == null) {
            return false;
        }
        return coll.contains(value);
    }

    public Collection<V> getCollection(Object key) {
        return (Collection)this.decorated().get(key);
    }

    public int size(Object key) {
        Collection<V> coll = this.getCollection(key);
        if (coll == null) {
            return 0;
        }
        return coll.size();
    }

    public boolean putAll(K key, Collection<V> values) {
        if (values == null || values.size() == 0) {
            return false;
        }
        boolean result = false;
        Collection<V> coll = this.getCollection(key);
        if (coll == null) {
            coll = this.createCollection(values.size());
            coll.addAll(values);
            if (coll.size() > 0) {
                this.decorated().put(key, coll);
                result = true;
            }
        } else {
            result = coll.addAll(values);
        }
        return result;
    }

    public Iterator<V> iterator(Object key) {
        if (!this.containsKey(key)) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(key);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        ArrayList allKeys = new ArrayList(this.keySet());
        final Iterator keyIterator = allKeys.iterator();
        return new LazyIteratorChain<Map.Entry<K, V>>(){

            @Override
            protected Iterator<? extends Map.Entry<K, V>> nextIterator(int count) {
                if (!keyIterator.hasNext()) {
                    return null;
                }
                final Object key = keyIterator.next();
                Transformer transformer = new Transformer<V, Map.Entry<K, V>>(){

                    @Override
                    public Map.Entry<K, V> transform(final V input) {
                        return new Map.Entry<K, V>(){

                            @Override
                            public K getKey() {
                                return key;
                            }

                            @Override
                            public V getValue() {
                                return input;
                            }

                            @Override
                            public V setValue(V value) {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }
                };
                return new TransformIterator(new ValuesIterator(key), transformer);
            }
        };
    }

    public int totalSize() {
        int total = 0;
        for (Object v : this.decorated().values()) {
            total += CollectionUtils.size(v);
        }
        return total;
    }

    protected Collection<V> createCollection(int size) {
        return this.collectionFactory.create();
    }

    private static class ReflectionFactory<T extends Collection<?>>
    implements Factory<T>,
    Serializable {
        private static final long serialVersionUID = 2986114157496788874L;
        private final Class<T> clazz;

        public ReflectionFactory(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T create() {
            try {
                return (T)((Collection)this.clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (Exception ex) {
                throw new FunctorException("Cannot instantiate class: " + this.clazz, ex);
            }
        }

        private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
            is.defaultReadObject();
            if (this.clazz != null && !Collection.class.isAssignableFrom(this.clazz)) {
                throw new UnsupportedOperationException();
            }
        }
    }

    private class ValuesIterator
    implements Iterator<V> {
        private final Object key;
        private final Collection<V> values;
        private final Iterator<V> iterator;

        public ValuesIterator(Object key) {
            this.key = key;
            this.values = MultiValueMap.this.getCollection(key);
            this.iterator = this.values.iterator();
        }

        @Override
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                MultiValueMap.this.remove(this.key);
            }
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public V next() {
            return this.iterator.next();
        }
    }

    private class Values
    extends AbstractCollection<V> {
        private Values() {
        }

        @Override
        public Iterator<V> iterator() {
            IteratorChain chain = new IteratorChain();
            for (Object k : MultiValueMap.this.keySet()) {
                chain.addIterator(new ValuesIterator(k));
            }
            return chain;
        }

        @Override
        public int size() {
            return MultiValueMap.this.totalSize();
        }

        @Override
        public void clear() {
            MultiValueMap.this.clear();
        }
    }
}

