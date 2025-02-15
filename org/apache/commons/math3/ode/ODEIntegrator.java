/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.Collection;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface ODEIntegrator {
    public String getName();

    public void addStepHandler(StepHandler var1);

    public Collection<StepHandler> getStepHandlers();

    public void clearStepHandlers();

    public void addEventHandler(EventHandler var1, double var2, double var4, int var6);

    public void addEventHandler(EventHandler var1, double var2, double var4, int var6, UnivariateSolver var7);

    public Collection<EventHandler> getEventHandlers();

    public void clearEventHandlers();

    public double getCurrentStepStart();

    public double getCurrentSignedStepsize();

    public void setMaxEvaluations(int var1);

    public int getMaxEvaluations();

    public int getEvaluations();
}

