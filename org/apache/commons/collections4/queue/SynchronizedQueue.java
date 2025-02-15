/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedQueue<E>
extends SynchronizedCollection<E>
implements Queue<E> {
    private static final long serialVersionUID = 1L;

    public static <E> SynchronizedQueue<E> synchronizedQueue(Queue<E> queue) {
        return new SynchronizedQueue<E>(queue);
    }

    protected SynchronizedQueue(Queue<E> queue) {
        super(queue);
    }

    protected SynchronizedQueue(Queue<E> queue, Object lock) {
        super(queue, lock);
    }

    @Override
    protected Queue<E> decorated() {
        return (Queue)super.decorated();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public E element() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().element();
        }
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
            return ((Object)this.decorated()).equals(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int hashCode() {
        Object object = this.lock;
        synchronized (object) {
            return ((Object)this.decorated()).hashCode();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean offer(E e) {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().offer(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public E peek() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().peek();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public E poll() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().poll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public E remove() {
        Object object = this.lock;
        synchronized (object) {
            return this.decorated().remove();
        }
    }
}

