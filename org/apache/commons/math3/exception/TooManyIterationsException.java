/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class TooManyIterationsException
extends MaxCountExceededException {
    private static final long serialVersionUID = 20121211L;

    public TooManyIterationsException(Number max) {
        super(max);
        this.getContext().addMessage(LocalizedFormats.ITERATIONS, new Object[0]);
    }
}

