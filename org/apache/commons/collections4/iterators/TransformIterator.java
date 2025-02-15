/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Transformer;

public class TransformIterator<I, O>
implements Iterator<O> {
    private Iterator<? extends I> iterator;
    private Transformer<? super I, ? extends O> transformer;

    public TransformIterator() {
    }

    public TransformIterator(Iterator<? extends I> iterator) {
        this.iterator = iterator;
    }

    public TransformIterator(Iterator<? extends I> iterator, Transformer<? super I, ? extends O> transformer) {
        this.iterator = iterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public O next() {
        return this.transform(this.iterator.next());
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

    public Iterator<? extends I> getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator<? extends I> iterator) {
        this.iterator = iterator;
    }

    public Transformer<? super I, ? extends O> getTransformer() {
        return this.transformer;
    }

    public void setTransformer(Transformer<? super I, ? extends O> transformer) {
        this.transformer = transformer;
    }

    protected O transform(I source) {
        return this.transformer.transform(source);
    }
}

