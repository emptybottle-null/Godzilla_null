/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Predicate;

public final class ExceptionPredicate<T>
implements Predicate<T>,
Serializable {
    private static final long serialVersionUID = 7179106032121985545L;
    public static final Predicate INSTANCE = new ExceptionPredicate();

    public static <T> Predicate<T> exceptionPredicate() {
        return INSTANCE;
    }

    private ExceptionPredicate() {
    }

    @Override
    public boolean evaluate(T object) {
        throw new FunctorException("ExceptionPredicate invoked");
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

