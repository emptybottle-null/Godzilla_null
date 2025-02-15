/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractListDecorator<E>
extends AbstractCollectionDecorator<E>
implements List<E> {
    private static final long serialVersionUID = 4500739654952315623L;

    protected AbstractListDecorator() {
    }

    protected AbstractListDecorator(List<E> list) {
        super(list);
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
    public void add(int index, E object) {
        this.decorated().add(index, object);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        return this.decorated().addAll(index, coll);
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
    public ListIterator<E> listIterator() {
        return this.decorated().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return this.decorated().listIterator(index);
    }

    @Override
    public E remove(int index) {
        return this.decorated().remove(index);
    }

    @Override
    public E set(int index, E object) {
        return this.decorated().set(index, object);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return this.decorated().subList(fromIndex, toIndex);
    }
}

