/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

public final class ExceptionClosure<E>
implements Closure<E>,
Serializable {
    private static final long serialVersionUID = 7179106032121985545L;
    public static final Closure INSTANCE = new ExceptionClosure();

    public static <E> Closure<E> exceptionClosure() {
        return INSTANCE;
    }

    private ExceptionClosure() {
    }

    @Override
    public void execute(E input) {
        throw new FunctorException("ExceptionClosure invoked");
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

