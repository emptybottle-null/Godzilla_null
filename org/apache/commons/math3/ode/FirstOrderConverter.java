/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.SecondOrderDifferentialEquations;

public class FirstOrderConverter
implements FirstOrderDifferentialEquations {
    private final SecondOrderDifferentialEquations equations;
    private final int dimension;
    private final double[] z;
    private final double[] zDot;
    private final double[] zDDot;

    public FirstOrderConverter(SecondOrderDifferentialEquations equations) {
        this.equations = equations;
        this.dimension = equations.getDimension();
        this.z = new double[this.dimension];
        this.zDot = new double[this.dimension];
        this.zDDot = new double[this.dimension];
    }

    public int getDimension() {
        return 2 * this.dimension;
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) {
        System.arraycopy(y, 0, this.z, 0, this.dimension);
        System.arraycopy(y, this.dimension, this.zDot, 0, this.dimension);
        this.equations.computeSecondDerivatives(t, this.z, this.zDot, this.zDDot);
        System.arraycopy(this.zDot, 0, yDot, 0, this.dimension);
        System.arraycopy(this.zDDot, 0, yDot, this.dimension, this.dimension);
    }
}

