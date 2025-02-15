/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.exception.util.ExceptionContextProvider;
import org.apache.commons.math3.exception.util.Localizable;

public class MathRuntimeException
extends RuntimeException
implements ExceptionContextProvider {
    private static final long serialVersionUID = 20120926L;
    private final ExceptionContext context = new ExceptionContext(this);

    public MathRuntimeException(Localizable pattern, Object ... args) {
        this.context.addMessage(pattern, args);
    }

    public ExceptionContext getContext() {
        return this.context;
    }

    public String getMessage() {
        return this.context.getMessage();
    }

    public String getLocalizedMessage() {
        return this.context.getLocalizedMessage();
    }
}

