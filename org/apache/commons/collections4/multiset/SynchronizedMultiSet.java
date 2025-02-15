/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedMultiSet<E>
extends SynchronizedCollection<E>
implements MultiSet<E> {
    private static final long serialVersionUID = 20150629L;

    public static <E> SynchronizedMultiSet<E> synchronizedMultiSet(MultiSet<E> multiset) {
        return new SynchronizedMultiSet<E>(multiset);
    }

    protected SynchronizedMultiSet(MultiSet<E> multiset) {
        super(multiset);
    }

    protected SynchronizedMultiSet(MultiSet<E> multiset, Object lock) {
        super(multiset, lock);
    }

    @Override
    protected MultiSet<E> decorated() {
        return (MultiSet)super.decorated();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().equals(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int hashCode() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().hashCode();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int add(E object, int count) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().add(object, count);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int remove(Object object, int count) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().remove(object, count);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getCount(Object object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().getCount(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int setCount(E object, int count) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().setCount(object, count);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Set<E> uniqueSet() {
        Object object = this.lock;
        synchronized (object) {
            Set set = this.decorated().uniqueSet();
            return new SynchronizedSet(set, this.lock);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Set<MultiSet.Entry<E>> entrySet() {
        Object object = this.lock;
        synchronized (object) {
            Set set = this.decorated().entrySet();
            return new SynchronizedSet(set, this.lock);
        }
    }

    static class SynchronizedSet<T>
    extends SynchronizedCollection<T>
    implements Set<T> {
        private static final long serialVersionUID = 20150629L;

        SynchronizedSet(Set<T> set, Object lock) {
            super(set, lock);
        }
    }
}

