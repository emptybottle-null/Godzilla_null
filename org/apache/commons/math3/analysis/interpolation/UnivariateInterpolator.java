/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public interface UnivariateInterpolator {
    public UnivariateFunction interpolate(double[] var1, double[] var2) throws MathIllegalArgumentException, DimensionMismatchException;
}

