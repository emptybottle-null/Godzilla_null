/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;

public abstract class AbstractDualBidiMap<K, V>
implements BidiMap<K, V> {
    transient Map<K, V> normalMap;
    transient Map<V, K> reverseMap;
    transient BidiMap<V, K> inverseBidiMap = null;
    transient Set<K> keySet = null;
    transient Set<V> values = null;
    transient Set<Map.Entry<K, V>> entrySet = null;

    protected AbstractDualBidiMap() {
    }

    protected AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
    }

    protected AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
        this.inverseBidiMap = inverseBidiMap;
    }

    protected abstract BidiMap<V, K> createBidiMap(Map<V, K> var1, Map<K, V> var2, BidiMap<K, V> var3);

    @Override
    public V get(Object key) {
        return this.normalMap.get(key);
    }

    @Override
    public int size() {
        return this.normalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.normalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.normalMap.containsKey(key);
    }

    @Override
    public boolean equals(Object obj) {
        return this.normalMap.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.normalMap.hashCode();
    }

    public String toString() {
        return this.normalMap.toString();
    }

    @Override
    public V put(K key, V value) {
        if (this.normalMap.containsKey(key)) {
            this.reverseMap.remove(this.normalMap.get(key));
        }
        if (this.reverseMap.containsKey(value)) {
            this.normalMap.remove(this.reverseMap.get(value));
        }
        V obj = this.normalMap.put(key, value);
        this.reverseMap.put(value, key);
        return obj;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        V value = null;
        if (this.normalMap.containsKey(key)) {
            value = this.normalMap.remove(key);
            this.reverseMap.remove(value);
        }
        return value;
    }

    @Override
    public void clear() {
        this.normalMap.clear();
        this.reverseMap.clear();
    }

    @Override
    public boolean containsValue(Object value) {
        return this.reverseMap.containsKey(value);
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return new BidiMapIterator(this);
    }

    @Override
    public K getKey(Object value) {
        return this.reverseMap.get(value);
    }

    @Override
    public K removeValue(Object value) {
        K key = null;
        if (this.reverseMap.containsKey(value)) {
            key = this.reverseMap.remove(value);
            this.normalMap.remove(key);
        }
        return key;
    }

    @Override
    public BidiMap<V, K> inverseBidiMap() {
        if (this.inverseBidiMap == null) {
            this.inverseBidiMap = this.createBidiMap(this.reverseMap, this.normalMap, this);
        }
        return this.inverseBidiMap;
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    protected Iterator<K> createKeySetIterator(Iterator<K> iterator) {
        return new KeySetIterator<K>(iterator, this);
    }

    @Override
    public Set<V> values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }

    protected Iterator<V> createValuesIterator(Iterator<V> iterator) {
        return new ValuesIterator<V>(iterator, this);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator(Iterator<Map.Entry<K, V>> iterator) {
        return new EntrySetIterator<K, V>(iterator, this);
    }

    protected static class BidiMapIterator<K, V>
    implements MapIterator<K, V>,
    ResettableIterator<K> {
        protected final AbstractDualBidiMap<K, V> parent;
        protected Iterator<Map.Entry<K, V>> iterator;
        protected Map.Entry<K, V> last = null;
        protected boolean canRemove = false;

        protected BidiMapIterator(AbstractDualBidiMap<K, V> parent) {
            this.parent = parent;
            this.iterator = parent.normalMap.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public K next() {
            this.last = this.iterator.next();
            this.canRemove = true;
            return this.last.getKey();
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            V value = this.last.getValue();
            this.iterator.remove();
            this.parent.reverseMap.remove(value);
            this.last = null;
            this.canRemove = false;
        }

        @Override
        public K getKey() {
            if (this.last == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.last.getKey();
        }

        @Override
        public V getValue() {
            if (this.last == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.last.getValue();
        }

        @Override
        public V setValue(V value) {
            if (this.last == null) {
                throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
            }
            if (this.parent.reverseMap.containsKey(value) && this.parent.reverseMap.get(value) != this.last.getKey()) {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
            return this.parent.put(this.last.getKey(), value);
        }

        @Override
        public void reset() {
            this.iterator = this.parent.normalMap.entrySet().iterator();
            this.last = null;
            this.canRemove = false;
        }

        public String toString() {
            if (this.last != null) {
                return "MapIterator[" + this.getKey() + "=" + this.getValue() + "]";
            }
            return "MapIterator[]";
        }
    }

    protected static class MapEntry<K, V>
    extends AbstractMapEntryDecorator<K, V> {
        protected final AbstractDualBidiMap<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractDualBidiMap<K, V> parent) {
            super(entry);
            this.parent = parent;
        }

        @Override
        public V setValue(V value) {
            Object key = this.getKey();
            if (this.parent.reverseMap.containsKey(value) && this.parent.reverseMap.get(value) != key) {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
            this.parent.put(key, value);
            return super.setValue(value);
        }
    }

    protected static class EntrySetIterator<K, V>
    extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        protected final AbstractDualBidiMap<K, V> parent;
        protected Map.Entry<K, V> last = null;
        protected boolean canRemove = false;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractDualBidiMap<K, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public Map.Entry<K, V> next() {
            this.last = new MapEntry<K, V>((Map.Entry)super.next(), this.parent);
            this.canRemove = true;
            return this.last;
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            V value = this.last.getValue();
            super.remove();
            this.parent.reverseMap.remove(value);
            this.last = null;
            this.canRemove = false;
        }
    }

    protected static class EntrySet<K, V>
    extends View<K, V, Map.Entry<K, V>>
    implements Set<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4040410962603292348L;

        protected EntrySet(AbstractDualBidiMap<K, V> parent) {
            super(parent.normalMap.entrySet(), parent);
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator(super.iterator());
        }

        @Override
        public boolean remove(Object obj) {
            Object value;
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object key = entry.getKey();
            if (this.parent.containsKey(key) && ((value = this.parent.normalMap.get(key)) == null ? entry.getValue() == null : value.equals(entry.getValue()))) {
                this.parent.normalMap.remove(key);
                this.parent.reverseMap.remove(value);
                return true;
            }
            return false;
        }
    }

    protected static class ValuesIterator<V>
    extends AbstractIteratorDecorator<V> {
        protected final AbstractDualBidiMap<Object, V> parent;
        protected V lastValue = null;
        protected boolean canRemove = false;

        protected ValuesIterator(Iterator<V> iterator, AbstractDualBidiMap<?, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public V next() {
            this.lastValue = super.next();
            this.canRemove = true;
            return this.lastValue;
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            super.remove();
            this.parent.reverseMap.remove(this.lastValue);
            this.lastValue = null;
            this.canRemove = false;
        }
    }

    protected static class Values<V>
    extends View<Object, V, V>
    implements Set<V> {
        private static final long serialVersionUID = 4023777119829639864L;

        protected Values(AbstractDualBidiMap<?, V> parent) {
            super(parent.normalMap.values(), parent);
        }

        @Override
        public Iterator<V> iterator() {
            return this.parent.createValuesIterator(super.iterator());
        }

        @Override
        public boolean contains(Object value) {
            return this.parent.reverseMap.containsKey(value);
        }

        @Override
        public boolean remove(Object value) {
            if (this.parent.reverseMap.containsKey(value)) {
                Object key = this.parent.reverseMap.remove(value);
                this.parent.normalMap.remove(key);
                return true;
            }
            return false;
        }
    }

    protected static class KeySetIterator<K>
    extends AbstractIteratorDecorator<K> {
        protected final AbstractDualBidiMap<K, ?> parent;
        protected K lastKey = null;
        protected boolean canRemove = false;

        protected KeySetIterator(Iterator<K> iterator, AbstractDualBidiMap<K, ?> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public K next() {
            this.lastKey = super.next();
            this.canRemove = true;
            return this.lastKey;
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            Object value = this.parent.normalMap.get(this.lastKey);
            super.remove();
            this.parent.reverseMap.remove(value);
            this.lastKey = null;
            this.canRemove = false;
        }
    }

    protected static class KeySet<K>
    extends View<K, Object, K>
    implements Set<K> {
        private static final long serialVersionUID = -7107935777385040694L;

        protected KeySet(AbstractDualBidiMap<K, ?> parent) {
            super(parent.normalMap.keySet(), parent);
        }

        @Override
        public Iterator<K> iterator() {
            return this.parent.createKeySetIterator(super.iterator());
        }

        @Override
        public boolean contains(Object key) {
            return this.parent.normalMap.containsKey(key);
        }

        @Override
        public boolean remove(Object key) {
            if (this.parent.normalMap.containsKey(key)) {
                Object value = this.parent.normalMap.remove(key);
                this.parent.reverseMap.remove(value);
                return true;
            }
            return false;
        }
    }

    protected static abstract class View<K, V, E>
    extends AbstractCollectionDecorator<E> {
        private static final long serialVersionUID = 4621510560119690639L;
        protected final AbstractDualBidiMap<K, V> parent;

        protected View(Collection<E> coll, AbstractDualBidiMap<K, V> parent) {
            super(coll);
            this.parent = parent;
        }

        @Override
        public boolean equals(Object object) {
            return object == this || this.decorated().equals(object);
        }

        @Override
        public int hashCode() {
            return this.decorated().hashCode();
        }

        @Override
        public boolean removeIf(Predicate<? super E> filter) {
            if (this.parent.isEmpty() || Objects.isNull(filter)) {
                return false;
            }
            boolean modified = false;
            Iterator it = this.iterator();
            while (it.hasNext()) {
                Object e = it.next();
                if (!filter.test(e)) continue;
                it.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean removeAll(Collection<?> coll) {
            if (this.parent.isEmpty() || coll.isEmpty()) {
                return false;
            }
            boolean modified = false;
            Iterator<?> it = coll.iterator();
            while (it.hasNext()) {
                modified |= this.remove(it.next());
            }
            return modified;
        }

        @Override
        public boolean retainAll(Collection<?> coll) {
            if (this.parent.isEmpty()) {
                return false;
            }
            if (coll.isEmpty()) {
                this.parent.clear();
                return true;
            }
            boolean modified = false;
            Iterator it = this.iterator();
            while (it.hasNext()) {
                if (coll.contains(it.next())) continue;
                it.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public void clear() {
            this.parent.clear();
        }
    }
}

