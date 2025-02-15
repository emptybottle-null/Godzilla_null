/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.AbstractMultiValuedMap;

public abstract class AbstractListValuedMap<K, V>
extends AbstractMultiValuedMap<K, V>
implements ListValuedMap<K, V> {
    protected AbstractListValuedMap() {
    }

    protected AbstractListValuedMap(Map<K, ? extends List<V>> map) {
        super(map);
    }

    @Override
    protected Map<K, List<V>> getMap() {
        return super.getMap();
    }

    @Override
    protected abstract List<V> createCollection();

    @Override
    public List<V> get(K key) {
        return this.wrappedCollection((Object)key);
    }

    @Override
    List<V> wrappedCollection(K key) {
        return new WrappedList(key);
    }

    @Override
    public List<V> remove(Object key) {
        return ListUtils.emptyIfNull(this.getMap().remove(key));
    }

    private class ValuesListIterator
    implements ListIterator<V> {
        private final K key;
        private List<V> values;
        private ListIterator<V> iterator;

        public ValuesListIterator(K key) {
            this.key = key;
            this.values = ListUtils.emptyIfNull(AbstractListValuedMap.this.getMap().get(key));
            this.iterator = this.values.listIterator();
        }

        public ValuesListIterator(K key, int index) {
            this.key = key;
            this.values = ListUtils.emptyIfNull(AbstractListValuedMap.this.getMap().get(key));
            this.iterator = this.values.listIterator(index);
        }

        @Override
        public void add(V value) {
            if (AbstractListValuedMap.this.getMap().get(this.key) == null) {
                Collection list = AbstractListValuedMap.this.createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, (List<Collection>)list);
                this.values = list;
                this.iterator = list.listIterator();
            }
            this.iterator.add(value);
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        @Override
        public V next() {
            return this.iterator.next();
        }

        @Override
        public int nextIndex() {
            return this.iterator.nextIndex();
        }

        @Override
        public V previous() {
            return this.iterator.previous();
        }

        @Override
        public int previousIndex() {
            return this.iterator.previousIndex();
        }

        @Override
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                AbstractListValuedMap.this.getMap().remove(this.key);
            }
        }

        @Override
        public void set(V value) {
            this.iterator.set(value);
        }
    }

    private class WrappedList
    extends AbstractMultiValuedMap.WrappedCollection
    implements List<V> {
        public WrappedList(K key) {
            super(AbstractListValuedMap.this, key);
        }

        protected List<V> getMapping() {
            return AbstractListValuedMap.this.getMap().get(this.key);
        }

        @Override
        public void add(int index, V value) {
            Collection list = this.getMapping();
            if (list == null) {
                list = AbstractListValuedMap.this.createCollection();
                AbstractListValuedMap.this.getMap().put(this.key, (List<Collection>)list);
            }
            list.add(index, value);
        }

        @Override
        public boolean addAll(int index, Collection<? extends V> c) {
            Collection list = this.getMapping();
            if (list == null) {
                list = AbstractListValuedMap.this.createCollection();
                boolean changed = list.addAll(index, c);
                if (changed) {
                    AbstractListValuedMap.this.getMap().put(this.key, (List<Collection>)list);
                }
                return changed;
            }
            return list.addAll(index, c);
        }

        @Override
        public V get(int index) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            return list.get(index);
        }

        @Override
        public int indexOf(Object o) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            return list.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            return list.lastIndexOf(o);
        }

        @Override
        public ListIterator<V> listIterator() {
            return new ValuesListIterator(this.key);
        }

        @Override
        public ListIterator<V> listIterator(int index) {
            return new ValuesListIterator(this.key, index);
        }

        @Override
        public V remove(int index) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            Object value = list.remove(index);
            if (list.isEmpty()) {
                AbstractListValuedMap.this.remove(this.key);
            }
            return value;
        }

        @Override
        public V set(int index, V value) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            return list.set(index, value);
        }

        @Override
        public List<V> subList(int fromIndex, int toIndex) {
            List list = ListUtils.emptyIfNull(this.getMapping());
            return list.subList(fromIndex, toIndex);
        }

        @Override
        public boolean equals(Object other) {
            Collection list = this.getMapping();
            if (list == null) {
                return Collections.emptyList().equals(other);
            }
            if (!(other instanceof List)) {
                return false;
            }
            List otherList = (List)other;
            return ListUtils.isEqualList(list, otherList);
        }

        @Override
        public int hashCode() {
            Collection list = this.getMapping();
            return ListUtils.hashCodeForList(list);
        }
    }
}

