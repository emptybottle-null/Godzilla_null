/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AdaptiveStepsizeFieldIntegrator<T extends RealFieldElement<T>>
extends AbstractFieldIntegrator<T> {
    protected double scalAbsoluteTolerance;
    protected double scalRelativeTolerance;
    protected double[] vecAbsoluteTolerance;
    protected double[] vecRelativeTolerance;
    protected int mainSetDimension;
    private T initialStep;
    private T minStep;
    private T maxStep;

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String name, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(field, name);
        this.setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.resetInternalState();
    }

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String name, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, name);
        this.setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.resetInternalState();
    }

    public void setStepSizeControl(double minimalStep, double maximalStep, double absoluteTolerance, double relativeTolerance) {
        this.minStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(minimalStep));
        this.maxStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(maximalStep));
        this.initialStep = (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate();
        this.scalAbsoluteTolerance = absoluteTolerance;
        this.scalRelativeTolerance = relativeTolerance;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
    }

    public void setStepSizeControl(double minimalStep, double maximalStep, double[] absoluteTolerance, double[] relativeTolerance) {
        this.minStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(minimalStep));
        this.maxStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(maximalStep));
        this.initialStep = (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate();
        this.scalAbsoluteTolerance = 0.0;
        this.scalRelativeTolerance = 0.0;
        this.vecAbsoluteTolerance = (double[])absoluteTolerance.clone();
        this.vecRelativeTolerance = (double[])relativeTolerance.clone();
    }

    public void setInitialStepSize(T initialStepSize) {
        this.initialStep = ((RealFieldElement)initialStepSize.subtract(this.minStep)).getReal() < 0.0 || ((RealFieldElement)initialStepSize.subtract(this.maxStep)).getReal() > 0.0 ? (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate() : initialStepSize;
    }

    @Override
    protected void sanityChecks(FieldODEState<T> eqn, T t) throws DimensionMismatchException, NumberIsTooSmallException {
        super.sanityChecks(eqn, t);
        this.mainSetDimension = eqn.getStateDimension();
        if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
        }
        if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
        }
    }

    public T initializeStep(boolean forward, int order, T[] scale, FieldODEStateAndDerivative<T> state0, FieldEquationsMapper<T> mapper) throws MaxCountExceededException, DimensionMismatchException {
        RealFieldElement h;
        if (this.initialStep.getReal() > 0.0) {
            return (T)(forward ? this.initialStep : (RealFieldElement)this.initialStep.negate());
        }
        RealFieldElement[] y0 = mapper.mapState(state0);
        RealFieldElement[] yDot0 = mapper.mapDerivative(state0);
        RealFieldElement yOnScale2 = (RealFieldElement)this.getField().getZero();
        RealFieldElement yDotOnScale2 = (RealFieldElement)this.getField().getZero();
        for (int j = 0; j < scale.length; ++j) {
            RealFieldElement ratio = (RealFieldElement)y0[j].divide(scale[j]);
            yOnScale2 = yOnScale2.add(ratio.multiply(ratio));
            RealFieldElement ratioDot = (RealFieldElement)yDot0[j].divide(scale[j]);
            yDotOnScale2 = yDotOnScale2.add(ratioDot.multiply(ratioDot));
        }
        RealFieldElement realFieldElement = h = yOnScale2.getReal() < 1.0E-10 || yDotOnScale2.getReal() < 1.0E-10 ? (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(1.0E-6) : (RealFieldElement)((RealFieldElement)yOnScale2.divide(yDotOnScale2).sqrt()).multiply(0.01);
        if (!forward) {
            h = (RealFieldElement)h.negate();
        }
        RealFieldElement[] y1 = (RealFieldElement[])MathArrays.buildArray(this.getField(), y0.length);
        for (int j = 0; j < y0.length; ++j) {
            y1[j] = y0[j].add(yDot0[j].multiply(h));
        }
        RealFieldElement[] yDot1 = this.computeDerivatives(state0.getTime().add((RealFieldElement)h), y1);
        RealFieldElement yDDotOnScale = (RealFieldElement)this.getField().getZero();
        for (int j = 0; j < scale.length; ++j) {
            RealFieldElement ratioDotDot = (RealFieldElement)yDot1[j].subtract(yDot0[j]).divide(scale[j]);
            yDDotOnScale = yDDotOnScale.add(ratioDotDot.multiply(ratioDotDot));
        }
        yDDotOnScale = ((RealFieldElement)yDDotOnScale.sqrt()).divide(h);
        RealFieldElement maxInv2 = MathUtils.max((RealFieldElement)yDotOnScale2.sqrt(), yDDotOnScale);
        RealFieldElement h1 = maxInv2.getReal() < 1.0E-15 ? MathUtils.max((RealFieldElement)((RealFieldElement)this.getField().getZero()).add(1.0E-6), (RealFieldElement)((RealFieldElement)h.abs()).multiply(0.001)) : (RealFieldElement)((RealFieldElement)((RealFieldElement)maxInv2.multiply(100)).reciprocal()).pow(1.0 / (double)order);
        h = MathUtils.min((RealFieldElement)((RealFieldElement)h.abs()).multiply(100), h1);
        h = MathUtils.max(h, (RealFieldElement)((RealFieldElement)state0.getTime().abs()).multiply(1.0E-12));
        h = MathUtils.max(this.minStep, MathUtils.min(this.maxStep, h));
        if (!forward) {
            h = (RealFieldElement)h.negate();
        }
        return (T)h;
    }

    protected T filterStep(T h, boolean forward, boolean acceptSmall) throws NumberIsTooSmallException {
        Object filteredH = h;
        if (((RealFieldElement)((RealFieldElement)h.abs()).subtract(this.minStep)).getReal() < 0.0) {
            if (acceptSmall) {
                filteredH = forward ? this.minStep : (RealFieldElement)this.minStep.negate();
            } else {
                throw new NumberIsTooSmallException((Localizable)LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, (Number)((RealFieldElement)h.abs()).getReal(), this.minStep.getReal(), true);
            }
        }
        if (((RealFieldElement)filteredH.subtract(this.maxStep)).getReal() > 0.0) {
            filteredH = this.maxStep;
        } else if (((RealFieldElement)filteredH.add(this.maxStep)).getReal() < 0.0) {
            filteredH = (RealFieldElement)this.maxStep.negate();
        }
        return filteredH;
    }

    protected void resetInternalState() {
        this.setStepStart(null);
        this.setStepSize((RealFieldElement)((RealFieldElement)this.minStep.multiply(this.maxStep)).sqrt());
    }

    public T getMinStep() {
        return this.minStep;
    }

    public T getMaxStep() {
        return this.maxStep;
    }
}

