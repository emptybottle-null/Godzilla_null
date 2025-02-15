/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

public class FilterIterator<E>
implements Iterator<E> {
    private Iterator<? extends E> iterator;
    private Predicate<? super E> predicate;
    private E nextObject;
    private boolean nextObjectSet = false;

    public FilterIterator() {
    }

    public FilterIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public FilterIterator(Iterator<? extends E> iterator, Predicate<? super E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        return this.nextObjectSet || this.setNextObject();
    }

    @Override
    public E next() {
        if (!this.nextObjectSet && !this.setNextObject()) {
            throw new NoSuchElementException();
        }
        this.nextObjectSet = false;
        return this.nextObject;
    }

    @Override
    public void remove() {
        if (this.nextObjectSet) {
            throw new IllegalStateException("remove() cannot be called");
        }
        this.iterator.remove();
    }

    public Iterator<? extends E> getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(Predicate<? super E> predicate) {
        this.predicate = predicate;
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private boolean setNextObject() {
        while (this.iterator.hasNext()) {
            E object = this.iterator.next();
            if (!this.predicate.evaluate(object)) continue;
            this.nextObject = object;
            this.nextObjectSet = true;
            return true;
        }
        return false;
    }
}

