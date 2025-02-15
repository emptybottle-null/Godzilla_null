/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;

public class PredicatedBag<E>
extends PredicatedCollection<E>
implements Bag<E> {
    private static final long serialVersionUID = -2575833140344736876L;

    public static <E> PredicatedBag<E> predicatedBag(Bag<E> bag, Predicate<? super E> predicate) {
        return new PredicatedBag<E>(bag, predicate);
    }

    protected PredicatedBag(Bag<E> bag, Predicate<? super E> predicate) {
        super(bag, predicate);
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
    public boolean add(E object, int count) {
        this.validate(object);
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

    @Override
    public int getCount(Object object) {
        return this.decorated().getCount(object);
    }
}

