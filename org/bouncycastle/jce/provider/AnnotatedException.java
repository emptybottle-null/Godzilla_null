/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import org.bouncycastle.jce.exception.ExtException;

public class AnnotatedException
extends Exception
implements ExtException {
    private Throwable _underlyingException;

    public AnnotatedException(String string, Throwable throwable) {
        super(string);
        this._underlyingException = throwable;
    }

    public AnnotatedException(String string) {
        this(string, null);
    }

    Throwable getUnderlyingException() {
        return this._underlyingException;
    }

    public Throwable getCause() {
        return this._underlyingException;
    }
}

