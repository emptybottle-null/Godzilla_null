/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldFixedStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepNormalizerBounds;
import org.apache.commons.math3.ode.sampling.StepNormalizerMode;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldStepNormalizer<T extends RealFieldElement<T>>
implements FieldStepHandler<T> {
    private double h;
    private final FieldFixedStepHandler<T> handler;
    private FieldODEStateAndDerivative<T> first;
    private FieldODEStateAndDerivative<T> last;
    private boolean forward;
    private final StepNormalizerBounds bounds;
    private final StepNormalizerMode mode;

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler) {
        this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerMode mode) {
        this(h, handler, mode, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerBounds bounds) {
        this(h, handler, StepNormalizerMode.INCREMENT, bounds);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        this.first = null;
        this.last = null;
        this.forward = true;
    }

    @Override
    public void init(FieldODEStateAndDerivative<T> initialState, T finalTime) {
        this.first = null;
        this.last = null;
        this.forward = true;
        this.handler.init(initialState, finalTime);
    }

    @Override
    public void handleStep(FieldStepInterpolator<T> interpolator, boolean isLast) throws MaxCountExceededException {
        RealFieldElement nextTime;
        if (this.last == null) {
            this.first = interpolator.getPreviousState();
            this.last = this.first;
            this.forward = interpolator.isForward();
            if (!this.forward) {
                this.h = -this.h;
            }
        }
        RealFieldElement realFieldElement = nextTime = this.mode == StepNormalizerMode.INCREMENT ? (RealFieldElement)this.last.getTime().add(this.h) : (RealFieldElement)((RealFieldElement)this.last.getTime().getField().getZero()).add((FastMath.floor(this.last.getTime().getReal() / this.h) + 1.0) * this.h);
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime.getReal(), this.last.getTime().getReal(), 1)) {
            nextTime = (RealFieldElement)nextTime.add(this.h);
        }
        boolean nextInStep = this.isNextInStep(nextTime, interpolator);
        while (nextInStep) {
            this.doNormalizedStep(false);
            this.last = interpolator.getInterpolatedState(nextTime);
            nextTime = (RealFieldElement)nextTime.add(this.h);
            nextInStep = this.isNextInStep(nextTime, interpolator);
        }
        if (isLast) {
            boolean addLast = this.bounds.lastIncluded() && this.last.getTime().getReal() != interpolator.getCurrentState().getTime().getReal();
            this.doNormalizedStep(!addLast);
            if (addLast) {
                this.last = interpolator.getCurrentState();
                this.doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(T nextTime, FieldStepInterpolator<T> interpolator) {
        return this.forward ? nextTime.getReal() <= interpolator.getCurrentState().getTime().getReal() : nextTime.getReal() >= interpolator.getCurrentState().getTime().getReal();
    }

    private void doNormalizedStep(boolean isLast) {
        if (!this.bounds.firstIncluded() && this.first.getTime().getReal() == this.last.getTime().getReal()) {
            return;
        }
        this.handler.handleStep(this.last, isLast);
    }
}

