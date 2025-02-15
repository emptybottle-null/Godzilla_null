/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.MultistepIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer;

public abstract class AdamsIntegrator
extends MultistepIntegrator {
    private final AdamsNordsieckTransformer transformer;

    public AdamsIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(name, nSteps, order, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.transformer = AdamsNordsieckTransformer.getInstance(nSteps);
    }

    public AdamsIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(name, nSteps, order, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.transformer = AdamsNordsieckTransformer.getInstance(nSteps);
    }

    public abstract void integrate(ExpandableStatefulODE var1, double var2) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    protected Array2DRowRealMatrix initializeHighOrderDerivatives(double h, double[] t, double[][] y, double[][] yDot) {
        return this.transformer.initializeHighOrderDerivatives(h, t, y, yDot);
    }

    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix highOrder) {
        return this.transformer.updateHighOrderDerivativesPhase1(highOrder);
    }

    public void updateHighOrderDerivativesPhase2(double[] start, double[] end, Array2DRowRealMatrix highOrder) {
        this.transformer.updateHighOrderDerivativesPhase2(start, end, highOrder);
    }
}

