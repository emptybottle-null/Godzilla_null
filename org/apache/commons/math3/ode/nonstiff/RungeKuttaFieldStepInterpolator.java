/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
abstract class RungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>>
extends AbstractFieldStepInterpolator<T> {
    private final Field<T> field;
    private final T[][] yDotK;

    protected RungeKuttaFieldStepInterpolator(Field<T> field, boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> mapper) {
        super(forward, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        this.field = field;
        this.yDotK = (RealFieldElement[][])MathArrays.buildArray(field, yDotK.length, -1);
        for (int i = 0; i < yDotK.length; ++i) {
            this.yDotK[i] = (RealFieldElement[])yDotK[i].clone();
        }
    }

    @Override
    protected RungeKuttaFieldStepInterpolator<T> create(boolean newForward, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return this.create(this.field, newForward, (RealFieldElement[][])this.yDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    protected abstract RungeKuttaFieldStepInterpolator<T> create(Field<T> var1, boolean var2, T[][] var3, FieldODEStateAndDerivative<T> var4, FieldODEStateAndDerivative<T> var5, FieldODEStateAndDerivative<T> var6, FieldODEStateAndDerivative<T> var7, FieldEquationsMapper<T> var8);

    protected final T[] previousStateLinearCombination(T ... coefficients) {
        return this.combine(this.getPreviousState().getState(), (RealFieldElement[])coefficients);
    }

    protected T[] currentStateLinearCombination(T ... coefficients) {
        return this.combine(this.getCurrentState().getState(), (RealFieldElement[])coefficients);
    }

    protected T[] derivativeLinearCombination(T ... coefficients) {
        return this.combine((RealFieldElement[])MathArrays.buildArray(this.field, this.yDotK[0].length), (RealFieldElement[])coefficients);
    }

    private T[] combine(T[] a, T ... coefficients) {
        for (int i = 0; i < a.length; ++i) {
            for (int k = 0; k < coefficients.length; ++k) {
                a[i] = (RealFieldElement)a[i].add(coefficients[k].multiply(this.yDotK[k][i]));
            }
        }
        return a;
    }
}

