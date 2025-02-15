/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.AdamsFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AdamsMoultonFieldIntegrator<T extends RealFieldElement<T>>
extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsMoultonFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
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
                error = (RealFieldElement)predictedNordsieck.walkInOptimizedOrder(new Corrector(this, y, predictedScaled, predictedY));
                if (!(((RealFieldElement)error.subtract(1.0)).getReal() >= 0.0)) continue;
                RealFieldElement factor = this.computeStepGrowShrinkFactor(error);
                this.rescale(this.filterStep(this.getStepSize().multiply((RealFieldElement)factor), forward, false));
                stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())), this.getStepSize(), (RealFieldElement[])this.scaled, (Array2DRowFieldMatrix)this.nordsieck);
            }
            RealFieldElement[] correctedYDot = this.computeDerivatives((RealFieldElement)stepEnd.getTime(), predictedY);
            RealFieldElement[] correctedScaled = (RealFieldElement[])MathArrays.buildArray(this.getField(), y.length);
            for (int j = 0; j < correctedScaled.length; ++j) {
                correctedScaled[j] = this.getStepSize().multiply((RealFieldElement)correctedYDot[j]);
            }
            this.updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, predictedNordsieck);
            stepEnd = new FieldODEStateAndDerivative(stepEnd.getTime(), predictedY, correctedYDot);
            this.setStepStart(this.acceptStep(new AdamsFieldStepInterpolator(this.getStepSize(), stepEnd, correctedScaled, predictedNordsieck, forward, this.getStepStart(), stepEnd, equations.getMapper()), finalTime));
            this.scaled = correctedScaled;
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

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Corrector
    implements FieldMatrixPreservingVisitor<T> {
        private final T[] previous;
        private final T[] scaled;
        private final T[] before;
        private final T[] after;
        final /* synthetic */ AdamsMoultonFieldIntegrator this$0;

        Corrector(T[] previous, T[] scaled, T[] state) {
            this.this$0 = var1_1;
            this.previous = previous;
            this.scaled = scaled;
            this.after = state;
            this.before = (RealFieldElement[])state.clone();
        }

        @Override
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            Arrays.fill(this.after, this.this$0.getField().getZero());
        }

        @Override
        public void visit(int row, int column, T value) {
            this.after[column] = (row & 1) == 0 ? (RealFieldElement)this.after[column].subtract(value) : (RealFieldElement)this.after[column].add(value);
        }

        @Override
        public T end() {
            RealFieldElement error = (RealFieldElement)this.this$0.getField().getZero();
            for (int i = 0; i < this.after.length; ++i) {
                this.after[i] = (RealFieldElement)this.after[i].add(this.previous[i].add(this.scaled[i]));
                if (i >= this.this$0.mainSetDimension) continue;
                RealFieldElement yScale = MathUtils.max((RealFieldElement)this.previous[i].abs(), (RealFieldElement)this.after[i].abs());
                RealFieldElement tol = this.this$0.vecAbsoluteTolerance == null ? (RealFieldElement)((RealFieldElement)yScale.multiply(this.this$0.scalRelativeTolerance)).add(this.this$0.scalAbsoluteTolerance) : (RealFieldElement)((RealFieldElement)yScale.multiply(this.this$0.vecRelativeTolerance[i])).add(this.this$0.vecAbsoluteTolerance[i]);
                RealFieldElement ratio = ((RealFieldElement)this.after[i].subtract(this.before[i])).divide(tol);
                error = error.add(ratio.multiply(ratio));
            }
            return (RealFieldElement)((RealFieldElement)error.divide(this.this$0.mainSetDimension)).sqrt();
        }
    }
}

