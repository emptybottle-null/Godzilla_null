/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;

public class GradientFunction
implements MultivariateVectorFunction {
    private final MultivariateDifferentiableFunction f;

    public GradientFunction(MultivariateDifferentiableFunction f) {
        this.f = f;
    }

    public double[] value(double[] point) {
        DerivativeStructure[] dsX = new DerivativeStructure[point.length];
        for (int i = 0; i < point.length; ++i) {
            dsX[i] = new DerivativeStructure(point.length, 1, i, point[i]);
        }
        DerivativeStructure dsY = this.f.value(dsX);
        double[] y = new double[point.length];
        int[] orders = new int[point.length];
        for (int i = 0; i < point.length; ++i) {
            orders[i] = 1;
            y[i] = dsY.getPartialDerivative(orders);
            orders[i] = 0;
        }
        return y;
    }
}

