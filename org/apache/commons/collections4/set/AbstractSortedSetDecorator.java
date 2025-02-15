/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import org.apache.commons.collections4.set.AbstractSetDecorator;

public abstract class AbstractSortedSetDecorator<E>
extends AbstractSetDecorator<E>
implements SortedSet<E> {
    private static final long serialVersionUID = -3462240946294214398L;

    protected AbstractSortedSetDecorator() {
    }

    protected AbstractSortedSetDecorator(Set<E> set) {
        super(set);
    }

    @Override
    protected SortedSet<E> decorated() {
        return (SortedSet)super.decorated();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return this.decorated().subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return this.decorated().headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return this.decorated().tailSet(fromElement);
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

