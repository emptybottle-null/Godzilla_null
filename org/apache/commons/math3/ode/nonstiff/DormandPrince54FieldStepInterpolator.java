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
class DormandPrince54FieldStepInterpolator<T extends RealFieldElement<T>>
extends RungeKuttaFieldStepInterpolator<T> {
    private final T a70;
    private final T a72;
    private final T a73;
    private final T a74;
    private final T a75;
    private final T d0;
    private final T d2;
    private final T d3;
    private final T d4;
    private final T d5;
    private final T d6;

    DormandPrince54FieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        RealFieldElement one = (RealFieldElement)field.getOne();
        this.a70 = (RealFieldElement)((RealFieldElement)one.multiply(35.0)).divide(384.0);
        this.a72 = (RealFieldElement)((RealFieldElement)one.multiply(500.0)).divide(1113.0);
        this.a73 = (RealFieldElement)((RealFieldElement)one.multiply(125.0)).divide(192.0);
        this.a74 = (RealFieldElement)((RealFieldElement)one.multiply(-2187.0)).divide(6784.0);
        this.a75 = (RealFieldElement)((RealFieldElement)one.multiply(11.0)).divide(84.0);
        this.d0 = (RealFieldElement)((RealFieldElement)one.multiply(-1.2715105075E10)).divide(1.1282082432E10);
        this.d2 = (RealFieldElement)((RealFieldElement)one.multiply(8.74874797E10)).divide(3.2700410799E10);
        this.d3 = (RealFieldElement)((RealFieldElement)one.multiply(-1.0690763975E10)).divide(1.880347072E9);
        this.d4 = (RealFieldElement)((RealFieldElement)one.multiply(7.01980252875E11)).divide(1.99316789632E11);
        this.d5 = (RealFieldElement)((RealFieldElement)one.multiply(-1.453857185E9)).divide(8.22651844E8);
        this.d6 = (RealFieldElement)((RealFieldElement)one.multiply(6.9997945E7)).divide(2.9380423E7);
    }

    @Override
    protected DormandPrince54FieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new DormandPrince54FieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> mapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        RealFieldElement[] interpolatedDerivatives;
        RealFieldElement[] interpolatedState;
        RealFieldElement one = (RealFieldElement)time.getField().getOne();
        RealFieldElement eta = (RealFieldElement)one.subtract(theta);
        RealFieldElement twoTheta = (RealFieldElement)theta.multiply((int)2);
        RealFieldElement dot2 = one.subtract(twoTheta);
        RealFieldElement dot3 = (RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((int)-3)).add(2.0));
        RealFieldElement dot4 = (RealFieldElement)twoTheta.multiply(((RealFieldElement)theta.multiply(twoTheta.subtract(3.0))).add(1.0));
        if (this.getGlobalPreviousState() != null && theta.getReal() <= 0.5) {
            RealFieldElement f1 = thetaH;
            RealFieldElement f2 = f1.multiply((RealFieldElement)eta);
            RealFieldElement f3 = (RealFieldElement)f2.multiply(theta);
            RealFieldElement f4 = f3.multiply(eta);
            RealFieldElement coeff0 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a70)).subtract(f2.multiply(this.a70.subtract(1.0)))).add(f3.multiply(((RealFieldElement)this.a70.multiply((int)2)).subtract(1.0)))).add(f4.multiply(this.d0));
            RealFieldElement coeff1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeff2 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a72)).subtract(f2.multiply(this.a72))).add(f3.multiply(this.a72.multiply((int)2)))).add(f4.multiply(this.d2));
            RealFieldElement coeff3 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a73)).subtract(f2.multiply(this.a73))).add(f3.multiply(this.a73.multiply((int)2)))).add(f4.multiply(this.d3));
            RealFieldElement coeff4 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a74)).subtract(f2.multiply(this.a74))).add(f3.multiply(this.a74.multiply((int)2)))).add(f4.multiply(this.d4));
            RealFieldElement coeff5 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a75)).subtract(f2.multiply(this.a75))).add(f3.multiply(this.a75.multiply((int)2)))).add(f4.multiply(this.d5));
            RealFieldElement coeff6 = ((RealFieldElement)f4.multiply(this.d6)).subtract(f3);
            RealFieldElement coeffDot0 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a70.subtract(dot2.multiply(this.a70.subtract(1.0)))).add(dot3.multiply(((RealFieldElement)this.a70.multiply((int)2)).subtract(1.0)))).add(dot4.multiply(this.d0));
            RealFieldElement coeffDot1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeffDot2 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a72.subtract(dot2.multiply(this.a72))).add(dot3.multiply(this.a72.multiply((int)2)))).add(dot4.multiply(this.d2));
            RealFieldElement coeffDot3 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a73.subtract(dot2.multiply(this.a73))).add(dot3.multiply(this.a73.multiply((int)2)))).add(dot4.multiply(this.d3));
            RealFieldElement coeffDot4 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a74.subtract(dot2.multiply(this.a74))).add(dot3.multiply(this.a74.multiply((int)2)))).add(dot4.multiply(this.d4));
            RealFieldElement coeffDot5 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a75.subtract(dot2.multiply(this.a75))).add(dot3.multiply(this.a75.multiply((int)2)))).add(dot4.multiply(this.d5));
            RealFieldElement coeffDot6 = ((RealFieldElement)dot4.multiply(this.d6)).subtract(dot3);
            interpolatedState = this.previousStateLinearCombination(new RealFieldElement[]{coeff0, coeff1, coeff2, coeff3, coeff4, coeff5, coeff6});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot0, coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6});
        } else {
            RealFieldElement f1 = (RealFieldElement)oneMinusThetaH.negate();
            RealFieldElement f2 = (RealFieldElement)oneMinusThetaH.multiply(theta);
            RealFieldElement f3 = (RealFieldElement)f2.multiply(theta);
            RealFieldElement f4 = f3.multiply(eta);
            RealFieldElement coeff0 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a70)).subtract(f2.multiply(this.a70.subtract(1.0)))).add(f3.multiply(((RealFieldElement)this.a70.multiply((int)2)).subtract(1.0)))).add(f4.multiply(this.d0));
            RealFieldElement coeff1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeff2 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a72)).subtract(f2.multiply(this.a72))).add(f3.multiply(this.a72.multiply((int)2)))).add(f4.multiply(this.d2));
            RealFieldElement coeff3 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a73)).subtract(f2.multiply(this.a73))).add(f3.multiply(this.a73.multiply((int)2)))).add(f4.multiply(this.d3));
            RealFieldElement coeff4 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a74)).subtract(f2.multiply(this.a74))).add(f3.multiply(this.a74.multiply((int)2)))).add(f4.multiply(this.d4));
            RealFieldElement coeff5 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a75)).subtract(f2.multiply(this.a75))).add(f3.multiply(this.a75.multiply((int)2)))).add(f4.multiply(this.d5));
            RealFieldElement coeff6 = ((RealFieldElement)f4.multiply(this.d6)).subtract(f3);
            RealFieldElement coeffDot0 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a70.subtract(dot2.multiply(this.a70.subtract(1.0)))).add(dot3.multiply(((RealFieldElement)this.a70.multiply((int)2)).subtract(1.0)))).add(dot4.multiply(this.d0));
            RealFieldElement coeffDot1 = (RealFieldElement)time.getField().getZero();
            RealFieldElement coeffDot2 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a72.subtract(dot2.multiply(this.a72))).add(dot3.multiply(this.a72.multiply((int)2)))).add(dot4.multiply(this.d2));
            RealFieldElement coeffDot3 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a73.subtract(dot2.multiply(this.a73))).add(dot3.multiply(this.a73.multiply((int)2)))).add(dot4.multiply(this.d3));
            RealFieldElement coeffDot4 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a74.subtract(dot2.multiply(this.a74))).add(dot3.multiply(this.a74.multiply((int)2)))).add(dot4.multiply(this.d4));
            RealFieldElement coeffDot5 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.a75.subtract(dot2.multiply(this.a75))).add(dot3.multiply(this.a75.multiply((int)2)))).add(dot4.multiply(this.d5));
            RealFieldElement coeffDot6 = ((RealFieldElement)dot4.multiply(this.d6)).subtract(dot3);
            interpolatedState = this.currentStateLinearCombination(new RealFieldElement[]{coeff0, coeff1, coeff2, coeff3, coeff4, coeff5, coeff6});
            interpolatedDerivatives = this.derivativeLinearCombination(new RealFieldElement[]{coeffDot0, coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6});
        }
        return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
    }
}

