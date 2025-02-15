/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Iterator;

public class SingleIterator<T>
implements Iterator<T> {
    private boolean first = true;
    private T object;

    public SingleIterator(T object) {
        this.object = object;
    }

    @Override
    public boolean hasNext() {
        return this.first;
    }

    @Override
    public T next() {
        T answer = this.object;
        this.object = null;
        this.first = false;
        return answer;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported by this iterator");
    }
}

