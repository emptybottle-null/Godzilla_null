/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;

public final class ExceptionFactory<T>
implements Factory<T>,
Serializable {
    private static final long serialVersionUID = 7179106032121985545L;
    public static final Factory INSTANCE = new ExceptionFactory();

    public static <T> Factory<T> exceptionFactory() {
        return INSTANCE;
    }

    private ExceptionFactory() {
    }

    @Override
    public T create() {
        throw new FunctorException("ExceptionFactory invoked");
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

