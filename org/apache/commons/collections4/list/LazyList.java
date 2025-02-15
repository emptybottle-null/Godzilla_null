/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.list.AbstractSerializableListDecorator;

public class LazyList<E>
extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -3677737457567429713L;
    private final Factory<? extends E> factory;
    private final Transformer<Integer, ? extends E> transformer;

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return new LazyList<E>(list, factory);
    }

    public static <E> LazyList<E> lazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        return new LazyList<E>(list, transformer);
    }

    protected LazyList(List<E> list, Factory<? extends E> factory) {
        super(list);
        this.factory = Objects.requireNonNull(factory);
        this.transformer = null;
    }

    protected LazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        super(list);
        this.factory = null;
        this.transformer = Objects.requireNonNull(transformer);
    }

    @Override
    public E get(int index) {
        int size = this.decorated().size();
        if (index < size) {
            Object object = this.decorated().get(index);
            if (object == null) {
                object = this.element(index);
                this.decorated().set(index, object);
                return object;
            }
            return object;
        }
        for (int i = size; i < index; ++i) {
            this.decorated().add(null);
        }
        E object = this.element(index);
        this.decorated().add(object);
        return object;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List sub = this.decorated().subList(fromIndex, toIndex);
        if (this.factory != null) {
            return new LazyList<E>(sub, this.factory);
        }
        if (this.transformer != null) {
            return new LazyList<E>(sub, this.transformer);
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }

    private E element(int index) {
        if (this.factory != null) {
            return this.factory.create();
        }
        if (this.transformer != null) {
            return this.transformer.transform(index);
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }
}

