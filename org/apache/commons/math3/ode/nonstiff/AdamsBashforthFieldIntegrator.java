/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.AdamsFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AdamsBashforthFieldIntegrator<T extends RealFieldElement<T>>
extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsBashforthFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, METHOD_NAME, nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    private T errorEstimation(T[] previousState, T[] predictedState, T[] predictedScaled, FieldMatrix<T> predictedNordsieck) {
        RealFieldElement error = (RealFieldElement)this.getField().getZero();
        for (int i = 0; i < this.mainSetDimension; ++i) {
            RealFieldElement yScale = (RealFieldElement)predictedState[i].abs();
            RealFieldElement tol = this.vecAbsoluteTolerance == null ? (RealFieldElement)((RealFieldElement)yScale.multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance) : (RealFieldElement)((RealFieldElement)yScale.multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i]);
            RealFieldElement variation = (RealFieldElement)this.getField().getZero();
            int sign = predictedNordsieck.getRowDimension() % 2 == 0 ? -1 : 1;
            for (int k = predictedNordsieck.getRowDimension() - 1; k >= 0; --k) {
                variation = (RealFieldElement)variation.add(((RealFieldElement)predictedNordsieck.getEntry(k, i)).multiply(sign));
                sign = -sign;
            }
            variation = (RealFieldElement)variation.subtract(predictedScaled[i]);
            RealFieldElement ratio = ((RealFieldElement)predictedState[i].subtract(previousState[i])).add(variation).divide(tol);
            error = error.add(ratio.multiply(ratio));
        }
        return (T)((RealFieldElement)((RealFieldElement)error.divide(this.mainSetDimension)).sqrt());
    }

    @Override
    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> equations, FieldODEState<T> initialState, T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        this.sanityChecks(initialState, finalTime);
        T t0 = initialState.getTime();
        RealFieldElement[] y = equations.getMapper().mapState(initialState);
        this.setStepStart(this.initIntegration(equations, (RealFieldElement)t0, y, (RealFieldElement)finalTime));
        boolean forward = ((RealFieldElement)finalTime.subtract(initialState.getTime())).getReal() > 0.0;
        this.start(equations, this.getStepStart(), finalTime);
        FieldODEStateAndDerivative stepStart = this.getStepStart();
        FieldODEStateAndDerivative stepEnd = AdamsFieldStepInterpolator.taylor(stepStart, (RealFieldElement)((RealFieldElement)stepStart.getTime().add(this.getStepSize())), this.getStepSize(), (RealFieldElement[])this.scaled, (Array2DRowFieldMatrix)this.nordsieck);
        this.setIsLastStep(false);
        do {
            boolean filteredNextIsLast;
            RealFieldElement[] predictedY = null;
            RealFieldElement[] predictedScaled = (RealFieldElement[])MathArrays.buildArray(this.getField(), y.length);
            Array2DRowFieldMatrix predictedNordsieck = null;
            RealFieldElement error = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(10.0);
            while (((RealFieldElement)error.subtract(1.0)).getReal() >= 0.0) {
                predictedY = stepEnd.getState();
                RealFieldElement[] yDot = this.computeDerivatives((RealFieldElement)stepEnd.getTime(), predictedY);
                for (int j = 0; j < predictedScaled.length; ++j) {
                    predictedScaled[j] = this.getStepSize().multiply((RealFieldElement)yDot[j]);
                }
                predictedNordsieck = this.updateHighOrderDerivativesPhase1(this.nordsieck);
                this.updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, predictedNordsieck);
                error = this.errorEstimation(y, predictedY, predictedScaled, predictedNordsieck);
                if (!(((RealFieldElement)error.subtract(1.0)).getReal() >= 0.0)) continue;
                RealFieldElement factor = this.computeStepGrowShrinkFactor(error);
                this.rescale(this.filterStep(this.getStepSize().multiply((RealFieldElement)factor), forward, false));
                stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())), this.getStepSize(), (RealFieldElement[])this.scaled, (Array2DRowFieldMatrix)this.nordsieck);
            }
            this.setStepStart(this.acceptStep(new AdamsFieldStepInterpolator(this.getStepSize(), stepEnd, predictedScaled, predictedNordsieck, forward, this.getStepStart(), stepEnd, equations.getMapper()), finalTime));
            this.scaled = predictedScaled;
            this.nordsieck = predictedNordsieck;
            if (this.isLastStep()) continue;
            System.arraycopy(predictedY, 0, y, 0, y.length);
            if (this.resetOccurred()) {
                this.start(equations, this.getStepStart(), finalTime);
            }
            RealFieldElement factor = this.computeStepGrowShrinkFactor(error);
            RealFieldElement scaledH = this.getStepSize().multiply((RealFieldElement)factor);
            RealFieldElement nextT = this.getStepStart().getTime().add((RealFieldElement)scaledH);
            boolean nextIsLast = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= 0.0 : ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= 0.0;
            RealFieldElement hNew = this.filterStep(scaledH, forward, nextIsLast);
            RealFieldElement filteredNextT = this.getStepStart().getTime().add((RealFieldElement)hNew);
            boolean bl = forward ? ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() >= 0.0 : (filteredNextIsLast = ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() <= 0.0);
            if (filteredNextIsLast) {
                hNew = (RealFieldElement)finalTime.subtract(this.getStepStart().getTime());
            }
            this.rescale(hNew);
            stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())), this.getStepSize(), (RealFieldElement[])this.scaled, (Array2DRowFieldMatrix)this.nordsieck);
        } while (!this.isLastStep());
        FieldODEStateAndDerivative finalState = this.getStepStart();
        this.setStepStart(null);
        this.setStepSize(null);
        return finalState;
    }
}

