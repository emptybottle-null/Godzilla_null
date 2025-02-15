/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public abstract class ContinuedFraction {
    private static final double DEFAULT_EPSILON = 1.0E-8;

    protected ContinuedFraction() {
    }

    protected abstract double getA(int var1, double var2);

    protected abstract double getB(int var1, double var2);

    public double evaluate(double x) throws ConvergenceException {
        return this.evaluate(x, 1.0E-8, Integer.MAX_VALUE);
    }

    public double evaluate(double x, double epsilon) throws ConvergenceException {
        return this.evaluate(x, epsilon, Integer.MAX_VALUE);
    }

    public double evaluate(double x, int maxIterations) throws ConvergenceException, MaxCountExceededException {
        return this.evaluate(x, 1.0E-8, maxIterations);
    }

    public double evaluate(double x, double epsilon, int maxIterations) throws ConvergenceException, MaxCountExceededException {
        int n;
        double small = 1.0E-50;
        double hPrev = this.getA(0, x);
        if (Precision.equals(hPrev, 0.0, 1.0E-50)) {
            hPrev = 1.0E-50;
        }
        double dPrev = 0.0;
        double cPrev = hPrev;
        double hN = hPrev;
        for (n = 1; n < maxIterations; ++n) {
            double deltaN;
            double cN;
            double b;
            double a = this.getA(n, x);
            double dN = a + (b = this.getB(n, x)) * dPrev;
            if (Precision.equals(dN, 0.0, 1.0E-50)) {
                dN = 1.0E-50;
            }
            if (Precision.equals(cN = a + b / cPrev, 0.0, 1.0E-50)) {
                cN = 1.0E-50;
            }
            if (Double.isInfinite(hN = hPrev * (deltaN = cN * (dN = 1.0 / dN)))) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, x);
            }
            if (Double.isNaN(hN)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, x);
            }
            if (FastMath.abs(deltaN - 1.0) < epsilon) break;
            dPrev = dN;
            cPrev = cN;
            hPrev = hN;
        }
        if (n >= maxIterations) {
            throw new MaxCountExceededException((Localizable)LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, maxIterations, x);
        }
        return hN;
    }
}

