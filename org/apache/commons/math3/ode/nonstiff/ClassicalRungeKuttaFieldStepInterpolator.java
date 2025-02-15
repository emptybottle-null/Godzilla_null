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
class ClassicalRungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    ClassicalRungeKuttaFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    @Override
    protected ClassicalRungeKuttaFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement one = (RealFieldElement)time.getField().getOne();
        RealFieldElement oneMinusTheta = (RealFieldElement)one.subtract(theta);
        RealFieldElement oneMinus2Theta = (RealFieldElement)one.subtract(theta.multiply((int)2));
        RealFieldElement coeffDot1 = oneMinusTheta.multiply(oneMinus2Theta);
        RealFieldElement coeffDot23 = (RealFieldElement)theta.multiply((RealFieldElement)oneMinusTheta).multiply(2);
        RealFieldElement coeffDot4 = (RealFieldElement)theta.multiply((RealFieldElement)oneMinus2Theta).negate();
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement fourTheta2 = (RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4);
            RealFieldElement s = (RealFieldElement)thetaH.divide(6.0);
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply((int)9))).add(6.0));
            RealFieldElement coeff23 = s.multiply(((RealFieldElement)theta.multiply((int)6)).subtract(fourTheta2));
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply((int)3)));
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff23, coeff23, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot23, coeffDot23, coeffDot4});
        } else {
            RealFieldElement fourTheta = (RealFieldElement)theta.multiply((int)4);
            RealFieldElement s = (RealFieldElement)oneMinusThetaH.divide(6.0);
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)fourTheta.negate()).add(5.0))).subtract(1.0));
            RealFieldElement coeff23 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(fourTheta.subtract(2.0))).subtract(2.0));
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)fourTheta.negate()).subtract(1.0))).subtract(1.0));
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff23, coeff23, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot23, coeffDot23, coeffDot4});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

