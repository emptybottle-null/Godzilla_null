/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NotFiniteNumberException
extends MathIllegalNumberException {
    private static final long serialVersionUID = -6100997100383932834L;

    public NotFiniteNumberException(Number wrong, Object ... args) {
        this((Localizable)LocalizedFormats.NOT_FINITE_NUMBER, wrong, args);
    }

    public NotFiniteNumberException(Localizable specific, Number wrong, Object ... args) {
        super(specific, wrong, args);
    }
}

