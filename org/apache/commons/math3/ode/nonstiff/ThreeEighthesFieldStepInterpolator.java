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
class ThreeEighthesFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    ThreeEighthesFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    @Override
    protected ThreeEighthesFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new ThreeEighthesFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement coeffDot3 = (RealFieldElement)theta.multiply(0.75);
        RealFieldElement coeffDot1 = (RealFieldElement)((RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply((int)4)).subtract(5.0))).add(1.0);
        RealFieldElement coeffDot2 = (RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply((int)-6)).add(5.0));
        RealFieldElement coeffDot4 = (RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply((int)2)).subtract(1.0));
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement s = (RealFieldElement)thetaH.divide(8.0);
            RealFieldElement fourTheta2 = (RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4);
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)((RealFieldElement)fourTheta2.multiply(2)).subtract(theta.multiply((int)15))).add(8.0));
            RealFieldElement coeff2 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply((int)5)).subtract(fourTheta2)).multiply(3);
            RealFieldElement coeff3 = (RealFieldElement)((RealFieldElement)s.multiply(theta)).multiply(3);
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply((int)3)));
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
        } else {
            RealFieldElement s = (RealFieldElement)oneMinusThetaH.divide(-8.0);
            RealFieldElement fourTheta2 = (RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4);
            RealFieldElement thetaPlus1 = (RealFieldElement)theta.add(1.0);
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)((RealFieldElement)fourTheta2.multiply(2)).subtract(theta.multiply((int)7))).add(1.0));
            RealFieldElement coeff2 = (RealFieldElement)s.multiply(thetaPlus1.subtract(fourTheta2)).multiply(3);
            RealFieldElement coeff3 = (RealFieldElement)s.multiply(thetaPlus1).multiply(3);
            RealFieldElement coeff4 = s.multiply(thetaPlus1.add(fourTheta2));
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

