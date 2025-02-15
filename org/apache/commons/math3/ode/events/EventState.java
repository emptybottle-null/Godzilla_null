/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class EventState {
    private final EventHandler handler;
    private final double maxCheckInterval;
    private final double convergence;
    private final int maxIterationCount;
    private ExpandableStatefulODE expandable;
    private double t0;
    private double g0;
    private boolean g0Positive;
    private boolean pendingEvent;
    private double pendingEventTime;
    private double previousEventTime;
    private boolean forward;
    private boolean increasing;
    private EventHandler.Action nextAction;
    private final UnivariateSolver solver;

    public EventState(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = FastMath.abs(convergence);
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
        this.expandable = null;
        this.t0 = Double.NaN;
        this.g0 = Double.NaN;
        this.g0Positive = true;
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        this.previousEventTime = Double.NaN;
        this.increasing = true;
        this.nextAction = EventHandler.Action.CONTINUE;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public void setExpandable(ExpandableStatefulODE expandable) {
        this.expandable = expandable;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(StepInterpolator interpolator) throws MaxCountExceededException {
        this.t0 = interpolator.getPreviousTime();
        interpolator.setInterpolatedTime(this.t0);
        this.g0 = this.handler.g(this.t0, this.getCompleteState(interpolator));
        if (this.g0 == 0.0) {
            double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0));
            double tStart = this.t0 + 0.5 * epsilon;
            interpolator.setInterpolatedTime(tStart);
            this.g0 = this.handler.g(tStart, this.getCompleteState(interpolator));
        }
        this.g0Positive = this.g0 >= 0.0;
    }

    private double[] getCompleteState(StepInterpolator interpolator) {
        double[] complete = new double[this.expandable.getTotalDimension()];
        this.expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), complete);
        int index = 0;
        for (EquationsMapper secondary : this.expandable.getSecondaryMappers()) {
            secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index++), complete);
        }
        return complete;
    }

    public boolean evaluateStep(final StepInterpolator interpolator) throws MaxCountExceededException, NoBracketingException {
        try {
            this.forward = interpolator.isForward();
            double t1 = interpolator.getCurrentTime();
            double dt = t1 - this.t0;
            if (FastMath.abs(dt) < this.convergence) {
                return false;
            }
            int n = FastMath.max(1, (int)FastMath.ceil(FastMath.abs(dt) / this.maxCheckInterval));
            double h = dt / (double)n;
            UnivariateFunction f = new UnivariateFunction(){

                public double value(double t) throws LocalMaxCountExceededException {
                    try {
                        interpolator.setInterpolatedTime(t);
                        return EventState.this.handler.g(t, EventState.this.getCompleteState(interpolator));
                    } catch (MaxCountExceededException mcee) {
                        throw new LocalMaxCountExceededException(mcee);
                    }
                }
            };
            double ta = this.t0;
            double ga = this.g0;
            for (int i = 0; i < n; ++i) {
                double tb = i == n - 1 ? t1 : this.t0 + (double)(i + 1) * h;
                interpolator.setInterpolatedTime(tb);
                double gb = this.handler.g(tb, this.getCompleteState(interpolator));
                if (this.g0Positive ^ gb >= 0.0) {
                    double root;
                    boolean bl = this.increasing = gb >= ga;
                    if (this.solver instanceof BracketedUnivariateSolver) {
                        BracketedUnivariateSolver bracketing = (BracketedUnivariateSolver)((Object)this.solver);
                        root = this.forward ? bracketing.solve(this.maxIterationCount, f, ta, tb, AllowedSolution.RIGHT_SIDE) : bracketing.solve(this.maxIterationCount, f, tb, ta, AllowedSolution.LEFT_SIDE);
                    } else {
                        double baseRoot = this.forward ? this.solver.solve(this.maxIterationCount, f, ta, tb) : this.solver.solve(this.maxIterationCount, f, tb, ta);
                        int remainingEval = this.maxIterationCount - this.solver.getEvaluations();
                        PegasusSolver bracketing = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
                        double d = root = this.forward ? UnivariateSolverUtils.forceSide(remainingEval, f, bracketing, baseRoot, ta, tb, AllowedSolution.RIGHT_SIDE) : UnivariateSolverUtils.forceSide(remainingEval, f, bracketing, baseRoot, tb, ta, AllowedSolution.LEFT_SIDE);
                    }
                    if (!Double.isNaN(this.previousEventTime) && FastMath.abs(root - ta) <= this.convergence && FastMath.abs(root - this.previousEventTime) <= this.convergence) {
                        while (this.g0Positive ^ (ga = f.value(ta = this.forward ? ta + this.convergence : ta - this.convergence)) >= 0.0 && this.forward ^ ta >= tb) {
                        }
                        if (this.forward ^ ta >= tb) {
                            --i;
                            continue;
                        }
                        this.pendingEventTime = root;
                        this.pendingEvent = true;
                        return true;
                    }
                    if (Double.isNaN(this.previousEventTime) || FastMath.abs(this.previousEventTime - root) > this.convergence) {
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
            this.pendingEventTime = Double.NaN;
            return false;
        } catch (LocalMaxCountExceededException lmcee) {
            throw lmcee.getException();
        }
    }

    public double getEventTime() {
        return this.pendingEvent ? this.pendingEventTime : (this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    public void stepAccepted(double t, double[] y) {
        this.t0 = t;
        this.g0 = this.handler.g(t, y);
        if (this.pendingEvent && FastMath.abs(this.pendingEventTime - t) <= this.convergence) {
            this.previousEventTime = t;
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(t, y, !(this.increasing ^ this.forward));
        } else {
            this.g0Positive = this.g0 >= 0.0;
            this.nextAction = EventHandler.Action.CONTINUE;
        }
    }

    public boolean stop() {
        return this.nextAction == EventHandler.Action.STOP;
    }

    public boolean reset(double t, double[] y) {
        if (!this.pendingEvent || !(FastMath.abs(this.pendingEventTime - t) <= this.convergence)) {
            return false;
        }
        if (this.nextAction == EventHandler.Action.RESET_STATE) {
            this.handler.resetState(t, y);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        return this.nextAction == EventHandler.Action.RESET_STATE || this.nextAction == EventHandler.Action.RESET_DERIVATIVES;
    }

    private static class LocalMaxCountExceededException
    extends RuntimeException {
        private static final long serialVersionUID = 20120901L;
        private final MaxCountExceededException wrapped;

        LocalMaxCountExceededException(MaxCountExceededException exception) {
            this.wrapped = exception;
        }

        public MaxCountExceededException getException() {
            return this.wrapped;
        }
    }
}

