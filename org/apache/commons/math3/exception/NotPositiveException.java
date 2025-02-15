/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;

public class NotPositiveException
extends NumberIsTooSmallException {
    private static final long serialVersionUID = -2250556892093726375L;

    public NotPositiveException(Number value) {
        super(value, (Number)INTEGER_ZERO, true);
    }

    public NotPositiveException(Localizable specific, Number value) {
        super(specific, value, INTEGER_ZERO, true);
    }
}

