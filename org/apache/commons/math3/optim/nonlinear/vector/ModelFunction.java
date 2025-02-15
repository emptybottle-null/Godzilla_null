/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.OptimizationData;

@Deprecated
public class ModelFunction
implements OptimizationData {
    private final MultivariateVectorFunction model;

    public ModelFunction(MultivariateVectorFunction m) {
        this.model = m;
    }

    public MultivariateVectorFunction getModelFunction() {
        return this.model;
    }
}

