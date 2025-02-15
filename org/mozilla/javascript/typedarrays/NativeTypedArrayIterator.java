/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.typedarrays.NativeTypedArrayView;

public class NativeTypedArrayIterator<T>
implements ListIterator<T> {
    private final NativeTypedArrayView<T> view;
    private int position;
    private int lastPosition = -1;

    NativeTypedArrayIterator(NativeTypedArrayView<T> view, int start) {
        this.view = view;
        this.position = start;
    }

    @Override
    public boolean hasNext() {
        return this.position < this.view.length;
    }

    @Override
    public boolean hasPrevious() {
        return this.position > 0;
    }

    @Override
    public int nextIndex() {
        return this.position;
    }

    @Override
    public int previousIndex() {
        return this.position - 1;
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            Object ret = this.view.get(this.position);
            this.lastPosition = this.position++;
            return (T)ret;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T previous() {
        if (this.hasPrevious()) {
            --this.position;
            this.lastPosition = this.position;
            return (T)this.view.get(this.position);
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(T t) {
        if (this.lastPosition < 0) {
            throw new IllegalStateException();
        }
        this.view.js_set(this.lastPosition, t);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}

