/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Logit;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class MultivariateFunctionMappingAdapter
implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final Mapper[] mappers;

    public MultivariateFunctionMappingAdapter(MultivariateFunction bounded, double[] lower, double[] upper) {
        int i;
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        if (lower.length != upper.length) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        for (i = 0; i < lower.length; ++i) {
            if (upper[i] >= lower[i]) continue;
            throw new NumberIsTooSmallException(upper[i], (Number)lower[i], true);
        }
        this.bounded = bounded;
        this.mappers = new Mapper[lower.length];
        for (i = 0; i < this.mappers.length; ++i) {
            if (Double.isInfinite(lower[i])) {
                if (Double.isInfinite(upper[i])) {
                    this.mappers[i] = new NoBoundsMapper();
                    continue;
                }
                this.mappers[i] = new UpperBoundMapper(upper[i]);
                continue;
            }
            this.mappers[i] = Double.isInfinite(upper[i]) ? new LowerBoundMapper(lower[i]) : new LowerUpperBoundMapper(lower[i], upper[i]);
        }
    }

    public double[] unboundedToBounded(double[] point) {
        double[] mapped = new double[this.mappers.length];
        for (int i = 0; i < this.mappers.length; ++i) {
            mapped[i] = this.mappers[i].unboundedToBounded(point[i]);
        }
        return mapped;
    }

    public double[] boundedToUnbounded(double[] point) {
        double[] mapped = new double[this.mappers.length];
        for (int i = 0; i < this.mappers.length; ++i) {
            mapped[i] = this.mappers[i].boundedToUnbounded(point[i]);
        }
        return mapped;
    }

    public double value(double[] point) {
        return this.bounded.value(this.unboundedToBounded(point));
    }

    private static class LowerUpperBoundMapper
    implements Mapper {
        private final UnivariateFunction boundingFunction;
        private final UnivariateFunction unboundingFunction;

        LowerUpperBoundMapper(double lower, double upper) {
            this.boundingFunction = new Sigmoid(lower, upper);
            this.unboundingFunction = new Logit(lower, upper);
        }

        public double unboundedToBounded(double y) {
            return this.boundingFunction.value(y);
        }

        public double boundedToUnbounded(double x) {
            return this.unboundingFunction.value(x);
        }
    }

    private static class UpperBoundMapper
    implements Mapper {
        private final double upper;

        UpperBoundMapper(double upper) {
            this.upper = upper;
        }

        public double unboundedToBounded(double y) {
            return this.upper - FastMath.exp(-y);
        }

        public double boundedToUnbounded(double x) {
            return -FastMath.log(this.upper - x);
        }
    }

    private static class LowerBoundMapper
    implements Mapper {
        private final double lower;

        LowerBoundMapper(double lower) {
            this.lower = lower;
        }

        public double unboundedToBounded(double y) {
            return this.lower + FastMath.exp(y);
        }

        public double boundedToUnbounded(double x) {
            return FastMath.log(x - this.lower);
        }
    }

    private static class NoBoundsMapper
    implements Mapper {
        NoBoundsMapper() {
        }

        public double unboundedToBounded(double y) {
            return y;
        }

        public double boundedToUnbounded(double x) {
            return x;
        }
    }

    private static interface Mapper {
        public double unboundedToBounded(double var1);

        public double boundedToUnbounded(double var1);
    }
}

