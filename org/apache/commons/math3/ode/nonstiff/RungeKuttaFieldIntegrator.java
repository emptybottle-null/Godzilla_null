/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class RungeKuttaFieldIntegrator<T extends RealFieldElement<T>>
extends AbstractFieldIntegrator<T>
implements FieldButcherArrayProvider<T> {
    private final T[] c = this.getC();
    private final T[][] a = this.getA();
    private final T[] b = this.getB();
    private final T step;

    protected RungeKuttaFieldIntegrator(Field<T> field, String name, T step) {
        super(field, name);
        this.step = (RealFieldElement)step.abs();
    }

    protected T fraction(int p, int q) {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add(p)).divide(q));
    }

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean var1, T[][] var2, FieldODEStateAndDerivative<T> var3, FieldODEStateAndDerivative<T> var4, FieldEquationsMapper<T> var5);

    @Override
    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> equations, FieldODEState<T> initialState, T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        this.sanityChecks(initialState, finalTime);
        T t0 = initialState.getTime();
        RealFieldElement[] y0 = equations.getMapper().mapState(initialState);
        this.setStepStart(this.initIntegration(equations, (RealFieldElement)t0, y0, (RealFieldElement)finalTime));
        boolean forward = ((RealFieldElement)finalTime.subtract(initialState.getTime())).getReal() > 0.0;
        int stages = this.c.length + 1;
        RealFieldElement[] y = y0;
        RealFieldElement[][] yDotK = (RealFieldElement[][])MathArrays.buildArray(this.getField(), stages, -1);
        RealFieldElement[] yTmp = (RealFieldElement[])MathArrays.buildArray(this.getField(), y0.length);
        if (forward) {
            if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.step)).subtract(finalTime)).getReal() >= 0.0) {
                this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
            } else {
                this.setStepSize(this.step);
            }
        } else if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().subtract(this.step)).subtract(finalTime)).getReal() <= 0.0) {
            this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
        } else {
            this.setStepSize((RealFieldElement)this.step.negate());
        }
        this.setIsLastStep(false);
        do {
            boolean nextIsLast;
            y = equations.getMapper().mapState(this.getStepStart());
            yDotK[0] = equations.getMapper().mapDerivative(this.getStepStart());
            for (int k = 1; k < stages; ++k) {
                for (int j = 0; j < y0.length; ++j) {
                    RealFieldElement sum = (RealFieldElement)yDotK[0][j].multiply(this.a[k - 1][0]);
                    for (int l = 1; l < k; ++l) {
                        sum = (RealFieldElement)sum.add(yDotK[l][j].multiply(this.a[k - 1][l]));
                    }
                    yTmp[j] = y[j].add(this.getStepSize().multiply((RealFieldElement)sum));
                }
                yDotK[k] = this.computeDerivatives((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize().multiply(this.c[k - 1])), yTmp);
            }
            for (int j = 0; j < y0.length; ++j) {
                RealFieldElement sum = (RealFieldElement)yDotK[0][j].multiply(this.b[0]);
                for (int l = 1; l < stages; ++l) {
                    sum = (RealFieldElement)sum.add(yDotK[l][j].multiply(this.b[l]));
                }
                yTmp[j] = y[j].add(this.getStepSize().multiply((RealFieldElement)sum));
            }
            RealFieldElement stepEnd = (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize());
            RealFieldElement[] yDotTmp = this.computeDerivatives(stepEnd, yTmp);
            FieldODEStateAndDerivative stateTmp = new FieldODEStateAndDerivative(stepEnd, yTmp, yDotTmp);
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            this.setStepStart(this.acceptStep(this.createInterpolator(forward, yDotK, this.getStepStart(), stateTmp, equations.getMapper()), finalTime));
            if (this.isLastStep()) continue;
            RealFieldElement nextT = (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize());
            boolean bl = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= 0.0 : (nextIsLast = ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= 0.0);
            if (!nextIsLast) continue;
            this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
        } while (!this.isLastStep());
        FieldODEStateAndDerivative finalState = this.getStepStart();
        this.setStepStart(null);
        this.setStepSize(null);
        return finalState;
    }

    public T[] singleStep(FirstOrderFieldDifferentialEquations<T> equations, T t0, T[] y0, T t) {
        RealFieldElement[] y = (RealFieldElement[])y0.clone();
        int stages = this.c.length + 1;
        RealFieldElement[][] yDotK = (RealFieldElement[][])MathArrays.buildArray(this.getField(), stages, -1);
        RealFieldElement[] yTmp = (RealFieldElement[])y0.clone();
        RealFieldElement h = (RealFieldElement)t.subtract(t0);
        yDotK[0] = equations.computeDerivatives((RealFieldElement)t0, y);
        for (int k = 1; k < stages; ++k) {
            for (int j = 0; j < y0.length; ++j) {
                RealFieldElement sum = (RealFieldElement)yDotK[0][j].multiply(this.a[k - 1][0]);
                for (int l = 1; l < k; ++l) {
                    sum = (RealFieldElement)sum.add(yDotK[l][j].multiply(this.a[k - 1][l]));
                }
                yTmp[j] = y[j].add(h.multiply(sum));
            }
            yDotK[k] = equations.computeDerivatives((RealFieldElement)t0.add(h.multiply(this.c[k - 1])), yTmp);
        }
        for (int j = 0; j < y0.length; ++j) {
            RealFieldElement sum = (RealFieldElement)yDotK[0][j].multiply(this.b[0]);
            for (int l = 1; l < stages; ++l) {
                sum = (RealFieldElement)sum.add(yDotK[l][j].multiply(this.b[l]));
            }
            y[j] = y[j].add(h.multiply(sum));
        }
        return y;
    }
}

