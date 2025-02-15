/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class ContinuousOutputModel
implements StepHandler,
Serializable {
    private static final long serialVersionUID = -1417964919405031606L;
    private double initialTime;
    private double finalTime;
    private boolean forward = true;
    private int index = 0;
    private List<StepInterpolator> steps = new ArrayList<StepInterpolator>();

    public ContinuousOutputModel() {
        this.initialTime = Double.NaN;
        this.finalTime = Double.NaN;
    }

    public void append(ContinuousOutputModel model) throws MathIllegalArgumentException, MaxCountExceededException {
        if (model.steps.size() == 0) {
            return;
        }
        if (this.steps.size() == 0) {
            this.initialTime = model.initialTime;
            this.forward = model.forward;
        } else {
            if (this.getInterpolatedState().length != model.getInterpolatedState().length) {
                throw new DimensionMismatchException(model.getInterpolatedState().length, this.getInterpolatedState().length);
            }
            if (this.forward ^ model.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            StepInterpolator lastInterpolator = this.steps.get(this.index);
            double current = lastInterpolator.getCurrentTime();
            double previous = lastInterpolator.getPreviousTime();
            double step = current - previous;
            double gap = model.getInitialTime() - current;
            if (FastMath.abs(gap) > 0.001 * FastMath.abs(step)) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, FastMath.abs(gap));
            }
        }
        for (StepInterpolator interpolator : model.steps) {
            this.steps.add(interpolator.copy());
        }
        this.index = this.steps.size() - 1;
        this.finalTime = this.steps.get(this.index).getCurrentTime();
    }

    public void init(double t0, double[] y0, double t) {
        this.initialTime = Double.NaN;
        this.finalTime = Double.NaN;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = interpolator.getPreviousTime();
            this.forward = interpolator.isForward();
        }
        this.steps.add(interpolator.copy());
        if (isLast) {
            this.finalTime = interpolator.getCurrentTime();
            this.index = this.steps.size() - 1;
        }
    }

    public double getInitialTime() {
        return this.initialTime;
    }

    public double getFinalTime() {
        return this.finalTime;
    }

    public double getInterpolatedTime() {
        return this.steps.get(this.index).getInterpolatedTime();
    }

    public void setInterpolatedTime(double time) {
        int iMin = 0;
        StepInterpolator sMin = this.steps.get(iMin);
        double tMin = 0.5 * (sMin.getPreviousTime() + sMin.getCurrentTime());
        int iMax = this.steps.size() - 1;
        StepInterpolator sMax = this.steps.get(iMax);
        double tMax = 0.5 * (sMax.getPreviousTime() + sMax.getCurrentTime());
        if (this.locatePoint(time, sMin) <= 0) {
            this.index = iMin;
            sMin.setInterpolatedTime(time);
            return;
        }
        if (this.locatePoint(time, sMax) >= 0) {
            this.index = iMax;
            sMax.setInterpolatedTime(time);
            return;
        }
        while (iMax - iMin > 5) {
            StepInterpolator si = this.steps.get(this.index);
            int location = this.locatePoint(time, si);
            if (location < 0) {
                iMax = this.index;
                tMax = 0.5 * (si.getPreviousTime() + si.getCurrentTime());
            } else if (location > 0) {
                iMin = this.index;
                tMin = 0.5 * (si.getPreviousTime() + si.getCurrentTime());
            } else {
                si.setInterpolatedTime(time);
                return;
            }
            int iMed = (iMin + iMax) / 2;
            StepInterpolator sMed = this.steps.get(iMed);
            double tMed = 0.5 * (sMed.getPreviousTime() + sMed.getCurrentTime());
            if (FastMath.abs(tMed - tMin) < 1.0E-6 || FastMath.abs(tMax - tMed) < 1.0E-6) {
                this.index = iMed;
            } else {
                double d12 = tMax - tMed;
                double d23 = tMed - tMin;
                double d13 = tMax - tMin;
                double dt1 = time - tMax;
                double dt2 = time - tMed;
                double dt3 = time - tMin;
                double iLagrange = (dt2 * dt3 * d23 * (double)iMax - dt1 * dt3 * d13 * (double)iMed + dt1 * dt2 * d12 * (double)iMin) / (d12 * d23 * d13);
                this.index = (int)FastMath.rint(iLagrange);
            }
            int low = FastMath.max(iMin + 1, (9 * iMin + iMax) / 10);
            int high = FastMath.min(iMax - 1, (iMin + 9 * iMax) / 10);
            if (this.index < low) {
                this.index = low;
                continue;
            }
            if (this.index <= high) continue;
            this.index = high;
        }
        this.index = iMin;
        while (this.index <= iMax && this.locatePoint(time, this.steps.get(this.index)) > 0) {
            ++this.index;
        }
        this.steps.get(this.index).setInterpolatedTime(time);
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedState();
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedDerivatives();
    }

    public double[] getInterpolatedSecondaryState(int secondaryStateIndex) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryState(secondaryStateIndex);
    }

    public double[] getInterpolatedSecondaryDerivatives(int secondaryStateIndex) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryDerivatives(secondaryStateIndex);
    }

    private int locatePoint(double time, StepInterpolator interval) {
        if (this.forward) {
            if (time < interval.getPreviousTime()) {
                return -1;
            }
            if (time > interval.getCurrentTime()) {
                return 1;
            }
            return 0;
        }
        if (time > interval.getPreviousTime()) {
            return -1;
        }
        if (time < interval.getCurrentTime()) {
            return 1;
        }
        return 0;
    }
}

