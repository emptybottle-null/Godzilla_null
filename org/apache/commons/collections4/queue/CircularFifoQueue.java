/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.queue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.apache.commons.collections4.BoundedCollection;

public class CircularFifoQueue<E>
extends AbstractCollection<E>
implements Queue<E>,
BoundedCollection<E>,
Serializable {
    private static final long serialVersionUID = -8423413834657610406L;
    private transient E[] elements;
    private transient int start = 0;
    private transient int end = 0;
    private transient boolean full = false;
    private final int maxElements;

    public CircularFifoQueue() {
        this(32);
    }

    public CircularFifoQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        this.elements = new Object[size];
        this.maxElements = this.elements.length;
    }

    public CircularFifoQueue(Collection<? extends E> coll) {
        this(coll.size());
        this.addAll(coll);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(this.size());
        for (E e : this) {
            out.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.elements = new Object[this.maxElements];
        int size = in.readInt();
        for (int i = 0; i < size; ++i) {
            this.elements[i] = in.readObject();
        }
        this.start = 0;
        this.full = size == this.maxElements;
        this.end = this.full ? 0 : size;
    }

    @Override
    public int size() {
        int size = 0;
        size = this.end < this.start ? this.maxElements - this.start + this.end : (this.end == this.start ? (this.full ? this.maxElements : 0) : this.end - this.start);
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public boolean isAtFullCapacity() {
        return this.size() == this.maxElements;
    }

    @Override
    public int maxSize() {
        return this.maxElements;
    }

    @Override
    public void clear() {
        this.full = false;
        this.start = 0;
        this.end = 0;
        Arrays.fill(this.elements, null);
    }

    @Override
    public boolean add(E element) {
        if (null == element) {
            throw new NullPointerException("Attempted to add null object to queue");
        }
        if (this.isAtFullCapacity()) {
            this.remove();
        }
        this.elements[this.end++] = element;
        if (this.end >= this.maxElements) {
            this.end = 0;
        }
        if (this.end == this.start) {
            this.full = true;
        }
        return true;
    }

    public E get(int index) {
        int sz = this.size();
        if (index < 0 || index >= sz) {
            throw new NoSuchElementException(String.format("The specified index (%1$d) is outside the available range [0, %2$d)", index, sz));
        }
        int idx = (this.start + index) % this.maxElements;
        return this.elements[idx];
    }

    @Override
    public boolean offer(E element) {
        return this.add(element);
    }

    @Override
    public E poll() {
        if (this.isEmpty()) {
            return null;
        }
        return this.remove();
    }

    @Override
    public E element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return this.peek();
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.elements[this.start];
    }

    @Override
    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        E element = this.elements[this.start];
        if (null != element) {
            this.elements[this.start++] = null;
            if (this.start >= this.maxElements) {
                this.start = 0;
            }
            this.full = false;
        }
        return element;
    }

    private int increment(int index) {
        if (++index >= this.maxElements) {
            index = 0;
        }
        return index;
    }

    private int decrement(int index) {
        if (--index < 0) {
            index = this.maxElements - 1;
        }
        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>(){
            private int index;
            private int lastReturnedIndex;
            private boolean isFirst;
            {
                this.index = CircularFifoQueue.this.start;
                this.lastReturnedIndex = -1;
                this.isFirst = CircularFifoQueue.this.full;
            }

            @Override
            public boolean hasNext() {
                return this.isFirst || this.index != CircularFifoQueue.this.end;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.isFirst = false;
                this.lastReturnedIndex = this.index;
                this.index = CircularFifoQueue.this.increment(this.index);
                return CircularFifoQueue.this.elements[this.lastReturnedIndex];
            }

            @Override
            public void remove() {
                if (this.lastReturnedIndex == -1) {
                    throw new IllegalStateException();
                }
                if (this.lastReturnedIndex == CircularFifoQueue.this.start) {
                    CircularFifoQueue.this.remove();
                    this.lastReturnedIndex = -1;
                    return;
                }
                int pos = this.lastReturnedIndex + 1;
                if (CircularFifoQueue.this.start < this.lastReturnedIndex && pos < CircularFifoQueue.this.end) {
                    System.arraycopy(CircularFifoQueue.this.elements, pos, CircularFifoQueue.this.elements, this.lastReturnedIndex, CircularFifoQueue.this.end - pos);
                } else {
                    while (pos != CircularFifoQueue.this.end) {
                        if (pos >= CircularFifoQueue.this.maxElements) {
                            ((CircularFifoQueue)CircularFifoQueue.this).elements[pos - 1] = CircularFifoQueue.this.elements[0];
                            pos = 0;
                            continue;
                        }
                        ((CircularFifoQueue)CircularFifoQueue.this).elements[((CircularFifoQueue)CircularFifoQueue.this).decrement((int)pos)] = CircularFifoQueue.this.elements[pos];
                        pos = CircularFifoQueue.this.increment(pos);
                    }
                }
                this.lastReturnedIndex = -1;
                CircularFifoQueue.this.end = CircularFifoQueue.this.decrement(CircularFifoQueue.this.end);
                ((CircularFifoQueue)CircularFifoQueue.this).elements[((CircularFifoQueue)CircularFifoQueue.this).end] = null;
                CircularFifoQueue.this.full = false;
                this.index = CircularFifoQueue.this.decrement(this.index);
            }
        };
    }
}

