/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractBagDecorator<E>
extends AbstractCollectionDecorator<E>
implements Bag<E> {
    private static final long serialVersionUID = -3768146017343785417L;

    protected AbstractBagDecorator() {
    }

    protected AbstractBagDecorator(Bag<E> bag) {
        super(bag);
    }

    @Override
    protected Bag<E> decorated() {
        return (Bag)super.decorated();
    }

    @Override
    public boolean equals(Object object) {
        return object == this || ((Object)this.decorated()).equals(object);
    }

    @Override
    public int hashCode() {
        return ((Object)this.decorated()).hashCode();
    }

    @Override
    public int getCount(Object object) {
        return this.decorated().getCount(object);
    }

    @Override
    public boolean add(E object, int count) {
        return this.decorated().add(object, count);
    }

    @Override
    public boolean remove(Object object, int count) {
        return this.decorated().remove(object, count);
    }

    @Override
    public Set<E> uniqueSet() {
        return this.decorated().uniqueSet();
    }
}

