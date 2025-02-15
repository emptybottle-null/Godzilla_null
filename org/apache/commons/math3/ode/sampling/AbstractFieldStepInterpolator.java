/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractFieldStepInterpolator<T extends RealFieldElement<T>>
implements FieldStepInterpolator<T> {
    private final FieldODEStateAndDerivative<T> globalPreviousState;
    private final FieldODEStateAndDerivative<T> globalCurrentState;
    private final FieldODEStateAndDerivative<T> softPreviousState;
    private final FieldODEStateAndDerivative<T> softCurrentState;
    private final boolean forward;
    private FieldEquationsMapper<T> mapper;

    protected AbstractFieldStepInterpolator(boolean isForward, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldODEStateAndDerivative<T> softPreviousState, FieldODEStateAndDerivative<T> softCurrentState, FieldEquationsMapper<T> equationsMapper) {
        this.forward = isForward;
        this.globalPreviousState = globalPreviousState;
        this.globalCurrentState = globalCurrentState;
        this.softPreviousState = softPreviousState;
        this.softCurrentState = softCurrentState;
        this.mapper = equationsMapper;
    }

    public AbstractFieldStepInterpolator<T> restrictStep(FieldODEStateAndDerivative<T> previousState, FieldODEStateAndDerivative<T> currentState) {
        return this.create(this.forward, this.globalPreviousState, this.globalCurrentState, previousState, currentState, this.mapper);
    }

    protected abstract AbstractFieldStepInterpolator<T> create(boolean var1, FieldODEStateAndDerivative<T> var2, FieldODEStateAndDerivative<T> var3, FieldODEStateAndDerivative<T> var4, FieldODEStateAndDerivative<T> var5, FieldEquationsMapper<T> var6);

    public FieldODEStateAndDerivative<T> getGlobalPreviousState() {
        return this.globalPreviousState;
    }

    public FieldODEStateAndDerivative<T> getGlobalCurrentState() {
        return this.globalCurrentState;
    }

    @Override
    public FieldODEStateAndDerivative<T> getPreviousState() {
        return this.softPreviousState;
    }

    @Override
    public FieldODEStateAndDerivative<T> getCurrentState() {
        return this.softCurrentState;
    }

    @Override
    public FieldODEStateAndDerivative<T> getInterpolatedState(T time) {
        RealFieldElement thetaH = (RealFieldElement)time.subtract(this.globalPreviousState.getTime());
        RealFieldElement oneMinusThetaH = (RealFieldElement)this.globalCurrentState.getTime().subtract(time);
        RealFieldElement theta = (RealFieldElement)thetaH.divide(this.globalCurrentState.getTime().subtract(this.globalPreviousState.getTime()));
        return this.computeInterpolatedStateAndDerivatives(this.mapper, time, theta, thetaH, oneMinusThetaH);
    }

    @Override
    public boolean isForward() {
        return this.forward;
    }

    protected abstract FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> var1, T var2, T var3, T var4, T var5) throws MaxCountExceededException;
}

