/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class SynchronizedCollection<E>
implements Collection<E>,
Serializable {
    private static final long serialVersionUID = 2412805092710877986L;
    private final Collection<E> collection;
    protected final Object lock;

    public static <T> SynchronizedCollection<T> synchronizedCollection(Collection<T> coll) {
        return new SynchronizedCollection<T>(coll);
    }

    protected SynchronizedCollection(Collection<E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null.");
        }
        this.collection = collection;
        this.lock = this;
    }

    protected SynchronizedCollection(Collection<E> collection, Object lock) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null.");
        }
        if (lock == null) {
            throw new NullPointerException("Lock must not be null.");
        }
        this.collection = collection;
        this.lock = lock;
    }

    protected Collection<E> decorated() {
        return this.collection;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean add(E object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().add(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean addAll(Collection<? extends E> coll) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().addAll(coll);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clear() {
        Object object = this.lock;
        synchronized (object) {
            this.decorated().clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean contains(Object object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().contains(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsAll(Collection<?> coll) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().containsAll(coll);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEmpty() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().isEmpty();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.decorated().iterator();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object[] toArray() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().toArray();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T[] toArray(T[] object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().toArray(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean remove(Object object) {
        Object object2 = this.lock;
        synchronized (object2) {
            return this.decorated().remove(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().removeIf(filter);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeAll(Collection<?> coll) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().removeAll(coll);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainAll(Collection<?> coll) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().retainAll(coll);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean equals(Object object) {
        Object object2 = this.lock;
        synchronized (object2) {
            if (object == this) {
                return true;
            }
            return object == this || this.decorated().equals(object);
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
    public String toString() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().toString();
        }
    }
}

