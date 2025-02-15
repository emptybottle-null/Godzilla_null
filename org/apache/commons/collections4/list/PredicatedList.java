/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;

public class PredicatedList<E>
extends PredicatedCollection<E>
implements List<E> {
    private static final long serialVersionUID = -5722039223898659102L;

    public static <T> PredicatedList<T> predicatedList(List<T> list, Predicate<? super T> predicate) {
        return new PredicatedList<T>(list, predicate);
    }

    protected PredicatedList(List<E> list, Predicate<? super E> predicate) {
        super(list, predicate);
    }

    @Override
    protected List<E> decorated() {
        return (List)super.decorated();
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
    public E get(int index) {
        return this.decorated().get(index);
    }

    @Override
    public int indexOf(Object object) {
        return this.decorated().indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.decorated().lastIndexOf(object);
    }

    @Override
    public E remove(int index) {
        return this.decorated().remove(index);
    }

    @Override
    public void add(int index, E object) {
        this.validate(object);
        this.decorated().add(index, object);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        for (E aColl : coll) {
            this.validate(aColl);
        }
        return this.decorated().addAll(index, coll);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new PredicatedListIterator(this.decorated().listIterator(i));
    }

    @Override
    public E set(int index, E object) {
        this.validate(object);
        return this.decorated().set(index, object);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List sub = this.decorated().subList(fromIndex, toIndex);
        return new PredicatedList(sub, this.predicate);
    }

    protected class PredicatedListIterator
    extends AbstractListIteratorDecorator<E> {
        protected PredicatedListIterator(ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(E object) {
            PredicatedList.this.validate(object);
            this.getListIterator().add(object);
        }

        @Override
        public void set(E object) {
            PredicatedList.this.validate(object);
            this.getListIterator().set(object);
        }
    }
}

