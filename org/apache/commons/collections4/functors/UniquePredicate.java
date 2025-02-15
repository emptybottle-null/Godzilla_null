/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.Predicate;

public final class UniquePredicate<T>
implements Predicate<T>,
Serializable {
    private static final long serialVersionUID = -3319417438027438040L;
    private final Set<T> iSet = new HashSet<T>();

    public static <T> Predicate<T> uniquePredicate() {
        return new UniquePredicate<T>();
    }

    @Override
    public boolean evaluate(T object) {
        return this.iSet.add(object);
    }
}

