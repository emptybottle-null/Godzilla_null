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
class LutherFieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    private final T c5a;
    private final T c5b;
    private final T c5c;
    private final T c5d;
    private final T c6a;
    private final T c6b;
    private final T c6c;
    private final T c6d;
    private final T d5a;
    private final T d5b;
    private final T d5c;
    private final T d6a;
    private final T d6b;
    private final T d6c;

    LutherFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        RealFieldElement q = (RealFieldElement)((RealFieldElement)((RealFieldElement)field.getZero()).add(21.0)).sqrt();
        this.c5a = (RealFieldElement)((RealFieldElement)q.multiply(-49)).add(-49.0);
        this.c5b = (RealFieldElement)((RealFieldElement)q.multiply(287)).add(392.0);
        this.c5c = (RealFieldElement)((RealFieldElement)q.multiply(-357)).add(-637.0);
        this.c5d = (RealFieldElement)((RealFieldElement)q.multiply(343)).add(833.0);
        this.c6a = (RealFieldElement)((RealFieldElement)q.multiply(49)).add(-49.0);
        this.c6b = (RealFieldElement)((RealFieldElement)q.multiply(-287)).add(392.0);
        this.c6c = (RealFieldElement)((RealFieldElement)q.multiply(357)).add(-637.0);
        this.c6d = (RealFieldElement)((RealFieldElement)q.multiply(-343)).add(833.0);
        this.d5a = (RealFieldElement)((RealFieldElement)q.multiply(49)).add(49.0);
        this.d5b = (RealFieldElement)((RealFieldElement)q.multiply(-847)).add(-1372.0);
        this.d5c = (RealFieldElement)((RealFieldElement)q.multiply(1029)).add(2254.0);
        this.d6a = (RealFieldElement)((RealFieldElement)q.multiply(-49)).add(49.0);
        this.d6b = (RealFieldElement)((RealFieldElement)q.multiply(847)).add(-1372.0);
        this.d6c = (RealFieldElement)((RealFieldElement)q.multiply(-1029)).add(2254.0);
    }

    @Override
    protected LutherFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new LutherFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement coeffDot1 = (RealFieldElement)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((int)21)).add(-47.0))).add(36.0))).add(-10.8))).add(1.0);
        RealFieldElement coeffDot2 = (RealFieldElement)time.getField().getZero();
        RealFieldElement coeffDot3 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((int)112)).add(-202.66666666666666))).add(106.66666666666667))).add(-13.866666666666667));
        RealFieldElement coeffDot4 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-113.4)).add(194.4))).add(-97.2))).add(12.96));
        RealFieldElement coeffDot5 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c5a.divide(5.0))).add(this.c5b.divide(15.0)))).add(this.c5c.divide(30.0)))).add(this.c5d.divide(150.0)));
        RealFieldElement coeffDot6 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c6a.divide(5.0))).add(this.c6b.divide(15.0)))).add(this.c6c.divide(30.0)))).add(this.c6d.divide(150.0)));
        RealFieldElement coeffDot7 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(3.0)).add(-3.0))).add(0.6));
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            Object s = thetaH;
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(4.2)).add(-11.75))).add(12.0))).add(-5.4))).add(1.0));
            RealFieldElement coeff2 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeff3 = (RealFieldElement)s.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(22.4)).add(-50.666666666666664))).add(35.55555555555556))).add(-6.933333333333334)));
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-22.68)).add(48.6))).add(-32.4))).add(6.48)));
            RealFieldElement coeff5 = (RealFieldElement)s.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c5a.divide(25.0))).add(this.c5b.divide(60.0)))).add(this.c5c.divide(90.0)))).add(this.c5d.divide(300.0))));
            RealFieldElement coeff6 = (RealFieldElement)s.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c6a.divide(25.0))).add(this.c6b.divide(60.0)))).add(this.c6c.divide(90.0)))).add(this.c6d.divide(300.0))));
            RealFieldElement coeff7 = (RealFieldElement)s.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(0.75)).add(-1.0))).add(0.3)));
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7});
        } else {
            Object s = oneMinusThetaH;
            RealFieldElement coeff1 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-4.2)).add(7.55))).add(-4.45))).add(0.95))).add(-0.05));
            RealFieldElement coeff2 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeff3 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-22.4)).add(28.266666666666666))).add(-7.288888888888889))).add(-0.35555555555555557))).add(-0.35555555555555557));
            RealFieldElement coeff4 = (RealFieldElement)s.multiply(theta.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(22.68)).add(-25.92))).add(6.48))));
            RealFieldElement coeff5 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.d5a.divide(25.0))).add(this.d5b.divide(300.0)))).add(this.d5c.divide(900.0)))).add(-0.2722222222222222))).add(-0.2722222222222222));
            RealFieldElement coeff6 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.d6a.divide(25.0))).add(this.d6b.divide(300.0)))).add(this.d6c.divide(900.0)))).add(-0.2722222222222222))).add(-0.2722222222222222));
            RealFieldElement coeff7 = (RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-0.75)).add(0.25))).add(-0.05))).add(-0.05));
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

