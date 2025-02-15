/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ContinuousOutputFieldModel<T extends RealFieldElement<T>>
implements FieldStepHandler<T> {
    private T initialTime = null;
    private T finalTime = null;
    private boolean forward = true;
    private int index = 0;
    private List<FieldStepInterpolator<T>> steps = new ArrayList<FieldStepInterpolator<T>>();

    public void append(ContinuousOutputFieldModel<T> model) throws MathIllegalArgumentException, MaxCountExceededException {
        if (model.steps.size() == 0) {
            return;
        }
        if (this.steps.size() == 0) {
            this.initialTime = model.initialTime;
            this.forward = model.forward;
        } else {
            FieldODEStateAndDerivative<T> s1 = this.steps.get(0).getPreviousState();
            FieldODEStateAndDerivative<T> s2 = model.steps.get(0).getPreviousState();
            this.checkDimensionsEquality(s1.getStateDimension(), s2.getStateDimension());
            this.checkDimensionsEquality(s1.getNumberOfSecondaryStates(), s2.getNumberOfSecondaryStates());
            for (int i = 0; i < s1.getNumberOfSecondaryStates(); ++i) {
                this.checkDimensionsEquality(s1.getSecondaryStateDimension(i), s2.getSecondaryStateDimension(i));
            }
            if (this.forward ^ model.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            FieldStepInterpolator<T> lastInterpolator = this.steps.get(this.index);
            Object current = lastInterpolator.getCurrentState().getTime();
            Object previous = lastInterpolator.getPreviousState().getTime();
            RealFieldElement step = (RealFieldElement)current.subtract(previous);
            RealFieldElement gap = (RealFieldElement)model.getInitialTime().subtract(current);
            if (((RealFieldElement)((RealFieldElement)gap.abs()).subtract(((RealFieldElement)step.abs()).multiply(0.001))).getReal() > 0.0) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, ((RealFieldElement)gap.abs()).getReal());
            }
        }
        for (FieldStepInterpolator<T> interpolator : model.steps) {
            this.steps.add(interpolator);
        }
        this.index = this.steps.size() - 1;
        this.finalTime = this.steps.get(this.index).getCurrentState().getTime();
    }

    private void checkDimensionsEquality(int d1, int d2) throws DimensionMismatchException {
        if (d1 != d2) {
            throw new DimensionMismatchException(d2, d1);
        }
    }

    @Override
    public void init(FieldODEStateAndDerivative<T> initialState, T t) {
        this.initialTime = initialState.getTime();
        this.finalTime = t;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    @Override
    public void handleStep(FieldStepInterpolator<T> interpolator, boolean isLast) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = interpolator.getPreviousState().getTime();
            this.forward = interpolator.isForward();
        }
        this.steps.add(interpolator);
        if (isLast) {
            this.finalTime = interpolator.getCurrentState().getTime();
            this.index = this.steps.size() - 1;
        }
    }

    public T getInitialTime() {
        return this.initialTime;
    }

    public T getFinalTime() {
        return this.finalTime;
    }

    public FieldODEStateAndDerivative<T> getInterpolatedState(T time) {
        int iMin = 0;
        FieldStepInterpolator<T> sMin = this.steps.get(iMin);
        RealFieldElement tMin = (RealFieldElement)((RealFieldElement)sMin.getPreviousState().getTime().add(sMin.getCurrentState().getTime())).multiply(0.5);
        int iMax = this.steps.size() - 1;
        FieldStepInterpolator<T> sMax = this.steps.get(iMax);
        RealFieldElement tMax = (RealFieldElement)((RealFieldElement)sMax.getPreviousState().getTime().add(sMax.getCurrentState().getTime())).multiply(0.5);
        if (this.locatePoint(time, sMin) <= 0) {
            this.index = iMin;
            return sMin.getInterpolatedState(time);
        }
        if (this.locatePoint(time, sMax) >= 0) {
            this.index = iMax;
            return sMax.getInterpolatedState(time);
        }
        while (iMax - iMin > 5) {
            FieldStepInterpolator<T> si = this.steps.get(this.index);
            int location = this.locatePoint(time, si);
            if (location < 0) {
                iMax = this.index;
                tMax = (RealFieldElement)((RealFieldElement)si.getPreviousState().getTime().add(si.getCurrentState().getTime())).multiply(0.5);
            } else if (location > 0) {
                iMin = this.index;
                tMin = (RealFieldElement)((RealFieldElement)si.getPreviousState().getTime().add(si.getCurrentState().getTime())).multiply(0.5);
            } else {
                return si.getInterpolatedState(time);
            }
            int iMed = (iMin + iMax) / 2;
            FieldStepInterpolator<T> sMed = this.steps.get(iMed);
            RealFieldElement tMed = (RealFieldElement)((RealFieldElement)sMed.getPreviousState().getTime().add(sMed.getCurrentState().getTime())).multiply(0.5);
            if (((RealFieldElement)((RealFieldElement)tMed.subtract(tMin).abs()).subtract(1.0E-6)).getReal() < 0.0 || ((RealFieldElement)((RealFieldElement)tMax.subtract(tMed).abs()).subtract(1.0E-6)).getReal() < 0.0) {
                this.index = iMed;
            } else {
                RealFieldElement d12 = tMax.subtract(tMed);
                RealFieldElement d23 = tMed.subtract(tMin);
                RealFieldElement d13 = tMax.subtract(tMin);
                RealFieldElement dt1 = time.subtract((RealFieldElement)tMax);
                RealFieldElement dt2 = time.subtract((RealFieldElement)tMed);
                RealFieldElement dt3 = time.subtract((RealFieldElement)tMin);
                RealFieldElement iLagrange = ((RealFieldElement)((RealFieldElement)((RealFieldElement)dt2.multiply(dt3).multiply(d23).multiply(iMax)).subtract(dt1.multiply(dt3).multiply(d13).multiply(iMed))).add(dt1.multiply(dt2).multiply(d12).multiply(iMin))).divide(d12.multiply(d23).multiply(d13));
                this.index = (int)FastMath.rint(iLagrange.getReal());
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
        return this.steps.get(this.index).getInterpolatedState(time);
    }

    private int locatePoint(T time, FieldStepInterpolator<T> interval) {
        if (this.forward) {
            if (((RealFieldElement)time.subtract(interval.getPreviousState().getTime())).getReal() < 0.0) {
                return -1;
            }
            if (((RealFieldElement)time.subtract(interval.getCurrentState().getTime())).getReal() > 0.0) {
                return 1;
            }
            return 0;
        }
        if (((RealFieldElement)time.subtract(interval.getPreviousState().getTime())).getReal() > 0.0) {
            return -1;
        }
        if (((RealFieldElement)time.subtract(interval.getCurrentState().getTime())).getReal() < 0.0) {
            return 1;
        }
        return 0;
    }
}

