/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class AdamsFieldStepInterpolator<T extends RealFieldElement<T>>
extends AbstractFieldStepInterpolator<T> {
    private T scalingH;
    private final FieldODEStateAndDerivative<T> reference;
    private final T[] scaled;
    private final Array2DRowFieldMatrix<T> nordsieck;

    AdamsFieldStepInterpolator(T stepSize, FieldODEStateAndDerivative<T> reference, T[] scaled, Array2DRowFieldMatrix<T> nordsieck, boolean isForward, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> equationsMapper) {
        this((RealFieldElement)stepSize, reference, (RealFieldElement[])scaled, nordsieck, isForward, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, equationsMapper);
    }

    private AdamsFieldStepInterpolator(T stepSize, FieldODEStateAndDerivative<T> reference, T[] scaled, Array2DRowFieldMatrix<T> nordsieck, boolean isForward, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> equationsMapper) {
        super(isForward, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, equationsMapper);
        this.scalingH = stepSize;
        this.reference = reference;
        this.scaled = (RealFieldElement[])scaled.clone();
        this.nordsieck = new Array2DRowFieldMatrix(nordsieck.getData(), false);
    }

    @Override
    protected AdamsFieldStepInterpolator<T> create(boolean newForward, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new AdamsFieldStepInterpolator(this.scalingH, this.reference, this.scaled, this.nordsieck, newForward, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> equationsMapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        return AdamsFieldStepInterpolator.taylor(this.reference, time, this.scalingH, this.scaled, this.nordsieck);
    }

    public static <S extends RealFieldElement<S>> FieldODEStateAndDerivative<S> taylor(FieldODEStateAndDerivative<S> reference, S time, S stepSize, S[] scaled, Array2DRowFieldMatrix<S> nordsieck) {
        RealFieldElement x = (RealFieldElement)time.subtract(reference.getTime());
        S normalizedAbscissa = x.divide(stepSize);
        Object[] stateVariation = (RealFieldElement[])MathArrays.buildArray(time.getField(), scaled.length);
        Arrays.fill(stateVariation, time.getField().getZero());
        Object[] estimatedDerivatives = (RealFieldElement[])MathArrays.buildArray(time.getField(), scaled.length);
        Arrays.fill(estimatedDerivatives, time.getField().getZero());
        RealFieldElement[][] nData = (RealFieldElement[][])nordsieck.getDataRef();
        for (int i = nData.length - 1; i >= 0; --i) {
            int order = i + 2;
            RealFieldElement[] nDataI = nData[i];
            RealFieldElement power = (RealFieldElement)normalizedAbscissa.pow((int)order);
            for (int j = 0; j < nDataI.length; ++j) {
                RealFieldElement d = nDataI[j].multiply(power);
                stateVariation[j] = stateVariation[j].add(d);
                estimatedDerivatives[j] = (RealFieldElement)estimatedDerivatives[j].add(d.multiply(order));
            }
        }
        RealFieldElement[] estimatedState = reference.getState();
        for (int j = 0; j < stateVariation.length; ++j) {
            stateVariation[j] = stateVariation[j].add(scaled[j].multiply(normalizedAbscissa));
            estimatedState[j] = (RealFieldElement)estimatedState[j].add(stateVariation[j]);
            estimatedDerivatives[j] = estimatedDerivatives[j].add(scaled[j].multiply(normalizedAbscissa)).divide((RealFieldElement)x);
        }
        return new FieldODEStateAndDerivative(time, estimatedState, (RealFieldElement[])estimatedDerivatives);
    }
}

