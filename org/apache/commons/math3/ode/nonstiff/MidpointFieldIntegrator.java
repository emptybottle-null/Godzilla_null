/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.MidpointFieldStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MidpointFieldIntegrator<T extends RealFieldElement<T>>
extends RungeKuttaFieldIntegrator<T> {
    public MidpointFieldIntegrator(Field<T> field, T step) {
        super(field, "midpoint", step);
    }

    @Override
    public T[] getC() {
        RealFieldElement[] c = (RealFieldElement[])MathArrays.buildArray(this.getField(), 1);
        c[0] = (RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(0.5);
        return c;
    }

    @Override
    public T[][] getA() {
        RealFieldElement[][] a = (RealFieldElement[][])MathArrays.buildArray(this.getField(), 1, 1);
        a[0][0] = this.fraction(1, 2);
        return a;
    }

    @Override
    public T[] getB() {
        RealFieldElement[] b = (RealFieldElement[])MathArrays.buildArray(this.getField(), 2);
        b[0] = (RealFieldElement)this.getField().getZero();
        b[1] = (RealFieldElement)this.getField().getOne();
        return b;
    }

    @Override
    protected MidpointFieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper) {
        return new MidpointFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }
}

