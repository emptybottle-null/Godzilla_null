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
class GillFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    private final T one_minus_inv_sqrt_2;
    private final T one_plus_inv_sqrt_2;

    GillFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        RealFieldElement sqrt = (RealFieldElement)((RealFieldElement)((RealFieldElement)field.getZero()).add(0.5)).sqrt();
        this.one_minus_inv_sqrt_2 = ((RealFieldElement)field.getOne()).subtract(sqrt);
        this.one_plus_inv_sqrt_2 = ((RealFieldElement)field.getOne()).add(sqrt);
    }

    @Override
    protected GillFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new GillFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement one = (RealFieldElement)time.getField().getOne();
        RealFieldElement twoTheta = (RealFieldElement)theta.multiply((int)2);
        RealFieldElement fourTheta2 = twoTheta.multiply(twoTheta);
        RealFieldElement coeffDot1 = (RealFieldElement)((RealFieldElement)theta.multiply(twoTheta.subtract(3.0))).add(1.0);
        RealFieldElement cDot23 = (RealFieldElement)twoTheta.multiply(one.subtract(theta));
        RealFieldElement coeffDot2 = (RealFieldElement)cDot23.multiply(this.one_minus_inv_sqrt_2);
        RealFieldElement coeffDot3 = (RealFieldElement)cDot23.multiply(this.one_plus_inv_sqrt_2);
        RealFieldElement coeffDot4 = (RealFieldElement)theta.multiply(twoTheta.subtract(1.0));
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement s = (RealFieldElement)thetaH.divide(6.0);
            RealFieldElement c23 = s.multiply(((RealFieldElement)theta.multiply((int)6)).subtract(fourTheta2));
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply((int)9))).add(6.0));
            RealFieldElement coeff2 = (RealFieldElement)c23.multiply(this.one_minus_inv_sqrt_2);
            RealFieldElement coeff3 = (RealFieldElement)c23.multiply(this.one_plus_inv_sqrt_2);
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply((int)3)));
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
        } else {
            RealFieldElement s = (RealFieldElement)oneMinusThetaH.divide(-6.0);
            RealFieldElement c23 = s.multiply(((RealFieldElement)twoTheta.add(2.0)).subtract(fourTheta2));
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply((int)5))).add(1.0));
            RealFieldElement coeff2 = (RealFieldElement)c23.multiply(this.one_minus_inv_sqrt_2);
            RealFieldElement coeff3 = (RealFieldElement)c23.multiply(this.one_plus_inv_sqrt_2);
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.add(theta)).add(1.0));
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

