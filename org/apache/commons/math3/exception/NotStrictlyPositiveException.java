/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;

public class NotStrictlyPositiveException
extends NumberIsTooSmallException {
    private static final long serialVersionUID = -7824848630829852237L;

    public NotStrictlyPositiveException(Number value) {
        super(value, (Number)INTEGER_ZERO, false);
    }

    public NotStrictlyPositiveException(Localizable specific, Number value) {
        super(specific, value, INTEGER_ZERO, false);
    }
}

