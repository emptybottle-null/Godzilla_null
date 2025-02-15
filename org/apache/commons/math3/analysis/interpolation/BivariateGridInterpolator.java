/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public interface BivariateGridInterpolator {
    public BivariateFunction interpolate(double[] var1, double[] var2, double[][] var3) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException;
}

