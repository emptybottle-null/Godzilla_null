/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LutherFieldIntegrator<T extends RealFieldElement<T>>
extends RungeKuttaFieldIntegrator<T> {
    public LutherFieldIntegrator(Field<T> field, T step) {
        super(field, "Luther", step);
    }

    @Override
    public T[] getC() {
        RealFieldElement q = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add(21.0)).sqrt();
        RealFieldElement[] c = (RealFieldElement[])MathArrays.buildArray(this.getField(), 6);
        c[0] = (RealFieldElement)this.getField().getOne();
        c[1] = this.fraction(1, 2);
        c[2] = this.fraction(2, 3);
        c[3] = (RealFieldElement)((RealFieldElement)q.subtract(7.0)).divide(-14.0);
        c[4] = (RealFieldElement)((RealFieldElement)q.add(7.0)).divide(14.0);
        c[5] = (RealFieldElement)this.getField().getOne();
        return c;
    }

    @Override
    public T[][] getA() {
        RealFieldElement q = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add(21.0)).sqrt();
        RealFieldElement[][] a = (RealFieldElement[][])MathArrays.buildArray(this.getField(), 6, -1);
        for (int i = 0; i < a.length; ++i) {
            a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
        }
        a[0][0] = (RealFieldElement)this.getField().getOne();
        a[1][0] = this.fraction(3, 8);
        a[1][1] = this.fraction(1, 8);
        a[2][0] = this.fraction(8, 27);
        a[2][1] = this.fraction(2, 27);
        a[2][2] = a[2][0];
        a[3][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(9)).add(-21.0)).divide(392.0);
        a[3][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(8)).add(-56.0)).divide(392.0);
        a[3][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-48)).add(336.0)).divide(392.0);
        a[3][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(3)).add(-63.0)).divide(392.0);
        a[4][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-255)).add(-1155.0)).divide(1960.0);
        a[4][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-40)).add(-280.0)).divide(1960.0);
        a[4][2] = (RealFieldElement)((RealFieldElement)q.multiply(-320)).divide(1960.0);
        a[4][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(363)).add(63.0)).divide(1960.0);
        a[4][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(392)).add(2352.0)).divide(1960.0);
        a[5][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(105)).add(330.0)).divide(180.0);
        a[5][1] = this.fraction(2, 3);
        a[5][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(280)).add(-200.0)).divide(180.0);
        a[5][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-189)).add(126.0)).divide(180.0);
        a[5][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-126)).add(-686.0)).divide(180.0);
        a[5][5] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-70)).add(490.0)).divide(180.0);
        return a;
    }

    @Override
    public T[] getB() {
        RealFieldElement[] b = (RealFieldElement[])MathArrays.buildArray(this.getField(), 7);
        b[0] = this.fraction(1, 20);
        b[1] = (RealFieldElement)this.getField().getZero();
        b[2] = this.fraction(16, 45);
        b[3] = (RealFieldElement)this.getField().getZero();
        b[4] = this.fraction(49, 180);
        b[5] = b[4];
        b[6] = b[0];
        return b;
    }

    @Override
    protected LutherFieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper) {
        return new LutherFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }
}

