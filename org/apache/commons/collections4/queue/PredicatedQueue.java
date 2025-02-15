/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;

public class PredicatedQueue<E>
extends PredicatedCollection<E>
implements Queue<E> {
    private static final long serialVersionUID = 2307609000539943581L;

    public static <E> PredicatedQueue<E> predicatedQueue(Queue<E> Queue2, Predicate<? super E> predicate) {
        return new PredicatedQueue<E>(Queue2, predicate);
    }

    protected PredicatedQueue(Queue<E> queue, Predicate<? super E> predicate) {
        super(queue, predicate);
    }

    @Override
    protected Queue<E> decorated() {
        return (Queue)super.decorated();
    }

    @Override
    public boolean offer(E object) {
        this.validate(object);
        return this.decorated().offer(object);
    }

    @Override
    public E poll() {
        return this.decorated().poll();
    }

    @Override
    public E peek() {
        return this.decorated().peek();
    }

    @Override
    public E element() {
        return this.decorated().element();
    }

    @Override
    public E remove() {
        return this.decorated().remove();
    }
}

