/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class MultistepFieldIntegrator<T extends RealFieldElement<T>>
extends AdaptiveStepsizeFieldIntegrator<T> {
    protected T[] scaled;
    protected Array2DRowFieldMatrix<T> nordsieck;
    private FirstOrderFieldIntegrator<T> starter;
    private final int nSteps;
    private double exp;
    private double safety;
    private double minReduction;
    private double maxGrowth;

    protected MultistepFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        if (nSteps < 2) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, (Number)nSteps, 2, true);
        }
        this.starter = new DormandPrince853FieldIntegrator<T>(field, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = -1.0 / (double)order;
        this.setSafety(0.9);
        this.setMinReduction(0.2);
        this.setMaxGrowth(FastMath.pow(2.0, -this.exp));
    }

    protected MultistepFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.starter = new DormandPrince853FieldIntegrator<T>(field, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = -1.0 / (double)order;
        this.setSafety(0.9);
        this.setMinReduction(0.2);
        this.setMaxGrowth(FastMath.pow(2.0, -this.exp));
    }

    public FirstOrderFieldIntegrator<T> getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderFieldIntegrator<T> starterIntegrator) {
        this.starter = starterIntegrator;
    }

    protected void start(FieldExpandableODE<T> equations, FieldODEState<T> initialState, T t) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new FieldNordsieckInitializer(equations.getMapper(), (this.nSteps + 3) / 2));
        try {
            this.starter.integrate(equations, initialState, t);
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY, new Object[0]);
        } catch (InitializationCompletedMarkerException icme) {
            this.getEvaluationsCounter().increment(this.starter.getEvaluations());
            this.starter.clearStepHandlers();
            return;
        }
    }

    protected abstract Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T var1, T[] var2, T[][] var3, T[][] var4);

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

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    public int getNSteps() {
        return this.nSteps;
    }

    protected void rescale(T newStepSize) {
        RealFieldElement ratio = (RealFieldElement)newStepSize.divide(this.getStepSize());
        for (int i = 0; i < this.scaled.length; ++i) {
            this.scaled[i] = this.scaled[i].multiply((RealFieldElement)ratio);
        }
        RealFieldElement[][] nData = (RealFieldElement[][])this.nordsieck.getDataRef();
        RealFieldElement power = ratio;
        for (int i = 0; i < nData.length; ++i) {
            power = power.multiply(ratio);
            RealFieldElement[] nDataI = nData[i];
            for (int j = 0; j < nDataI.length; ++j) {
                nDataI[j] = nDataI[j].multiply(power);
            }
        }
        this.setStepSize(newStepSize);
    }

    protected T computeStepGrowShrinkFactor(T error) {
        return (T)MathUtils.min((RealFieldElement)((RealFieldElement)error.getField().getZero()).add(this.maxGrowth), MathUtils.max((RealFieldElement)((RealFieldElement)error.getField().getZero()).add(this.minReduction), (RealFieldElement)((RealFieldElement)error.pow((double)this.exp)).multiply(this.safety)));
    }

    private static class InitializationCompletedMarkerException
    extends RuntimeException {
        private static final long serialVersionUID = -1914085471038046418L;

        InitializationCompletedMarkerException() {
            super((Throwable)null);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class FieldNordsieckInitializer
    implements FieldStepHandler<T> {
        private final FieldEquationsMapper<T> mapper;
        private int count;
        private FieldODEStateAndDerivative<T> savedStart;
        private final T[] t;
        private final T[][] y;
        private final T[][] yDot;

        FieldNordsieckInitializer(FieldEquationsMapper<T> mapper, int nbStartPoints) {
            this.mapper = mapper;
            this.count = 0;
            this.t = (RealFieldElement[])MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), nbStartPoints);
            this.y = (RealFieldElement[][])MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), nbStartPoints, -1);
            this.yDot = (RealFieldElement[][])MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), nbStartPoints, -1);
        }

        @Override
        public void handleStep(FieldStepInterpolator<T> interpolator, boolean isLast) throws MaxCountExceededException {
            if (this.count == 0) {
                FieldODEStateAndDerivative prev = interpolator.getPreviousState();
                this.savedStart = prev;
                this.t[this.count] = prev.getTime();
                this.y[this.count] = this.mapper.mapState(prev);
                this.yDot[this.count] = this.mapper.mapDerivative(prev);
            }
            ++this.count;
            FieldODEStateAndDerivative curr = interpolator.getCurrentState();
            this.t[this.count] = curr.getTime();
            this.y[this.count] = this.mapper.mapState(curr);
            this.yDot[this.count] = this.mapper.mapDerivative(curr);
            if (this.count == this.t.length - 1) {
                MultistepFieldIntegrator.this.setStepSize((RealFieldElement)((RealFieldElement)this.t[this.t.length - 1].subtract(this.t[0])).divide(this.t.length - 1));
                MultistepFieldIntegrator.this.scaled = (RealFieldElement[])MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), this.yDot[0].length);
                for (int j = 0; j < MultistepFieldIntegrator.this.scaled.length; ++j) {
                    MultistepFieldIntegrator.this.scaled[j] = (RealFieldElement)this.yDot[0][j].multiply(MultistepFieldIntegrator.this.getStepSize());
                }
                MultistepFieldIntegrator.this.nordsieck = MultistepFieldIntegrator.this.initializeHighOrderDerivatives((RealFieldElement)MultistepFieldIntegrator.this.getStepSize(), (RealFieldElement[])this.t, (RealFieldElement[][])this.y, (RealFieldElement[][])this.yDot);
                MultistepFieldIntegrator.this.setStepStart(this.savedStart);
                throw new InitializationCompletedMarkerException();
            }
        }

        @Override
        public void init(FieldODEStateAndDerivative<T> initialState, T finalTime) {
        }
    }
}

