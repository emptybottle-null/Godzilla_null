/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractQueueDecorator<E>
extends AbstractCollectionDecorator<E>
implements Queue<E> {
    private static final long serialVersionUID = -2629815475789577029L;

    protected AbstractQueueDecorator() {
    }

    protected AbstractQueueDecorator(Queue<E> queue) {
        super(queue);
    }

    @Override
    protected Queue<E> decorated() {
        return (Queue)super.decorated();
    }

    @Override
    public boolean offer(E obj) {
        return this.decorated().offer(obj);
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

