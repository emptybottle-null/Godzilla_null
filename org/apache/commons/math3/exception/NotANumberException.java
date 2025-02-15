/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NotANumberException
extends MathIllegalNumberException {
    private static final long serialVersionUID = 20120906L;

    public NotANumberException() {
        super((Localizable)LocalizedFormats.NAN_NOT_ALLOWED, Double.NaN, new Object[0]);
    }
}

