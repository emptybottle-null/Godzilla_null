/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator;
import org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;

public class PiecewiseBicubicSplineInterpolator
implements BivariateGridInterpolator {
    public PiecewiseBicubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[][] fval) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        if (xval == null || yval == null || fval == null || fval[0] == null) {
            throw new NullArgumentException();
        }
        if (xval.length == 0 || yval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        return new PiecewiseBicubicSplineInterpolatingFunction(xval, yval, fval);
    }
}

