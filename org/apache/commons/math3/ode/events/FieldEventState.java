/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.events.Action;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldEventState<T extends RealFieldElement<T>> {
    private final FieldEventHandler<T> handler;
    private final double maxCheckInterval;
    private final T convergence;
    private final int maxIterationCount;
    private T t0;
    private T g0;
    private boolean g0Positive;
    private boolean pendingEvent;
    private T pendingEventTime;
    private T previousEventTime;
    private boolean forward;
    private boolean increasing;
    private Action nextAction;
    private final BracketedRealFieldUnivariateSolver<T> solver;

    public FieldEventState(FieldEventHandler<T> handler, double maxCheckInterval, T convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver<T> solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = (RealFieldElement)convergence.abs();
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
        this.t0 = null;
        this.g0 = null;
        this.g0Positive = true;
        this.pendingEvent = false;
        this.pendingEventTime = null;
        this.previousEventTime = null;
        this.increasing = true;
        this.nextAction = Action.CONTINUE;
    }

    public FieldEventHandler<T> getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public T getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(FieldStepInterpolator<T> interpolator) throws MaxCountExceededException {
        FieldODEStateAndDerivative<T> s0 = interpolator.getPreviousState();
        this.t0 = s0.getTime();
        this.g0 = this.handler.g(s0);
        if (this.g0.getReal() == 0.0) {
            double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy().getReal(), FastMath.abs(((RealFieldElement)this.solver.getRelativeAccuracy().multiply(this.t0)).getReal()));
            RealFieldElement tStart = (RealFieldElement)this.t0.add(0.5 * epsilon);
            this.g0 = this.handler.g(interpolator.getInterpolatedState(tStart));
        }
        this.g0Positive = this.g0.getReal() >= 0.0;
    }

    public boolean evaluateStep(final FieldStepInterpolator<T> interpolator) throws MaxCountExceededException, NoBracketingException {
        this.forward = interpolator.isForward();
        FieldODEStateAndDerivative<T> s1 = interpolator.getCurrentState();
        T t1 = s1.getTime();
        RealFieldElement dt = (RealFieldElement)t1.subtract(this.t0);
        if (((RealFieldElement)((RealFieldElement)dt.abs()).subtract(this.convergence)).getReal() < 0.0) {
            return false;
        }
        int n = FastMath.max(1, (int)FastMath.ceil(FastMath.abs(dt.getReal()) / this.maxCheckInterval));
        RealFieldElement h = (RealFieldElement)dt.divide(n);
        RealFieldUnivariateFunction f = new RealFieldUnivariateFunction<T>(){

            @Override
            public T value(T t) {
                return FieldEventState.this.handler.g(interpolator.getInterpolatedState(t));
            }
        };
        Object ta = this.t0;
        T ga = this.g0;
        for (int i = 0; i < n; ++i) {
            Object tb = i == n - 1 ? t1 : (RealFieldElement)this.t0.add(h.multiply(i + 1));
            T gb = this.handler.g(interpolator.getInterpolatedState(tb));
            if (this.g0Positive ^ gb.getReal() >= 0.0) {
                T root;
                this.increasing = ((RealFieldElement)gb.subtract(ga)).getReal() >= 0.0;
                T t = root = this.forward ? this.solver.solve(this.maxIterationCount, f, ta, tb, AllowedSolution.RIGHT_SIDE) : this.solver.solve(this.maxIterationCount, f, tb, ta, AllowedSolution.LEFT_SIDE);
                if (this.previousEventTime != null && ((RealFieldElement)((RealFieldElement)((RealFieldElement)root.subtract(ta)).abs()).subtract(this.convergence)).getReal() <= 0.0 && ((RealFieldElement)((RealFieldElement)((RealFieldElement)root.subtract(this.previousEventTime)).abs()).subtract(this.convergence)).getReal() <= 0.0) {
                    while (this.g0Positive ^ (ga = f.value(ta = this.forward ? (RealFieldElement)ta.add(this.convergence) : (RealFieldElement)ta.subtract(this.convergence))).getReal() >= 0.0 && this.forward ^ ((RealFieldElement)ta.subtract(tb)).getReal() >= 0.0) {
                    }
                    if (this.forward ^ ((RealFieldElement)ta.subtract(tb)).getReal() >= 0.0) {
                        --i;
                        continue;
                    }
                    this.pendingEventTime = root;
                    this.pendingEvent = true;
                    return true;
                }
                if (this.previousEventTime == null || ((RealFieldElement)((RealFieldElement)((RealFieldElement)this.previousEventTime.subtract(root)).abs()).subtract(this.convergence)).getReal() > 0.0) {
                    this.pendingEventTime = root;
                    this.pendingEvent = true;
                    return true;
                }
                ta = tb;
                ga = gb;
                continue;
            }
            ta = tb;
            ga = gb;
        }
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return false;
    }

    public T getEventTime() {
        return (T)(this.pendingEvent ? this.pendingEventTime : (RealFieldElement)((RealFieldElement)this.t0.getField().getZero()).add(this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY));
    }

    public void stepAccepted(FieldODEStateAndDerivative<T> state) {
        this.t0 = state.getTime();
        this.g0 = this.handler.g(state);
        if (this.pendingEvent && ((RealFieldElement)((RealFieldElement)((RealFieldElement)this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() <= 0.0) {
            this.previousEventTime = state.getTime();
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(state, !(this.increasing ^ this.forward));
        } else {
            this.g0Positive = this.g0.getReal() >= 0.0;
            this.nextAction = Action.CONTINUE;
        }
    }

    public boolean stop() {
        return this.nextAction == Action.STOP;
    }

    public FieldODEState<T> reset(FieldODEStateAndDerivative<T> state) {
        if (!this.pendingEvent || !(((RealFieldElement)((RealFieldElement)((RealFieldElement)this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() <= 0.0)) {
            return null;
        }
        FieldODEState<T> newState = this.nextAction == Action.RESET_STATE ? this.handler.resetState(state) : (this.nextAction == Action.RESET_DERIVATIVES ? state : null);
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return newState;
    }
}

