/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

public abstract class CatchAndRethrowClosure<E>
implements Closure<E> {
    @Override
    public void execute(E input) {
        try {
            this.executeAndThrow(input);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new FunctorException(t);
        }
    }

    protected abstract void executeAndThrow(E var1) throws Throwable;
}

