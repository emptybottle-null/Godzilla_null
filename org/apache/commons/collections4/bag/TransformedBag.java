/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.set.TransformedSet;

public class TransformedBag<E>
extends TransformedCollection<E>
implements Bag<E> {
    private static final long serialVersionUID = 5421170911299074185L;

    public static <E> Bag<E> transformingBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedBag<E>(bag, transformer);
    }

    public static <E> Bag<E> transformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        TransformedBag<E> decorated = new TransformedBag<E>(bag, transformer);
        if (bag.size() > 0) {
            Object[] values = bag.toArray();
            bag.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    protected Bag<E> getBag() {
        return (Bag)this.decorated();
    }

    @Override
    public boolean equals(Object object) {
        return object == this || this.decorated().equals(object);
    }

    @Override
    public int hashCode() {
        return this.decorated().hashCode();
    }

    @Override
    public int getCount(Object object) {
        return this.getBag().getCount(object);
    }

    @Override
    public boolean remove(Object object, int nCopies) {
        return this.getBag().remove(object, nCopies);
    }

    @Override
    public boolean add(E object, int nCopies) {
        return this.getBag().add(this.transform(object), nCopies);
    }

    @Override
    public Set<E> uniqueSet() {
        Set<E> set = this.getBag().uniqueSet();
        return TransformedSet.transformingSet(set, this.transformer);
    }
}

