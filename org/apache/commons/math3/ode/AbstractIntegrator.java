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
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.events.EventState;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractIntegrator
implements FirstOrderIntegrator {
    protected Collection<StepHandler> stepHandlers;
    protected double stepStart;
    protected double stepSize;
    protected boolean isLastStep;
    protected boolean resetOccurred;
    private Collection<EventState> eventsStates;
    private boolean statesInitialized;
    private final String name;
    private IntegerSequence.Incrementor evaluations;
    private transient ExpandableStatefulODE expandable;

    public AbstractIntegrator(String name) {
        this.name = name;
        this.stepHandlers = new ArrayList<StepHandler>();
        this.stepStart = Double.NaN;
        this.stepSize = Double.NaN;
        this.eventsStates = new ArrayList<EventState>();
        this.statesInitialized = false;
        this.evaluations = IntegerSequence.Incrementor.create().withMaximalCount(Integer.MAX_VALUE);
    }

    protected AbstractIntegrator() {
        this(null);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addStepHandler(StepHandler handler) {
        this.stepHandlers.add(handler);
    }

    @Override
    public Collection<StepHandler> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    @Override
    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    @Override
    public void addEventHandler(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        this.addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, new BracketingNthOrderBrentSolver(convergence, 5));
    }

    @Override
    public void addEventHandler(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
        this.eventsStates.add(new EventState(handler, maxCheckInterval, convergence, maxIterationCount, solver));
    }

    @Override
    public Collection<EventHandler> getEventHandlers() {
        ArrayList<EventHandler> list = new ArrayList<EventHandler>(this.eventsStates.size());
        for (EventState state : this.eventsStates) {
            list.add(state.getEventHandler());
        }
        return Collections.unmodifiableCollection(list);
    }

    @Override
    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    @Override
    public double getCurrentStepStart() {
        return this.stepStart;
    }

    @Override
    public double getCurrentSignedStepsize() {
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

    protected void initIntegration(double t0, double[] y0, double t) {
        this.evaluations = this.evaluations.withStart(0);
        for (EventState state : this.eventsStates) {
            state.setExpandable(this.expandable);
            state.getEventHandler().init(t0, y0, t);
        }
        for (StepHandler handler : this.stepHandlers) {
            handler.init(t0, y0, t);
        }
        this.setStateInitialized(false);
    }

    protected void setEquations(ExpandableStatefulODE equations) {
        this.expandable = equations;
    }

    protected ExpandableStatefulODE getExpandable() {
        return this.expandable;
    }

    @Deprecated
    protected Incrementor getEvaluationsCounter() {
        return Incrementor.wrap(this.evaluations);
    }

    protected IntegerSequence.Incrementor getCounter() {
        return this.evaluations;
    }

    @Override
    public double integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        if (y0.length != equations.getDimension()) {
            throw new DimensionMismatchException(y0.length, equations.getDimension());
        }
        if (y.length != equations.getDimension()) {
            throw new DimensionMismatchException(y.length, equations.getDimension());
        }
        ExpandableStatefulODE expandableODE = new ExpandableStatefulODE(equations);
        expandableODE.setTime(t0);
        expandableODE.setPrimaryState(y0);
        this.integrate(expandableODE, t);
        System.arraycopy(expandableODE.getPrimaryState(), 0, y, 0, y.length);
        return expandableODE.getTime();
    }

    public abstract void integrate(ExpandableStatefulODE var1, double var2) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException, NullPointerException {
        this.evaluations.increment();
        this.expandable.computeDerivatives(t, y, yDot);
    }

    protected void setStateInitialized(boolean stateInitialized) {
        this.statesInitialized = stateInitialized;
    }

    protected double acceptStep(AbstractStepInterpolator interpolator, double[] y, double[] yDot, double tEnd) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
        double previousT = interpolator.getGlobalPreviousTime();
        double currentT = interpolator.getGlobalCurrentTime();
        if (!this.statesInitialized) {
            for (EventState state : this.eventsStates) {
                state.reinitializeBegin(interpolator);
            }
            this.statesInitialized = true;
        }
        final int orderingSign = interpolator.isForward() ? 1 : -1;
        TreeSet<EventState> occurringEvents = new TreeSet<EventState>(new Comparator<EventState>(){

            @Override
            public int compare(EventState es0, EventState es1) {
                return orderingSign * Double.compare(es0.getEventTime(), es1.getEventTime());
            }
        });
        for (EventState state : this.eventsStates) {
            if (!state.evaluateStep(interpolator)) continue;
            occurringEvents.add(state);
        }
        while (!occurringEvents.isEmpty()) {
            Iterator iterator = occurringEvents.iterator();
            EventState currentEvent = (EventState)iterator.next();
            iterator.remove();
            double eventT = currentEvent.getEventTime();
            interpolator.setSoftPreviousTime(previousT);
            interpolator.setSoftCurrentTime(eventT);
            interpolator.setInterpolatedTime(eventT);
            double[] eventYComplete = new double[y.length];
            this.expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), eventYComplete);
            int index = 0;
            for (EquationsMapper secondary : this.expandable.getSecondaryMappers()) {
                secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index++), eventYComplete);
            }
            for (EventState state : this.eventsStates) {
                state.stepAccepted(eventT, eventYComplete);
                this.isLastStep = this.isLastStep || state.stop();
            }
            for (StepHandler handler : this.stepHandlers) {
                handler.handleStep(interpolator, this.isLastStep);
            }
            if (this.isLastStep) {
                System.arraycopy(eventYComplete, 0, y, 0, y.length);
                return eventT;
            }
            boolean needReset = false;
            this.resetOccurred = false;
            needReset = currentEvent.reset(eventT, eventYComplete);
            if (needReset) {
                interpolator.setInterpolatedTime(eventT);
                System.arraycopy(eventYComplete, 0, y, 0, y.length);
                this.computeDerivatives(eventT, y, yDot);
                this.resetOccurred = true;
                return eventT;
            }
            previousT = eventT;
            interpolator.setSoftPreviousTime(eventT);
            interpolator.setSoftCurrentTime(currentT);
            if (!currentEvent.evaluateStep(interpolator)) continue;
            occurringEvents.add(currentEvent);
        }
        interpolator.setInterpolatedTime(currentT);
        double[] currentY = new double[y.length];
        this.expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), currentY);
        int index = 0;
        for (EquationsMapper secondary : this.expandable.getSecondaryMappers()) {
            secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index++), currentY);
        }
        for (EventState state : this.eventsStates) {
            state.stepAccepted(currentT, currentY);
            this.isLastStep = this.isLastStep || state.stop();
        }
        this.isLastStep = this.isLastStep || Precision.equals(currentT, tEnd, 1);
        for (StepHandler handler : this.stepHandlers) {
            handler.handleStep(interpolator, this.isLastStep);
        }
        return currentT;
    }

    protected void sanityChecks(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException {
        double threshold = 1000.0 * FastMath.ulp(FastMath.max(FastMath.abs(equations.getTime()), FastMath.abs(t)));
        double dt = FastMath.abs(equations.getTime() - t);
        if (dt <= threshold) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, (Number)dt, threshold, false);
        }
    }
}

