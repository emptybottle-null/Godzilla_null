/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface OptimizationProblem<PAIR> {
    public Incrementor getEvaluationCounter();

    public Incrementor getIterationCounter();

    public ConvergenceChecker<PAIR> getConvergenceChecker();
}

