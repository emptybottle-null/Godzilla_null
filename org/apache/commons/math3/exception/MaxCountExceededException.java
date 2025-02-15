/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MaxCountExceededException
extends MathIllegalStateException {
    private static final long serialVersionUID = 4330003017885151975L;
    private final Number max;

    public MaxCountExceededException(Number max) {
        this((Localizable)LocalizedFormats.MAX_COUNT_EXCEEDED, max, new Object[0]);
    }

    public MaxCountExceededException(Localizable specific, Number max, Object ... args) {
        this.getContext().addMessage(specific, max, args);
        this.max = max;
    }

    public Number getMax() {
        return this.max;
    }
}

