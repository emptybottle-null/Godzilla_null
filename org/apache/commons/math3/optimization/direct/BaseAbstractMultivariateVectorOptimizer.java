/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
import org.apache.commons.math3.optimization.Target;
import org.apache.commons.math3.optimization.Weight;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class BaseAbstractMultivariateVectorOptimizer<FUNC extends MultivariateVectorFunction>
implements BaseMultivariateVectorOptimizer<FUNC> {
    protected final Incrementor evaluations = new Incrementor();
    private ConvergenceChecker<PointVectorValuePair> checker;
    private double[] target;
    private RealMatrix weightMatrix;
    @Deprecated
    private double[] weight;
    private double[] start;
    private FUNC function;

    @Deprecated
    protected BaseAbstractMultivariateVectorOptimizer() {
        this(new SimpleVectorValueChecker());
    }

    protected BaseAbstractMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this.checker = checker;
    }

    @Override
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    @Override
    public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
        return this.checker;
    }

    protected double[] computeObjectiveValue(double[] point) {
        try {
            this.evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return this.function.value(point);
    }

    @Override
    @Deprecated
    public PointVectorValuePair optimize(int maxEval, FUNC f, double[] t, double[] w, double[] startPoint) {
        return this.optimizeInternal(maxEval, f, t, w, startPoint);
    }

    protected PointVectorValuePair optimize(int maxEval, FUNC f, OptimizationData ... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        return this.optimizeInternal(maxEval, f, optData);
    }

    @Deprecated
    protected PointVectorValuePair optimizeInternal(int maxEval, FUNC f, double[] t, double[] w, double[] startPoint) {
        if (f == null) {
            throw new NullArgumentException();
        }
        if (t == null) {
            throw new NullArgumentException();
        }
        if (w == null) {
            throw new NullArgumentException();
        }
        if (startPoint == null) {
            throw new NullArgumentException();
        }
        if (t.length != w.length) {
            throw new DimensionMismatchException(t.length, w.length);
        }
        return this.optimizeInternal(maxEval, f, new Target(t), new Weight(w), new InitialGuess(startPoint));
    }

    protected PointVectorValuePair optimizeInternal(int maxEval, FUNC f, OptimizationData ... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        this.evaluations.setMaximalCount(maxEval);
        this.evaluations.resetCount();
        this.function = f;
        this.parseOptimizationData(optData);
        this.checkParameters();
        this.setUp();
        return this.doOptimize();
    }

    public double[] getStartPoint() {
        return (double[])this.start.clone();
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }

    public double[] getTarget() {
        return (double[])this.target.clone();
    }

    protected FUNC getObjectiveFunction() {
        return this.function;
    }

    protected abstract PointVectorValuePair doOptimize();

    @Deprecated
    protected double[] getTargetRef() {
        return this.target;
    }

    @Deprecated
    protected double[] getWeightRef() {
        return this.weight;
    }

    protected void setUp() {
        int dim = this.target.length;
        this.weight = new double[dim];
        for (int i = 0; i < dim; ++i) {
            this.weight[i] = this.weightMatrix.getEntry(i, i);
        }
    }

    private void parseOptimizationData(OptimizationData ... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof Target) {
                this.target = ((Target)data).getTarget();
                continue;
            }
            if (data instanceof Weight) {
                this.weightMatrix = ((Weight)data).getWeight();
                continue;
            }
            if (!(data instanceof InitialGuess)) continue;
            this.start = ((InitialGuess)data).getInitialGuess();
        }
    }

    private void checkParameters() {
        if (this.target.length != this.weightMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(this.target.length, this.weightMatrix.getColumnDimension());
        }
    }
}

