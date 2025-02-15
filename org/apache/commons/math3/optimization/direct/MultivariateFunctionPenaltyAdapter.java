/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class MultivariateFunctionPenaltyAdapter
implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final double[] lower;
    private final double[] upper;
    private final double offset;
    private final double[] scale;

    public MultivariateFunctionPenaltyAdapter(MultivariateFunction bounded, double[] lower, double[] upper, double offset, double[] scale) {
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        MathUtils.checkNotNull(scale);
        if (lower.length != upper.length) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        if (lower.length != scale.length) {
            throw new DimensionMismatchException(lower.length, scale.length);
        }
        for (int i = 0; i < lower.length; ++i) {
            if (upper[i] >= lower[i]) continue;
            throw new NumberIsTooSmallException(upper[i], (Number)lower[i], true);
        }
        this.bounded = bounded;
        this.lower = (double[])lower.clone();
        this.upper = (double[])upper.clone();
        this.offset = offset;
        this.scale = (double[])scale.clone();
    }

    public double value(double[] point) {
        for (int i = 0; i < this.scale.length; ++i) {
            if (!(point[i] < this.lower[i]) && !(point[i] > this.upper[i])) continue;
            double sum = 0.0;
            for (int j = i; j < this.scale.length; ++j) {
                double overshoot = point[j] < this.lower[j] ? this.scale[j] * (this.lower[j] - point[j]) : (point[j] > this.upper[j] ? this.scale[j] * (point[j] - this.upper[j]) : 0.0);
                sum += FastMath.sqrt(overshoot);
            }
            return this.offset + sum;
        }
        return this.bounded.value(point);
    }
}

