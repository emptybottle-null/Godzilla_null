/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class LinearInterpolator
implements UnivariateInterpolator {
    public PolynomialSplineFunction interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (x.length != y.length) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (x.length < 2) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.NUMBER_OF_POINTS, (Number)x.length, 2, true);
        }
        int n = x.length - 1;
        MathArrays.checkOrder(x);
        double[] m = new double[n];
        for (int i = 0; i < n; ++i) {
            m[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
        }
        PolynomialFunction[] polynomials = new PolynomialFunction[n];
        double[] coefficients = new double[2];
        for (int i = 0; i < n; ++i) {
            coefficients[0] = y[i];
            coefficients[1] = m[i];
            polynomials[i] = new PolynomialFunction(coefficients);
        }
        return new PolynomialSplineFunction(x, polynomials);
    }
}

