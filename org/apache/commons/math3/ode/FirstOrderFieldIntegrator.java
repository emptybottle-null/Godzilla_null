/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.Collection;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FirstOrderFieldIntegrator<T extends RealFieldElement<T>> {
    public String getName();

    public void addStepHandler(FieldStepHandler<T> var1);

    public Collection<FieldStepHandler<T>> getStepHandlers();

    public void clearStepHandlers();

    public void addEventHandler(FieldEventHandler<T> var1, double var2, double var4, int var6);

    public void addEventHandler(FieldEventHandler<T> var1, double var2, double var4, int var6, BracketedRealFieldUnivariateSolver<T> var7);

    public Collection<FieldEventHandler<T>> getEventHandlers();

    public void clearEventHandlers();

    public FieldODEStateAndDerivative<T> getCurrentStepStart();

    public T getCurrentSignedStepsize();

    public void setMaxEvaluations(int var1);

    public int getMaxEvaluations();

    public int getEvaluations();

    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> var1, FieldODEState<T> var2, T var3) throws NumberIsTooSmallException, MaxCountExceededException, NoBracketingException;
}

