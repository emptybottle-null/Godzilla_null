/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;

public class ObjectGraphIterator<E>
implements Iterator<E> {
    private final Deque<Iterator<? extends E>> stack = new ArrayDeque<Iterator<? extends E>>(8);
    private E root;
    private final Transformer<? super E, ? extends E> transformer;
    private boolean hasNext = false;
    private Iterator<? extends E> currentIterator;
    private E currentValue;
    private Iterator<? extends E> lastUsedIterator;

    public ObjectGraphIterator(E root, Transformer<? super E, ? extends E> transformer) {
        if (root instanceof Iterator) {
            this.currentIterator = (Iterator)root;
        } else {
            this.root = root;
        }
        this.transformer = transformer;
    }

    public ObjectGraphIterator(Iterator<? extends E> rootIterator) {
        this.currentIterator = rootIterator;
        this.transformer = null;
    }

    protected void updateCurrentIterator() {
        if (this.hasNext) {
            return;
        }
        if (this.currentIterator == null) {
            if (this.root != null) {
                if (this.transformer == null) {
                    this.findNext(this.root);
                } else {
                    this.findNext(this.transformer.transform(this.root));
                }
                this.root = null;
            }
        } else {
            this.findNextByIterator(this.currentIterator);
        }
    }

    protected void findNext(E value) {
        if (value instanceof Iterator) {
            this.findNextByIterator((Iterator)value);
        } else {
            this.currentValue = value;
            this.hasNext = true;
        }
    }

    protected void findNextByIterator(Iterator<? extends E> iterator) {
        if (iterator != this.currentIterator) {
            if (this.currentIterator != null) {
                this.stack.push(this.currentIterator);
            }
            this.currentIterator = iterator;
        }
        while (this.currentIterator.hasNext() && !this.hasNext) {
            E next = this.currentIterator.next();
            if (this.transformer != null) {
                next = this.transformer.transform(next);
            }
            this.findNext(next);
        }
        if (!this.hasNext && !this.stack.isEmpty()) {
            this.currentIterator = this.stack.pop();
            this.findNextByIterator(this.currentIterator);
        }
    }

    @Override
    public boolean hasNext() {
        this.updateCurrentIterator();
        return this.hasNext;
    }

    @Override
    public E next() {
        this.updateCurrentIterator();
        if (!this.hasNext) {
            throw new NoSuchElementException("No more elements in the iteration");
        }
        this.lastUsedIterator = this.currentIterator;
        E result = this.currentValue;
        this.currentValue = null;
        this.hasNext = false;
        return result;
    }

    @Override
    public void remove() {
        if (this.lastUsedIterator == null) {
            throw new IllegalStateException("Iterator remove() cannot be called at this time");
        }
        this.lastUsedIterator.remove();
        this.lastUsedIterator = null;
    }
}

