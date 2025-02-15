/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class ConvergenceException
extends MathIllegalStateException {
    private static final long serialVersionUID = 4330003017885151975L;

    public ConvergenceException() {
        this(LocalizedFormats.CONVERGENCE_FAILED, new Object[0]);
    }

    public ConvergenceException(Localizable pattern, Object ... args) {
        this.getContext().addMessage(pattern, args);
    }
}

