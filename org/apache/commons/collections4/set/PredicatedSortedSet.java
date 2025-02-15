/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.SortedSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.set.PredicatedSet;

public class PredicatedSortedSet<E>
extends PredicatedSet<E>
implements SortedSet<E> {
    private static final long serialVersionUID = -9110948148132275052L;

    public static <E> PredicatedSortedSet<E> predicatedSortedSet(SortedSet<E> set, Predicate<? super E> predicate) {
        return new PredicatedSortedSet<E>(set, predicate);
    }

    protected PredicatedSortedSet(SortedSet<E> set, Predicate<? super E> predicate) {
        super(set, predicate);
    }

    @Override
    protected SortedSet<E> decorated() {
        return (SortedSet)super.decorated();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.decorated().comparator();
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
    public SortedSet<E> subSet(E fromElement, E toElement) {
        SortedSet<E> sub = this.decorated().subSet(fromElement, toElement);
        return new PredicatedSortedSet<E>(sub, this.predicate);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        SortedSet<E> head = this.decorated().headSet(toElement);
        return new PredicatedSortedSet<E>(head, this.predicate);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        SortedSet<E> tail = this.decorated().tailSet(fromElement);
        return new PredicatedSortedSet<E>(tail, this.predicate);
    }
}

