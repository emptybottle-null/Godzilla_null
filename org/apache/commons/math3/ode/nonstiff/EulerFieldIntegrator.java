/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.EulerFieldStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EulerFieldIntegrator<T extends RealFieldElement<T>>
extends RungeKuttaFieldIntegrator<T> {
    public EulerFieldIntegrator(Field<T> field, T step) {
        super(field, "Euler", step);
    }

    @Override
    public T[] getC() {
        return (RealFieldElement[])MathArrays.buildArray(this.getField(), 0);
    }

    @Override
    public T[][] getA() {
        return (RealFieldElement[][])MathArrays.buildArray(this.getField(), 0, 0);
    }

    @Override
    public T[] getB() {
        RealFieldElement[] b = (RealFieldElement[])MathArrays.buildArray(this.getField(), 1);
        b[0] = (RealFieldElement)this.getField().getOne();
        return b;
    }

    @Override
    protected EulerFieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper) {
        return new EulerFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }
}

