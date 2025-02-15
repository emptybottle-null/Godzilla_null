/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractEmptyIterator;

public class EmptyOrderedIterator<E>
extends AbstractEmptyIterator<E>
implements OrderedIterator<E>,
ResettableIterator<E> {
    public static final OrderedIterator INSTANCE = new EmptyOrderedIterator();

    public static <E> OrderedIterator<E> emptyOrderedIterator() {
        return INSTANCE;
    }

    protected EmptyOrderedIterator() {
    }
}

