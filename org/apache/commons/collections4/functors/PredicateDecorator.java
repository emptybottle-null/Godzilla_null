/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;

public interface PredicateDecorator<T>
extends Predicate<T> {
    public Predicate<? super T>[] getPredicates();
}

