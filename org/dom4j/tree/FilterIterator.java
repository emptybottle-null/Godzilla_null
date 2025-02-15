/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class FilterIterator<T>
implements Iterator<T> {
    protected Iterator<T> proxy;
    private T next;
    private boolean first = true;

    public FilterIterator(Iterator<T> proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean hasNext() {
        if (this.first) {
            this.next = this.findNext();
            this.first = false;
        }
        return this.next != null;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        T answer = this.next;
        this.next = this.findNext();
        return answer;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    protected abstract boolean matches(T var1);

    protected T findNext() {
        if (this.proxy != null) {
            while (this.proxy.hasNext()) {
                T nextObject = this.proxy.next();
                if (nextObject == null || !this.matches(nextObject)) continue;
                return nextObject;
            }
            this.proxy = null;
        }
        return null;
    }
}

