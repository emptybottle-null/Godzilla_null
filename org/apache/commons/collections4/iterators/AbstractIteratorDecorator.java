/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator;

public abstract class AbstractIteratorDecorator<E>
extends AbstractUntypedIteratorDecorator<E, E> {
    protected AbstractIteratorDecorator(Iterator<E> iterator) {
        super(iterator);
    }

    @Override
    public E next() {
        return (E)this.getIterator().next();
    }
}

