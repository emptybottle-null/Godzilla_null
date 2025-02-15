/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.commons.collections4.multiset.AbstractMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;

public abstract class AbstractMultiValuedMap<K, V>
implements MultiValuedMap<K, V> {
    private transient Collection<V> valuesView;
    private transient EntryValues entryValuesView;
    private transient MultiSet<K> keysMultiSetView;
    private transient AsMap asMapView;
    private transient Map<K, Collection<V>> map;

    protected AbstractMultiValuedMap() {
    }

    protected AbstractMultiValuedMap(Map<K, ? extends Collection<V>> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null.");
        }
        this.map = map;
    }

    protected Map<K, ? extends Collection<V>> getMap() {
        return this.map;
    }

    protected void setMap(Map<K, ? extends Collection<V>> map) {
        this.map = map;
    }

    protected abstract Collection<V> createCollection();

    @Override
    public boolean containsKey(Object key) {
        return this.getMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.values().contains(value);
    }

    @Override
    public boolean containsMapping(Object key, Object value) {
        Collection<V> coll = this.getMap().get(key);
        return coll != null && coll.contains(value);
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        return this.entryValuesView != null ? this.entryValuesView : (this.entryValuesView = new EntryValues());
    }

    @Override
    public Collection<V> get(K key) {
        return this.wrappedCollection(key);
    }

    Collection<V> wrappedCollection(K key) {
        return new WrappedCollection(key);
    }

    @Override
    public Collection<V> remove(Object key) {
        return CollectionUtils.emptyIfNull(this.getMap().remove(key));
    }

    @Override
    public boolean removeMapping(Object key, Object value) {
        Collection<V> coll = this.getMap().get(key);
        if (coll == null) {
            return false;
        }
        boolean changed = coll.remove(value);
        if (coll.isEmpty()) {
            this.getMap().remove(key);
        }
        return changed;
    }

    @Override
    public boolean isEmpty() {
        return this.getMap().isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.getMap().keySet();
    }

    @Override
    public int size() {
        int size = 0;
        for (Collection<V> col : this.getMap().values()) {
            size += col.size();
        }
        return size;
    }

    @Override
    public Collection<V> values() {
        Values vs = this.valuesView;
        return vs != null ? vs : (this.valuesView = new Values());
    }

    @Override
    public void clear() {
        this.getMap().clear();
    }

    @Override
    public boolean put(K key, V value) {
        Collection<V> coll = this.getMap().get(key);
        if (coll == null) {
            coll = this.createCollection();
            if (coll.add(value)) {
                this.map.put(key, coll);
                return true;
            }
            return false;
        }
        return coll.add(value);
    }

    @Override
    public boolean putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null.");
        }
        boolean changed = false;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            changed |= this.put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException("Map must not be null.");
        }
        boolean changed = false;
        for (Map.Entry<K, V> entry : map.entries()) {
            changed |= this.put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override
    public MultiSet<K> keys() {
        if (this.keysMultiSetView == null) {
            this.keysMultiSetView = UnmodifiableMultiSet.unmodifiableMultiSet(new KeysMultiSet());
        }
        return this.keysMultiSetView;
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        return this.asMapView != null ? this.asMapView : (this.asMapView = new AsMap(this.map));
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> values) {
        if (values == null) {
            throw new NullPointerException("Values must not be null.");
        }
        if (values instanceof Collection) {
            Collection valueCollection = (Collection)values;
            return !valueCollection.isEmpty() && this.get(key).addAll(valueCollection);
        }
        Iterator<V> it = values.iterator();
        return it.hasNext() && CollectionUtils.addAll(this.get(key), it);
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        if (this.size() == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new MultiValuedMapIterator();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MultiValuedMap) {
            return this.asMap().equals(((MultiValuedMap)obj).asMap());
        }
        return false;
    }

    public int hashCode() {
        return this.getMap().hashCode();
    }

    public String toString() {
        return this.getMap().toString();
    }

    protected void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.map.size());
        for (Map.Entry<K, Collection<V>> entry : this.map.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeInt(entry.getValue().size());
            for (V value : entry.getValue()) {
                out.writeObject(value);
            }
        }
    }

    protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int entrySize = in.readInt();
        for (int i = 0; i < entrySize; ++i) {
            Object key = in.readObject();
            Collection<V> values = this.get(key);
            int valueSize = in.readInt();
            for (int j = 0; j < valueSize; ++j) {
                Object value = in.readObject();
                values.add(value);
            }
        }
    }

    private class AsMap
    extends AbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> decoratedMap;

        AsMap(Map<K, Collection<V>> map) {
            this.decoratedMap = map;
        }

        @Override
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            return new AsMapEntrySet();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.decoratedMap.containsKey(key);
        }

        @Override
        public Collection<V> get(Object key) {
            Collection collection = this.decoratedMap.get(key);
            if (collection == null) {
                return null;
            }
            Object k = key;
            return AbstractMultiValuedMap.this.wrappedCollection(k);
        }

        @Override
        public Set<K> keySet() {
            return AbstractMultiValuedMap.this.keySet();
        }

        @Override
        public int size() {
            return this.decoratedMap.size();
        }

        @Override
        public Collection<V> remove(Object key) {
            Collection collection = this.decoratedMap.remove(key);
            if (collection == null) {
                return null;
            }
            Collection output = AbstractMultiValuedMap.this.createCollection();
            output.addAll(collection);
            collection.clear();
            return output;
        }

        @Override
        public boolean equals(Object object) {
            return this == object || this.decoratedMap.equals(object);
        }

        @Override
        public int hashCode() {
            return this.decoratedMap.hashCode();
        }

        @Override
        public String toString() {
            return this.decoratedMap.toString();
        }

        @Override
        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }

        class AsMapEntrySetIterator
        extends AbstractIteratorDecorator<Map.Entry<K, Collection<V>>> {
            AsMapEntrySetIterator(Iterator<Map.Entry<K, Collection<V>>> iterator) {
                super(iterator);
            }

            @Override
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry entry = (Map.Entry)super.next();
                Object key = entry.getKey();
                return new UnmodifiableMapEntry(key, AbstractMultiValuedMap.this.wrappedCollection(key));
            }
        }

        class AsMapEntrySet
        extends AbstractSet<Map.Entry<K, Collection<V>>> {
            AsMapEntrySet() {
            }

            @Override
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapEntrySetIterator(AsMap.this.decoratedMap.entrySet().iterator());
            }

            @Override
            public int size() {
                return AsMap.this.size();
            }

            @Override
            public void clear() {
                AsMap.this.clear();
            }

            @Override
            public boolean contains(Object o) {
                return AsMap.this.decoratedMap.entrySet().contains(o);
            }

            @Override
            public boolean remove(Object o) {
                if (!this.contains(o)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry)o;
                AbstractMultiValuedMap.this.remove(entry.getKey());
                return true;
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
            this.values = AbstractMultiValuedMap.this.getMap().get(key);
            this.iterator = this.values.iterator();
        }

        @Override
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
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
            for (Object k : AbstractMultiValuedMap.this.keySet()) {
                chain.addIterator(new ValuesIterator(k));
            }
            return chain;
        }

        @Override
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }

        @Override
        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }
    }

    private class MultiValuedMapIterator
    implements MapIterator<K, V> {
        private final Iterator<Map.Entry<K, V>> it;
        private Map.Entry<K, V> current = null;

        public MultiValuedMapIterator() {
            this.it = AbstractMultiValuedMap.this.entries().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public K next() {
            this.current = this.it.next();
            return this.current.getKey();
        }

        @Override
        public K getKey() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.getKey();
        }

        @Override
        public V getValue() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.getValue();
        }

        @Override
        public void remove() {
            this.it.remove();
        }

        @Override
        public V setValue(V value) {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current.setValue(value);
        }
    }

    private class MultiValuedMapEntry
    extends AbstractMapEntry<K, V> {
        public MultiValuedMapEntry(K key, V value) {
            super(key, value);
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }

    private class EntryValues
    extends AbstractCollection<Map.Entry<K, V>> {
        private EntryValues() {
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LazyIteratorChain<Map.Entry<K, V>>(){
                final Collection<K> keysCol;
                final Iterator<K> keyIterator;
                {
                    this.keysCol = new ArrayList(AbstractMultiValuedMap.this.getMap().keySet());
                    this.keyIterator = this.keysCol.iterator();
                }

                @Override
                protected Iterator<? extends Map.Entry<K, V>> nextIterator(int count) {
                    if (!this.keyIterator.hasNext()) {
                        return null;
                    }
                    final Object key = this.keyIterator.next();
                    Transformer entryTransformer = new Transformer<V, Map.Entry<K, V>>(){

                        @Override
                        public Map.Entry<K, V> transform(V input) {
                            return new MultiValuedMapEntry(key, input);
                        }
                    };
                    return new TransformIterator(new ValuesIterator(key), entryTransformer);
                }
            };
        }

        @Override
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }
    }

    private class KeysMultiSet
    extends AbstractMultiSet<K> {
        private KeysMultiSet() {
        }

        @Override
        public boolean contains(Object o) {
            return AbstractMultiValuedMap.this.getMap().containsKey(o);
        }

        @Override
        public boolean isEmpty() {
            return AbstractMultiValuedMap.this.getMap().isEmpty();
        }

        @Override
        public int size() {
            return AbstractMultiValuedMap.this.size();
        }

        @Override
        protected int uniqueElements() {
            return AbstractMultiValuedMap.this.getMap().size();
        }

        @Override
        public int getCount(Object object) {
            int count = 0;
            Collection col = AbstractMultiValuedMap.this.getMap().get(object);
            if (col != null) {
                count = col.size();
            }
            return count;
        }

        @Override
        protected Iterator<MultiSet.Entry<K>> createEntrySetIterator() {
            MapEntryTransformer transformer = new MapEntryTransformer();
            return IteratorUtils.transformedIterator(AbstractMultiValuedMap.this.map.entrySet().iterator(), transformer);
        }

        private final class MapEntryTransformer
        implements Transformer<Map.Entry<K, Collection<V>>, MultiSet.Entry<K>> {
            private MapEntryTransformer() {
            }

            @Override
            public MultiSet.Entry<K> transform(final Map.Entry<K, Collection<V>> mapEntry) {
                return new AbstractMultiSet.AbstractEntry<K>(){

                    @Override
                    public K getElement() {
                        return mapEntry.getKey();
                    }

                    @Override
                    public int getCount() {
                        return ((Collection)mapEntry.getValue()).size();
                    }
                };
            }
        }
    }

    class WrappedCollection
    implements Collection<V> {
        protected final K key;

        public WrappedCollection(K key) {
            this.key = key;
        }

        protected Collection<V> getMapping() {
            return AbstractMultiValuedMap.this.getMap().get(this.key);
        }

        @Override
        public boolean add(V value) {
            Collection coll = this.getMapping();
            if (coll == null) {
                coll = AbstractMultiValuedMap.this.createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, coll);
            }
            return coll.add(value);
        }

        @Override
        public boolean addAll(Collection<? extends V> other) {
            Collection coll = this.getMapping();
            if (coll == null) {
                coll = AbstractMultiValuedMap.this.createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, coll);
            }
            return coll.addAll(other);
        }

        @Override
        public void clear() {
            Collection coll = this.getMapping();
            if (coll != null) {
                coll.clear();
                AbstractMultiValuedMap.this.remove(this.key);
            }
        }

        @Override
        public Iterator<V> iterator() {
            Collection coll = this.getMapping();
            if (coll == null) {
                return IteratorUtils.EMPTY_ITERATOR;
            }
            return new ValuesIterator(this.key);
        }

        @Override
        public int size() {
            Collection coll = this.getMapping();
            return coll == null ? 0 : coll.size();
        }

        @Override
        public boolean contains(Object obj) {
            Collection coll = this.getMapping();
            return coll != null && coll.contains(obj);
        }

        @Override
        public boolean containsAll(Collection<?> other) {
            Collection coll = this.getMapping();
            return coll != null && coll.containsAll(other);
        }

        @Override
        public boolean isEmpty() {
            Collection coll = this.getMapping();
            return coll == null || coll.isEmpty();
        }

        @Override
        public boolean remove(Object item) {
            Collection coll = this.getMapping();
            if (coll == null) {
                return false;
            }
            boolean result = coll.remove(item);
            if (coll.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return result;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            Collection coll = this.getMapping();
            if (coll == null) {
                return false;
            }
            boolean result = coll.removeAll(c);
            if (coll.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return result;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            Collection coll = this.getMapping();
            if (coll == null) {
                return false;
            }
            boolean result = coll.retainAll(c);
            if (coll.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return result;
        }

        @Override
        public Object[] toArray() {
            Collection coll = this.getMapping();
            if (coll == null) {
                return CollectionUtils.EMPTY_COLLECTION.toArray();
            }
            return coll.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            Collection coll = this.getMapping();
            if (coll == null) {
                return CollectionUtils.EMPTY_COLLECTION.toArray(a);
            }
            return coll.toArray(a);
        }

        public String toString() {
            Collection coll = this.getMapping();
            if (coll == null) {
                return CollectionUtils.EMPTY_COLLECTION.toString();
            }
            return coll.toString();
        }
    }
}

