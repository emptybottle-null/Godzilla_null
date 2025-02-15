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
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class EmbeddedRungeKuttaFieldIntegrator<T extends RealFieldElement<T>>
extends AdaptiveStepsizeFieldIntegrator<T>
implements FieldButcherArrayProvider<T> {
    private final int fsal;
    private final T[] c;
    private final T[][] a;
    private final T[] b;
    private final T exp;
    private T safety;
    private T minReduction;
    private T maxGrowth;

    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String name, int fsal, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(field, name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.fsal = fsal;
        this.c = this.getC();
        this.a = this.getA();
        this.b = this.getB();
        this.exp = (RealFieldElement)((RealFieldElement)field.getOne()).divide(-this.getOrder());
        this.setSafety((RealFieldElement)((RealFieldElement)field.getZero()).add(0.9));
        this.setMinReduction((RealFieldElement)((RealFieldElement)field.getZero()).add(0.2));
        this.setMaxGrowth((RealFieldElement)((RealFieldElement)field.getZero()).add(10.0));
    }

    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String name, int fsal, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.fsal = fsal;
        this.c = this.getC();
        this.a = this.getA();
        this.b = this.getB();
        this.exp = (RealFieldElement)((RealFieldElement)field.getOne()).divide(-this.getOrder());
        this.setSafety((RealFieldElement)((RealFieldElement)field.getZero()).add(0.9));
        this.setMinReduction((RealFieldElement)((RealFieldElement)field.getZero()).add(0.2));
        this.setMaxGrowth((RealFieldElement)((RealFieldElement)field.getZero()).add(10.0));
    }

    protected T fraction(int p, int q) {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(p)).divide(q));
    }

    protected T fraction(double p, double q) {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(p)).divide(q));
    }

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean var1, T[][] var2, FieldODEStateAndDerivative<T> var3, FieldODEStateAndDerivative<T> var4, FieldEquationsMapper<T> var5);

    public abstract int getOrder();

    public T getSafety() {
        return this.safety;
    }

    public void setSafety(T safety) {
        this.safety = safety;
    }

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
        RealFieldElement hNew = (RealFieldElement)this.getField().getZero();
        boolean firstTime = true;
        this.setIsLastStep(false);
        do {
            boolean filteredNextIsLast;
            RealFieldElement error = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(10.0);
            while (((RealFieldElement)error.subtract(1.0)).getReal() >= 0.0) {
                y = equations.getMapper().mapState(this.getStepStart());
                yDotK[0] = equations.getMapper().mapDerivative(this.getStepStart());
                if (firstTime) {
                    int i;
                    RealFieldElement[] scale = (RealFieldElement[])MathArrays.buildArray(this.getField(), this.mainSetDimension);
                    if (this.vecAbsoluteTolerance == null) {
                        for (i = 0; i < scale.length; ++i) {
                            scale[i] = (RealFieldElement)((RealFieldElement)((RealFieldElement)y[i].abs()).multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance);
                        }
                    } else {
                        for (i = 0; i < scale.length; ++i) {
                            scale[i] = (RealFieldElement)((RealFieldElement)((RealFieldElement)y[i].abs()).multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i]);
                        }
                    }
                    hNew = this.initializeStep(forward, this.getOrder(), scale, this.getStepStart(), equations.getMapper());
                    firstTime = false;
                }
                this.setStepSize(hNew);
                if (forward) {
                    if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())).subtract(finalTime)).getReal() >= 0.0) {
                        this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
                    }
                } else if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())).subtract(finalTime)).getReal() <= 0.0) {
                    this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
                }
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
                error = this.estimateError(yDotK, y, yTmp, (RealFieldElement)this.getStepSize());
                if (!(((RealFieldElement)error.subtract(1.0)).getReal() >= 0.0)) continue;
                RealFieldElement factor = MathUtils.min(this.maxGrowth, MathUtils.max(this.minReduction, (RealFieldElement)this.safety.multiply(error.pow(this.exp))));
                hNew = this.filterStep(this.getStepSize().multiply((RealFieldElement)factor), forward, false);
            }
            RealFieldElement stepEnd = (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize());
            RealFieldElement[] yDotTmp = this.fsal >= 0 ? yDotK[this.fsal] : this.computeDerivatives(stepEnd, yTmp);
            FieldODEStateAndDerivative stateTmp = new FieldODEStateAndDerivative(stepEnd, yTmp, yDotTmp);
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            this.setStepStart(this.acceptStep(this.createInterpolator(forward, yDotK, this.getStepStart(), stateTmp, equations.getMapper()), finalTime));
            if (this.isLastStep()) continue;
            RealFieldElement factor = MathUtils.min(this.maxGrowth, MathUtils.max(this.minReduction, (RealFieldElement)this.safety.multiply(error.pow(this.exp))));
            RealFieldElement scaledH = this.getStepSize().multiply((RealFieldElement)factor);
            RealFieldElement nextT = this.getStepStart().getTime().add((RealFieldElement)scaledH);
            boolean nextIsLast = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= 0.0 : ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= 0.0;
            hNew = this.filterStep(scaledH, forward, nextIsLast);
            RealFieldElement filteredNextT = this.getStepStart().getTime().add((RealFieldElement)hNew);
            boolean bl = forward ? ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() >= 0.0 : (filteredNextIsLast = ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() <= 0.0);
            if (!filteredNextIsLast) continue;
            hNew = (RealFieldElement)finalTime.subtract(this.getStepStart().getTime());
        } while (!this.isLastStep());
        FieldODEStateAndDerivative finalState = this.getStepStart();
        this.resetInternalState();
        return finalState;
    }

    public T getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(T minReduction) {
        this.minReduction = minReduction;
    }

    public T getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(T maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    protected abstract T estimateError(T[][] var1, T[] var2, T[] var3, T var4);
}

