/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public abstract class EmbeddedRungeKuttaIntegrator
extends AdaptiveStepsizeIntegrator {
    private final boolean fsal;
    private final double[] c;
    private final double[][] a;
    private final double[] b;
    private final RungeKuttaStepInterpolator prototype;
    private final double exp;
    private double safety;
    private double minReduction;
    private double maxGrowth;

    protected EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.exp = -1.0 / (double)this.getOrder();
        this.setSafety(0.9);
        this.setMinReduction(0.2);
        this.setMaxGrowth(10.0);
    }

    protected EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.exp = -1.0 / (double)this.getOrder();
        this.setSafety(0.9);
        this.setMinReduction(0.2);
        this.setMaxGrowth(10.0);
    }

    public abstract int getOrder();

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        this.sanityChecks(equations, t);
        this.setEquations(equations);
        boolean forward = t > equations.getTime();
        double[] y0 = equations.getCompleteState();
        double[] y = (double[])y0.clone();
        int stages = this.c.length + 1;
        double[][] yDotK = new double[stages][y.length];
        double[] yTmp = (double[])y0.clone();
        double[] yDotTmp = new double[y.length];
        RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator)this.prototype.copy();
        interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        interpolator.storeTime(equations.getTime());
        this.stepStart = equations.getTime();
        double hNew = 0.0;
        boolean firstTime = true;
        this.initIntegration(equations.getTime(), y0, t);
        this.isLastStep = false;
        do {
            boolean filteredNextIsLast;
            interpolator.shift();
            double error = 10.0;
            while (error >= 1.0) {
                if (firstTime || !this.fsal) {
                    this.computeDerivatives(this.stepStart, y, yDotK[0]);
                }
                if (firstTime) {
                    int i;
                    double[] scale = new double[this.mainSetDimension];
                    if (this.vecAbsoluteTolerance == null) {
                        for (i = 0; i < scale.length; ++i) {
                            scale[i] = this.scalAbsoluteTolerance + this.scalRelativeTolerance * FastMath.abs(y[i]);
                        }
                    } else {
                        for (i = 0; i < scale.length; ++i) {
                            scale[i] = this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * FastMath.abs(y[i]);
                        }
                    }
                    hNew = this.initializeStep(forward, this.getOrder(), scale, this.stepStart, y, yDotK[0], yTmp, yDotK[1]);
                    firstTime = false;
                }
                this.stepSize = hNew;
                if (forward) {
                    if (this.stepStart + this.stepSize >= t) {
                        this.stepSize = t - this.stepStart;
                    }
                } else if (this.stepStart + this.stepSize <= t) {
                    this.stepSize = t - this.stepStart;
                }
                for (int k = 1; k < stages; ++k) {
                    for (int j = 0; j < y0.length; ++j) {
                        double sum = this.a[k - 1][0] * yDotK[0][j];
                        for (int l = 1; l < k; ++l) {
                            sum += this.a[k - 1][l] * yDotK[l][j];
                        }
                        yTmp[j] = y[j] + this.stepSize * sum;
                    }
                    this.computeDerivatives(this.stepStart + this.c[k - 1] * this.stepSize, yTmp, yDotK[k]);
                }
                for (int j = 0; j < y0.length; ++j) {
                    double sum = this.b[0] * yDotK[0][j];
                    for (int l = 1; l < stages; ++l) {
                        sum += this.b[l] * yDotK[l][j];
                    }
                    yTmp[j] = y[j] + this.stepSize * sum;
                }
                error = this.estimateError(yDotK, y, yTmp, this.stepSize);
                if (!(error >= 1.0)) continue;
                double factor = FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
                hNew = this.filterStep(this.stepSize * factor, forward, false);
            }
            interpolator.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            System.arraycopy(yDotK[stages - 1], 0, yDotTmp, 0, y0.length);
            this.stepStart = this.acceptStep(interpolator, y, yDotTmp, t);
            System.arraycopy(y, 0, yTmp, 0, y.length);
            if (this.isLastStep) continue;
            interpolator.storeTime(this.stepStart);
            if (this.fsal) {
                System.arraycopy(yDotTmp, 0, yDotK[0], 0, y0.length);
            }
            double factor = FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
            double scaledH = this.stepSize * factor;
            double nextT = this.stepStart + scaledH;
            boolean nextIsLast = forward ? nextT >= t : nextT <= t;
            hNew = this.filterStep(scaledH, forward, nextIsLast);
            double filteredNextT = this.stepStart + hNew;
            boolean bl = forward ? filteredNextT >= t : (filteredNextIsLast = filteredNextT <= t);
            if (!filteredNextIsLast) continue;
            hNew = t - this.stepStart;
        } while (!this.isLastStep);
        equations.setTime(this.stepStart);
        equations.setCompleteState(y);
        this.resetInternalState();
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double minReduction) {
        this.minReduction = minReduction;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    protected abstract double estimateError(double[][] var1, double[] var2, double[] var3, double var4);
}

