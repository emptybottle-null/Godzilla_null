/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class DividedDifferenceInterpolator
implements UnivariateInterpolator,
Serializable {
    private static final long serialVersionUID = 107049519551235069L;

    public PolynomialFunctionNewtonForm interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
        double[] c = new double[x.length - 1];
        System.arraycopy(x, 0, c, 0, c.length);
        double[] a = DividedDifferenceInterpolator.computeDividedDifference(x, y);
        return new PolynomialFunctionNewtonForm(a, c);
    }

    protected static double[] computeDividedDifference(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
        double[] divdiff = (double[])y.clone();
        int n = x.length;
        double[] a = new double[n];
        a[0] = divdiff[0];
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n - i; ++j) {
                double denominator = x[j + i] - x[j];
                divdiff[j] = (divdiff[j + 1] - divdiff[j]) / denominator;
            }
            a[i] = divdiff[0];
        }
        return a;
    }
}

