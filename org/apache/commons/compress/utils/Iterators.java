/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Iterators {
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> iterator) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iterator);
        boolean wasModified = false;
        while (iterator.hasNext()) {
            wasModified |= collection.add(iterator.next());
        }
        return wasModified;
    }

    private Iterators() {
    }
}

