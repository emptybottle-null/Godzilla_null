/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedBag<E>
extends SynchronizedCollection<E>
implements Bag<E> {
    private static final long serialVersionUID = 8084674570753837109L;

    public static <E> SynchronizedBag<E> synchronizedBag(Bag<E> bag) {
        return new SynchronizedBag<E>(bag);
    }

    protected SynchronizedBag(Bag<E> bag) {
        super(bag);
    }

    protected SynchronizedBag(Bag<E> bag, Object lock) {
        super(bag, lock);
    }

    protected Bag<E> getBag() {
        return (Bag)this.decorated();
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
            return this.getBag().equals(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int hashCode() {
        Object object = this.lock;
        synchronized (object) {
            return this.getBag().hashCode();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean add(E object, int count) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.getBag().add(object, count);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean remove(Object object, int count) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.getBag().remove(object, count);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Set<E> uniqueSet() {
        Object object = this.lock;
        synchronized (object) {
            Set<E> set = this.getBag().uniqueSet();
            return new SynchronizedBagSet(set, this.lock);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getCount(Object object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.getBag().getCount(object);
        }
    }

    class SynchronizedBagSet
    extends SynchronizedCollection<E>
    implements Set<E> {
        private static final long serialVersionUID = 2990565892366827855L;

        SynchronizedBagSet(Set<E> set, Object lock) {
            super(set, lock);
        }
    }
}

