/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.HPSFException;

public class UnexpectedPropertySetTypeException
extends HPSFException {
    public UnexpectedPropertySetTypeException() {
    }

    public UnexpectedPropertySetTypeException(String msg) {
        super(msg);
    }

    public UnexpectedPropertySetTypeException(Throwable reason) {
        super(reason);
    }

    public UnexpectedPropertySetTypeException(String msg, Throwable reason) {
        super(msg, reason);
    }
}

