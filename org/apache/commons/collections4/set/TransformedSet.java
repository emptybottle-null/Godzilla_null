/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;

public class TransformedSet<E>
extends TransformedCollection<E>
implements Set<E> {
    private static final long serialVersionUID = 306127383500410386L;

    public static <E> TransformedSet<E> transformingSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSet<E>(set, transformer);
    }

    public static <E> Set<E> transformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        TransformedSet<E> decorated = new TransformedSet<E>(set, transformer);
        if (set.size() > 0) {
            Object[] values = set.toArray();
            set.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        super(set, transformer);
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

