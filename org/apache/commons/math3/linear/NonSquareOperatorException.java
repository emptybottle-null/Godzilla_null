/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NonSquareOperatorException
extends DimensionMismatchException {
    private static final long serialVersionUID = -4145007524150846242L;

    public NonSquareOperatorException(int wrong, int expected) {
        super((Localizable)LocalizedFormats.NON_SQUARE_OPERATOR, wrong, expected);
    }
}

