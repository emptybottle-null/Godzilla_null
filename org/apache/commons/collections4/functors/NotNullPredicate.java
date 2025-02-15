/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;

public final class NotNullPredicate<T>
implements Predicate<T>,
Serializable {
    private static final long serialVersionUID = 7533784454832764388L;
    public static final Predicate INSTANCE = new NotNullPredicate();

    public static <T> Predicate<T> notNullPredicate() {
        return INSTANCE;
    }

    private NotNullPredicate() {
    }

    @Override
    public boolean evaluate(T object) {
        return object != null;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

