/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class EulerFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    EulerFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    @Override
    protected EulerFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new EulerFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{thetaH});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{(RealFieldElement)time.getField().getOne()});
        } else {
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{(RealFieldElement)oneMinusThetaH.negate()});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{(RealFieldElement)time.getField().getOne()});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

