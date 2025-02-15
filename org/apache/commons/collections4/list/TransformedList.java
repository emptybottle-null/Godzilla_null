/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;

public class TransformedList<E>
extends TransformedCollection<E>
implements List<E> {
    private static final long serialVersionUID = 1077193035000013141L;

    public static <E> TransformedList<E> transformingList(List<E> list, Transformer<? super E, ? extends E> transformer) {
        return new TransformedList<E>(list, transformer);
    }

    public static <E> TransformedList<E> transformedList(List<E> list, Transformer<? super E, ? extends E> transformer) {
        TransformedList<E> decorated = new TransformedList<E>(list, transformer);
        if (list.size() > 0) {
            Object[] values = list.toArray();
            list.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedList(List<E> list, Transformer<? super E, ? extends E> transformer) {
        super(list, transformer);
    }

    protected List<E> getList() {
        return (List)this.decorated();
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
        return this.getList().get(index);
    }

    @Override
    public int indexOf(Object object) {
        return this.getList().indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.getList().lastIndexOf(object);
    }

    @Override
    public E remove(int index) {
        return this.getList().remove(index);
    }

    @Override
    public void add(int index, E object) {
        object = this.transform(object);
        this.getList().add(index, object);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        coll = this.transform(coll);
        return this.getList().addAll(index, coll);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new TransformedListIterator(this.getList().listIterator(i));
    }

    @Override
    public E set(int index, E object) {
        object = this.transform(object);
        return this.getList().set(index, object);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sub = this.getList().subList(fromIndex, toIndex);
        return new TransformedList<E>(sub, this.transformer);
    }

    protected class TransformedListIterator
    extends AbstractListIteratorDecorator<E> {
        protected TransformedListIterator(ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(E object) {
            object = TransformedList.this.transform(object);
            this.getListIterator().add(object);
        }

        @Override
        public void set(E object) {
            object = TransformedList.this.transform(object);
            this.getListIterator().set(object);
        }
    }
}

