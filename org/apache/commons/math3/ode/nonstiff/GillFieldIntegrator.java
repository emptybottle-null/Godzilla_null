/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.GillFieldStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GillFieldIntegrator<T extends RealFieldElement<T>>
extends RungeKuttaFieldIntegrator<T> {
    public GillFieldIntegrator(Field<T> field, T step) {
        super(field, "Gill", step);
    }

    @Override
    public T[] getC() {
        RealFieldElement[] c = (RealFieldElement[])MathArrays.buildArray(this.getField(), 3);
        c[0] = this.fraction(1, 2);
        c[1] = c[0];
        c[2] = (RealFieldElement)this.getField().getOne();
        return c;
    }

    @Override
    public T[][] getA() {
        RealFieldElement two = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(2.0);
        RealFieldElement sqrtTwo = (RealFieldElement)two.sqrt();
        RealFieldElement[][] a = (RealFieldElement[][])MathArrays.buildArray(this.getField(), 3, -1);
        for (int i = 0; i < a.length; ++i) {
            a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
        }
        a[0][0] = this.fraction(1, 2);
        a[1][0] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract(1.0)).multiply(0.5);
        a[1][1] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract(2.0)).multiply(-0.5);
        a[2][0] = (RealFieldElement)this.getField().getZero();
        a[2][1] = (RealFieldElement)sqrtTwo.multiply(-0.5);
        a[2][2] = (RealFieldElement)((RealFieldElement)sqrtTwo.add(2.0)).multiply(0.5);
        return a;
    }

    @Override
    public T[] getB() {
        RealFieldElement two = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(2.0);
        RealFieldElement sqrtTwo = (RealFieldElement)two.sqrt();
        RealFieldElement[] b = (RealFieldElement[])MathArrays.buildArray(this.getField(), 4);
        b[0] = this.fraction(1, 6);
        b[1] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract(2.0)).divide(-6.0);
        b[2] = (RealFieldElement)((RealFieldElement)sqrtTwo.add(2.0)).divide(6.0);
        b[3] = b[0];
        return b;
    }

    @Override
    protected GillFieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper) {
        return new GillFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }
}

