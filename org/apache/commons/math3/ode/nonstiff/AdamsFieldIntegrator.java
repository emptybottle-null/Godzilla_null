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
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.MultistepFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AdamsFieldIntegrator<T extends RealFieldElement<T>>
extends MultistepFieldIntegrator<T> {
    private final AdamsNordsieckFieldTransformer<T> transformer;

    public AdamsFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, name, nSteps, order, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.transformer = AdamsNordsieckFieldTransformer.getInstance(field, nSteps);
    }

    public AdamsFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, name, nSteps, order, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.transformer = AdamsNordsieckFieldTransformer.getInstance(field, nSteps);
    }

    @Override
    public abstract FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> var1, FieldODEState<T> var2, T var3) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    @Override
    protected Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T h, T[] t, T[][] y, T[][] yDot) {
        return this.transformer.initializeHighOrderDerivatives((RealFieldElement)h, (RealFieldElement[])t, (RealFieldElement[][])y, (RealFieldElement[][])yDot);
    }

    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix<T> highOrder) {
        return this.transformer.updateHighOrderDerivativesPhase1(highOrder);
    }

    public void updateHighOrderDerivativesPhase2(T[] start, T[] end, Array2DRowFieldMatrix<T> highOrder) {
        this.transformer.updateHighOrderDerivativesPhase2((RealFieldElement[])start, (RealFieldElement[])end, highOrder);
    }
}

