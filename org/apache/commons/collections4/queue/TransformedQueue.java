/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;

public class TransformedQueue<E>
extends TransformedCollection<E>
implements Queue<E> {
    private static final long serialVersionUID = -7901091318986132033L;

    public static <E> TransformedQueue<E> transformingQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        return new TransformedQueue<E>(queue, transformer);
    }

    public static <E> TransformedQueue<E> transformedQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        TransformedQueue<E> decorated = new TransformedQueue<E>(queue, transformer);
        if (queue.size() > 0) {
            Object[] values = queue.toArray();
            queue.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        super(queue, transformer);
    }

    protected Queue<E> getQueue() {
        return (Queue)this.decorated();
    }

    @Override
    public boolean offer(E obj) {
        return this.getQueue().offer(this.transform(obj));
    }

    @Override
    public E poll() {
        return this.getQueue().poll();
    }

    @Override
    public E peek() {
        return this.getQueue().peek();
    }

    @Override
    public E element() {
        return this.getQueue().element();
    }

    @Override
    public E remove() {
        return this.getQueue().remove();
    }
}

