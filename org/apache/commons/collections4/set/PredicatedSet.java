/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;

public class PredicatedSet<E>
extends PredicatedCollection<E>
implements Set<E> {
    private static final long serialVersionUID = -684521469108685117L;

    public static <E> PredicatedSet<E> predicatedSet(Set<E> set, Predicate<? super E> predicate) {
        return new PredicatedSet<E>(set, predicate);
    }

    protected PredicatedSet(Set<E> set, Predicate<? super E> predicate) {
        super(set, predicate);
    }

    @Override
    protected Set<E> decorated() {
        return (Set)super.decorated();
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

