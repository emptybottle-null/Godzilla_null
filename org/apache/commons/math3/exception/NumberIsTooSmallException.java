/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NumberIsTooSmallException
extends MathIllegalNumberException {
    private static final long serialVersionUID = -6100997100383932834L;
    private final Number min;
    private final boolean boundIsAllowed;

    public NumberIsTooSmallException(Number wrong, Number min, boolean boundIsAllowed) {
        this((Localizable)(boundIsAllowed ? LocalizedFormats.NUMBER_TOO_SMALL : LocalizedFormats.NUMBER_TOO_SMALL_BOUND_EXCLUDED), wrong, min, boundIsAllowed);
    }

    public NumberIsTooSmallException(Localizable specific, Number wrong, Number min, boolean boundIsAllowed) {
        super(specific, wrong, min);
        this.min = min;
        this.boundIsAllowed = boundIsAllowed;
    }

    public boolean getBoundIsAllowed() {
        return this.boundIsAllowed;
    }

    public Number getMin() {
        return this.min;
    }
}

