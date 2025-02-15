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
class MidpointFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    MidpointFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    @Override
    protected MidpointFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new MidpointFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement coeffDot2 = (RealFieldElement)theta.multiply((int)2);
        RealFieldElement coeffDot1 = ((RealFieldElement)time.getField().getOne()).subtract(coeffDot2);
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement coeff1 = (RealFieldElement)theta.multiply(oneMinusThetaH);
            RealFieldElement coeff2 = (RealFieldElement)theta.multiply(thetaH);
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2});
        } else {
            RealFieldElement coeff1 = (RealFieldElement)oneMinusThetaH.multiply(theta);
            RealFieldElement coeff2 = (RealFieldElement)((RealFieldElement)oneMinusThetaH.multiply(theta.add(1.0))).negate();
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

