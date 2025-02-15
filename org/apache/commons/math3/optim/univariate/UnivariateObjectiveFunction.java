/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.OptimizationData;

public class UnivariateObjectiveFunction
implements OptimizationData {
    private final UnivariateFunction function;

    public UnivariateObjectiveFunction(UnivariateFunction f) {
        this.function = f;
    }

    public UnivariateFunction getObjectiveFunction() {
        return this.function;
    }
}

