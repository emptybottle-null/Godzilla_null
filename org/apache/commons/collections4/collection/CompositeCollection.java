/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeCollection<E>
implements Collection<E>,
Serializable {
    private static final long serialVersionUID = 8417515734108306801L;
    private CollectionMutator<E> mutator;
    private final List<Collection<E>> all = new ArrayList<Collection<E>>();

    public CompositeCollection() {
    }

    public CompositeCollection(Collection<E> compositeCollection) {
        this.addComposited(compositeCollection);
    }

    public CompositeCollection(Collection<E> compositeCollection1, Collection<E> compositeCollection2) {
        this.addComposited(compositeCollection1, compositeCollection2);
    }

    public CompositeCollection(Collection<E> ... compositeCollections) {
        this.addComposited(compositeCollections);
    }

    @Override
    public int size() {
        int size = 0;
        for (Collection<E> item : this.all) {
            size += item.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Collection<E> item : this.all) {
            if (item.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object obj) {
        for (Collection<E> item : this.all) {
            if (!item.contains(obj)) continue;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        IteratorChain<E> chain = new IteratorChain<E>();
        for (Collection<E> item : this.all) {
            chain.addIterator(item.iterator());
        }
        return chain;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        int i = 0;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result[i] = it.next();
            ++i;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int size = this.size();
        Object[] result = null;
        result = array.length >= size ? array : (Object[])Array.newInstance(array.getClass().getComponentType(), size);
        int offset = 0;
        for (Collection<E> item : this.all) {
            for (E e : item) {
                result[offset++] = e;
            }
        }
        if (result.length > size) {
            result[size] = null;
        }
        return result;
    }

    @Override
    public boolean add(E obj) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.add(this, this.all, obj);
    }

    @Override
    public boolean remove(Object obj) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.remove(this, this.all, obj);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        if (coll == null) {
            return false;
        }
        for (Object item : coll) {
            if (this.contains(item)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.addAll(this, this.all, coll);
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return false;
        }
        boolean changed = false;
        for (Collection<E> item : this.all) {
            changed |= item.removeAll(coll);
        }
        return changed;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        if (Objects.isNull(filter)) {
            return false;
        }
        boolean changed = false;
        for (Collection<? super E> collection : this.all) {
            changed |= collection.removeIf(filter);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean changed = false;
        if (coll != null) {
            for (Collection<E> item : this.all) {
                changed |= item.retainAll(coll);
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        for (Collection<E> coll : this.all) {
            coll.clear();
        }
    }

    public void setMutator(CollectionMutator<E> mutator) {
        this.mutator = mutator;
    }

    public void addComposited(Collection<E> compositeCollection) {
        if (compositeCollection != null) {
            this.all.add(compositeCollection);
        }
    }

    public void addComposited(Collection<E> compositeCollection1, Collection<E> compositeCollection2) {
        if (compositeCollection1 != null) {
            this.all.add(compositeCollection1);
        }
        if (compositeCollection2 != null) {
            this.all.add(compositeCollection2);
        }
    }

    public void addComposited(Collection<E> ... compositeCollections) {
        for (Collection<E> compositeCollection : compositeCollections) {
            if (compositeCollection == null) continue;
            this.all.add(compositeCollection);
        }
    }

    public void removeComposited(Collection<E> coll) {
        this.all.remove(coll);
    }

    public Collection<E> toCollection() {
        return new ArrayList(this);
    }

    public List<Collection<E>> getCollections() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    protected CollectionMutator<E> getMutator() {
        return this.mutator;
    }

    public static interface CollectionMutator<E>
    extends Serializable {
        public boolean add(CompositeCollection<E> var1, List<Collection<E>> var2, E var3);

        public boolean addAll(CompositeCollection<E> var1, List<Collection<E>> var2, Collection<? extends E> var3);

        public boolean remove(CompositeCollection<E> var1, List<Collection<E>> var2, Object var3);
    }
}

