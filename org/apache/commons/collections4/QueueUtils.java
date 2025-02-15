/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.apache.commons.collections4.queue.TransformedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;

public class QueueUtils {
    public static final Queue EMPTY_QUEUE = UnmodifiableQueue.unmodifiableQueue(new LinkedList());

    private QueueUtils() {
    }

    public static <E> Queue<E> synchronizedQueue(Queue<E> queue) {
        return SynchronizedQueue.synchronizedQueue(queue);
    }

    public static <E> Queue<E> unmodifiableQueue(Queue<? extends E> queue) {
        return UnmodifiableQueue.unmodifiableQueue(queue);
    }

    public static <E> Queue<E> predicatedQueue(Queue<E> queue, Predicate<? super E> predicate) {
        return PredicatedQueue.predicatedQueue(queue, predicate);
    }

    public static <E> Queue<E> transformingQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        return TransformedQueue.transformingQueue(queue, transformer);
    }

    public static <E> Queue<E> emptyQueue() {
        return EMPTY_QUEUE;
    }
}

