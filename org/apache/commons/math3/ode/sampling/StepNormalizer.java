/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.FixedStepHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.ode.sampling.StepNormalizerBounds;
import org.apache.commons.math3.ode.sampling.StepNormalizerMode;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class StepNormalizer
implements StepHandler {
    private double h;
    private final FixedStepHandler handler;
    private double firstTime;
    private double lastTime;
    private double[] lastState;
    private double[] lastDerivatives;
    private boolean forward;
    private final StepNormalizerBounds bounds;
    private final StepNormalizerMode mode;

    public StepNormalizer(double h, FixedStepHandler handler) {
        this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode) {
        this(h, handler, mode, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerBounds bounds) {
        this(h, handler, StepNormalizerMode.INCREMENT, bounds);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
    }

    public void init(double t0, double[] y0, double t) {
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
        this.handler.init(t0, y0, t);
    }

    public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
        double nextTime;
        if (this.lastState == null) {
            this.firstTime = interpolator.getPreviousTime();
            this.lastTime = interpolator.getPreviousTime();
            interpolator.setInterpolatedTime(this.lastTime);
            this.lastState = (double[])interpolator.getInterpolatedState().clone();
            this.lastDerivatives = (double[])interpolator.getInterpolatedDerivatives().clone();
            boolean bl = this.forward = interpolator.getCurrentTime() >= this.lastTime;
            if (!this.forward) {
                this.h = -this.h;
            }
        }
        double d = nextTime = this.mode == StepNormalizerMode.INCREMENT ? this.lastTime + this.h : (FastMath.floor(this.lastTime / this.h) + 1.0) * this.h;
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime, this.lastTime, 1)) {
            nextTime += this.h;
        }
        boolean nextInStep = this.isNextInStep(nextTime, interpolator);
        while (nextInStep) {
            this.doNormalizedStep(false);
            this.storeStep(interpolator, nextTime);
            nextInStep = this.isNextInStep(nextTime += this.h, interpolator);
        }
        if (isLast) {
            boolean addLast = this.bounds.lastIncluded() && this.lastTime != interpolator.getCurrentTime();
            this.doNormalizedStep(!addLast);
            if (addLast) {
                this.storeStep(interpolator, interpolator.getCurrentTime());
                this.doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(double nextTime, StepInterpolator interpolator) {
        return this.forward ? nextTime <= interpolator.getCurrentTime() : nextTime >= interpolator.getCurrentTime();
    }

    private void doNormalizedStep(boolean isLast) {
        if (!this.bounds.firstIncluded() && this.firstTime == this.lastTime) {
            return;
        }
        this.handler.handleStep(this.lastTime, this.lastState, this.lastDerivatives, isLast);
    }

    private void storeStep(StepInterpolator interpolator, double t) throws MaxCountExceededException {
        this.lastTime = t;
        interpolator.setInterpolatedTime(this.lastTime);
        System.arraycopy(interpolator.getInterpolatedState(), 0, this.lastState, 0, this.lastState.length);
        System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, this.lastDerivatives, 0, this.lastDerivatives.length);
    }
}

