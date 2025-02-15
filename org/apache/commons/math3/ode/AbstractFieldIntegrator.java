/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldIntegrator;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.events.FieldEventState;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IntegerSequence;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractFieldIntegrator<T extends RealFieldElement<T>>
implements FirstOrderFieldIntegrator<T> {
    private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14;
    private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15;
    private Collection<FieldStepHandler<T>> stepHandlers;
    private FieldODEStateAndDerivative<T> stepStart;
    private T stepSize;
    private boolean isLastStep;
    private boolean resetOccurred;
    private final Field<T> field;
    private Collection<FieldEventState<T>> eventsStates;
    private boolean statesInitialized;
    private final String name;
    private IntegerSequence.Incrementor evaluations;
    private transient FieldExpandableODE<T> equations;

    protected AbstractFieldIntegrator(Field<T> field, String name) {
        this.field = field;
        this.name = name;
        this.stepHandlers = new ArrayList<FieldStepHandler<T>>();
        this.stepStart = null;
        this.stepSize = null;
        this.eventsStates = new ArrayList<FieldEventState<T>>();
        this.statesInitialized = false;
        this.evaluations = IntegerSequence.Incrementor.create().withMaximalCount(Integer.MAX_VALUE);
    }

    public Field<T> getField() {
        return this.field;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addStepHandler(FieldStepHandler<T> handler) {
        this.stepHandlers.add(handler);
    }

    @Override
    public Collection<FieldStepHandler<T>> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    @Override
    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    @Override
    public void addEventHandler(FieldEventHandler<T> handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        this.addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, new FieldBracketingNthOrderBrentSolver<RealFieldElement>((RealFieldElement)((RealFieldElement)this.field.getZero()).add(1.0E-14), (RealFieldElement)((RealFieldElement)this.field.getZero()).add(convergence), (RealFieldElement)((RealFieldElement)this.field.getZero()).add(1.0E-15), 5));
    }

    @Override
    public void addEventHandler(FieldEventHandler<T> handler, double maxCheckInterval, double convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver<T> solver) {
        this.eventsStates.add(new FieldEventState<RealFieldElement>(handler, maxCheckInterval, (RealFieldElement)((RealFieldElement)this.field.getZero()).add(convergence), maxIterationCount, solver));
    }

    @Override
    public Collection<FieldEventHandler<T>> getEventHandlers() {
        ArrayList<FieldEventHandler<T>> list = new ArrayList<FieldEventHandler<T>>(this.eventsStates.size());
        for (FieldEventState<T> state : this.eventsStates) {
            list.add(state.getEventHandler());
        }
        return Collections.unmodifiableCollection(list);
    }

    @Override
    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    @Override
    public FieldODEStateAndDerivative<T> getCurrentStepStart() {
        return this.stepStart;
    }

    @Override
    public T getCurrentSignedStepsize() {
        return this.stepSize;
    }

    @Override
    public void setMaxEvaluations(int maxEvaluations) {
        this.evaluations = this.evaluations.withMaximalCount(maxEvaluations < 0 ? Integer.MAX_VALUE : maxEvaluations);
    }

    @Override
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    protected FieldODEStateAndDerivative<T> initIntegration(FieldExpandableODE<T> eqn, T t0, T[] y0, T t) {
        this.equations = eqn;
        this.evaluations = this.evaluations.withStart(0);
        eqn.init((RealFieldElement)t0, (RealFieldElement[])y0, (RealFieldElement)t);
        RealFieldElement[] y0Dot = this.computeDerivatives((RealFieldElement)t0, (RealFieldElement[])y0);
        FieldODEStateAndDerivative state0 = new FieldODEStateAndDerivative(t0, y0, y0Dot);
        for (FieldEventState<T> fieldEventState : this.eventsStates) {
            fieldEventState.getEventHandler().init(state0, t);
        }
        for (FieldStepHandler fieldStepHandler : this.stepHandlers) {
            fieldStepHandler.init(state0, t);
        }
        this.setStateInitialized(false);
        return state0;
    }

    protected FieldExpandableODE<T> getEquations() {
        return this.equations;
    }

    protected IntegerSequence.Incrementor getEvaluationsCounter() {
        return this.evaluations;
    }

    public T[] computeDerivatives(T t, T[] y) throws DimensionMismatchException, MaxCountExceededException, NullPointerException {
        this.evaluations.increment();
        return this.equations.computeDerivatives((RealFieldElement)t, (RealFieldElement[])y);
    }

    protected void setStateInitialized(boolean stateInitialized) {
        this.statesInitialized = stateInitialized;
    }

    protected FieldODEStateAndDerivative<T> acceptStep(AbstractFieldStepInterpolator<T> interpolator, T tEnd) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
        FieldODEStateAndDerivative<T> previousState = interpolator.getGlobalPreviousState();
        FieldODEStateAndDerivative<T> currentState = interpolator.getGlobalCurrentState();
        if (!this.statesInitialized) {
            for (FieldEventState<T> state : this.eventsStates) {
                state.reinitializeBegin(interpolator);
            }
            this.statesInitialized = true;
        }
        final int orderingSign = interpolator.isForward() ? 1 : -1;
        TreeSet<FieldEventState> occurringEvents = new TreeSet<FieldEventState>(new Comparator<FieldEventState<T>>(){

            @Override
            public int compare(FieldEventState<T> es0, FieldEventState<T> es1) {
                return orderingSign * Double.compare(es0.getEventTime().getReal(), es1.getEventTime().getReal());
            }
        });
        for (FieldEventState<T> state : this.eventsStates) {
            if (!state.evaluateStep(interpolator)) continue;
            occurringEvents.add(state);
        }
        AbstractFieldStepInterpolator<T> restricted = interpolator;
        while (!occurringEvents.isEmpty()) {
            Iterator iterator = occurringEvents.iterator();
            FieldEventState fieldEventState = (FieldEventState)iterator.next();
            iterator.remove();
            FieldODEStateAndDerivative<T> eventState = restricted.getInterpolatedState(fieldEventState.getEventTime());
            restricted = restricted.restrictStep(previousState, eventState);
            for (FieldEventState<T> fieldEventState2 : this.eventsStates) {
                fieldEventState2.stepAccepted(eventState);
                this.isLastStep = this.isLastStep || fieldEventState2.stop();
            }
            for (FieldStepHandler fieldStepHandler : this.stepHandlers) {
                fieldStepHandler.handleStep(restricted, this.isLastStep);
            }
            if (this.isLastStep) {
                return eventState;
            }
            FieldODEState<T> newState = null;
            this.resetOccurred = false;
            for (FieldEventState<T> state : this.eventsStates) {
                newState = state.reset(eventState);
                if (newState == null) continue;
                RealFieldElement[] y = this.equations.getMapper().mapState(newState);
                RealFieldElement[] yDot = this.computeDerivatives((RealFieldElement)newState.getTime(), y);
                this.resetOccurred = true;
                return this.equations.getMapper().mapStateAndDerivative((RealFieldElement)newState.getTime(), y, yDot);
            }
            previousState = eventState;
            if (!fieldEventState.evaluateStep(restricted = restricted.restrictStep(eventState, currentState))) continue;
            occurringEvents.add(fieldEventState);
        }
        for (FieldEventState<T> fieldEventState : this.eventsStates) {
            fieldEventState.stepAccepted(currentState);
            this.isLastStep = this.isLastStep || fieldEventState.stop();
        }
        this.isLastStep = this.isLastStep || ((RealFieldElement)((RealFieldElement)currentState.getTime().subtract(tEnd)).abs()).getReal() <= FastMath.ulp(tEnd.getReal());
        for (FieldStepHandler fieldStepHandler : this.stepHandlers) {
            fieldStepHandler.handleStep(restricted, this.isLastStep);
        }
        return currentState;
    }

    protected void sanityChecks(FieldODEState<T> eqn, T t) throws NumberIsTooSmallException, DimensionMismatchException {
        double threshold = 1000.0 * FastMath.ulp(FastMath.max(FastMath.abs(eqn.getTime().getReal()), FastMath.abs(t.getReal())));
        double dt = ((RealFieldElement)((RealFieldElement)eqn.getTime().subtract(t)).abs()).getReal();
        if (dt <= threshold) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, (Number)dt, threshold, false);
        }
    }

    protected boolean resetOccurred() {
        return this.resetOccurred;
    }

    protected void setStepSize(T stepSize) {
        this.stepSize = stepSize;
    }

    protected T getStepSize() {
        return this.stepSize;
    }

    protected void setStepStart(FieldODEStateAndDerivative<T> stepStart) {
        this.stepStart = stepStart;
    }

    protected FieldODEStateAndDerivative<T> getStepStart() {
        return this.stepStart;
    }

    protected void setIsLastStep(boolean isLastStep) {
        this.isLastStep = isLastStep;
    }

    protected boolean isLastStep() {
        return this.isLastStep;
    }
}

