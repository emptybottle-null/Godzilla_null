/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

@GwtCompatible
class ConsumingQueueIterator<T>
extends AbstractIterator<T> {
    private final Queue<T> queue;

    ConsumingQueueIterator(T ... elements) {
        this.queue = new ArrayDeque<T>(elements.length);
        Collections.addAll(this.queue, elements);
    }

    ConsumingQueueIterator(Queue<T> queue) {
        this.queue = Preconditions.checkNotNull(queue);
    }

    @Override
    public T computeNext() {
        return this.queue.isEmpty() ? this.endOfData() : this.queue.remove();
    }
}

