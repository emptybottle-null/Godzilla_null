/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.BaseOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.SimpleBounds;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseMultivariateOptimizer<PAIR>
extends BaseOptimizer<PAIR> {
    private double[] start;
    private double[] lowerBound;
    private double[] upperBound;

    protected BaseMultivariateOptimizer(ConvergenceChecker<PAIR> checker) {
        super(checker);
    }

    @Override
    public PAIR optimize(OptimizationData ... optData) {
        return super.optimize(optData);
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof InitialGuess) {
                this.start = ((InitialGuess)data).getInitialGuess();
                continue;
            }
            if (!(data instanceof SimpleBounds)) continue;
            SimpleBounds bounds = (SimpleBounds)data;
            this.lowerBound = bounds.getLower();
            this.upperBound = bounds.getUpper();
        }
        this.checkParameters();
    }

    public double[] getStartPoint() {
        return this.start == null ? null : (double[])this.start.clone();
    }

    public double[] getLowerBound() {
        return this.lowerBound == null ? null : (double[])this.lowerBound.clone();
    }

    public double[] getUpperBound() {
        return this.upperBound == null ? null : (double[])this.upperBound.clone();
    }

    private void checkParameters() {
        if (this.start != null) {
            double v;
            int i;
            int dim = this.start.length;
            if (this.lowerBound != null) {
                if (this.lowerBound.length != dim) {
                    throw new DimensionMismatchException(this.lowerBound.length, dim);
                }
                for (i = 0; i < dim; ++i) {
                    v = this.start[i];
                    double lo = this.lowerBound[i];
                    if (!(v < lo)) continue;
                    throw new NumberIsTooSmallException(v, (Number)lo, true);
                }
            }
            if (this.upperBound != null) {
                if (this.upperBound.length != dim) {
                    throw new DimensionMismatchException(this.upperBound.length, dim);
                }
                for (i = 0; i < dim; ++i) {
                    v = this.start[i];
                    double hi = this.upperBound[i];
                    if (!(v > hi)) continue;
                    throw new NumberIsTooLargeException(v, (Number)hi, true);
                }
            }
        }
    }
}

