/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractSetDecorator<E>
extends AbstractCollectionDecorator<E>
implements Set<E> {
    private static final long serialVersionUID = -4678668309576958546L;

    protected AbstractSetDecorator() {
    }

    protected AbstractSetDecorator(Set<E> set) {
        super(set);
    }

    @Override
    protected Set<E> decorated() {
        return (Set)super.decorated();
    }

    @Override
    public boolean equals(Object object) {
        return object == this || this.decorated().equals(object);
    }

    @Override
    public int hashCode() {
        return this.decorated().hashCode();
    }
}

