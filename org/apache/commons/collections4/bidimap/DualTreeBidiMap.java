/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.bidimap.AbstractDualBidiMap;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;

public class DualTreeBidiMap<K, V>
extends AbstractDualBidiMap<K, V>
implements SortedBidiMap<K, V>,
Serializable {
    private static final long serialVersionUID = 721969328361809L;
    private final Comparator<? super K> comparator;
    private final Comparator<? super V> valueComparator;

    public DualTreeBidiMap() {
        super(new TreeMap(), new TreeMap());
        this.comparator = null;
        this.valueComparator = null;
    }

    public DualTreeBidiMap(Map<? extends K, ? extends V> map) {
        super(new TreeMap(), new TreeMap());
        this.putAll(map);
        this.comparator = null;
        this.valueComparator = null;
    }

    public DualTreeBidiMap(Comparator<? super K> keyComparator, Comparator<? super V> valueComparator) {
        super(new TreeMap(keyComparator), new TreeMap(valueComparator));
        this.comparator = keyComparator;
        this.valueComparator = valueComparator;
    }

    protected DualTreeBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap) {
        super(normalMap, reverseMap, inverseBidiMap);
        this.comparator = ((SortedMap)normalMap).comparator();
        this.valueComparator = ((SortedMap)reverseMap).comparator();
    }

    @Override
    protected DualTreeBidiMap<V, K> createBidiMap(Map<V, K> normalMap, Map<K, V> reverseMap, BidiMap<K, V> inverseMap) {
        return new DualTreeBidiMap<V, K>(normalMap, reverseMap, inverseMap);
    }

    @Override
    public Comparator<? super K> comparator() {
        return ((SortedMap)this.normalMap).comparator();
    }

    @Override
    public Comparator<? super V> valueComparator() {
        return ((SortedMap)this.reverseMap).comparator();
    }

    @Override
    public K firstKey() {
        return ((SortedMap)this.normalMap).firstKey();
    }

    @Override
    public K lastKey() {
        return ((SortedMap)this.normalMap).lastKey();
    }

    @Override
    public K nextKey(K key) {
        if (this.isEmpty()) {
            return null;
        }
        if (this.normalMap instanceof OrderedMap) {
            return ((OrderedMap)this.normalMap).nextKey(key);
        }
        SortedMap sm = (SortedMap)this.normalMap;
        Iterator<K> it = sm.tailMap(key).keySet().iterator();
        it.next();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    @Override
    public K previousKey(K key) {
        if (this.isEmpty()) {
            return null;
        }
        if (this.normalMap instanceof OrderedMap) {
            return ((OrderedMap)this.normalMap).previousKey(key);
        }
        SortedMap sm = (SortedMap)this.normalMap;
        SortedMap hm = sm.headMap(key);
        if (hm.isEmpty()) {
            return null;
        }
        return hm.lastKey();
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        return new BidiOrderedMapIterator(this);
    }

    public SortedBidiMap<V, K> inverseSortedBidiMap() {
        return this.inverseBidiMap();
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        return this.inverseBidiMap();
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap sub = ((SortedMap)this.normalMap).headMap(toKey);
        return new ViewMap(this, sub);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap sub = ((SortedMap)this.normalMap).tailMap(fromKey);
        return new ViewMap(this, sub);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        SortedMap sub = ((SortedMap)this.normalMap).subMap(fromKey, toKey);
        return new ViewMap(this, sub);
    }

    @Override
    public SortedBidiMap<V, K> inverseBidiMap() {
        return (SortedBidiMap)super.inverseBidiMap();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.normalMap);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.normalMap = new TreeMap(this.comparator);
        this.reverseMap = new TreeMap(this.valueComparator);
        Map map = (Map)in.readObject();
        this.putAll(map);
    }

    protected static class BidiOrderedMapIterator<K, V>
    implements OrderedMapIterator<K, V>,
    ResettableIterator<K> {
        private final AbstractDualBidiMap<K, V> parent;
        private ListIterator<Map.Entry<K, V>> iterator;
        private Map.Entry<K, V> last = null;

        protected BidiOrderedMapIterator(AbstractDualBidiMap<K, V> parent) {
            this.parent = parent;
            this.iterator = new ArrayList<Map.Entry<K, V>>(parent.entrySet()).listIterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public K next() {
            this.last = this.iterator.next();
            return this.last.getKey();
        }

        @Override
        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        @Override
        public K previous() {
            this.last = this.iterator.previous();
            return this.last.getKey();
        }

        @Override
        public void remove() {
            this.iterator.remove();
            this.parent.remove(this.last.getKey());
            this.last = null;
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
            V oldValue = this.parent.put(this.last.getKey(), value);
            this.last.setValue(value);
            return oldValue;
        }

        @Override
        public void reset() {
            this.iterator = new ArrayList<Map.Entry<K, V>>(this.parent.entrySet()).listIterator();
            this.last = null;
        }

        public String toString() {
            if (this.last != null) {
                return "MapIterator[" + this.getKey() + "=" + this.getValue() + "]";
            }
            return "MapIterator[]";
        }
    }

    protected static class ViewMap<K, V>
    extends AbstractSortedMapDecorator<K, V> {
        protected ViewMap(DualTreeBidiMap<K, V> bidi, SortedMap<K, V> sm) {
            super(new DualTreeBidiMap<K, V>(sm, bidi.reverseMap, bidi.inverseBidiMap));
        }

        @Override
        public boolean containsValue(Object value) {
            return ((DualTreeBidiMap)this.decorated()).normalMap.containsValue(value);
        }

        @Override
        public void clear() {
            Iterator it = this.keySet().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        @Override
        public SortedMap<K, V> headMap(K toKey) {
            return new ViewMap(this.decorated(), super.headMap(toKey));
        }

        @Override
        public SortedMap<K, V> tailMap(K fromKey) {
            return new ViewMap(this.decorated(), super.tailMap(fromKey));
        }

        @Override
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return new ViewMap(this.decorated(), super.subMap(fromKey, toKey));
        }

        @Override
        protected DualTreeBidiMap<K, V> decorated() {
            return (DualTreeBidiMap)super.decorated();
        }

        @Override
        public K previousKey(K key) {
            return ((DualTreeBidiMap)this.decorated()).previousKey(key);
        }

        @Override
        public K nextKey(K key) {
            return ((DualTreeBidiMap)this.decorated()).nextKey(key);
        }
    }
}

