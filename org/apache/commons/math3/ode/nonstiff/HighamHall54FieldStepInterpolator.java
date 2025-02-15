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
class HighamHall54FieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    HighamHall54FieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    @Override
    protected HighamHall54FieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new HighamHall54FieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement bDot0 = (RealFieldElement)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-10.0)).add(16.0))).add(-7.5))).add(1.0);
        RealFieldElement bDot1 = (RealFieldElement)time.getField().getZero();
        RealFieldElement bDot2 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(67.5)).add(-91.125))).add(28.6875));
        RealFieldElement bDot3 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-120.0)).add(152.0))).add(-44.0));
        RealFieldElement bDot4 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(62.5)).add(-78.125))).add(23.4375));
        RealFieldElement bDot5 = (RealFieldElement)((RealFieldElement)theta.multiply(0.625)).multiply(((RealFieldElement)theta.multiply((int)2)).subtract(1.0));
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement b0 = (RealFieldElement)thetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-2.5)).add(5.333333333333333))).add(-3.75))).add(1.0));
            RealFieldElement b1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement b2 = (RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(16.875)).add(-30.375))).add(14.34375)));
            RealFieldElement b3 = (RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-30.0)).add(50.666666666666664))).add(-22.0)));
            RealFieldElement b4 = (RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(15.625)).add(-26.041666666666668))).add(11.71875)));
            RealFieldElement b5 = (RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(0.4166666666666667)).add(-0.3125)));
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{b0, b1, b2, b3, b4, b5});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{bDot0, bDot1, bDot2, bDot3, bDot4, bDot5});
        } else {
            RealFieldElement theta2 = (RealFieldElement)theta.multiply(theta);
            RealFieldElement h = (RealFieldElement)thetaH.divide(theta);
            RealFieldElement b0 = (RealFieldElement)h.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-2.5)).add(5.333333333333333))).add(-3.75))).add(1.0))).add(-0.08333333333333333));
            RealFieldElement b1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement b2 = (RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(16.875)).add(-30.375))).add(14.34375))).add(-0.84375));
            RealFieldElement b3 = (RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-30.0)).add(50.666666666666664))).add(-22.0))).add(1.3333333333333333));
            RealFieldElement b4 = (RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(15.625)).add(-26.041666666666668))).add(11.71875))).add(-1.3020833333333333));
            RealFieldElement b5 = (RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(0.4166666666666667)).add(-0.3125))).add(-0.10416666666666667));
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{b0, b1, b2, b3, b4, b5});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{bDot0, bDot1, bDot2, bDot3, bDot4, bDot5});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

