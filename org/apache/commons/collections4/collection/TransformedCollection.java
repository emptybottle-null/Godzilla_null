/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public class TransformedCollection<E>
extends AbstractCollectionDecorator<E> {
    private static final long serialVersionUID = 8692300188161871514L;
    protected final Transformer<? super E, ? extends E> transformer;

    public static <E> TransformedCollection<E> transformingCollection(Collection<E> coll, Transformer<? super E, ? extends E> transformer) {
        return new TransformedCollection<E>(coll, transformer);
    }

    public static <E> TransformedCollection<E> transformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        TransformedCollection<E> decorated = new TransformedCollection<E>(collection, transformer);
        if (collection.size() > 0) {
            Object[] values = collection.toArray();
            collection.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedCollection(Collection<E> coll, Transformer<? super E, ? extends E> transformer) {
        super(coll);
        if (transformer == null) {
            throw new NullPointerException("Transformer must not be null");
        }
        this.transformer = transformer;
    }

    protected E transform(E object) {
        return this.transformer.transform(object);
    }

    protected Collection<E> transform(Collection<? extends E> coll) {
        ArrayList<E> list = new ArrayList<E>(coll.size());
        for (E item : coll) {
            list.add(this.transform(item));
        }
        return list;
    }

    @Override
    public boolean add(E object) {
        return this.decorated().add(this.transform(object));
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        return this.decorated().addAll(this.transform(coll));
    }
}

