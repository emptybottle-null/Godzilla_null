/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class FractionConversionException
extends ConvergenceException {
    private static final long serialVersionUID = -4661812640132576263L;

    public FractionConversionException(double value, int maxIterations) {
        super(LocalizedFormats.FAILED_FRACTION_CONVERSION, value, maxIterations);
    }

    public FractionConversionException(double value, long p, long q) {
        super(LocalizedFormats.FRACTION_CONVERSION_OVERFLOW, value, p, q);
    }
}

