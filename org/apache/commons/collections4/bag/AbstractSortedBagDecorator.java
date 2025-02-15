/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.bag.AbstractBagDecorator;

public abstract class AbstractSortedBagDecorator<E>
extends AbstractBagDecorator<E>
implements SortedBag<E> {
    private static final long serialVersionUID = -8223473624050467718L;

    protected AbstractSortedBagDecorator() {
    }

    protected AbstractSortedBagDecorator(SortedBag<E> bag) {
        super(bag);
    }

    @Override
    protected SortedBag<E> decorated() {
        return (SortedBag)super.decorated();
    }

    @Override
    public E first() {
        return this.decorated().first();
    }

    @Override
    public E last() {
        return this.decorated().last();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.decorated().comparator();
    }
}

