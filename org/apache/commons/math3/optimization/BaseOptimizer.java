/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.optimization.ConvergenceChecker;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface BaseOptimizer<PAIR> {
    public int getMaxEvaluations();

    public int getEvaluations();

    public ConvergenceChecker<PAIR> getConvergenceChecker();
}

