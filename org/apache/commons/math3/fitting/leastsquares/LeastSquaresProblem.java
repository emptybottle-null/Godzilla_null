/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.OptimizationProblem;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface LeastSquaresProblem
extends OptimizationProblem<Evaluation> {
    public RealVector getStart();

    public int getObservationSize();

    public int getParameterSize();

    public Evaluation evaluate(RealVector var1);

    public static interface Evaluation {
        public RealMatrix getCovariances(double var1);

        public RealVector getSigma(double var1);

        public double getRMS();

        public RealMatrix getJacobian();

        public double getCost();

        public RealVector getResiduals();

        public RealVector getPoint();
    }
}

