/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.optim.OptimizationData;

@Deprecated
public class ModelFunctionJacobian
implements OptimizationData {
    private final MultivariateMatrixFunction jacobian;

    public ModelFunctionJacobian(MultivariateMatrixFunction j) {
        this.jacobian = j;
    }

    public MultivariateMatrixFunction getModelFunctionJacobian() {
        return this.jacobian;
    }
}

