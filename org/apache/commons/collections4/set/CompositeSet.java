/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeSet<E>
implements Set<E>,
Serializable {
    private static final long serialVersionUID = 5185069727540378940L;
    private SetMutator<E> mutator;
    private final List<Set<E>> all = new ArrayList<Set<E>>();

    public CompositeSet() {
    }

    public CompositeSet(Set<E> set) {
        this.addComposited(set);
    }

    public CompositeSet(Set<E> ... sets) {
        this.addComposited(sets);
    }

    @Override
    public int size() {
        int size = 0;
        for (Set<E> item : this.all) {
            size += item.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Set<E> item : this.all) {
            if (item.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object obj) {
        for (Set<E> item : this.all) {
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
        for (Set<E> item : this.all) {
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
        for (Collection collection : this.all) {
            for (Object e : collection) {
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
            throw new UnsupportedOperationException("add() is not supported on CompositeSet without a SetMutator strategy");
        }
        return this.mutator.add(this, this.all, obj);
    }

    @Override
    public boolean remove(Object obj) {
        for (Set<E> set : this.getSets()) {
            if (!set.contains(obj)) continue;
            return set.remove(obj);
        }
        return false;
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
            throw new UnsupportedOperationException("addAll() is not supported on CompositeSet without a SetMutator strategy");
        }
        return this.mutator.addAll(this, this.all, coll);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        if (Objects.isNull(filter)) {
            return false;
        }
        boolean changed = false;
        for (Collection collection : this.all) {
            changed |= collection.removeIf(filter);
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return false;
        }
        boolean changed = false;
        for (Collection collection : this.all) {
            changed |= collection.removeAll(coll);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean changed = false;
        for (Collection collection : this.all) {
            changed |= collection.retainAll(coll);
        }
        return changed;
    }

    @Override
    public void clear() {
        for (Collection collection : this.all) {
            collection.clear();
        }
    }

    public void setMutator(SetMutator<E> mutator) {
        this.mutator = mutator;
    }

    public synchronized void addComposited(Set<E> set) {
        if (set != null) {
            for (Set<E> existingSet : this.getSets()) {
                Collection<E> intersects = CollectionUtils.intersection(existingSet, set);
                if (intersects.size() <= 0) continue;
                if (this.mutator == null) {
                    throw new UnsupportedOperationException("Collision adding composited set with no SetMutator set");
                }
                this.getMutator().resolveCollision(this, existingSet, set, intersects);
                if (CollectionUtils.intersection(existingSet, set).size() <= 0) continue;
                throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
            }
            this.all.add(set);
        }
    }

    public void addComposited(Set<E> set1, Set<E> set2) {
        this.addComposited(set1);
        this.addComposited(set2);
    }

    public void addComposited(Set<E> ... sets) {
        if (sets != null) {
            for (Set<E> set : sets) {
                this.addComposited(set);
            }
        }
    }

    public void removeComposited(Set<E> set) {
        this.all.remove(set);
    }

    public Set<E> toSet() {
        return new HashSet(this);
    }

    public List<Set<E>> getSets() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    protected SetMutator<E> getMutator() {
        return this.mutator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            Set set = (Set)obj;
            return set.size() == this.size() && set.containsAll(this);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int code = 0;
        for (E e : this) {
            code += e == null ? 0 : e.hashCode();
        }
        return code;
    }

    public static interface SetMutator<E>
    extends Serializable {
        public boolean add(CompositeSet<E> var1, List<Set<E>> var2, E var3);

        public boolean addAll(CompositeSet<E> var1, List<Set<E>> var2, Collection<? extends E> var3);

        public void resolveCollision(CompositeSet<E> var1, Set<E> var2, Set<E> var3, Collection<E> var4);
    }
}

