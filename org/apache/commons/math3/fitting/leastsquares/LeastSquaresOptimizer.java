/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;

public interface LeastSquaresOptimizer {
    public Optimum optimize(LeastSquaresProblem var1);

    public static interface Optimum
    extends LeastSquaresProblem.Evaluation {
        public int getEvaluations();

        public int getIterations();
    }
}

