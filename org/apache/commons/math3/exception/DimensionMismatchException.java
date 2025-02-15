/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class DimensionMismatchException
extends MathIllegalNumberException {
    private static final long serialVersionUID = -8415396756375798143L;
    private final int dimension;

    public DimensionMismatchException(Localizable specific, int wrong, int expected) {
        super(specific, wrong, expected);
        this.dimension = expected;
    }

    public DimensionMismatchException(int wrong, int expected) {
        this((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, wrong, expected);
    }

    public int getDimension() {
        return this.dimension;
    }
}

