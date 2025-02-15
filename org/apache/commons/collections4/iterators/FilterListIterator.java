/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

public class FilterListIterator<E>
implements ListIterator<E> {
    private ListIterator<? extends E> iterator;
    private Predicate<? super E> predicate;
    private E nextObject;
    private boolean nextObjectSet = false;
    private E previousObject;
    private boolean previousObjectSet = false;
    private int nextIndex = 0;

    public FilterListIterator() {
    }

    public FilterListIterator(ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public FilterListIterator(ListIterator<? extends E> iterator, Predicate<? super E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    public FilterListIterator(Predicate<? super E> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void add(E o) {
        throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
    }

    @Override
    public boolean hasNext() {
        return this.nextObjectSet || this.setNextObject();
    }

    @Override
    public boolean hasPrevious() {
        return this.previousObjectSet || this.setPreviousObject();
    }

    @Override
    public E next() {
        if (!this.nextObjectSet && !this.setNextObject()) {
            throw new NoSuchElementException();
        }
        ++this.nextIndex;
        E temp = this.nextObject;
        this.clearNextObject();
        return temp;
    }

    @Override
    public int nextIndex() {
        return this.nextIndex;
    }

    @Override
    public E previous() {
        if (!this.previousObjectSet && !this.setPreviousObject()) {
            throw new NoSuchElementException();
        }
        --this.nextIndex;
        E temp = this.previousObject;
        this.clearPreviousObject();
        return temp;
    }

    @Override
    public int previousIndex() {
        return this.nextIndex - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
    }

    @Override
    public void set(E o) {
        throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
    }

    public ListIterator<? extends E> getListIterator() {
        return this.iterator;
    }

    public void setListIterator(ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(Predicate<? super E> predicate) {
        this.predicate = predicate;
    }

    private void clearNextObject() {
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private boolean setNextObject() {
        if (this.previousObjectSet) {
            this.clearPreviousObject();
            if (!this.setNextObject()) {
                return false;
            }
            this.clearNextObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasNext()) {
            E object = this.iterator.next();
            if (!this.predicate.evaluate(object)) continue;
            this.nextObject = object;
            this.nextObjectSet = true;
            return true;
        }
        return false;
    }

    private void clearPreviousObject() {
        this.previousObject = null;
        this.previousObjectSet = false;
    }

    private boolean setPreviousObject() {
        if (this.nextObjectSet) {
            this.clearNextObject();
            if (!this.setPreviousObject()) {
                return false;
            }
            this.clearPreviousObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasPrevious()) {
            E object = this.iterator.previous();
            if (!this.predicate.evaluate(object)) continue;
            this.previousObject = object;
            this.previousObjectSet = true;
            return true;
        }
        return false;
    }
}

